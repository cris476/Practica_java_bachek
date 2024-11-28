package pizzeria.Controller.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static pizzeria.utils.DatabaseConfig.*;
import pizzeria.Controller.dao.InnerLineaPedidoDAO;
import pizzeria.Modelo.Conexion;
import pizzeria.Modelo.LineaPedido;

public class JdbcLineaPedido implements InnerLineaPedidoDAO {

    @Override
    public void save(Connection con, int idProducto, int idPedido, int cantidad)
            throws SQLException, ClassNotFoundException {

        try (
                PreparedStatement preparedStatement = con.prepareStatement(INSERT_LINEAPEDIDO);) {
            preparedStatement.setInt(1, cantidad);
            preparedStatement.setInt(2, idProducto);
            preparedStatement.setInt(3, idPedido);

            preparedStatement.executeUpdate();

        }

    }

    @Override
    public List<LineaPedido> getAllLineaPedido(int idPedido) throws SQLException, ClassNotFoundException {

        List<LineaPedido> listaLineaPedido = new ArrayList<LineaPedido>();

        try (Connection con = new Conexion().getConexion();
                PreparedStatement preparedStatement = con.prepareStatement(INSERT_LINEAPEDIDO);) {
        

            preparedStatement.executeUpdate();

        }

        return listaLineaPedido;
    }

}
