package com.confia.springboot.service.tsr.beans;

import java.util.Date;

public interface ICatParamExeBancos {

	int getBancoID(); 
	String getBanco(); 
	boolean getActivoBanco(); 
	int getCuentaBancoID(); 
	String getNumeroCuenta(); 
	boolean getActivoCuentaBanco(); 
	int getCuentaID(); 
	String getCuentaContable(); 
	String getNombreCuentaContable(); 
	int getParametrosID(); 
	Date getFechaRegistro(); 
	double getMontoMin(); 
	double getMontoMax(); 
	boolean getActivoParametros();
	
}
