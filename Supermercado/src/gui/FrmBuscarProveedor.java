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

import entidad.Proveedor;
import model.ProveedorModel;
import util.FechaUtil;
import util.Mensaje;
import util.TablaUtil;
import javax.swing.ImageIcon;

public class FrmBuscarProveedor extends JInternalFrame implements ActionListener, KeyListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblProveedor;
	private JTextField txtProveedor;
	private JPanel panel;
	private JRadioButton rbtCodigo;
	private JRadioButton rbtRepresentante;
	private JRadioButton rbtDireccion;
	private JRadioButton rbtRuc;
	private JRadioButton rbtRazonSocial;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private DefaultTableModel modeloTabla;
	private ProveedorModel modeloProveedor;
	private JLabel lblNumeroRegistros;
	private JLabel lblNroDeRegistros;
	private int filaSeleccionada;
	private JRadioButton rbtDistrito;
	private JRadioButton rbtEmail;
	private JRadioButton rbtTelefono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmBuscarProveedor frame = new FrmBuscarProveedor();
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
	public FrmBuscarProveedor() {
		setIconifiable(true);
		setClosable(true);
		setTitle(".:: Buscar Proveedor ::.");
		setBounds(100, 100, 862, 553);
		getContentPane().setLayout(null);

		this.lblProveedor = new JLabel("Proveedor");
		this.lblProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblProveedor.setBounds(10, 27, 71, 14);
		getContentPane().add(this.lblProveedor);

		this.txtProveedor = new JTextField();
		this.txtProveedor.addKeyListener(this);
		this.txtProveedor.setBounds(80, 24, 126, 20);
		getContentPane().add(this.txtProveedor);
		this.txtProveedor.setColumns(10);

		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(null, "Buscar por", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel.setBounds(216, 11, 620, 42);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);

		this.rbtCodigo = new JRadioButton("C\u00F3digo");
		this.rbtCodigo.setBounds(29, 12, 66, 23);
		this.panel.add(this.rbtCodigo);

		this.rbtRepresentante = new JRadioButton("Representante");
		this.rbtRepresentante.setBounds(514, 12, 100, 23);
		this.panel.add(this.rbtRepresentante);

		this.rbtDireccion = new JRadioButton("Direcci\u00F3n");
		this.rbtDireccion.setBounds(312, 12, 76, 23);
		this.panel.add(this.rbtDireccion);

		this.rbtRuc = new JRadioButton("RUC");
		this.rbtRuc.setSelected(true);
		this.rbtRuc.setBounds(97, 12, 51, 23);
		this.panel.add(this.rbtRuc);

		this.rbtRazonSocial = new JRadioButton("Raz\u00F3n Social");
		this.rbtRazonSocial.setBounds(150, 12, 93, 23);
		this.panel.add(this.rbtRazonSocial);

		this.rbtDistrito = new JRadioButton("Distrito");
		this.rbtDistrito.setBounds(244, 12, 66, 23);
		this.panel.add(this.rbtDistrito);

		this.rbtEmail = new JRadioButton("Email");
		this.rbtEmail.setBounds(390, 12, 52, 23);
		this.panel.add(this.rbtEmail);
		
		this.rbtTelefono = new JRadioButton("Tel\u00E9fono");
		this.rbtTelefono.setBounds(444, 12, 68, 23);
		this.panel.add(this.rbtTelefono);

		ButtonGroup bg = new ButtonGroup();
		bg.add(this.rbtCodigo);
		bg.add(this.rbtRuc);
		bg.add(this.rbtRazonSocial);
		bg.add(this.rbtDistrito);
		bg.add(this.rbtDireccion);
		bg.add(this.rbtEmail);
		bg.add(this.rbtTelefono);
		bg.add(this.rbtRepresentante);
		
		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(10, 56, 826, 403);
		getContentPane().add(this.scrollPane);

		this.table = new JTable();
		this.table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "RUC", "Razón Social", "Distrito",
				"Dirección", "Email", "Teléfono", "Fecha Registro", "Representante" }));
		
		this.formatearTabla();
		this.table.setFillsViewportHeight(true);
		this.scrollPane.setViewportView(this.table);

		this.btnAceptar = new JButton("ACEPTAR");
		this.btnAceptar.setIcon(new ImageIcon(FrmBuscarProveedor.class.getResource("/iconos/btnACeptar.png")));
		this.btnAceptar.addActionListener(this);
		this.btnAceptar.setBounds(10, 470, 126, 42);
		getContentPane().add(this.btnAceptar);

		this.btnCancelar = new JButton("CANCELAR");
		this.btnCancelar.setIcon(new ImageIcon(FrmBuscarProveedor.class.getResource("/iconos/btnSalir.png")));
		this.btnCancelar.addActionListener(this);
		this.btnCancelar.setBounds(138, 470, 126, 42);
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
		this.modeloProveedor = new ProveedorModel();

		// Se lista los proveedores al cargar el formulario
		this.listarProveedores(this.modeloProveedor.listarProveedor());

		this.filaSeleccionada = -1;
	}

	public void listarProveedores(List<Proveedor> proveedores) {
		// Se limpia la tabla
		this.modeloTabla.setRowCount(0);

		// Se añade a la tabla los proveedores fila a fila
		for (Proveedor proveedor : proveedores) {
			Object[] fila = { proveedor.getIdProveedor(), proveedor.getRuc(), proveedor.getRazonSocial(),
					proveedor.getDistrito().getNombre(), proveedor.getDireccion(), proveedor.getEmail(),
					proveedor.getTelefono(), FechaUtil.formatoDeFechaClasico(proveedor.getFechaRegistro()),
					proveedor.getNombreRepresentante() };

			this.modeloTabla.addRow(fila);
		}

		this.lblNumeroRegistros.setText(String.valueOf(proveedores.size()));
	}

	public void formatearTabla() {
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Se establece un ancho a las columnas
		TableColumnModel modeloColumna = this.table.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(80); // ID
		modeloColumna.getColumn(1).setPreferredWidth(90); // Ruc
		modeloColumna.getColumn(2).setPreferredWidth(150); // Razón social
		modeloColumna.getColumn(3).setPreferredWidth(100); // Distrito
		modeloColumna.getColumn(4).setPreferredWidth(180); // Dirección
		modeloColumna.getColumn(5).setPreferredWidth(140); // Email
		modeloColumna.getColumn(6).setPreferredWidth(70); // Teléfono
		modeloColumna.getColumn(7).setPreferredWidth(90); // Fecha de Registro
		modeloColumna.getColumn(8).setPreferredWidth(150); // Representante

		// Se centran algunas columnas
		for (int i = 0; i < modeloColumna.getColumnCount(); ++i) {
			TablaUtil.centrarCelda(this.table, i);
		}
	}

	public void seleccionarProveedor() {
		if (this.filaSeleccionada == -1) {
			Mensaje.mensajeError(this, "No ha seleccionado ninguna fila");

		} else {
			// Se obtiene el código y nombre completo del proveedor seleccionado
			String codigoProveedor = this.table.getValueAt(this.filaSeleccionada, 0).toString();
			String razonSocialProveedor = this.table.getValueAt(this.filaSeleccionada, 2).toString();
			String direccionProveedor = this.table.getValueAt(this.filaSeleccionada, 4).toString();
			String distritoProveedor = this.table.getValueAt(this.filaSeleccionada, 3).toString();
			String rucProveedor = this.table.getValueAt(this.filaSeleccionada, 1).toString();

			// Se envía el código y nombre completo del proveedor elegido
			FrmCompra.txtCodigoProveedor.setText(codigoProveedor);
			FrmCompra.txtRazonSocial.setText(razonSocialProveedor);
			FrmCompra.txtDireccionProveedor.setText(direccionProveedor);
			FrmCompra.txtDistrito.setText(distritoProveedor);
			FrmCompra.txtRuc.setText(rucProveedor);

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
		this.seleccionarProveedor();
	}

	protected void actionPerformedBtnCancelar(ActionEvent e) {
		this.dispose();
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if (e.getSource() == this.txtProveedor) {
			keyReleasedTxtProducto(e);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	protected void keyReleasedTxtProducto(KeyEvent e) {
		this.filaSeleccionada = -1;
		String filtro = this.txtProveedor.getText().trim();

		if (this.rbtCodigo.isSelected()) {
			this.listarProveedores(this.modeloProveedor.listarProveedorxCodigo(filtro));

		} else if (this.rbtRuc.isSelected()) {
			this.listarProveedores(this.modeloProveedor.listarProveedorxRuc(filtro));

		} else if (this.rbtRazonSocial.isSelected()) {
			this.listarProveedores(this.modeloProveedor.listarProveedorxRazonSocial(filtro));

		} else if (this.rbtDistrito.isSelected()) {
			this.listarProveedores(this.modeloProveedor.listarProveedorxNombreDistrito(filtro));

		} else if (this.rbtDireccion.isSelected()) {
			this.listarProveedores(this.modeloProveedor.listarProveedorxDireccion(filtro));

		} else if (this.rbtEmail.isSelected()) {
			this.listarProveedores(this.modeloProveedor.listarProveedorxEmail(filtro));

		} else if (this.rbtTelefono.isSelected()) {
			this.listarProveedores(this.modeloProveedor.listarProveedorxTelefono(filtro));

		} else if (this.rbtRepresentante.isSelected()) {
			this.listarProveedores(this.modeloProveedor.listarProveedorxRepresentante(filtro));
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

			this.seleccionarProveedor();
		}
	}
}
