package pizzeria.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBException;

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

  JdbcClienteDAO jdbcClienteDAO = new JdbcClienteDAO();

  public void registrarCliente(Cliente cliente) throws SQLException, ClassNotFoundException, ExceptionFoundCliente {
    Cliente clienteEncontrado = jdbcClienteDAO.finbyCliente(cliente.getEmail());
    if (clienteEncontrado != null)
      throw new ExceptionFoundCliente("Cliente ya existe");
    jdbcClienteDAO.save(cliente);
  }

  public Cliente encontrarCliente(String email) throws ClassNotFoundException, SQLException {
    return jdbcClienteDAO.finbyCliente(email);
  }

  public Cliente loginCliente(String nombre, String password) throws ClassNotFoundException, SQLException {
    return jdbcClienteDAO.login(password, nombre);
  }

  public List<Cliente> todosClientes() throws ClassNotFoundException, SQLException {
    return jdbcClienteDAO.getAllCliente();
  }

  // ContraladorPedido controladorPedido;
  // GestionarFicheros gestionarFicheros = new GestionarFicheros();
  // static int contador = 0;

  // private List<Cliente> clientes = new ArrayL<ist<>(
  // Arrays.asList(
  // new Cliente("87654321B", "Ana García", "Avenida Siempre Viva 456",
  // "987654321", "ana@example.com",
  // "password456", null),
  // new Cliente("11223344C", "Luis Fernández", "Plaza Mayor 789", "654321987",
  // "luis@example.com",
  // "password789", null)));

  // static private Cliente clienteActual;

  // public void registrarCliente(
  // int id,
  // String dni,
  // String nombre,
  // String direccion,
  // String telefono,
  // String email,
  // String password) {

  // boolean registrado = clientes.stream().anyMatch(clie -> {
  // return clie.getDni().equals(dni);
  // });
  // if (!registrado) {
  // System.out.println("Cliente registrado");
  // this.clientes.add(new Cliente(dni, nombre, direccion, telefono, email,
  // password, null));
  // } else {
  // System.out.println("Cliente ya existe");
  // }

  // }

  // public void loginCliente(String dni, String gmail) {

  // Boolean logeado = true;

  // for (Cliente cliente : clientes) {
  // if (cliente.getDni().equals(dni) && cliente.getEmail().equals(gmail)) {
  // System.out.println("Usuario logeado");
  // logeado = false;
  // ContraladorCliente.clienteActual = cliente;
  // this.controladorPedido = new ContraladorPedido(this.clienteActual);
  // }
  // }

  // if (logeado) {
  // System.out.println("usario no encontrado");
  // }

  // }

  // public void agragarLineaPedido(int cantidad, Producto producto) throws
  // Exception{
  // if (this.clienteActual != null) {
  // this.controladorPedido.registrarLineaPedido(cantidad, producto,
  // clienteActual);
  // }
  // }

  // public List<Cliente> importarClientesTxT(String archivo) throws Exception{
  // return gestionarFicheros.importarClientesTxT(archivo);
  // }

  // public void exportarClientesXML(List<Cliente> clientes, String nombreFichero)
  // throws JAXBException {
  // gestionarFicheros.exportarClientesXML(clientes, nombreFichero);
  // }

  // public List<Cliente> importarClienteXML(String fichero) throws JAXBException
  // {
  // return gestionarFicheros.importarClienteXML(fichero);
  // }

  // public static Cliente getClienteActual() {
  // return clienteActual;
  // }

}
