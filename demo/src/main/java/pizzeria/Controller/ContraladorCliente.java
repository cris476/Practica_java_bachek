package pizzeria.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBException;

import pizzeria.Controller.dao.InnerClienteDAO;
import pizzeria.Controller.dao.impl.JdbcClienteDAO;
import pizzeria.Exceptions.ExceptionFoundCliente;
import pizzeria.Modelo.Cliente;
import pizzeria.Modelo.Clientes;
import pizzeria.Modelo.GestionarFicheros;
import pizzeria.Modelo.Ingredientes;
import pizzeria.Modelo.Producto;

// import pizzeria.Modelo.Classes.Clientes.ºCliente;
// import pizzeria.Modelo.Classes.Productos.Producto;

public class ContraladorCliente {

  InnerClienteDAO jdbcClienteDAO = new JdbcClienteDAO();

  public void registrarCliente(Cliente cliente) throws SQLException, ClassNotFoundException, ExceptionFoundCliente {
    Cliente clienteEncontrado = jdbcClienteDAO.finClienteByEmail(cliente.getEmail());
    if (clienteEncontrado != null)
      throw new ExceptionFoundCliente("Cliente ya existe");
    jdbcClienteDAO.save(cliente);
  }

  public Cliente encontrarCliente(String email) throws ClassNotFoundException, SQLException {
    return jdbcClienteDAO.finClienteByEmail(email);
  }

  public Cliente loginCliente(String nombre, String password) throws ClassNotFoundException, SQLException {
    return jdbcClienteDAO.login(password, nombre);
  }

  public List<Cliente> todosClientes() throws ClassNotFoundException, SQLException {
    return jdbcClienteDAO.getAllCliente();
  }

  public void actualizarCliente(Cliente cliente) throws ClassNotFoundException, SQLException {
    jdbcClienteDAO.update(cliente);
  }

  public void eliminarCliente(int idCliente) throws ClassNotFoundException, SQLException {
    jdbcClienteDAO.delete(idCliente);
  }

}
