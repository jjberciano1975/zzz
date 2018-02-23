package com.paises.persistence;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.paises.entities.User;

public class UserCrud {
	 private static List<User> ent = new ArrayList<User>();
	 public static String  hacerlogin (String email, String password) {
		 String ses_id="";
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
    	 
    	 conn = DriverManager.getConnection(connectionURL);
    	 System.out.println ("voy a hacer login:"+email );
       

     Statement statement = conn.createStatement();
	 ResultSet rs = statement.executeQuery("select name,email,password,iduser from users where email='"+email+"' and password='"+password+"'");		       			  
     if (rs.next()) 
     { 
        
  	   User u = new User(rs.getString (1),rs.getString (2),rs.getString (3),rs.getInt(4));
  	   System.out.println ("ha hecho login:" + u.getEmail());
  	/*  String sqlCreateTableUsers ="drop TABLE SESION";
  	  statement.execute(sqlCreateTableUsers);
  	sqlCreateTableUsers=
  			    "CREATE TABLE SESION ( " +
	                  
	                  "SES_ID  VARCHAR(10) NOT NULL CONSTRAINT SESION_PK PRIMARY KEY, " +
	                  "EMAIL VARCHAR(30), " +
	                  "FECHAINI TIMESTAMP,  " +
	                  "FECHAFIN TIMESTAMP   " +
	                  ")";
	    	  statement.execute(sqlCreateTableUsers);
	    	  */
	    	  ses_id =fu_creases();
	    	 
	    	 statement.execute("update SESION set FECHAFIN=CURRENT_TIMESTAMP where email='"+u.getEmail()+"' and FECHAFIN is null ");
	    	 statement.execute("insert into SESION values ('"+ses_id+"','"+u.getEmail()+"',CURRENT_TIMESTAMP,null)");
	    	 System.out.println ("ha registrado la sesion :" + ses_id);
     }
     
     }  catch (Throwable e)  {
       System.out.println("Error en la bd '" + dbName + "'");
       e.printStackTrace();
     } finally {
       try { conn.close(); }
       catch (Throwable t){}
     }
		
		 return ses_id;
	 }
	 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
	 public static List<User>  cargalista(String accion, String name , String email, String password, int iduser) {
		
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
	    	 System.out.println("accion user "+accion); 
	    	 conn = DriverManager.getConnection(connectionURL);
	      
	       
	
	     Statement statement = conn.createStatement();
	     if (accion=="S"){
	    	  /*String sqlCreateTableUsers ="drop table USERS";
	    	  statement.execute(sqlCreateTableUsers);
	    	  sqlCreateTableUsers=
	    			  "CREATE TABLE USERS ( " +
	                  "NAME VARCHAR(30) NOT NULL, " +
	                  "EMAIL VARCHAR(30) NOT NULL NOT NULL CONSTRAINT users_PK PRIMARY KEY, " +
	                  "PASSWORD VARCHAR(30) NOT NULL, " +
	                  "IDUSER INTEGER  " +
	                  ")";
	    	  statement.execute(sqlCreateTableUsers);
	    	 
	    	  statement.executeUpdate("delete from USERS");
	    	 
	    	  statement.executeUpdate("INSERT INTO USERS VALUES('JAVIER JB', 'JJBERCIANO1975@YAHOO.ES','AAA', 2)");     
  */
	    	  
	    	 ent.clear();	
	    	 ResultSet rs = statement.executeQuery("select name,email,password,iduser from users");		       			  
		       while (rs.next()) 
		       { 
		           //System.out.println (rs.getString (1) + " " + rs.getString (2)+ " " + rs.getInt(3));
		    	   User u = new User(rs.getString (1),rs.getString (2),rs.getString (3),rs.getInt(4)); 
		    	   ent.add(u);
		    	
		       }
		     
		       
	     }  //if (accion=="G"){ 
	     
	     else if (accion=="C"){
	    	 //System.out.println("insert "+marca+" , "+modelo+","+iduser);
	    	 statement.executeUpdate("INSERT INTO users (name,email,password,iduser)VALUES('"+name+"' , '"+email+"', '"+password+"',"+iduser+")");
	    	 
	     }  //else if (accion=="C"){
	   	     
	     else if (accion=="D"){
	    	  
	    	 statement.executeUpdate("delete from users where iduser="+iduser); 
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
	 private static String fu_creases() {
		 String ses = "";
		 for (int i=0;i<10;i++){
			 int valorDado = (int) Math.floor(Math.random()*9+1);
			 ses=ses+valorDado;
		 }
		 return ses;
	 }
}