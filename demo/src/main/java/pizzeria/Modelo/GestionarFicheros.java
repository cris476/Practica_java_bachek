package pizzeria.Modelo;

import java.util.*;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.util.stream.Stream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.nio.file.Files;
import java.nio.file.Path;

public class GestionarFicheros {

    public List<Ingredientes> importarIngredientesCSV(String nombreArchivo) throws IOException, Exception {

        List<Ingredientes> listaIngredientes = new ArrayList<>();

        try (FileReader fileReader = new FileReader(nombreArchivo)) {

            CsvToBean<Ingredientes> csvToBean = new CsvToBeanBuilder<Ingredientes>(fileReader)
                    .withType(Ingredientes.class)
                    .withSeparator(';')
                    .build();

            listaIngredientes = csvToBean.parse();

        }

        return listaIngredientes;

    }

    public static void exportarIngredientesCsv(List<Ingredientes> listaIngrediente, String nombre)
            throws FileNotFoundException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        try (PrintWriter pw = new PrintWriter(nombre)) {
            StatefulBeanToCsv<Ingredientes> beanToCsv = new StatefulBeanToCsvBuilder<Ingredientes>(pw)
                    .withSeparator(';').build();
            beanToCsv.write(listaIngrediente);
        }
    }

    public List<Cliente> importarClientesTxT(String archivo) throws IOException {
        List<Cliente> clientesTxt = new ArrayList<Cliente>();
        String regex = "[;|,]";
        String separador = ",";
        try (Stream<String> lineas = Files.lines(Path.of(archivo))) {

            List<String> resultado = lineas.map(cliente -> cliente).collect(Collectors.toList());

            resultado.stream().forEach(
                    cliente -> {
                        String clienteSeparado = cliente.replaceAll(regex, ",");

                        clientesTxt.add(
                                new Cliente(Integer.parseInt(clienteSeparado.split(separador)[0].replace(" ", "")),
                                        clienteSeparado.split(separador)[1],
                                        clienteSeparado.split(separador)[2], clienteSeparado.split(separador)[3],
                                        clienteSeparado.split(separador)[4], clienteSeparado.split(separador)[5],
                                        clienteSeparado.split(separador)[6], true));

                    }

            );
            return clientesTxt;
        }

    }

    public List<Cliente> importarClienteXML(String archivo) throws JAXBException {
        File  archivoXML= new File(archivo);
        JAXBContext context = JAXBContext.newInstance(Clientes.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Clientes clientes = (Clientes) unmarshaller.unmarshal(archivoXML);
        return clientes.getClientes();
    }

    public void exportarClientesXML(List<Cliente> listaClientes, String archivoDestino) throws JAXBException {
        Clientes clientes = new Clientes();
        clientes.setClientes(listaClientes);
        JAXBContext context = JAXBContext.newInstance(Clientes.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(clientes, new File(archivoDestino));
        System.out.println("Clientes exportados correctamente a: " + archivoDestino);
    }


 

     public void   exportarCochesCSV(List<coche> listaCoches ,   String fichero) throws FileNotFoundException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException{

          try(PrintWriter escribir =  new PrintWriter(fichero)){

             StatefulBeanToCsv<coche>  csvToBin = new  StatefulBeanToCsvBuilder<coche>(escribir).withSeparator(';').build();  
               csvToBin.write(listaCoches);
             
          }


     }



}
