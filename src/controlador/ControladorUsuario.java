package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelo.Usuario;
import utils.ConexionBD;
import vista.VentanaLogin;
import vista.VentanaPrincipal;

public class ControladorUsuario implements ActionListener {
	
	Usuario usuario;
	VentanaLogin ventanaLogin;
	ConexionBD conexionBD;

	public ControladorUsuario() {
		conexionBD = new ConexionBD();
	}
	public ControladorUsuario(VentanaLogin ventanaLogin) {
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
	
	public void cambiarVentana() {
		try {
			ventanaLogin.getFrame().dispose();
			
			VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
			ventanaPrincipal.getFrame().setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String[][] informacionDeTabla(int tamano) {
		//Usuario usuarioDAO = new Usuario();

		ArrayList<Usuario> miLista = conexionBD.obtenerTodosUsuarios();

		String informacion[][] = new String[miLista.size()][tamano];

		for (int x = 0; x < informacion.length; x++) {
			informacion[x][0] = miLista.get(x).getEmail() + "";
			informacion[x][1] = miLista.get(x).getNombre() + "";
			informacion[x][2] = miLista.get(x).getPassword() + "";
		}
		return informacion;
	}

}
