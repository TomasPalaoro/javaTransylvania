package modelo;

import java.io.IOException;

import conexion.PeticionHTTP;

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
	
	public Usuario() {}

	public boolean login() {
		try {
			if (PeticionHTTP.loginPOST(this, POST_URL)) {
				System.out.println("LOGGEADO CORRECTAMENTE");
				System.out.println(this.getNombre()+" "+this.getToken());
				return true;
			}
			else {
				System.out.println("INCORRECTO");
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
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
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
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
