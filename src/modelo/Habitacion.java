package modelo;

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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getNumero_maximo_personas() {
		return numero_maximo_personas;
	}
	public void setNumero_maximo_personas(int numero_maximo_personas) {
		this.numero_maximo_personas = numero_maximo_personas;
	}
	public int getNumero_camas() {
		return numero_camas;
	}
	public void setNumero_camas(int numero_camas) {
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
	public void setPrecio(double precio) {
		this.precio = precio;
	}
}
