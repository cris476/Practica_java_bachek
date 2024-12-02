package pizzeria.Controller.dao.impl;

import static pizzeria.utils.DatabaseConfig.INSERT_PEDIDO;
import static pizzeria.utils.DatabaseConfig.SELECT_PEDIDO_ESTADO;
import static pizzeria.utils.DatabaseConfig.SELECT_PEDIDO_ID;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pizzeria.Controller.dao.InnerPedido;
import pizzeria.Modelo.Conexion;
import pizzeria.Modelo.EstadoPedido;
import pizzeria.Modelo.LineaPedido;
import pizzeria.Modelo.Pedido;
import pizzeria.Modelo.Producto;
import pizzeria.Controller.dao.impl.JdbcLineaPedido;

public class JdbcPedidoDAO implements InnerPedido {

    JdbcLineaPedido jdbcLineaPedido = new JdbcLineaPedido();

    @Override
    public void save(int idCliente, Pedido pedido) throws ClassNotFoundException, SQLException {
        try (Connection con = new Conexion().getConexion();
                PreparedStatement preparedStatement = con.prepareStatement(INSERT_PEDIDO,
                        Statement.RETURN_GENERATED_KEYS)) {

            java.sql.Date sqlDate = new java.sql.Date(pedido.getFecha().getTime());

            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setInt(2, idCliente);
            preparedStatement.setString(3, pedido.getEstado().getValue());

            preparedStatement.executeUpdate();

            int idPedido = 0;

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idPedido = generatedKeys.getInt(1);

                    for (LineaPedido lineaPedido : pedido.getListaLineaPedidos()) {
                        jdbcLineaPedido.save(con, lineaPedido.getProducto().getId(), idPedido,
                                lineaPedido.getCantidad());
                    }

                } else {
                    throw new SQLException("No se pudo obtener el ID generado para el pedido.");
                }
            }
        }
    }

    @Override
    public List<Pedido> getAllPedidoByEstado(EstadoPedido estado) throws ClassNotFoundException, SQLException {
        List<Pedido> listaLineaPedidos = new ArrayList<Pedido>();

        try (Connection con = new Conexion().getConexion();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_PEDIDO_ESTADO);) {

            preparedStatement.setString(1, estado.getValue());

            try (ResultSet resultado = preparedStatement.executeQuery()) {

                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    Date fecha = resultado.getDate("fecha");
                    String estadoPedido = resultado.getString("estadopedido");
                    int idProducto = resultado.getInt("id_producto");
                    List<LineaPedido> listaLineaPedido = jdbcLineaPedido.getAllLineaPedidonbyID(id);

                    Pedido pedido = new Pedido(id, fecha, EstadoPedido.valueOf(estadoPedido), listaLineaPedido);
                    listaLineaPedidos.add(pedido);
                }
            }

        }

        return listaLineaPedidos;
    }

    public List<Pedido> getAllPedidoByIdCliente(int idCliente) throws ClassNotFoundException, SQLException {
        List<Pedido> listaLineaPedidos = new ArrayList<Pedido>();

        try (Connection con = new Conexion().getConexion();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_PEDIDO_ID)) {

            preparedStatement.setInt(1, idCliente);

            try (ResultSet resultado = preparedStatement.executeQuery()) {

                while (resultado.next()) {
                    int id = resultado.getInt("id");
                    Date fecha = resultado.getDate("fecha");
                    String estadoPedido = resultado.getString("estadopedido");
                    int idProducto = resultado.getInt("id_producto");
                    List<LineaPedido> listaLineaPedido = jdbcLineaPedido.getAllLineaPedidonbyID(id);

                    Pedido pedido = new Pedido(id, fecha, EstadoPedido.valueOf(estadoPedido), listaLineaPedido);
                    listaLineaPedidos.add(pedido);
                }
            }

        }

        return listaLineaPedidos;
    }

}
