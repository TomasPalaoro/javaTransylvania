package controlador;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import conexion.ConexionBD;
import modelo.Habitacion;
import modelo.Reserva;
import modelo.Reserva_Habitacion;
import modelo.Usuario;
import vista.VentanaLogin;
import vista.VentanaPrincipal;

public class ControladorPrincipal implements ActionListener {

	VentanaPrincipal ventanaPrincipal;
	ConexionBD conexionBD;
	CardLayout cardLayout;

	public ControladorPrincipal(VentanaPrincipal ventanaPrincipal) {
		conexionBD = ConexionBD.getInstance();
		this.ventanaPrincipal = ventanaPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "GOTOCREARRESERVA":
			cambiarPestana("panelCardCrearReserva");
			break;
		case "GOTOUSERS":
			cambiarPestana("panelCardUsuarios");
			break;
		case "GOTORESERVAS":
			cambiarPestana("panelCardReservas");
			break;
		case "GOTOHABITACIONES":
			cambiarPestana("panelCardHabitaciones");
			break;
		case "CREARRESERVA":
			reservar();
			break;
		case "CREARUSUARIO":
			registrarUsuario();
			break;
		case "CREARHABITACION":
			nuevaHabitacion();
			break;
		case "LOGOUT":
			cerrarSesion();
			break;
		default:
			JOptionPane.showMessageDialog(null, "Hello world");
			break;
		}
	}
	
	private boolean nuevaHabitacion() {
		Habitacion nuevaHabitacion = new Habitacion(); 
		try {
			String tfNombre, tfDescripcion, tfPrecio, tfNumMaxPersonas, tfNumCamas;
			tfNombre=ventanaPrincipal.getTfNuevoHabitacionNombre().getText();
			tfDescripcion=ventanaPrincipal.getTfNuevoHabitacionDescripcion().getText();
			//tfCantidad TODO
			tfPrecio=ventanaPrincipal.getTfNuevaHabitacionPrecio().getText();
			tfNumMaxPersonas=ventanaPrincipal.getTfNuevoHabitacionMaxPersonas().getText();
			tfNumCamas=ventanaPrincipal.getTfNuevoHabitacionNumCamas().getText();
			nuevaHabitacion.setNombre(tfNombre);
			nuevaHabitacion.setDescripcion(tfDescripcion);
			nuevaHabitacion.setPrecio(tfPrecio);
			nuevaHabitacion.setNumero_maximo_personas(tfNumMaxPersonas);
			nuevaHabitacion.setNumero_camas(tfNumCamas);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), e2.getMessage(), "Error en campos de habitación",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		int input = JOptionPane.showConfirmDialog(ventanaPrincipal.getFrame(), 
                "¿Deseas crear la nueva habitación?", "Crear habitación", JOptionPane.YES_NO_CANCEL_OPTION);
		if (input == 0) {
			if (nuevaHabitacion.insert()) {
				JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), "Habitación creada exitosamente");
				ventanaPrincipal.getControladorTablas().resetearPaginas();
				ventanaPrincipal.getTfNuevoHabitacionNombre().setText("");
				ventanaPrincipal.getTfNuevoHabitacionDescripcion().setText("");
				ventanaPrincipal.getTfNuevaHabitacionPrecio().setText("");
				ventanaPrincipal.getTfNuevoHabitacionMaxPersonas().setText("");
				ventanaPrincipal.getTfNuevoHabitacionNumCamas().setText("");
				return true;
			}
			else {
			JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), "Error al crear habitación",
					"Error al crear habitación", JOptionPane.WARNING_MESSAGE);
			return false;
			}
		}
		else return false;
	}

	private void cambiarPestana(String panel) {
		ventanaPrincipal.getControladorTablas().resetearPaginas();

		cardLayout = (CardLayout) ventanaPrincipal.getPanelCard().getLayout();
		cardLayout.show(ventanaPrincipal.getPanelCard(), panel);
	}

	private boolean registrarUsuario() {

		Usuario nuevoUsuario = new Usuario();
		try {
			String tfEmail, tfPass, tfNombre, tfApellidos, tfTelefono;
			tfEmail=ventanaPrincipal.getTfNuevoEmail().getText();
			tfPass=ventanaPrincipal.getTfNuevaPass().getText();
			tfNombre=ventanaPrincipal.getTfNuevoNombre().getText();
			tfApellidos=ventanaPrincipal.getTfNuevoApellido().getText();
			tfTelefono=ventanaPrincipal.getTfNuevoTelefono().getText();
			nuevoUsuario.setEmail(tfEmail);
			nuevoUsuario.setPassword(tfPass);
			if (!tfNombre.equals("")) nuevoUsuario.setNombre(tfNombre);
			if (!tfApellidos.equals("")) nuevoUsuario.setApellidos(tfApellidos);
			if (!tfTelefono.equals("")) nuevoUsuario.setTelefono(tfTelefono);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), e2.getMessage(), "Error en campos de usuario",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		int input = JOptionPane.showConfirmDialog(ventanaPrincipal.getFrame(), 
                "¿Deseas registrar el nuevo usuario?", "Registrar usuario", JOptionPane.YES_NO_CANCEL_OPTION);
		if (input == 0) {
			if (nuevoUsuario.insert()) {
				JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), "Usuario creado exitosamente");
				ventanaPrincipal.getControladorTablas().resetearPaginas();
				ventanaPrincipal.getTfNuevoEmail().setText("");
				ventanaPrincipal.getTfNuevaPass().setText("");
				ventanaPrincipal.getTfNuevoNombre().setText("");
				ventanaPrincipal.getTfNuevoApellido().setText("");
				ventanaPrincipal.getTfNuevoTelefono().setText("");
				return true;
			} else {
				JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), "Error al crear usuario",
						"Error al crear usuario", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		else return false;
	}

	private void cerrarSesion() {
		try {
			ventanaPrincipal.getFrame().dispose();

			VentanaLogin ventanaLogin = new VentanaLogin();
			ventanaLogin.getFrame().setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private boolean reservar() {
		/* PREPARAR RESERVA */
		Reserva nuevaReserva = new Reserva();
		String fecha = fechaActual();
		try {
			nuevaReserva.setUser_id(ventanaPrincipal.getTfUsuario().getText());
			nuevaReserva.setFecha_entrada(ventanaPrincipal.getDatePickerEntrada().getJFormattedTextField().getText());
			nuevaReserva.setFecha_salida(ventanaPrincipal.getDatePickerSalida().getJFormattedTextField().getText());
			nuevaReserva.compararFechas();
			nuevaReserva.setNumero_adultos(ventanaPrincipal.getFormattedNumAdultos().getText());
			nuevaReserva.setNumero_ninyos(ventanaPrincipal.getFormattedNumNinyos().getText());
			nuevaReserva.setFecha(fecha);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), "Introduzca número de adultos y menores",
					"Error de campos de reserva", JOptionPane.WARNING_MESSAGE);
			return false;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), e.getMessage(), "Error de campos de reserva",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		System.out.println("NUEVA RESERVA: fechaEntrada: " + nuevaReserva.getFecha_entrada() + " fechaSalida: "
				+ nuevaReserva.getFecha_salida() + " adultos: " + nuevaReserva.getNumero_adultos() + " niños: "
				+ nuevaReserva.getNumero_ninyos() + " idUsuario: " + nuevaReserva.getUser_id());
		nuevaReserva.insert();
		int idReserva = conexionBD.reservaWhere(fecha);

		/* PREPARAR HABITACION */
		String nombreHabitacion = ventanaPrincipal.getComboBoxHabitaciones().getSelectedItem().toString();
		Habitacion habitacion = conexionBD.habitacionWhere(nombreHabitacion);

		/* RESERVA DE HABITACION */
		Reserva_Habitacion reserva_habitacion = new Reserva_Habitacion(habitacion.getId(), idReserva,
				habitacion.getCantidad(), habitacion.getPrecio());
		System.out.println("NUEVA RESERVA_HABITACION: idHabitacion: " + habitacion.getId() + " idReserva: "
				+ nuevaReserva.getId() + " cantidadHabitacion:" + habitacion.getCantidad() + " precioHabitacion"
				+ habitacion.getPrecio());
		reserva_habitacion.insert();

		JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), "Reserva creada exitosamente");

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
