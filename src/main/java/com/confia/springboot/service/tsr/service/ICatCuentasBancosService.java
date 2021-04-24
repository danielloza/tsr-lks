package com.confia.springboot.service.tsr.service;

import java.util.List;

import com.confia.springboot.service.tsr.beans.IcatCuentasContables;
import com.confia.springboot.service.tsr.models.CatCuentasBancos;

public interface ICatCuentasBancosService {

	public CatCuentasBancos addAcount(CatCuentasBancos ccBancos);
	public List<IcatCuentasContables> findAllAccount();
	public CatCuentasBancos findByAccount(Integer accountID);
	public List<CatCuentasBancos> findByBanck(Integer bancoID);
	
}
