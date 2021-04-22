package com.confia.springboot.service.tsr.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Tesoreria.MovimientoPolizas")
public class MovimientoPoliza implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MovimientoPolizaID")
	private Long movPolID;

	
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name = "PolizaId", nullable = false, referencedColumnName = "PolizaId")
	private Poliza movimientoPoliza;

	
	
	@Column(name = "Descripcion")
	private String descripcion;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CuentaID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CatCtaContRef cuenta;

	@Column(name = "Referencia")
	private String referencia;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatEstatusMovID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CatEstatusMov estatus;

	@Column(name = "Debe")
	private Double debe;

	@Column(name = "Haber")
	private Double haber;

	public Long getMovPolID() {
		return movPolID;
	}

	public void setMovPolID(Long movPolID) {
		this.movPolID = movPolID;
	}

	public void setMovimientoPoliza(Poliza movimientoPoliza) {
		this.movimientoPoliza = movimientoPoliza;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public CatCtaContRef getCuenta() {
		return cuenta;
	}

	public void setCuenta(CatCtaContRef cuenta) {
		this.cuenta = cuenta;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public CatEstatusMov getEstatus() {
		return estatus;
	}

	public void setEstatus(CatEstatusMov estatus) {
		this.estatus = estatus;
	}

	public Double getDebe() {
		return debe;
	}

	public void setDebe(Double debe) {
		this.debe = debe;
	}

	public Double getHaber() {
		return haber;
	}

	public void setHaber(Double haber) {
		this.haber = haber;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
