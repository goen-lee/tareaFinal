package com.escalab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.escalab.model.Publicacion;

public interface IPublicacionService extends ICRUD<Publicacion>{

	Page<Publicacion> listarPageable(Pageable pageable);
}
