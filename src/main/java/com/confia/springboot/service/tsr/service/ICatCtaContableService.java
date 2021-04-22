package com.confia.springboot.service.tsr.service;

import java.util.List;



import com.confia.springboot.service.tsr.models.CatCtaCont;
import com.confia.springboot.service.tsr.models.CatCtaContRef;
import com.confia.springboot.service.tsr.models.CatEmpresa;
import com.confia.springboot.service.tsr.models.CatMonedaSat;
import com.confia.springboot.service.tsr.models.CatNaturaleza;
import com.confia.springboot.service.tsr.models.CatRubro;
import com.confia.springboot.service.tsr.models.CatTipoCuenta;
import com.confia.springboot.service.tsr.utils.Response;



public interface ICatCtaContableService {
	
	public List<CatCtaCont> findAll();

	public CatCtaCont findById(Long id);

	public CatCtaCont save(CatCtaCont catCtaCont);

	public void delete(Long id);

	public List<CatCtaContRef> findAllCatCtasContables();
    
	public List<CatEmpresa> findAllCatEmpresas();

	public List<CatMonedaSat> findAllCatMonedaSat();

	public List<CatNaturaleza> findAllCatNaturaleza();	

	public List<CatRubro> findAllCatRubro();

	public List<CatTipoCuenta> findAllCatTipoCuenta();
	
	public Response findResponse(int num1, int num2);
	
}

