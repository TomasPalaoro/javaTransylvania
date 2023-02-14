package clienteServidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.FAQs;

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

	public static String COMANDO_VOLVER = "volver";
	private String idioma = "";

	Scanner sc = new Scanner(System.in);

	ArrayList<FAQs> faqs = new ArrayList<FAQs>();

	private String categoria = "";
	private String todasCategoriasEsp = "";
	private String todasCategoriasEng = "";

	/**
	 * Hilo con interfaz runnable que establece el serversocket y da de alta los
	 * flujos de datos para poder contectarnos, por último recibiendo los datos. Al
	 * ser un hilo evita que se bloquee la aplicacion
	 * 
	 * @param puerto
	 */
	public void ejecutarConexion(int puerto) {
		iniciarFAQs();

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

	private boolean setCategoria(String busqueda) {
		for (FAQs faq : faqs) {
			if (faq.getCategoria().toLowerCase().equals(busqueda.toLowerCase())) {
				categoria = faq.getCategoria();
				return true;
			}
		}
		return false;
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

				if (idioma.equals("")) {
					// SELECCIONAR IDIOMA
					switch (mensajeCliente) {
					case "esp":
						idioma = "esp";
						COMANDO_VOLVER = "volver";
						enviar("Bienvenid@ ¿En qué podemos ayudarte?");
						enviar(mensajeAyuda());
						break;
					case "eng":
						idioma = "eng";
						COMANDO_VOLVER = "back";
						enviar("Welcome. How can we help?");
						enviar(mensajeAyuda());
						break;
					default:
						enviar("Write 'eng' if you'd like to be assisted in english");
						enviar("Escribe 'esp' si deseas asistencia en español");
						break;
					}
				} else {
					if (!categoria.equals("") && !mensajeCliente.equals(COMANDO_VOLVER)) {
						if (!respuestasCoincidentes(mensajeCliente))
							enviar(mensajeAyuda());
					} else if (mensajeCliente.equals(COMANDO_VOLVER)) {
						categoria = "";
						enviar(mensajeAyuda());
					} else {
						setCategoria(mensajeCliente);
						enviar(mensajeAyuda());
					}
				}

			} while (true);
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
		}
	}

	/**
	 * Mensaje genérico con posibles comandos
	 * @return mensaje
	 */
	private String mensajeAyuda() {
		String mensaje = "";
		if (idioma.equals("esp")) {
			switch (categoria) {
			case "reservas":
				mensaje = "RESERVAS, Te podemos ayudar a: 'Crear', 'Editar', 'Eliminar', 'Info'";
				break;
			case "usuarios":
				mensaje = "USUARIOS, Te podemos ayudar a: 'Registrar', 'Editar', 'Dar de baja'";
				break;
			case "habitaciones":
				mensaje = "HABITACIONES, Te podemos ayudar a: 'Crear', 'Editar', 'Eliminar'";
				break;
			default:
				mensaje = "Elige una categoria: " + todasCategoriasEsp;
				break;
			}
		} else if (idioma.equals("eng")) {
			switch (categoria) {
			case "reservations":
				mensaje = "RESERVATIONS, We can help with: 'Create', 'Edit', 'Delete', 'Info'";
				break;
			case "users":
				mensaje = "USERS, We can help with: 'Register', 'Edit', 'Deregister'";
				break;
			case "rooms":
				mensaje = "ROOMS, We can help with: 'Create', 'Edit', 'Delete'";
				break;
			default:
				mensaje = "Choose a category: " + todasCategoriasEng;
				break;
			}
		}
		return mensaje;
	}

	/**
	 * Compara la búsqueda con las respuestas de la categoría actual y muestra hasta
	 * un máximo de 3 coincidencias
	 * 
	 * @param búsqueda
	 * @return true si coincide con alguna
	 */
	private boolean respuestasCoincidentes(String busqueda) {
		boolean coincide = false;
		int resultadosMostrados = 0;
		if (busqueda.length() > 3) {
			for (FAQs faq : faqs) {
				if (faq.getCategoria().equals(categoria)) {
					if (faq.getRespuesta().toLowerCase().contains(busqueda.toLowerCase())
							&& (resultadosMostrados < 3)) {
						enviar(faq.getRespuesta());
						resultadosMostrados++;
						coincide = true;
					}
				}
			}
		}
		return coincide;
	}

	/**
	 * Lista de faqs en cada idioma
	 */
	private void iniciarFAQs() {
		// ESPAÑOL
		faqs.add(new FAQs("esp", "reservas", "Para crear y añadir una reserva pulsa el botón de añadir reserva"));
		faqs.add(new FAQs("esp", "reservas",
				"Para editar una reserva edita los campos de reserva en la tabla reservas"));
		faqs.add(new FAQs("esp", "reservas",
				"Para eliminar o cancelar una reserva pulsa el botón de borrar desde el modo edición de la tabla reservas"));
		faqs.add(new FAQs("esp", "reservas",
				"Puedes ver más información sobre las habitaciones que contiene una reserva haciendo doble click sobre una de ellas en la tabla"));
		//
		faqs.add(new FAQs("esp", "habitaciones",
				"Para crear y añadir una habitacion pulsa el botón de añadir habitacion"));
		faqs.add(new FAQs("esp", "habitaciones",
				"Para editar una habitacion pulsa edita los campos en la tabla habitaciones"));
		faqs.add(new FAQs("esp", "habitaciones",
				"Para eliminar una habitacion pulsa el botón de borrar habitacion en el modo edición"));
		//
		faqs.add(new FAQs("esp", "usuarios",
				"En la pestaña \"Ver usuarios\" puedes editar y dar de baja los usuarios activos. "));
		faqs.add(new FAQs("esp", "usuarios",
				"Puedes crear y registrar nuevos usuarios pulsando el botón de añadir usuario."));

		// INGLES
		faqs.add(new FAQs("eng", "reservations", "To create and add a reservation press the button add reservation"));
		faqs.add(new FAQs("eng", "reservations",
				"To edit a reservation, edit the reservation fields in the reservations table"));
		faqs.add(new FAQs("eng", "reservations",
				"To delete or cancel a reservation, press the delete button from the edition mode of the reservations table"));
		faqs.add(new FAQs("eng", "reservations",
				"You can see more information about the rooms that a reservation contains by double clicking on one of them in the table"));
		//
		faqs.add(new FAQs("eng", "rooms", "To create and add a room press the add room button"));
		faqs.add(new FAQs("eng", "rooms", "To edit a room press edit the fields in the rooms table"));
		faqs.add(new FAQs("eng", "rooms", "To delete a room press the delete room button"));
		//
		faqs.add(new FAQs("eng", "users", "In the \"View users\" tab you can edit and deregister active users."));
		faqs.add(new FAQs("eng", "users", "You can create and register new users by clicking the add user button."));

		for (FAQs faq : faqs) {
			if (faq.getIdioma().equals("esp") && !todasCategoriasEsp.contains(faq.getCategoria()))
				todasCategoriasEsp = todasCategoriasEsp + "'" + faq.getCategoria() + "' ";
		}

		for (FAQs faq : faqs) {
			if (faq.getIdioma().equals("eng") && !todasCategoriasEng.contains(faq.getCategoria()))
				todasCategoriasEng = todasCategoriasEng + "'" + faq.getCategoria() + "' ";
		}

	}
}
