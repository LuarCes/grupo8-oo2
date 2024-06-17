package com.unla.grupo8_oo2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo8_oo2.services.ICarritoService;
import com.unla.grupo8_oo2.helpers.ViewRouteHelper;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

	private ICarritoService carritoService;
	
	public CarritoController(ICarritoService carritoService) {
		this.carritoService = carritoService;
	}
	
	@GetMapping("")
	public ModelAndView carrito() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.CARRITO);
		mAV.addObject("carrito", carritoService.getAll());
		return mAV;
	}
	
	
	@GetMapping("/")
	public RedirectView redirectToHomeIndex() {
		return new RedirectView(ViewRouteHelper.CARRITO_ROOT);
	}
}
