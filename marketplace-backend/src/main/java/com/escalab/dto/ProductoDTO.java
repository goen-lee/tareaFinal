package com.escalab.dto;

import org.springframework.hateoas.ResourceSupport;

import com.escalab.model.Categoria;
import com.escalab.model.SubCategoria;

public class ProductoDTO extends ResourceSupport {
	
	private Integer idProducto;
	private Categoria categoria;
	private SubCategoria subCategoria;
	
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public SubCategoria getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}
}
