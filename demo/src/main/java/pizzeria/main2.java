package pizzeria;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pizzeria.Controller.ContraladorCliente;
import pizzeria.Controller.ContraladorPedido;
import pizzeria.Controller.ControladorIngredientes;
import pizzeria.Modelo.Bebida;
import pizzeria.Modelo.Cliente;
import pizzeria.Modelo.Ingredientes;
import pizzeria.Modelo.PagarEfectivo;
import pizzeria.Modelo.Pasta;
import pizzeria.Modelo.Pizza;
import pizzeria.Modelo.SizeApp;

public class main2 {
 
        
        public static void main(String[] args) {

                // ContraladorCliente controladorCliente = new ContraladorCliente();
                // ContraladorPedido controladorpedido = new ContraladorPedido(null);
                // ControladorIngredientes controladorIngrediente = new ControladorIngredientes();

                // controladorCliente.registrarCliente(14, "12345678A", "Juan Pérez", "Calle Falsa 123", "123456789",
                //                 "juan@example.com", "passwordSeguro123");

                // System.out.println("-------------------------------------------------------");
                // System.out.println("Logincliente");
                // controladorCliente.loginCliente("12345678A", "juan@example.com");

                // System.out.println("-------------------------------------------------------");
                // System.out.println("Registra linea pedido");

                // try {
                //         controladorCliente.agragarLineaPedido(2, new Pizza("Pizza", 10, SizeApp.GRANDE, new ArrayList<>(
                //                         Arrays.asList(
                //                                         new Ingredientes("Tomates", new ArrayList<>(
                //                                                         Arrays.asList("Nitrigeno")))

                //                         ))));
                //         controladorCliente.agragarLineaPedido(5, new Pizza("Pizza", 10, SizeApp.GRANDE, new ArrayList<>(
                //                         Arrays.asList(
                //                                         new Ingredientes("Tomates", new ArrayList<>(
                //                                                         Arrays.asList("Nitrigeno")))

                //                         ))));

                //         controladorCliente.agragarLineaPedido(5,
                //                         new Pizza("Pizza lustro", 3, SizeApp.GRANDE, new ArrayList<>(
                //                                         Arrays.asList(
                //                                                         new Ingredientes("Tomates", new ArrayList<>(
                //                                                                         Arrays.asList("Nitrigeno")))

                //                                         ))));
                //         controladorCliente.agragarLineaPedido(2, new Bebida("Cocacola", 4, SizeApp.GRANDE));

                //         controladorCliente.agragarLineaPedido(4, new Pasta("Pasta", 3, new ArrayList<>(
                //                         Arrays.asList(
                //                                         new Ingredientes("Harina", new ArrayList<>(
                //                                                         Arrays.asList("Gluten"))),
                //                                         new Ingredientes("Salsa de tomate", new ArrayList<>(
                //                                                         Arrays.asList("Tomate", "Albahaca"))),
                //                                         new Ingredientes("Queso parmesano", new ArrayList<>(
                //                                                         Arrays.asList("Leche")))))));
                // } catch (Exception e) {
                //         // TODO Auto-generated catch block
                //         e.printStackTrace();
                // }

                // System.out.println("-------------------------------------------------------");
                // System.out.println("Lista de productos en el pedido");
                // controladorpedido.getPedidoActual().getListaLineaPedidos().forEach(x -> System.out.println(x));

                // System.out.println("-------------------------------------------------------");
                // System.out.println("Finalizar Pedido etapas");

                // try {
                //         controladorpedido.cancelarPedido();
                // } catch (Exception e) {
                //         e.printStackTrace();
                // }
                // try {
                //         controladorpedido.finalizadoPedido(new PagarEfectivo());
                // } catch (Exception e) {

                //         e.printStackTrace();
                // }
                // try {
                //         controladorpedido.entregarPedido();
                // } catch (Exception e) {
                //         e.printStackTrace();
                // }
                // System.out.println("-------------------------------------------------------");
                // System.out.println("Importacion propia desde un TXT nuevo");
                // try {
                //         List<Cliente> clientesTxt = controladorCliente.importarClientesTxT("admin.txt");
                //         clientesTxt.forEach(p -> System.out.println(p));
                // } catch (Exception e) {
                //         System.out.println(e.getMessage());
                //         e.printStackTrace();
                // }
                // System.out.println("-------------------------------------------------------");
                // System.out.println("Importacion de XML");

                // try {
                //         List<Cliente> clientesTxt = controladorCliente.importarClienteXML("clientess.xml");
                //         clientesTxt.forEach(p -> System.out.println(p));
                // } catch (Exception e) {
                //         System.out.println(e.getMessage());
                //         e.printStackTrace();
                // }

                // System.out.println("-------------------------------------------------------");
                // System.out.println("Exportar de XML");

                // try {
                //         List<Cliente> listaClientes = new ArrayList<>();

                //         listaClientes.add(new Cliente(1, "11102496E", "Javier Uribe", "C/Mayor 10", "+34111929992",
                //                         "j.uribemontoya@edu.gva.es", "password123", true));
                //         listaClientes.add(new Cliente(2, "12345678Z", "Ana Gómez", "C/Sol 20", "+34678901234",
                //                         "ana.gomez@example.com", "password456", true));
                //         listaClientes.add(new Cliente(3, "87654321X", "Carlos Pérez", "C/ Luna 5", "+34612345678",
                //                         "carlos.perez@example.com", "password789", false));

                //         controladorCliente.exportarClientesXML(listaClientes, "clientess.xml");

                // } catch (Exception e) {
                //         System.out.println(e.getMessage());
                //         e.printStackTrace();
                // }

                // try {
                //         System.out.println("-------------------------------------------------------");
                //         System.out.println("Exportar de CSV");

                //         List<Ingredientes> ingredientes = new ArrayList<>();

                //         ingredientes.add(new Ingredientes("Queso mozzarella", Arrays.asList("Lactosa", "Glucosa")));
                //         ingredientes.add(new Ingredientes("Pepperoni", Arrays.asList("Sulfitos", "Lactosa")));
                //         ingredientes.add(new Ingredientes("Salsa de tomate", Arrays.asList()));
                //         ingredientes.add(new Ingredientes("Masa de pizza", Arrays.asList("Gluten")));
                //         ingredientes.add(new Ingredientes("Champinones", Arrays.asList()));

                //         controladorIngrediente.exportarIngredientesCSV(ingredientes, "listaingredientes");

                // } catch (Exception e) {
                //         System.out.println(e.getMessage());
                //         e.printStackTrace();
                // }

                // try {

                //         System.out.println("-------------------------------------------------------");
                //         System.out.println("importar csv");

                //         System.out.println(controladorIngrediente.ImportarIngredientesCSV("listaingredientes"));

                // } catch (Exception e) {
                //         System.out.println(e.getMessage());
                //         e.printStackTrace();
                // }

        }
}
