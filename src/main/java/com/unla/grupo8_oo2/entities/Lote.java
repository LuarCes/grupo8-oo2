package com.unla.grupo8_oo2.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Lote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="cantRecibida", unique=false, nullable=false)
	private int cantRecibida;
	
	@Column(name="fechaRecepcion", unique=false, nullable=false)
	private LocalDate fechaRecepcion;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="lote") //Revisar
	private Set<PedidoAprov> pedido = new HashSet<>();
	
	@Column(name="precioCompra", unique=false, nullable=false)
	private double precioCompra;
	
	@ManyToOne(fetch = FetchType.LAZY)
	// normalmente nullable es igual a false, en este caso se deja como true porque el ejemplo es simple
	@JoinColumn(name="idProducto", nullable=true) //Revisar
	private Producto producto;

	public Lote(int cantRecibida, LocalDate fechaRecepcion, Set<PedidoAprov> pedido, double precioCompra,
			Producto producto) {
		super();
		this.cantRecibida = cantRecibida;
		this.fechaRecepcion = fechaRecepcion;
		this.pedido = pedido;
		this.precioCompra = precioCompra;
		this.producto = producto;
	}
	
	
}
