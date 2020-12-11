package com.escalab.service;

import java.util.List;

import com.escalab.model.Vende;

public interface IVendeService {

	List<Vende> listarVentasProductosPorCuenta(Integer idCuenta);
}
