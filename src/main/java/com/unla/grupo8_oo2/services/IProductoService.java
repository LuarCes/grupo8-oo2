package com.unla.grupo8_oo2.services;

import java.util.List;
import java.util.Optional;

import com.unla.grupo8_oo2.entities.Producto;

public interface IProductoService {

	public List<Producto> getAll();
	
	public Optional<Producto> findById(int id) throws Exception;
	
	public Optional<Producto> findByNombre(String nombre) throws Exception;

	public Optional<Producto> findByCodigo(String codigo) throws Exception;
	
}
