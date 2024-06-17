package com.unla.grupo8_oo2.repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.unla.grupo8_oo2.entities.Proveedor;

@Repository("proveedorRepository")
public interface IProveedorRepository extends JpaRepository<Proveedor, Serializable>{
	
	public abstract Optional<Proveedor> findById(int id);
	
}
