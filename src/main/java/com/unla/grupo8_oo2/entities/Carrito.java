package com.unla.grupo8_oo2.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter @Setter @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Carrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="fecha", unique=false, nullable=false)
	private LocalDate fecha;

	@Column(name="hora", unique=false, nullable=false)
	private LocalTime hora;
	
	
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "carrito",
			cascade =CascadeType.ALL)
	private Set<Item> lstItem = new HashSet<>();
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="usuario_id", nullable= false)
	private User cliente;

	public Carrito(LocalDate fecha, LocalTime hora, Set<Item> lstItem, User cliente) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.lstItem = lstItem;
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "\nCarrito [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", lstItem=" + lstItem + ", cliente="
				+ cliente + "]";
	}

	
		
}
