package vista;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;

import conexion.ConexionBD;
import modelo.Reserva_Habitacion;

public class DialogoTablaReservas extends JDialog {
	private static final long serialVersionUID = 1L;
	
	ConexionBD conexionBD;
	ArrayList<Reserva_Habitacion> listaReservaHabitacion = new ArrayList<Reserva_Habitacion>();

	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public DialogoTablaReservas( JFrame frame, int idReserva) {
		super( frame, "Información de reserva", true );
		conexionBD = ConexionBD.getInstance();
        listaReservaHabitacion = conexionBD.obtenerInfoReserva(idReserva);
        Vector col = new Vector();
        col.add( "Id Habitacion" );
        col.add( "Cantidad" );
        col.add( "Precio" );
        Vector titulos = new Vector();
        titulos.add( "Id Habitacion" );
        titulos.add( "Cantidad" );
        titulos.add( "Precio" );
        
        Vector row = new Vector();
        row.add( titulos );
        for (int i = 0; i < listaReservaHabitacion.size(); i++) {
			Vector nuevaLinea = new Vector();
			nuevaLinea.add(listaReservaHabitacion.get(i).getHabitacion_id() + "");
			nuevaLinea.add(listaReservaHabitacion.get(i).getCantidad() + "");
			nuevaLinea.add(listaReservaHabitacion.get(i).getPrecio() + "");
			row.add(nuevaLinea);
		}
        
        JTable table = new JTable( row, col );
        Container c = getContentPane();
        c.setLayout( new BorderLayout() );
        c.add( table, BorderLayout.CENTER );
        this.pack();
        this.setLocation(100, 100);
        this.setSize(400,200);
        this.show();
    }
}
