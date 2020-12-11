package com.escalab.dto;

import java.util.List;

import com.escalab.model.Cuenta;
import com.escalab.model.Producto;

public class CuentaListaProductoDTO {
	
	private Cuenta cuenta;
	private List<Producto> lstProducto;
	
	public Cuenta getCuenta() {
		return cuenta;
	}
	
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	public List<Producto> getLstProducto() {
		return lstProducto;
	}
	
	public void setLstProducto(List<Producto> lstProducto) {
		this.lstProducto = lstProducto;
	}
}
