package com.confia.springboot.service.tsr.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.confia.springboot.service.tsr.dao.ICatBancosDAO;
import com.confia.springboot.service.tsr.dao.ICatCuentasBancosDAO;
import com.confia.springboot.service.tsr.dao.ICatParamExBancosDAO;
import com.confia.springboot.service.tsr.models.CatBancos;
import com.confia.springboot.service.tsr.models.CatCuentasBancos;
import com.confia.springboot.service.tsr.models.CatParamExeBancos;

@Service
public class CatBancosServiceImpl implements ICatBancosService{

	@Autowired
	public ICatBancosDAO cBancosDao;
	
	@Override
	@Transactional
	public CatBancos addBancos(CatBancos cbancos) {
		
		CatBancos banco = new CatBancos();
		CatBancos banco2 = new CatBancos();
		
		/*Obtenemos los datos del banco del objeto enviado desde la api*/
		banco.setNombre(cbancos.getNombre());
		banco.setActivo(cbancos.isActivo());
		
		/*Guardamos el objeto Bancos*/
		banco2 = cBancosDao.save(banco);
		
		
		return cBancosDao.findById(banco2.getBancoID()).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CatBancos> findAllBancos() {
		
		return cBancosDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public CatBancos findById(Integer bancoID) {
		
		return cBancosDao.findById(bancoID).orElse(null);
	}

	@Override
	@Transactional
	public CatBancos update(CatBancos catBancos) {
		

		cBancosDao.save(catBancos);
		
		
		return cBancosDao.findById(catBancos.getBancoID()).orElse(null);
	}

}
