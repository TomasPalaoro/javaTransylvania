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
	int camposPorPagina = 3;
	int primerRegistroMostrado = 0;

	public ControladorTablas(VentanaPrincipal ventanaPrincipal) {
		conexionBD = ConexionBD.getInstance();
		listaUsers = conexionBD.obtenerTodosUsuarios();
		this.ventanaPrincipal = ventanaPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "SIGUIENTE":
			if ((primerRegistroMostrado+camposPorPagina) >= (listaUsers.size()-1)) {
				primerRegistroMostrado = listaUsers.size()-camposPorPagina;
				System.out.println("sobrepasa");
			}
			else {
				System.out.println("avanza");
				primerRegistroMostrado = primerRegistroMostrado + camposPorPagina;
			}
			crearTablaUsers();
			break;
		case "ANTERIOR":
			if ((primerRegistroMostrado-camposPorPagina) < 0) {
				primerRegistroMostrado = 0;
				System.out.println("menos");
			} else primerRegistroMostrado = primerRegistroMostrado - camposPorPagina;
			crearTablaUsers();
			break;
		default:
			JOptionPane.showMessageDialog(null, "Hello world");
			break;
		}
	}
	
	public void crearTablaUsers() {
		String titulos[] = { "Email", "Nombre", "Contraseña"};
		String información[][] = informacionDeTablaUsers(titulos.length);
		
		JScrollPane scrollPane = ventanaPrincipal.getScrollPane();
		JTable table = ventanaPrincipal.getTable();
		table = new JTable(información, titulos);
		estilizarTabla(table);
		scrollPane.setViewportView(table);
	}
	
	private String[][] informacionDeTablaUsers(int tamano) {
		
		if (camposPorPagina > listaUsers.size()) {
			camposPorPagina = listaUsers.size();
		}		
		String informacion[][] = new String[camposPorPagina][tamano];
		
		for (int x = 0; x < informacion.length; x++) {
			informacion[x][0] = listaUsers.get(x+primerRegistroMostrado).getEmail() + "";
			informacion[x][1] = listaUsers.get(x+primerRegistroMostrado).getNombre() + "";
			informacion[x][2] = listaUsers.get(x+primerRegistroMostrado).getPassword() + "";
		}
		
		System.out.println(listaUsers.size());
		System.out.println("campos: "+camposPorPagina+" registro: "+primerRegistroMostrado);
		
		
		
		return informacion;
	}
	
	private void estilizarTabla(JTable table) {
		table.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setEnabled(false);
	}
	
	public void crearTablaReservas() {
		String titulos[] = { "Fecha entrada", "Fecha salida", "Numero adultos", "Numero niños"};
		String información[][] = informacionDeTablaReservas(titulos.length);
		
		JScrollPane scrollPaneReservas = ventanaPrincipal.getScrollPaneReservas();
		JTable table = ventanaPrincipal.getTable();
		table = new JTable(información, titulos);
		estilizarTabla(table);
		scrollPaneReservas.setViewportView(table);
	}
	
	private String[][] informacionDeTablaReservas(int tamano) {
		ArrayList<Reserva> miLista = conexionBD.obtenerTodasReservas();
		
		String informacion[][] = new String[miLista.size()][tamano];
		
		for (int x = 0; x < informacion.length; x++) {
			informacion[x][0] = miLista.get(x).getFecha_entrada() + "";
			informacion[x][1] = miLista.get(x).getFecha_salida() + "";
			informacion[x][2] = miLista.get(x).getNumero_adultos() + "";
			informacion[x][3] = miLista.get(x).getNumero_ninyos() + "";
		}
		return informacion;
	}
}
