package vista;



import javax.swing.JFrame;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.Usuario;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;

public class VentanaPrincipal {

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
		String titulos[] = { "Email", "Contraseña"};
		String información[][] = informacionDeTabla();// obtenemos la informacion de la BD

		table = new JTable(información, titulos);
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);
	}

	private String[][] informacionDeTabla() {
		//Usuario usuarioDAO = new Usuario();

		ArrayList<Usuario> miLista = new ArrayList<Usuario>();
		miLista.add(new Usuario("paco","1234"));
		miLista.add(new Usuario("paco2","1234"));
		miLista.add(new Usuario("paco3","1234"));
		miLista.add(new Usuario("paco4","1234"));
		miLista.add(new Usuario("paco5","1234"));

		String informacion[][] = new String[miLista.size()][2];

		for (int x = 0; x < informacion.length; x++) {
			informacion[x][0] = miLista.get(x).getEmail() + "";
			informacion[x][1] = miLista.get(x).getPassword() + "";
		}
		return informacion;
	}


	private void mostrarDatosConTableModel() {
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		model.addColumn("Nº Documento");
		model.addColumn("Nombre");
		model.addColumn("Edad");
		model.addColumn("Profesión");
		model.addColumn("Telefono");

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);

		scrollPane.setViewportView(table);
	}

}
