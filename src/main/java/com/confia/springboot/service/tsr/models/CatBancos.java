package com.confia.springboot.service.tsr.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="Bancos.CatalogoBancos")
public class CatBancos implements Serializable{
	
	
	@Id
	@Column(name = "BancoID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bancoID;
	
	@NotNull(message = "No puede ser Nulo el Nombre")
	@Column(name = "Nombre", unique = true)
	private String nombre;
	
	@NotNull(message = "No puede ser Nulo el estatus")
	@Column(name = "Activo")
	private boolean activo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "banco", cascade = CascadeType.ALL)
	private List<CatCuentasBancos> catCuentasBancos;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "catalogoBanco", cascade = CascadeType.ALL)
	private List<CatParamExeBancos> paramBancos;
	
	public Integer getBancoID() {
		return bancoID;
	}

	public void setBancoID(Integer bancoID) {
		this.bancoID = bancoID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public List<CatParamExeBancos> getParamBancos() {
		return paramBancos;
	}

	public void setParamBancos(List<CatParamExeBancos> paramBancos) {
		this.paramBancos = paramBancos;
	}

	public List<CatCuentasBancos> getCatCuentasBancos() {
		return catCuentasBancos;
	}

	public void setCatCuentasBancos(List<CatCuentasBancos> catCuentasBancos) {
		this.catCuentasBancos = catCuentasBancos;
	}


	private static final long serialVersionUID = 1L;

}
