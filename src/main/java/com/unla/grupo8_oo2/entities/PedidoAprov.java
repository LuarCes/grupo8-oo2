package com.unla.grupo8_oo2.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class PedidoAprov {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="cantidad", unique=false, nullable=false)
	private int cantidad;
	
	@ManyToOne(fetch = FetchType.LAZY)
	// normalmente nullable es igual a false, en este caso se deja como true porque el ejemplo es simple
	@JoinColumn(name="idProducto", nullable=true) //Revisar
	private Producto producto;
	
	@OneToOne
	@JoinColumn(name = "proveedor_id")
	private Proveedor Proveedor;
	
	@Column(name="fechaPedido", unique=false, nullable=false)
	private LocalDate fechaPedido;
	
	@Column(name="total", unique=false, nullable=false)
	private double total;

	public PedidoAprov(int cantidad, Producto producto, com.unla.grupo8_oo2.entities.Proveedor proveedor,
			LocalDate fechaPedido, double total) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
		Proveedor = proveedor;
		this.fechaPedido = fechaPedido;
		this.total = total;
	}
	
	
	
}