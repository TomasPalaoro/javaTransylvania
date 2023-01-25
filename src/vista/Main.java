package vista;

public class Main {
	
	static VentanaLogin ventanaLogin;
	static VentanaPrincipal ventanaPrincipal;
	
	public static void main(String[] args) {
		 try {
			 /*
			ventanaLogin = new VentanaLogin();
			ventanaLogin.getFrame().setVisible(true);	*/		
			ventanaPrincipal = new VentanaPrincipal();
			ventanaPrincipal.getFrame().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
