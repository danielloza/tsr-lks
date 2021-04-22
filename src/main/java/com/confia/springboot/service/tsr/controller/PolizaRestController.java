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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.confia.springboot.service.tsr.models.CatTipoCuenta;
import com.confia.springboot.service.tsr.models.CatTipoPoliza;
import com.confia.springboot.service.tsr.models.Poliza;
import com.confia.springboot.service.tsr.service.PolizaService;
import com.confia.springboot.service.tsr.utils.Mensaje;

@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class PolizaRestController {
	
	@Autowired
	private PolizaService polizaService;
	
	
	/**
	 * Consultar polizas
	 * @return
	 */
	@GetMapping("/polizas")
	public List<Poliza> index() {
		return polizaService.findAll();
	}
	
	/**
	 * Consultar polizas por ID
	 * @param id
	 * @return
	 */
	@GetMapping("/polizas/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Poliza poliza = null;
		
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			poliza = polizaService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", Mensaje.MSJ_SELECT_ERROR);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(poliza == null) {
			response.put("mensaje", "La poliza ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Poliza>(poliza, HttpStatus.OK);
	}
	
	/**
	 * Insertar Polizas
	 * @param poliza
	 * @param result
	 * @return
	 */
	@PostMapping("/polizas")
	public ResponseEntity<?> create(@Valid @RequestBody Poliza poliza, 
									BindingResult result) {
		
		Poliza poliNew = null;
		
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
			poliNew = polizaService.save(poliza);
		} catch(DataAccessException e) {
			response.put("mensaje", Mensaje.MSJ_INSERT_ERROR);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", Mensaje.MSJ_INSERT_EXITO);
		response.put("La poliza", poliNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}	

	
	/**
	 * UPDATE Polizas
	 * @param poliza
	 * @param result
	 * @param id
	 * @return
	 */
	@PutMapping("/polizas/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Poliza poliza, 
			                       BindingResult result, @PathVariable Long id) {

		Poliza polizaActual = polizaService.findById(id);

		Poliza polizaUpdate = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (polizaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la cuenta ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			
			polizaActual.setReferencia(poliza.getReferencia());
			polizaActual.setUsuario(poliza.getUsuario());
			polizaActual.setNumero(poliza.getNumero());
			polizaActual.setFecha(poliza.getFecha());
			polizaActual.setConcepto(poliza.getConcepto());
			polizaActual.setEstatus(poliza.getEstatus());
			polizaActual.setTipoPoliza(poliza.getTipoPoliza());
			polizaActual.setListMovPoliza(poliza.getListMovPoliza());
			
			polizaUpdate = polizaService.save(polizaActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la póliza en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", Mensaje.MSJ_UPDATE_EXITO);
		response.put("póliza", polizaUpdate);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}		

	/**
	 * Eliminar poliza
	 * @param id
	 * @return
	 */
	@DeleteMapping("/polizas/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			polizaService.delete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", Mensaje.MSJ_DELETE_ERROR);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", Mensaje.MSJ_DELETE_EXITO);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}	

	/**
	 * Lista de tipos de cuenta
	 * @return
	 */
	@GetMapping("/polizas/tipo-poliza")
	public List<CatTipoPoliza> listarTipoPoliza(){
		return polizaService.findAllCatTipoPoliza();	
	}

}
