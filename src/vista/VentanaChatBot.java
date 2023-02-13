package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controlador.ControladorChatBot;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;

import java.awt.Insets;

/**
 * Vista de la ventana del chatbot
 * @author Tomas
 *
 */
public class VentanaChatBot {

	ControladorChatBot controlador;

	private JFrame frmChatbot;
	private JTextField tfEntradaUsuario;
	private JPanel panelContenedor;
	private JPanel panelInferior;
	private JPanel panelSuperior;
	private JPanel panelChat;
	private JLabel lblTitulo;
	private JPanel panelBot;
	private JLabel lblImagenBot;
	private JButton btnEnviar;
	private JTextArea txtMensajeBot;
	private JPanel panelPersona;
	private JTextArea txtrPregunta;
	private JLabel lblNewLabel;
	private JScrollPane scrollPaneCentral;

	/**
	 * Create the application.
	 */
	public VentanaChatBot() {
		initialize();
		frmChatbot.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		frmChatbot.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		        ControladorChatBot.chatIniciado = false;
		    }
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChatbot = new JFrame();
		frmChatbot.setTitle("CHATBOT");
		frmChatbot.setResizable(false);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //
		frmChatbot.setSize(screenSize.width / 4, screenSize.height / 2); //
		
		frmChatbot.setLocationRelativeTo(null); //
		
		frmChatbot.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelContenedor = new JPanel();
		frmChatbot.getContentPane().add(panelContenedor, BorderLayout.CENTER);
		panelContenedor.setLayout(new BorderLayout(0, 0));

		panelInferior = new JPanel();
		panelContenedor.add(panelInferior, BorderLayout.SOUTH);
		panelInferior.setLayout(new BoxLayout(panelInferior, BoxLayout.X_AXIS));

		tfEntradaUsuario = new JTextField();
		tfEntradaUsuario.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		panelInferior.add(tfEntradaUsuario);
		tfEntradaUsuario.setColumns(10);

		btnEnviar = new JButton("");
		btnEnviar.setToolTipText("Enviar");
		btnEnviar.setBackground(Color.BLUE);
		btnEnviar.setIcon(new ImageIcon(VentanaChatBot.class.getResource("/iconos/icons8-enviado-48.png")));
		btnEnviar.setActionCommand("ENVIAR");
		//btnEnviar.addActionListener(controlador); //
		panelInferior.add(btnEnviar);

		panelSuperior = new JPanel();
		panelContenedor.add(panelSuperior, BorderLayout.NORTH);

		lblTitulo = new JLabel("Asistente virtual");
		lblTitulo.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		panelSuperior.add(lblTitulo);

		panelChat = new JPanel();
		panelChat.setBackground(Color.WHITE);
		panelChat.setLayout(new BoxLayout(panelChat, BoxLayout.Y_AXIS));

		panelPersona = new JPanel();
		panelPersona.setBackground(Color.WHITE);
		panelChat.add(panelPersona);
		panelPersona.setLayout(new BoxLayout(panelPersona, BoxLayout.X_AXIS));

		txtrPregunta = new JTextArea();
		txtrPregunta.setBackground(Color.LIGHT_GRAY);
		txtrPregunta.setMargin(new Insets(12, 22, 2, 2));
		txtrPregunta.setWrapStyleWord(true);
		txtrPregunta.setText("Pregunta");
		txtrPregunta.setLineWrap(true);
		txtrPregunta.setEditable(false);
		panelPersona.add(txtrPregunta);

		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(VentanaChatBot.class.getResource("/iconos/icons8-cabeza-humana-48.png")));
		panelPersona.add(lblNewLabel);
		panelPersona.setVisible(false); //
		
		//panelChat.add(Box.createVerticalStrut(50)); //

		panelBot = new JPanel();
		panelBot.setBackground(Color.WHITE);
		panelChat.add(panelBot);
		panelBot.setLayout(new BoxLayout(panelBot, BoxLayout.X_AXIS));

		lblImagenBot = new JLabel("");
		lblImagenBot.setIcon(new ImageIcon(VentanaChatBot.class.getResource("/iconos/icons8-chatbot-48.png")));
		panelBot.add(lblImagenBot);


		txtMensajeBot = new JTextArea();
		txtMensajeBot.setBackground(Color.LIGHT_GRAY);
		txtMensajeBot.setMargin(new Insets(12, 22, 2, 2));
		txtMensajeBot.setEditable(false);
		txtMensajeBot.setWrapStyleWord(true);
		txtMensajeBot.setLineWrap(true);
		txtMensajeBot.setText(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. ");
		panelBot.add(txtMensajeBot);
		
		panelBot.setVisible(false);
		
		
		scrollPaneCentral = new JScrollPane();
		scrollPaneCentral.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneCentral.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		panelContenedor.add(scrollPaneCentral, BorderLayout.CENTER);
		
		panelChat.add(Box.createVerticalStrut(50)); //
		
		scrollPaneCentral.setViewportView(panelChat);

		frmChatbot.getRootPane().setDefaultButton(btnEnviar); //
	}

	public JFrame getFrmChatbot() {
		return frmChatbot;
	}

	public void setFrmChatbot(JFrame frmChatbot) {
		this.frmChatbot = frmChatbot;
	}

	public JTextArea getTxtMensajeBot() {
		return txtMensajeBot;
	}

	public void setTxtMensajeBot(String txtMensajeBot) {
		this.txtMensajeBot.setText(txtMensajeBot);
	}

	public JTextArea getTxtrPregunta() {
		return txtrPregunta;
	}

	public void setTxtrPregunta(String txtrPregunta) {
		this.txtrPregunta.setText(txtrPregunta);
	}

	public JTextField getTfEntradaUsuario() {
		return tfEntradaUsuario;
	}

	public JPanel getPanelChat() {
		return panelChat;
	}

	public JScrollPane getScrollPaneCentral() {
		return scrollPaneCentral;
	}

	public ControladorChatBot getControlador() {
		return controlador;
	}

	public void setControlador(ControladorChatBot controlador) {
		this.controlador = controlador;
	}

	public JButton getBtnEnviar() {
		return btnEnviar;
	}	
}
