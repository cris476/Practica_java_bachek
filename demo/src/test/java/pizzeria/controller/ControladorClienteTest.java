package pizzeria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzeria.Controller.ContraladorCliente;
import pizzeria.Controller.dao.impl.JdbcClienteDAO;
import pizzeria.Exceptions.ExceptionFoundCliente;
import pizzeria.Modelo.Cliente;
import pizzeria.Modelo.Conexion;

public class ControladorClienteTest {

    ContraladorCliente contraladorCliente;

    @BeforeEach
    void setUp() throws ClassNotFoundException, SQLException {
        contraladorCliente = new ContraladorCliente();
    }

    @Test
    void testConexion() throws SQLException, Exception {

        Connection conexion = new Conexion().getConexion();
    }

    @Test
    void testInsertarCliente() throws ClassNotFoundException, SQLException, ExceptionFoundCliente {
        String dni = "48642963N";
        String nombre = "Jhon2";
        String apellido = "Solano Macass";
        String telefono = "681207536e";
        String email = "solanomacascristoferr@gmail.com";
        String password = "123453";
        Boolean admin = false;

        Cliente cliente = new Cliente(dni, nombre, apellido, telefono, email, password, admin);
        contraladorCliente.registrarCliente(cliente);
        assertEquals(true, true);
    }

    @Test
    void testEncontrarCLiente() throws ClassNotFoundException, SQLException {
        Cliente cliente = contraladorCliente.encontrarCliente("solanomacascristofer@gmail.com");
        assertNotEquals(cliente, null);
    }

    @Test
    void testLoginCliente() throws ClassNotFoundException, SQLException {
        Cliente cliente = contraladorCliente.loginCliente("Jhon", "12345");
        assertNotEquals(cliente, null);
    }

    @Test
    void testGetAllCliente() throws ClassNotFoundException, SQLException {
        List<Cliente> listadoCliente = null;
        listadoCliente = contraladorCliente.todosClientes();
        assertNotEquals(listadoCliente, null);
    }

}
