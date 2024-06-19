package com.unla.grupo8_oo2.services;

import java.util.List;
import java.util.Optional;

import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.entities.Stock;

public interface IStockService {
	public List<Stock> getAll();
	public Optional<Stock> findById(int id) throws Exception;
	public Stock insertOrUpdate(Stock stock);
	public abstract Stock findByProducto(Producto producto);
	public abstract List<Stock> traerMayoresACero();
}

