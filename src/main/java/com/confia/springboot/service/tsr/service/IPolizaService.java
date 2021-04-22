package com.confia.springboot.service.tsr.service;

import java.util.List;

import com.confia.springboot.service.tsr.models.CatTipoPoliza;
import com.confia.springboot.service.tsr.models.Poliza;

public interface IPolizaService {
	
	public List<Poliza> findAll();

	public Poliza findById(Long id);

	public Poliza save(Poliza poliza);

	public void delete(Long id);

	public List<CatTipoPoliza> findAllCatTipoPoliza();
	
}
