package com.confia.springboot.service.tsr.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Tesoreria.CuentasContables")
public class CatCtaContRef implements Serializable{


	@Id
	@Column(name = "CuentaID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "Cuenta")
	private String cuenta;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "TipoID")
	private Long tipoId;

	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getCuenta() {
		return cuenta;
	}



	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public Long getTipoId() {
		return tipoId;
	}



	public void setTipoId(Long tipoId) {
		this.tipoId = tipoId;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
}
