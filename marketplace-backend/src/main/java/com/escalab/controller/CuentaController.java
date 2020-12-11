package com.escalab.controller;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.escalab.dto.CuentaResumenDTO;
import com.escalab.dto.FiltroCuentaDTO;
import com.escalab.dto.ProductoDTO;
import com.escalab.exception.ModeloNotFoundException;
import com.escalab.model.Archivo;
import com.escalab.model.Cuenta;
import com.escalab.service.IArchivoService;
import com.escalab.service.ICuentaService;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

	@Autowired
	private ICuentaService service;
	
	@Autowired
	private IArchivoService serviceArchivo;
	
	@GetMapping
	public ResponseEntity<List<Cuenta>> listar(){
		List<Cuenta> lista = service.listar();
		return new ResponseEntity<List<Cuenta>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cuenta> listarPorId(@PathVariable("id") Integer id){
		Cuenta cue = service.leerPorId(id);
		if (cue.getIdCuenta() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<Cuenta>(cue, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Cuenta>> listarPageable(Pageable pageable){
		Page<Cuenta> cuentas = service.listarPageable(pageable);
		return new ResponseEntity<Page<Cuenta>>(cuentas, HttpStatus.OK);
	} 
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Cuenta cuenta){
		Cuenta cue = service.registrar(cuenta);
		//cuenta/7
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cuenta.getIdCuenta()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Cuenta> modificar(@Valid @RequestBody Cuenta cuenta) {
		Cuenta cue = service.modificar(cuenta);
		return new ResponseEntity<Cuenta>(cue, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Cuenta cue = service.leerPorId(id);
		if (cue.getIdCuenta() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@PostMapping("/buscar")
	public ResponseEntity<List<Cuenta>> buscar(@RequestBody FiltroCuentaDTO filtro) {
		List<Cuenta> cuentas = new ArrayList<>();

		if (filtro != null) {
			if (filtro.getTarjetaCredito() != null) {
				cuentas = service.buscarTarjetaCredito(filtro); 
			} else {
				cuentas = service.buscar(filtro);
			}
				
		}
		return new ResponseEntity<List<Cuenta>>(cuentas, HttpStatus.OK);
	}
	
	@GetMapping(value = "/listarResumen")
	public ResponseEntity<List<CuentaResumenDTO>> listarResumen() {
		List<CuentaResumenDTO> cuentas = new ArrayList<>();
		cuentas = service.listarResumen();
		return new ResponseEntity<List<CuentaResumenDTO>>(cuentas, HttpStatus.OK);
	}
	
	@GetMapping(value = "/generarReporte", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> generarReporte(){
		byte[] data = null;
		data = service.generarReporte();
		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
	}
	
	@PostMapping(value = "/guardarArchivo", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Integer> guardarArchivo(@RequestParam("adjunto") MultipartFile file) throws IOException{
		int rpta = 0;
		Archivo ar = new Archivo();
		ar.setFileType(file.getContentType());
		ar.setFileName(file.getName());
		ar.setValue(file.getBytes());
		
		rpta = serviceArchivo.guardar(ar);

		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);	
	}
}
