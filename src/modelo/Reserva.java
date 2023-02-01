package modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import conexion.ConexionBD;

public class Reserva {
	String fecha, fecha_entrada, fecha_salida, user_id, fecha_baja, created_at, updated_at;
	int id, numero_adultos, numero_ninyos;

	public Reserva(int id, String fecha, String fecha_entrada, String fecha_salida, int numero_adultos,
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

	
	public Reserva(String fecha, String fecha_entrada, String fecha_salida, int numero_adultos, int numero_ninyos,
			String user_id) {
		this.fecha = fecha;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.user_id = user_id;
		this.numero_adultos = numero_adultos;
		this.numero_ninyos = numero_ninyos;
	}
	
	public Reserva() {}

	/**
	 * Inserta en la BD el objeto
	 * 
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
	 * Convierte el string de fecha al formato fecha para compararla con la fecha actual
	 * @param fecha
	 * @return true si la fecha introducida es anterior a la actual
	 * @throws ParseException
	 */
	private boolean fechaAntigua(String fecha) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha1 = sdf.parse(fecha);
        Date currentTime = Calendar.getInstance().getTime();
        int result = fecha1.compareTo(currentTime);
        if (result < 0) return true;
        return false;
	}
	
	public void compararFechas() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date fecha1 = sdf.parse(fecha_entrada);
		Date fecha2 = sdf.parse(fecha_salida);
		int result = fecha1.compareTo(fecha2);
		if (result > 0) throw new IllegalArgumentException("La fecha de entrada no puede ser posterior a la salida");
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public String getFecha_entrada() {
		return fecha_entrada;
	}


	public void setFecha_entrada(String fecha_entrada) throws ParseException {
		if (fecha_entrada==null) throw new NullPointerException();
		if (fecha_entrada.equals("")) throw new IllegalArgumentException("El campo fecha de entrada no puede estar vacío");
		if (fechaAntigua(fecha_entrada)) throw new IllegalArgumentException("La fecha de entrada no puede ser anterior a la fecha actual");
		this.fecha_entrada = fecha_entrada;
	}


	public String getFecha_salida() {
		return fecha_salida;
	}


	public void setFecha_salida(String fecha_salida) throws ParseException {
		if (fecha_salida==null) throw new NullPointerException();
		if (fecha_salida.equals("")) throw new IllegalArgumentException("El campo fecha de salida no puede estar vacío");
		if (fechaAntigua(fecha_salida)) throw new IllegalArgumentException("La fecha de salida no puede ser anterior a la fecha actual");
		this.fecha_salida = fecha_salida;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		if (user_id==null) throw new NullPointerException();
		if (user_id.equals("")) throw new IllegalArgumentException("El campo usuario no puede estar vacío");
		this.user_id = user_id;
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


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getNumero_adultos() {
		return numero_adultos;
	}


	public void setNumero_adultos(int numero_adultos) {
		if (numero_adultos == 0) throw new IllegalArgumentException("Debe ir al menos un adulto");
		this.numero_adultos = numero_adultos;
	}


	public int getNumero_ninyos() {
		return numero_ninyos;
	}


	public void setNumero_ninyos(int numero_ninyos) {
		this.numero_ninyos = numero_ninyos;
	}

	
}
