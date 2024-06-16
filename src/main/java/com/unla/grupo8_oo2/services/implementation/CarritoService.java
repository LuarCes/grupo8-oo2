package com.unla.grupo8_oo2.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.repositories.ICarritoRepository;
import com.unla.grupo8_oo2.services.ICarritoService;


@Service("carritoService")
public class CarritoService implements ICarritoService {
	private final ICarritoRepository carritoRepository;
	
	@Autowired
	public CarritoService(ICarritoRepository carritoRepository) {
		this.carritoRepository = carritoRepository;
	}

	@Override
	public List<Carrito> findByFecha(LocalDate fecha) {
		return null;
	}


	//REVISAR
<<<<<<< HEAD
	/*@Override
	public Optional<Carrito> remove(int id) {
		return carritoRepository.findById(id);
	}*/
=======
	@Override
	public boolean remove(int id) {
		return false;
	}
>>>>>>> feeb83066afeca7241cfca5f41ce30f1a30dbdee

	@Override
	public Carrito insertOrUpdate(Carrito carrito) {
		return carritoRepository.save(carrito);
	}


	@Override
	public List<Carrito> getAll() {
		return carritoRepository.findAllWithItems();
		
	}

	//REVISAR
	@Override
	public Optional<Carrito> findById(int id) throws Exception {
		Optional<Carrito> carritoToUpdate = carritoRepository.findById(id);
		return carritoToUpdate;
	}

<<<<<<< HEAD
	@Override
	public List<Carrito> findLotesByFechaBetween(LocalDate inicio, LocalDate fin) {
		return carritoRepository.findByFechaBetween(inicio, fin);
	}

	@Override
	public boolean remove(int id) {
		// TODO Auto-generated method stub
		return false;
	}
=======
	
>>>>>>> feeb83066afeca7241cfca5f41ce30f1a30dbdee
	
}
