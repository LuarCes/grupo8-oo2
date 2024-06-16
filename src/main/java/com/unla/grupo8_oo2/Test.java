package com.unla.grupo8_oo2;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.entities.User;
import com.unla.grupo8_oo2.repositories.ICarritoRepository;
import com.unla.grupo8_oo2.repositories.IUserRepository;
import com.unla.grupo8_oo2.services.implementation.ProductoService;
import com.unla.grupo8_oo2.services.implementation.UserService;

@SpringBootApplication
public class Test {

    private final ProductoService productoService;
    private final IUserRepository userRepository;
    private final ICarritoRepository carritoRepository;
    
    public Test(ProductoService productoService, IUserRepository userService, ICarritoRepository carritoRepository) {
        this.productoService = productoService;
        this.userRepository = userService;
        this.carritoRepository = carritoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Test.class, args);
    }

    @Bean
    public CommandLineRunner run(ApplicationContext ctx) {
        return args -> {
            // Probar el método getAll() del servicio
            //List<Producto> productos = productoService.getAll();
            //System.out.println("Todos los productos: " + productos);

        	//User user = userRepository.findByUsernameAndFetchUserRolesEagerly("cliente");
        	//System.out.println(user);
        	
        	
        	
        	//Carrito carrito = new Carrito;
        	
            // Aquí puedes agregar más pruebas para otros métodos de ProductoService
        };
    }
}