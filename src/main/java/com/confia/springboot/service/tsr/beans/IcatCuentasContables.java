package com.confia.springboot.service.tsr.beans;

import java.util.Date;

public interface IcatCuentasContables {
	
	int getBancoID(); 
	String getBanco(); 
	boolean getActivoBanco(); 
	int getCuentaBancoID(); 
	String getNumeroCuenta(); 
	boolean getActivoCuentaBanco(); 
	int getCuentaID(); 
	String getCuentaContable(); 
	String getNombreCuentaContable(); 
	String getParametrosID(); 
	String getFechaRegistro(); 
	String getMontoMin(); 
	String getMontoMax(); 
	String getActivoParametros();

}
