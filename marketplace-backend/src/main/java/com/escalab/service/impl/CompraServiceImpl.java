package com.escalab.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.model.Compra;
import com.escalab.repo.ICompraRepo;
import com.escalab.service.ICompraService;

@Service
public class CompraServiceImpl implements ICompraService {
	
	@Autowired
	private ICompraRepo repo;
	
	@Override
	public List<Compra> listarComprasProductosPorCuenta(Integer idcuenta) {
		return repo.listarComprasProductosPorCuenta(idcuenta);
	}
}
