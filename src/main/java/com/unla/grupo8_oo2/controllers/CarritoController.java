package com.unla.grupo8_oo2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo8_oo2.services.ICarritoService;
import com.unla.grupo8_oo2.services.IStockService;
import com.unla.grupo8_oo2.services.implementation.ProductoService;
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
	
	@GetMapping("")
	public ModelAndView carrito() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CARRITO);
		mAV.addObject("stock", stockService.traerMayoresACero());
		System.out.println(stockService.traerMayoresACero());
		return mAV;
	}
	
	
	@GetMapping("/")
	public RedirectView redirectToHomeIndex() {
		return new RedirectView(ViewRouteHelper.CARRITO_ROOT);
	}
	
	@GetMapping("/carrito")
    public String mostrarCarrito(Model model) {
        List<Producto> productos = productoService.getAll(); // Obtiene todos los productos de alguna fuente (base de datos, servicio, etc.)
        model.addAttribute("productos", productos);
        return "carrito"; // Nombre de tu vista HTML
    }
	
}
