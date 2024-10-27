package pizzeria.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

import pizzeria.Modelo.Pagable;
import pizzeria.Modelo.LineaPedido;
import pizzeria.Modelo.Ingredientes;
import pizzeria.Modelo.Pedido;
import pizzeria.Modelo.*;
import pizzeria.Modelo.Cliente;
import pizzeria.Modelo.Bebida;
import pizzeria.Modelo.Pizza;
import pizzeria.Modelo.Producto;
import pizzeria.Modelo.EstadoPedido;
import pizzeria.Modelo.SizeApp;

public class ContraladorPedido {

      static List<LineaPedido> listaLineaPedidos1 = new ArrayList<>(
                  Arrays.asList(
                              new LineaPedido(2, new Pizza("Pizza barbacoa", 0, SizeApp.GRANDE, new ArrayList<>(
                                          Arrays.asList(
                                                      new Ingredientes("Carne", new ArrayList<>(
                                                                  Arrays.asList("Pollo", "Ternera"))),
                                                      new Ingredientes("Salsa barbacoa", new ArrayList<>(
                                                                  Arrays.asList("Tomate", "Especias"))),
                                                      new Ingredientes("Queso", new ArrayList<>(
                                                                  Arrays.asList("Leche"))))))),
                              new LineaPedido(1, new Bebida("Cocola", 3, SizeApp.PEQUENO))));

      static List<LineaPedido> listaLineaPedidos2 = new ArrayList<>(
                  Arrays.asList(

                              new LineaPedido(1, new Pizza("Pizza Pepperoni", 0, SizeApp.PEQUENO, new ArrayList<>(
                                          Arrays.asList(
                                                      new Ingredientes("Pepperoni", new ArrayList<>(
                                                                  Arrays.asList("Carne de cerdo", "Especias"))),
                                                      new Ingredientes("Queso mozzarella", new ArrayList<>(
                                                                  Arrays.asList("Leche"))),
                                                      new Ingredientes("Salsa de tomate", new ArrayList<>(
                                                                  Arrays.asList("Tomate", "Ajo",
                                                                              "Aceite de oliva"))))))),

                              new LineaPedido(2, new Bebida("Sprite", 2.5, SizeApp.PEQUENO)))

      );

      static List<Pedido> pedidos = new ArrayList<>(
                  Arrays.asList(
                              new Pedido(new Date(), 25.5f, EstadoPedido.PENDIENTE, listaLineaPedidos1 , null),
                              new Pedido(new Date(), 30.0f, EstadoPedido.PENDIENTE, listaLineaPedidos2, null)));

      static Pedido pedidoActual = null;
      static Cliente clienteActual = null;

      public ContraladorPedido(Cliente cliente) {
            this.clienteActual = cliente;
      }

      public void cancelarPedido()  throws Exception  {
            if (this.pedidoActual != null && this.clienteActual != null) {
                  this.pedidoActual.setEstado(EstadoPedido.CANCELADO);
                
                  System.out.println("Pedido Cancelado");
            } else {
                  throw new Exception("El pedido actual no existe o no estas logeado");
            }
                
            
      }

      public void entregarPedido() throws Exception {
            if (this.pedidoActual != null && this.clienteActual != null)   {
                  this.pedidoActual.setEstado(EstadoPedido.ENTREGADO);
                  System.out.println("Pedido entregado");
            } else {
                  throw new Exception("no hay pedido o no te has logeado");
            }
      }

      public void finalizadoPedido(Pagable pagable) throws Exception {
            if (this.pedidoActual != null && this.clienteActual != null) {
                  this.pedidoActual.setEstado(EstadoPedido.ENTREGADO);
                  pagable.pagar(this.pedidoActual.totalPrecio());
            } else {
                  throw new Exception("El pedido actual no existe o no estas logeado");
            }

      }

      public void v() {
            System.out.println(this.pedidoActual == null);
      }

      public void registrarLineaPedido(int cantidad, Producto producto , Cliente cliente)  throws Exception{
            boolean lineaPedidoLocalizado = true;

            if (this.clienteActual != null) {

                  if (this.pedidoActual == null) {
                        List<LineaPedido> lineaCreacion = new ArrayList<>(
                                    Arrays.asList(new LineaPedido(cantidad, producto)));
                        Pedido pedidoCreado = new Pedido(new Date(), 0, EstadoPedido.PENDIENTE, lineaCreacion, cliente );
                        this.pedidos.add(pedidoCreado);
                        this.pedidoActual = pedidoCreado;

                        System.out.println("Linea  de Pedido Registrado");
                  } else {
                        for (LineaPedido lineaPedido : this.pedidoActual.getListaLineaPedidos()) {
                              if (lineaPedido.getProducto().getNombre() == producto.getNombre()) {
                                    lineaPedido.IncremetoCantidad(cantidad);
                                    System.out.println(lineaPedido.getCantidad());
                                    lineaPedidoLocalizado = false;
                              }
                        }
                        if (lineaPedidoLocalizado) {
                              this.pedidoActual.AñadirLineaPedido(new LineaPedido(cantidad, producto));
                              System.out.println("Linea  de Pedido Registrado 2");

                        }
                  }

            }
            else{
                  throw new Exception("El cliente  no esta logeado");
            }

      }

    

      public List<Pedido> getPedidos() {
            return pedidos;
      }

      public static Pedido getPedidoActual() {
            return pedidoActual;
      }

      public static Cliente getClienteActual() {
            return clienteActual;
      }

}
