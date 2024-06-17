package com.unla.grupo8_oo2.repositories;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.entities.Stock;

@Repository("stockRepository")
public interface IStockRepository extends JpaRepository<Stock, Serializable>{
	
	public abstract Optional<Stock> findById(int id);
	//public abstract List<Stock> findByProductos(Producto producto);
	public abstract Stock findByProducto(Producto producto);
	public abstract List<Stock> findAll();

}
