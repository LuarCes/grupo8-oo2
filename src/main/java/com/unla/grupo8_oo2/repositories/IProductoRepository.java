package com.unla.grupo8_oo2.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo8_oo2.entities.Producto;

@Repository("productoRepository")
public interface IProductoRepository extends JpaRepository<Producto, Serializable>{

	public abstract Optional<Producto> findByNombre(String nombre);
	
	public abstract Optional<Producto> findByCodigo(String codigo);
	
	
	
	
}
