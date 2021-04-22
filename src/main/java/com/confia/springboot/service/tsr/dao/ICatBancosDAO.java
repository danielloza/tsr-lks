package com.confia.springboot.service.tsr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.confia.springboot.service.tsr.models.CatBancos;

@Repository
public interface ICatBancosDAO extends JpaRepository<CatBancos, Integer>{

	
}
