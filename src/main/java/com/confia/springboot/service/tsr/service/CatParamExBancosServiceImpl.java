package com.confia.springboot.service.tsr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.confia.springboot.service.tsr.beans.ICatParamExeBancos;
import com.confia.springboot.service.tsr.dao.ICatParamExBancosDAO;
import com.confia.springboot.service.tsr.models.CatParamExeBancos;

@Service
public class CatParamExBancosServiceImpl implements ICatParamExBancosService{

	@Autowired
	ICatParamExBancosDAO pebDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<CatParamExeBancos> findAllParam() {
		
		return pebDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public CatParamExeBancos findByID(Integer id) {
	
		return pebDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public CatParamExeBancos addParam(CatParamExeBancos peb) {
		
		return pebDao.save(peb);
	}

	@Override
	@Transactional
	public void delete(Integer id) {
		pebDao.deleteById(id);
		
	}

	@Override
	public List<ICatParamExeBancos> findByBanck(Integer banckID) {
		
		return pebDao.findByBanck(banckID);
	}



}
