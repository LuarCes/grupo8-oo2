package com.unla.grupo8_oo2.services;

import java.util.List;
import java.util.Optional;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.Item;
import com.unla.grupo8_oo2.entities.User;


public interface ICarritoService {
	public List<Carrito> getAll();

	public Carrito insertOrUpdate(List<Item> lstItem, String username);
	public void addItemToCarrito(Carrito carrito, Item item);
	public Carrito createCarrito(String username);

	public Optional<Carrito> findByUser(User user);
	public void insertOrUpdate(Carrito carrito);
	
}








