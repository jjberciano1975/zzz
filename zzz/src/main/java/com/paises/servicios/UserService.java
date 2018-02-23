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

import org.glassfish.jersey.server.model.ParamQualifier;

import com.paises.entities.User;
import com.paises.persistence.UserCrud;



@Path("user")
public class UserService {
	
	@Context
	private UriInfo context; 

	public UserService() {
   }

	@DELETE
	public void deleteById(){
		 
		UserCrud.cargalista("D","","","",0);
	}
	
	
	@POST 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("text/html")
	@Path("/add")

	public void create(
			@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("password") String password	) {
		int iduser=0;
		UserCrud.cargalista("C",name,email,password,iduser);
		
	}
	
	@POST 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("text/html")	
	@Path("/hlogin")
	public String hlogin(
			
		/*	@PathParam("password")	 String email,
			@PathParam("password") String password*/
			@FormParam("email") String email,
			@FormParam("password") String password) {
		
		System.out.println("hlogin: email"+email+"/password"+password);
		String ses_id=UserCrud.hacerlogin(email,password);
		System.out.println("vuelve"+ses_id);
		return ses_id;		
	}	
	
	
	@POST 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("text/html")	
	@Path("/rlogin")
	public String rlogin(
			
		/*	@PathParam("password")	 String email,
			@PathParam("password") String password*/
			@FormParam("sesion_logada") String sesion_logada
			) {
		
		System.out.println("rlogin: sesion_logada"+sesion_logada);
		//String ses_id=UserCrud.hacerlogin(email,password);
		System.out.println("vuelve sesion_logada"+sesion_logada);
		return sesion_logada;		
	}		
	
	@POST 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("text/html")
	@Path("/login")

	public void login(
			@FormParam("name") String name,
			@FormParam("email") String email,
			@FormParam("password") String password	) {
		
		System.out.println("email"+email+"/password"+password);
		String ses_id=UserCrud.hacerlogin(email,password);
		System.out.println("vuelve"+ses_id);		
	}	

	
	
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces("text/html")
	@Path("/del")

	public void delete(
			@FormParam("marca") String marca,
			@FormParam("modelo") String modelo,
			@FormParam("password") String password,
			@FormParam("iduser") int iduser	) {
		  System.out.println("pre delete "+iduser);
		  UserCrud.cargalista("D",marca,modelo,password,iduser);
		
	}	
	
	@SuppressWarnings("null")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public List<User> getUser(@PathParam("param") int iduser ) {
	
		List<User> user = new ArrayList<User>();
		
	//	 int retval = coches.size();
	 //    System.out.println("Size of list = " + retval); 
		user =UserCrud.cargalista("S","","","", 0);
			 // retval = coches.size();
		    //  System.out.println("Size of list = " + retval); 
		      
		    
			return user;
		
	}

}




