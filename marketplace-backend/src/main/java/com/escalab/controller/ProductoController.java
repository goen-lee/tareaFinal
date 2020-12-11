package com.escalab.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.escalab.dto.ProductoDTO;
import com.escalab.exception.ModeloNotFoundException;
import com.escalab.model.Producto;
import com.escalab.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService service;
	
	@GetMapping
	public ResponseEntity<List<Producto>> listar(){
		List<Producto> lista = service.listar();
		return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> listarPorId(@PathVariable("id") Integer id){
		Producto cue = service.leerPorId(id);
		if (cue.getIdProducto() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return new ResponseEntity<Producto>(cue, HttpStatus.OK);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Producto>> listarPageable(Pageable pageable){
		Page<Producto> productos = service.listarPageable(pageable);
		return new ResponseEntity<Page<Producto>>(productos, HttpStatus.OK);
	} 
	
	@PostMapping
	public ResponseEntity<Object> registrar(@Valid @RequestBody Producto producto){
		Producto cue = service.registrar(producto);
		//producto/7
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(producto.getIdProducto()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Producto> modificar(@Valid @RequestBody Producto producto) {
		Producto cue = service.modificar(producto);
		return new ResponseEntity<Producto>(cue, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) {
		Producto cue = service.leerPorId(id);
		if (cue.getIdProducto() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/hateoas", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductoDTO> listarHateoas() {
		List<Producto> consultas = new ArrayList<>();
		List<ProductoDTO> consultasDTO = new ArrayList<>();
		consultas = service.listar();
		
		for (Producto c : consultas) {
			ProductoDTO d = new ProductoDTO();
			d.setIdProducto(c.getIdProducto());
			d.setCategoria(c.getCategoria());
			d.setSubCategoria(c.getSubCategoria());
			
			// localhost:8080/consultas/1
			ControllerLinkBuilder linkTo = linkTo(methodOn(ProductoController.class).listarPorId((c.getIdProducto())));
			d.add(linkTo.withSelfRel());
			consultasDTO.add(d);
			
			ControllerLinkBuilder linkTo1 = linkTo(methodOn(CategoriaController.class).listarPorId((c.getCategoria().getIdCategoria())));
			d.add(linkTo1.withSelfRel());
			consultasDTO.add(d);
			
			ControllerLinkBuilder linkTo2 = linkTo(methodOn(SubCategoriaController.class).listarPorId((c.getSubCategoria().getIdSubCategoria())));
			d.add(linkTo2.withSelfRel());		
			consultasDTO.add(d);
		}
		return consultasDTO;
	}
}
