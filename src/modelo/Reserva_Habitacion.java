package modelo;

import utils.ConexionBD;

public class Reserva_Habitacion {
	int id, habitacion_id, reserva_id, cantidad;
	double precio;
	String created_at, updated_at;
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
	 * Inserta en la BD el objeto
	 * 
	 * @return int Resultado de la operación
	 */
	public int insert() {
		int res = 0;
		ConexionBD conexion = new ConexionBD();
		String query = "Insert into reservas_habitaciones (habitacion_id,reserva_id,cantidad,precio) values ('"
				+ this.habitacion_id + "','" + this.reserva_id + "','" + this.cantidad + "','" + this.precio + "')";
		res = conexion.executeChanges(query);
		conexion.desconectar();
		return res;
	}

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
