package pizzeria.Controller.dao;

import java.sql.Connection;
import java.sql.SQLException;

import pizzeria.Modelo.Ingredientes;

public interface InnerIngredienteDAO {

        public void save(Connection con, int id_producto, Ingredientes ingrediente)
                        throws SQLException, ClassNotFoundException;

}