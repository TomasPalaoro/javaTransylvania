package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import main.Main;
import modelo.Usuario;

public class ControladorUsuario implements ActionListener {
	
	Usuario usuario;
	JTextField tfUsername, tfPassword;

	public ControladorUsuario() {}
	public ControladorUsuario(JTextField tfUsername, JTextField tfPassword) {
		this.tfUsername = tfUsername;
		this.tfPassword = tfPassword;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "LOGIN":
			String username = tfUsername.getText();
			String password = tfPassword.getText();
			System.out.println(username+" "+password);
			usuario.login(username,password);
			Main.cambiarVentana();
			break;
		default:
			JOptionPane.showMessageDialog(null, "Hello world");
			break;
		}
	}

}
