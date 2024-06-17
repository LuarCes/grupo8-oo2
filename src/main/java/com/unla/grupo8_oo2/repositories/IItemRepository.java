package com.unla.grupo8_oo2.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.Item;
import com.unla.grupo8_oo2.entities.Lote;

public interface IItemRepository extends JpaRepository<Item, Serializable>{
	
	public abstract Optional<Item> findItemById(int id);
	
	public List<Item> getAll();
	
	
}
