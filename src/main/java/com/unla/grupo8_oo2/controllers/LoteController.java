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
import com.unla.grupo8_oo2.services.ILoteService;
import com.unla.grupo8_oo2.services.IProductoService;

@Controller
@RequestMapping("/lote")
public class LoteController {

	
	private ILoteService loteService;
	
	public LoteController(ILoteService loteService) {
		this.loteService = loteService;
	}
	

    @GetMapping("")
    public ModelAndView producto() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOTE); 
        mAV.addObject("lotes", loteService.getAll());
        return mAV;
    }
	
	
	
	
}
