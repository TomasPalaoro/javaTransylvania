package vista;



import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JTable;

import controlador.ControladorUsuario;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class VentanaPrincipal {

	ControladorUsuario controladorUsuario = new ControladorUsuario();
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public VentanaPrincipal() {
		initialize();
		crearTabla();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(54, 19, 81));

		scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public void crearTabla() {
		String titulos[] = { "Email", "Nombre", "Contraseña"};
		String información[][] = controladorUsuario.informacionDeTabla(titulos.length);

		table = new JTable(información, titulos);
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);
	}

	
}
