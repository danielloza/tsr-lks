package com.confia.springboot.service.tsr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.confia.springboot.service.tsr.utils.Response;

public interface IResponseDao extends JpaRepository<Response, Long>{
	
	@Query(value = "EXECUTE Tesoreria.PrCalculadora ?1, ?2", nativeQuery = true)
	public Response findResponse(int num1, int num2);	
	
}
