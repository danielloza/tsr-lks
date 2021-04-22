package com.confia.springboot.service.tsr.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tesoreria.MonedaSAT")
public class CatMonedaSat implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CatMonedaSatID")
	private Long monedaId;
	@Column(name = "NombreMoneda")
	private String nombreMoneda;
	@Column(name = "TipoCambio")
	private BigDecimal tipoCambio;
	@Column(name = "Fecha")
	private Date fecha;
	@Column(name = "ClaveMonedaSat")
	private String cveMonedaSat;

	public Long getMonedaId() {
		return monedaId;
	}

	public void setMonedaId(Long monedaId) {
		this.monedaId = monedaId;
	}

	public String getNombreMoneda() {
		return nombreMoneda;
	}

	public void setNombreMoneda(String nombreMoneda) {
		this.nombreMoneda = nombreMoneda;
	}

	public BigDecimal getTipoCambio() {
		return tipoCambio;
	}

	public void setTipoCambio(BigDecimal tipoCambio) {
		this.tipoCambio = tipoCambio;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCveMonedaSat() {
		return cveMonedaSat;
	}

	public void setCveMonedaSat(String cveMonedaSat) {
		this.cveMonedaSat = cveMonedaSat;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
