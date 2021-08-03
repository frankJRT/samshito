package com.hito.am.web.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "am_comp_cat_periodo")
public class Periodo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3664581231666427413L;

	@Id
	private Integer I_Periodo;

	@Temporal(TemporalType.DATE)
	private Date D_Periodo_Inicio;

	@Temporal(TemporalType.DATE)
	private Date D_Periodo_Final;

	@Column(name="Q_Periodo_Consecutivo")
	private Integer consecutivo;

	public Integer getI_Periodo() {
		return I_Periodo;
	}

	public void setI_Periodo(Integer i_Periodo) {
		I_Periodo = i_Periodo;
	}

	public Date getD_Periodo_Inicio() {
		return D_Periodo_Inicio;
	}

	public void setD_Periodo_Inicio(Date d_Periodo_Inicio) {
		D_Periodo_Inicio = d_Periodo_Inicio;
	}

	public Date getD_Periodo_Final() {
		return D_Periodo_Final;
	}

	public void setD_Periodo_Final(Date d_Periodo_Final) {
		D_Periodo_Final = d_Periodo_Final;
	}

	public Integer getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}

	

}
