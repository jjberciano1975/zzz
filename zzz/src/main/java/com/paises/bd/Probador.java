package  com.paises.bd;

import java.util.List;


import com.paises.entities.Coches;
import com.paises.persistence.CochesCrud;




public class Probador {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/* List<Users> users =	UsersCrud.cargalista();
		 System.out.println (users);*/
		List<Coches> coches =	CochesCrud.cargalista("S","","",0);
		 System.out.println (coches);
	}
}
