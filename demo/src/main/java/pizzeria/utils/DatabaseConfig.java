package pizzeria.utils;

public class DatabaseConfig {

   public static final String INNER_CLIENTE = "INSERT INTO CLIENTE(dni , nombre , direccion , email , password , admin , telefono) VALUES (?,?,?,?,?,?,?)";
   public static final String INSERT_PRODUCTO = "INSERT INTO PRODUCTO(nombre, precio,tipo , size) VALUES (?,?,?,?)";
   public static final String INSERT_PRODUCTO_iNGREDIENTE = "INSERT INTO  producto_ingrediente (id_producto , id_ingrediente) VALUES (?,?)";
   public static final String INSERT_INGREDIENTE = "INSERT INTO INGREDIENTE (nombre) VALUES (?)";
   public static final String INSERT_INGREDIENTE_ALERGENO = "INSERT INTO  ingrediente_alergeno (id_ingrediente , id_alergeno) VALUES (?,?)";
   public static final String INSERT_ALERGENO = "INSERT INTO  ALERGENO (nombre) VALUES (?)";

   public static final String SELECT_INGREDIENTE_ALERGENO = "select  alergeno.id , alergeno.nombre from  ingrediente inner join  \n"
         + //
         "ingrediente_alergeno on  ingrediente.id = \n" + //
         "ingrediente_alergeno.id_ingrediente \n" + //
         "inner  join  alergeno on alergeno.id = ingrediente_alergeno.id_alergeno\n" + //
         "where  ingrediente_alergeno.id_ingrediente = ?; ";

   public static final String SELECT_CLIENTE = "SELECT * FROM cliente WHERE email = ?";
   public static final String SELECT_ALERGENO_ID = "SELECT id , nombre FROM  alergeno where id = ?";
   public static final String SELECT_ALERGENO_NOMBRE = "SELECT id , nombre FROM  alergeno where nombre = ?";
   public static final String SELECT_INGREDIENTE_NOMBRE = "SELECT id , nombre FROM  ingrediente where nombre = ?";
   public static final String SELECT_ALL_PRODUCTO = "SELECT * from producto";
   public static final String SELECT_LOGIN_CLIENTE = "SELECT * FROM cliente WHERE nombre = ? AND password = ?";
   public static final String SELECT_ALL_CLIENTE = "SELECT id , dni , nombre , direccion , email , password , admin , telefono FROM cliente ";
   public static final String SELECT_ALL_PRODUCT = "SELECT id  , nombre , precio , tipo FROM producto";

   public static final String UPDATE_CLIENTE = "UPDATE cliente SET nombre = ? ,  ";

}
