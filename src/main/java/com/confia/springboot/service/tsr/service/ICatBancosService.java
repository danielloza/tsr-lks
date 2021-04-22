package com.confia.springboot.service.tsr.service;

import java.util.List;

import com.confia.springboot.service.tsr.models.CatBancos;

public interface ICatBancosService {
	
	public CatBancos addBancos(CatBancos cbancos);
	public List<CatBancos> findAllBancos();
	public CatBancos findById(Integer bancoID);
	public CatBancos update(CatBancos cbancos);

}
