package com.confia.springboot.service.tsr.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name="Tesoreria.ParamExcedentBancos")
public class CatParamExeBancos implements Serializable{

	@Id
	@Column(name = "ParametrosID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int parametrosID;
	
	@Column(name = "FechaRegistro")
	@Temporal(TemporalType.DATE)
	private Date fechaRegistro;
	
	@Column(name = "MontoMin")
	@NotNull(message = "No puede ser Nulo el Monto Minimo")
	private double montoMin;
	
	@NotNull(message = "No puede ser Nulo el Monto Máximo")
	@Column(name = "MontoMax")
	private double montoMax;
	
	@Column(name = "CuentaBancoID", unique = true)
	private Integer cuentaBancoID;
	
	@JoinColumn(name = "BancoID")
	private Integer bancoID;
	
	@NotNull(message = "No puede ser Nulo el campo Activo")
	@Column(name = "Activo")
	private boolean activo;
	
	
	public int getParametrosID() {
		return parametrosID;
	}

	public void setParametrosID(int parametrosID) {
		this.parametrosID = parametrosID;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	@PrePersist
	public void prePersist() {
		this.fechaRegistro = new Date();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.fechaRegistro = new Date();
	}

	public double getMontoMin() {
		return montoMin;
	}

	public void setMontoMin(double montoMin) {
		this.montoMin = montoMin;
	}

	public double getMontoMax() {
		return montoMax;
	}

	public void setMontoMax(double montoMax) {
		this.montoMax = montoMax;
	}

	public Integer getCuentaBanco() {
		return cuentaBancoID;
	}

	public void setCuentaBancoID(Integer cuentaBancoID) {
		this.cuentaBancoID = cuentaBancoID;
	}

	public Integer getBancoID() {
		return bancoID;
	}
	
	public void setBancoID(Integer bancoID) {
		this.bancoID = bancoID;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	private static final long serialVersionUID = 1L;

}
