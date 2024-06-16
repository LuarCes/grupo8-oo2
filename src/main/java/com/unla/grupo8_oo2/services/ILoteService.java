package com.unla.grupo8_oo2.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.unla.grupo8_oo2.entities.Lote;
import com.unla.grupo8_oo2.entities.Producto;


public interface ILoteService {
	
	public List<Lote> getAll();
	public Optional<Lote> findById(int id) throws Exception;
	public Lote insertOrUpdate(Lote lote);
	public boolean remove(int id);
    
	List<Lote> findLotesByFechaRecepcion(LocalDate fechaRecepcion);
	List<Lote> findLotesByProducto(Producto producto);
	List<Lote> findLotesByFechaRecepcionBetween(LocalDate startDate, LocalDate endDate);
	
}
