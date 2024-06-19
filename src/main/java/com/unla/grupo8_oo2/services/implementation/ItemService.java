package com.unla.grupo8_oo2.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.unla.grupo8_oo2.entities.Item;
import com.unla.grupo8_oo2.repositories.IItemRepository;
import com.unla.grupo8_oo2.services.IItemService;

@Service("itemService")
public class ItemService implements IItemService{
	
	private final IItemRepository itemRepository; 
	
    public ItemService(IItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }
	
	@Override
	public Optional<Item> findItemById(int id) {
		return itemRepository.findItemById(id);
	}

	@Override
	public List<Item> getAll() {	
		return itemRepository.findAll();
	}

	
	
}
