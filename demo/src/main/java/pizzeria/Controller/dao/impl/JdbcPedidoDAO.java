package pizzeria.Controller.dao.impl;

import static pizzeria.utils.DatabaseConfig.INSERT_PEDIDO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pizzeria.Controller.dao.InnerPedido;
import pizzeria.Modelo.Conexion;
import pizzeria.Modelo.Pedido;

public class JdbcPedidoDAO implements InnerPedido {

    @Override
    public int save(int idCliente, Pedido pedido) throws ClassNotFoundException, SQLException {
        try (Connection con = new Conexion().getConexion();
             PreparedStatement preparedStatement = con.prepareStatement(INSERT_PEDIDO, Statement.RETURN_GENERATED_KEYS)) {

           
            java.sql.Date sqlDate = new java.sql.Date(pedido.getFecha().getTime());

       
            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setInt(2, idCliente);
            preparedStatement.setString(3, pedido.getEstado().getValue());

           
            preparedStatement.executeUpdate();

         
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para el pedido.");
                }
            }
        }
    }
}
