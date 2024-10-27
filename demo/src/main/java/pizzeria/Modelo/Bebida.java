package pizzeria.Modelo;

// import pizzeria.Modelo.enums.SizeApp;

public class Bebida extends Producto {

    private SizeApp size;

    public Bebida(String nombre, double precio, SizeApp size) {
        super(nombre, precio);
        this.size = size;
        // TODO Auto-generated constructor stub
    }

    public SizeApp getSize() {
        return size;
    }

    public void setSize(SizeApp size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Bebida [id=" + id + ", size=" + size + ", nombre=" + nombre + ", precio=" + precio + "]";
    }

    
    

}
