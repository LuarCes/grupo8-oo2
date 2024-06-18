package com.unla.grupo8_oo2.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo8_oo2.entities.Lote;
import com.unla.grupo8_oo2.entities.Stock;
import com.unla.grupo8_oo2.helpers.ViewRouteHelper;
import com.unla.grupo8_oo2.services.ILoteService;
import com.unla.grupo8_oo2.services.IStockService;

@Controller
@RequestMapping("/lote")
public class LoteController {

	private ILoteService loteService;
	private IStockService stockService;

	public LoteController(ILoteService loteService, IStockService stockService) {
		this.loteService = loteService;
		this.stockService = stockService;
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
		Optional<Stock> stockToUpdate = Optional
				.ofNullable(stockService.findByProducto(loteToUpdate.get().getProducto()));
		int stockNuevo;
		if (loteToUpdate.isPresent()) {
			loteToUpdate.get().setCantRecibida(lote.getCantRecibida());
			loteToUpdate.get().setComentario(lote.getComentario());
			loteToUpdate.get().setAprobado(lote.isAprobado());
			stockNuevo = stockToUpdate.get().getStockActual() + loteToUpdate.get().getCantRecibida();
			stockToUpdate.get().setStockActual(stockNuevo);

			loteService.insertOrUpdate(loteToUpdate.get());
		}

		return new RedirectView(ViewRouteHelper.LOTE_ROOT);
	}

	@GetMapping("/informe")
	public ModelAndView informe() {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.INFORME);
		mAV.addObject("lote", loteService.getAll());
		return mAV;
	}

	@GetMapping("/consulta-fechas")
	public ModelAndView consultaFechas(
			@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
			@RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {
		ModelAndView mAV = new ModelAndView(ViewRouteHelper.INFORME); // Actualiza con la ruta correcta
		List<Lote> lotes = loteService.findLotesByFechaRecepcionBetween(fechaInicio, fechaFin);
		mAV.addObject("lotes", lotes);
		return mAV;
	}

}
