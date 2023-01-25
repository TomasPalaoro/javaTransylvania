package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelo.Usuario;
import utils.ConexionBD;
import vista.VentanaLogin;
import vista.VentanaPrincipal;

public class ControladorUsuario implements ActionListener {
	
	Usuario usuario;
	VentanaLogin ventanaLogin;
	VentanaPrincipal ventanaPrincipal;
	ConexionBD conexionBD;

	public ControladorUsuario() {
		conexionBD = new ConexionBD();
	}
	public ControladorUsuario(VentanaLogin ventanaLogin) {
		conexionBD = new ConexionBD();
		this.ventanaLogin = ventanaLogin;
	}
	public ControladorUsuario(VentanaPrincipal ventanaPrincipal) {
		conexionBD = new ConexionBD();
		this.ventanaPrincipal = ventanaPrincipal;
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
	
	public void crearTabla() {
		String titulos[] = { "Email", "Nombre", "Contraseña"};
		String información[][] = informacionDeTabla(titulos.length);
		
		JScrollPane scrollPane = ventanaPrincipal.getScrollPane();
		JTable table = ventanaPrincipal.getTable();
		table = new JTable(información, titulos);
		scrollPane.setViewportView(table);
	}
	
	private String[][] informacionDeTabla(int tamano) {
		ArrayList<Usuario> miLista = conexionBD.obtenerTodosUsuarios();
		
		String informacion[][] = new String[miLista.size()][tamano];
		
		for (int x = 0; x < informacion.length; x++) {
			informacion[x][0] = miLista.get(x).getEmail() + "";
			informacion[x][1] = miLista.get(x).getNombre() + "";
			informacion[x][2] = miLista.get(x).getPassword() + "";
		}
		return informacion;
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
