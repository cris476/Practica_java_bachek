package pizzeria.Controller.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pizzeria.Controller.dao.InnerProductoDAO;
import pizzeria.Modelo.Bebida;
import pizzeria.Modelo.Conexion;
import pizzeria.Modelo.Ingredientes;
import pizzeria.Modelo.Pasta;
import pizzeria.Modelo.Pizza;
import pizzeria.Modelo.Producto;
import pizzeria.Modelo.SizeApp;

import static pizzeria.utils.DatabaseConfig.*;
import pizzeria.Modelo.Tipo;

public class JdbcProductoDAO implements InnerProductoDAO {

    JdbcIngredienteDAO jdbcIngredienteDAO = new JdbcIngredienteDAO();

    @Override
    public void save(Producto producto) throws SQLException, ClassNotFoundException {

        try (Connection con = new Conexion().getConexion()) {
            con.setAutoCommit(false);
            // if (findByNameAndSize(producto) != null) {
            try {
                int idProducto = insertarProducto(con, producto);
                if (producto instanceof Pizza) {
                    Pizza pizza = (Pizza) producto;
                    if (pizza.getIngredientes() != null && pizza.getIngredientes().size() >= 0) {
                        for (Ingredientes ingrediente : pizza.getIngredientes()) {
                            Ingredientes ingredienteEncontrado = jdbcIngredienteDAO.findByName(ingrediente.getNombre());
                            if (ingredienteEncontrado == null) {
                                insertarIngredienteYRelacion(con, idProducto, ingrediente);
                            } else {
                                jdbcIngredienteDAO.relacionProductoIngrediente(con, idProducto, ingredienteEncontrado);
                            }
                        }
                    }

                } else if (producto instanceof Pasta) {
                    Pasta pasta = (Pasta) producto;
                    if (pasta.getIngredientes() != null && pasta.getIngredientes().size() >= 0) {
                        for (Ingredientes ingrediente : pasta.getIngredientes()) {
                            Ingredientes ingredienteEncontrado = jdbcIngredienteDAO.findByName(ingrediente.getNombre());
                            if (ingredienteEncontrado == null) {
                                insertarIngredienteYRelacion(con, idProducto, ingrediente);
                            } else {
                                jdbcIngredienteDAO.relacionProductoIngrediente(con, idProducto, ingredienteEncontrado);
                            }
                        }
                    }
                }
                con.commit();
            } catch (SQLException | ClassNotFoundException e) {
                con.rollback();
                throw e;
            }
        }
    }

    private int insertarProducto(Connection con, Producto producto) throws SQLException, ClassNotFoundException {
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_PRODUCTO,
                Statement.RETURN_GENERATED_KEYS)) {
            if (producto instanceof Pizza) {
                savePizza(preparedStatement, (Pizza) producto);
            } else if (producto instanceof Bebida) {
                saveBebida(preparedStatement, (Bebida) producto);
            } else if (producto instanceof Pasta) {
                savePasta(preparedStatement, (Pasta) producto);
            }

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para el producto.");
                }
            }
        }
    }

    public Producto findByNameAndSize(Producto producto) throws SQLException, ClassNotFoundException {
        Pizza pizza;
        Bebida bebida;
        Pasta pasta;
        Producto productoEncontrado = null;
        try (Connection con = new Conexion().getConexion();
                Statement statement = con.createStatement();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_PRODUCTO_NAME_SIZE)) {

            if (producto instanceof Pizza) {
                pizza = (Pizza) producto;
                preparedStatement.setString(1, pizza.getNombre());
                preparedStatement.setString(2, pizza.getSize().getValue());
            } else if (producto instanceof Bebida) {
                bebida = (Bebida) producto;
                preparedStatement.setString(1, bebida.getNombre());
                preparedStatement.setString(2, bebida.getSize().getValue());
            } else if (producto instanceof Pasta) {
                return null;
            }

            preparedStatement.setString(1, producto.getNombre());

            try (ResultSet resultado = preparedStatement.executeQuery();) {
                if (resultado.next()) {

                    int idProducto = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    Double precio = resultado.getDouble("precio");
                    SizeApp size = SizeApp.valueOf(resultado.getString("size").toUpperCase());
                    Tipo tipo = Tipo.valueOf(resultado.getString("tipo").toUpperCase());
                    List<Ingredientes> listaIngredientes = new ArrayList<Ingredientes>();

                    switch (tipo.getValue()) {
                        case "pasta":
                            listaIngredientes = jdbcIngredienteDAO.getAllIngredienteByidProducto(con, idProducto);
                            productoEncontrado = new Pasta(idProducto, nombre, precio, listaIngredientes);

                            break;
                        case "pizza":
                            listaIngredientes = jdbcIngredienteDAO.getAllIngredienteByidProducto(con,
                                    idProducto);
                            productoEncontrado = new Pizza(idProducto, nombre, precio, size, listaIngredientes);
                            break;
                        case "bebida":
                            productoEncontrado = new Bebida(idProducto, nombre, precio, size);
                            break;
                        default:
                            break;
                    }

                }
            }

        }

        return productoEncontrado;
    }

    private void insertarIngredienteYRelacion(Connection con, int idProducto, Ingredientes ingrediente)
            throws SQLException, ClassNotFoundException {
        jdbcIngredienteDAO.save(con, idProducto, ingrediente);
    }

    private void savePizza(PreparedStatement preparedStatement, Pizza pizza)
            throws SQLException, ClassNotFoundException {

        preparedStatement.setString(1, pizza.getNombre());
        preparedStatement.setDouble(2, pizza.getPrecio());
        preparedStatement.setString(3, Tipo.PIZZA.getValue());
        preparedStatement.setString(4, pizza.getSize().getValue());

    }

    private void saveBebida(PreparedStatement preparedStatement, Bebida bebida) throws SQLException {
        preparedStatement.setString(1, bebida.getNombre());
        preparedStatement.setDouble(2, bebida.getPrecio());
        preparedStatement.setString(3, Tipo.BEBIDA.getValue());
        preparedStatement.setString(4, bebida.getSize().getValue());

    }

    private void savePasta(PreparedStatement preparedStatement, Pasta pasta)
            throws SQLException, ClassNotFoundException {

        preparedStatement.setString(1, pasta.getNombre());
        preparedStatement.setDouble(2, pasta.getPrecio());
        preparedStatement.setString(3, Tipo.PASTA.getValue());
        preparedStatement.setNull(4, java.sql.Types.NULL);

    }

    @Override
    public List<Producto> getAllProductos() throws SQLException, ClassNotFoundException {

        List<Producto> listaProductos = new ArrayList<Producto>();
        try (Connection con = new Conexion().getConexion();
                Statement statement = con.createStatement();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_PRODUCTO)) {

            try (ResultSet resultado = preparedStatement.executeQuery();) {
                while (resultado.next()) {
                    SizeApp size;
                    int idProducto = resultado.getInt("id");
                    String nombre = resultado.getString("nombre");
                    Double precio = resultado.getDouble("precio");
                    Tipo tipo = Tipo.valueOf(resultado.getString("tipo").toUpperCase());
                    List<Ingredientes> listaIngredientes = new ArrayList<Ingredientes>();

                    switch (tipo.getValue()) {
                        case "pasta":
                            listaIngredientes = jdbcIngredienteDAO.getAllIngredienteByidProducto(con, idProducto);
                            listaProductos.add(new Pasta(idProducto, nombre, precio, listaIngredientes));

                            break;
                        case "pizza":
                            size = SizeApp.valueOf(resultado.getString("size").toUpperCase());
                            listaIngredientes = jdbcIngredienteDAO.getAllIngredienteByidProducto(con, idProducto);
                            listaProductos.add(new Pizza(idProducto, nombre, precio, size, listaIngredientes));
                            break;
                        case "bebida":
                            size = SizeApp.valueOf(resultado.getString("size").toUpperCase());
                            listaProductos.add(new Bebida(idProducto, nombre, precio, size));
                            break;
                        default:
                            break;
                    }

                }
            }

        }

        return listaProductos;

    }

    public Producto getProductoById(int idProducto) throws SQLException, ClassNotFoundException {

        Producto producto = null;
        SizeApp size ; 
        try (Connection con = new Conexion().getConexion();
                Statement statement = con.createStatement();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_PRODUCTO_ID)) {

            preparedStatement.setInt(1, idProducto);

            try (ResultSet resultado = preparedStatement.executeQuery();) {
                if (resultado.next()) {
                    String nombre = resultado.getString("nombre");
                    Double precio = resultado.getDouble("precio");

                    Tipo tipo = Tipo.valueOf(resultado.getString("tipo").toUpperCase());
                    List<Ingredientes> listaIngredientes = new ArrayList<Ingredientes>();

                    switch (tipo.getValue()) {
                        case "pasta":
                            listaIngredientes = jdbcIngredienteDAO.getAllIngredienteByidProducto(con, idProducto);
                            producto = new Pasta(idProducto, nombre, precio, listaIngredientes);

                            break;
                        case "pizza":
                             size = SizeApp.valueOf(resultado.getString("size").toUpperCase());
                            listaIngredientes = jdbcIngredienteDAO.getAllIngredienteByidProducto(con, idProducto);
                            producto = new Pizza(idProducto, nombre, precio, size, listaIngredientes);
                            break;
                        case "bebida":
                             size = SizeApp.valueOf(resultado.getString("size").toUpperCase());
                            producto = new Bebida(idProducto, nombre, precio, size);
                            break;
                        default:
                            break;
                    }

                }
            }

        }

        return producto;

    }

}
