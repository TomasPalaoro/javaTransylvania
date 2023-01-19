package vista;



import javax.swing.JFrame;
import java.awt.Color;

public class VentanaPrincipal {

	private JFrame frame;
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public VentanaPrincipal() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(54, 19, 81));
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(900, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
