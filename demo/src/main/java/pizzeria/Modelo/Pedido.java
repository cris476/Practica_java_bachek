package pizzeria.Modelo;

import java.util.Date;
import java.util.List;

public class Pedido {

    private int id;
    private Date fecha;
    private EstadoPedido estado;
    private List<LineaPedido> listaLineaPedidos;
    private Cliente cliente;
    private Pagable pagable;

    public Pedido(int id, Date fecha, EstadoPedido estado, List<LineaPedido> listaLineaPedidos,
            Cliente cliente, Pagable pagable) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.listaLineaPedidos = listaLineaPedidos;
        this.cliente = cliente;
        this.pagable = pagable;
    }

    public Pedido(int id, Date fecha, EstadoPedido estado, List<LineaPedido> listaLineaPedidos, Pagable pagable) {
        this.id = id;
        this.fecha = fecha;
        this.estado = estado;
        this.listaLineaPedidos = listaLineaPedidos;

    }

    public Pedido(Date fecha, EstadoPedido estado, List<LineaPedido> listaLineaPedidos,
            Cliente cliente, Pagable pagable) {
        this.fecha = fecha;
        this.estado = estado;
        this.listaLineaPedidos = listaLineaPedidos;
        this.cliente = cliente;
        this.pagable = pagable;
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

    public Pagable getPagable() {
        return pagable;
    }

    public void setPagable(Pagable pagable) {
        this.pagable = pagable;
    }

}
