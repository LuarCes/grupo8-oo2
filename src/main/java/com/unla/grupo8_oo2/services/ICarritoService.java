package com.unla.grupo8_oo2.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.Item;
import com.unla.grupo8_oo2.entities.User;


public interface ICarritoService {
	public List<Carrito> getAll();
	//public Optional<Carrito> findById(int id) throws Exception;
	public Carrito insertOrUpdate(List<Item> lstItem, String username);
	public void addItemToCarrito(Carrito carrito, Item item);
	public Carrito createCarrito(String username);
	public Carrito findByFechaAndHora(LocalDate fecha, LocalTime hora);
	//public boolean remove(int id);
	
	//public abstract List<Carrito> findByFecha(LocalDate fecha);
	
}








