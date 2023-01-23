package main;

import vista.*;

public class Main {
	
	static VentanaLogin ventanaLogin;
	
	public static void main(String[] args) {
		 try {
			ventanaLogin = new VentanaLogin();
			ventanaLogin.getFrame().setVisible(true);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
