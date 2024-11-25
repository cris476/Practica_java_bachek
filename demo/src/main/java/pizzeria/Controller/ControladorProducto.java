package pizzeria.Controller;

import java.sql.SQLException;

import pizzeria.Controller.dao.impl.JdbcProductoDAO;
import pizzeria.Modelo.Producto;

public class ControladorProducto {

    JdbcProductoDAO jdbcProductoDAO = new JdbcProductoDAO();

    public void registrarProducto(Producto producto) throws ClassNotFoundException, SQLException {
        jdbcProductoDAO.save(producto);
    }

}
