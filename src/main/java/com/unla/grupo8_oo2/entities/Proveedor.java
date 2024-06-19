package com.unla.grupo8_oo2.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

	@Column(name="telefono", unique=false, nullable=false, length=10)
	private String telefono;
	
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<PedidoAprov> pedidosAprov;
	
	public Proveedor(String nombre, String telefono) {
		this.nombre = nombre;
		this.telefono = telefono;
	}
	
}
