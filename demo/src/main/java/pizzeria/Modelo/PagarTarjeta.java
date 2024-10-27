package pizzeria.Modelo;



public class PagarTarjeta implements Pagable {

    @Override
    public void pagar(Float cantidad) {
        // TODO Auto-generated method stub
        System.out.println("Se ha pagado  con tarjeta la cantidad de " + cantidad + "$");
    }

}
