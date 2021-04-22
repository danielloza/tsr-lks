package com.confia.springboot.service.tsr.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.confia.springboot.service.tsr.models.CatCuentasBancos;

public interface ICatCuentasBancosDAO extends JpaRepository<CatCuentasBancos, Integer>{

}
