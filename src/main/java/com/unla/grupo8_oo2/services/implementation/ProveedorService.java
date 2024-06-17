package com.unla.grupo8_oo2.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unla.grupo8_oo2.entities.Proveedor;
import com.unla.grupo8_oo2.repositories.IProveedorRepository;
import com.unla.grupo8_oo2.services.IProveedorService;

@Service("proveedorService")
public class ProveedorService implements IProveedorService{
	@Autowired
	private IProveedorRepository proveedorRepository;
	
	@Override
	public List<Proveedor> getAll() {
		return proveedorRepository.findAll();
	}
	
}
