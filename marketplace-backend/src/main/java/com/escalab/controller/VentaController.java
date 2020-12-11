package com.escalab.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.escalab.model.Vende;
import com.escalab.service.IVendeService;


@RestController
@RequestMapping("/Ventas")
public class VentaController {
	
	@Autowired
	private IVendeService service;

	public ResponseEntity<List<Vende>> listar(@PathVariable("idCuenta") Integer idCuenta){
		List<Vende> vende = new ArrayList<>();
		vende = service.listarVentasProductosPorCuenta(idCuenta);
		return new ResponseEntity<List<Vende>>(vende, HttpStatus.OK);
	}	
}
