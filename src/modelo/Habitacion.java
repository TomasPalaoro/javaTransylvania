package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.Statement;

import conexion.ConexionBD;

public class Habitacion {
	
	int id, cantidad, numero_maximo_personas, numero_camas;
	String nombre, descripcion, fecha_baja, created_at, updated_at;
	double precio;
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
	
	public boolean insert() {		
		try {
			ConexionBD conexionBD = ConexionBD.getInstance();
			Statement stmt = conexionBD.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT * FROM habitaciones");
			
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
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean update(int id) {
		try {
			ConexionBD conexionBD = ConexionBD.getInstance();
			Statement stmt = conexionBD.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT * FROM habitaciones WHERE id = '"+ id +"' LIMIT 1");
			
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
	
	public Habitacion() {}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cad) {
		int cantidad = Integer.parseInt(cad);
		this.cantidad = cantidad;
	}
	public int getNumero_maximo_personas() {
		return numero_maximo_personas;
	}
	public void setNumero_maximo_personas(String cad) {
		int numero_maximo_personas = Integer.parseInt(cad);
		this.numero_maximo_personas = numero_maximo_personas;
	}
	public int getNumero_camas() {
		return numero_camas;
	}
	public void setNumero_camas(String cad) {
		int numero_camas = Integer.parseInt(cad);
		this.numero_camas = numero_camas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
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
	public void setPrecio(String cad) {
		double precio = Double.parseDouble(cad);
		this.precio = precio;
	}
}
