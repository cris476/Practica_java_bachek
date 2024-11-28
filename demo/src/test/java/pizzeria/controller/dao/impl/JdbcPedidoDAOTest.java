package pizzeria.controller.dao.impl;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import pizzeria.Controller.dao.impl.JdbcPedidoDAO;
import pizzeria.Modelo.Cliente;
import pizzeria.Modelo.EstadoPedido;
import pizzeria.Modelo.Pedido;

public class JdbcPedidoDAOTest {

    JdbcPedidoDAO jdbcPedidoDAO = new JdbcPedidoDAO();

    @Test
    void testSave() throws ClassNotFoundException, SQLException {

        Pedido pedido = new Pedido(
                new Date(),
                EstadoPedido.PENDIENTE,
                new ArrayList<>(),
                new Cliente(
                        "12345678A",
                        "Nombre del Cliente",
                        "Dirección del Cliente",
                        "123456789",
                        "cliente@example.com",
                        "password123",
                        false));

        int numero = jdbcPedidoDAO.save(1, pedido);

        assertNotEquals(0, numero);

    }
}
