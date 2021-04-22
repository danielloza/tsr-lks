package com.confia.springboot.service.tsr.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tesoreria.TipoPoliza")
public class CatTipoPoliza implements Serializable {
	

	@Id
	@Column(name = "TipoPolizaID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long tipoPolizaID;
	@Column(name = "Descripcion")
	private String descripcion;
	
	
	public Long getTipoPolizaID() {
		return tipoPolizaID;
	}
	public void setTipoPolizaID(Long tipoPolizaID) {
		this.tipoPolizaID = tipoPolizaID;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
