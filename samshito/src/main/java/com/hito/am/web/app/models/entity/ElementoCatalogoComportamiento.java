package com.hito.am.web.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "am_comp_cat_elemento_catalogo_comportamiento")
public class ElementoCatalogoComportamiento implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6623145046093927559L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer I_Elemento_Catalogo_Comportamiento;
	@Temporal(TemporalType.DATE)
	private Date D_Elemento_Catalogo_Comportamiento; 
	private String E_Elemento_Catalogo_Comportamiento_Decripcion; 
	private String N_Elemento_Catalogo_Comportamiento_Clave; 
	private String N_Elemento_Catalogo_Comportamiento; 
	private String X_Elemento_Catalogo_Comportamiento_Status; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="I_Catalogo_Comportamiento")
	private CatalogoComportamiento catalogoComportamiento;

	public Integer getI_Elemento_Catalogo_Comportamiento() {
		return I_Elemento_Catalogo_Comportamiento;
	}

	public void setI_Elemento_Catalogo_Comportamiento(Integer i_Elemento_Catalogo_Comportamiento) {
		I_Elemento_Catalogo_Comportamiento = i_Elemento_Catalogo_Comportamiento;
	}

	public Date getD_Elemento_Catalogo_Comportamiento() {
		return D_Elemento_Catalogo_Comportamiento;
	}

	public void setD_Elemento_Catalogo_Comportamiento(Date d_Elemento_Catalogo_Comportamiento) {
		D_Elemento_Catalogo_Comportamiento = d_Elemento_Catalogo_Comportamiento;
	}

	public String getE_Elemento_Catalogo_Comportamiento_Decripcion() {
		return E_Elemento_Catalogo_Comportamiento_Decripcion;
	}

	public void setE_Elemento_Catalogo_Comportamiento_Decripcion(String e_Elemento_Catalogo_Comportamiento_Decripcion) {
		E_Elemento_Catalogo_Comportamiento_Decripcion = e_Elemento_Catalogo_Comportamiento_Decripcion;
	}

	public String getN_Elemento_Catalogo_Comportamiento_Clave() {
		return N_Elemento_Catalogo_Comportamiento_Clave;
	}

	public void setN_Elemento_Catalogo_Comportamiento_Clave(String n_Elemento_Catalogo_Comportamiento_Clave) {
		N_Elemento_Catalogo_Comportamiento_Clave = n_Elemento_Catalogo_Comportamiento_Clave;
	}

	public String getN_Elemento_Catalogo_Comportamiento() {
		return N_Elemento_Catalogo_Comportamiento;
	}

	public void setN_Elemento_Catalogo_Comportamiento(String n_Elemento_Catalogo_Comportamiento) {
		N_Elemento_Catalogo_Comportamiento = n_Elemento_Catalogo_Comportamiento;
	}

	public String getX_Elemento_Catalogo_Comportamiento_Status() {
		return X_Elemento_Catalogo_Comportamiento_Status;
	}

	public void setX_Elemento_Catalogo_Comportamiento_Status(String x_Elemento_Catalogo_Comportamiento_Status) {
		X_Elemento_Catalogo_Comportamiento_Status = x_Elemento_Catalogo_Comportamiento_Status;
	}

	public CatalogoComportamiento getCatalogoComportamiento() {
		return catalogoComportamiento;
	}

	public void setCatalogoComportamiento(CatalogoComportamiento catalogoComportamiento) {
		this.catalogoComportamiento = catalogoComportamiento;
	}	
}
