package com.unla.grupo8_oo2.repositories;

import java.io.Serializable;
<<<<<<< HEAD
=======
import java.time.LocalDate;
import java.time.LocalTime;
>>>>>>> 108d1b2970ba631202709a17812d3b37c66354fd
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.User;


@Repository ("carritoRepository")
public interface ICarritoRepository extends JpaRepository<Carrito, Serializable>{
	
	public abstract List<Carrito> findAll();
<<<<<<< HEAD
=======
	
	
	//public abstract Optional<Carrito> findById(int id);
	
	//@Query("SELECT c FROM Carrito c WHERE c.fecha = (:fecha)")
	//public abstract List<Carrito> findByFecha(LocalDate fecha);
	
>>>>>>> 108d1b2970ba631202709a17812d3b37c66354fd

	public abstract Optional<Carrito> findByUser(User user);
	
	
}
