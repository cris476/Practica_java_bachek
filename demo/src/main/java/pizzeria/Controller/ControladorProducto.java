package pizzeria.Controller;

import java.sql.SQLException;
import java.util.List;

import pizzeria.Controller.dao.impl.JdbcProductoDAO;
import pizzeria.Modelo.Producto;

public class ControladorProducto {

    JdbcProductoDAO jdbcProductoDAO = new JdbcProductoDAO();

    public void registrarProducto(Producto producto) throws ClassNotFoundException, SQLException {
        jdbcProductoDAO.save(producto);
    }

    public List<Producto> getAllProducts() throws ClassNotFoundException, SQLException {
        return jdbcProductoDAO.getAllProductos();
    }

    public void  saveProduct(Producto producto) throws ClassNotFoundException, SQLException{
            jdbcProductoDAO.save(producto);
    }

}
