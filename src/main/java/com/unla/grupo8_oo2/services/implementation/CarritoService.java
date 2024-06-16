package com.unla.grupo8_oo2.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.repositories.ICarritoRepository;
import com.unla.grupo8_oo2.services.ICarritoService;


@Service("carritoService")
public class CarritoService implements ICarritoService {

	private ICarritoRepository carritoRepository;
	
	
	public CarritoService(ICarritoRepository carritoRepository) {
		this.carritoRepository = carritoRepository;
	}

	@Override
	public List<Producto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Producto> findById(int id) throws Exception {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Carrito> findByFecha(LocalDate fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto insertOrUpdate(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
}