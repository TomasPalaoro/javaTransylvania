package modelo;

import java.sql.Timestamp;
import java.util.Date;

import utils.ConexionBD;

public class Reserva {
	String id, fecha, fecha_entrada, fecha_salida, user_id, fecha_baja, created_at, updated_at;
	int numero_adultos, numero_ninyos;

	public Reserva(String id, String fecha, String fecha_entrada, String fecha_salida, int numero_adultos,
			int numero_ninyos, String user_id, String fecha_baja, String created_at, String updated_at) {
		this.id = id;
		this.fecha = fecha;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.user_id = user_id;
		this.fecha_baja = fecha_baja;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.numero_adultos = numero_adultos;
		this.numero_ninyos = numero_ninyos;
	}

	/**
	 * Constructor que genera la fecha actual para el atributo fecha
	 * @param fecha_entrada
	 * @param fecha_salida
	 * @param numero_adultos
	 * @param numero_ninyos
	 * @param user_id
	 */
	public Reserva(String fecha_entrada, String fecha_salida, int numero_adultos, int numero_ninyos,
			String user_id) {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());;
		this.fecha = timestamp.toString();
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.user_id = user_id;
		this.numero_adultos = numero_adultos;
		this.numero_ninyos = numero_ninyos;
	}

	/**
	 * Inserta en la BD el objeto
	 * 
	 * @return int Resultado de la operación
	 */
	public int insert() {
		int res = 0;
		ConexionBD conexion = new ConexionBD();
		String query = "Insert into reservas (fecha,fecha_entrada,fecha_salida,numero_adultos,numero_ninyos,user_id) values ('"
				+ this.fecha + "','" + this.fecha_entrada + "','" + this.fecha_salida + "','" + this.numero_adultos
				+ "','" + this.numero_ninyos + "','" + this.user_id + "')";
		res = conexion.executeChanges(query);
		conexion.desconectar();
		return res;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the fecha_entrada
	 */
	public String getFecha_entrada() {
		return fecha_entrada;
	}

	/**
	 * @param fecha_entrada the fecha_entrada to set
	 */
	public void setFecha_entrada(String fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	/**
	 * @return the fecha_salida
	 */
	public String getFecha_salida() {
		return fecha_salida;
	}

	/**
	 * @param fecha_salida the fecha_salida to set
	 */
	public void setFecha_salida(String fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the fecha_baja
	 */
	public String getFecha_baja() {
		return fecha_baja;
	}

	/**
	 * @param fecha_baja the fecha_baja to set
	 */
	public void setFecha_baja(String fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	/**
	 * @return the created_at
	 */
	public String getCreated_at() {
		return created_at;
	}

	/**
	 * @param created_at the created_at to set
	 */
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	/**
	 * @return the updated_at
	 */
	public String getUpdated_at() {
		return updated_at;
	}

	/**
	 * @param updated_at the updated_at to set
	 */
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	/**
	 * @return the numero_adultos
	 */
	public int getNumero_adultos() {
		return numero_adultos;
	}

	/**
	 * @param numero_adultos the numero_adultos to set
	 */
	public void setNumero_adultos(int numero_adultos) {
		this.numero_adultos = numero_adultos;
	}

	/**
	 * @return the numero_ninyos
	 */
	public int getNumero_ninyos() {
		return numero_ninyos;
	}

	/**
	 * @param numero_ninyos the numero_ninyos to set
	 */
	public void setNumero_ninyos(int numero_ninyos) {
		this.numero_ninyos = numero_ninyos;
	}
}
