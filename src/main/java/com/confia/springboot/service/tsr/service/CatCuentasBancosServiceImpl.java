package com.confia.springboot.service.tsr.service;

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

}
