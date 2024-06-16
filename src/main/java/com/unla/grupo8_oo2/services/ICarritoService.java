package com.unla.grupo8_oo2.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.unla.grupo8_oo2.entities.Carrito;

public interface ICarritoService {
	public List<Carrito> getAll();
	
	public Optional<Carrito> findById(int id) throws Exception;
	
	//Traer por fechas
	public abstract List<Carrito> findByFecha(LocalDate fecha);
	
	
	public Carrito insertOrUpdate(Carrito carrito);
	
	public boolean remove(int id);
}
