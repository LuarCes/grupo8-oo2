package com.unla.grupo8_oo2.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
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
	
	//@Query("SELECT c FROM Carrito c JOIN FETCH c.lstItem i JOIN FETCH i.producto")
    //List<Carrito> findAllWithItems();
	
	public abstract List<Carrito> findAll();
	
	
	//public abstract Optional<Carrito> findById(int id);
	
	//@Query("SELECT c FROM Carrito c WHERE c.fecha = (:fecha)")
	//public abstract List<Carrito> findByFecha(LocalDate fecha);
	

	/*
	@Query("SELECT c FROM Carrito c JOIN FETCH c.user u WHERE u.usernombre= (:nombre)")
	Optional<Carrito> findByUser(String nombre);
	
	@Query("SELECT c FROM Carrito c JOIN FETCH c.lstItem")
    List<Carrito> findAllWithItems();
	*/

	//public abstract List<Carrito> findByFechaBetween( LocalDate inicio, LocalDate fin);
}
