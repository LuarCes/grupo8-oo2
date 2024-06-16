package com.unla.grupo8_oo2.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.entities.User;

@Repository ("carritoRepository")
public interface ICarritoRepository extends JpaRepository<Carrito, Serializable>{
	
	public abstract Optional<Carrito> findById(int id);
	
	@Query("FROM Carrito c WHERE c.fecha = :fecha")
	public abstract List<Carrito> findByFecha(@Param("fecha") LocalDate fecha);
	
	@Query("FROM Carrito c WHERE c.user = :user")
	public abstract Optional<Carrito> findByClient(@Param("user") User user);
	
	public abstract List<Carrito> findByFechaBetween( LocalDate inicio, LocalDate fin);
}
