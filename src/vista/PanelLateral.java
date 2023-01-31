package vista;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelLateral{
	
	static private JLabel lblNewLabel;
	static private JLabel lblNewLabel_1;
	static private JLabel lblNewLabel_2;
	static private JLabel lblNewLabel_3;
	static private JPanel panelContenedorTexto;
	static private JTextField textField;
	static private JPanel panelContenedorTexto_1;
	static private JTextField textField_1;
	static private JPanel panelContenedorTexto_2;
	static private JTextField textField_2;
	static private JPanel panelContenedorTexto_3;
	static private JTextField textField_3;
	static private JLabel lblNewLabel_4;
	static private JLabel lblNewLabel_5;
	static private JLabel lblNewLabel_6;
	static private JPanel panelContenedorTexto_4;
	static private JButton btnCrear;
	
	public static JPanel generar(VentanaPrincipal ventanaPrincipal) {
		JPanel panelLateral = new JPanel();
		panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
		
		lblNewLabel_4 = new JLabel("TABLA DE USUARIOS");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelLateral.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("----------------------------");
		panelLateral.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Crear nuevo usuario:");
		lblNewLabel_6.setMaximumSize(new Dimension(200, 50));
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panelLateral.add(lblNewLabel_6);
		
		lblNewLabel = new JLabel("E-mail");
		panelLateral.add(lblNewLabel);
		
		panelContenedorTexto = new JPanel();
		panelContenedorTexto.setMaximumSize(new Dimension(32767, 50));
		panelLateral.add(panelContenedorTexto);
		
		textField = new JTextField();
		panelContenedorTexto.add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Contraseña");
		panelLateral.add(lblNewLabel_1);
		
		panelContenedorTexto_1 = new JPanel();
		panelContenedorTexto_1.setMaximumSize(new Dimension(32767, 50));
		panelLateral.add(panelContenedorTexto_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panelContenedorTexto_1.add(textField_1);
		
		lblNewLabel_2 = new JLabel("Nombre");
		panelLateral.add(lblNewLabel_2);
		
		panelContenedorTexto_2 = new JPanel();
		panelContenedorTexto_2.setMaximumSize(new Dimension(32767, 50));
		panelLateral.add(panelContenedorTexto_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panelContenedorTexto_2.add(textField_2);
		
		lblNewLabel_3 = new JLabel("Apellido");
		panelLateral.add(lblNewLabel_3);
		
		panelContenedorTexto_3 = new JPanel();
		panelContenedorTexto_3.setMaximumSize(new Dimension(32767, 50));
		panelLateral.add(panelContenedorTexto_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		panelContenedorTexto_3.add(textField_3);
		
		panelContenedorTexto_4 = new JPanel();
		panelContenedorTexto_4.setMaximumSize(new Dimension(32767, 50));
		panelLateral.add(panelContenedorTexto_4);
		
		btnCrear = new JButton("CREAR");
		btnCrear.addActionListener(ventanaPrincipal.getControladorTablas());
		btnCrear.setActionCommand("CREARUSUARIO");
		panelContenedorTexto_4.add(btnCrear);
		
		return panelLateral;
	}
	
	

}
