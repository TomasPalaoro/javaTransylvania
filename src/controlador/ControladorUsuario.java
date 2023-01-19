package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Main;
import modelo.Usuario;

public class ControladorUsuario implements ActionListener {
	
	Usuario usuario;

	@Override
	public void actionPerformed(ActionEvent e) {
		Main.cambiarVentana();
	}

}
