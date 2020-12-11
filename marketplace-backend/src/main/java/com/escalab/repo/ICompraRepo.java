package com.escalab.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.escalab.model.Compra;

public interface ICompraRepo extends JpaRepository<Compra, Integer> {
	
	//@Transactional
	@Modifying
	@Query(value = "INSERT INTO compra(id_cuenta, id_producto) VALUES (:idCuenta, :idProducto)", nativeQuery = true)
	Integer registrar(@Param("idCuenta") Integer idCuenta, @Param("idProducto") Integer idProducto);
	
	@Query("from Compra co where co.cuenta.idCuenta = :idCuenta")
	List<Compra> listarComprasProductosPorCuenta(@Param("idCuenta") Integer idcuenta);
}
