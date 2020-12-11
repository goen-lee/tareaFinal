package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.escalab.model.Cuenta;
import com.escalab.model.Publicacion;
import com.escalab.repo.IPublicacionRepo;
import com.escalab.service.IPublicacionService;

@Service
public class PublicacionServiceImpl implements IPublicacionService {

	@Autowired
	IPublicacionRepo repo;
	
	@Override
	public Publicacion registrar(Publicacion obj) {
		return repo.save(obj);
	}
	
	@Override
	public Publicacion modificar(Publicacion obj) {
		return repo.save(obj);
	}
	
	@Override
	public List<Publicacion> listar() {
		return repo.findAll();
	}
	
	@Override
	public Page<Publicacion> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	@Override
	public Publicacion leerPorId(Integer id) {
		Optional<Publicacion> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Publicacion();
 	}
	
	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}
}
