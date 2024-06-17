package com.unla.grupo8_oo2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo8_oo2.entities.Lote;
import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.helpers.ViewRouteHelper;
import com.unla.grupo8_oo2.services.ILoteService;

@Controller
@RequestMapping("/lote")
public class LoteController {

	private ILoteService loteService;
	
	public LoteController(ILoteService loteService) {
		this.loteService = loteService;
	}
	

    @GetMapping("")
    public ModelAndView lote() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOTE); 
        mAV.addObject("lotes", loteService.getAll());
        return mAV;
    }
	
    @GetMapping("/new")
	public ModelAndView create() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOTE_NEW);
		mAV.addObject("lote", new Lote());
		return mAV;
	}
	
    @GetMapping("{id}")
	public ModelAndView get(@PathVariable("id") int id) throws Exception {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.LOTE_UPDATE);
		Lote lote = loteService.findById(id).get();
		mAV.addObject("lote", lote);
		return mAV;
	}
}
