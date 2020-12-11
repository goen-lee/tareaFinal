package com.escalab.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.escalab.model.Cuenta;

public interface ICuentaRepo extends JpaRepository<Cuenta, Integer>{
	
	@Query("from Cuenta c where c.usuario =:nombreUsuario or LOWER(c.nombres) like %:nombreCompleto% or LOWER(c.apellidos) like %:nombreCompleto%")
	List<Cuenta> buscar(@Param("nombreUsuario")String nombreUsuario,@Param("nombreCompleto") String nombreCompleto);

	@Query("from Cuenta c where c.tarjeta =:tarjetaCredito")
	List<Cuenta> buscarTarjetaCredito(@Param("tarjetaCredito")String tarjetaCredito);
	
	@Query(value = "select * from fn_listarResumen()", nativeQuery = true)
	List<Object[]> listarResumen();

}
