package com.confia.springboot.service.tsr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.confia.springboot.service.tsr.dao.IPolizaDao;
import com.confia.springboot.service.tsr.models.CatTipoCuenta;
import com.confia.springboot.service.tsr.models.CatTipoPoliza;
import com.confia.springboot.service.tsr.models.Poliza;

@Service
public class PolizaService implements IPolizaService {

	@Autowired
	private IPolizaDao polizaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Poliza> findAll() {
		return (List<Poliza>) polizaDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Poliza findById(Long id) {
		return polizaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Poliza save(Poliza Poliza) {
		return polizaDao.save(Poliza);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		polizaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CatTipoPoliza> findAllCatTipoPoliza() {
		return polizaDao.findAllCatTipoPoliza();
	}

}
