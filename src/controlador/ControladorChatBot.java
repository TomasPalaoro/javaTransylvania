package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

import clienteServidor.Cliente;
import clienteServidor.Servidor;
import vista.VentanaChatBot;

/**
 * Controlador de la vista
 * 
 * @author Tomas
 *
 */
public class ControladorChatBot implements ActionListener {

	VentanaChatBot ventana;
	Servidor servidor;
	Cliente cliente;
	static final String ip = "localhost";
	static final int puerto = 5050;
	
	public static boolean chatIniciado = false;

	// escribirLento //
	Timer timer;
	int index = 0;

	/**
	 * Constructor principal
	 * 
	 * @param ventana
	 */
	public ControladorChatBot() {
		ventana = new VentanaChatBot();
		ventana.setControlador(this);
		ventana.getBtnEnviar().addActionListener(this);
		iniciarChat();
	}
	
	private void abrirVentana() {
		ventana.getFrmChatbot().setVisible(true);
		chatIniciado = true;
	}

	public void iniciarChat() {
		servidor = new Servidor();
		servidor.ejecutarConexion(puerto);

		cliente = new Cliente();
		cliente.setControlador(this);
		cliente.ejecutarConexion(ip, puerto);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		switch (comando) {
		case "ENVIAR":
			JTextField tfEnviar = ventana.getTfEntradaUsuario();
			if ((tfEnviar.getText() != null) && (!tfEnviar.getText().equals(""))) {
				ventana.setTxtrPregunta(tfEnviar.getText());
				cliente.enviar(tfEnviar.getText());
				crearPanelPersona(tfEnviar.getText());
				tfEnviar.setText("");
			}
			break;

		case "ABRIRCHAT":
			if (!chatIniciado) abrirVentana();
			break;
		default:
			break;
		}
		
	}
	
	/**
	 * Estilo en común para los textAreas generados
	 * @param textArea
	 */
	private void estilizarTextArea(JTextArea textArea) {
		textArea.setBackground(Color.BLUE);
		textArea.setForeground(Color.WHITE);
		textArea.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		textArea.setMargin(new Insets(12, 22, 4, 4));
		textArea.setWrapStyleWord(true);
		textArea.setText("");
		textArea.setLineWrap(true);
		textArea.setEditable(false);
	}

	/**
	 * Genera el panel y textarea del nuevo mensaje de cliente
	 * 
	 * @param mensaje
	 */
	public void crearPanelPersona(String mensaje) {

		JPanel panelPersona = new JPanel();
		panelPersona.setBackground(Color.WHITE);
		panelPersona.setLayout(new BoxLayout(panelPersona, BoxLayout.X_AXIS));

		JTextArea txtrPregunta = new JTextArea();
		estilizarTextArea(txtrPregunta);
		panelPersona.add(txtrPregunta);

		JLabel lblIcono = new JLabel("");
		lblIcono.setIcon(new ImageIcon(VentanaChatBot.class.getResource("/iconos/icons8-cabeza-humana-48.png")));
		panelPersona.add(lblIcono);

		ventana.getPanelChat().add(panelPersona);

		ventana.getPanelChat().add(Box.createVerticalStrut(50));

		ventana.getFrmChatbot().repaint();

		escribirLento(txtrPregunta, mensaje);

		bajarAlLimite();
	}

	/**
	 * Genera el panel y textarea del nuevo mensaje de servidor
	 * 
	 * @param mensaje
	 */
	public void crearPanelBot(String mensaje) {

		JPanel panelBot = new JPanel();
		panelBot.setBackground(Color.WHITE);
		panelBot.setLayout(new BoxLayout(panelBot, BoxLayout.X_AXIS));

		JLabel lblImagenBot = new JLabel("");
		lblImagenBot.setIcon(new ImageIcon(VentanaChatBot.class.getResource("/iconos/icons8-chatbot-48.png")));
		panelBot.add(lblImagenBot);

		JTextArea txtMensajeBot = new JTextArea();
		estilizarTextArea(txtMensajeBot);
		panelBot.add(txtMensajeBot);

		ventana.getPanelChat().add(panelBot);

		ventana.getPanelChat().add(Box.createVerticalStrut(50));

		ventana.getFrmChatbot().repaint();

		escribirLento(txtMensajeBot, mensaje);

		bajarAlLimite();
	}

	/**
	 * Scroll hasta abajo
	 */
	private void bajarAlLimite() {
		ventana.getScrollPaneCentral().validate(); //comprueba el nuevo máximo de altura
		ventana.getScrollPaneCentral().getVerticalScrollBar()
				.setValue(ventana.getScrollPaneCentral().getVerticalScrollBar().getMaximum());
	}

	/**
	 * Hilo que escribe los mensajes carácter a carácter en las cajas de texto,
	 * esperando que haya acabado el mensaje anterior
	 * 
	 * @param cajaTexto
	 * @param mensaje
	 */
	private void escribirLento(JTextArea cajaTexto, String mensajeCompleto) {
		Thread hilo = new Thread(new Runnable() {

			@Override
			public void run() {
				while (timer != null && timer.isRunning()) {
				} // esperar
				String mensaje = mensajeCompleto;
				cajaTexto.setText("");

				int tiempo = 10;
				timer = new Timer(tiempo, new AbstractAction() {
					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							cajaTexto.setText(cajaTexto.getText() + String.valueOf(mensaje.charAt(index)));
							index++;
							if (index >= mensaje.length()) {
								timer.stop();
								index = 0;
								bajarAlLimite();
							}
						} catch (IndexOutOfBoundsException e2) {
							timer.stop();
							index = 0;
						}
					}
				});
				timer.start();
			}
		});
		hilo.start();

	}

}
