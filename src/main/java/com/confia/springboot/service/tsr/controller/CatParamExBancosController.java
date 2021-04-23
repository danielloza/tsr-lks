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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.confia.springboot.service.tsr.beans.ICatParamExeBancos;
import com.confia.springboot.service.tsr.models.CatParamExeBancos;
import com.confia.springboot.service.tsr.service.ICatParamExBancosService;
import com.confia.springboot.service.tsr.utils.Mensaje;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api/parametros-bancos")
public class CatParamExBancosController {

	@Autowired
	private ICatParamExBancosService pebService;
	
	@GetMapping("/find-all")
	public List<CatParamExeBancos> findAllParam() {
		return pebService.findAllParam();
	}
	
	@GetMapping("/find-by-param/{id}")
	public ResponseEntity<?> findByID(@PathVariable Integer id) {
		
		CatParamExeBancos peb = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			peb = pebService.findByID(id);
		} catch(DataAccessException e) {
			response.put("mensaje", Mensaje.MSJ_SELECT_ERROR);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(peb == null) {
			response.put("mensaje", "El Parametro ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<CatParamExeBancos>(peb, HttpStatus.OK);
	}
	
	@GetMapping("/find-by-banck/{bancoID}")
	public List<ICatParamExeBancos> findByBanck(@PathVariable Integer bancoID) {
		
		
			return (List<ICatParamExeBancos>) pebService.findByBanck(bancoID);
		
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createParam(@Valid @RequestBody CatParamExeBancos peb, BindingResult result) {
		
		CatParamExeBancos pebNew = null;
		
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
			pebNew = pebService.addParam(peb);
		} catch(DataAccessException e) {
			response.put("mensaje", Mensaje.MSJ_INSERT_ERROR);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", Mensaje.MSJ_INSERT_EXITO);
		response.put("El Parametro", pebNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody CatParamExeBancos peb, 
			                       BindingResult result, @PathVariable Integer id) {

		CatParamExeBancos pebActual = pebService.findByID(id);

		CatParamExeBancos pebUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (pebActual == null) {
			response.put("mensaje", "Error: no se pudo editar, El Parametro de Bancos con ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			
			pebActual.setMontoMin(peb.getMontoMin());
			pebActual.setMontoMax(peb.getMontoMax());	
			pebActual.setFechaRegistro(new Date());
			
			pebUpdated = pebService.addParam(pebActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el Parametro de Bancos en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", Mensaje.MSJ_UPDATE_EXITO);
		response.put("Parametro Bancos", pebUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			pebService.delete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", Mensaje.MSJ_DELETE_ERROR);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", Mensaje.MSJ_DELETE_EXITO);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
