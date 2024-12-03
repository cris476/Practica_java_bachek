package pizzeria.Controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pizzeria.Controller.dao.impl.JdbcPedidoDAO;
import pizzeria.Modelo.*;

public class ContraladorPedido {

    JdbcPedidoDAO jdbcPedidoDAO = new JdbcPedidoDAO();

    public void savePedido(Pedido pedido) throws ClassNotFoundException, SQLException {
        jdbcPedidoDAO.save(pedido.getCliente().getId(), pedido);
    }

    public void addCarrito(Producto producto, int cantidad, Cliente cliente)
            throws ClassNotFoundException, SQLException {
        Pedido pedidoNuevo = null;
        Pedido PedidoExistente = null;
        List<Pedido> listaPedido = new ArrayList<Pedido>();
        List<LineaPedido> lineaPedidos = new ArrayList<>();

        listaPedido = jdbcPedidoDAO.getAllPedidoByEstado(EstadoPedido.PENDIENTE);

        PedidoExistente = listaPedido.stream()
                .filter(pedido -> pedido.getEstado() == EstadoPedido.PENDIENTE)
                .findFirst()
                .orElse(null);

        if (PedidoExistente == null) {
            lineaPedidos.add(new LineaPedido(cantidad, producto));
            pedidoNuevo = new Pedido(new Date(), EstadoPedido.PENDIENTE, lineaPedidos, cliente);
            savePedido(pedidoNuevo);
        } else {
            jdbcPedidoDAO.addCarrito(PedidoExistente, producto, cantidad);
        }

    }

    public void finalizarPedido(Cliente cliente, Pagable pagable) throws ClassNotFoundException, SQLException {

        List<Pedido> listaPedidos = jdbcPedidoDAO.getAllPedidoByIdCliente(cliente.getId());

        Pedido pedidoPendiente = listaPedidos.stream()
                .filter(pedido -> pedido.getEstado() == EstadoPedido.PENDIENTE)
                .findFirst()
                .orElse(null);
        pedidoPendiente.setEstado(EstadoPedido.ENTREGADO);
        jdbcPedidoDAO.updatePedidoEstadoAndPagable(pedidoPendiente, pagable);

    }

    public void cancelarPedido(Cliente cliente) throws ClassNotFoundException, SQLException {
        List<Pedido> listaPedidos = jdbcPedidoDAO.getAllPedidoByIdCliente(cliente.getId());

        Pedido pedidoPendiente = listaPedidos.stream()
                .filter(pedido -> pedido.getEstado() == EstadoPedido.PENDIENTE)
                .findFirst()
                .orElse(null);
        pedidoPendiente.setEstado(EstadoPedido.CANCELADO);

        jdbcPedidoDAO.updatePedidoEstado(pedidoPendiente);
    }

    public void entregarPedido(Cliente cliente) throws ClassNotFoundException, SQLException {
        List<Pedido> listaPedidos = jdbcPedidoDAO.getAllPedidoByIdCliente(cliente.getId());

        Pedido pedidoPendiente = listaPedidos.stream()
                .filter(pedido -> pedido.getEstado() == EstadoPedido.PENDIENTE)
                .findFirst()
                .orElse(null);
        pedidoPendiente.setEstado(EstadoPedido.ENTREGADO);

        jdbcPedidoDAO.updatePedidoEstado(pedidoPendiente);
    }

}
