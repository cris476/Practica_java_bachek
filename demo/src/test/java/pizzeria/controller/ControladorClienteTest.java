package pizzeria.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static pizzeria.utils.DatabaseConfig.CREATE_ALL_TABLES;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pizzeria.Controller.ContraladorCliente;
import pizzeria.Exceptions.ExceptionFoundCliente;
import pizzeria.Modelo.Cliente;
import pizzeria.Modelo.Conexion;

public class ControladorClienteTest {

    ContraladorCliente contraladorCliente;

    @BeforeEach
    void setUp() throws ClassNotFoundException, SQLException {
        CREATE_ALL_TABLES();
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
    void testEncontrarCLiente() throws ClassNotFoundException, SQLException, ExceptionFoundCliente {

        Cliente cliente = new Cliente(
                "2131212", // DNI
                "Juan Pérez300", // Nombre
                "Calle Falsa 123", // Dirección
                "987654321", // Teléfono
                "juansolo@g3dwqq32l.com", // Email
                "password12", // Contraseña
                false // Admin (false = no es administrador)
        );

        contraladorCliente.registrarCliente(cliente);

        Cliente clienteEncontrado = contraladorCliente.encontrarCliente("juansolo@g3dwqq32l.com");
        assertNotEquals(cliente, null);
    }

    @Test
    void testLoginCliente() throws ClassNotFoundException, SQLException, ExceptionFoundCliente {

        Cliente cliente = new Cliente(
                "2131234212", // DNI
                "Juan Pére4120", // Nombre
                "Calle Falsa 123", // Dirección
                "987654321", // Teléfono
                "juansolo@g3131.com", // Email
                "password412", // Contraseña
                false // Admin (false = no es administrador)
        );

        contraladorCliente.registrarCliente(cliente);

        Cliente clienteLogin = contraladorCliente.loginCliente("Juan Pére4120", "password412");
        assertNotEquals(clienteLogin, null);
    }

    @Test
    void testGetAllCliente() throws ClassNotFoundException, SQLException {
        List<Cliente> listadoCliente = null;
        listadoCliente = contraladorCliente.todosClientes();
        assertNotEquals(listadoCliente, null);
    }

    @Test
    void testGetDeleteCliente() throws ClassNotFoundException, SQLException, ExceptionFoundCliente {

        Cliente cliente = new Cliente(
                "214341214212", // DNI
                "Juan Pére4412", // Nombre
                "Calle Falsa 123", // Dirección
                "987654321", // Teléfono
                "juansolo@g35423241.com", // Email
                "password765", // Contraseña
                false // Admin (false = no es administrador)
        );

        contraladorCliente.registrarCliente(cliente);

        Cliente clienteLogin = contraladorCliente.loginCliente("Juan Pére4412", "password765");

        contraladorCliente.eliminarCliente(clienteLogin.getId());
    }

    @Test
    void testActualizarCliente() throws ClassNotFoundException, SQLException, ExceptionFoundCliente {

        Cliente cliente = new Cliente(
                "21412123424212", // DNI
                "Juan Pér23412", // Nombre
                "Calle Falsa 123", // Dirección
                "987654321", // Teléfono
                "juansolo@g356553.com", // Email
                "password7875", // Contraseña
                false // Admin (false = no es administrador)
        );

        contraladorCliente.registrarCliente(cliente);

        Cliente clienteLogin = contraladorCliente.loginCliente("Juan Pér23412", "password7875");

        clienteLogin.setAdmin(true);
        contraladorCliente.actualizarCliente(cliente);
    }

}
