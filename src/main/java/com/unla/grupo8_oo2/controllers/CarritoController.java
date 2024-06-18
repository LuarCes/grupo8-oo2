package com.unla.grupo8_oo2.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.unla.grupo8_oo2.entities.Stock;
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
	            redirectAttributes.addFlashAttribute("message", "Producto agregado al carrito");
	        } else {
	            redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
	        }
	        return "redirect:/carrito";
	    }
	
	 @PostMapping("/eliminar/{id}")
	    public RedirectView eliminarItem(@PathVariable("itemId") int itemId, RedirectAttributes redirectAttributes) {
		 System.out.println("ENTRE AL ELIMINAR");
		 if (itemId >= 0 && itemId < lstItem.size()) {
		        lstItem.remove(itemId);
		        redirectAttributes.addFlashAttribute("message", "Producto eliminado del carrito");
		    } else {
		        redirectAttributes.addFlashAttribute("error", "Ítem no encontrado en el carrito");
		    }
	        return new RedirectView(ViewRouteHelper.CARRITO);
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
