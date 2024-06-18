package com.unla.grupo8_oo2.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo8_oo2.services.ICarritoService;
import com.unla.grupo8_oo2.services.IStockService;
import com.unla.grupo8_oo2.services.implementation.ProductoService;
import com.unla.grupo8_oo2.entities.Item;
import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

	private ICarritoService carritoService;
	private IStockService stockService;
	
	@Autowired
	private ProductoService productoService;
	
	
	public CarritoController(ICarritoService carritoService, IStockService stockService) {
		this.carritoService = carritoService;
		this.stockService = stockService;
	}
	
	private List<Item> lstItem = new ArrayList<>();
	
	@GetMapping("")
	public ModelAndView carrito() {
		System.out.println("ENTRE AL CARRITO");
		System.out.println(lstItem);
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CARRITO);
		mAV.addObject("stock", stockService.traerMayoresACero());
		mAV.addObject("lstItem", lstItem);
		return mAV;
	}
	
	 @PostMapping("/agregar")
	    public String agregarItem(@RequestParam("productoId") int productoId, @RequestParam("cantidad") int cantidad, RedirectAttributes redirectAttributes) throws Exception {
	        Producto producto = productoService.findById(productoId).get(); // Implementa esta lógica para encontrar el producto
	        if (producto != null) {
	            Item item = new Item(cantidad, producto);
	            item.setId(lstItem.size() + 1);
	            lstItem.add(item);
	            System.out.println("Producto agregado al carrito");
	            redirectAttributes.addFlashAttribute("message", "Producto agregado al carrito");
	        } else {
	        	System.out.println("Producto no encontrado");
	            redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
	        }
	        return "redirect:/carrito";
	    }
	
	 @PostMapping("/eliminar/{id}")
	 public String eliminarItem(@PathVariable("id") int itemId, RedirectAttributes redirectAttributes) {
	     System.out.println("ENTRE AL ELIMINAR");
	     System.out.println("Ítem con id " + itemId + " a eliminar");
	     
	     // Buscar el ítem por su ID
	     Item itemToRemove = null;
	     for (Item item : lstItem) {
	         if (item.getId() == itemId) {
	             itemToRemove = item;
	             break;
	         }
	     }

	     if (itemToRemove != null) {
	         lstItem.remove(itemToRemove);
	         System.out.println(lstItem);
	         System.out.println("Producto eliminado del carrito");
	         redirectAttributes.addFlashAttribute("message", "Producto eliminado del carrito");
	     } else {
	         System.out.println("Ítem con id" + itemId + " no encontrado en el carrito");
	         redirectAttributes.addFlashAttribute("error", "Ítem no encontrado en el carrito");
	     }
	     
	     return "redirect:/carrito";
	 }
	 
	@PostMapping("/create")
	public RedirectView create() {
		System.out.println("/ESTOY DENTRO DEL CREATE");
		System.out.println(lstItem);
		//carritoService.insertOrUpdate(lstItem);
		return new RedirectView(ViewRouteHelper.CARRITO_ROOT);
	}
	
	
	@GetMapping("/")
	public RedirectView redirectToHomeIndex() {
		return new RedirectView(ViewRouteHelper.CARRITO_ROOT);
	}
	/*
	@GetMapping("/carrito")
    public String mostrarCarrito(Model model) {
        List<Producto> productos = productoService.getAll(); // Obtiene todos los productos de alguna fuente (base de datos, servicio, etc.)
        model.addAttribute("productos", productos);
        return "carrito"; // Nombre de tu vista HTML
    }*/
	
}
