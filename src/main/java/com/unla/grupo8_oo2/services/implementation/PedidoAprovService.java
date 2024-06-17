package com.unla.grupo8_oo2.services.implementation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo8_oo2.entities.PedidoAprov;
import com.unla.grupo8_oo2.repositories.IPedidoAprovRepository;
import com.unla.grupo8_oo2.services.IPedidoAprovService;

@Service("pedidoAprovService")
public class PedidoAprovService implements IPedidoAprovService {
	@Autowired
	private IPedidoAprovRepository pedidoAprovRepository;
	
	@Override
	public List<PedidoAprov> getAll() {
		return pedidoAprovRepository.findAll();
	}
	
	@Override
	public Optional<PedidoAprov> findByFechaPedido(LocalDate fechaPedido) {
		return pedidoAprovRepository.findByFechaPedido(fechaPedido);
	}
	
	@Override
	public boolean remove(int id) {
		try {
			pedidoAprovRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public Optional<PedidoAprov> findById(int id) throws Exception {
		return pedidoAprovRepository.findById(id);
	}

	@Override
	public List<PedidoAprov> findByProveedor(String nombre) {
		return pedidoAprovRepository.findByProveedor(nombre);
	}
	
	public PedidoAprov insertOrUpdate(PedidoAprov pedidoAprov) {
		return pedidoAprovRepository.save(pedidoAprov);
	}
}
