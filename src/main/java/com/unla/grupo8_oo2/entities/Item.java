package com.unla.grupo8_oo2.entities;

import java.util.Optional;

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
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="cantidad", unique=false, nullable=false)
	private int cantidad;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="producto_id")
	private Producto producto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="carrito_id", nullable=true)
	private Carrito carrito;
	
	public Item(int cantidad, Producto producto) {
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public Item(int id, int cantidad, Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", cantidad=" + cantidad + ", producto=" + producto + ", carrito=" + carrito + "]";
	}
	
	
	
	
}
