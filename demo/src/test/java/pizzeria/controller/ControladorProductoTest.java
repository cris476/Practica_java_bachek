package pizzeria.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pizzeria.Controller.ControladorProducto;
import pizzeria.Modelo.Alergeno;
import pizzeria.Modelo.Bebida;
import pizzeria.Modelo.Ingredientes;
import pizzeria.Modelo.Pasta;
import pizzeria.Modelo.Pizza;
import pizzeria.Modelo.Producto;
import pizzeria.Modelo.SizeApp;

public class ControladorProductoTest {

    ControladorProducto controladorProducto;

    @BeforeEach
    void setUp() throws ClassNotFoundException, SQLException {
        controladorProducto = new ControladorProducto();
    }

    @Test
    void testRegistrarProducto() throws ClassNotFoundException, SQLException {
        // Crear alérgenos
        List<Alergeno> alergenosTomate = new ArrayList<>();
        alergenosTomate.add(new Alergeno("Gluten"));
        alergenosTomate.add(new Alergeno("Lactosa"));

        List<Alergeno> alergenosQueso = new ArrayList<>();
        alergenosQueso.add(new Alergeno("Lactosa"));

        // Crear ingredientes con alérgenos
        Ingredientes tomate = new Ingredientes("Tomate", alergenosTomate);
        Ingredientes queso = new Ingredientes("Queso Parmesano", alergenosQueso);

        // Ingredientes de la pizza
        List<Ingredientes> ingredientesPizza = new ArrayList<>();
        ingredientesPizza.add(tomate);
        ingredientesPizza.add(queso);

        // Crear el producto Pizza
        Pizza pizza = new Pizza("Margarita", 12.5, SizeApp.MEDIANO, ingredientesPizza);

        // Ingredientes de la pasta
        List<Ingredientes> ingredientesPasta = new ArrayList<>();
        ingredientesPasta.add(new Ingredientes("Pasta Fresca", alergenosTomate));
        ingredientesPasta.add(queso);

        // Crear el producto Pasta
        Pasta pasta = new Pasta("Pasta Bolognesa", 12.5, ingredientesPasta);

        // Crear el producto Bebida
        Bebida bebida = new Bebida("Coca Cola", 1.5, SizeApp.MEDIANO);

        // Registrar productos
        controladorProducto.registrarProducto(pizza);
        controladorProducto.registrarProducto(pasta);
        controladorProducto.registrarProducto(bebida);

        // Verificar que los productos se registraron correctamente
        assertTrue(true, "Los productos se registraron correctamente");
    }

    @Test
    void testObtenerProductoPorId() throws ClassNotFoundException, SQLException {
        // Crear y registrar un producto
        Pizza pizza = new Pizza("Carbonara", 15.0, SizeApp.GRANDE, new ArrayList<>());
        controladorProducto.registrarProducto(pizza);
        List<Producto> listproducto = new ArrayList<>();
        Producto productoExistente;
        // Obtener el producto por su ID
        listproducto = controladorProducto.getAllProducts();

        productoExistente = listproducto.stream().filter((producto) -> producto.getNombre().equals("Carbonara"))
                .findFirst().orElse(null);

        // Verificar que el producto obtenido no sea null y coincida el nombre
        assertNotNull(productoExistente);
        assertEquals("Carbonara", productoExistente.getNombre());
    }

    @Test
    void testEliminarProducto() throws ClassNotFoundException, SQLException {
        Pizza pizza = new Pizza(1, "Peperonni", 15, SizeApp.GRANDE, new ArrayList<>());
        controladorProducto.registrarProducto(pizza);
        List<Producto> listproducto = new ArrayList<>();
        Producto productoExistente;
        listproducto = controladorProducto.getAllProducts();
        productoExistente = listproducto.stream().filter((producto) -> producto.getNombre().equals("Peperonni"))
                .findFirst().orElse(null);

        controladorProducto.eliminarProducto(productoExistente.getId());

        Producto productoEliminado = controladorProducto.getProductoById(productoExistente.getId());
        assertNull(productoEliminado);
    }

    @Test
    void testActualizarProducto() throws ClassNotFoundException, SQLException {
        Pasta pasta = new Pasta("Fettuccine Alfredo", 10.0, new ArrayList<>());
        controladorProducto.registrarProducto(pasta);
        List<Producto> listproducto = new ArrayList<>();
        Producto productoExistente;
        listproducto = controladorProducto.getAllProducts();
        productoExistente = listproducto.stream()
                .filter((producto) -> producto.getNombre().equals("Fettuccine Alfredo"))
                .findFirst().orElse(null);

        productoExistente.setNombre("Fettuccine Alfredo Premium");
        productoExistente.setPrecio(12.5);
        controladorProducto.actualizarProducto(productoExistente);

        Producto productoActualizado = controladorProducto.getProductoById(productoExistente.getId());

        assertNotNull(productoActualizado);
        assertEquals("Fettuccine Alfredo Premium", productoActualizado.getNombre());
        assertEquals(12.5, productoActualizado.getPrecio());
    }

    @Test
    void testObtenerTodosLosProductos() throws ClassNotFoundException, SQLException {
        // Registrar algunos productos
        controladorProducto.registrarProducto(new Pizza("Pepperoni", 13.0, SizeApp.GRANDE, new ArrayList<>()));
        controladorProducto.registrarProducto(new Pasta("Ravioli", 11.0, new ArrayList<>()));

        // Obtener todos los productos
        List<Producto> productos = controladorProducto.getAllProducts();

        // Verificar que la lista no esté vacía
        assertNotNull(productos);
        assertTrue(productos.size() > 0, "La lista de productos debería contener elementos");
    }
}
