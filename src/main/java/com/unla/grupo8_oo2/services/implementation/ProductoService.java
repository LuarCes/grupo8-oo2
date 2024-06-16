package com.unla.grupo8_oo2.services.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.repositories.IProductoRepository;
import com.unla.grupo8_oo2.services.IProductoService;

@Service("productoService")
public class ProductoService implements IProductoService {

	private IProductoRepository productoRepository;
	
	public ProductoService(IProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	@Override
	public List<Producto> getAll() {
		return productoRepository.findAll();
	}

	@Override
	public Optional<Producto> findById(int id) throws Exception {
		return productoRepository.findById(id);
	}

	@Override
	public Optional<Producto> findByNombre(String nombre) throws Exception {
		return productoRepository.findByNombre(nombre);
	}

	@Override
	public Optional<Producto> findByCodigo(String codigo) throws Exception {
		return productoRepository.findByCodigo(codigo);
	}

	@Override
	public Producto insertOrUpdate(Producto producto) {
		return productoRepository.save(producto);
	}

	@Override
	public boolean remove(int id) {
		try {
			productoRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
