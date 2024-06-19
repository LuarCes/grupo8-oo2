package com.unla.grupo8_oo2.controllers;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.entities.Stock;
import com.unla.grupo8_oo2.helpers.ViewRouteHelper;
import com.unla.grupo8_oo2.services.IProductoService;
import com.unla.grupo8_oo2.services.IStockService;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	private IProductoService productoService;
	private IStockService stockService;
	
	public ProductoController(IProductoService productoService, IStockService stockService) {
		this.productoService = productoService;
		this.stockService = stockService;
	}
	
	

    @GetMapping("")
    public ModelAndView producto() {
    	
    	 ModelAndView mav = new ModelAndView();

         // Obteniene la información de autenticación del contexto de seguridad
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();
         
         // Verifica si el usuario tiene el rol ROLE_ADMIN
         boolean isAdmin = auth != null && auth.getAuthorities().stream()
                 .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

         if (isAdmin) {
             mav.setViewName(ViewRouteHelper.PRODUCTO);
             mav.addObject("productos", productoService.getAll());
         } else {
             // Si no es administrador, redirigir a la página de no administrador
             mav.setView(new RedirectView(ViewRouteHelper.NOT_ADMIN));
         }

         return mav;
    }
	
	
	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_NEW);
		mAV.addObject("producto", new Producto());
		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("producto") Producto producto, @RequestParam("stockCritico") int stockCritico) {
		productoService.insertOrUpdate(producto);
		Stock s = new Stock(producto, 0, stockCritico);
		stockService.insertOrUpdate(s);
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}
	
	@GetMapping("{id}")
	public ModelAndView get(@PathVariable("id") int id) throws Exception {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_UPDATE);
		Producto producto = productoService.findById(id).get();
		mAV.addObject("producto", producto);
		return mAV;
	}
	
	@PostMapping("/update")
	public RedirectView update(@ModelAttribute("producto") Producto producto) throws Exception {
		Optional<Producto> productoToUpdate = productoService.findById(producto.getId());
		if(productoToUpdate.isPresent()) {
			productoToUpdate.get().setNombre(producto.getNombre());
			productoToUpdate.get().setCodigo(producto.getCodigo());
			productoToUpdate.get().setCosto(producto.getCosto());
			productoToUpdate.get().setPrecioVenta(producto.getPrecioVenta());
			productoToUpdate.get().setDescripcion(producto.getDescripcion());
			productoService.insertOrUpdate(productoToUpdate.get());
		}
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}
	
	@PostMapping("/delete/{id}")
	public RedirectView delete(@PathVariable("id") int id){
		productoService.remove(id);
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}
	
	@GetMapping("/")
	public RedirectView redirectToHomeIndex() {
		return new RedirectView(ViewRouteHelper.PRODUCTO_ROOT);
	}
	
	
	
	@GetMapping("/consulta-por-producto")
	public ModelAndView consultaPorProductoForm() {
	    ModelAndView mAV = new ModelAndView(ViewRouteHelper.INFORME); 
	    mAV.addObject("productos", productoService.getAll());
	    return mAV;
	}
	
}
