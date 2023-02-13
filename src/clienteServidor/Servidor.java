package clienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Clase servidor
 * 
 * @author Tomas
 *
 */
public class Servidor {

	private Socket socket; // se le pasa la IP y el puerto
	private ServerSocket serverSocket; // socket especial que otorga el rol de servidor, no recibe IP solamente tiene
										// puerto

	private DataInputStream bufferEntrada = null;
	private DataOutputStream bufferSalida = null;

	private String COMANDO_TERMINACION = "exit"; // TODO cambiar idioma
	private String idioma = "";

	Scanner sc = new Scanner(System.in);

	/**
	 * Hilo principal que inicia un puerto por defecto y ejecuta el hilo de conexión
	 * 
	 * @param args
	 * @deprecated
	 */
	public static void main(String[] args) {
		Servidor servidor = new Servidor();
		int puerto = 5050;
		servidor.ejecutarConexion(puerto);
		servidor.escribirDatos();
	}

	/**
	 * Hilo con interfaz runnable que establece el serversocket y da de alta los
	 * flujos de datos para poder contectarnos, por último recibiendo los datos. Al
	 * ser un hilo evita que se bloquee la aplicacion
	 * 
	 * @param puerto
	 */
	public void ejecutarConexion(int puerto) {

		Thread hilo = new Thread(new Runnable() {
			/**
			 * Ejecución del hilo cuando se haga start
			 */
			@Override
			public void run() {
				while (true) {
					try {
						System.out.println("entra");
						iniciarConexion(puerto);
						flujos();
						// PRIMER MENSAJE //
						enviar("Write 'eng' if you'd like to be assisted in english");
						enviar("Escribe 'esp' si deseas asistencia en español");
						recibir();
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
	 * Inicializa los sockets
	 * 
	 * @param puerto
	 */
	public void iniciarConexion(int puerto) {
		try {
			serverSocket = new ServerSocket(puerto);
			System.out.println("SERVIDOR");
			System.out.println("Esperando conexión entrando en el puerto " + puerto + "...");
			socket = serverSocket.accept(); // a la escucha de recibir peticiones de cliente. Método bloqueante,
											// necesita multihilo
			System.out.println("Conexión establecida con: " + socket.getInetAddress().getHostName() + "\n\n");
		} catch (Exception e) {
			System.err.println("Servidor: Error en iniciarConexion: " + e.getMessage());
			// SALIR DEL SISTEMA
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
			System.err.println("Servidor: Error en la apertura de flujos" + e.getMessage());
		}
	}

	/**
	 * Lógica de contestar a mensajes del cliente
	 */
	public void recibir() {
		String mensajeCliente = "";
		try {
			do {
				mensajeCliente = bufferEntrada.readUTF();
				System.out.println("[Cliente] => " + mensajeCliente);

				switch (idioma) {
				case "esp":
					// ESPAÑOL
					switch (mensajeCliente) {
					case "reserva":
						enviar("Información de reserva");
						break;
					default:
						enviar("Podemos ayudarte con: 'reserva', 'precio', 'info'");
						break;
					}
					break;

				case "eng":
					// INGLÉS
					switch (mensajeCliente) {
					case "reservatiom":
						enviar("To make a reservation, simply download the app, select the date, location and type of room you prefer, and complete the booking process.");
						break;
					case "age":
						enviar("Children under 2 years old are considered babies and do not pay as guests.\r\n"
								+ "From 2 years up to 12, they are considered children and pay as such.\r\n"
								+ "From 13 years old they count as an adult and pay the full price");
						break;
					default:
						enviar("We can provide assistance with booking information");
						break;
					}
					break;

				default:
					// SELECCIONAR IDIOMA
					switch (mensajeCliente) {
					case "esp":
						idioma = "esp";
						COMANDO_TERMINACION = "salir";
						enviar("Bienvenid@ ¿En qué podemos ayudarte?");
						break;
					case "eng":
						idioma = "eng";
						COMANDO_TERMINACION = "exit";
						enviar("Welcome. How can we help?");
						break;
					default:
						enviar("Write 'eng' if you'd like to be assisted in english");
						enviar("Escribe 'esp' si deseas asistencia en español");
						break;
					}
					break;
				}

			} while (!mensajeCliente.equals(COMANDO_TERMINACION));
		} catch (IOException e) {
			System.err.println("Servidor: Error al recibir" + e.getMessage());
		}
	}

	/**
	 * Scanner de entrada de texto
	 * 
	 * @deprecated
	 */
	public void escribirDatos() {
		String entrada = "";
		while (true) {
			entrada = sc.nextLine();
			System.out.println("[Servidor] => " + entrada);
			if (entrada.length() > 0) {
				enviar(entrada);
			}
		}
	}

	/**
	 * Envia al cliente el String indicado
	 * 
	 * @param cadena
	 */
	public void enviar(String cadena) {
		try {
			bufferSalida.writeUTF(cadena);
			bufferSalida.flush();
		} catch (IOException e) {
			System.err.println("Servidor: Error al enviar " + e.getMessage());
		}
	}

	/**
	 * Cierra los buffers y el socket
	 */
	public void cerrarConexion() {
		try {
			bufferEntrada.close();
			bufferSalida.close();
			socket.close();
		} catch (IOException e) {
			System.err.println("Servidor: Error en cerrarConexion: " + e.getMessage());
		} finally {
			System.out.println("Conversación finalizada...");
			// SALIR DEL SISTEMA
			//System.exit(0);
		}
	}
}
