package com.unla.grupo8_oo2.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.Producto;

public interface ICarritoRepository extends JpaRepository<Producto, Serializable>{
	
	public abstract Optional<Carrito> findById(int id);
	
	// Todas las personas que tengan un título con ese nombre (parámetro name)
		@Query("SELECT c FROM Carrito c WHERE c.fecha = :fecha")
		public abstract List<Carrito> findByFecha(LocalDate fecha);
	
	
}
