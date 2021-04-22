package com.confia.springboot.service.tsr.models;

import java.io.Serializable;
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
@Table(name = "Bancos.CatalogoCuentasBancos")
public class CatCuentasBancos implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CuentaBancoID")
	private Integer cuentaBancoID;
	
	@Column(name = "NumeroCuenta", unique = true)
	private String numeroCuenta;
	
	@Column(name="Saldo")
	private double saldo;
	
	@JsonIgnoreProperties({"tipoCuenta", "naturaleza", "rubro", "empresa", "monedaSat", 
		"acumulaCuenta","activa","fechaRegistro", "nombre",  "hibernateLazyInitializer", "handler"})
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CuentaID", unique = true)
	private CatCtaCont cuentaContable;
	
	@JsonIgnoreProperties({"catCuentasBancos","hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BancoID")
	private CatBancos banco;
	
	@Column(name="Activo")
	private boolean activo;
	

	public Integer getCuentaBancoID() {
		return cuentaBancoID;
	}


	public void setCuentaBancoID(Integer cuentaBancoID) {
		this.cuentaBancoID = cuentaBancoID;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public CatCtaCont getCuentaContable() {
		return cuentaContable;
	}

	public void setCuentaContable(CatCtaCont cuentaContable) {
		this.cuentaContable = cuentaContable;
	}

	public void setBanco(CatBancos banco) {
		this.banco = banco;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	private static final long serialVersionUID = 1L;

}
