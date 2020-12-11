package com.escalab.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escalab.model.Compra;
import com.escalab.service.ICompraService;


@RestController
@RequestMapping("/compras")
public class CompraController {
	
	@Autowired
	private ICompraService service;

	public ResponseEntity<List<Compra>> listar(@PathVariable("idCuenta") Integer idCuenta){
		List<Compra> compra = new ArrayList<>();
		compra = service.listarComprasProductosPorCuenta(idCuenta);
		return new ResponseEntity<List<Compra>>(compra, HttpStatus.OK);
	}	
	
}
