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
		case "CREARRESERVA":
			reservar();
			break;
		case "CREARUSUARIO":
			registrarUsuario();
			break;
		case "LOGOUT":
			cerrarSesion();
			break;
		default:
			JOptionPane.showMessageDialog(null, "Hello world");
			break;
		}
	}
	
	private void cambiarPestana(String panel) {
		ventanaPrincipal.getControladorTablas().resetearPaginas();
		
		cardLayout = (CardLayout) ventanaPrincipal.getPanelCard().getLayout();
		cardLayout.show(ventanaPrincipal.getPanelCard(), panel);
	}
	
	private boolean registrarUsuario() {
		int input = JOptionPane.showConfirmDialog(ventanaPrincipal.getFrame(), 
                "¿Deseas registrar el nuevo usuario?", "Registrar usuario", JOptionPane.YES_NO_CANCEL_OPTION);
		if (input == 0) {
			Usuario nuevoUsuario = new Usuario();
			try {
				nuevoUsuario.setEmail(ventanaPrincipal.getTfNuevoEmail().getText());
				nuevoUsuario.setPassword(ventanaPrincipal.getTfNuevaPass().getText());
				nuevoUsuario.setNombre(ventanaPrincipal.getTfNuevoNombre().getText());
				nuevoUsuario.setApellidos(ventanaPrincipal.getTfNuevoApellido().getText());
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), e2.getMessage(),
						"Error en campos de usuario", JOptionPane.WARNING_MESSAGE);
				return false;
			}
			if (nuevoUsuario.insert()) 	{
				JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), "Usuario creado exitosamente");

				ventanaPrincipal.getControladorTablas().resetearPaginas();
				return true;
			}
			else {
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
			nuevaReserva.setNumero_adultos(Integer.parseInt(ventanaPrincipal.getFormattedNumAdultos().getText()));
			nuevaReserva.setNumero_ninyos(Integer.parseInt(ventanaPrincipal.getFormattedNumNinyos().getText()));
			nuevaReserva.setFecha(fecha);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), "Introduzca número de adultos y menores",
					"Error de campos de reserva", JOptionPane.WARNING_MESSAGE);
			return false;		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), e.getMessage(),
					"Error de campos de reserva", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		System.out.println("NUEVA RESERVA: fechaEntrada: " + nuevaReserva.getFecha_entrada() + " fechaSalida: " + nuevaReserva.getFecha_salida()
				+ " adultos: " + nuevaReserva.getNumero_adultos() + " niños: " + nuevaReserva.getNumero_ninyos() + " idUsuario: " + nuevaReserva.getUser_id());
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
		// reserva_habitacion.insert();
		
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
