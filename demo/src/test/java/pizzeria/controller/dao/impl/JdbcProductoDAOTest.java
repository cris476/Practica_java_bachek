package pizzeria.controller.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import pizzeria.Controller.dao.impl.JdbcProductoDAO;
import pizzeria.Modelo.Pizza;
import pizzeria.Modelo.Producto;
import pizzeria.Modelo.SizeApp;

public class JdbcProductoDAOTest {

    JdbcProductoDAO jdbcProductoDAO = new JdbcProductoDAO();

    @Test
    void testGetAllProductos() throws ClassNotFoundException, SQLException {
        List<Producto> listaProductos = jdbcProductoDAO.getAllProductos();
        assertNotEquals(0, listaProductos.size());
    }

    // @Test
    // void testfindByNameAndSize() throws ClassNotFoundException, SQLException {

    //     Pizza pizza = new Pizza(1, "Peperonni", 12, SizeApp.MEDIANO, null);

    //     Producto producto = jdbcProductoDAO.findByNameAndSize(pizza);

    //     assertEquals(0, producto.getPrecio());
    // }
}
