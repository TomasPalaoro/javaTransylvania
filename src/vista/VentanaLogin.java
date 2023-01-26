package vista;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;

import controlador.ControladorLogin;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;

public class VentanaLogin {

	ControladorLogin controladorLogin = new ControladorLogin(this);
	private JFrame frame;
	private JPanel panel;
	private JButton btnEntrar;
	private JLabel lblContrasena;
	private JLabel lblEmail;
	private JTextField tfEmail;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JLabel lblTitulo;
	private JPasswordField tfContrasena;

	public VentanaLogin() {
		initialize();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width/2, screenSize.height/2);
		frame.getRootPane().setDefaultButton(btnEntrar);
		btnEntrar.setActionCommand("LOGIN");
		btnEntrar.addActionListener(controladorLogin);
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 64));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(5, 5));
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 64), 6, true));
		panel.setBackground(new Color(0, 0, 160));
		
		lblContrasena = new JLabel("CONTRASEÑA");
		lblContrasena.setForeground(new Color(255, 255, 255));
		lblContrasena.setHorizontalAlignment(SwingConstants.TRAILING);
		lblContrasena.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		
		lblEmail = new JLabel("E-MAIL");
		lblEmail.setForeground(new Color(255, 255, 255));
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setFont(new Font("Microsoft JhengHei", Font.BOLD, 18));
		
		tfEmail = new JTextField();
		tfEmail.setToolTipText("E-mail");
		tfEmail.setColumns(10);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
		btnEntrar = new JButton("ENTRAR");
		btnEntrar.setForeground(new Color(255, 255, 255));
		btnEntrar.setBackground(new Color(0, 0, 64));
		btnEntrar.setVerticalAlignment(SwingConstants.BOTTOM);
		btnEntrar.setFont(new Font("Microsoft JhengHei", Font.BOLD | Font.ITALIC, 16));
		
		lblTitulo = new JLabel("INICIO DE SESIÓN");
		lblTitulo.setForeground(new Color(0, 0, 64));
		lblTitulo.setFont(new Font("Microsoft JhengHei", Font.BOLD, 38));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfContrasena = new JPasswordField();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(53)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblContrasena)
						.addComponent(lblEmail))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(296)
							.addComponent(btnEntrar, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(tfEmail, GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
								.addComponent(tfContrasena, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))))
					.addGap(93))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(123)
					.addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
					.addGap(129))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(53)
					.addComponent(lblTitulo, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addGap(30)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrasena)
						.addComponent(tfContrasena, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(btnEntrar)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		panel_1 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
		flowLayout_2.setHgap(0);
		flowLayout_2.setVgap(0);
		frame.getContentPane().add(panel_1, BorderLayout.NORTH);
		
		panel_2 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_2.getLayout();
		flowLayout_3.setVgap(0);
		flowLayout_3.setHgap(0);
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		
		panel_3 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_3.getLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		frame.getContentPane().add(panel_3, BorderLayout.WEST);
		
		panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		frame.getContentPane().add(panel_4, BorderLayout.EAST);
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTextField getTfContrasena() {
		return tfContrasena;
	}

	public JTextField getTfEmail() {
		return tfEmail;
	}
}
