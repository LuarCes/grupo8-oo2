package com.unla.grupo8_oo2.controllers;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.unla.grupo8_oo2.entities.Lote;
import com.unla.grupo8_oo2.entities.PedidoAprov;
import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.entities.Proveedor;
import com.unla.grupo8_oo2.helpers.ViewRouteHelper;
import com.unla.grupo8_oo2.services.ILoteService;
import com.unla.grupo8_oo2.services.IPedidoAprovService;
import com.unla.grupo8_oo2.services.IProveedorService;
import com.unla.grupo8_oo2.services.IStockService;

@Controller
@RequestMapping("/pedidosAprov")
public class PedidoAprovController {

	private IPedidoAprovService pedidoAprovService;
	private IProveedorService proveedorService;
	private IStockService stockService;
	private ILoteService loteService;

	public PedidoAprovController(IPedidoAprovService pedidoAprovService, IProveedorService proveedorService,
			IStockService stockService, ILoteService loteService) {
		super();
		this.pedidoAprovService = pedidoAprovService;
		this.proveedorService = proveedorService;
		this.stockService = stockService;
		this.loteService = loteService;
	}

	@GetMapping("")
    public ModelAndView pedidosAprov() {
		
		ModelAndView mav = new ModelAndView();
		// Obteniene la información de autenticación del contexto de seguridad
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        // Verifica si el usuario tiene el rol ROLE_ADMIN
        boolean isAdmin = auth != null && auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            mav.setViewName(ViewRouteHelper.APROV);
            mav.addObject("pedidos", pedidoAprovService.getAll());
        } else {
            mav.setView(new RedirectView(ViewRouteHelper.NOT_ADMIN));
        }

        return mav;
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
        
        return new RedirectView(ViewRouteHelper.APROV_ROOT);
    }
    
	@PostMapping("{id}")
	public RedirectView get(@ModelAttribute("p") PedidoAprov p) throws Exception {
			PedidoAprov pedidoAprov = pedidoAprovService.findById(p.getId()).get();
			pedidoAprov.setEntregado(true);
			
		 	Set<PedidoAprov> setAprov = new HashSet<PedidoAprov>();
	        setAprov.add(pedidoAprov);
	        
	        double precioProducto = pedidoAprov.getProducto().getCosto();
	        int cantidad = pedidoAprov.getCantidad();
	        double total = precioProducto * cantidad;
	        
	        int stockActual = stockService.findByProducto(pedidoAprov.getProducto()).getStockActual();
	        
	        Lote lote = new Lote(cantidad, LocalDate.now(), setAprov, total, pedidoAprov.getProducto(), stockActual);
	        loteService.insertOrUpdate(lote);
	        pedidoAprovService.insertOrUpdate(pedidoAprov);
		return new RedirectView(ViewRouteHelper.APROV_ROOT);
	}
    
    
    
    
}
