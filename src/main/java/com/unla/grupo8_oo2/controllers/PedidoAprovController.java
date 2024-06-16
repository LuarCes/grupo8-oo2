package com.unla.grupo8_oo2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo8_oo2.helpers.ViewRouteHelper;
import com.unla.grupo8_oo2.services.IPedidoAprovService;

@Controller
@RequestMapping("/pedidosAprov")
public class PedidoAprovController {

	private IPedidoAprovService pedidoAprovService;
	
	public PedidoAprovController(IPedidoAprovService pedidoAprovService) {
		this.pedidoAprovService = pedidoAprovService;
	}
	

    @GetMapping("")
    public ModelAndView pedidosAprov() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.APROV); 
        mAV.addObject("pedidosAprovisionamiento", pedidoAprovService.getAll());
        return mAV;
    }
	
	
	
}
