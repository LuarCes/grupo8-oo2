package com.unla.grupo8_oo2.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo8_oo2.entities.Lote;
import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.repositories.ILoteRepository;
import com.unla.grupo8_oo2.services.ILoteService;

@Service("loteService")
public class LoteService implements ILoteService {
	private final ILoteRepository loteRepository;

    @Autowired
    public LoteService(ILoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }
    
    @Override
	public List<Lote> getAll() {
		return loteRepository.findAll();
	}

	@Override
	public Optional<Lote> findById(int id) throws Exception {
		return loteRepository.findById(id);
	}

	@Override
	public Lote insertOrUpdate(Lote lote) {
		return loteRepository.save(lote);
	}

	@Override
	public boolean remove(int id) {
		try {
			loteRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Lote> findLotesByFechaRecepcion(LocalDate fechaRecepcion) {
		return loteRepository.findByFechaRecepcion(fechaRecepcion);
	}

	@Override
	public List<Lote> findLotesByProducto(Producto producto) {
		return loteRepository.findByProducto(producto);
	}

	@Override
	public List<Lote> findLotesByFechaRecepcionBetween(LocalDate inicio, LocalDate fin) {
		return loteRepository.findByFechaRecepcionBetween(inicio, fin);
	}
    
	@Override
	public List<Lote> findByAprobado(boolean estado) {
		return loteRepository.findByAprobado(estado);
	}

	public List<Lote> findByProductoId(int productoId) {
		return loteRepository.findByProductoId(productoId);
	}
	
	
}
