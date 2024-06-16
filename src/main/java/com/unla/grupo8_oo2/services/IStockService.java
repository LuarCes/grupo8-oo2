package com.unla.grupo8_oo2.services;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.unla.grupo8_oo2.entities.Lote;
import com.unla.grupo8_oo2.entities.Stock;

@Repository("stockRepository")
public class IStockService {
	
	public abstract Optional<Stock> findById(int id);
	
}
