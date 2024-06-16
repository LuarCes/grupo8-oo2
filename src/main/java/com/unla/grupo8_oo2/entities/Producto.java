package com.unla.grupo8_oo2.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name="nombre", unique=true, nullable=false, length=45)
	private String nombre;

	@Column(name="codigo", unique=true, nullable=false, length=10)
	private String codigo;
	
	@Column(name="descripcion", unique=false, nullable=false, length=100)
	private String descripcion;
	
	@Column(name="costo", unique=false, nullable=false)
	private double costo;
	
	@Column(name="precioVenta", unique=false, nullable=false)
	private double precioVenta;
	
	@OneToOne(mappedBy = "producto", fetch = FetchType.LAZY)
    private Item item;
	
	@OneToOne(mappedBy = "producto", fetch = FetchType.LAZY)
	private Stock stock;
	
	@OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private Set<Lote> lotes = new HashSet<>();
	
	@OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    private Set<PedidoAprov> pedidosAprov = new HashSet<>();

	public Producto(String nombre, String codigo, String descripcion, double costo, double precioVenta) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.costo = costo;
		this.precioVenta = precioVenta;
	}

	@Override
	public String toString() {
		return "\nProducto [id=" + id + ", nombre=" + nombre + ", codigo=" + codigo + ", descripcion=" + descripcion
				+ ", costo=" + costo + ", precioVenta=" + precioVenta + "]";
	}
	
	
}
