package vista;

import com.formdev.flatlaf.FlatDarkLaf;

/**
 * Clase main que instancia la primera vista
 * @author Tomas
 *
 */
public class Main {
	
	static VentanaLogin ventanaLogin;
	static VentanaPrincipal ventanaPrincipal;
	
	public static void main(String[] args) {
		 try {
			 FlatDarkLaf.setup();
			 empezarEnLogin();		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void empezarEnLogin() {
		ventanaLogin = new VentanaLogin();
		ventanaLogin.getFrame().setVisible(true);
	}
	
	public static void empezarEnPrincipal() {
		ventanaPrincipal = new VentanaPrincipal();
		ventanaPrincipal.getFrame().setVisible(true);
	}
}
