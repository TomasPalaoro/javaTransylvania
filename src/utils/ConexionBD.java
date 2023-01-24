package utils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.Connection;
import org.mariadb.jdbc.Statement;

public class ConexionBD {
	String db;
	Connection conexion;
	Connection c;
	Statement s;
	ResultSet rs;
	public ConexionBD() {
		db = "gestion_hotelera";
		conexion = null;
		c = null;
		s = null;
		rs = null;
		conectarBaseDeDatos();
	}

	public void conectarBaseDeDatos() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			//objeto conexion inicializar la conexion
			conexion =	(Connection) DriverManager.getConnection("jdbc:mariadb://localhost:3306/"+db,"root","");
			if (conexion != null){
				System.out.println("Conexión a base de datos "+ db +" OK");		
			}else {
				System.err.println("Conexión vacía");
			}
		} catch (ClassNotFoundException e) {
			System.
			out.println(e.getMessage());
		} catch (SQLException e) {
			System.
			out.println(e.getMessage());
		}
	}

	public void desconectar() {		
		try {
			if (s != null) s.close();
			if (rs != null) rs.close();
			if (conexion != null) conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
