package com.unla.grupo8_oo2.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.entities.Stock;
import com.unla.grupo8_oo2.repositories.IStockRepository;
import com.unla.grupo8_oo2.services.IStockService;

@Service("stockService")
public class StockService implements IStockService {
	private final IStockRepository stockRepository;
    public StockService(IStockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

	@Override
	public List<Stock> getAll() {
		return stockRepository.findAll();
	}

	@Override
	public Optional<Stock> findById(int id) throws Exception {
		  try {
	            return stockRepository.findById(id);
	        } catch (Exception e) {
	            throw new Exception("Error de fetching stock con id: " + id, e);
	        }
	}

	@Override
	public Stock insertOrUpdate(Stock stock) {
		return stockRepository.save(stock);
	}

	@Override
	public List<Stock> traerMayoresACero() {
		return stockRepository.traerMayoresACero();
	}

	@Override
	public Stock findByProducto(Producto producto) {
		return stockRepository.findByProducto(producto);
	}

	
}
