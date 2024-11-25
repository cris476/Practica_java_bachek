package pizzeria.Controller.dao.impl;

import static pizzeria.utils.DatabaseConfig.INSERT_ALERGENO;
import static pizzeria.utils.DatabaseConfig.INSERT_INGREDIENTE_ALERGENO;
import static pizzeria.utils.DatabaseConfig.SELECT_ALERGENO_ID;
import static pizzeria.utils.DatabaseConfig.SELECT_ALERGENO_NOMBRE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pizzeria.Controller.dao.InnerAlergeno;
import pizzeria.Modelo.Alergeno;
import pizzeria.Modelo.Conexion;

public class JdbcAlergeno implements InnerAlergeno {

    @Override
    public void save(Connection con, int id_ingrediente, Alergeno alergeno)
            throws SQLException, ClassNotFoundException {

        try (Statement statement = con.createStatement();
                PreparedStatement preparedStatement = con.prepareStatement(INSERT_ALERGENO,
                        statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, alergeno.getNombre());
            preparedStatement.executeUpdate();

            int id_alegeno = 0;
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id_alegeno = generatedKeys.getInt(1);
                }
            }
            relacionIngredienteAlergeno(con, id_ingrediente, id_alegeno);
        }
    }

    public void relacionIngredienteAlergeno(Connection con, int id_alergeno, int id_ingrediente) throws SQLException {

        try (PreparedStatement preparedStatement2 = con.prepareStatement(INSERT_INGREDIENTE_ALERGENO)) {
            con.setAutoCommit(false);
            preparedStatement2.setInt(1, id_ingrediente);
            preparedStatement2.setInt(2, id_alergeno);
            preparedStatement2.executeUpdate();
            con.commit();
        }
    }

    @Override
    public Alergeno findById(int id) throws SQLException, ClassNotFoundException {

        Alergeno alergeno = null;
        try (Connection con = new Conexion().getConexion();
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALERGENO_ID)) {

            con.setAutoCommit(false);

            preparedStatement.setInt(1, id);

            try (ResultSet resultado = preparedStatement.executeQuery()) {

                if (resultado.next()) {

                    int idAlergeno = resultado.getInt("id");
                    String nombreAlergeno = resultado.getString("nombre");

                    alergeno = new Alergeno(idAlergeno, nombreAlergeno);
                }
            } catch (Exception e) {
                con.rollback();
                throw e;
            }

            con.commit();
            return alergeno;
        }

    }

    @Override
    public Alergeno findByName(Connection con, String name) throws SQLException, ClassNotFoundException {

        Alergeno alergeno = null;
        try (
                PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALERGENO_NOMBRE)) {

            con.setAutoCommit(false);

            preparedStatement.setString(1, name);

            try (ResultSet resultado = preparedStatement.executeQuery()) {

                if (resultado.next()) {

                    int idAlergeno = resultado.getInt("id");
                    String nombreAlergeno = resultado.getString("nombre");

                    alergeno = new Alergeno(idAlergeno, nombreAlergeno);
                }
            } catch (Exception e) {
                con.rollback();
                throw e;
            }

            con.commit();
            return alergeno;
        }

    }

}