package pizzeria.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

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

        if (listaPedido.size() < 0) {
            lineaPedidos.add(new LineaPedido(cantidad, producto));
            pedidoNuevo = new Pedido(new Date(), EstadoPedido.PENDIENTE, lineaPedidos, cliente);
            savePedido(pedidoNuevo);
        } else {
            PedidoExistente = listaPedido.get(0);
            jdbcPedidoDAO.addCarrito(PedidoExistente, cantidad);
        }

    }

    public void finalizarPedido(Cliente cliente, Pagable pagable) throws ClassNotFoundException, SQLException {

        List<Pedido> listaPedidos = jdbcPedidoDAO.getAllPedidoByIdCliente(cliente.getId());

        Pedido pedidoPendiente = listaPedidos.stream()
                .filter(pedido -> pedido.getEstado() == EstadoPedido.PENDIENTE)
                .findFirst()
                .orElse(null);

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
