package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import conexion.ConexionBD;
import modelo.Usuario;
import vista.VentanaLogin;
import vista.VentanaPrincipal;

public class ControladorLogin implements ActionListener {
	
	Usuario usuario;
	VentanaLogin ventanaLogin;
	VentanaPrincipal ventanaPrincipal;
	ConexionBD conexionBD;

	public ControladorLogin(VentanaLogin ventanaLogin) {
		conexionBD = new ConexionBD();
		this.ventanaLogin = ventanaLogin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "LOGIN":
			String username = ventanaLogin.getTfEmail().getText();
			String password = ventanaLogin.getTfContrasena().getText();
			
			usuario = new Usuario(username,password);

			//COMPROBAR LOGIN
			if (usuario.login()) cambiarVentana();		
			
			break;
		default:
			JOptionPane.showMessageDialog(null, "Hello world");
			break;
		}
	}
	
	private void cambiarVentana() {
		try {
			ventanaLogin.getFrame().dispose();
			
			VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
			ventanaPrincipal.getFrame().setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
