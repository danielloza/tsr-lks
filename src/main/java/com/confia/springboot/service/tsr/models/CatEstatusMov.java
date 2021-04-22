package com.confia.springboot.service.tsr.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tesoreria.EstatusMovimiento")
public class CatEstatusMov implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CatEstatusMovID")
	private Long estatusMovId;
	@Column(name = "Caracter")
	private String caracter;
	@Column(name = "Descripcion")
	private String Descripcion;

	public Long getEstatusMovId() {
		return estatusMovId;
	}

	public void setEstatusMovId(Long estatusMovId) {
		this.estatusMovId = estatusMovId;
	}

	public String getCaracter() {
		return caracter;
	}

	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
