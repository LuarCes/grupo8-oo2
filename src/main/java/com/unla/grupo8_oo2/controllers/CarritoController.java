package com.unla.grupo8_oo2.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
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
import com.unla.grupo8_oo2.services.implementation.UserService;
import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.Item;
import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.entities.Stock;
import com.unla.grupo8_oo2.entities.User;
import com.unla.grupo8_oo2.helpers.ViewRouteHelper;


@Controller
@RequestMapping("/carrito")
public class CarritoController {

	private ICarritoService carritoService;
	private IStockService stockService;

	@Autowired
	private ProductoService productoService;
	@Autowired
	private UserService userService;

	public CarritoController(ICarritoService carritoService, IStockService stockService) {
		this.carritoService = carritoService;
		this.stockService = stockService;
	}

	private Set<Item> lstItem = new HashSet<>();

	@GetMapping("")
	public ModelAndView carrito() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CARRITO);
		// Encabezados de respuesta para evitar el cache
		mAV.addObject("headerCacheControl", "no-cache, no-store, must-revalidate");
		mAV.addObject("headerPragma", "no-cache");
		mAV.addObject("headerExpires", "0");

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
			Optional<Item> optionalItem = lstItem.stream().filter(item -> item.getProducto().getId() == productoId)
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

			redirectAttributes.addFlashAttribute("message", "Producto agregado al carrito");
		} else {
			redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
		}

		return "redirect:/carrito";
	}

	@PostMapping("/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<String> eliminarItem(@PathVariable("id") int itemId, RedirectAttributes redirectAttributes) {
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
			redirectAttributes.addFlashAttribute("message", "Producto eliminado del carrito");
			return ResponseEntity.ok("Producto eliminado del carrito");
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ítem no encontrado en el carrito");
	}

	@PostMapping("/create")
	public RedirectView create(RedirectAttributes redirectAttributes) {
		System.out.println(lstItem);


		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userService.findUserByUsername(username);

		Optional<Carrito> carritoExistente = carritoService.findByUser(user);
		Carrito carrito;

		if (carritoExistente.isPresent()) {
			carrito = carritoExistente.get();
			for (Item item : lstItem) {
				item.setCarrito(carrito);
				carrito.getLstItem().add(item);
			}
		} else {
			carrito = new Carrito(LocalDate.now(), LocalTime.now(), lstItem, user);
		}

		carritoService.insertOrUpdate(carrito);

		Producto auxProducto = null;
		Stock auxStock = null;
		int stockActu=0;
		
		
		 // Añadir cada item al carrito recuperado
	    for (Item item : lstItem) {
	        auxProducto= item.getProducto();
	        auxStock= stockService.findByProducto(auxProducto);
	        stockActu=auxStock.getStockActual()-item.getCantidad();
	        auxStock.setStockActual(stockActu);
	        stockService.insertOrUpdate(auxStock);
	    }
		lstItem = new HashSet<Item>();

		redirectAttributes.addFlashAttribute("message", "Compra finalizada con éxito.");
		return new RedirectView(ViewRouteHelper.CARRITO_ROOT);
	}

	@GetMapping("/")
	public RedirectView redirectToHomeIndex() {
		return new RedirectView(ViewRouteHelper.CARRITO_ROOT);
	}

}
