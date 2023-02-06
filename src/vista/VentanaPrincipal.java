package vista;



import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import controlador.ControladorPrincipal;
import controlador.ControladorTablas;
import iconos.CargarIcono;
import utils.AutoCompleteTextField;
import utils.Colores;
import utils.DateLabelFormatter;
import utils.StyledButtonUI;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
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
import javax.swing.Box;
import javax.swing.BoxLayout;
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
	private JMenuBar menuBar;
	private JButton btnVerUsers;
	private JButton btnVerReservas;
	private JScrollPane scrollPaneReservas;
	private JScrollPane scrollPaneHabitaciones;
	private JButton btnVerCrearReserva;
	private JButton btnVerHabitaciones;
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
	//
	private JPanel panelLateral;
	private JPanel panelHeaderUsers;
	private JButton btnNextUser;
	private JButton btnBackUser;
	private JButton btnFirstUser;
	private JButton btnLastUser;
	private JLabel lblNumPaginaUser;
	private JButton btnEditarUser;
	private JButton btnEliminarUser;
	private JButton btnBuscarUser;
	//
	private JPanel panelHeaderReserva;
	private JButton btnNextReserva;
	private JButton btnBackReserva;
	private JButton btnFirstReserva;
	private JButton btnLastReserva;
	private JLabel lblNumPaginaReserva;
	private JButton btnEditarReserva;
	private JButton btnEliminarReserva;
	private JButton btnBuscarReserva;
	//
	private JPanel panelHeaderHabitaciones;
	private JButton btnNextHabitacion;
	private JButton btnBackHabitacion;
	private JButton btnFirstHabitacion;
	private JButton btnLastHabitacion;
	private JLabel lblNumPaginaHabitacion;
	private JButton btnEditarHabitacion;
	private JButton btnEliminarHabitacion;
	private JButton btnBuscarHabitacion;
	//
	private JLabel lblNuevoEmail;
	private JLabel lblNuevaPass;
	private JLabel lblNuevoNombre;
	private JLabel lblNuevoApellido;
	private JPanel panelContenedorTexto;
	private JTextField tfNuevoEmail;
	private JPanel panelContenedorTexto_1;
	private JTextField tfNuevaPass;
	private JPanel panelContenedorTexto_2;
	private JTextField tfNuevoNombre;
	private JPanel panelContenedorTexto_3;
	private JTextField tfNuevoApellido;
	private JPanel panelContenedorTexto_4;
	private JButton btnCrear;
	private JButton btnMostrarCrearUser;
	private JLabel iconoAddUser;
	private JLabel lblNuevoTelefono;
	private JPanel panelContenedorTexto_5;
	private JTextField tfNuevoTelefono;

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
		panelCard.add(scrollPaneHabitaciones, "panelCardHabitaciones");
		
		panelLateral = generarLateral();
		scrollPane.setRowHeaderView(panelLateral);
		
		panelHeaderUsers = headerUsers();
		scrollPane.setColumnHeaderView(panelHeaderUsers);
		
		panelHeaderHabitaciones = headerHabitaciones();
		scrollPaneHabitaciones.setColumnHeaderView(panelHeaderHabitaciones);
		
		panelHeaderReserva = headerReservas();
		scrollPaneReservas.setColumnHeaderView(panelHeaderReserva);
		
		controladorTablas.crearTabla("USUARIO");
		controladorTablas.crearTabla("RESERVA");
		controladorTablas.crearTabla("HABITACION");
		
		btnVerUsers.setActionCommand("GOTOUSERS");
		btnVerUsers.addActionListener(controladorPrincipal);
		btnVerReservas.setActionCommand("GOTORESERVAS");
		btnVerReservas.addActionListener(controladorPrincipal);
		btnVerHabitaciones.setActionCommand("GOTOHABITACIONES");
		btnVerHabitaciones.addActionListener(controladorPrincipal);
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
		
		/*TextPrompt placeholder = new TextPrompt("Introduce el e-mail", tfUsuario);
		placeholder.changeAlpha(0.75f);
	    placeholder.changeStyle(Font.ITALIC);*/
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
	
	private AutoCompleteTextField autoCompletarUsuarios() {
		AutoCompleteTextField a = new AutoCompleteTextField();
		for (int i = 0; i < controladorTablas.listaUsers.size(); i++) {
			a.addPossibility(controladorTablas.listaUsers.get(i).getEmail());
		}
		return a;
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
	
	private JPanel headerUsers() {
		JPanel panelHeaderUsers = new JPanel();
		btnMostrarCrearUser = new JButton(CargarIcono.crearIcono(getClass().getResource("/iconos/addUserWhite.png"), "Añadir usuario", 34));
		btnMostrarCrearUser.setText("AÑADIR");
		btnMostrarCrearUser.setActionCommand("MOSTRARLATERALUSUARIO");
		btnMostrarCrearUser.addActionListener(controladorTablas);
		panelHeaderUsers.add(btnMostrarCrearUser);
		
		btnEditarUser = new JButton(CargarIcono.crearIcono(getClass().getResource("/iconos/editar.png"), "Editar usuario", 34));
		btnEditarUser.setText("EDITAR/ELIMINAR");
		btnEditarUser.setActionCommand("ACTIVAREDICIONUSUARIO");
		btnEditarUser.addActionListener(controladorTablas);
		panelHeaderUsers.add(btnEditarUser);
		
		btnEliminarUser = new JButton(CargarIcono.crearIcono(getClass().getResource("/iconos/papelera.png"), "Eliminar usuario", 34));
		btnEliminarUser.setActionCommand("ELIMINARUSUARIO");
		btnEliminarUser.addActionListener(controladorTablas);
		panelHeaderUsers.add(btnEliminarUser);
		btnEliminarUser.setVisible(false);
		
		btnBuscarUser = new JButton(CargarIcono.crearIcono(getClass().getResource("/iconos/buscar.png"), "Buscar usuario", 34));
		btnBuscarUser.setActionCommand("BUSCARUSUARIO");
		btnBuscarUser.addActionListener(controladorTablas);
		panelHeaderUsers.add(btnBuscarUser);
		
		panelHeaderUsers.add(Box.createHorizontalStrut(40)); //espacio
		
		btnFirstUser = new JButton("<<");
		btnFirstUser.setActionCommand("PRIMERUSUARIO");
		btnFirstUser.setEnabled(false);
		btnFirstUser.addActionListener(controladorTablas);
		panelHeaderUsers.add(btnFirstUser);		
		
		btnBackUser = new JButton(" < ");
		btnBackUser.setActionCommand("ANTERIORUSUARIO");
		btnBackUser.setEnabled(false);
		btnBackUser.addActionListener(controladorTablas);
		panelHeaderUsers.add(btnBackUser);
		
		btnNextUser = new JButton(" > ");
		btnNextUser.setActionCommand("SIGUIENTEUSUARIO");
		btnNextUser.addActionListener(controladorTablas);
		
		lblNumPaginaUser = new JLabel("1");
		panelHeaderUsers.add(lblNumPaginaUser);
		panelHeaderUsers.add(btnNextUser);
		
		btnLastUser = new JButton(">>");
		btnLastUser.setActionCommand("ULTIMOUSUARIO");
		btnLastUser.addActionListener(controladorTablas);
		panelHeaderUsers.add(btnLastUser);
		
		panelHeaderUsers.add(Box.createHorizontalStrut(50)); //espacio
		return panelHeaderUsers;
	}
	
	private JPanel headerReservas() {
		JPanel panelHeaderReservas = new JPanel();
		
		btnEditarReserva = new JButton(CargarIcono.crearIcono(getClass().getResource("/iconos/editar.png"), "Editar reserva", 34));
		btnEditarReserva.setText("EDITAR/ELIMINAR");
		btnEditarReserva.setActionCommand("ACTIVAREDICIONRESERVA");
		btnEditarReserva.addActionListener(controladorTablas);
		panelHeaderReservas.add(btnEditarReserva);
		
		btnEliminarReserva = new JButton(CargarIcono.crearIcono(getClass().getResource("/iconos/papelera.png"), "Eliminar reserva", 34));
		btnEliminarReserva.setActionCommand("ELIMINARRESERVA");
		btnEliminarReserva.addActionListener(controladorTablas);
		panelHeaderReservas.add(btnEliminarReserva);
		btnEliminarReserva.setVisible(false);
		
		btnBuscarReserva = new JButton(CargarIcono.crearIcono(getClass().getResource("/iconos/buscar.png"), "Buscar reserva", 34));
		btnBuscarReserva.setActionCommand("BUSCARRESERVA");
		btnBuscarReserva.addActionListener(controladorTablas);
		panelHeaderReservas.add(btnBuscarReserva);
		
		panelHeaderReservas.add(Box.createHorizontalStrut(80)); //espacio
		
		btnFirstReserva = new JButton("<<");
		btnFirstReserva.setActionCommand("PRIMERARESERVA");
		btnFirstReserva.setEnabled(false);
		btnFirstReserva.addActionListener(controladorTablas);
		panelHeaderReservas.add(btnFirstReserva);		
		
		btnBackReserva = new JButton(" < ");
		btnBackReserva.setActionCommand("ANTERIORRESERVA");
		btnBackReserva.setEnabled(false);
		btnBackReserva.addActionListener(controladorTablas);
		panelHeaderReservas.add(btnBackReserva);
		
		btnNextReserva = new JButton(" > ");
		btnNextReserva.setActionCommand("SIGUIENTERESERVA");
		btnNextReserva.addActionListener(controladorTablas);
		
		lblNumPaginaReserva = new JLabel("1");
		panelHeaderReservas.add(lblNumPaginaReserva);
		panelHeaderReservas.add(btnNextReserva);
		
		btnLastReserva = new JButton(">>");
		btnLastReserva.setActionCommand("ULTIMARESERVA");
		btnLastReserva.addActionListener(controladorTablas);
		panelHeaderReservas.add(btnLastReserva);
		
		panelHeaderReservas.add(Box.createHorizontalStrut(100)); //espacio
		return panelHeaderReservas;
	}
	
	private JPanel headerHabitaciones() {
		JPanel panelHeaderHabitaciones = new JPanel();
		
		btnEditarHabitacion = new JButton(CargarIcono.crearIcono(getClass().getResource("/iconos/editar.png"), "Editar reserva", 34));
		btnEditarHabitacion.setText("EDITAR/ELIMINAR");
		btnEditarHabitacion.setActionCommand("ACTIVAREDICIONHABITACION");
		btnEditarHabitacion.addActionListener(controladorTablas);
		panelHeaderHabitaciones.add(btnEditarHabitacion);
		
		btnEliminarHabitacion = new JButton(CargarIcono.crearIcono(getClass().getResource("/iconos/papelera.png"), "Eliminar reserva", 34));
		btnEliminarHabitacion.setActionCommand("ELIMINARHABITACION");
		btnEliminarHabitacion.addActionListener(controladorTablas);
		panelHeaderHabitaciones.add(btnEliminarHabitacion);
		btnEliminarHabitacion.setVisible(false);
		
		btnBuscarHabitacion = new JButton(CargarIcono.crearIcono(getClass().getResource("/iconos/buscar.png"), "Buscar reserva", 34));
		btnBuscarHabitacion.setActionCommand("BUSCARHABITACION");
		btnBuscarHabitacion.addActionListener(controladorTablas);
		panelHeaderHabitaciones.add(btnBuscarHabitacion);
		
		panelHeaderHabitaciones.add(Box.createHorizontalStrut(80)); //espacio
		
		btnFirstHabitacion = new JButton("<<");
		btnFirstHabitacion.setActionCommand("PRIMERAHABITACION");
		btnFirstHabitacion.setEnabled(false);
		btnFirstHabitacion.addActionListener(controladorTablas);
		panelHeaderHabitaciones.add(btnFirstHabitacion);		
		
		btnBackHabitacion = new JButton(" < ");
		btnBackHabitacion.setActionCommand("ANTERIORHABITACION");
		btnBackHabitacion.setEnabled(false);
		btnBackHabitacion.addActionListener(controladorTablas);
		panelHeaderHabitaciones.add(btnBackHabitacion);
		
		btnNextHabitacion = new JButton(" > ");
		btnNextHabitacion.setActionCommand("SIGUIENTEHABITACION");
		btnNextHabitacion.addActionListener(controladorTablas);
		
		lblNumPaginaHabitacion = new JLabel("1");
		panelHeaderHabitaciones.add(lblNumPaginaHabitacion);
		panelHeaderHabitaciones.add(btnNextHabitacion);
		
		btnLastHabitacion = new JButton(">>");
		btnLastHabitacion.setActionCommand("ULTIMAHABITACION");
		btnLastHabitacion.addActionListener(controladorTablas);
		panelHeaderHabitaciones.add(btnLastHabitacion);
		
		panelHeaderHabitaciones.add(Box.createHorizontalStrut(100)); //espacio
		return panelHeaderHabitaciones;
	}
	
	private JPanel generarLateral() {
		JPanel panelLateral = new JPanel();
		panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
		
		iconoAddUser = new JLabel("");
		iconoAddUser.setIcon(CargarIcono.crearIcono(getClass().getResource("/iconos/addUserWhite.png"), "Añadir usuario", 34));
		panelLateral.add(iconoAddUser);
		
		lblNuevoEmail = new JLabel("*E-mail");
		panelLateral.add(lblNuevoEmail);
		
		panelContenedorTexto = new JPanel();
		panelContenedorTexto.setMaximumSize(new Dimension(32767, 50));
		panelLateral.add(panelContenedorTexto);
		
		tfNuevoEmail = new JTextField();
		panelContenedorTexto.add(tfNuevoEmail);
		tfNuevoEmail.setColumns(10);
		
		lblNuevaPass = new JLabel("*Contraseña");
		panelLateral.add(lblNuevaPass);
		
		panelContenedorTexto_1 = new JPanel();
		panelContenedorTexto_1.setMaximumSize(new Dimension(32767, 50));
		panelLateral.add(panelContenedorTexto_1);
		
		tfNuevaPass = new JTextField();
		tfNuevaPass.setColumns(10);
		panelContenedorTexto_1.add(tfNuevaPass);
		
		lblNuevoNombre = new JLabel("Nombre");
		panelLateral.add(lblNuevoNombre);
		
		panelContenedorTexto_2 = new JPanel();
		panelContenedorTexto_2.setMaximumSize(new Dimension(32767, 50));
		panelLateral.add(panelContenedorTexto_2);
		
		tfNuevoNombre = new JTextField();
		tfNuevoNombre.setColumns(10);
		panelContenedorTexto_2.add(tfNuevoNombre);
		
		lblNuevoApellido = new JLabel("Apellido");
		panelLateral.add(lblNuevoApellido);
		
		panelContenedorTexto_3 = new JPanel();
		panelContenedorTexto_3.setMaximumSize(new Dimension(32767, 50));
		panelLateral.add(panelContenedorTexto_3);
		
		tfNuevoApellido = new JTextField();
		tfNuevoApellido.setColumns(10);
		panelContenedorTexto_3.add(tfNuevoApellido);
		
		lblNuevoTelefono = new JLabel("Teléfono");
		panelLateral.add(lblNuevoTelefono);
		
		panelContenedorTexto_5 = new JPanel();
		panelContenedorTexto_5.setMaximumSize(new Dimension(32767, 50));
		panelLateral.add(panelContenedorTexto_5);
		
		tfNuevoTelefono = new JTextField();
		tfNuevoTelefono.setColumns(10);
		panelContenedorTexto_5.add(tfNuevoTelefono);
		
		panelContenedorTexto_4 = new JPanel();
		panelContenedorTexto_4.setMaximumSize(new Dimension(32767, 50));
		panelLateral.add(panelContenedorTexto_4);
		
		btnCrear = new JButton("CREAR");
		btnCrear.addActionListener(controladorPrincipal);
		btnCrear.setActionCommand("CREARUSUARIO");
		panelContenedorTexto_4.setLayout(new BoxLayout(panelContenedorTexto_4, BoxLayout.Y_AXIS));
		panelContenedorTexto_4.add(btnCrear);
		
		panelLateral.setVisible(false);
		return panelLateral;
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
		scrollPaneReservas = new JScrollPane();
		scrollPaneHabitaciones = new JScrollPane();
		
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
		
		tfUsuario = autoCompletarUsuarios();
		
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
		
		btnVerHabitaciones = new JButton("VER HABITACIONES");
		menuBar.add(btnVerHabitaciones);
		
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

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JScrollPane getScrollPaneReservas() {
		return scrollPaneReservas;
	}

	public JScrollPane getScrollPaneHabitaciones() {
		return scrollPaneHabitaciones;
	}

	public JDatePickerImpl getDatePickerEntrada() {
		return datePickerEntrada;
	}

	public JDatePickerImpl getDatePickerSalida() {
		return datePickerSalida;
	}

	public JFormattedTextField getFormattedNumAdultos() {
		return formattedNumAdultos;
	}

	public JFormattedTextField getFormattedNumNinyos() {
		return formattedNumNinyos;
	}

	public JComboBox<String> getComboBoxHabitaciones() {
		return comboBoxHabitaciones;
	}

	public JTextField getTfUsuario() {
		return tfUsuario;
	}

	public JButton getBtnNextUser() {
		return btnNextUser;
	}

	public JButton getBtnBackUser() {
		return btnBackUser;
	}

	public JButton getBtnFirstUser() {
		return btnFirstUser;
	}

	public JButton getBtnLastUser() {
		return btnLastUser;
	}

	public JTextField getTfNuevoEmail() {
		return tfNuevoEmail;
	}

	public JTextField getTfNuevaPass() {
		return tfNuevaPass;
	}

	public JTextField getTfNuevoNombre() {
		return tfNuevoNombre;
	}

	public JTextField getTfNuevoApellido() {
		return tfNuevoApellido;
	}

	public JTextField getTfNuevoTelefono() {
		return tfNuevoTelefono;
	}

	public ControladorTablas getControladorTablas() {
		return controladorTablas;
	}

	public JButton getBtnNextReserva() {
		return btnNextReserva;
	}
	
	public JButton getBtnBackReserva() {
		return btnBackReserva;
	}

	public JButton getBtnFirstReserva() {
		return btnFirstReserva;
	}

	public JButton getBtnLastReserva() {
		return btnLastReserva;
	}

	public JPanel getPanelLateralUsers() {
		return panelLateral;
	}

	public JLabel getLblNumPaginaUser() {
		return lblNumPaginaUser;
	}

	public JLabel getLblNumPaginaReserva() {
		return lblNumPaginaReserva;
	}

	public JButton getBtnEliminarUser() {
		return btnEliminarUser;
	}

	public JButton getBtnEliminarReserva() {
		return btnEliminarReserva;
	}

	public JButton getBtnNextHabitacion() {
		return btnNextHabitacion;
	}

	public JButton getBtnBackHabitacion() {
		return btnBackHabitacion;
	}

	public JButton getBtnFirstHabitacion() {
		return btnFirstHabitacion;
	}

	public JButton getBtnLastHabitacion() {
		return btnLastHabitacion;
	}

	public JLabel getLblNumPaginaHabitacion() {
		return lblNumPaginaHabitacion;
	}

	public JButton getBtnEliminarHabitacion() {
		return btnEliminarHabitacion;
	}	
}
