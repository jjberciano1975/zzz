package com.paises.persistence;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.paises.entities.Coches;

public class CochesCrud {
	 private static List<Coches> ent = new ArrayList<Coches>();
	 
	 public static List<Coches>  cargalista(String accion, String marca, String modelo, int iduser) {
		
		  String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		     String dbName = "BdPaises";
		     String dbParam = "create=true"; //Si la base de datos no existe, se crear� una nueva
		     String dbschema= "schema=paises";
		     String dbuser= "user=paises";
		     String dbpass= "password=manager";
		    // String dbDirectory = "//localhost:1527/BdPaises";
		     String connectionURL = "jdbc:derby:"   +dbName + ";"+ dbParam;//+ ";"   + dbschema + ";" + dbuser+ ";" + dbpass;
	     
	     Connection conn = null;
	     try{
	       Class.forName(driver);
	     } catch(java.lang.ClassNotFoundException e) {
	       e.printStackTrace();
	     }
	     
	     try {
	    	 System.out.println("accion "+accion); 
	    	 conn = DriverManager.getConnection(connectionURL);
	      
	    	 System.out.println("creado o conectada Bd "+dbName);
	
	     Statement statement = conn.createStatement();
	     if (accion=="S"){
	    	 
	    /*	 statement.executeUpdate( "CREATE TABLE partidas ( " +
	                  "IDUSER INTEGER, " +
	                  "CODPART INTEGER, " +
	                  "FECHAPART TIMESTAMP,"+
	                  "CODEQUI INTEGER," +	                  
	                  "EQUIPO VARCHAR(30)  " +
	                  ")");
	    	         
	    	 statement.executeUpdate("INSERT INTO partidas VALUES(1, 10001,CURRENT_TIMESTAMP, 79,'ISLA CRISTINA')");  
	    	 statement.executeUpdate("INSERT INTO partidas VALUES(1, 10001,CURRENT_TIMESTAMP, 319,'KRONENBURG FC')");
	    	 */
	    	 
	    	 statement.executeUpdate( "CREATE TABLE coches (marca VARCHAR(30),modelo VARCHAR(30),IDUSER INTEGER  )");
	    	
	    	  
	    	  statement.executeUpdate("delete from coches"); 
	    	  statement.executeUpdate("INSERT INTO coches VALUES('Renault', 'R21', 1)");     
	    	  statement.executeUpdate("INSERT INTO coches VALUES('Seat', '124 L', 2)");
	    	  statement.executeUpdate("INSERT INTO coches VALUES('Fiat', 'uno', 3)");   
	    	  statement.executeUpdate("INSERT INTO coches VALUES('Renault', 'R12', 4)");
	    	  
	    	 ent.clear();	
	    	 ResultSet rs = statement.executeQuery("select * from coches");		       			  
		       while (rs.next()) 
		       { 
		           //System.out.println (rs.getString (1) + " " + rs.getString (2)+ " " + rs.getInt(3));
		    	   Coches u = new Coches(rs.getString (1),rs.getString (2),rs.getInt(3)); 
		    	   ent.add(u);
		    	
		       }
		       
	     }  //if (accion=="G"){ 
	     
	     else if (accion=="C"){
	    	 //System.out.println("insert "+marca+" , "+modelo+","+iduser);
	    	 statement.executeUpdate("INSERT INTO coches VALUES('"+marca+"' , '"+modelo+"',"+iduser+")");
	    	 
	     }  //else if (accion=="C"){ 
	     else if (accion=="D"){
	    	  System.out.println("delete "+iduser);
	    	 statement.executeUpdate("delete from coches where iduser="+iduser); 
	     } //  else if (accion=="D"){ 
	       
	     }  catch (Throwable e)  {
	       System.out.println("Error en la bd '" + dbName + "'");
	       e.printStackTrace();
	     } finally {
	       try { conn.close(); }
	       catch (Throwable t){}
	     }
		return ent;
	 }		
}
