package pizzeria.Modelo;

import java.util.Date;
import java.util.List;

public class Pedido {

    private int id;
    private Date fecha;
    private EstadoPedido estado;
    private List<LineaPedido> listaLineaPedidos;
    private Cliente cliente;

    public Pedido(int id, Date fecha, EstadoPedido estado, List<LineaPedido> listaLineaPedidos,
            Cliente cliente) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.listaLineaPedidos = listaLineaPedidos;
        this.cliente = cliente;
    }

    public Pedido(int id, Date fecha, EstadoPedido estado, List<LineaPedido> listaLineaPedidos) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.listaLineaPedidos = listaLineaPedidos;

    }

    public Pedido(Date fecha, EstadoPedido estado, List<LineaPedido> listaLineaPedidos,
            Cliente cliente) {
        this.fecha = fecha;
        this.estado = estado;
        this.listaLineaPedidos = listaLineaPedidos;
        this.cliente = cliente;
    }

    public boolean AñadirLineaPedido(LineaPedido lineaPedido) {
        this.listaLineaPedidos.add(lineaPedido);
        return true;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public List<LineaPedido> getListaLineaPedidos() {
        return listaLineaPedidos;
    }

    public void setListaLineaPedidos(List<LineaPedido> listaLineaPedidos) {
        this.listaLineaPedidos = listaLineaPedidos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
