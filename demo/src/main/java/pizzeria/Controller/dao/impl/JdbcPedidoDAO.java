package pizzeria.Controller.dao.impl;

import static pizzeria.utils.DatabaseConfig.DELETE_PEDIDO;
import static pizzeria.utils.DatabaseConfig.INSERT_PEDIDO;
import static pizzeria.utils.DatabaseConfig.SELECT_PEDIDO_ESTADO;
import static pizzeria.utils.DatabaseConfig.SELECT_PEDIDO_ID;
import static pizzeria.utils.DatabaseConfig.UPDATE_PEDIDO_ESTADO;
import static pizzeria.utils.DatabaseConfig.UPDATE_PEDIDO_ESTADO_AND_PAGO;

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
import pizzeria.Modelo.Pagable;
import pizzeria.Modelo.Pedido;
import pizzeria.Modelo.Producto;


public class JdbcPedidoDAO implements InnerPedido {

    private final JdbcLineaPedido jdbcLineaPedido = new JdbcLineaPedido();

    @Override
    public void save(int idCliente, Pedido pedido) throws ClassNotFoundException, SQLException {
        try (Connection con = new Conexion().getConexion()) {
            con.setAutoCommit(false); 
            try {
                int idPedido = insertarPedido(con, idCliente, pedido);


                for (LineaPedido lineaPedido : pedido.getListaLineaPedidos()) {
                    jdbcLineaPedido.save(con, lineaPedido.getProducto().getId(), idPedido, lineaPedido.getCantidad());
                }
            } catch (SQLException e) {
                con.rollback(); 
                throw new RuntimeException("Error al guardar el pedido: " + e.getMessage(), e);
            }
        }
    }

    private int insertarPedido(Connection con, int idCliente, Pedido pedido) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_PEDIDO,
                Statement.RETURN_GENERATED_KEYS)) {
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

    @Override
    public List<Pedido> getAllPedidoByEstado(EstadoPedido estado) throws ClassNotFoundException, SQLException {
        return getAllPedidos(SELECT_PEDIDO_ESTADO, ps -> ps.setString(1, estado.getValue()));
    }

    @Override
    public List<Pedido> getAllPedidoByIdCliente(int idCliente) throws ClassNotFoundException, SQLException {
        return getAllPedidos(SELECT_PEDIDO_ID, ps -> ps.setInt(1, idCliente));
    }

    private List<Pedido> getAllPedidos(String query, PreparedStatementSetter setter)
            throws ClassNotFoundException, SQLException {
        List<Pedido> listaPedidos = new ArrayList<>();
        try (Connection con = new Conexion().getConexion();
                PreparedStatement preparedStatement = con.prepareStatement(query)) {

            setter.setValues(preparedStatement);

            try (ResultSet resultado = preparedStatement.executeQuery()) {
                while (resultado.next()) {
                    listaPedidos.add(buildPedidoFromResultSet(con, resultado));
                }
            }
        }
        return listaPedidos;
    }

    private Pedido buildPedidoFromResultSet(Connection con, ResultSet resultado)
            throws SQLException, ClassNotFoundException {
        int id = resultado.getInt("id");
        Date fecha = resultado.getDate("fecha");
        String estadoPedido = resultado.getString("estadopedido");

        List<LineaPedido> listaLineaPedido = jdbcLineaPedido.getAllLineaPedidonbyID(id);

        return new Pedido(id, fecha, EstadoPedido.valueOf(estadoPedido), listaLineaPedido);
    }

    @Override
    public void updatePedidoEstadoAndPagable(Pedido pedido, Pagable pagable)
            throws ClassNotFoundException, SQLException {
        try (Connection con = new Conexion().getConexion();
                PreparedStatement preparedStatement = con.prepareStatement(UPDATE_PEDIDO_ESTADO_AND_PAGO)) {
            preparedStatement.setString(1, pedido.getEstado().getValue());
            preparedStatement.setInt(2, pagable.pagar());
            preparedStatement.setInt(3, pedido.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updatePedidoEstado(Pedido pedido) throws SQLException, ClassNotFoundException {
        try (Connection con = new Conexion().getConexion();
                PreparedStatement preparedStatement = con.prepareStatement(UPDATE_PEDIDO_ESTADO)) {
            preparedStatement.setString(1, pedido.getEstado().getValue());
            preparedStatement.setInt(2, pedido.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleted(int idPedido) throws ClassNotFoundException, SQLException {
        try (Connection con = new Conexion().getConexion();
                PreparedStatement preparedStatement = con.prepareStatement(DELETE_PEDIDO)) {
            preparedStatement.setInt(1, idPedido);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void addCarrito(Pedido pedido, Producto producto, int cantidad) throws ClassNotFoundException, SQLException {
        try (Connection con = new Conexion().getConexion()) {
            jdbcLineaPedido.save(con, producto.getId(), pedido.getId(), cantidad);
        }
    }

    @FunctionalInterface
    private interface PreparedStatementSetter {
        void setValues(PreparedStatement ps) throws SQLException;
    }
}
