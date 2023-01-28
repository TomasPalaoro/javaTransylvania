package controlador;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import modelo.Habitacion;
import modelo.Reserva;
import modelo.Reserva_Habitacion;
import utils.ConexionBD;
import vista.VentanaPrincipal;

public class ControladorPrincipal implements ActionListener {

	VentanaPrincipal ventanaPrincipal;
	ConexionBD conexionBD;
	CardLayout cardLayout;

	public ControladorPrincipal(VentanaPrincipal ventanaPrincipal) {
		conexionBD = new ConexionBD();
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
			reservar();
			break;
		default:
			JOptionPane.showMessageDialog(null, "Hello world");
			break;
		}
	}

	@SuppressWarnings("unused")
	private boolean reservar() {
		/* PREPARAR RESERVA */
		String idUsuario = "j@j.com";
		String fechaEntrada = ventanaPrincipal.getDatePickerEntrada().getJFormattedTextField().getText();
		String fechaSalida = ventanaPrincipal.getDatePickerSalida().getJFormattedTextField().getText();
		if (fechaEntrada.equals("") || fechaSalida.equals("")) {
			JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), "Introduce las fechas de entrada y salida",
					"Error de campos de reserva", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		int adultos, ninyos;
		try {
			adultos = Integer.parseInt(ventanaPrincipal.getFormattedNumAdultos().getText());
			ninyos = Integer.parseInt(ventanaPrincipal.getFormattedNumNinyos().getText());
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), "Introduce el número de adultos y menores",
					"Error de campos de reserva", JOptionPane.WARNING_MESSAGE);
			return false;
		}		
		String fecha = fechaActual();
		Reserva reserva = new Reserva(fecha, fechaEntrada, fechaSalida, adultos, ninyos, idUsuario);
		System.out.println("NUEVA RESERVA: fechaEntrada: " + fechaEntrada + " fechaSalida: " + fechaSalida
				+ " adultos: " + adultos + " niños: " + ninyos + " idUsuario: " + idUsuario);
		// reserva.insert();
		int idReserva = conexionBD.reservaWhere(fecha);

		/* PREPARAR HABITACION */
		String nombreHabitacion = ventanaPrincipal.getComboBoxHabitaciones().getSelectedItem().toString();
		Habitacion habitacion = conexionBD.habitacionWhere(nombreHabitacion);

		/* RESERVA DE HABITACION */
		Reserva_Habitacion reserva_habitacion = new Reserva_Habitacion(habitacion.getId(), idReserva,
				habitacion.getCantidad(), habitacion.getPrecio());
		System.out.println("NUEVA RESERVA_HABITACION: idHabitacion: " + habitacion.getId() + " idReserva: "
				+ reserva.getId() + " cantidadHabitacion:" + habitacion.getCantidad() + " precioHabitacion"
				+ habitacion.getPrecio());
		// reserva_habitacion.insert();
		return true;
	}

	private String fechaActual() {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		String sinMilisegundos = timestamp.toString().split("\\.")[0];
		return sinMilisegundos;
	}

	public String[] cargarNombresHabitaciones() {
		ArrayList<Habitacion> habitaciones = conexionBD.obtenerTodasHabitaciones();
		if (habitaciones.size() > 0) {
			String[] nombres = new String[habitaciones.size()];
			;
			for (int i = 0; i < habitaciones.size(); i++) {
				nombres[i] = habitaciones.get(i).getNombre();
			}
			return nombres;
		} else {
			String[] nombres = { "Sin habitaciones disponibles" };
			return nombres;
		}
	}
}
