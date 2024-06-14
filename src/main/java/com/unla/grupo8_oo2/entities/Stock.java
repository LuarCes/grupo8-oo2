package com.unla.grupo8_oo2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name="producto_id")
	private Producto producto;
	
	@Column(name="stockActual")
	private int stockActual;
	
	@Column(name="stockCritico")
	private int descripcion;

	public Stock(Producto producto, int stockActual, int descripcion) {
		this.producto = producto;
		this.stockActual = stockActual;
		this.descripcion = descripcion;
	}
	
	
}
