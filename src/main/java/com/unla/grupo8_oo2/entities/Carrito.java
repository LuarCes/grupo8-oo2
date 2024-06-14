package com.unla.grupo8_oo2.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public class Carrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="fecha", unique=false, nullable=false)
	private LocalDate fecha;

	@Column(name="hora", unique=false, nullable=false)
	private LocalTime hora;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="item_id", nullable= false)
	private Item item;
	
	@OneToOne
	@JoinColumn(name="usuario_id", nullable= false)
	private User cliente;

	public Carrito(LocalDate fecha, LocalTime hora, Item item, User cliente) {
		this.fecha = fecha;
		this.hora = hora;
		this.item = item;
		this.cliente = cliente;
	}
	
}
