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
	ConexionBD conexionBD;

	public ControladorLogin(VentanaLogin ventanaLogin) {
		conexionBD = ConexionBD.getInstance();
		this.ventanaLogin = ventanaLogin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "LOGIN":
			String username = ventanaLogin.getTfEmail().getText();
			String password = ventanaLogin.getTfContrasena().getText();
			
			if (username.equals("") || password.equals("")) {
				JOptionPane.showMessageDialog(ventanaLogin.getFrame(), "Rellena los campos",
						"Error de inicio de sesión", JOptionPane.WARNING_MESSAGE);
			}else {
				usuario = new Usuario(username,password);

				//COMPROBAR LOGIN
				if (usuario.login()) cambiarVentana();		
				else {
					JOptionPane.showMessageDialog(ventanaLogin.getFrame(), "Usuario o contraseña incorrectos",
							"Error de inicio de sesión", JOptionPane.WARNING_MESSAGE);
				}
			}			
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
