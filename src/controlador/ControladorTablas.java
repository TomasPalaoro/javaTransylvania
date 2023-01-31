package controlador;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
	int camposPorPagina = 3;
	int primerRegistroMostrado = 0;

	public ControladorTablas(VentanaPrincipal ventanaPrincipal) {
		conexionBD = ConexionBD.getInstance();
		listaUsers = conexionBD.obtenerTodosUsuarios();
		listaReservas = conexionBD.obtenerTodasReservas();
		this.ventanaPrincipal = ventanaPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "SIGUIENTE":
			ventanaPrincipal.getBtnAnterior().setEnabled(true);
			if ((primerRegistroMostrado+camposPorPagina) >= (listaUsers.size()-1)) {
				//LIMITE FINAL
				primerRegistroMostrado = listaUsers.size()-camposPorPagina;
				System.out.println("sobrepasa");
				ventanaPrincipal.getBtnSiguiente().setEnabled(false);
			}
			else {
				//AVANZA
				primerRegistroMostrado = primerRegistroMostrado + camposPorPagina;
			}
			crearTabla("USUARIO");
			break;
		case "ANTERIOR":
			ventanaPrincipal.getBtnSiguiente().setEnabled(true);
			if ((primerRegistroMostrado-camposPorPagina) < 0) {
				//LIMITE PRINCIPIO
				primerRegistroMostrado = 0;
				System.out.println("menos");
				ventanaPrincipal.getBtnAnterior().setEnabled(false);
			} else {
				//RETROCEDE
				primerRegistroMostrado = primerRegistroMostrado - camposPorPagina;
			}
			crearTabla("USUARIO");
			break;
		default:
			JOptionPane.showMessageDialog(null, "Hello world");
			break;
		}
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
				informacionReserva[x][0] = listaReservas.get(x).getFecha_entrada() + "";
				informacionReserva[x][1] = listaReservas.get(x).getFecha_salida() + "";
				informacionReserva[x][2] = listaReservas.get(x).getNumero_adultos() + "";
				informacionReserva[x][3] = listaReservas.get(x).getNumero_ninyos() + "";
			}
			
			scrollPane = ventanaPrincipal.getScrollPaneReservas();
			table = new JTable(informacionReserva, titulosReserva);
			
			break;			
		default:
			break;
		}		
		
		estilizarTabla(table);
		scrollPane.setViewportView(table);
	}
	
	private void estilizarTabla(JTable table) {
		table.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setEnabled(false);
	}
}
