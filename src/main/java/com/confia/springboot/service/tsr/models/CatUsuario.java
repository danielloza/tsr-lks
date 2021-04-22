package com.confia.springboot.service.tsr.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Seguridad.Usuarios")
public class CatUsuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UsuarioID")
	private Long usuarioID;
	@Column(name = "Usuario")
	private String usuario;
	@Column(name = "Nombre")
	private String nombre;

	public Long getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(Long usuarioID) {
		this.usuarioID = usuarioID;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
