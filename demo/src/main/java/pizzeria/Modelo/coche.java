package pizzeria.Modelo;

import com.opencsv.bean.CsvBindByName;

public class coche {

    private int id ;
    @CsvBindByName(column = "matricula") 
    private  String  matricula; 
    @CsvBindByName(column = "marca") 
    private  String  marca; 
    @CsvBindByName(column = "motor") 
    private  String motor ;
 

     public coche(){
        
     }

    public coche(int id, String matricula, String marca, String motor) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.motor = motor;
    }

 
    public static void main(String[] args) {
        
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getMatricula() {
        return matricula;
    }


    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }


    public String getMarca() {
        return marca;
    }


    public void setMarca(String marca) {
        this.marca = marca;
    }


    public String getMotor() {
        return motor;
    }


    public void setMotor(String motor) {
        this.motor = motor;
    } 
    
   
    



}
