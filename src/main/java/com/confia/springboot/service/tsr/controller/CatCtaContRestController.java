package com.confia.springboot.service.tsr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

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

import com.confia.springboot.service.tsr.models.Calculadora;
import com.confia.springboot.service.tsr.models.CatCtaCont;
import com.confia.springboot.service.tsr.models.CatCtaContRef;
import com.confia.springboot.service.tsr.models.CatEmpresa;
import com.confia.springboot.service.tsr.models.CatMonedaSat;
import com.confia.springboot.service.tsr.models.CatNaturaleza;
import com.confia.springboot.service.tsr.models.CatRubro;
import com.confia.springboot.service.tsr.models.CatTipoCuenta;
import com.confia.springboot.service.tsr.service.ICatCtaContableService;
import com.confia.springboot.service.tsr.utils.Mensaje;
import com.confia.springboot.service.tsr.utils.Response;


@CrossOrigin(origins = { "http://localhost:4200", "*" })
@RestController
@RequestMapping("/api")
public class CatCtaContRestController {

	@Autowired
	private ICatCtaContableService catCtaContService;


	/**
	 * Consultar Cuentas Contables por ID
	 * @param id
	 * @return
	 */
	@GetMapping("/cuentas-contables/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		CatCtaCont catCtaCont = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			catCtaCont = catCtaContService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", Mensaje.MSJ_SELECT_ERROR);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(catCtaCont == null) {
			response.put("mensaje", "La cuenta ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<CatCtaCont>(catCtaCont, HttpStatus.OK);
	}
	
	
	/**
	 * Insertar Cuenta Contable
	 * @param cliente
	 * @param result
	 * @return
	 */
	@PostMapping("/cuentas-contables")
	public ResponseEntity<?> create(@Valid @RequestBody CatCtaCont catCtaCont, 
									BindingResult result) {
		System.out.println("Horacio Entraste al insert de cuenta");
		
		CatCtaCont catCtaContNew = null;
		
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
			catCtaContNew = catCtaContService.save(catCtaCont);
		} catch(DataAccessException e) {
			response.put("mensaje", Mensaje.MSJ_INSERT_ERROR);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", Mensaje.MSJ_INSERT_EXITO);
		response.put("La cuenta", catCtaContNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/**
	 * UPDATE Catalogo Cuentas Contables
	 * @param cliente
	 * @param result
	 * @param id
	 * @return
	 */
	@PutMapping("/cuentas-contables/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody CatCtaCont catCtaCont, 
			                       BindingResult result, @PathVariable Long id) {

		CatCtaCont catCtaContActual = catCtaContService.findById(id);

		CatCtaCont catCtaContUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (catCtaContActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la cuenta ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			
			catCtaContActual.setCuenta(catCtaCont.getCuenta());
			catCtaContActual.setAcumulaCuenta(catCtaCont.getAcumulaCuenta());
			catCtaContActual.setNombre(catCtaCont.getNombre());
			catCtaContActual.setTipoCuenta(catCtaCont.getTipoCuenta());
			catCtaContActual.setNaturaleza(catCtaCont.getNaturaleza());
			catCtaContActual.setRubro(catCtaCont.getRubro());
			catCtaContActual.setEmpresa(catCtaCont.getEmpresa());
			catCtaContActual.setMonedaSat(catCtaCont.getMonedaSat());
			catCtaContActual.setActiva(catCtaCont.getActiva());
			catCtaContActual.setFechaRegistro(catCtaCont.getFechaRegistro());
			
			catCtaContUpdated = catCtaContService.save(catCtaContActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar la cuenta contable en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", Mensaje.MSJ_UPDATE_EXITO);
		response.put("cuenta contable", catCtaContUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}	

	/**
	 * Eliminar Cuenta Contable
	 * @param id
	 * @return
	 */
	@DeleteMapping("/cuentas-contables/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			catCtaContService.delete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", Mensaje.MSJ_DELETE_ERROR);
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", Mensaje.MSJ_DELETE_EXITO);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	

	/**
	 * Consultar Cuentas Contables
	 * @return
	 */
	@GetMapping("/cuentas-contables")
	public List<CatCtaCont> index() {
		return catCtaContService.findAll();
	}
	
	/**
	 * Lista de Cuentas Contables
	 * @return
	 */
	@GetMapping("/cuentas-contables/cuentas")
	public List<CatCtaContRef> listarCuentas(){
		return catCtaContService.findAllCatCtasContables();
	}	

	/**
	 * Lista de Empresas
	 * @return
	 */
	@GetMapping("/cuentas-contables/empresas")
	public List<CatEmpresa> listarEmpresas(){
		return catCtaContService.findAllCatEmpresas();
	}
	
	/**
	 * Lista de monedas
	 * @return
	 */
	@GetMapping("/cuentas-contables/monedas")
	public List<CatMonedaSat> listarMonedas(){
		return catCtaContService.findAllCatMonedaSat();
	}
	
	/**
	 * Lista de Naturalezas
	 * @return
	 */
	@GetMapping("/cuentas-contables/naturalezas")
	public List<CatNaturaleza> listarNaturaleza(){
		return catCtaContService.findAllCatNaturaleza();
	}
	
	/**
	 * Lista de rubros
	 * @return
	 */
	@GetMapping("/cuentas-contables/rubros")
	public List<CatRubro> listarRubro(){
		return catCtaContService.findAllCatRubro();
	}	

	/**
	 * Lista de tipos de cuenta
	 * @return
	 */
	@GetMapping("/cuentas-contables/tipo-cuentas")
	public List<CatTipoCuenta> listarTipoCuenta(){
		return catCtaContService.findAllCatTipoCuenta();	
	}
	
	/**
	 * Lista de tipos de cuenta
	 * @return
	 */
	@PostMapping("/cuentas-contables/response")
	public Response listarResponse(@RequestBody Calculadora cal){
		return catCtaContService.findResponse(cal.getNumero1(), cal.getNumero2());	
	}
}
