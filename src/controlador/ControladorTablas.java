package controlador;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;

import conexion.ConexionBD;
import modelo.Reserva;
import modelo.Usuario;
import vista.VentanaPrincipal;

public class ControladorTablas implements ActionListener {
	
	Usuario usuario;
	VentanaPrincipal ventanaPrincipal;
	ConexionBD conexionBD;
	CardLayout cardLayout;
	
	ArrayList<Usuario> listaUsers;
	ArrayList<Reserva> listaReservas;
	public int camposPorPagina;
	public int primerRegistroMostrado;

	public ControladorTablas(VentanaPrincipal ventanaPrincipal) {
		conexionBD = ConexionBD.getInstance();
		listaUsers = conexionBD.obtenerTodosUsuarios();
		listaReservas = conexionBD.obtenerTodasReservas();
		camposPorPagina = 3;
		primerRegistroMostrado = 0;
		this.ventanaPrincipal = ventanaPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "SIGUIENTEUSUARIO":
			paginar("USUARIO","AVANZAR");
			break;
		case "SIGUIENTERESERVA":
			paginar("RESERVA","AVANZAR");
			break;
		case "ANTERIORUSUARIO":
			paginar("USUARIO","RETROCEDER");
			break;
		case "ANTERIORRESERVA":
			paginar("RESERVA","RETROCEDER");
			break;
		case "PRIMERUSUARIO":
			paginar("USUARIO","PRINCIPIO");
			break;
		case "PRIMERARESERVA":
			paginar("RESERVA","PRINCIPIO");
			break;
		case "ULTIMOUSUARIO":
			paginar("USUARIO","FIN");
			break;
		case "ULTIMARESERVA":
			paginar("RESERVA","FIN");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Hello world");
			break;
		}
	}
	
	public void resetearPaginas() {
		camposPorPagina = 3;
		primerRegistroMostrado = 0;
		ventanaPrincipal.getBtnBackUser().setEnabled(false);
		ventanaPrincipal.getBtnBackReserva().setEnabled(false);
		ventanaPrincipal.getBtnLastUser().setEnabled(true);
		ventanaPrincipal.getBtnLastReserva().setEnabled(true);
		ventanaPrincipal.getBtnFirstUser().setEnabled(false);
		ventanaPrincipal.getBtnFirstReserva().setEnabled(false);
		ventanaPrincipal.getBtnNextUser().setEnabled(true);
		ventanaPrincipal.getBtnNextReserva().setEnabled(true);
		crearTabla("USUARIO");
		crearTabla("RESERVA");
	}
	
	private void paginar(String modelo, String accion) {
		ArrayList<?> arrayModelo = null;
		JButton botonBack = null;
		JButton botonNext = null;
		JButton botonFirst = null;
		JButton botonLast = null;
		switch (modelo) {
		case "USUARIO":
			arrayModelo = listaUsers;
			botonNext = ventanaPrincipal.getBtnNextUser();
			botonBack = ventanaPrincipal.getBtnBackUser();
			botonFirst = ventanaPrincipal.getBtnFirstUser();
			botonLast = ventanaPrincipal.getBtnLastUser();
			break;
		case "RESERVA":
			arrayModelo = listaReservas;
			botonNext = ventanaPrincipal.getBtnNextReserva();
			botonBack = ventanaPrincipal.getBtnBackReserva();
			botonFirst = ventanaPrincipal.getBtnFirstReserva();
			botonLast = ventanaPrincipal.getBtnLastReserva();
			break;
		}
		switch (accion) {
		case "PRINCIPIO":
			botonNext.setEnabled(true);
			botonLast.setEnabled(true);
			primerRegistroMostrado = 0;
			botonBack.setEnabled(false);
			botonFirst.setEnabled(false);
			break;
		case "FIN":
			botonBack.setEnabled(true);
			botonFirst.setEnabled(true);
			primerRegistroMostrado = arrayModelo.size()-camposPorPagina;
			botonNext.setEnabled(false);
			botonLast.setEnabled(false);
			break;
		case "AVANZAR":
			botonFirst.setEnabled(true);
			botonBack.setEnabled(true);
			if ((primerRegistroMostrado+camposPorPagina) >= (arrayModelo.size()-1)) {
				//LIMITE FINAL
				primerRegistroMostrado = arrayModelo.size()-camposPorPagina;
				botonNext.setEnabled(false);
				botonLast.setEnabled(false);
			}
			else {
				//AVANZA
				primerRegistroMostrado = primerRegistroMostrado + camposPorPagina;
			}
			break;
		case "RETROCEDER":
			botonNext.setEnabled(true);
			botonLast.setEnabled(true);
			if (((primerRegistroMostrado-1)-camposPorPagina) < 0) {
				//LIMITE PRINCIPIO
				primerRegistroMostrado = 0;
				botonFirst.setEnabled(false);
				botonBack.setEnabled(false);
			} else {
				//RETROCEDE
				primerRegistroMostrado = primerRegistroMostrado - camposPorPagina;
			}
			break;
		}
		//refrescar
		crearTabla(modelo);
	}
	
	public void crearTabla(String modelo) {
		JScrollPane scrollPane = null;
		JTable table = null;
		
		switch (modelo) {
		case "USUARIO":
			if (camposPorPagina > listaUsers.size()) {
				camposPorPagina = listaUsers.size();
			}		
			String titulosUsers[] = { "Email", "Nombre", "Contraseña"};
			String informacionUsers[][] = new String[camposPorPagina][titulosUsers.length];
			
			for (int x = 0; x < informacionUsers.length; x++) {
				informacionUsers[x][0] = listaUsers.get(x+primerRegistroMostrado).getEmail() + "";
				informacionUsers[x][1] = listaUsers.get(x+primerRegistroMostrado).getNombre() + "";
				informacionUsers[x][2] = listaUsers.get(x+primerRegistroMostrado).getPassword() + "";
			}
			
			scrollPane = ventanaPrincipal.getScrollPane();
			table = new JTable(informacionUsers, titulosUsers);
			
			break;
			
		case "RESERVA":
			if (camposPorPagina > listaReservas.size()) {
				camposPorPagina = listaReservas.size();
			}		
			String titulosReserva[] = { "Fecha entrada", "Fecha salida", "Numero adultos", "Numero niños"};
			String informacionReserva[][] = new String[camposPorPagina][titulosReserva.length];
			
			for (int x = 0; x < informacionReserva.length; x++) {
				informacionReserva[x][0] = listaReservas.get(x+primerRegistroMostrado).getFecha_entrada() + "";
				informacionReserva[x][1] = listaReservas.get(x+primerRegistroMostrado).getFecha_salida() + "";
				informacionReserva[x][2] = listaReservas.get(x+primerRegistroMostrado).getNumero_adultos() + "";
				informacionReserva[x][3] = listaReservas.get(x+primerRegistroMostrado).getNumero_ninyos() + "";
			}
			
			scrollPane = ventanaPrincipal.getScrollPaneReservas();
			table = new JTable(informacionReserva, titulosReserva);
			
			
			break;			
		default:
			break;
		}		
		
		estilizarTabla(table);
		scrollPane.setViewportView(new JScrollPane(table));
		//scrollPane.setColumnHeaderView(ventanaPrincipal.getPanelHeaderReserva());
	}
	
	private void estilizarTabla(JTable table) {
		table.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setEnabled(false);
	}
	
}
