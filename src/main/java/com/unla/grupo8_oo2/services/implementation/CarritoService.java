package com.unla.grupo8_oo2.services.implementation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.Item;
import com.unla.grupo8_oo2.entities.User;
import com.unla.grupo8_oo2.repositories.ICarritoRepository;
import com.unla.grupo8_oo2.repositories.IItemRepository;
import com.unla.grupo8_oo2.repositories.IUserRepository;
import com.unla.grupo8_oo2.services.ICarritoService;

<<<<<<< HEAD
=======
import jakarta.transaction.Transactional;


>>>>>>> 108d1b2970ba631202709a17812d3b37c66354fd
@Service("carritoService")
public class CarritoService implements ICarritoService {
	private final ICarritoRepository carritoRepository;
	private final IItemRepository itemRepository;
	private final IUserRepository userRepository;

	public CarritoService(ICarritoRepository carritoRepository, IItemRepository itemRepository,
			IUserRepository userRepository) {
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
<<<<<<< HEAD
		 User user = userRepository.findByUsername(username);
		    if (user == null) {
		        throw new RuntimeException("Usuario no encontrado: " + username);
		    }

		    // Buscar el carrito existente del usuario (si existe)
		    Optional<Carrito> optionalCarrito = carritoRepository.findByUser(user);
		    Carrito carrito;
		    if (optionalCarrito.isPresent()) {
		        // Si existe un carrito, actualizar su contenido
		        carrito = optionalCarrito.get();
		        Set<Item> currentItems = carrito.getLstItem();
		        currentItems.clear(); // Limpiar los items actuales
		        currentItems.addAll(lstItem); // Agregar los nuevos items
		        carrito.setLstItem(currentItems); // Actualizar la lista de items
		    } else {
		        // Si no existe un carrito, crear uno nuevo
		        carrito = new Carrito(LocalDate.now(), LocalTime.now(), new HashSet<Item>(), user);
		    }

		    // Guardar o actualizar el carrito
		    return carritoRepository.save(carrito);
	}

	@Override
	public Optional<Carrito> findByUser(User user) {
		return carritoRepository.findByUser(user);
	}

	@Transactional
	public void insertOrUpdate(Carrito carrito) {
		if (carrito.getId() != 0 && carritoRepository.existsById(carrito.getId())) {
			// Actualizar carrito existente
			Carrito existingCarrito = carritoRepository.findById(carrito.getId()).orElse(null);
			if (existingCarrito != null) {
				existingCarrito.setFecha(carrito.getFecha());
				existingCarrito.setHora(carrito.getHora());
				existingCarrito.setLstItem(carrito.getLstItem());
				existingCarrito.setUser(carrito.getUser());
				carritoRepository.save(existingCarrito);
			}
		} else {
			// Insertar nuevo carrito
			carritoRepository.save(carrito);
		}

	}

=======
		//Set<Item> setItem = new  HashSet<>(lstItem);
		User user = userRepository.findByUsername(username);
		Carrito carrito = new Carrito(LocalDate.now(), LocalTime.now(), user);
		for (Item item : lstItem) {
	        item.setCarrito(carrito);  // Establecer la relación bidireccional
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
	    	//Carrito carrito = findByFechaAndHora(fecha, hora);
	        item.setCarrito(carrito);
	        carrito.getLstItem().add(item);
	        itemRepository.save(item);
	        carritoRepository.save(carrito);
	    }


		
	
	
	
>>>>>>> 108d1b2970ba631202709a17812d3b37c66354fd
}
