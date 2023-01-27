package controlador;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import vista.VentanaPrincipal;

public class ControladorPrincipal implements ActionListener {
	
	VentanaPrincipal ventanaPrincipal;
	CardLayout cardLayout;

	public ControladorPrincipal(VentanaPrincipal ventanaPrincipal) {
		this.ventanaPrincipal = ventanaPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "GOTOCREARRESERVA":
			cardLayout = (CardLayout) ventanaPrincipal.getPanelCard().getLayout();
			cardLayout.show(ventanaPrincipal.getPanelCard(), "panelCardCrearReserva");
			break;
		case "GOTOUSERS":
			cardLayout = (CardLayout) ventanaPrincipal.getPanelCard().getLayout();
			cardLayout.show(ventanaPrincipal.getPanelCard(), "panelCardUsuarios");
			break;
		case "GOTORESERVAS":
			cardLayout = (CardLayout) ventanaPrincipal.getPanelCard().getLayout();
			cardLayout.show(ventanaPrincipal.getPanelCard(), "panelCardReservas");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Hello world");
			break;
		}
	}
}
