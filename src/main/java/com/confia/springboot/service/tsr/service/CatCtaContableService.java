package com.confia.springboot.service.tsr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.confia.springboot.service.tsr.dao.ICatCtaContDao;
import com.confia.springboot.service.tsr.dao.IResponseDao;
import com.confia.springboot.service.tsr.models.CatCtaCont;
import com.confia.springboot.service.tsr.models.CatCtaContRef;
import com.confia.springboot.service.tsr.models.CatEmpresa;
import com.confia.springboot.service.tsr.models.CatMonedaSat;
import com.confia.springboot.service.tsr.models.CatNaturaleza;
import com.confia.springboot.service.tsr.models.CatRubro;
import com.confia.springboot.service.tsr.models.CatTipoCuenta;
import com.confia.springboot.service.tsr.utils.Response;



@Service
public class CatCtaContableService implements ICatCtaContableService{

	@Autowired
	private ICatCtaContDao catCtaContDao;
	
	@Autowired
	private IResponseDao responseDao;

	@Override
	@Transactional(readOnly = true)
	public List<CatCtaCont> findAll() {
		return (List<CatCtaCont>) catCtaContDao.findAll(Sort.by("cuenta").ascending());
	}
	
	@Override
	@Transactional(readOnly = true)
	public CatCtaCont findById(Long id) {
		return catCtaContDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public CatCtaCont save(CatCtaCont catCtaCont) {
		return catCtaContDao.save(catCtaCont);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		catCtaContDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CatCtaContRef> findAllCatCtasContables() {
		return catCtaContDao.findAllCatCtasContables();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CatEmpresa> findAllCatEmpresas() {
		return catCtaContDao.findAllCatEmpresas();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CatMonedaSat> findAllCatMonedaSat() {
		return catCtaContDao.findAllCatMonedaSat();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CatNaturaleza> findAllCatNaturaleza() {
		return catCtaContDao.findAllCatNaturaleza();
	}	
	
	@Override
	@Transactional(readOnly = true)
	public List<CatRubro> findAllCatRubro() {
		return catCtaContDao.findAllCatRubro();
	}	
	
	@Override
	@Transactional(readOnly = true)
	public List<CatTipoCuenta> findAllCatTipoCuenta() {
		return catCtaContDao.findAllCatTipoCuenta();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Response findResponse(int num1, int num2) {
		return responseDao.findResponse(num1, num2);
	}

}
