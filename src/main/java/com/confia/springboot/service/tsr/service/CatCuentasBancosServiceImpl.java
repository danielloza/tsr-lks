package com.confia.springboot.service.tsr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.confia.springboot.service.tsr.dao.ICatCuentasBancosDAO;
import com.confia.springboot.service.tsr.models.CatCuentasBancos;

@Service
public class CatCuentasBancosServiceImpl implements ICatCuentasBancosService{
	
	@Autowired
	public ICatCuentasBancosDAO ccBancosDao;

	@Override
	@Transactional
	public CatCuentasBancos addAcount(CatCuentasBancos ccBancos) {
		
		return ccBancosDao.save(ccBancos);
	}

	@Override
	public List<CatCuentasBancos> findAllAccount() {
		
		return ccBancosDao.findAll();
	}

	@Override
	public CatCuentasBancos findByAccount(Integer accountID) {
		
		return ccBancosDao.findById(accountID).orElse(null);
	}

	@Override
	public List<CatCuentasBancos> findByBanck(Integer bancoID) {
		
		return (List<CatCuentasBancos>) ccBancosDao.findByBanck(bancoID);
	}

}
