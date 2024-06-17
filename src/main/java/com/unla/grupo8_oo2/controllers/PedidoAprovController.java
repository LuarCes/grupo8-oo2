package com.unla.grupo8_oo2.controllers;

import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo8_oo2.entities.PedidoAprov;
import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.entities.Proveedor;
import com.unla.grupo8_oo2.helpers.ViewRouteHelper;
import com.unla.grupo8_oo2.services.IPedidoAprovService;
import com.unla.grupo8_oo2.services.IProveedorService;
import com.unla.grupo8_oo2.services.IStockService;

@Controller
@RequestMapping("/pedidosAprov")
public class PedidoAprovController {

	private IPedidoAprovService pedidoAprovService;
	private IProveedorService proveedorService;
	private IStockService stockService;

	public PedidoAprovController(IPedidoAprovService pedidoAprovService, IProveedorService proveedorService,
			IStockService stockService) {
		super();
		this.pedidoAprovService = pedidoAprovService;
		this.proveedorService = proveedorService;
		this.stockService = stockService;
	}

	@GetMapping("")
    public ModelAndView pedidosAprov() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.APROV); 
        mAV.addObject("pedidos", pedidoAprovService.getAll());
        return mAV;
    }
	
	@GetMapping("/new")
    public ModelAndView create() {
        ModelAndView mAV = new ModelAndView(ViewRouteHelper.APROV_NEW);
        mAV.addObject("pedidoAprov", new PedidoAprov());
        mAV.addObject("proveedores", proveedorService.getAll());
        mAV.addObject("productosEnStock", stockService.getAll().stream()
                .map(stock -> stock.getProducto())
                .collect(Collectors.toList()));
        return mAV;
    }
    
    //FALTA TRAER LOS PROVEEDORES
    @PostMapping("/create")
    public RedirectView create(@ModelAttribute("pedidoAprov") PedidoAprov pedidoAprov,
            @ModelAttribute("proveedor") Proveedor proveedor,
            @ModelAttribute("producto") Producto producto) {

        //calculo el total
        double precioProducto = pedidoAprov.getProducto().getCosto();
        int cantidad = pedidoAprov.getCantidad();
        double total = precioProducto * cantidad;

        pedidoAprov.setTotal(total);
        pedidoAprov.setProducto(producto);
        pedidoAprov.setProveedor(proveedor);
        pedidoAprovService.insertOrUpdate(pedidoAprov);

        return new RedirectView(ViewRouteHelper.APROV_UPDATE);
    }
    
}
