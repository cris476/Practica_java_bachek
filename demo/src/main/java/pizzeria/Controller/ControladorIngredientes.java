package pizzeria.Controller;

import java.util.*;
import pizzeria.Modelo.GestionarFicheros;
import pizzeria.Modelo.Ingredientes;

public class ControladorIngredientes {

    GestionarFicheros gestionarFicheros = new GestionarFicheros();

    public List<Ingredientes> ImportarIngredientesCSV(String nombreArchio) throws Exception {
        return gestionarFicheros.importarIngredientesCSV(nombreArchio);
    }

    public void exportarIngredientesCSV(List<Ingredientes> listaIngredientes, String nombreFichero) throws Exception {
        gestionarFicheros.exportarIngredientesCsv(listaIngredientes, nombreFichero);
    }

}
