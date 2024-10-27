package pizzeria.Modelo;

import java.util.List;

import com.opencsv.bean.CsvBindAndSplitByName;
import com.opencsv.bean.CsvBindByName;

public class Ingredientes {
    @CsvBindByName(column = "ID")
    private int id;
    @CsvBindByName(column = "NOMBRE")
    private String nombre;
    @CsvBindAndSplitByName(elementType = String.class, splitOn = ",")
    private List<String> alergenos;
    static int contador = 0;

    public Ingredientes() {
    }

    public Ingredientes(String nombre, List<String> alergenos) {
        this.id = this.contador++;
        this.nombre = nombre;
        this.alergenos = alergenos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getAlergenos() {
        return alergenos;
    }

    public void setAlergenos(List<String> alergenos) {
        this.alergenos = alergenos;
    }

    @Override
    public String toString() {
        return "Ingredientes [id=" + id + ", nombre=" + nombre + ", alergenos=" + alergenos + "]";
    }

}
