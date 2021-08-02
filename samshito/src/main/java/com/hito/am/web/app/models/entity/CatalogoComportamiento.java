package com.hito.am.web.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "am_comp_cat_catalogo_comportamiento")
public class CatalogoComportamiento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5465644425106368157L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer I_Catalogo_Comportamiento;

	@Temporal(TemporalType.DATE)
	private Date D_Catalogo_Comportamiento;

	private String N_Catalogo_Comportamiento;

	private String N_Catalogo_Comportamiento_Clave;

	private String E_Catalogo_Comportamiento_Decripcion;

	private String E_Catalogo_Comportamiento_Tipo_Dato;

	private String X_Catalogo_Comportamiento_Status;

	public Integer getI_Catalogo_Comportamiento() {
		return I_Catalogo_Comportamiento;
	}

	public void setI_Catalogo_Comportamiento(Integer i_Catalogo_Comportamiento) {
		I_Catalogo_Comportamiento = i_Catalogo_Comportamiento;
	}

	public Date getD_Catalogo_Comportamiento() {
		return D_Catalogo_Comportamiento;
	}

	public void setD_Catalogo_Comportamiento(Date d_Catalogo_Comportamiento) {
		D_Catalogo_Comportamiento = d_Catalogo_Comportamiento;
	}

	public String getN_Catalogo_Comportamiento() {
		return N_Catalogo_Comportamiento;
	}

	public void setN_Catalogo_Comportamiento(String n_Catalogo_Comportamiento) {
		N_Catalogo_Comportamiento = n_Catalogo_Comportamiento;
	}

	public String getN_Catalogo_Comportamiento_Clave() {
		return N_Catalogo_Comportamiento_Clave;
	}

	public void setN_Catalogo_Comportamiento_Clave(String n_Catalogo_Comportamiento_Clave) {
		N_Catalogo_Comportamiento_Clave = n_Catalogo_Comportamiento_Clave;
	}

	public String getE_Catalogo_Comportamiento_Decripcion() {
		return E_Catalogo_Comportamiento_Decripcion;
	}

	public void setE_Catalogo_Comportamiento_Decripcion(String e_Catalogo_Comportamiento_Decripcion) {
		E_Catalogo_Comportamiento_Decripcion = e_Catalogo_Comportamiento_Decripcion;
	}

	public String getE_Catalogo_Comportamiento_Tipo_Dato() {
		return E_Catalogo_Comportamiento_Tipo_Dato;
	}

	public void setE_Catalogo_Comportamiento_Tipo_Dato(String e_Catalogo_Comportamiento_Tipo_Dato) {
		E_Catalogo_Comportamiento_Tipo_Dato = e_Catalogo_Comportamiento_Tipo_Dato;
	}

	public String getX_Catalogo_Comportamiento_Status() {
		return X_Catalogo_Comportamiento_Status;
	}

	public void setX_Catalogo_Comportamiento_Status(String x_Catalogo_Comportamiento_Status) {
		X_Catalogo_Comportamiento_Status = x_Catalogo_Comportamiento_Status;
	}

}
