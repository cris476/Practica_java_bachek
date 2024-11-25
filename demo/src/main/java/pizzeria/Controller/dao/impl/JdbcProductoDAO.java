package pizzeria.Controller.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import pizzeria.Controller.dao.InnerProductoDAO;
import pizzeria.Modelo.Bebida;
import pizzeria.Modelo.Conexion;
import pizzeria.Modelo.Ingredientes;
import pizzeria.Modelo.Pasta;
import pizzeria.Modelo.Pizza;
import pizzeria.Modelo.Producto;
import static pizzeria.utils.DatabaseConfig.*;

public class JdbcProductoDAO implements InnerProductoDAO {

    JdbcIngredienteDAO jdbcIngredienteDAO = new JdbcIngredienteDAO();

    @Override
    public void save(Producto producto) throws SQLException, ClassNotFoundException {
        try (Connection con = new Conexion().getConexion()) {
            con.setAutoCommit(false);

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
                                  
                            }
                        }
                    }

                } else if (producto instanceof Pasta) {
                    Pasta pasta = (Pasta) producto;
                    if (pasta.getIngredientes() != null && pasta.getIngredientes().size() >= 0) {
                        for (Ingredientes ingrediente : pasta.getIngredientes()) {
                            insertarIngredienteYRelacion(con, idProducto, ingrediente);
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

    private void insertarIngredienteYRelacion(Connection con, int idProducto, Ingredientes ingrediente)
            throws SQLException, ClassNotFoundException {
        JdbcIngredienteDAO ingredienteDAO = new JdbcIngredienteDAO();
        ingredienteDAO.save(con, idProducto, ingrediente);
    }

    private void savePizza(PreparedStatement preparedStatement, Pizza pizza)
            throws SQLException, ClassNotFoundException {

        preparedStatement.setString(1, pizza.getNombre());
        preparedStatement.setDouble(2, pizza.getPrecio());
        preparedStatement.setString(3, pizza.getSize().getValue());

    }

    private void saveBebida(PreparedStatement preparedStatement, Bebida bebida) throws SQLException {
        preparedStatement.setString(1, bebida.getNombre());
        preparedStatement.setDouble(2, bebida.getPrecio());
        preparedStatement.setString(3, bebida.getSize().getValue());

    }

    private void savePasta(PreparedStatement preparedStatement, Pasta pasta)
            throws SQLException, ClassNotFoundException {

        preparedStatement.setString(1, pasta.getNombre());
        preparedStatement.setDouble(2, pasta.getPrecio());
        preparedStatement.setNull(4, java.sql.Types.NULL);

    }

    @Override
    public void getAllProductos() throws SQLException, ClassNotFoundException {

        try (Connection con = new Conexion().getConexion();
                Statement statement = con.createStatement();
                PreparedStatement preparedStatement = con.prepareStatement(INSERT_PRODUCTO,
                        statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.executeUpdate();

        }

    }

}