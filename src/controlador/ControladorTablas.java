package controlador;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import conexion.ConexionBD;
import modelo.Reserva;
import modelo.Usuario;
import vista.VentanaPrincipal;

public class ControladorTablas implements ActionListener {
	
	Usuario usuario;
	VentanaPrincipal ventanaPrincipal;
	ConexionBD conexionBD;
	CardLayout cardLayout;
	
	public ArrayList<Usuario> listaUsers;
	public ArrayList<Reserva> listaReservas;
	public int camposPorPagina;
	public int primerRegistroMostrado;
	int numPagina;
	
	boolean editando;
	
	JTable tablaUsuarios, tablaReservas;

	public ControladorTablas(VentanaPrincipal ventanaPrincipal) {
		conexionBD = ConexionBD.getInstance();
		listaUsers = conexionBD.obtenerTodosUsuarios();
		listaReservas = conexionBD.obtenerTodasReservas();
		editando = false;
		camposPorPagina = 5;
		primerRegistroMostrado = 0;
		numPagina = 1;
		this.ventanaPrincipal = ventanaPrincipal;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		try {
			System.out.println(listaUsers.get(tablaUsuarios.getSelectedRow()+((numPagina-1)*camposPorPagina)).getEmail());
		} catch (Exception e2) {
			e2.getMessage();
		}
		
		switch (comando) {
		case "SIGUIENTEUSUARIO":
			paginar("USUARIO","AVANZAR");
			break;
		case "SIGUIENTERESERVA":
			paginar("RESERVA","AVANZAR");
			break;
		case "ANTERIORUSUARIO":
			paginar("USUARIO","RETROCEDER");
			break;
		case "ANTERIORRESERVA":
			paginar("RESERVA","RETROCEDER");
			break;
		case "PRIMERUSUARIO":
			paginar("USUARIO","PRINCIPIO");
			break;
		case "PRIMERARESERVA":
			paginar("RESERVA","PRINCIPIO");
			break;
		case "ULTIMOUSUARIO":
			paginar("USUARIO","FIN");
			break;
		case "ULTIMARESERVA":
			paginar("RESERVA","FIN");
			break;
		case "ELIMINARUSUARIO":
			try {
				String email = listaUsers.get(tablaUsuarios.getSelectedRow()).getEmail();
				int input = JOptionPane.showConfirmDialog(ventanaPrincipal.getFrame(), 
	                    "¿Deseas eliminar el registro de "+email+"?", "Eliminar usuario", JOptionPane.YES_NO_CANCEL_OPTION);
	    		if (input == 0) {
	    			Usuario usuarioEliminar = new Usuario();
	    			usuarioEliminar.setFecha_baja(fechaActual());
	    			usuarioEliminar.darDeBaja(email);
	    			resetearPaginas();
	    		}
			} catch (IndexOutOfBoundsException e2) {}
			break;
		case "ACTIVAREDICIONUSUARIO":
			JButton botonEditarUsers = (JButton) e.getSource();
			if (!editando){
				tablaUsuarios.setEnabled(true);
				tablaUsuarios.setShowGrid(true);
				ventanaPrincipal.getBtnEliminarUser().setVisible(true);
				editando = true;
				botonEditarUsers.setText("DEJAR DE EDITAR");
			}else {
				tablaUsuarios.setEnabled(false);
				tablaUsuarios.setShowGrid(false);
				ventanaPrincipal.getBtnEliminarUser().setVisible(false);
				editando = false;
				botonEditarUsers.setText("EDITAR / ELIMINAR");
			}
			break;
		case "MOSTRARLATERALUSUARIO":
			JButton botonMostrarLateral = (JButton) e.getSource();
			ventanaPrincipal.getPanelLateralUsers().setVisible(true);
			botonMostrarLateral.setEnabled(false);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Hello world");
			break;
		}
	}
	
	public void resetearPaginas() {
		listaUsers = conexionBD.obtenerTodosUsuarios();
		listaReservas = conexionBD.obtenerTodasReservas();
		camposPorPagina = 5;
		primerRegistroMostrado = 0;
		numPagina = 1;
		ventanaPrincipal.getLblNumPaginaUser().setText(numPagina+"");
		//TODO lblpaginareservas
		ventanaPrincipal.getBtnBackUser().setEnabled(false);
		ventanaPrincipal.getBtnBackReserva().setEnabled(false);
		ventanaPrincipal.getBtnLastUser().setEnabled(true);
		ventanaPrincipal.getBtnLastReserva().setEnabled(true);
		ventanaPrincipal.getBtnFirstUser().setEnabled(false);
		ventanaPrincipal.getBtnFirstReserva().setEnabled(false);
		ventanaPrincipal.getBtnNextUser().setEnabled(true);
		ventanaPrincipal.getBtnNextReserva().setEnabled(true);
		crearTabla("USUARIO");
		crearTabla("RESERVA");
	}
	
	private void paginar(String modelo, String accion) {
		ArrayList<?> arrayModelo = null;
		JButton botonBack = null;
		JButton botonNext = null;
		JButton botonFirst = null;
		JButton botonLast = null;
		JLabel labelPagina = null;
		switch (modelo) {
		case "USUARIO":
			arrayModelo = listaUsers;
			botonNext = ventanaPrincipal.getBtnNextUser();
			botonBack = ventanaPrincipal.getBtnBackUser();
			botonFirst = ventanaPrincipal.getBtnFirstUser();
			botonLast = ventanaPrincipal.getBtnLastUser();
			labelPagina = ventanaPrincipal.getLblNumPaginaUser();
			break;
		case "RESERVA":
			arrayModelo = listaReservas;
			botonNext = ventanaPrincipal.getBtnNextReserva();
			botonBack = ventanaPrincipal.getBtnBackReserva();
			botonFirst = ventanaPrincipal.getBtnFirstReserva();
			botonLast = ventanaPrincipal.getBtnLastReserva();
			break;
		}
		switch (accion) {
		case "PRINCIPIO":
			botonNext.setEnabled(true);
			botonLast.setEnabled(true);
			primerRegistroMostrado = 0;
			botonBack.setEnabled(false);
			botonFirst.setEnabled(false);
			numPagina = 1;
			break;
		case "FIN":
			//CALCULAR ULTIMA PAGINA
			int ultimaPagina;
			if (arrayModelo.size()%camposPorPagina==0) ultimaPagina = arrayModelo.size()/camposPorPagina;
			else ultimaPagina = (arrayModelo.size()/camposPorPagina)+1;
			botonBack.setEnabled(true);
			botonFirst.setEnabled(true);
			for (int i = 0; i < ultimaPagina; i++) {
				primerRegistroMostrado = primerRegistroMostrado + camposPorPagina;
				i++;
			}
			botonNext.setEnabled(false);
			botonLast.setEnabled(false);
			numPagina = ultimaPagina;
			break;
		case "AVANZAR":
			botonFirst.setEnabled(true);
			botonBack.setEnabled(true);
			if (((primerRegistroMostrado+camposPorPagina)+camposPorPagina) >= (arrayModelo.size())) {
				//LIMITE FINAL
				primerRegistroMostrado = primerRegistroMostrado + camposPorPagina;
				botonNext.setEnabled(false);
				botonLast.setEnabled(false);
			}
			else {
				//AVANZA
				primerRegistroMostrado = primerRegistroMostrado + camposPorPagina;
			}
			numPagina++;
			break;
		case "RETROCEDER":
			botonNext.setEnabled(true);
			botonLast.setEnabled(true);
			if (((primerRegistroMostrado-camposPorPagina)-camposPorPagina) < 0) {
				//LIMITE PRINCIPIO
				primerRegistroMostrado = 0;
				botonFirst.setEnabled(false);
				botonBack.setEnabled(false);
			} else {
				//RETROCEDE
				primerRegistroMostrado = primerRegistroMostrado - camposPorPagina;
			}
			numPagina--;
			break;
		}
		//refrescar
		labelPagina.setText(numPagina+"");
		crearTabla(modelo);
	}
	
	public void crearTabla(String modelo) {		
		switch (modelo) {
		case "USUARIO":
			if (camposPorPagina > listaUsers.size()) {
				camposPorPagina = listaUsers.size();
			}		
			String titulosUsers[] = { "Email", "Nombre", "Apellidos", "Teléfono", "Contraseña"};
			String informacionUsers[][] = new String[camposPorPagina][titulosUsers.length];
			try {
				for (int x = 0; x < informacionUsers.length; x++) {
					informacionUsers[x][0] = listaUsers.get(x+primerRegistroMostrado).getEmail() + "";
					informacionUsers[x][1] = listaUsers.get(x+primerRegistroMostrado).getNombre() + "";
					informacionUsers[x][2] = listaUsers.get(x+primerRegistroMostrado).getApellidos() + "";
					informacionUsers[x][3] = listaUsers.get(x+primerRegistroMostrado).getTelefono() + "";
					informacionUsers[x][4] = listaUsers.get(x+primerRegistroMostrado).getPassword() + "";
				}
			} catch (IndexOutOfBoundsException e) {}		
			
			tablaUsuarios = new JTable(informacionUsers, titulosUsers);
			estilizarTabla(tablaUsuarios);
			ventanaPrincipal.getScrollPane().setViewportView(new JScrollPane(tablaUsuarios));
			tablaUsuarios.getModel().addTableModelListener(new TableModelListener(){
			    @Override
			    public void tableChanged(TableModelEvent tableModelEvent) {
			        if(tablaUsuarios.isEditing()) {
			        	boolean error = false;
			        	String mensajeError = "";
			        	Usuario usuarioModificado = new Usuario();
			        	String email = listaUsers.get(tablaUsuarios.getSelectedRow()).getEmail();
			        	String modificado = (String) tablaUsuarios.getValueAt(tablaUsuarios.getSelectedRow(),tablaUsuarios.getSelectedColumn());;
			        	//CHECK COLUMNA
			        	if (titulosUsers[tablaUsuarios.getSelectedColumn()].equals("Email")) {
			        		try {
			        			usuarioModificado.setEmail(modificado);
							} catch (Exception e) {
								error = true;
								mensajeError = e.getMessage();
							}			        		
			        	}
			        	else if (titulosUsers[tablaUsuarios.getSelectedColumn()].equals("Contraseña")){
			        		try {
			        			usuarioModificado.setPassword(modificado);
							} catch (Exception e) {
								error = true;
								mensajeError = e.getMessage();
							}	
			        	}
			        	else if (titulosUsers[tablaUsuarios.getSelectedColumn()].equals("Nombre")){
			        		try {
			        			usuarioModificado.setNombre(modificado);
							} catch (Exception e) {
								error = true;
								mensajeError = e.getMessage();
							}	
			        	}
			        	else if (titulosUsers[tablaUsuarios.getSelectedColumn()].equals("Apellidos")){
			        		try {
			        			usuarioModificado.setApellidos(modificado);
							} catch (Exception e) {
								error = true;
								mensajeError = e.getMessage();
							}	
			        	}
			        	else if (titulosUsers[tablaUsuarios.getSelectedColumn()].equals("Teléfono")){
			        		try {
			        			usuarioModificado.setTelefono(modificado);
							} catch (Exception e) {
								error = true;
								mensajeError = e.getMessage();
							}	
			        	}
			        	else {
			        		error = true;
			        	}
			        	//UPDATE
			        	if (error) {
			        		JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), mensajeError,
			    					"Error al editar usuario", JOptionPane.WARNING_MESSAGE);
			        	}
			        	else {
			        		int input = JOptionPane.showConfirmDialog(ventanaPrincipal.getFrame(), 
			                        "¿Deseas editar el registro de "+email+"?", "Editar usuario", JOptionPane.YES_NO_CANCEL_OPTION);
			        		if (input == 0) {
			        			try {
									usuarioModificado.update(email);
									JOptionPane.showMessageDialog(ventanaPrincipal.getFrame(), "Usuario "+email+" editado");
								} catch (Exception e) {
									System.err.println(e.getMessage());
								}
			        		}
			        	}
						resetearPaginas();
			        }      
			    }
			});
			break;
			
		case "RESERVA":
			if (camposPorPagina > listaReservas.size()) {
				camposPorPagina = listaReservas.size();
			}		
			String titulosReserva[] = { "Fecha entrada", "Fecha salida", "Numero adultos", "Numero niños"};
			String informacionReserva[][] = new String[camposPorPagina][titulosReserva.length];
			
			try {
				for (int x = 0; x < informacionReserva.length; x++) {
					informacionReserva[x][0] = listaReservas.get(x+primerRegistroMostrado).getFecha_entrada() + "";
					informacionReserva[x][1] = listaReservas.get(x+primerRegistroMostrado).getFecha_salida() + "";
					informacionReserva[x][2] = listaReservas.get(x+primerRegistroMostrado).getNumero_adultos() + "";
					informacionReserva[x][3] = listaReservas.get(x+primerRegistroMostrado).getNumero_ninyos() + "";
				}
			} catch (IndexOutOfBoundsException e) {}
			
			tablaReservas = new JTable(informacionReserva, titulosReserva);
			estilizarTabla(tablaReservas);
			ventanaPrincipal.getScrollPaneReservas().setViewportView(new JScrollPane(tablaReservas));
			
			break;			
		default:
			break;
		}
	}
	
	private void estilizarTabla(JTable table) {
		table.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 14));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.getTableHeader().setReorderingAllowed(false);
		if (!editando) table.setEnabled(false);
		
	}
	
	private String fechaActual() {
		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());
		String sinMilisegundos = timestamp.toString().split("\\.")[0];
		return sinMilisegundos;
	}
	
}
