package com.unla.grupo8_oo2.entities;

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
	private String cantidad;

	@OneToOne 
	@JoinColumn(name="producto_id")
	private Producto producto;
	

	public Item(String cantidad, Producto producto) {
		this.cantidad = cantidad;
		this.producto = producto;
	}

	public Item(int id, String cantidad, Producto producto) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.producto = producto;
	}
	
	
	
	
}
