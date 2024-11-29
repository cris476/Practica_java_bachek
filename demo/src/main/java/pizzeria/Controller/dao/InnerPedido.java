package pizzeria.Controller.dao;

import java.sql.SQLException;
import java.util.List;

import pizzeria.Modelo.EstadoPedido;
import pizzeria.Modelo.LineaPedido;
import pizzeria.Modelo.Pedido;

public interface InnerPedido {

    public void save(int idCliente, Pedido pedido) throws ClassNotFoundException, SQLException;

    public List<Pedido> getAllPedidoByEstado(EstadoPedido estado) throws ClassNotFoundException, SQLException;;

}
