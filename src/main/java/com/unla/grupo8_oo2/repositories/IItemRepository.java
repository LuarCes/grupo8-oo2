package com.unla.grupo8_oo2.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo8_oo2.entities.Item;

@Repository("itemRepository")
public interface IItemRepository extends JpaRepository<Item, Serializable>{
	
	public abstract Optional<Item> findItemById(int id);
	
	
}
