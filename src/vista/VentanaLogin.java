package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;

import controlador.ControladorLogin;
import utils.Colores;
import utils.StyledButtonUI;
import utils.TextPrompt;

import javax.swing.JTextField;
import javax.swing.JButton;

import com.k33ptoo.components.KGradientPanel;

import javax.swing.JPasswordField;

/**
 * Vista que contiene los componentes necesarios para el inicio de sesión
 * 
 * @author Tomas
 *
 */
public class VentanaLogin {

	ControladorLogin controladorLogin = new ControladorLogin(this);
	private JFrame frmHotelTransylvania;
	private JPanel panel;
	private JButton btnEntrar;
	private JLabel lblContrasena;
	private JLabel lblEmail;
	private JTextField tfEmail;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPasswordField tfContrasena;

	public VentanaLogin() {
		initialize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frmHotelTransylvania.setSize(screenSize.width / 2, screenSize.height / 2);
		frmHotelTransylvania.getRootPane().setDefaultButton(btnEntrar);
		btnEntrar.setActionCommand("LOGIN");
		btnEntrar.addActionListener(controladorLogin);
	}

	/**
	 * Genera el fondo con gradiente de colores
	 * 
	 * @return gradiente
	 */
	private KGradientPanel gradiente() {
		KGradientPanel gradiente = new KGradientPanel();
		gradiente.setkEndColor(Colores.gradiente1);
		gradiente.setkGradientFocus(600);
		gradiente.setkStartColor(Colores.gradiente2);
		return gradiente;
	}

	/**
	 * Generado por WindowBuilder
	 */
	private void initialize() {
		frmHotelTransylvania = new JFrame();
		frmHotelTransylvania.setTitle("Hotel Transylvania - Login");
		frmHotelTransylvania.getContentPane().setBackground(new Color(0, 0, 64));
		frmHotelTransylvania.setResizable(false);
		frmHotelTransylvania.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHotelTransylvania.getContentPane().setLayout(new BorderLayout(5, 5));

		panel = gradiente();

		lblContrasena = new JLabel("CONTRASEÑA:");
		lblContrasena.setForeground(new Color(255, 255, 255));
		lblContrasena.setHorizontalAlignment(SwingConstants.TRAILING);
		lblContrasena.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));

		lblEmail = new JLabel("E-MAIL:");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setFont(new Font("Microsoft JhengHei", Font.BOLD, 14));

		tfEmail = new JTextField();
		tfEmail.setForeground(new Color(0, 0, 0));
		tfEmail.setBackground(new Color(255, 255, 255));
		tfEmail.setToolTipText("E-mail");
		tfEmail.setColumns(10);
		tfEmail.putClientProperty("JComponent.roundRect", true); // bordes redondeados
		frmHotelTransylvania.getContentPane().add(panel, BorderLayout.CENTER);

		btnEntrar = new JButton("ENTRAR");
		btnEntrar.setFont(new Font("Microsoft YaHei", Font.BOLD, 12));
		btnEntrar.setForeground(Colores.blanco);
		btnEntrar.setBackground(new Color(0, 0, 128));
		btnEntrar.setUI(new StyledButtonUI());

		tfContrasena = new JPasswordField();
		tfContrasena.setForeground(new Color(0, 0, 0));
		tfContrasena.setBackground(new Color(0x00FFFFFF));
		tfContrasena.putClientProperty("JComponent.roundRect", true); // bordes redondeados

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(VentanaLogin.class.getResource("/iconos/htlogoLittle.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnEntrar, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap(195, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblEmail)
								.addComponent(lblContrasena))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(tfEmail, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
								.addComponent(tfContrasena, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))))
					.addGap(93))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(432, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(215))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(47)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrasena)
						.addComponent(tfContrasena, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(btnEntrar, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(93, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);

		panel_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
		flowLayout_2.setHgap(0);
		flowLayout_2.setVgap(0);
		frmHotelTransylvania.getContentPane().add(panel_1, BorderLayout.NORTH);

		panel_2 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_2.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(0);
		frmHotelTransylvania.getContentPane().add(panel_2, BorderLayout.SOUTH);

		panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		frmHotelTransylvania.getContentPane().add(panel_3, BorderLayout.WEST);

		panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		frmHotelTransylvania.getContentPane().add(panel_4, BorderLayout.EAST);

		TextPrompt placeholder1 = new TextPrompt("Introduce tu e-mail", tfEmail);
		placeholder1.changeAlpha(0.75f);
		placeholder1.changeStyle(Font.ITALIC);
		TextPrompt placeholder2 = new TextPrompt("Introduce tu contraseña", tfContrasena);
		placeholder2.changeAlpha(0.75f);
		placeholder2.changeStyle(Font.ITALIC);
	}

	public JFrame getFrame() {
		return frmHotelTransylvania;
	}

	public void setFrame(JFrame frame) {
		this.frmHotelTransylvania = frame;
	}

	public JTextField getTfContrasena() {
		return tfContrasena;
	}

	public JTextField getTfEmail() {
		return tfEmail;
	}
}
