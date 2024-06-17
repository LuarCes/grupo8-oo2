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
    
    @PostMapping("/update")
	public RedirectView update(@ModelAttribute("lote") Lote lote) throws Exception {
		Optional<Lote> loteToUpdate = loteService.findById(lote.getId());
		if(loteToUpdate.isPresent()) {
			loteToUpdate.get().setCantRecibida(lote.getCantRecibida());
			loteToUpdate.get().setFechaRecepcion(lote.getFechaRecepcion());
			loteToUpdate.get().setPrecioCompra(lote.getPrecioCompra());
			loteToUpdate.get().setProducto(lote.getProducto());
			loteToUpdate.get().setAprobado(lote.isAprobado());
		}
		return new RedirectView(ViewRouteHelper.LOTE_ROOT);
	}
}
