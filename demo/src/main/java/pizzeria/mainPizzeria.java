package pizzeria;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.xdevapi.Client;

import javafx.css.Size;
import pizzeria.Controller.ContraladorCliente;
import pizzeria.Controller.ContraladorPedido;
import pizzeria.Controller.ControladorProducto;
import pizzeria.Exceptions.ExceptionFoundCliente;
import pizzeria.Modelo.Alergeno;
import pizzeria.Modelo.Bebida;
import pizzeria.Modelo.Cliente;
import pizzeria.Modelo.Ingredientes;
import pizzeria.Modelo.Pasta;
import pizzeria.Modelo.Pizza;
import pizzeria.Modelo.Producto;
import pizzeria.Modelo.SizeApp;
import pizzeria.Modelo.Tipo;

public class mainPizzeria {

    public static void main(String[] args) {

        Cliente cliente;
        Boolean parte1 = true;
        Boolean parte2 = false;
        Boolean añadirAlergeno = true;
        Boolean añadirIngrediente = true;
        Boolean tipoProducto = true;
        Boolean sizeProducto = true;
        String nombre;
        String password;
        String email;
        String telefono;
        String direccion;
        String dni;
        String opcion;
        String nombreProducto;
        List<Alergeno> alergenos = new ArrayList<>();
        List<Ingredientes> ingredientes = new ArrayList<>();
        String nombreIngrediente;
        String alergeno;
        List<Producto> productos;
        Producto producto = null;
        Producto productoInteresado = null;
        Double precio;
        int cantidad;
        Tipo tipo = null;
        SizeApp size = null;

        Scanner sc = new Scanner(System.in);

        ContraladorCliente controladorCliente = new ContraladorCliente();
        ControladorProducto controladorProducto = new ControladorProducto();
        ContraladorPedido controladorPedido = new ContraladorPedido();

        while (parte1) {

            System.out.println("1. Login");
            System.out.println("2. Registrarse");
            System.out.print("3.salir");
            System.out.println("Seleccion una opcion : ");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":

                    System.out.print("Introduce el nombre: ");
                    nombre = sc.nextLine();

                    System.out.print("Introduce la contraseña: ");
                    password = sc.nextLine();

                    try {
                        cliente = controladorCliente.loginCliente(nombre, password);

                        if (cliente != null) {
                            parte2 = true;
                        }

                        while (parte2) {
                            System.out.println();
                            System.out.println();

                            System.out.println("1. Visualizar todos los Productos");
                            System.out.println("2. añadir producto");
                            System.out.println("3. añadir al carrito");
                            System.out.println("4. salir");
                            System.out.print("Seleccion una opcion : ");
                            opcion = sc.nextLine();

                            switch (opcion) {
                                case "1":
                                    productos = controladorProducto.getAllProducts();

                                    for (Producto productooo : productos) {
                                        System.out.println(productooo);
                                    }
                                    break;
                                case "2":
                                    while (tipoProducto) {

                                        System.out.println("1. pizza");
                                        System.out.println("2. pasta");
                                        System.out.println("3. bebida");

                                        System.out.print("selecciona el tipo de producto:");
                                        opcion = sc.nextLine();

                                        switch (opcion) {
                                            case "1":
                                                tipo = Tipo.PIZZA;
                                                tipoProducto = false;
                                                break;
                                            case "2":
                                                tipo = Tipo.PASTA;
                                                tipoProducto = false;
                                                break;
                                            case "3":
                                                tipo = Tipo.BEBIDA;
                                                tipoProducto = false;
                                                break;
                                            default:
                                                System.out.println("selecciona una opción de las mostradas");
                                                break;
                                        }

                                    }

                                    while (añadirIngrediente && Tipo.BEBIDA != tipo) {

                                        while (añadirAlergeno) {
                                            System.out.println();
                                            System.out.print("Añadir Alergeno:");
                                            alergeno = sc.nextLine();

                                            alergenos.add(new Alergeno(alergeno));

                                            System.out.print("Quieres Añadir mas Alergenos S/N:");
                                            opcion = sc.nextLine();
                                            if (opcion.equalsIgnoreCase("N")) {
                                                añadirAlergeno = false;
                                            }

                                        }
                                        System.out.println();
                                        System.out.print("Introduce el nombre del Ingrediente:");
                                        nombreIngrediente = sc.nextLine();

                                        ingredientes.add(new Ingredientes(nombreIngrediente, alergenos));
                                        System.out.println();
                                        System.out.print("Quieres Añadir mas Ingredientes S/N:");
                                        opcion = sc.nextLine();

                                        if (opcion.equalsIgnoreCase("N")) {
                                            añadirIngrediente = false;
                                        } else {
                                            añadirAlergeno = true;
                                        }
                                    }

                                    System.out.print("Introduce un nombre al producto " + tipo.getValue() + ":");
                                    nombreProducto = sc.nextLine();

                                    System.out.print("Introduce el precio del producto " + tipo.getValue() + ":");
                                    precio = sc.nextDouble();

                                    switch (tipo.getValue()) {
                                        case "pizza":

                                            while (sizeProducto) {
                                                System.out.println();
                                                System.out.println("1. grande");
                                                System.out.println("2. medio");
                                                System.out.println("3. pequeño");
                                                System.out.print("Selecciona el tamaño del producto");
                                                opcion = sc.next();

                                                switch (opcion) {
                                                    case "1":
                                                        size = SizeApp.GRANDE;
                                                        sizeProducto = false;
                                                        break;
                                                    case "2":
                                                        size = SizeApp.MEDIANO;
                                                        sizeProducto = false;
                                                        break;
                                                    case "3":
                                                        size = SizeApp.PEQUENO;
                                                        sizeProducto = false;
                                                        break;
                                                    default:
                                                        System.out.println("Selecciona un producto mostrados");
                                                        break;
                                                }

                                            }

                                            producto = new Pizza(nombreProducto, precio, size, ingredientes);
                                            break;
                                        case "pasta":
                                            producto = new Pasta(nombreProducto, precio, ingredientes);
                                            break;
                                        case "bebida":

                                            while (sizeProducto) {
                                                System.out.println();
                                                System.out.println("1. grande");
                                                System.out.println("2. medio");
                                                System.out.println("3. pequeño");
                                                System.out.print("Selecciona el tamaño del producto");
                                                opcion = sc.nextLine();

                                                switch (opcion) {
                                                    case "1":
                                                        size = SizeApp.GRANDE;
                                                        sizeProducto = false;
                                                        break;
                                                    case "2":
                                                        size = SizeApp.MEDIANO;
                                                        sizeProducto = false;
                                                        break;
                                                    case "3":
                                                        size = SizeApp.PEQUENO;
                                                        sizeProducto = false;
                                                        break;
                                                    default:
                                                        System.out.println("Selecciona un producto mostrados");
                                                        break;
                                                }

                                            }
                                            producto = new Bebida(nombreProducto, precio, size);
                                            break;
                                        default:
                                            System.out.println("selecciona una opción de las mostradas");
                                            break;
                                    }

                                    controladorProducto.saveProduct(producto);
                                    productos = controladorProducto.getAllProducts();

                                    for (Producto productooo : productos) {
                                        System.out.println(productooo);
                                    }
                                    parte2 = false;
                                    break;
                                case "3":
                                    productos = controladorProducto.getAllProducts();

                                    for (Producto productoItem : productos) {
                                        System.out.println(
                                                productoItem.getId() + " El producto " + productoItem.getNombre()
                                                        + " con el precio " + productoItem.getPrecio() + " ");
                                    }

                                    while (productoInteresado == null) {
                                        System.out.print("seleccione el id del  producto interesado: ");
                                        opcion = sc.nextLine();
                                        productoInteresado = controladorProducto
                                                .getProductoById(Integer.parseInt(opcion));
                                    }

                                    System.out.println(productoInteresado);
                                    System.out.print("selecciona  la cantida del producto");
                                    cantidad = sc.nextInt();

                                    controladorPedido.addCarrito(productoInteresado, cantidad, cliente);

                                    break;
                                case "4":
                                    parte2 = false;
                                    break;
                                default:
                                    break;
                            }

                        }

                    } catch (ClassNotFoundException | SQLException e) {

                        e.printStackTrace();
                    }

                    break;
                case "2":

                    System.out.print("Introduce el DNI: ");
                    dni = sc.nextLine();

                    System.out.print("Introduce el nombre: ");
                    nombre = sc.nextLine();

                    System.out.print("Introduce la dirección: ");
                    direccion = sc.nextLine();

                    System.out.print("Introduce el teléfono: ");
                    telefono = sc.nextLine();

                    System.out.print("Introduce el email: ");
                    email = sc.nextLine();

                    System.out.print("Introduce la contraseña: ");
                    password = sc.nextLine();

                    Cliente clienteRegistrado = new Cliente(dni, nombre, direccion, telefono, email, password, false);
                    try {
                        controladorCliente.registrarCliente(clienteRegistrado);
                    } catch (ClassNotFoundException | SQLException | ExceptionFoundCliente e) {
                        e.printStackTrace();
                    }
                    break;
                case "3":
                    parte1 = false;
                    break;
                default:
                    break;
            }

        }

    }

}
