package com.unla.grupo8_oo2.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.User;


@Repository ("carritoRepository")
public interface ICarritoRepository extends JpaRepository<Carrito, Serializable>{
	
	public abstract List<Carrito> findAll();

	public abstract Optional<Carrito> findByUser(User user);
	
	
}
