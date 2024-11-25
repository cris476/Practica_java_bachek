package pizzeria.Controller.dao;

import java.sql.Connection;
import java.sql.SQLException;

import pizzeria.Modelo.Alergeno;

public interface InnerAlergeno {

    public void save(Connection con, int id, Alergeno alergeno) throws SQLException, ClassNotFoundException;

    public Alergeno findById(int id) throws SQLException, ClassNotFoundException;

    
    public Alergeno findByName(Connection con,  String name) throws SQLException, ClassNotFoundException;

}