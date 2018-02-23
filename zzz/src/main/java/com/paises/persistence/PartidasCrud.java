package com.paises.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import com.paises.entities.Partidas;

public class PartidasCrud {
	 private static List<Partidas> ent= new ArrayList<Partidas>();
	 public static List<Partidas>  cargalista( String accion,int iduser,int codpart, Timestamp fechapart, int codequi, String equipo) {
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
   
    

  Statement statement = conn.createStatement();
  if (accion=="S"){
 	 
 	 ent.clear();	
 	 ResultSet rs = statement.executeQuery("select * from Partidas");		       			  
	       while (rs.next()) 
	       { 
	           //System.out.println (rs.getString (1) + " " + rs.getString (2)+ " " + rs.getInt(3));
	    	   Partidas u = new Partidas(rs.getInt (1),rs.getInt(2),rs.getTimestamp(3),rs.getInt(4),rs.getString (5)); 
	    	   ent.add(u);
	    	
	       }
	       
  }  //if (accion=="G"){ 
  
  else if (accion=="C"){
 	 //System.out.println("insert "+marca+" , "+modelo+","+iduser);
	 
 	 statement.executeUpdate("INSERT INTO Partidas VALUES("+iduser+","+codpart+","+fechapart+","+codequi+"'"+equipo+"')");
 	 
  }  //else if (accion=="C"){ 
  else if (accion=="D"){
 	  System.out.println("delete "+iduser);
 	 statement.executeUpdate("delete from Partidas where codpart="+codpart); 
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
