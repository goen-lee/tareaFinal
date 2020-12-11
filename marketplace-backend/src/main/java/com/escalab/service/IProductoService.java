package com.escalab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.escalab.model.Producto;

public interface IProductoService extends ICRUD<Producto> {

	Page<Producto> listarPageable(Pageable pageable);
}
