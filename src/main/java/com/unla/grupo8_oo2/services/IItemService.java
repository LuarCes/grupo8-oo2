package com.unla.grupo8_oo2.services;

import java.util.List;
import java.util.Optional;

import com.unla.grupo8_oo2.entities.Item;

public interface IItemService {

	public abstract Optional<Item> findItemById(int id);
	public List<Item> getAll();
	
}
