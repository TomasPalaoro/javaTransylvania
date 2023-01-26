package vista;



import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JTable;

import controlador.ControladorTablas;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JButton;

public class VentanaPrincipal {

	ControladorTablas controladorTablas = new ControladorTablas(this);
	private JFrame frame;
	private JPanel panelCard;
	private JScrollPane scrollPane;
	private JTable table;
	private JMenuBar menuBar;
	private JButton btnVerUsers;
	private JButton btnVerReservas;
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
		controladorTablas.crearTablaUsers();
		controladorTablas.crearTablaReservas();
		
		btnVerUsers.setActionCommand("GOTOUSERS");
		btnVerUsers.addActionListener(controladorTablas);
		btnVerReservas.setActionCommand("GOTORESERVAS");
		btnVerReservas.addActionListener(controladorTablas);
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
		
		btnVerUsers = new JButton("VER USUARIOS");
		menuBar.add(btnVerUsers);
		
		btnVerReservas = new JButton("VER RESERVAS");
		menuBar.add(btnVerReservas);
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public JPanel getPanelCard() {
		return panelCard;
	}

	public void setPanelCard(JPanel panelCard) {
		this.panelCard = panelCard;
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

	public JScrollPane getScrollPaneReservas() {
		return scrollPaneReservas;
	}

	public void setScrollPaneReservas(JScrollPane scrollPaneReservas) {
		this.scrollPaneReservas = scrollPaneReservas;
	}
	
}
