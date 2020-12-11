package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escalab.model.SubCategoria;
import com.escalab.repo.ISubCategoriaRepo;
import com.escalab.service.ISubCategoriaService;

@Service
public class SubCategoriaServiceImpl implements ISubCategoriaService {
	
	@Autowired
	ISubCategoriaRepo repo;

	@Override
	public SubCategoria registrar(SubCategoria pac) {
		return repo.save(pac);
	}
	
	@Override
	public SubCategoria modificar(SubCategoria pac) {
		return repo.save(pac);
	}
	
	@Override 
	public List<SubCategoria> listar() {
		return repo.findAll();
	}
	
	@Override
	public SubCategoria leerPorId(Integer id) {
		Optional<SubCategoria> op = repo.findById(id);
		return op.isPresent() ? op.get() : new SubCategoria(); 
	}
	
	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}
}
