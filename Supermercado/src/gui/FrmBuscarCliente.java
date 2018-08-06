package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import entidad.Cliente;
import model.ClienteModel;
import util.FechaUtil;
import util.Mensaje;
import util.TablaUtil;
import javax.swing.ImageIcon;

public class FrmBuscarCliente extends JInternalFrame implements ActionListener, KeyListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblCliente;
	private JTextField txtProducto;
	private JPanel panel;
	private JRadioButton rbtCodigo;
	private JRadioButton rbtApellidos;
	private JRadioButton rbtDireccion;
	private JRadioButton rbtDni;
	private JRadioButton rbtNombres;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private DefaultTableModel modeloTabla;
	private ClienteModel modeloCliente;
	private JLabel lblNumeroRegistros;
	private JLabel lblNroDeRegistros;
	private int filaSeleccionada;
	private JRadioButton rbtDistrito;
	private JRadioButton rbtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmBuscarCliente frame = new FrmBuscarCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmBuscarCliente() {
		setIconifiable(true);
		setClosable(true);
		setTitle(".:: Buscar Cliente ::.");
		setBounds(100, 100, 862, 552);
		getContentPane().setLayout(null);

		this.lblCliente = new JLabel("Cliente");
		this.lblCliente.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblCliente.setBounds(10, 27, 71, 14);
		getContentPane().add(this.lblCliente);

		this.txtProducto = new JTextField();
		this.txtProducto.addKeyListener(this);
		this.txtProducto.setBounds(80, 24, 126, 20);
		getContentPane().add(this.txtProducto);
		this.txtProducto.setColumns(10);

		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(null, "Buscar por", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel.setBounds(216, 11, 620, 42);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);

		this.rbtCodigo = new JRadioButton("C\u00F3digo");
		this.rbtCodigo.setBounds(29, 12, 66, 23);
		this.panel.add(this.rbtCodigo);

		this.rbtApellidos = new JRadioButton("Apellidos");
		this.rbtApellidos.setBounds(266, 12, 76, 23);
		this.panel.add(this.rbtApellidos);

		this.rbtDireccion = new JRadioButton("Direcci\u00F3n");
		this.rbtDireccion.setBounds(357, 12, 76, 23);
		this.panel.add(this.rbtDireccion);

		this.rbtDni = new JRadioButton("DNI");
		this.rbtDni.setSelected(true);
		this.rbtDni.setBounds(107, 12, 51, 23);
		this.panel.add(this.rbtDni);

		this.rbtNombres = new JRadioButton("Nombres");
		this.rbtNombres.setBounds(173, 12, 76, 23);
		this.panel.add(this.rbtNombres);

		this.rbtDistrito = new JRadioButton("Distrito");
		this.rbtDistrito.setBounds(446, 12, 66, 23);
		this.panel.add(this.rbtDistrito);

		this.rbtEmail = new JRadioButton("Email");
		this.rbtEmail.setBounds(525, 12, 51, 23);
		this.panel.add(this.rbtEmail);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(this.rbtCodigo);
		bg.add(this.rbtDni);
		bg.add(this.rbtNombres);
		bg.add(this.rbtApellidos);
		bg.add(this.rbtDireccion);
		bg.add(this.rbtDistrito);
		bg.add(this.rbtEmail);
		

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(10, 56, 826, 403);
		getContentPane().add(this.scrollPane);

		this.table = new JTable();
		this.table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "DNI", "Nombres", "Apellidos",
				"Fecha Nac", "Dirección", "Distrito", "Teléfono", "Email", "Sexo" }));
		this.formatearTabla();
		this.table.setFillsViewportHeight(true);
		this.scrollPane.setViewportView(this.table);

		this.btnAceptar = new JButton("ACEPTAR");
		this.btnAceptar.setIcon(new ImageIcon(FrmBuscarCliente.class.getResource("/iconos/btnACeptar.png")));
		this.btnAceptar.addActionListener(this);
		this.btnAceptar.setBounds(10, 470, 136, 41);
		getContentPane().add(this.btnAceptar);

		this.btnCancelar = new JButton("CANCELAR");
		this.btnCancelar.setIcon(new ImageIcon(FrmBuscarCliente.class.getResource("/iconos/btnSalir.png")));
		this.btnCancelar.addActionListener(this);
		this.btnCancelar.setBounds(150, 470, 133, 41);
		getContentPane().add(this.btnCancelar);

		this.lblNumeroRegistros = new JLabel("");
		this.lblNumeroRegistros.setBounds(764, 470, 72, 14);
		getContentPane().add(this.lblNumeroRegistros);

		this.lblNroDeRegistros = new JLabel("Nro de registros:");
		this.lblNroDeRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblNroDeRegistros.setBounds(637, 470, 117, 14);
		getContentPane().add(this.lblNroDeRegistros);

		// Se obtiene el modelo de Tabla
		this.modeloTabla = (DefaultTableModel) this.table.getModel();

		// Se crea el modelo
		this.modeloCliente = new ClienteModel();

		// Se lista los clientes al cargar el formulario
		this.listarClientes(this.modeloCliente.listarCliente());

		this.filaSeleccionada = -1;
	}

	public void listarClientes(List<Cliente> clientes) {
		// Se limpia la tabla
		this.modeloTabla.setRowCount(0);

		// Se añade a la tabla los clientes fila a fila
		for (Cliente cliente : clientes) {
			Object[] fila = { cliente.getIdCliente(), cliente.getDni(), cliente.getNombres(), cliente.getApellidos(),
					FechaUtil.formatoDeFechaClasico(cliente.getFechaNacimiento()), cliente.getDireccion(),
					cliente.getDistrito().getNombre(), cliente.getTelefono(), cliente.getEmail(),
					cliente.getSexo().getDescripcion() };

			this.modeloTabla.addRow(fila);
		}

		this.lblNumeroRegistros.setText(String.valueOf(clientes.size()));
	}

	public void formatearTabla() {
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Se establece un ancho a las columnas
		TableColumnModel modeloColumna = this.table.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(75); // ID
		modeloColumna.getColumn(1).setPreferredWidth(75); // DNI
		modeloColumna.getColumn(2).setPreferredWidth(135); // Nombres
		modeloColumna.getColumn(3).setPreferredWidth(135); // Apellidos
		modeloColumna.getColumn(4).setPreferredWidth(85); // Fecha de nacimiento
		modeloColumna.getColumn(5).setPreferredWidth(170); // Dirección
		modeloColumna.getColumn(6).setPreferredWidth(170); // Distrito
		modeloColumna.getColumn(7).setPreferredWidth(70); // Teléfono
		modeloColumna.getColumn(8).setPreferredWidth(140); // Email
		modeloColumna.getColumn(9).setPreferredWidth(100); // Sexo

		// Se centran algunas columnas
		for (int i = 0; i < modeloColumna.getColumnCount(); ++i) {
			TablaUtil.centrarCelda(this.table, i);
		}
	}

	public void seleccionarCliente() {
		if (this.filaSeleccionada == -1) {
			Mensaje.mensajeError(this, "No ha seleccionado ninguna fila");

		} else {
			// Se obtiene el código y nombre completo del cliente seleccionado
			String codigoCliente = this.table.getValueAt(this.filaSeleccionada, 0).toString();
			String nombresCliente = this.table.getValueAt(this.filaSeleccionada, 2).toString();
			String apellidosCliente = this.table.getValueAt(this.filaSeleccionada, 3).toString();
			String direccionCliente = this.table.getValueAt(this.filaSeleccionada, 5).toString();
			String distritoCliente = this.table.getValueAt(this.filaSeleccionada, 6).toString();
			String dniCliente = this.table.getValueAt(this.filaSeleccionada, 1).toString();

			// Se envía el código y nombre completo del cliente elegido
			FrmVenta.txtCodigoCliente.setText(codigoCliente);
			FrmVenta.txtCliente.setText(String.format("%s %s", nombresCliente, apellidosCliente));
			FrmVenta.txtDireccionCliente.setText(direccionCliente);
			FrmVenta.txtDistrito.setText(distritoCliente);
			FrmVenta.txtDni.setText(dniCliente);

			this.dispose();
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnCancelar) {
			actionPerformedBtnCancelar(e);
		}
		if (e.getSource() == this.btnAceptar) {
			actionPerformedBtnAceptar(e);
		}
	}

	protected void actionPerformedBtnAceptar(ActionEvent e) {
		this.seleccionarCliente();
	}

	protected void actionPerformedBtnCancelar(ActionEvent e) {
		this.dispose();
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if (e.getSource() == this.txtProducto) {
			keyReleasedTxtProducto(e);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	protected void keyReleasedTxtProducto(KeyEvent e) {
		this.filaSeleccionada = -1;
		String filtro = this.txtProducto.getText().trim();

		if (this.rbtCodigo.isSelected()) {
			this.listarClientes(this.modeloCliente.listarClientexCodigo(filtro));

		} else if (this.rbtDni.isSelected()) {
			this.listarClientes(this.modeloCliente.listarClientexDni(filtro));

		} else if (this.rbtNombres.isSelected()) {
			this.listarClientes(this.modeloCliente.listarClientexNombres(filtro));

		} else if (this.rbtApellidos.isSelected()) {
			this.listarClientes(this.modeloCliente.listarClientexApellidos(filtro));

		} else if (this.rbtDireccion.isSelected()) {
			this.listarClientes(this.modeloCliente.listarClientexDireccion(filtro));
			
		} else if (this.rbtDistrito.isSelected()) {
			this.listarClientes(this.modeloCliente.listarClientexNombreDistrito(filtro));
			
		} else if (this.rbtEmail.isSelected()) {
			this.listarClientes(this.modeloCliente.listarClientexEmail(filtro));
			
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this.table) {
			mouseClickedTable(e);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		if (e.getSource() == this.table) {
			mousePressedTable(e);
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	protected void mouseClickedTable(MouseEvent e) {
		// Se obtiene la fila seleccionada
		this.filaSeleccionada = this.table.getSelectedRow();
	}

	protected void mousePressedTable(MouseEvent e) {
		// Al hacer doble clic
		if (e.getClickCount() == 2) {

			// Se obtiene la fila seleccionada
			this.filaSeleccionada = this.table.getSelectedRow();

			this.seleccionarCliente();
		}
	}

}
