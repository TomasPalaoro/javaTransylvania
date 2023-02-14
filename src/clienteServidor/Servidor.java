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

	public static String COMANDO_TERMINACION = "exit";
	private String idioma = "";

	Scanner sc = new Scanner(System.in);

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
			// System.exit(0);
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
					case "reservas":
						enviar("Para crear una reserva dirigete a la pestaña principal de la aplicación e introduce los campos necesarios. Elimina y edita reservas desde la pestaña \"Ver reservas\". Doble click sobre una reserva de la tabla para ver más información sobre ella.");
						break;
					case "usuarios":
						enviar("En la pestaña \"Ver usuarios\" puedes editar y dar de baja los usuarios activos. También puedes registrar nuevos usuarios.");
						break;
					case "habitaciones":
						enviar("Puedes editar y eliminar habitaciones en la pestaña \"Ver habitaciones\". Para crear una nueva habitación pulsa el botón de añadir habitación.");
						break;
					case "edad":
						enviar("Los niños menores de 2 años se consideran bebés y no pagan como huéspedes. Desde los 2 años hasta los 12, se consideran niños y pagan como tales. A partir de 13 años cuentan como adulto y pagan el precio completo.");
						break;
					case "eng":
						idioma = "eng";
						COMANDO_TERMINACION = "exit";
						enviar("Welcome. How can we help?");
						break;
					default:
						enviar("Podemos ayudarte con los siguientes temas: 'reservas', 'usuarios', 'habitaciones', 'edad'");
						break;
					}
					break;

				case "eng":
					// INGLÉS
					switch (mensajeCliente) {
					case "reservations":
						enviar("To create a reservation, go to the main tab of the application and fill the necessary fields. You're able to delete and edit reservations from the \"View reservations\" tab. Double click on a reservation from the table to see more information about it.");
						break;
					case "users":
						enviar("In the \"View users\" tab you can edit and unsubscribe active users. You can also register new users by clicking the add user button.");
						break;
					case "rooms":
						enviar("You can edit and delete rooms in the \"View Rooms\" tab. To create a new room press the add new room button.");
						break;
					case "age":
						enviar("Children under 2 years old are considered babies and do not pay as guests. From 2 years up to 12, they are considered children and pay as such. From 13 years old they count as an adult and pay the full price.");
						break;
					case "esp":
						idioma = "esp";
						COMANDO_TERMINACION = "salir";
						enviar("Bienvenid@ ¿En qué podemos ayudarte?");
						break;
					default:
						enviar("We can provide assistance with the following topics: 'reservations', 'users', 'rooms', 'age'");
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
			serverSocket.close();
		} catch (IOException e) {
			System.err.println("Servidor: Error en cerrarConexion: " + e.getMessage());
		} finally {
			System.out.println("Conversación finalizada...");
			// SALIR DEL SISTEMA
			// System.exit(0);
		}
	}
}
