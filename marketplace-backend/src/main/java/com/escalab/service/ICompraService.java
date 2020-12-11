package com.escalab.service;

import java.util.List;

import com.escalab.model.Compra;

public interface ICompraService {
	
	List<Compra> listarComprasProductosPorCuenta(Integer idCuenta);

}
