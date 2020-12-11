package com.escalab.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.escalab.model.Vende;

public interface IVendeRepo extends JpaRepository<Vende, Integer> {

	//@Transactional
	@Modifying
	@Query(value = "INSERT INTO Venta(id_cuenta, id_producto) VALUES (:idCuenta, :idProducto)", nativeQuery = true)
	Integer registrar(@Param("idCuenta") Integer idCuenta, @Param("idProducto") Integer idProducto);
		
	@Query("from Vende ve where ve.cuenta.idCuenta = :idCuenta")
	List<Vende> listarVentasProductosPorCuenta(@Param("idCuenta") Integer idcuenta);
}
