package com.unla.grupo8_oo2.repositories;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.unla.grupo8_oo2.entities.Lote;
import com.unla.grupo8_oo2.entities.Producto;

@Repository("loteRepository")
public interface ILoteRepository extends JpaRepository<Lote, Serializable>{
	
	public abstract Optional<Lote> findById(int id);
	
	
	List<Lote> findByFechaRecepcion(LocalDate fechaRecepcion);
	
	List<Lote> findByProducto(Producto producto);
	
	List<Lote> findByFechaRecepcionBetween(LocalDate inicio, LocalDate fin);
	
}
