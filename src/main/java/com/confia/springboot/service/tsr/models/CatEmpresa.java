package com.confia.springboot.service.tsr.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "General.Empresas")
public class CatEmpresa implements Serializable {

	@Id
	@Column(name = "empresaId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "empresaNombre")
	private String nombre;
	@Column(name = "empresaRFC")
	private String rfc;
	@Column(name = "empresaDireccionFiscal")
	private String dirFiscal;
	@Column(name = "empresaRegistroPatronal")
	private String regPatronal;
	@Column(name = "empresaRazonSocial")
	private String razonSocial;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getDirFiscal() {
		return dirFiscal;
	}

	public void setDirFiscal(String dirFiscal) {
		this.dirFiscal = dirFiscal;
	}

	public String getRegPatronal() {
		return regPatronal;
	}

	public void setRegPatronal(String regPatronal) {
		this.regPatronal = regPatronal;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
