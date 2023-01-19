package main;

import vista.*;

public class Main {
	
	static VentanaLogin ventanaLogin;
	static VentanaPrincipal ventanaPrincipal;
	
	public static void main(String[] args) {
		 try {
			ventanaLogin = new VentanaLogin();
			ventanaLogin.getFrame().setVisible(true);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void cambiarVentana() {
		try {
			ventanaLogin.getFrame().dispose();
			ventanaPrincipal = new VentanaPrincipal();
			ventanaPrincipal.getFrame().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
