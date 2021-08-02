package com.hito.am.web.app.models.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ModelCartera implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1317875434641633981L;

	private Integer I_Cartera_Comportamiento; 
	
	@Temporal(TemporalType.DATE)
	private Date  D_Cartera_Comportamiento;
	
	private String E_Cartera_Comportamiento_Descripcion; 
	private String N_Cartera_Comportamiento_Clave; 
	private String N_Cartera_Comportamiento; 
	private String X_Cartera_Comportamiento_Status; 
	private String I_Administrador; 
	private String I_Intermediario_Financiero; 
	private Integer I_Archivo_Comportamieto;
	
	public Integer getI_Cartera_Comportamiento() {
		return I_Cartera_Comportamiento;
	}
	public void setI_Cartera_Comportamiento(Integer i_Cartera_Comportamiento) {
		I_Cartera_Comportamiento = i_Cartera_Comportamiento;
	}
	public Date getD_Cartera_Comportamiento() {
		return D_Cartera_Comportamiento;
	}
	public void setD_Cartera_Comportamiento(Date d_Cartera_Comportamiento) {
		D_Cartera_Comportamiento = d_Cartera_Comportamiento;
	}
	public String getE_Cartera_Comportamiento_Descripcion() {
		return E_Cartera_Comportamiento_Descripcion;
	}
	public void setE_Cartera_Comportamiento_Descripcion(String e_Cartera_Comportamiento_Descripcion) {
		E_Cartera_Comportamiento_Descripcion = e_Cartera_Comportamiento_Descripcion;
	}
	public String getN_Cartera_Comportamiento_Clave() {
		return N_Cartera_Comportamiento_Clave;
	}
	public void setN_Cartera_Comportamiento_Clave(String n_Cartera_Comportamiento_Clave) {
		N_Cartera_Comportamiento_Clave = n_Cartera_Comportamiento_Clave;
	}
	public String getN_Cartera_Comportamiento() {
		return N_Cartera_Comportamiento;
	}
	public void setN_Cartera_Comportamiento(String n_Cartera_Comportamiento) {
		N_Cartera_Comportamiento = n_Cartera_Comportamiento;
	}
	public String getX_Cartera_Comportamiento_Status() {
		return X_Cartera_Comportamiento_Status;
	}
	public void setX_Cartera_Comportamiento_Status(String x_Cartera_Comportamiento_Status) {
		X_Cartera_Comportamiento_Status = x_Cartera_Comportamiento_Status;
	}
	public String getI_Administrador() {
		return I_Administrador;
	}
	public void setI_Administrador(String i_Administrador) {
		I_Administrador = i_Administrador;
	}
	public String getI_Intermediario_Financiero() {
		return I_Intermediario_Financiero;
	}
	public void setI_Intermediario_Financiero(String i_Intermediario_Financiero) {
		I_Intermediario_Financiero = i_Intermediario_Financiero;
	}
	public Integer getI_Archivo_Comportamieto() {
		return I_Archivo_Comportamieto;
	}
	public void setI_Archivo_Comportamieto(Integer i_Archivo_Comportamieto) {
		I_Archivo_Comportamieto = i_Archivo_Comportamieto;
	}
	
	
	
}
