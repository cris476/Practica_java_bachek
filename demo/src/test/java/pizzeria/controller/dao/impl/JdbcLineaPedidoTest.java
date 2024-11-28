package pizzeria.controller.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import pizzeria.Controller.dao.impl.JdbcLineaPedido;
import pizzeria.Modelo.Conexion;

public class JdbcLineaPedidoTest {

    JdbcLineaPedido jdbcLineaPedido = new JdbcLineaPedido();

    @Test
    void testSave() throws ClassNotFoundException, SQLException {
        Connection con = new Conexion().getConexion();
        jdbcLineaPedido.save(con, 1, 1, 2);
    }
}
