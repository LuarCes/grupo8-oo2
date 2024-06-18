package com.unla.grupo8_oo2.entities;

import org.springframework.beans.factory.annotation.Autowired;

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

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
	private Producto producto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="carrito_id", nullable=false)
	private Carrito carrito;
	
	public Item(int cantidad, Producto producto) {
		this.cantidad = cantidad;
		this.producto = producto;
	}

	
	
	@Override
	public String toString() {
		return "\nItem [id=" + id + ", cantidad=" + cantidad + ", producto=" + producto + "]";
	}
	
	public String toStringConCarrito() {
		return "\nItem [id=" + id + ", cantidad=" + cantidad + ", producto=" + producto + ", carrito " + carrito.getId()+ "]";
	}
	
	
}
