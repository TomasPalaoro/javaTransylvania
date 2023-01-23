package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Usuario;
import vista.VentanaLogin;
import vista.VentanaPrincipal;

public class ControladorUsuario implements ActionListener {
	
	Usuario usuario;
	VentanaLogin ventanaLogin;

	public ControladorUsuario() {}
	public ControladorUsuario(VentanaLogin ventanaLogin) {
		usuario = new Usuario();
		this.ventanaLogin = ventanaLogin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "LOGIN":
			String username = ventanaLogin.getTfEmail().getText();
			String password = ventanaLogin.getTfContrasena().getText();

			//COMPROBAR LOGIN
			if (usuario.login(username,password)) cambiarVentana();		
			
			break;
		default:
			JOptionPane.showMessageDialog(null, "Hello world");
			break;
		}
	}
	
	public void cambiarVentana() {
		try {
			ventanaLogin.getFrame().dispose();
			VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
			ventanaPrincipal.getFrame().setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
