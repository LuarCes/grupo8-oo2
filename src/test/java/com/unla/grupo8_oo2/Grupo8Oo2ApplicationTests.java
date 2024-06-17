package com.unla.grupo8_oo2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.entities.User;
import com.unla.grupo8_oo2.repositories.ICarritoRepository;
import com.unla.grupo8_oo2.services.implementation.CarritoService;

@SpringBootTest
class Grupo8Oo2ApplicationTests {

	 @InjectMocks
	    private CarritoService carritoService;

    @Mock
    private ICarritoRepository carritoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
	
    @Test
    public void testGetAll() {
        // Crear datos de prueba
        User user = new User();
        user.setId(1);
        user.setUsername("testuser");

        Carrito carrito1 = new Carrito();
        carrito1.setId(1);
        carrito1.setFecha(LocalDate.now());
        carrito1.setHora(LocalTime.now());
        carrito1.setCliente(user);

        Carrito carrito2 = new Carrito();
        carrito2.setId(2);
        carrito2.setFecha(LocalDate.now());
        carrito2.setHora(LocalTime.now());
        carrito2.setCliente(user);

        List<Carrito> carritos = Arrays.asList(carrito1, carrito2);

        // Configurar comportamiento del repositorio simulado
        when(carritoRepository.findAll()).thenReturn(carritos);

        // Llamar al método de servicio
        List<Carrito> result = carritoService.getAll();

        // Verificar el resultado
        assertEquals(2, result.size());
        assertEquals(carrito1, result.get(0));
        assertEquals(carrito2, result.get(1));
    }

}
