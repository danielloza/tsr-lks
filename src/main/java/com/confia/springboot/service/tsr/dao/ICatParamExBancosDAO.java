package com.confia.springboot.service.tsr.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.confia.springboot.service.tsr.beans.ICatParamExeBancos;
import com.confia.springboot.service.tsr.models.CatParamExeBancos;

public interface ICatParamExBancosDAO extends JpaRepository<CatParamExeBancos, Integer>{

	@Query(value = "SELECT BancoID, Banco, ActivoBanco, CuentaBancoID, NumeroCuenta, ActivoCuentaBanco, CuentaID, CuentaContable, "
			+ "NombreCuentaContable, ParametrosID, FechaRegistro, MontoMin, MontoMax, ActivoParametros "
			+ "FROM Bancos.fnSpsParametrosBancos(?1)", nativeQuery = true)
	public List<ICatParamExeBancos> findByBanck(int bancoID);
	
}
