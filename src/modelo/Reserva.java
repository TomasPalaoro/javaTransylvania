package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import org.mariadb.jdbc.Statement;

import conexion.ConexionBD;

public class Reserva {
	String fecha, fecha_entrada, fecha_salida, user_id, fecha_baja, created_at, updated_at;
	int id, numero_adultos, numero_ninyos;
	
	ConexionBD conexionBD;
	Statement stmt;
	ResultSet rs;

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
	
	public Reserva() {
	}

	/**
	 * Inserta en la BD el objeto
	 * 
	 */
	public boolean insert() {		
		try {
			conexionBD = ConexionBD.getInstance();
			stmt = conexionBD.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT * FROM reservas");
			
			rs.moveToInsertRow();
			rs.updateString("fecha", this.fecha);
			rs.updateString("fecha_entrada", this.fecha_entrada);
			rs.updateString("fecha_salida", this.fecha_salida);
			rs.updateInt("numero_adultos", this.numero_adultos);
			rs.updateInt("numero_ninyos", this.numero_ninyos);
			rs.updateString("user_id", this.user_id);
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
	
	public boolean update(int id) {
		try {
			conexionBD = ConexionBD.getInstance();
			stmt = conexionBD.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT * FROM reservas WHERE id = '"+ id +"' LIMIT 1");
			
			rs.last();
			if (this.fecha != null) rs.updateString("fecha", this.fecha);
			if (this.fecha_entrada != null) rs.updateString("fecha_entrada", this.fecha_entrada);
			if (this.fecha_salida != null) rs.updateString("fecha_salida", this.fecha_salida);
			if (this.numero_adultos != 0) rs.updateInt("numero_adultos", this.numero_adultos);
			if (this.numero_ninyos != 0) rs.updateInt("numero_ninyos", this.numero_ninyos);
			if (this.user_id != null) rs.updateString("user_id", this.user_id);
			rs.updateRow();
			System.out.println("update finalizado");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean darDeBaja(int id) {
		try {
			conexionBD = ConexionBD.getInstance();
			stmt = conexionBD.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT * FROM reservas WHERE id = '"+ id +"' LIMIT 1");
			
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
		ConexionBD conexionBD = ConexionBD.getInstance();
		if (user_id==null) throw new NullPointerException();
		if (user_id.equals("")) throw new IllegalArgumentException("El campo usuario no puede estar vacío");
		if (!conexionBD.usuarioExiste(user_id)) {
			throw new NullPointerException("El usuario no existe");
		}
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


	public void setNumero_adultos(String cad) {
		if (cad==null) throw new NullPointerException("null num adultos");
		if (cad.equals("")) cad = "10";
		try {
			this.numero_adultos = Integer.parseInt(cad);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Número de adultos no válida");
		}
	}


	public int getNumero_ninyos() {
		return numero_ninyos;
	}


	public void setNumero_ninyos(String cad) {		
		if (cad==null) throw new NullPointerException("null num niños");
		if (cad.equals("")) cad = "10";
		try {
			this.numero_ninyos = Integer.parseInt(cad);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Número de niños no válida");
		}
	}
	
}
