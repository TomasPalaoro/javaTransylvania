package vista;



import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JTable;

import controlador.ControladorReserva;
import controlador.ControladorUsuario;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal {

	ControladorUsuario controladorUsuario = new ControladorUsuario(this);
	ControladorReserva controladorReserva = new ControladorReserva(this);
	private JFrame frame;
	private JPanel panelCard;
	private JScrollPane scrollPane;
	private JTable table;
	private JMenuBar menuBar;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel;
	private JScrollPane scrollPaneReservas;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public VentanaPrincipal() {
		initialize();
		controladorUsuario.crearTabla();
		//controladorReserva.crearTabla();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(54, 19, 81));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		panelCard = new JPanel();
		frame.getContentPane().add(panelCard);
		panelCard.setLayout(new CardLayout(0, 0));
		
		scrollPane = new JScrollPane();
		panelCard.add(scrollPane, "panelCardUsuarios");
		
		table = new JTable();
		table.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		table.setEnabled(false);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBackground(new Color(128, 0, 128));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);
		
		lblNewLabel = new JLabel("New label");
		scrollPane.setRowHeaderView(lblNewLabel);
		
		scrollPaneReservas = new JScrollPane();
		panelCard.add(scrollPaneReservas, "panelCardReservas");
		
		menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		btnNewButton = new JButton("VER USUARIOS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) panelCard.getLayout();
				cardLayout.show(panelCard, "panelCardUsuarios");
			}
		});
		menuBar.add(btnNewButton);
		
		btnNewButton_1 = new JButton("VER RESERVAS");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout) panelCard.getLayout();
				cardLayout.show(panelCard, "panelCardReservas");
			}
		});
		menuBar.add(btnNewButton_1);
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}


	

	
}
