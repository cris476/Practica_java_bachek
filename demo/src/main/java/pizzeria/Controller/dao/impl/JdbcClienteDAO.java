package pizzeria.Controller.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pizzeria.Controller.dao.InnerClienteDAO;
import pizzeria.Modelo.Cliente;
import pizzeria.Modelo.Conexion;
import static pizzeria.utils.DatabaseConfig.*;

public class JdbcClienteDAO implements InnerClienteDAO {

    @Override
    public void save(Cliente cliente) throws SQLException, ClassNotFoundException {
        try (Connection con = new Conexion().getConexion();
                Statement statement = con.createStatement();) {
            PreparedStatement preparedStatement = con.prepareStatement(INNER_CLIENTE);
            preparedStatement.setString(1, cliente.getDni());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getDireccion());
            preparedStatement.setString(4, cliente.getEmail());
            preparedStatement.setString(5, cliente.getPassword());
            preparedStatement.setBoolean(6, cliente.getAdmin());
            preparedStatement.setString(7, cliente.getTelefono());
            preparedStatement.executeUpdate();

        }
    }

    @Override
    public Cliente finbyCliente(String email) throws SQLException, ClassNotFoundException {

        Cliente cliente = null;
        try (Connection con = new Conexion().getConexion();
                Statement statement = con.createStatement();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_CLIENTE)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultado = preparedStatement.executeQuery()) {

                if (resultado.next()) {
                    int idCliente = resultado.getInt("id");
                    String dniCliente = resultado.getString("dni");
                    String nombreCliente = resultado.getString("nombre");
                    String dirreccionCliente = resultado.getString("direccion");
                    String emailCliente = resultado.getString("email");
                    String passwordCliente = resultado.getString("password");
                    Boolean adminCliente = resultado.getBoolean("admin");
                    String telefonoCliente = resultado.getString("telefono");
                    cliente = new Cliente(idCliente, dniCliente, nombreCliente, dirreccionCliente,
                            telefonoCliente, passwordCliente, emailCliente, adminCliente);
                }

            }

        }
        return cliente;
    }

    @Override
    public Cliente login(String password, String nombre) throws SQLException, ClassNotFoundException {

        Cliente cliente = null;

        try (Connection con = new Conexion().getConexion();
                Statement statement = con.createStatement();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_LOGIN_CLIENTE)) {
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, password);

            try (ResultSet resultado = preparedStatement.executeQuery()) {

                if (resultado.next()) {
                    int idCliente = resultado.getInt("id");
                    String dniCliente = resultado.getString("dni");
                    String nombreCliente = resultado.getString("nombre");
                    String dirreccionCliente = resultado.getString("direccion");
                    String emailCliente = resultado.getString("email");
                    String passwordCliente = resultado.getString("password");
                    Boolean adminCliente = resultado.getBoolean("admin");
                    String telefonoCliente = resultado.getString("telefono");
                    cliente = new Cliente(idCliente, dniCliente, nombreCliente, dirreccionCliente,
                            telefonoCliente, passwordCliente, emailCliente, adminCliente);
                }

            }

        }
        return cliente;

    }

    @Override
    public void update(String id, Cliente cliente) throws SQLException, ClassNotFoundException {

        try (Connection con = new Conexion().getConexion();
                Statement statement = con.createStatement();
                PreparedStatement preparedStatement = con.prepareStatement(id)) {

        }
    }

    @Override
    public List<Cliente> getAllCliente() throws SQLException, ClassNotFoundException {

        List<Cliente> listadoClientes = new ArrayList<Cliente>();

        try (Connection con = new Conexion().getConexion();
                Statement statement = con.createStatement();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_CLIENTE)) {

            try (ResultSet resultado = preparedStatement.executeQuery()) {

                if (resultado.next()) {
                    int idCliente = resultado.getInt("id");
                    String dniCliente = resultado.getString("dni");
                    String nombreCliente = resultado.getString("nombre");
                    String dirreccionCliente = resultado.getString("direccion");
                    String emailCliente = resultado.getString("email");
                    String passwordCliente = resultado.getString("password");
                    Boolean adminCliente = resultado.getBoolean("admin");
                    String telefonoCliente = resultado.getString("telefono");
                    listadoClientes.add(new Cliente(idCliente, dniCliente, nombreCliente, dirreccionCliente,
                            telefonoCliente, passwordCliente, emailCliente, adminCliente));
                }
            }

        }

        return listadoClientes;
    }

}