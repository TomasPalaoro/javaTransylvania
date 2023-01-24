package modelo;

import java.io.IOException;
import utils.PeticionHTTP;

public class Usuario {

	private static final String POST_URL = "http://localhost/gestionhotelera/sw_user.php";
	
	public boolean login(String username, String password) {
		try {
			if (PeticionHTTP.loginPOST(username,password, POST_URL)) {
				System.out.println("LOGGEADO CORRECTAMENTE");
				return true;
			}
			else {
				System.out.println("INCORRECTO");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
