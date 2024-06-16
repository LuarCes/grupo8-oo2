package com.unla.grupo8_oo2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.unla.grupo8_oo2.helpers.ViewRouteHelper;
import com.unla.grupo8_oo2.services.ILoteService;
import com.unla.grupo8_oo2.services.IStockService;

@Controller
@RequestMapping("/stock")
public class StockController {
	
private IStockService stockService;
	
	public StockController(IStockService stockService) {
		this.stockService = stockService;
	}
	

    @GetMapping("")
    public ModelAndView lote() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.STOCK); 
        mAV.addObject("stock", stockService.getAll());
        return mAV;
    }
	
	
	
}
