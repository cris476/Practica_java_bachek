package pizzeria.Modelo;

import java.util.List;

public class Pizza extends Producto {

    private SizeApp size;
    private List<Ingredientes> ingredientes;

    public Pizza(int id, String nombre, double precio, SizeApp size, List<Ingredientes> ingredientes) {
        super(id, nombre, precio);
        this.size = size;
        this.ingredientes = ingredientes;
    }

    public Pizza(String nombre, double precio, SizeApp size, List<Ingredientes> ingredientes) {
        super(nombre, precio);
        this.size = size;
        this.ingredientes = ingredientes;
    }

    public SizeApp getSize() {
        return size;
    }

    public void setSize(SizeApp size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Pizza [id=" + id + ", size=" + size + ", nombre=" + nombre + ", precio=" + precio + " ," + ingredientes
                + " + ingredientes ]";
    }

    public List<Ingredientes> getIngredientes() {
        return ingredientes;
    }

}
