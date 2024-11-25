package pizzeria.Modelo;
import java.util.Date;
import java.util.List;



public class Pedido {
    
   private int id ; 
   private Date fecha ; 
   private  float precioTotal;
   private EstadoPedido estado ; 
   private List<LineaPedido> listaLineaPedidos ;
   private Cliente cliente ;  
   static  int contador = 0; 

public Pedido( Date fecha, float precioTotal , EstadoPedido estado,  List<LineaPedido> listaLineaPedidos  , Cliente cliente) {
    this.id = this.contador++ ;
    this.fecha = fecha;
    this.precioTotal = precioTotal;
    this.estado = estado; 
    this.listaLineaPedidos = listaLineaPedidos ; 
    this.cliente = cliente ; 
}

  public  boolean AñadirLineaPedido(LineaPedido lineaPedido){     
      this.listaLineaPedidos.add(lineaPedido); 
      return true ; 
  } 

   public float totalPrecio(){    
           for (LineaPedido lineaPedido : listaLineaPedidos) {
             this.precioTotal +=  (float)   (lineaPedido.getCantidad() *  lineaPedido.getProducto().getPrecio()  );
           }
       return  this.precioTotal  ;    
   }


public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public Date getFecha() {
    return fecha;
}

public void setFecha(Date fecha) {
    this.fecha = fecha;
}

public float getPrecioTotal() {
    return precioTotal;
}

public void setPrecioTotal(float precioTotal) {
    this.precioTotal = precioTotal;
}

public EstadoPedido getEstado() {
    return estado;
}


public void setEstado(EstadoPedido estado) {
    this.estado = estado;
}


public List<LineaPedido> getListaLineaPedidos() {
    return listaLineaPedidos;
}


public void setListaLineaPedidos(List<LineaPedido> listaLineaPedidos) {
    this.listaLineaPedidos = listaLineaPedidos;
} 
   

}
