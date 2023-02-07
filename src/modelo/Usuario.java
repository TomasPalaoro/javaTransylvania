package modelo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.regex.Pattern;

import org.mariadb.jdbc.Statement;

import conexion.ConexionBD;
import conexion.PeticionHTTP;

/**
 * Modelo usuario encargado del login, con métodos insert, update y darDeBaja.
 * 
 * @author Tomas
 *
 */
public class Usuario {

	private static final String POST_URL = "http://localhost/gestionhotelera/sw_user.php";

	String email, password, token, fecha_validez_token, nombre, apellidos, telefono, fecha_baja, created_at, updated_at;

	public Usuario(String email, String password, String token, String fecha_validez_token, String nombre,
			String apellidos, String telefono, String fecha_baja, String created_at, String updated_at) {
		this.email = email;
		this.password = password;
		this.token = token;
		this.fecha_validez_token = fecha_validez_token;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.fecha_baja = fecha_baja;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public Usuario(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Usuario() {
	}

	/**
	 * Método de inicio de sesión que envía una petición HTTP por post
	 * @return
	 */
	public boolean login() {
		try {
			if (PeticionHTTP.loginPOST(this, POST_URL)) {
				System.out.println("LOGGEADO CORRECTAMENTE");
				return true;
			} else {
				System.out.println("INCORRECTO");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Inserta el usuario con los atributos instanciados en la base de datos
	 * @return true si finaliza
	 */
	public boolean insert() {
		try {
			ConexionBD conexionBD = ConexionBD.getInstance();
			Statement stmt = conexionBD.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT * FROM users");

			rs.moveToInsertRow();
			rs.updateString("email", this.email);
			rs.updateString("password", this.password);
			rs.updateString("nombre", this.nombre);
			rs.updateString("apellidos", this.apellidos);
			rs.updateString("telefono", this.telefono);
			rs.insertRow();
			rs.moveToCurrentRow();
			System.out.println("insert finalizado");
			return true;

		} catch (SQLIntegrityConstraintViolationException e) {
			System.err.println(e.getMessage());
			return false;
		} catch (SQLException e1) {
			System.err.println(e1.getMessage());
			return false;
		}
	}

	/**
	 * Actualiza usando los atributos instanciados el usuario con el email introducido
	 * @param id
	 * @return true si finaliza
	 */
	public boolean update(String email) {
		try {
			ConexionBD conexionBD = ConexionBD.getInstance();
			Statement stmt = conexionBD.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE email = '" + email + "' LIMIT 1");

			rs.last();
			if (!(this.email == null))
				rs.updateString("email", this.email);
			if (!(this.password == null))
				rs.updateString("password", this.password);
			if (!(this.nombre == null))
				rs.updateString("nombre", this.nombre);
			if (!(this.apellidos == null))
				rs.updateString("apellidos", this.apellidos);
			if (!(this.telefono == null))
				rs.updateString("telefono", this.telefono);
			rs.updateRow();
			System.out.println("update finalizado");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Actualiza el campo fecha de baja del usuario para ocultarlo de la tabla
	 * 
	 * @param id
	 * @return true si finaliza
	 */
	public boolean darDeBaja(String email) {
		try {
			ConexionBD conexionBD = ConexionBD.getInstance();
			Statement stmt = conexionBD.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE email = '" + email + "' LIMIT 1");

			rs.last();
			rs.updateString("fecha_baja", this.fecha_baja);
			rs.updateRow();
			System.out.println("remove finalizado");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public final static boolean isValidPhone(String target) {
		return Pattern.compile("^(\\+34|0034|34)?[6789]\\d{8}$").matcher(target).matches();
	}

	public final static boolean isValidPassword(String target) {
		return Pattern.compile("^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{4,12}$").matcher(target).matches();
	}

	public final static boolean isValidEmail(String target) {
		return Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$").matcher(target).matches();
	}

	public final static boolean isValidName(String target) {
		return Pattern.compile("^(?=.*[a-zA-Z가-힣])[a-zA-Z가-힣]{1,}$").matcher(target).matches();
	}

	public String getEmail() {
		return email;
	}

	/**
	 * Valida el email introducido
	 * @param email
	 */
	public void setEmail(String email) {
		if (email == null)
			throw new NullPointerException();
		if (email.equals(""))
			throw new IllegalArgumentException("El campo e-mail no puede estar vacío");
		if (!isValidEmail(email))
			throw new IllegalArgumentException("E-mail no válido");
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * Valida la contraseña introducida
	 * @param password
	 */
	public void setPassword(String password) {
		if (password == null)
			throw new NullPointerException();
		if (password.equals(""))
			throw new IllegalArgumentException("El campo contraseña no puede estar vacío");
		// if (!isValidPassword(password)) throw new
		// IllegalArgumentException("Contraseña inválida");
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFecha_validez_token() {
		return fecha_validez_token;
	}

	public void setFecha_validez_token(String fecha_validez_token) {
		this.fecha_validez_token = fecha_validez_token;
	}

	public String getNombre() {
		if (nombre == null)
			return "";
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		if (apellidos == null)
			return "";
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTelefono() {
		if (telefono == null)
			return "";
		return telefono;
	}

	/**
	 * Valida el teléfono introducido
	 * @param telefono
	 */
	public void setTelefono(String telefono) {
		if (!isValidPhone(telefono))
			throw new IllegalArgumentException("Teléfono no válido");
		this.telefono = telefono;
	}

	public String getFecha_baja() {
		return fecha_baja;
	}

	public void setFecha_baja(String fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
}
