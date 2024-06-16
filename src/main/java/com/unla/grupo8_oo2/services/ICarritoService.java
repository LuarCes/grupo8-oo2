package com.unla.grupo8_oo2.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.Producto;

public interface ICarritoService {
	public List<Producto> getAll();
	
	public Optional<Producto> findById(int id) throws Exception;
	
	//Traer por fechas
	public abstract List<Carrito> findByFecha(LocalDate fecha);
	
	
	public Producto insertOrUpdate(Producto producto);
	
	public boolean remove(int id);
}
