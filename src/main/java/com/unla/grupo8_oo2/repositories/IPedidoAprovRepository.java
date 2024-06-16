package com.unla.grupo8_oo2.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.unla.grupo8_oo2.entities.PedidoAprov;

@Repository("pedidoAprovRepository")
public interface IPedidoAprovRepository extends JpaRepository<PedidoAprov, Serializable>{

	public abstract Optional<PedidoAprov> findById(int id);
	
	public abstract Optional<PedidoAprov> findByFechaPedido(LocalDate fechaPedido);
	
	@Query("SELECT p FROM PedidoAprov p JOIN FETCH p.proveedor pr WHERE pr.nombre = (:nombre)")
	public abstract List<PedidoAprov> findByProveedor(String nombre);
}