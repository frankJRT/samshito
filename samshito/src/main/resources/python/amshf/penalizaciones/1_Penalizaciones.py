#!/usr/bin/env python
# coding: utf-8

import io
import os 
import base64
import time
import numpy as np
import pandas as pd
import mysql.connector
import datetime
import os
import pandas as pd
from dateutil.relativedelta import relativedelta
import matplotlib.pyplot as plt
import seaborn as sns
import openpyxl
import xlsxwriter
import sys

import smtplib,ssl
from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from email.mime.text import MIMEText
from email.utils import formatdate
from email import encoders

send_from = 'no_reply@hito.com.mx'
sender = 'no_reply@hito.com.mx'
send_to = ['javier.trejo@hito.com.mx','am_shf@hito.com.mx']
receivers = ['javier.trejo@hito.com.mx','am_shf@hito.com.mx']
subject ="Generador de Penalizaciones"


dbhost="192.168.200.83"
dbuser="comp_userPh"
dbpassword="hito+21"

print ('Number of Arguments:', len(sys.argv), 'arguments.')
print ('Argument List:', str(sys.argv))

class Datetime64Converter(mysql.connector.conversion.MySQLConverter):
    """ A mysql.connector Converter that handles datetime64 types """

    def _timestamp_to_mysql(self, value):
        return value.value


def getCarteras():
    sql = ('select I_Cartera_Comportamiento, N_Cartera_Comportamiento_Clave, N_Cartera_Comportamiento,'
        'I_Administrador, I_Intermediario_Financiero from comp_shf.am_comp_cat_cartera_comportamiento '
        ' where X_Cartera_Comportamiento_Status !="DEL"')
    print(sql)
    mycursor = mydb.cursor()
    mycursor.execute(sql)
    myresult = mycursor.fetchall()
    mycursor.close()
    return myresult

def getCartera(idCartera):
    sql = ('select I_Cartera_Comportamiento, N_Cartera_Comportamiento_Clave, N_Cartera_Comportamiento,'
        'I_Administrador, I_Intermediario_Financiero from comp_shf.am_comp_cat_cartera_comportamiento '
        ' where X_Cartera_Comportamiento_Status !="DEL" and I_Cartera_Comportamiento={}')
    print(sql)
    mycursor = mydb.cursor()
    mycursor.execute(sql.format(idCartera))
    myresult = mycursor.fetchall()
    mycursor.close()
    return myresult

def getCarteraPrimario(idCartera):
    sql = ('SELECT distinct I_Primario FROM comp_shf.am_pena_cat_cartera_reporte' 
            ' where I_Primario is not Null and I_Cartera={}')
    #print(sql)
    mycursor = mydb.cursor()
    mycursor.execute(sql.format(idCartera))
    myresult = mycursor.fetchall()
    mycursor.close()
    return myresult

def callSpPenalizaciones(cartera,periodo,primario):
    sql = ('call comp_shf.sp_calculo_penalizaciones_reportes({},{},{},10)')
    print(sql.format(cartera,periodo,primario))
    #mydb.set_converter_class(Datetime64Converter)
    mycursor = mydb.cursor()
    
    valor = True
    try:
        mycursor.execute(sql.format(cartera,periodo,primario),multi=True)
        mydb.commit()
    except:
        valor = False
        print("error")
    mycursor.close()
    return valor

def call_find_all_sp(args):
    myresult = None
    try:
        mydb.set_converter_class(Datetime64Converter)
        mycursor = mydb.cursor()        
        mycursor.callproc('comp_shf.sp_calculo_penalizaciones_reportes_automatized', args)
        #for result in mycursor.stored_results():
        #    myresult =result.fetchall()

    except Error as e:
        print(e)

    finally:
        mycursor.close()
    return myresult

def getResultadoPenalizaciones(cartera,periodo,primario):
    sql = (' select cat.I_Cartera,cat.I_Primario, cat.Q_Reporte, cat.E_Tipo_Cartera,cat.E_Reportes, reportes.D_Recibido, '
            ' reportes.E_Archivo, reportes.Q_dia_habil,reportes.I_Periodo'
            ' from comp_shf.am_pena_cat_cartera_reporte cat left join '
            ' ('
            ' select Q_Reporte,E_Tipo_Cartera,E_Reportes,D_Recibido,E_Archivo,Q_dia_habil, I_Cartera, I_Periodo'
            ' from comp_shf.am_pena_tra_cartera_reporte'
            ' where I_cartera =  {0} and I_periodo={1} and I_Primario = {2}'
            ' ) reportes on cat.E_Reportes = reportes.E_Reportes '
            ' and cat.I_Cartera = reportes.I_Cartera and cat.E_Tipo_Cartera = reportes.E_Tipo_Cartera'
            ' where cat.I_Cartera = {0} and cat.I_Primario = {2}'
            ' order by cat.Q_Reporte asc , reportes.D_Recibido desc')
    
    print(sql)
    mycursor = mydb.cursor()
    mycursor.execute(sql.format(cartera,periodo,primario))
    myresult = mycursor.fetchall()
    mycursor.close()
    return myresult
    

def callProcesa(carteras,periodo):
    for cartera in carteras:
        car =cartera[0]
        
        dfprimario = pd.DataFrame(getCarteraPrimario(car),columns=['I_primario'])
        for row in dfprimario.iterrows():
            primario =row[1]['I_primario']
            file_name = ("Penalizaciones_{0}_{1}_{2}.xlsx").format(str(car),str(periodo),str(primario))
            print(file_name)
            
            #callSpPenalizaciones(car,periodo,primario)
            call_find_all_sp([int(car),int(periodo),int(primario),int(10)])
            dfPenalizaciones =pd.DataFrame(getResultadoPenalizaciones(car,periodo,primario),columns=['I_Cartera','I_Primario',
                                                                            'Q_Reporte', 'E_Tipo_Cartera', 
                                                                            'E_Reportes','D_Recibido','E_Archivo',
                                                                            'Q_dia_habil','I_Periodo'])
            _year = int(str(periodo)[:-2])
            _mont = int(str(periodo)[-2:])
            
            newdfPenal= dfPenalizaciones.copy()
            newdfPenal = newdfPenal.dropna()
            #newdfPenal['Q_dia_habil'] = newdfPenal['Q_dia_habil'].fillna(1)
            newdfPenal['D_Recibido'] = newdfPenal['D_Recibido'].apply(lambda x: x.strftime('%Y-%m-%d'))

            #newdfPenal['habil_days_to_give'] = newdfPenal.apply(
            #    lambda row : np.busday_count(datetime.date(_year, _mont, 1),
            #                                 datetime.date(_year, _mont, int(row['Q_dia_habil']))),axis=1)

            newdfPenal['habil_days_getted'] = newdfPenal.apply(
                lambda row : np.busday_count(datetime.date(_year, _mont, 1),
                                             row['D_Recibido']
                                              ),axis=1)
            
            newdfPenal['days_dif'] =newdfPenal['Q_dia_habil']-newdfPenal['habil_days_getted']
            
            dfgroupPenalizaciones = newdfPenal.groupby(['I_Cartera','I_Primario','Q_Reporte',
                                                        'E_Tipo_Cartera','E_Reportes','Q_dia_habil']).agg(
                                                        min_date_recibed=pd.NamedAgg(column="D_Recibido", aggfunc="min"),
                                                        max_date_recibed=pd.NamedAgg(column="D_Recibido", aggfunc="max"),
                                                        min_day=pd.NamedAgg(column="habil_days_getted", aggfunc="min"),
                                                        max_day=pd.NamedAgg(column="habil_days_getted", aggfunc="max"),
                                                        min_day_dif=pd.NamedAgg(column="days_dif", aggfunc="min"),)
            
            dfgroupPenalizaciones['OnTime'] = dfgroupPenalizaciones['min_day_dif'].apply(lambda x: False if x <1 else True)
            
            dfResumen = dfgroupPenalizaciones.copy().reset_index()
            dfResumen['OnTime'] =np.where((dfResumen['Q_dia_habil']-dfResumen['min_day']) < 1 ,False,True)
            dfallReportes = dfPenalizaciones[['I_Cartera', 'I_Primario', 'Q_Reporte', 'E_Tipo_Cartera', 'E_Reportes']]
            dfallReportes = dfallReportes.drop_duplicates()           
            
            result = pd.concat([dfallReportes.set_index(['I_Cartera', 'I_Primario', 'Q_Reporte', 'E_Tipo_Cartera']),
            dfResumen.set_index(['I_Cartera', 'I_Primario', 'Q_Reporte', 'E_Tipo_Cartera'])], axis=1)
            result = result.reset_index()
            
            result2 = result.groupby(['E_Tipo_Cartera','OnTime']).size()
            result2 = result2.reset_index()
            result2 = result2.pivot(index='E_Tipo_Cartera',columns=['OnTime'], values=0)
            
            #generamos los array para guardar en Excel
            lista = list()
            lista.append(result)
            lista.append(result2)
            
            
            
            Excelwriter = pd.ExcelWriter(file_name,engine="xlsxwriter")
            a=0
            for i in lista:
                i.to_excel(Excelwriter, sheet_name="Sheet" +str(primario)+"_"+str(periodo)+str(a),index=True)
                a+=1
            #And finally save the file
            Excelwriter.save()
            
            text ="Este es tu generador automatizado de penalizaciones"
            msg = MIMEMultipart()
            msg['From'] = send_from
            msg['To'] = ', '.join(send_to)
            #msg['Date'] = formatdate(localtime = True)
            msg['Subject'] = subject
            msg.attach(MIMEText(text))
            part = MIMEBase('application', "octet-stream")
            part.set_payload(open(file_name, "rb").read())
            encoders.encode_base64(part)
            part.add_header('Content-Disposition', 'attachment; filename="'+file_name+'"')
            msg.attach(part)


            smtpObj = smtplib.SMTP('localhost')
            smtpObj.sendmail(sender, receivers, msg.as_string())
            smtpObj.quit()
            
            
if len(sys.argv)==3:
    mydb = mysql.connector.connect(
      host=dbhost,
      user=dbuser,
      password=dbpassword
    )
  
    try:
        cartera = int(sys.argv[1])
        periodo = int(sys.argv[2])
        

        lscarteras=None;
        
        if cartera >0:
            lscarteras = getCartera(cartera)
        else:
            lscarteras = getCarteras()
        #carteras = getCarteras()
    except:
        print('error')
    callProcesa(lscarteras,periodo)
    
    mydb.close()

