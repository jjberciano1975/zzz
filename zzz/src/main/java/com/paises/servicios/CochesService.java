package  com.paises.servicios;




import java.util.ArrayList;


import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


import com.paises.entities.Coches;
import com.paises.persistence.CochesCrud;



@Path("coches")
public class CochesService {
	
	@Context
	private UriInfo context; 

	public CochesService() {
   }

	@DELETE
	public void deleteById(){
		 
		CochesCrud.cargalista("D","","",0);
	}
	
	
	@POST 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("text/html")
	@Path("/add")

	public void create(
			@FormParam("marca") String marca,
			@FormParam("modelo") String modelo,
			@FormParam("iduser") int iduser	) {
		
		CochesCrud.cargalista("C",marca,modelo,iduser);
		
	}
	
	

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("text/html")
	@Path("/del")

	public void delete(
			@FormParam("marca") String marca,
			@FormParam("modelo") String modelo,
			@FormParam("iduser") int iduser	) {
		  System.out.println("pre delete "+iduser);
		CochesCrud.cargalista("D",marca,modelo,iduser);
		
	}	
	
	@SuppressWarnings("null")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<Coches> getCochesr(@PathParam("param") int iduser ) {
	
		List<Coches> coches = new ArrayList<Coches>();
		
	//	 int retval = coches.size();
	 //    System.out.println("Size of list = " + retval); 
		coches =CochesCrud.cargalista("S","","",0);
			 // retval = coches.size();
		    //  System.out.println("Size of list = " + retval); 
		      
		    
			return coches;
		
	}

}


