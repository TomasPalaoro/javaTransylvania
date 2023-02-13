package clienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import controlador.ControladorChatBot;

public class Cliente {

	private Socket socket; // canal de conexión

	private DataInputStream bufferEntrada = null;
	private DataOutputStream bufferSalida = null;

	private String COMANDO_TERMINACION = "exit"; // TODO cambiar idioma
	Scanner sc = new Scanner(System.in);

	ControladorChatBot controlador;

	public static final int esperaMostrarRespuesta = 500;

	/**
	 * 
	 * @param args
	 * @deprecated
	 */
	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		String ip = "localhost";
		System.out.println("CLIENTE");
		cliente.ejecutarConexion(ip, 5050);
		cliente.escribirDatos();
	}

	public void ejecutarConexion(String ip, int puerto) {

		Thread hilo = new Thread(new Runnable() {
			/**
			 * Ejecución del hilo cuando se haga start
			 */
			@Override
			public void run() {
				while (true) {
					try {
						System.out.println("entra");
						iniciarConexion(ip, puerto);
						flujos();
						recibirDatos();
					} finally {
						// obliga a cerrar la conexión
						cerrarConexion();
					}
				}
			}
		});

		hilo.start();
	}

	/**
	 * Inicializa el socket
	 * 
	 * @param ip
	 * @param puerto
	 */
	public void iniciarConexion(String ip, int puerto) {
		try {
			socket = new Socket(ip, puerto);
			System.out.println("Conectado a:" + socket.getInetAddress().getHostName());
		} catch (Exception e) {
			System.err.println("Error en iniciar conexion: " + e.getMessage());
			//System.exit(0);
			
		}
	}

	/**
	 * Flujo de datos
	 */
	public void flujos() {
		try {
			bufferEntrada = new DataInputStream(socket.getInputStream());
			bufferSalida = new DataOutputStream(socket.getOutputStream());
			bufferSalida.flush(); // vacía el canal
		} catch (IOException e) {
			System.err.println("Error en la apertura de flujos");
		}
	}

	/**
	 * Muestra los mensajes que recibe del servidor
	 */
	public void recibirDatos() {
		String st = "";
		try {
			do {
				st = bufferEntrada.readUTF();
				System.out.println("\n[Servidor] => " + st);
				if (!st.equals("")) {
					try {
						Thread.sleep(esperaMostrarRespuesta);
						controlador.crearPanelBot(st);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} while (!st.equals(COMANDO_TERMINACION));
		} catch (IOException e) {
			System.err.println("Cliente: Error en recibirDatos" + e.getMessage());
		}
	}

	/**
	 * @deprecated
	 */
	public void escribirDatos() {
		String entrada = "";
		while (true) {
			entrada = sc.nextLine();
			System.out.println("[Cliente] => " + entrada);
			if (entrada.length() > 0) {
				enviar(entrada);
			}

		}
	}

	/**
	 * Enviar mensaje al servidor
	 * 
	 * @param s
	 */
	public void enviar(String s) {
		try {
			bufferSalida.writeUTF(s);
			bufferSalida.flush();
		} catch (IOException e) {
			System.err.println("Cliente: Error en enviar" + e.getMessage());
		}
	}

	public void cerrarConexion() {
		try {
			bufferEntrada.close();
			bufferSalida.close();
			socket.close();
		} catch (IOException e) {
			System.err.println("Cliente: Error en cerrarConexion: " + e.getMessage());
		} finally {
			System.out.println("Conversación finalizada...");
			// SALIR DEL SISTEMA
			//System.exit(0);
		}
	}

	public ControladorChatBot getControlador() {
		return controlador;
	}

	public void setControlador(ControladorChatBot controlador) {
		this.controlador = controlador;
	}

}
