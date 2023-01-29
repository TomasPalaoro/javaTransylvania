package vista;



import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.JTable;

import controlador.ControladorPrincipal;
import controlador.ControladorTablas;
import utils.Colores;
import utils.DateLabelFormatter;
import utils.StyledButtonUI;
import utils.TextPrompt;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;
import javax.swing.text.NumberFormatter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.Font;
import java.text.NumberFormat;
import java.util.Properties;
import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class VentanaPrincipal {

	ControladorPrincipal controladorPrincipal = new ControladorPrincipal(this);
	ControladorTablas controladorTablas = new ControladorTablas(this);
	private JMenuItem mntmLogout;
	private JDatePickerImpl datePickerEntrada;
	private JDatePickerImpl datePickerSalida;
	private JButton btnCrearReserva;
	private JFrame frmHotelTransylvania;
	private JPanel panelCard;
	private JScrollPane scrollPane;
	private JTable table;
	private JMenuBar menuBar;
	private JButton btnVerUsers;
	private JButton btnVerReservas;
	private JLabel lblNewLabel;
	private JScrollPane scrollPaneReservas;
	private JButton btnVerCrearReserva;
	private JPanel groupCrearReserva;
	private JLabel lblSalida;
	private JLabel lblAdultos;
	private JLabel lblNinyos;
	private JComboBox<String> comboBoxHabitaciones;
	private JFormattedTextField formattedNumAdultos;
	private JFormattedTextField formattedNumNinyos;
	private JLabel lblUsuario;
	private JTextField tfUsuario;
	private JPanel panelCrearReserva;
	private JMenu mnOpciones;

	public JFrame getFrame() {
		return frmHotelTransylvania;
	}

	public void setFrame(JFrame frame) {
		this.frmHotelTransylvania = frame;
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public VentanaPrincipal() {
		datePickerEntrada = generarDatePicker();
		datePickerSalida = generarDatePicker();
		//datePickerEntrada = new JDatePickerImpl(null, null);
		//datePickerSalida = new JDatePickerImpl(null, null);
		formattedNumAdultos = new JFormattedTextField(permitirSoloNumeros());
		formattedNumNinyos = new JFormattedTextField(permitirSoloNumeros());
		
		comboBoxHabitaciones = new JComboBox(controladorPrincipal.cargarNombresHabitaciones());
		
		initialize();		

		panelCard.add(panelCrearReserva, "panelCardCrearReserva");
		panelCard.add(scrollPane, "panelCardUsuarios");
		panelCard.add(scrollPaneReservas, "panelCardReservas");
		
		controladorTablas.crearTablaUsers();
		controladorTablas.crearTablaReservas();
		
		btnVerUsers.setActionCommand("GOTOUSERS");
		btnVerUsers.addActionListener(controladorPrincipal);
		btnVerReservas.setActionCommand("GOTORESERVAS");
		btnVerReservas.addActionListener(controladorPrincipal);
		btnVerCrearReserva.setActionCommand("GOTOCREARRESERVA");		
		btnVerCrearReserva.addActionListener(controladorPrincipal);
		
		mntmLogout.setActionCommand("LOGOUT");
		mntmLogout.addActionListener(controladorPrincipal);

		btnCrearReserva.addActionListener(controladorPrincipal);
		btnCrearReserva.setBackground(Colores.colorBoton);
		btnCrearReserva.setForeground(Colores.blanco);
		btnCrearReserva.setUI(new StyledButtonUI());
		btnCrearReserva.setActionCommand("CREARRESERVA");
		frmHotelTransylvania.getRootPane().setDefaultButton(btnCrearReserva);
		
		TextPrompt placeholder = new TextPrompt("Introduce el e-mail", tfUsuario);
		placeholder.changeAlpha(0.75f);
	    placeholder.changeStyle(Font.ITALIC);
	}
	
	
	private JDatePickerImpl generarDatePicker() {
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl dp = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		//dp.getJFormattedTextField().setBackground(new Color(255, 255, 255));
		return dp;
	}
	
	private NumberFormatter permitirSoloNumeros() {
		NumberFormat longFormat = NumberFormat.getIntegerInstance();
		NumberFormatter numberFormatter = new NumberFormatter(longFormat);
		//numberFormatter.setValueClass(Long.class);
		numberFormatter.setAllowsInvalid(false);
		//numberFormatter.setMinimum(0l);
		numberFormatter.setMaximum(9);
		return numberFormatter;
	}

	private void initialize() {
		frmHotelTransylvania = new JFrame();
		frmHotelTransylvania.setTitle("Hotel Transylvania");
		frmHotelTransylvania.getContentPane().setBackground(new Color(54, 19, 81));
		frmHotelTransylvania.getContentPane().setLayout(new BorderLayout(0, 0));
		
		panelCard = new JPanel();
		frmHotelTransylvania.getContentPane().add(panelCard);
		panelCard.setLayout(new CardLayout(0, 0));
		
		scrollPane = new JScrollPane();
		
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
		
		groupCrearReserva = new JPanel();
		
		JLabel lblBienvenido = new JLabel("BIENVENIDO");
		lblBienvenido.setFont(new Font("Tahoma", Font.BOLD, 10));
		
		JLabel lblTitulo = new JLabel("NUEVA RESERVA");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
		
		JLabel lblEntrada = new JLabel("FECHA DE ENTRADA:");
		lblEntrada.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEntrada.setFont(new Font("Microsoft YaHei", Font.ITALIC, 14));
		
		lblSalida = new JLabel("FECHA DE SALIDA:");
		lblSalida.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSalida.setFont(new Font("Microsoft YaHei", Font.ITALIC, 14));
		
		lblAdultos = new JLabel("NÚM. DE ADULTOS:");
		lblAdultos.setHorizontalAlignment(SwingConstants.TRAILING);
		lblAdultos.setFont(new Font("Microsoft YaHei", Font.ITALIC, 14));
		
		lblNinyos = new JLabel("NÚM. DE NIÑOS:");
		lblNinyos.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNinyos.setFont(new Font("Microsoft YaHei", Font.ITALIC, 14));
		
		btnCrearReserva = new JButton("CREAR RESERVA");
		btnCrearReserva.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
		
		JLabel lblHabitacion = new JLabel("HABITACIÓN:");
		lblHabitacion.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHabitacion.setFont(new Font("Microsoft YaHei", Font.ITALIC, 14));
		
		lblUsuario = new JLabel("USUARIO:");
		lblUsuario.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUsuario.setFont(new Font("Microsoft YaHei", Font.ITALIC, 14));
		
		tfUsuario = new JTextField();
		
		GroupLayout gl_groupCrearReserva = new GroupLayout(groupCrearReserva);
		gl_groupCrearReserva.setHorizontalGroup(
			gl_groupCrearReserva.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_groupCrearReserva.createSequentialGroup()
					.addGap(36)
					.addComponent(lblBienvenido, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
					.addGap(675))
				.addGroup(gl_groupCrearReserva.createSequentialGroup()
					.addGroup(gl_groupCrearReserva.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_groupCrearReserva.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnCrearReserva, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_groupCrearReserva.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_groupCrearReserva.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
								.addGap(4)
								.addComponent(tfUsuario, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))
							.addGroup(Alignment.LEADING, gl_groupCrearReserva.createSequentialGroup()
								.addGap(108)
								.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE))
							.addGroup(Alignment.LEADING, gl_groupCrearReserva.createSequentialGroup()
								.addContainerGap(164, Short.MAX_VALUE)
								.addGroup(gl_groupCrearReserva.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_groupCrearReserva.createSequentialGroup()
										.addComponent(lblHabitacion, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
										.addGap(4)
										.addComponent(comboBoxHabitaciones, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_groupCrearReserva.createSequentialGroup()
										.addComponent(lblAdultos, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(formattedNumAdultos, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addGap(28)
										.addComponent(lblNinyos, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(formattedNumNinyos, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
										.addGap(32))
									.addGroup(gl_groupCrearReserva.createSequentialGroup()
										.addComponent(lblSalida, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
										.addGap(4)
										.addComponent(datePickerSalida, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_groupCrearReserva.createSequentialGroup()
										.addComponent(lblEntrada, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(datePickerEntrada, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE))))))
					.addGap(202))
		);
		gl_groupCrearReserva.setVerticalGroup(
			gl_groupCrearReserva.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_groupCrearReserva.createSequentialGroup()
					.addGap(18)
					.addComponent(lblBienvenido)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblTitulo)
					.addGap(30)
					.addGroup(gl_groupCrearReserva.createParallelGroup(Alignment.BASELINE)
						.addComponent(datePickerEntrada, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEntrada))
					.addGap(18)
					.addGroup(gl_groupCrearReserva.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_groupCrearReserva.createSequentialGroup()
							.addGap(3)
							.addComponent(lblSalida, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(datePickerSalida, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_groupCrearReserva.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_groupCrearReserva.createSequentialGroup()
							.addGap(24)
							.addComponent(lblHabitacion, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_groupCrearReserva.createSequentialGroup()
							.addGap(21)
							.addComponent(comboBoxHabitaciones, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addGap(39)
					.addGroup(gl_groupCrearReserva.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_groupCrearReserva.createSequentialGroup()
							.addGap(3)
							.addGroup(gl_groupCrearReserva.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAdultos, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(formattedNumAdultos, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_groupCrearReserva.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNinyos, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addComponent(formattedNumNinyos, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
					.addGap(30)
					.addGroup(gl_groupCrearReserva.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_groupCrearReserva.createSequentialGroup()
							.addGap(3)
							.addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addComponent(tfUsuario, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addComponent(btnCrearReserva, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(113, Short.MAX_VALUE))
		);
		groupCrearReserva.setLayout(gl_groupCrearReserva);
		
		panelCrearReserva = new JPanel();
		panelCrearReserva.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelCrearReserva.add(groupCrearReserva);
		
		menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(15, 25, 0, 0));
		frmHotelTransylvania.getContentPane().add(menuBar, BorderLayout.NORTH);
		
		btnVerCrearReserva = new JButton("☛ CREAR RESERVA");
		menuBar.add(btnVerCrearReserva);
		
		btnVerUsers = new JButton("VER USUARIOS");
		menuBar.add(btnVerUsers);
		
		btnVerReservas = new JButton("VER RESERVAS");
		menuBar.add(btnVerReservas);
		
		mnOpciones = new JMenu("Opciones ⚙");
		menuBar.add(mnOpciones);
		
		mntmLogout = new JMenuItem("Cerrar sesión");
		mnOpciones.add(mntmLogout);

		frmHotelTransylvania.setSize(900, 600);
		frmHotelTransylvania.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

	public JDatePickerImpl getDatePickerEntrada() {
		return datePickerEntrada;
	}

	public void setDatePickerEntrada(JDatePickerImpl datePickerEntrada) {
		this.datePickerEntrada = datePickerEntrada;
	}

	public JDatePickerImpl getDatePickerSalida() {
		return datePickerSalida;
	}

	public void setDatePickerSalida(JDatePickerImpl datePickerSalida) {
		this.datePickerSalida = datePickerSalida;
	}

	public JFormattedTextField getFormattedNumAdultos() {
		return formattedNumAdultos;
	}

	public void setFormattedNumAdultos(JFormattedTextField formattedNumAdultos) {
		this.formattedNumAdultos = formattedNumAdultos;
	}

	public JFormattedTextField getFormattedNumNinyos() {
		return formattedNumNinyos;
	}

	public void setFormattedNumNinyos(JFormattedTextField formattedNumNinyos) {
		this.formattedNumNinyos = formattedNumNinyos;
	}

	public JComboBox<String> getComboBoxHabitaciones() {
		return comboBoxHabitaciones;
	}

	public void setComboBoxHabitaciones(JComboBox<String> comboBoxHabitaciones) {
		this.comboBoxHabitaciones = comboBoxHabitaciones;
	}

	public JTextField getTfUsuario() {
		return tfUsuario;
	}

	public void setTfUsuario(JTextField tfUsuario) {
		this.tfUsuario = tfUsuario;
	}
}
