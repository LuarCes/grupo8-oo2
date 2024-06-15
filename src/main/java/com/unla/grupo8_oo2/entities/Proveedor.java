package com.unla.grupo8_oo2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="nombre", unique=false, nullable=false, length=20)
	private String nombre;

	@Column(name="telefono", unique=true, nullable=false, length=10)
	private String telefono;
	
	@OneToOne(mappedBy = "proveedor")
    private PedidoAprov pedidoAprov;
	
	public Proveedor(String nombre, String telefono) {
		this.nombre = nombre;
		this.telefono = telefono;
	}
	
}
