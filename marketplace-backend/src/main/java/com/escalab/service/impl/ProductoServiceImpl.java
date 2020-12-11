package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.escalab.model.Cuenta;
import com.escalab.model.Producto;
import com.escalab.repo.IProductoRepo;
import com.escalab.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	IProductoRepo repo;
	
	@Override
	public Producto registrar(Producto obj) {
		return repo.save(obj);
	}
	
	@Override
	public Producto modificar(Producto obj) {
		return repo.save(obj);
	}
	
	@Override
	public List<Producto> listar() {
		return repo.findAll();
	}
	
	@Override
	public Page<Producto> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	@Override
	public Producto leerPorId(Integer id) {
		Optional<Producto> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Producto();
 	}
	
	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}
}
