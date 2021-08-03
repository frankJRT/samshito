package com.hito.am.web.app.models.model;

public class ModelPenalizacionesProcess {
	
	private Integer cartera;
	private Integer periodo;
	private String  proceso;
	
	
	
	public ModelPenalizacionesProcess() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ModelPenalizacionesProcess(Integer cartera, Integer periodo, String proceso) {
		super();
		this.cartera = cartera;
		this.periodo = periodo;
		this.proceso = proceso;
	}
	public Integer getCartera() {
		return cartera;
	}
	public void setCartera(Integer cartera) {
		this.cartera = cartera;
	}
	public Integer getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	public String getProceso() {
		return proceso;
	}
	public void setProceso(String proceso) {
		this.proceso = proceso;
	}
	
	 

}
