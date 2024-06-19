package com.unla.grupo8_oo2.services.implementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.Item;
import com.unla.grupo8_oo2.entities.User;
import com.unla.grupo8_oo2.repositories.ICarritoRepository;
import com.unla.grupo8_oo2.repositories.IItemRepository;
import com.unla.grupo8_oo2.repositories.IUserRepository;
import com.unla.grupo8_oo2.services.ICarritoService;

import jakarta.transaction.Transactional;


@Service("carritoService")
public class CarritoService implements ICarritoService {
	private final ICarritoRepository carritoRepository;
	private final IItemRepository itemRepository;
	private final IUserRepository userRepository;
	
	public CarritoService(ICarritoRepository carritoRepository, IItemRepository itemRepository, IUserRepository userRepository) {
		this.carritoRepository = carritoRepository;
		this.itemRepository = itemRepository;
		this.userRepository = userRepository;
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

	@Transactional
	public Carrito insertOrUpdate(List<Item> lstItem, String username) {

		User user = userRepository.findByUsername(username);
		Carrito carrito = new Carrito(LocalDate.now(), LocalTime.now(), user);
		for (Item item : lstItem) {
	        item.setCarrito(carrito);
	        carrito.getLstItem().add(item);
	    }
		
		for(Item item : lstItem) {
			System.out.println(item.toStringConCarrito());
		}
		
		return carritoRepository.save(carrito);
	}

	 	@Transactional
	    public Carrito createCarrito(String username) {
	        User user = userRepository.findByUsername(username);
	        Carrito carrito = new Carrito(LocalDate.now(), LocalTime.now(), user);
	        return carritoRepository.save(carrito);
	    }

	 	
	    @Transactional
	    public void addItemToCarrito(Carrito carrito, Item item) {
	        item.setCarrito(carrito);
	        carrito.getLstItem().add(item);
	        itemRepository.save(item);
	        carritoRepository.save(carrito);
	    }

		@Override
		public Optional<Carrito> findByUser(User user) {
			return Optional.empty();
		}

		@Override
		public void insertOrUpdate(Carrito carrito) {
			// TODO Auto-generated method stub
			
		}
	
}
