package com.confia.springboot.service.tsr.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.JoinColumn;


@Entity
@Table(name = "Tesoreria.CuentasContables")
public class CatCtaCont implements Serializable {

	@Id
	@Column(name = "CuentaID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "no puede estar vacio")
	@Column(name = "Cuenta", 
	        unique=true)
	private String cuenta;
	@NotEmpty(message = "no puede estar vacio")
	@Column(name = "Nombre")
	private String nombre;
	

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AcumulaCuentaID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CatCtaContRef acumulaCuenta;
	

	@NotNull(message="el tipo cuenta no puede ser vacia")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CatTipoCuenta tipoCuenta;

	
	@NotNull(message="la naturaleza no puede ser vacia")	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NaturalezaID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CatNaturaleza naturaleza;

	@NotNull(message="el rubro no puede ser vacia")	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RubroID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CatRubro rubro;

	@NotNull(message="la empresa no puede ser vacia")	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "empresaId")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", 
							"rfc", "dirFiscal", "regPatronal", "razonSocial" })
	private CatEmpresa empresa;

	@NotNull(message="la moneda no puede ser vacia")	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatMonedaSatID") 
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", 
							"tipoCambio","fecha","cveMonedaSat"})
	private CatMonedaSat monedaSat;

	

	@NotNull(message="la activa no puede ser vacia")
	private Boolean activa;

	@NotNull(message="la fechaRegistro no puede ser vacia")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistro;

	@PrePersist
	public void prePersist() {
		this.fechaRegistro = new Date();
	}


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


	public CatCtaContRef getAcumulaCuenta() {
		return acumulaCuenta;
	}


	public void setAcumulaCuenta(CatCtaContRef acumulaCuenta) {
		this.acumulaCuenta = acumulaCuenta;
	}
	


	public CatTipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}


	public void setTipoCuenta(CatTipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}


	public CatNaturaleza getNaturaleza() {
		return naturaleza;
	}


	public void setNaturaleza(CatNaturaleza naturaleza) {
		this.naturaleza = naturaleza;
	}


	public CatRubro getRubro() {
		return rubro;
	}


	public void setRubro(CatRubro rubro) {
		this.rubro = rubro;
	}


	public CatEmpresa getEmpresa() {
		return empresa;
	}


	public void setEmpresa(CatEmpresa empresa) {
		this.empresa = empresa;
	}


	public CatMonedaSat getMonedaSat() {
		return monedaSat;
	}


	public void setMonedaSat(CatMonedaSat monedaSat) {
		this.monedaSat = monedaSat;
	}


	public Boolean getActiva() {
		return activa;
	}


	public void setActiva(Boolean activa) {
		this.activa = activa;
	}


	public Date getFechaRegistro() {
		return fechaRegistro;
	}


	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
