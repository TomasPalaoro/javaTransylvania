package iconos;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class CargarIcono {

	/** Devuelve un ImageIcon, o null si no se encuentra. */
	public static ImageIcon crearIcono(URL ruta, String descripcion, int dimensiones) {
	    if (ruta != null) {
	    	//CARGAR IMAGEN
	    	ImageIcon imageIcon = new ImageIcon(ruta,descripcion);
	    	//REESCALADO
	    	Image image = imageIcon.getImage();
	    	Image newimg = image.getScaledInstance(dimensiones, dimensiones,  Image.SCALE_SMOOTH); 
	    	imageIcon = new ImageIcon(newimg);
	        return imageIcon;
	    } else {
	        System.err.println("No se encontró el archivo: " + ruta);
	        return null;
	    }
	}
}
