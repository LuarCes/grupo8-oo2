package com.unla.grupo8_oo2.helpers;

public class ViewRouteHelper {
	/**** Views ****/
	//HOME
	public final static String INDEX = "home/index";
	public final static String HELLO = "home/hello";

	
	//USER
	public final static String USER_LOGIN = "user/login";
	public final static String USER_LOGOUT = "user/logout";

	//PRODUCTO
	public final static String PRODUCTO = "producto/index";
	public final static String PRODUCTO_NEW = "producto/new";
	public final static String PRODUCTO_UPDATE = "producto/update";
	
	/**** Redirects ****/
	public final static String ROUTE = "/index"; 
	public final static String PRODUCTO_ROOT = "/producto";
	public final static String CARRITO_ROOT = "/carrito";
	public final static String LOTE_ROOT = "/lote";
	
	//LOTE
	public final static String LOTE = "lote/index";
	public final static String LOTE_NEW = "lote/new";
	public final static String LOTE_UPDATE = "lote/update";
	
	//APROVISIONAMIENTO
	public final static String APROV = "pedidosAprov/index";
	public final static String APROV_NEW = "pedidosAprov/new";
	
	//REPORTES STOCK
	public final static String STOCK = "stock/index";
	
	//CARRITO
	public final static String CARRITO = "carrito/index";
	
}
