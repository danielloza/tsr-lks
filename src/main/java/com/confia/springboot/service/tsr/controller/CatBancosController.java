package com.confia.springboot.service.tsr.controller;

import java.util.Date;
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

import com.confia.springboot.service.tsr.models.CatBancos;
import com.confia.springboot.service.tsr.service.ICatBancosService;
import com.confia.springboot.service.tsr.utils.Mensaje;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/bancos")
public class CatBancosController {

	@Autowired
	private ICatBancosService bancosService;
	
	@GetMapping("/find-all")
	public List<CatBancos> findAllBancos() {
		return bancosService.findAllBancos();
	}
	
	@GetMapping("/find-by-bank/{id}")
	public ResponseEntity<?> findByID(@PathVariable Integer id) {
		
		CatBancos peb = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			peb = bancosService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", Mensaje.MSJ_SELECT_ERROR);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(peb == null) {
			response.put("mensaje", "El Banco ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<CatBancos>(peb, HttpStatus.OK);
	}
	
	
	@PostMapping("/create")
	public ResponseEntity<?> createParam(@Valid @RequestBody CatBancos cBancos, BindingResult result) {
		
		CatBancos cBancosNew = null;
		
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
			cBancosNew = bancosService.addBancos(cBancos);
		} catch(DataAccessException e) {
			response.put("mensaje", Mensaje.MSJ_INSERT_ERROR);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", Mensaje.MSJ_INSERT_EXITO);
		response.put("banco", cBancosNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody CatBancos catBancos, BindingResult result, @PathVariable int id) {

		CatBancos bancoActual = bancosService.findById(id);

		CatBancos bancoUpdated = null;

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
		
		if (bancoActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el Banco ID: "
					.concat("" + id + "".concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {

			bancoActual.setActivo(catBancos.isActivo());
			bancoActual.setCatCuentasBancos(catBancos.getCatCuentasBancos());
			bancoActual.setParamBancos(catBancos.getParamBancos());

			bancoUpdated = bancosService.update(bancoActual);	

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el banco en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El banco ha sido actualizada con éxito!");
		response.put("banco", bancoUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
}
