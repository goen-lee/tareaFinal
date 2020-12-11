package com.escalab.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.escalab.model.Comentario;
import com.escalab.repo.IComentarioRepo;
import com.escalab.service.IComentarioService;

@Service
public class ComentarioServiceImpl implements IComentarioService {

	@Autowired
	private IComentarioRepo repo;
	
	@Override
	public Comentario registrar(Comentario obj) {
		return repo.save(obj);
	}
	
	@Override
	public Comentario modificar(Comentario obj) {
		return repo.save(obj);
	}
	
	@Override
	public List<Comentario> listar() {
		return repo.findAll();
	}
	
	@Override
	public Comentario leerPorId(Integer id) {
		Optional<Comentario> op = repo.findById(id);
		return op.isPresent() ? op.get() : new Comentario();
 	}
	
	@Override
	public boolean eliminar(Integer id) {
		repo.deleteById(id);
		return true;
	}
}
