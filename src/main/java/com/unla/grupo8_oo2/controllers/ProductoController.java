package com.unla.grupo8_oo2.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.helpers.ViewRouteHelper;
import com.unla.grupo8_oo2.services.IProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	private IProductoService productoService;
	
	public ProductoController(IProductoService productoService) {
		this.productoService = productoService;
	}
	

    @GetMapping("")
    public ModelAndView producto() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO); // Usa "producto/index" directamente si es correcto
        mAV.addObject("productos", productoService.getAll()); // Asegúrate de que productoService está correctamente inyectado
        return mAV;
    }
	
	
	@GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.PRODUCTO_NEW);
		mAV.addObject("producto", new Producto());
		return mAV;
	}
	
	@PostMapping("/create")
	public RedirectView create(@ModelAttribute("producto") Producto producto) {
		productoService.insertOrUpdate(producto);
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
	
}
