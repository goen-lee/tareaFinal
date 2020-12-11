package com.escalab.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escalab.model.Publicacion;

public interface IPublicacionRepo extends JpaRepository<Publicacion, Integer> {

}
