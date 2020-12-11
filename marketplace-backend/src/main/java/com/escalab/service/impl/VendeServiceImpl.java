package com.escalab.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.model.Vende;
import com.escalab.repo.IVendeRepo;
import com.escalab.service.IVendeService;

@Service
public class VendeServiceImpl implements IVendeService {
	
	@Autowired
	private IVendeRepo repo;
	
	@Override
	public List<Vende> listarVentasProductosPorCuenta(Integer idcuenta) {
		return repo.listarVentasProductosPorCuenta(idcuenta);
	}
}
