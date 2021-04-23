package com.confia.springboot.service.tsr.service;

import java.util.List;

import com.confia.springboot.service.tsr.models.CatBancos;
import com.confia.springboot.service.tsr.models.CatParamExeBancos;

public interface ICatParamExBancosService {
	
	public List<CatParamExeBancos> findAllParam();
	public CatParamExeBancos findByID(Integer id);
	public List<CatParamExeBancos> findByBanck(Integer banckID);
	public CatParamExeBancos addParam(CatParamExeBancos peb);
	public void delete(Integer id);
	

}
