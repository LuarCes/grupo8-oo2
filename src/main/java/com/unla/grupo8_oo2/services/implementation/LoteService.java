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
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Lote insertOrUpdate(Lote lote) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Lote> findLotesByFechaRecepcion(LocalDate fechaRecepcion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lote> findLotesByProducto(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Lote> findLotesByFechaRecepcionBetween(LocalDate startDate, LocalDate endDate) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
	
}
