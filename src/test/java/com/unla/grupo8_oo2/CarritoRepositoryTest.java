package com.unla.grupo8_oo2;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.unla.grupo8_oo2.entities.Carrito;
import com.unla.grupo8_oo2.repositories.ICarritoRepository;

@SpringBootTest
@Transactional
public class CarritoRepositoryTest {

    @Autowired
    private ICarritoRepository carritoRepository;

    @Test
    public void testAgregarCarrito() {
        // Crear un nuevo carrito
        Carrito carrito = new Carrito();
        carrito.setFecha(LocalDate.now());
        carrito.setHora(LocalTime.now());

        // Guardar el carrito en la base de datos
        Carrito carritoGuardado = carritoRepository.save(carrito);

        // Verificar que se haya guardado correctamente
        assertNotNull(carritoGuardado.getId(), "El ID del carrito no debería ser nulo después de guardarlo");
    }
}
