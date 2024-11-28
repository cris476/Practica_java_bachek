package pizzeria.Controller.dao;

import java.sql.SQLException;
import java.util.List;

import pizzeria.Modelo.LineaPedido;
import pizzeria.Modelo.Pedido;

public interface InnerPedido {

    public int save(int idCliente, Pedido pedido) throws ClassNotFoundException, SQLException;


    

}
