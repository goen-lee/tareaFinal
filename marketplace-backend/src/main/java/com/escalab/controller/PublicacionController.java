package com.escalab.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.escalab.exception.ModeloNotFoundException;
import com.escalab.model.Publicacion;
import com.escalab.service.IPublicacionService;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

	@Autowired
	private IPublicacionService service;
	
	@GetMapping
	public ResponseEntity<List<Publicacion>> listar(){
		List<Publicacion> lista = service.listar();
		return new ResponseEntity<List<Publicacion>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Publicacion> listarPorId(@PathVariable("id") Integer id){
		Publicacion cue = service.leerPorId(id);
		if (cue.getIdPublicacion() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<Publicacion>(cue, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Publicacion>> listarPageable(Pageable pageable){
		Page<Publicacion> publicacions = service.listarPageable(pageable);
		return new ResponseEntity<Page<Publicacion>>(publicacions, HttpStatus.OK);
	} 
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Publicacion publicacion){
		Publicacion cue = service.registrar(publicacion);
		//publicacion/7
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(publicacion.getIdPublicacion()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Publicacion> modificar(@Valid @RequestBody Publicacion publicacion) {
		Publicacion cue = service.modificar(publicacion);
		return new ResponseEntity<Publicacion>(cue, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Publicacion cue = service.leerPorId(id);
		if (cue.getIdPublicacion() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
