package pizzeria.Controller.dao;

import java.sql.SQLException;
import java.util.List;

import pizzeria.Modelo.Cliente;

public interface InnerClienteDAO {

       public void save(Cliente cliente) throws SQLException, ClassNotFoundException;

       public Cliente finbyCliente(String dni) throws SQLException, ClassNotFoundException;

       public Cliente login(String password, String nombre) throws SQLException, ClassNotFoundException;

       

       public void update(String id, Cliente cliente) throws SQLException, ClassNotFoundException;

       public List<Cliente> getAllCliente() throws SQLException, ClassNotFoundException;

}
