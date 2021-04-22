package com.confia.springboot.service.tsr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.confia.springboot.service.tsr.models.CatTipoPoliza;
import com.confia.springboot.service.tsr.models.Poliza;

public interface IPolizaDao extends JpaRepository<Poliza, Long> {


	@Query("From CatTipoPoliza")
	public List<CatTipoPoliza> findAllCatTipoPoliza();		
	
}
