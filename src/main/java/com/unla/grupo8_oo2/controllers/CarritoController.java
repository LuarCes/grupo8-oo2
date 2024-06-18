package com.unla.grupo8_oo2.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo8_oo2.services.ICarritoService;
import com.unla.grupo8_oo2.services.IStockService;
import com.unla.grupo8_oo2.services.implementation.ProductoService;
import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.Item;
import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.entities.User;
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
		// Encabezados de respuesta para evitar el cache
	    mAV.addObject("headerCacheControl", "no-cache, no-store, must-revalidate"); 
	    mAV.addObject("headerPragma", "no-cache"); 
	    mAV.addObject("headerExpires", "0"); 
		System.out.println("ENTRE AL CARRITO");
		System.out.println(lstItem);
		mAV.addObject("stock", stockService.traerMayoresACero());
		mAV.addObject("lstItem", lstItem);
		return mAV;
	}
	
	@PostMapping("/agregar")
	public String agregarItem(@RequestParam("productoId") int productoId, @RequestParam("cantidad") int cantidad, 
			RedirectAttributes redirectAttributes) throws Exception {
	    	Producto producto = productoService.findById(productoId).orElse(null);
	    
	    if (producto != null) {
	        // Obtener el stock actual del producto
	        int stockActual = stockService.findByProducto(producto).getStockActual();
	        
	        // Verifico si ya existe un item con este producto en lstItem
	        Optional<Item> optionalItem = lstItem.stream()
	                .filter(item -> item.getProducto().getId() == productoId)
	                .findFirst();

	        if (optionalItem.isPresent()) {
	            // Si ya existe, sumo las cantidades
	            Item itemExistente = optionalItem.get();
	            int nuevaCantidad = itemExistente.getCantidad() + cantidad;
	            
	            // Si la nueva cantidad supera al stock
	            if (nuevaCantidad > stockActual) {
	                redirectAttributes.addFlashAttribute("error", "La cantidad supera el stock disponible");
	                return "redirect:/carrito";
	            }
	            
	            itemExistente.setCantidad(nuevaCantidad);
	        } else {
	            // Si no existe, creo un nuevo item
	            if (cantidad > stockActual) {
	                redirectAttributes.addFlashAttribute("error", "La cantidad supera el stock disponible");
	                return "redirect:/carrito";
	            }
	            
	            Item item = new Item(cantidad, producto);
	            item.setId(lstItem.size() + 1);
	            lstItem.add(item);
	        }
	        
	        System.out.println("Producto agregado al carrito");
	        redirectAttributes.addFlashAttribute("message", "Producto agregado al carrito");
	    } else {
	        System.out.println("Producto no encontrado");
	        redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
	    }
	    
	    return "redirect:/carrito";
	}
	
	 @PostMapping("/eliminar/{id}")
	 @ResponseBody
	 public ResponseEntity<String> eliminarItem(@PathVariable("id") int itemId, RedirectAttributes redirectAttributes) {
	     System.out.println("ENTRE AL ELIMINAR");
	     System.out.println("Ítem con id " + itemId + " a eliminar");
	     
	     // Busco un item por su ID
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
	         return ResponseEntity.ok("Producto eliminado del carrito");
	     } 
	     
	     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ítem no encontrado en el carrito");
	 }
	 
	@PostMapping("/create")
	public RedirectView create() {
		System.out.println("/ESTOY DENTRO DEL CREATE");
		System.out.println(lstItem);
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		carritoService.insertOrUpdate(lstItem, username);
		System.out.println("CREE UN CARRITO NUEVO");
		return new RedirectView(ViewRouteHelper.CARRITO_ROOT);
	}
	
	
	@GetMapping("/")
	public RedirectView redirectToHomeIndex() {
		return new RedirectView(ViewRouteHelper.CARRITO_ROOT);
	}
	
	
}
