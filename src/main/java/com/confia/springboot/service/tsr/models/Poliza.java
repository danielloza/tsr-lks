package com.confia.springboot.service.tsr.models;

import java.util.Date;
import java.util.List;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Tesoreria.Polizas")
public class Poliza implements Serializable {

	@Id
	@Column(name = "PolizaID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long polizaId;
	
	@Column(name = "Referencia")
	private Long referencia;

	@NotNull(message = "el usuario de la póliza no puede estar vacio")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UsuarioId")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CatUsuario usuario;

	@Column(name = "Numero")
	private Long numero;
	
	@Column(name = "Fecha")
	private Date fecha;
	@Column(name = "Concepto")
	private String concepto;

	@NotNull(message = "el estatus de la póliza no puede estar vacio")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CatEstatusMovID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CatEstatusMov estatus;

	@NotNull(message = "el tipo de póliza no puede estar vacio")
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TipoPolizaID")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private CatTipoPoliza tipoPoliza;
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy="movimientoPoliza",
			 cascade = CascadeType.ALL)
	private List<MovimientoPoliza> listMovPoliza;

	public Long getPolizaId() {
		return polizaId;
	}

	public void setPolizaId(Long polizaId) {
		this.polizaId = polizaId;
	}

	public Long getReferencia() {
		return referencia;
	}

	public void setReferencia(Long referencia) {
		this.referencia = referencia;
	}

	public CatUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(CatUsuario usuario) {
		this.usuario = usuario;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public CatEstatusMov getEstatus() {
		return estatus;
	}

	public void setEstatus(CatEstatusMov estatus) {
		this.estatus = estatus;
	}

	public CatTipoPoliza getTipoPoliza() {
		return tipoPoliza;
	}

	public void setTipoPoliza(CatTipoPoliza tipoPoliza) {
		this.tipoPoliza = tipoPoliza;
	}


	public List<MovimientoPoliza> getListMovPoliza() {
		return listMovPoliza;
	}

	public void setListMovPoliza(List<MovimientoPoliza> listMovPoliza) {
		for (MovimientoPoliza movPoliza : listMovPoliza) { 			
			movPoliza.setMovimientoPoliza(this); 		
		} 		

		this.listMovPoliza = listMovPoliza;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
