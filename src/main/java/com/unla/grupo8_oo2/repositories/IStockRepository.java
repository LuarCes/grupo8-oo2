package com.unla.grupo8_oo2.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.unla.grupo8_oo2.entities.Stock;

@Repository("stockRepository")
public class IStockRepository {
	
	public abstract Optional<Stock> findById(int id);
	
}
