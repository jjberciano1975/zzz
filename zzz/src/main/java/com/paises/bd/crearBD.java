package  com.paises.bd;


import java.sql.Connection;



import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.paises.entities.User;

/**
 * @author www.imagineanddo.com
 *
 */
public class crearBD {

 /**
  * @param args
 * @throws IllegalAccessException 
 * @throws InstantiationException
 * 
 
 
  */
 public static void main(String[] args)  {
     String driver = "org.apache.derby.jdbc.EmbeddedDriver";
     String dbName = "BdPaises";
     String dbParam = "create=true"; //Si la base de datos no existe, se crear� una nueva
     String dbschema= "schema=paises";
     String dbuser= "user=paises";
     String dbpass= "password=manager";
    		 
    // String dbDirectory = "//localhost:8080/BdPaises";
  
     //String connectionURL = "jdbc:derby:" +dbDirectory + ";" +dbName + ";" + dbParam+ ";" + dbuser+ ";" + dbpass;

     String connectionURL = "jdbc:derby:"   +dbName + ";"+ dbParam; //+ ";"   + dbschema + ";" + dbuser+ ";" + dbpass;
    /* Si no se especifica la ruta donde se crear� la base de datos,
     por defecto se crear� en la misma carpeta donde se encuentra el derby.jar
     En ese caso la cadena de conexi� ser�a la siguiente:
     String connectionURL = "jdbc:derby:" + dbName + ";" + dbParam;
     */
     Connection conn = null;
     try{
       Class.forName(driver);
    
     } catch(java.lang.ClassNotFoundException e) {
       e.printStackTrace();
     }
     try {
    	 
       conn = DriverManager.getConnection(connectionURL);
       Statement st = conn.createStatement();
       
      String sqlCreateTableUsers =
               "CREATE TABLE users ( " +
               "NAME VARCHAR(30) NOT NULL, " +
               "EMAIL VARCHAR(30) NOT NULL, " +
               "PASSWORD VARCHAR(30) NOT NULL, " +
               "IDUSER INTEGER NOT NULL CONSTRAINT idUser_PK PRIMARY KEY " +
               ")";
       st.execute(sqlCreateTableUsers);
       
       
       ResultSet rs = st.executeQuery("select name,email,password,iduser from user");		       			  
       while (rs.next()) 
       { 
           //System.out.println (rs.getString (1) + " " + rs.getString (2)+ " " + rs.getInt(3));
    	   User u = new User(rs.getString (1),rs.getString (2),rs.getString (3),rs.getInt(4)); 
    	   
    	
       }
      /* String sqlCreateTableCohes =
               "CREATE TABLE COCHES ( " +
               "marca VARCHAR(30) NOT NULL, " +
               "modelo VARCHAR(30) NOT NULL, " +              
               "idUser INTEGER NOT NULL CONSTRAINT idCoches_PK PRIMARY KEY " +
               ")";*/
     /*   st.execute(sqlCreateTableCohes);
        System.out.println("La base de datos '" + dbName + "' se ha creado correctamente");
        
         
     st.executeUpdate("INSERT INTO coches VALUES('Renault', 'R21', 1)");     
     st.executeUpdate("INSERT INTO coches VALUES('Seat', '124 L', 2)");
     st.executeUpdate("INSERT INTO coches VALUES('Fiat', 'uno', 3)");   
     st.executeUpdate("INSERT INTO coches VALUES('Renault', 'R12', 4)");*/
    /*   st.execute("drop table users ");
       String sqlCreateTableUsers =
              "CREATE TABLE users ( " +
              "name VARCHAR(30) NOT NULL, " +
              "email VARCHAR(30) NOT NULL, " +              
              "idUser INTEGER NOT NULL CONSTRAINT idUser_PK PRIMARY KEY " +
              ")";
       st.execute(sqlCreateTableUsers);
       System.out.println("La base de datos '" + dbName + "' se ha creado correctamente");
       
        
    st.executeUpdate("INSERT INTO users VALUES('Juan Perez', 'JuanPerez@lamoto.com', 1)");
    st.executeUpdate("INSERT INTO users VALUES('Renzo Lopez', 'RenzoLopez@lamoto.com', 2)");
    st.executeUpdate("INSERT INTO users VALUES('Carla Mendivil', 'CarlaMendivil@lamoto.com', 3)");       
      */ 
    System.out.println("insert se ha hecho correctamente");
       
     }  catch (Throwable e)  {
       System.out.println("Error al crear la base de datos '" + dbName + "'");
       e.printStackTrace();
     } finally {
       try { conn.close(); }
       catch (Throwable t){}
     }

 }

}