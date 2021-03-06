package com.escalab.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.escalab.model.SubCategoria;
import com.escalab.service.ISubCategoriaService;

@RestController
@RequestMapping("/subcategorias")
public class SubCategoriaController {

	@Autowired
	private ISubCategoriaService service;
	
	@GetMapping
	public ResponseEntity<List<SubCategoria>> listar() {
		List<SubCategoria> lista = service.listar();
		return new ResponseEntity<List<SubCategoria>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SubCategoria> listarPorId(@PathVariable("id") Integer id) {
		SubCategoria obj = service.leerPorId(id);
		if (obj.getIdSubCategoria() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<SubCategoria>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody SubCategoria subcategoria) {
		SubCategoria obj = service.registrar(subcategoria);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(subcategoria.getIdSubCategoria()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<SubCategoria> modificar(@Valid @RequestBody SubCategoria subCategoria) {
		SubCategoria obj = service.modificar(subCategoria);
		return new ResponseEntity<SubCategoria>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		SubCategoria obj = service.leerPorId(id);
		if (obj.getIdSubCategoria() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
