package com.confia.springboot.service.tsr.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tesoreria.Calculadora")
public class Calculadora implements Serializable {
	
	@Id
	@Column(name = "CalculadoraID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int calculadoraID;
	
	@Column(name="Numero1")
	private int numero1;
	@Column(name="Numero2")
	private int numero2;
	
	
	public int getCalculadoraID() {
		return calculadoraID;
	}
	public void setCalculadoraID(int calculadoraID) {
		this.calculadoraID = calculadoraID;
	}
	public int getNumero1() {
		return numero1;
	}
	public void setNumero1(int numero1) {
		this.numero1 = numero1;
	}
	public int getNumero2() {
		return numero2;
	}
	public void setNumero2(int numero2) {
		this.numero2 = numero2;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
}
