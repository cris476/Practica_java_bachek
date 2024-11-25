package pizzeria.Controller.dao;

import java.sql.SQLException;

import pizzeria.Modelo.Producto;

public interface InnerProductoDAO {

    public void save(Producto producto) throws SQLException, ClassNotFoundException;


    public void getAllProductos() throws SQLException, ClassNotFoundException; 
    
   
    

}
