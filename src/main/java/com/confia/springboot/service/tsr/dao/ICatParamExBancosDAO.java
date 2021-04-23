package com.confia.springboot.service.tsr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.confia.springboot.service.tsr.models.CatParamExeBancos;

public interface ICatParamExBancosDAO extends JpaRepository<CatParamExeBancos, Integer>{

	@Query("SELECT c FROM CatParamExeBancos c WHERE c.bancoID = ?1")
	public List<CatParamExeBancos> findByBanck(int bancoID);
}
