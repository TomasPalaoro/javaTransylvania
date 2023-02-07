package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.mariadb.jdbc.Statement;

import conexion.ConexionBD;

/**
 * Modelo reserva_habitacion con método insert
 * 
 * @author Tomas
 *
 */
public class Reserva_Habitacion {
	int id, habitacion_id, reserva_id, cantidad;
	double precio;
	String created_at, updated_at;

	ConexionBD conexionBD;
	Statement stmt;
	ResultSet rs;

	public Reserva_Habitacion(int id, int habitacion_id, int reserva_id, int cantidad, double precio, String created_at,
			String updated_at) {
		this.id = id;
		this.habitacion_id = habitacion_id;
		this.reserva_id = reserva_id;
		this.cantidad = cantidad;
		this.precio = precio;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public Reserva_Habitacion(int habitacion_id, int reserva_id, int cantidad, double precio) {
		this.habitacion_id = habitacion_id;
		this.reserva_id = reserva_id;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	/**
	 * Inserta la reserva_habitación con los atributos instanciados en la base de
	 * datos
	 * 
	 * @return true si finaliza
	 */
	public boolean insert() {
		try {
			conexionBD = ConexionBD.getInstance();
			stmt = conexionBD.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT * FROM reservas_habitaciones");

			rs.moveToInsertRow();
			rs.updateInt("habitacion_id", this.habitacion_id);
			rs.updateInt("reserva_id", this.reserva_id);
			rs.updateInt("cantidad", this.cantidad);
			rs.updateDouble("precio", this.precio);
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

	// TODO Validar setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHabitacion_id() {
		return habitacion_id;
	}

	public void setHabitacion_id(int habitacion_id) {
		this.habitacion_id = habitacion_id;
	}

	public int getReserva_id() {
		return reserva_id;
	}

	public void setReserva_id(int reserva_id) {
		this.reserva_id = reserva_id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
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
