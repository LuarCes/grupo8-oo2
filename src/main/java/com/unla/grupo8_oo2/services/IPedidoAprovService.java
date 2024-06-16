package com.unla.grupo8_oo2.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.unla.grupo8_oo2.entities.PedidoAprov;

public interface IPedidoAprovService {
	
	public List<PedidoAprov> getAll();
	
	public Optional<PedidoAprov> findById(int id) throws Exception;
	
	public Optional<PedidoAprov> findByFechaPedido(LocalDate fechaPedido);
	
	public List<PedidoAprov> findByProveedor(String nombre);
	
	public boolean remove(int id);
}
