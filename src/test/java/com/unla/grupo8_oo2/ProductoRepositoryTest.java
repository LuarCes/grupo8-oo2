package com.unla.grupo8_oo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import com.unla.grupo8_oo2.entities.Producto;
import com.unla.grupo8_oo2.repositories.IProductoRepository;

@DataJpaTest
@ActiveProfiles("test")
public class ProductoRepositoryTest {

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testGuardarProducto() {
        // Crear un nuevo producto
        Producto producto = new Producto("NombreProducto", "ABC123", "Descripción del producto", 10.0, 20.0);

        // Guardar el producto en la base de datos
        Producto productoGuardado = productoRepository.save(producto);

        // Verificar que se haya guardado correctamente
        assertNotNull(productoGuardado.getId(), "El ID del producto no debería ser nulo después de guardarlo");

        // Recuperar el producto de la base de datos y verificar sus atributos
        Optional<Producto> productoRecuperado = productoRepository.findById(productoGuardado.getId());
        assertTrue(productoRecuperado.isPresent(), "No se encontró el producto guardado en la base de datos");

        assertEquals("NombreProducto", productoRecuperado.get().getNombre(), "El nombre del producto debe ser 'NombreProducto'");
        assertEquals("ABC123", productoRecuperado.get().getCodigo(), "El código del producto debe ser 'ABC123'");
        assertEquals("Descripción del producto", productoRecuperado.get().getDescripcion(), "La descripción del producto debe ser 'Descripción del producto'");
        assertEquals(10.0, productoRecuperado.get().getCosto(), 0.01, "El costo del producto debe ser 10.0");
        assertEquals(20.0, productoRecuperado.get().getPrecioVenta(), 0.01, "El precio de venta del producto debe ser 20.0");
    }

    @Test
    public void testListarProductos() {
        // Crear varios productos
        Producto producto1 = new Producto("Producto1", "P1", "Descripción producto 1", 10.0, 20.0);
        Producto producto2 = new Producto("Producto2", "P2", "Descripción producto 2", 15.0, 25.0);

        // Guardar los productos en la base de datos
        entityManager.persist(producto1);
        entityManager.persist(producto2);
        entityManager.flush(); // Forzar la escritura en la base de datos

        // Obtener la lista de productos
        List<Producto> productos = productoRepository.findAll();

        // Verificar que la lista no esté vacía y tenga los productos guardados
        assertNotNull(productos, "La lista de productos no debería ser nula");
        assertEquals(2, productos.size(), "Se esperan 2 productos en la lista");

        // Verificar los atributos de los productos recuperados
        assertEquals("Producto1", productos.get(0).getNombre(), "El nombre del primer producto debe ser 'Producto1'");
        assertEquals("Producto2", productos.get(1).getNombre(), "El nombre del segundo producto debe ser 'Producto2'");
    }
}
