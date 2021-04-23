package com.confia.springboot.service.tsr.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.confia.springboot.service.tsr.models.CatCuentasBancos;

public interface ICatCuentasBancosDAO extends JpaRepository<CatCuentasBancos, Integer>{
	
	@Query("SELECT c FROM CatCuentasBancos c WHERE c.bancoID = ?1")
	public List<CatCuentasBancos> findByBanck(int bancoID);


}
