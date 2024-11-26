package pizzeria.Controller.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import pizzeria.Controller.dao.InnerIngredienteDAO;
import pizzeria.Modelo.Alergeno;
import pizzeria.Modelo.Conexion;
import pizzeria.Modelo.Ingredientes;
import static pizzeria.utils.DatabaseConfig.*;

public class JdbcIngredienteDAO implements InnerIngredienteDAO {

    private final JdbcAlergeno jdbcAlergeno = new JdbcAlergeno();

    @Override
    public void save(Connection con, int id_producto, Ingredientes ingrediente)
            throws SQLException, ClassNotFoundException {

        con.setAutoCommit(false);

        int id_ingrediente = insertarIngrediente(con, ingrediente);

        insertarProductoIngrediente(con, id_producto, id_ingrediente);

        if (ingrediente.getAlergenos() != null && ingrediente.getAlergenos().size() >= 0) {
            for (Alergeno alergeno : ingrediente.getAlergenos()) {

                Alergeno alergenoExiste = jdbcAlergeno.findByName(con, alergeno.getNombre());

                if (alergenoExiste == null) {
                    jdbcAlergeno.save(con, id_ingrediente, alergeno);
                } else {
                    jdbcAlergeno.relacionIngredienteAlergeno(con, id_ingrediente, alergenoExiste.getId());

                }
            }
        }
        con.commit();
    }

    public void saveWithIngrediente(Connection con, int id_producto, Ingredientes ingrediente)
            throws SQLException, ClassNotFoundException {

        con.setAutoCommit(false);
        insertarProductoIngrediente(con, id_producto, ingrediente.getId());
        con.commit();
    }

    private int insertarIngrediente(Connection con, Ingredientes ingrediente) throws SQLException {
        int idIngrediente;
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_INGREDIENTE,
                PreparedStatement.RETURN_GENERATED_KEYS)) {

            con.setAutoCommit(false);
            preparedStatement.setString(1, ingrediente.getNombre());
            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idIngrediente = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID generado para el ingrediente.");
                }
            }
            con.commit();
        }
        return idIngrediente;
    }

    public void insertarProductoIngrediente(Connection con, int id_producto, int id_ingrediente) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_PRODUCTO_iNGREDIENTE)) {
            preparedStatement.setInt(1, id_producto);
            preparedStatement.setInt(2, id_ingrediente);
            preparedStatement.executeUpdate();
        }
    }

    public Ingredientes findByName(String name) throws SQLException, ClassNotFoundException {

        Ingredientes ingrediente = null;
        try (Connection con = new Conexion().getConexion();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_INGREDIENTE_NOMBRE)) {

            con.setAutoCommit(false);

            preparedStatement.setString(1, name);

            try (ResultSet resultado = preparedStatement.executeQuery()) {

                if (resultado.next()) {
                    int idIngrediente = resultado.getInt("id");
                    String nombreIngrediente = resultado.getString("nombre");
                    List<Alergeno> alergenos = jdbcAlergeno.getAllAlergenoByidIngrediente(idIngrediente);
                    ingrediente = new Ingredientes(idIngrediente, nombreIngrediente, alergenos);
                }

            } catch (Exception e) {
                con.rollback();
                throw e;
            }

            con.commit();
            return ingrediente;
        }

    }
}
