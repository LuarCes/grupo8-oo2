package com.unla.grupo8_oo2.services;

import java.util.List;
import java.util.Optional;

import com.unla.grupo8_oo2.entities.Lote;
import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.entities.Stock;


public interface IStockService {
	public List<Stock> getAll();
	public Optional<Stock> findById(int id) throws Exception;
	public List<Stock> findByProducto(Producto producto) throws Exception;
	public Stock insertOrUpdate(Lote lote);
	public boolean remove(int id);
}
