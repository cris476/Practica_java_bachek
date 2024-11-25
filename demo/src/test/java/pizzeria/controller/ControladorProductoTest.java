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

        List<Alergeno> alergenosPizza = new ArrayList<Alergeno>();
        alergenosPizza.add(new Alergeno("Pelizilina"));

        List<Ingredientes> ingredientesPizza = new ArrayList<Ingredientes>();
        ingredientesPizza.add(new Ingredientes("Tomate", alergenosPizza));

        Pizza pizza = new Pizza("Peperonni", 15, SizeApp.MEDIANO, ingredientesPizza);

        controladorProducto.registrarProducto(pizza);
    }
}
