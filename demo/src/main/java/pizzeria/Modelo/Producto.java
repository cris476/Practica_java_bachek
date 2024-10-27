package pizzeria.Modelo;

/**
 * Producto
 */
public abstract class Producto  {

 
   protected int id ; 
   protected String nombre ; 
   protected double precio; 
   static  int contador = 0; 

    public Producto(String nombre ,double precio ){
           this.id = this.contador++ ; 
           this.nombre = nombre ; 
           this.precio = precio ; 

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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
     

    
}