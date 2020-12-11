package com.escalab.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.escalab.dto.CuentaListaProductoDTO;
import com.escalab.dto.CuentaResumenDTO;
import com.escalab.dto.FiltroCuentaDTO;
import com.escalab.model.Cuenta;

public interface ICuentaService extends ICRUD<Cuenta>{
	
	Page<Cuenta> listarPageable(Pageable pageable);
	
	Cuenta registrarTransaccionalCompra(CuentaListaProductoDTO dto);
	
	Cuenta registrarTransaccionalVende(CuentaListaProductoDTO dto);
	
	List<Cuenta> buscar(FiltroCuentaDTO filtro);
	
	List<Cuenta> buscarTarjetaCredito(FiltroCuentaDTO filtro);
	
	//List<Cuenta> buscarNombreUsuario(FiltroCuentaDTO filtro);
	
	List<CuentaResumenDTO> listarResumen();
	
	byte[] generarReporte();
}
