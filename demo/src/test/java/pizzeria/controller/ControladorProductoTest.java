package pizzeria.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pizzeria.Controller.ContraladorCliente;
import pizzeria.Controller.ControladorProducto;
import pizzeria.Modelo.Alergeno;
import pizzeria.Modelo.Ingredientes;
import pizzeria.Modelo.Pasta;
import pizzeria.Modelo.Pizza;
import pizzeria.Modelo.SizeApp;

public class ControladorProductoTest {

    ControladorProducto controladorProducto;

    @BeforeEach
    void setUp() throws ClassNotFoundException, SQLException {
        controladorProducto = new ControladorProducto();
    }

    @Test
    void testRegistrarProducto() throws ClassNotFoundException, SQLException {

        List<Alergeno> alergenosTomate = new ArrayList<>();
        alergenosTomate.add(new Alergeno("Pelizilina"));
        alergenosTomate.add(new Alergeno("Gluten"));
        alergenosTomate.add(new Alergeno("Lactosa"));
        alergenosTomate.add(new Alergeno("Frutos secos"));

        List<Alergeno> alergenosQueso = new ArrayList<>();
        alergenosQueso.add(new Alergeno("leche"));
        alergenosQueso.add(new Alergeno("leche1"));
        alergenosQueso.add(new Alergeno("leche2"));

        List<Ingredientes> ingredientesPizza = new ArrayList<>();
        ingredientesPizza.add(new Ingredientes("Tomate10", alergenosTomate));
        ingredientesPizza.add(new Ingredientes("Queso Mozzarella", alergenosQueso));
        ingredientesPizza.add(new Ingredientes("Peperonni", alergenosQueso));

        Pizza pizza = new Pizza("Peperonni", 15, SizeApp.GRANDE, ingredientesPizza);

        
       Pasta pasta = new Pasta("P", 12.3, ingredientesPizza);

        controladorProducto.registrarProducto(pasta);
    }
}
