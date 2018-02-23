package com.paises.servicios;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.paises.entities.Partidas;
import com.paises.persistence.GeneralCrud;
import com.paises.persistence.PartidasCrud;

@Path("partidas")
public class PartidasService {
	@Context
	private UriInfo context; 

	public PartidasService() {
   }
	
	
	@SuppressWarnings("null")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<Partidas> getCochesr(@PathParam("param") int iduser ) {
	
		List<Partidas> partidas = new ArrayList<Partidas>();
		

		//	 int retval = coches.size();
	 //    System.out.println("Size of list = " + retval); 
		partidas =PartidasCrud.cargalista("S",0,0,null,0,"");
			 // retval = coches.size();
		    //  System.out.println("Size of list = " + retval); 
		      
		    
			return partidas;
		
	}
	
	
	@SuppressWarnings("null")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sql")

	
	public void getsql( ) {
		System.out.println("GeneralCrud.accion(); ");
		GeneralCrud.accion();
	
	
		
		
	}

}
