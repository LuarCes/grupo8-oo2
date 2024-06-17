package com.unla.grupo8_oo2.services.implementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.Item;
import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.entities.User;
import com.unla.grupo8_oo2.repositories.ICarritoRepository;
import com.unla.grupo8_oo2.repositories.IItemRepository;
import com.unla.grupo8_oo2.services.ICarritoService;


@Service("carritoService")
public class CarritoService implements ICarritoService {
	private final ICarritoRepository carritoRepository;
	private final IItemRepository itemRepository;
	
	public CarritoService(ICarritoRepository carritoRepository, IItemRepository itemRepository) {
		this.carritoRepository = carritoRepository;
		this.itemRepository = itemRepository;
	}

	@Override
	public List<Carrito> getAll() {
		return carritoRepository.findAll();
		
	}

	
	public Carrito createCarrito(User user) {
		Carrito carrito = new Carrito(LocalDate.now(), LocalTime.now(), new HashSet<>(), user);
		return carritoRepository.save(carrito);
	}
	
	
	public Optional<Carrito> getCarritoById(int id) {
		return carritoRepository.findById(id);
	}

	
	
	
	
}
