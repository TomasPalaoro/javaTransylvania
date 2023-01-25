package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelo.Reserva;
import utils.ConexionBD;
import vista.VentanaPrincipal;

public class ControladorReserva implements ActionListener {
	
	Reserva reserva;
	VentanaPrincipal ventanaPrincipal;
	ConexionBD conexionBD;

	public ControladorReserva() {
		conexionBD = new ConexionBD();
	}
	public ControladorReserva(VentanaPrincipal ventanaPrincipal) {
		conexionBD = new ConexionBD();
		this.ventanaPrincipal = ventanaPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "":
		default:
			JOptionPane.showMessageDialog(null, "Hello world");
			break;
		}
	}
	
	public void crearTabla() {
		String titulos[] = { "Fecha entrada", "Fecha salida", "Numero adultos", "Numero niños"};
		String información[][] = informacionDeTabla(titulos.length);
		
		JScrollPane scrollPane = ventanaPrincipal.getScrollPane();
		JTable table = ventanaPrincipal.getTable();
		table = new JTable(información, titulos);
		scrollPane.setViewportView(table);
	}
	
	private String[][] informacionDeTabla(int tamano) {
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
