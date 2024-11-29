package pizzeria.controller.dao.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import pizzeria.Controller.dao.impl.JdbcPedidoDAO;
import pizzeria.Modelo.Alergeno;
import pizzeria.Modelo.Cliente;
import pizzeria.Modelo.EstadoPedido;
import pizzeria.Modelo.Ingredientes;
import pizzeria.Modelo.LineaPedido;
import pizzeria.Modelo.Pedido;
import pizzeria.Modelo.Pizza;
import pizzeria.Modelo.SizeApp;

public class JdbcPedidoDAOTest {

    JdbcPedidoDAO jdbcPedidoDAO = new JdbcPedidoDAO();

    @Test
    void testSave() throws ClassNotFoundException, SQLException {
        List<Alergeno> alergenosTomate = new ArrayList<>();
        alergenosTomate.add(new Alergeno("Pelizilina"));
        alergenosTomate.add(new Alergeno("Gluten"));
        alergenosTomate.add(new Alergeno("Lactosa"));
        alergenosTomate.add(new Alergeno("Frutos secos"));

        List<Alergeno> alergenosQueso = new ArrayList<>();
        alergenosQueso.add(new Alergeno("leche"));
        alergenosQueso.add(new Alergeno("leche1"));
        alergenosQueso.add(new Alergeno("leche2"));

        List<Ingredientes> ingredientesPizza = new ArrayList<>();
        ingredientesPizza.add(new Ingredientes("Tomate10", alergenosTomate));
        ingredientesPizza.add(new Ingredientes("Queso Mozzarella", alergenosQueso));
        ingredientesPizza.add(new Ingredientes("Peperonni", alergenosQueso));

        Pizza pizza = new Pizza(1, "Peperonni", 15, SizeApp.MEDIANO, ingredientesPizza);
        Pizza pizza2 = new Pizza(2, "Peperonni", 15, SizeApp.MEDIANO, ingredientesPizza);

        List<LineaPedido> listaLineaPedido = new ArrayList<LineaPedido>();

        listaLineaPedido.add(new LineaPedido(10, pizza));
        listaLineaPedido.add(new LineaPedido(10, pizza2));

        Pedido pedido = new Pedido(
                new Date(),
                EstadoPedido.ENTREGADO,
                listaLineaPedido,
                new Cliente(
                        "12345678A",
                        "Nombre del Cliente",
                        "Dirección del Cliente",
                        "123456789",
                        "cliente@example.com",
                        "password123",
                        false));

        jdbcPedidoDAO.save(2, pedido);

    }

    @Test
    void testgetAllPedidoByEstado() throws ClassNotFoundException, SQLException {
        List<Pedido> listaPedido = jdbcPedidoDAO.getAllPedidoByEstado(EstadoPedido.ENTREGADO);
        assertNotEquals(0, listaPedido.size());
    }

    @Test
    void testgetAllPedidoByIdCliente() throws ClassNotFoundException, SQLException {
        List<Pedido> listaPedido = jdbcPedidoDAO.getAllPedidoByIdCliente(1);
        assertEquals(0, listaPedido.size());
    }
}
