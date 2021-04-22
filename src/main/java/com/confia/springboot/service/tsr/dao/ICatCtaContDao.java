package com.confia.springboot.service.tsr.dao;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.confia.springboot.service.tsr.models.CatCtaCont;
import com.confia.springboot.service.tsr.models.CatCtaContRef;
import com.confia.springboot.service.tsr.models.CatEmpresa;
import com.confia.springboot.service.tsr.models.CatMonedaSat;
import com.confia.springboot.service.tsr.models.CatNaturaleza;
import com.confia.springboot.service.tsr.models.CatRubro;
import com.confia.springboot.service.tsr.models.CatTipoCuenta;



public interface ICatCtaContDao extends JpaRepository<CatCtaCont, Long>{

	@Query("select c from CatCtaContRef c where c.tipoId = 2")
	public List<CatCtaContRef> findAllCatCtasContables();
	
	@Query("from CatEmpresa")
	public List<CatEmpresa> findAllCatEmpresas();
	
	@Query("from CatMonedaSat")
	public List<CatMonedaSat> findAllCatMonedaSat();
	
	@Query("from CatNaturaleza")
	public List<CatNaturaleza> findAllCatNaturaleza();	
	
	@Query("from CatRubro")
	public List<CatRubro> findAllCatRubro();
	
	@Query("From CatTipoCuenta")
	public List<CatTipoCuenta> findAllCatTipoCuenta();	
	
	

}





