package com.confia.springboot.service.tsr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.confia.springboot.service.tsr.models.CatCuentasBancos;
import com.confia.springboot.service.tsr.service.ICatCuentasBancosService;
import com.confia.springboot.service.tsr.utils.Mensaje;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/cuentas-bancos")
public class CatCuentasBancosController {
	
	@Autowired
	private ICatCuentasBancosService cuentasBancosService;

	@GetMapping("/find-all")
	public List<CatCuentasBancos> findAllBancos() {
		return cuentasBancosService.findAllAccount();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/find-by-banck/{bancoID}")
	public List<CatCuentasBancos> findByBanck(@PathVariable Integer bancoID) {
		return (List<CatCuentasBancos>) cuentasBancosService.findByBanck(bancoID);
	}
	
	@GetMapping("/find-by-account/{cuentaID}")
	public CatCuentasBancos findByAccount(@PathVariable Integer cuentaID) {
		return cuentasBancosService.findByAccount(cuentaID);
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createAccountBanck(@Valid @RequestBody CatCuentasBancos cuentasBancos, BindingResult result) {
		
		CatCuentasBancos cuentasBancosNew = null;
		
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			cuentasBancosNew = cuentasBancosService.addAcount(cuentasBancos);
		} catch(DataAccessException e) {
			response.put("mensaje", Mensaje.MSJ_INSERT_ERROR);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", Mensaje.MSJ_INSERT_EXITO);
		response.put("banco", cuentasBancosNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody CatCuentasBancos cuentaBancos, BindingResult result, @PathVariable int id) {

		CatCuentasBancos cuentaBancoActual = cuentasBancosService.findByAccount(id);

		CatCuentasBancos cuentaBancoUpdated = null;

		Map<String, Object> response = new HashMap<>();

		/////// Block Validation Model //////
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		/////// Block Validation Model //////
		
		if (cuentaBancoActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la Cuenta del Banco ID: "
					.concat("" + id + "".concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			cuentaBancoActual.setNumeroCuenta(cuentaBancos.getNumeroCuenta());
			cuentaBancoActual.setActivo(cuentaBancos.isActivo());
			cuentaBancoUpdated = cuentasBancosService.addAcount(cuentaBancoActual);	

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la cuenta del banco en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "La cuenta del banco ha sido actualizada con éxito!");
		response.put("cuentaBanco", cuentaBancoUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
