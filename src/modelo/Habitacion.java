package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.mariadb.jdbc.Statement;

import conexion.ConexionBD;

/**
 * Modelo habitación con métodos insert y update
 * @author Tomas
 *
 */
public class Habitacion {
	
	int id, cantidad, numero_maximo_personas, numero_camas;
	String nombre, descripcion, fecha_baja, created_at, updated_at;
	double precio;
	
	ConexionBD conexionBD;
	Statement stmt;
	ResultSet rs;
	
	public Habitacion(int id, String nombre, String descripcion, int cantidad, double precio, int numero_maximo_personas,
			int numero_camas,  String fecha_baja, String created_at, String updated_at) {
		this.id = id;
		this.cantidad = cantidad;
		this.numero_maximo_personas = numero_maximo_personas;
		this.numero_camas = numero_camas;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fecha_baja = fecha_baja;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.precio = precio;
	}
	
	public Habitacion(int id, String nombre, String descripcion, int cantidad, double precio, int numero_maximo_personas,
			int numero_camas) {
		this.id = id;
		this.cantidad = cantidad;
		this.numero_maximo_personas = numero_maximo_personas;
		this.numero_camas = numero_camas;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	public Habitacion() {}
	
	/**
	 * Inserta la habitación con los atributos instanciados en la base de datos
	 * @return true si finaliza
	 */
	public boolean insert() {		
		try {
			conexionBD = ConexionBD.getInstance();
			stmt = conexionBD.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT * FROM habitaciones");
			
			rs.moveToInsertRow();
			rs.updateString("nombre", this.nombre);
			rs.updateString("descripcion", this.descripcion);
			rs.updateInt("cantidad", this.cantidad);
			rs.updateInt("numero_maximo_personas", this.numero_maximo_personas);
			rs.updateInt("numero_camas", this.numero_camas);
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
	
	/**
	 * Actualiza usando los atributos instanciados la habitación con la id introducida
	 * @param id
	 * @return true si finaliza
	 */
	public boolean update(int id) {
		try {
			conexionBD = ConexionBD.getInstance();
			stmt = conexionBD.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT * FROM habitaciones WHERE id = '"+ id +"' LIMIT 1");
			
			rs.last();
			if (!(this.nombre == null)) rs.updateString("nombre", this.nombre);
			if (!(this.descripcion == null)) rs.updateString("descripcion", this.descripcion);
			rs.updateInt("cantidad", this.cantidad);
			rs.updateInt("numero_maximo_personas", this.numero_maximo_personas);
			rs.updateInt("numero_camas", this.numero_camas);
			rs.updateDouble("precio", this.precio);
			rs.updateRow();
			System.out.println("update finalizado");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Actualiza el campo fecha de baja de la reserva para ocultarla de la tabla
	 * 
	 * @param id
	 * @return true si finaliza
	 */
	public boolean darDeBaja(int id) {
		try {
			conexionBD = ConexionBD.getInstance();
			stmt = conexionBD.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT * FROM habitaciones WHERE id = '" + id + "' LIMIT 1");

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

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	/**
	 * Convierte y valida la calidad introducida
	 * @param cad
	 */
	public void setCantidad(String cad) {
		if (cad==null) throw new NullPointerException("null cantidad");
		if (cad.equals("")) cad = "10";
		try {
			this.cantidad = Integer.parseInt(cad);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Cantidad no válida");
		}
	}
	public int getNumero_maximo_personas() {
		return numero_maximo_personas;
	}
	/**
	 * Convierte y valida el número máximo de personas introducido
	 * @param cad
	 */
	public void setNumero_maximo_personas(String cad) {
		if (cad==null) throw new NullPointerException("null num maximo");
		if (cad.equals("")) cad = "1";
		try {
			this.numero_maximo_personas = Integer.parseInt(cad);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Número máximo no válido");
		}
	}
	public int getNumero_camas() {
		return numero_camas;
	}
	/**
	 * Convierte y valida el número de camas introducido
	 * @param cad
	 */
	public void setNumero_camas(String cad) {
		if (cad==null) throw new NullPointerException("null num camas");
		if (cad.equals("")) cad = "1";
		try {
			this.numero_camas = Integer.parseInt(cad);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Número de camas no válido");
		}
	}
	public String getNombre() {
		return nombre;
	}
	/**
	 * Valida el nombre introducido
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		if (nombre==null) throw new NullPointerException();
		if (nombre.equals("")) throw new IllegalArgumentException("El campo nombre no puede estar vacío");
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
	public double getPrecio() {
		return precio;
	}
	/**
	 * Convierte y valida el precio introducido
	 * @param cad
	 */
	public void setPrecio(String cad) {
		try {
			this.precio = Double.parseDouble(cad);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Precio no válido");
		}
	}
}
