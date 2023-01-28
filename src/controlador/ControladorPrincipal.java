package controlador;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import modelo.Reserva;
import vista.VentanaPrincipal;

public class ControladorPrincipal implements ActionListener {
	
	Reserva reserva;
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
		case "CREARRESERVA":
			int adultos = Integer.parseInt(ventanaPrincipal.getFormattedNumAdultos().getText());
			int ninyos = Integer.parseInt(ventanaPrincipal.getFormattedNumNinyos().getText());
			String fechaEntrada = ventanaPrincipal.getDatePickerEntrada().getJFormattedTextField().getText();
			String fechaSalida = ventanaPrincipal.getDatePickerSalida().getJFormattedTextField().getText();
			reserva = new Reserva(fechaEntrada,fechaSalida,adultos,ninyos, "j@j.com");
			reserva.insert();
			break;
		default:
			JOptionPane.showMessageDialog(null, "Hello world");
			break;
		}
	}
}
