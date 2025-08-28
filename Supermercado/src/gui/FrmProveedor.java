package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import entidad.Distrito;
import entidad.Proveedor;
import model.DistritoModel;
import model.ProveedorModel;
import util.Constantes;
import util.FechaUtil;
import util.Mensaje;
import util.TablaUtil;

public class FrmProveedor extends JInternalFrame implements ActionListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblRuc;
	private JLabel lblRazonSocial;
	private JTextField txtRuc;
	private JTextField txtRazonSocial;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnRegistrar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnSalir;
	private JLabel lblCategoras;
	private DefaultTableModel modeloTabla;
	private ProveedorModel modelo;
	private String idSeleccionado;
	private JLabel lblDeRegistros;
	private JLabel lblNumeroRegistros;
	private JPanel panel_2;
	private JLabel lblCdigo;
	private JTextField txtIdProveedor;
	private JLabel lblDireccion;
	private JTextField txtDireccion;
	private JLabel lblDistrito;
	private JComboBox<String> cboDistrito;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JLabel lblFecha;
	private JDateChooser jdFechaRegistro;
	private JLabel lblRepresentante;
	private JTextField txtNombreRepresentante;
	private JButton btnLimpiar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmProveedor frame = new FrmProveedor();
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
	public FrmProveedor() {
		setFrameIcon(new ImageIcon(FrmProveedor.class.getResource("/iconos/proveedor.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle(".:: Proveedores ::.");
		setBounds(100, 100, 1027, 618);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		this.panel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.setBorder(new TitledBorder(null, "Ingreso de Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 42, 791, 190);
		contentPane.add(panel);
		panel.setLayout(null);

		lblRuc = new JLabel("* RUC:");
		this.lblRuc.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblRuc.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRuc.setBounds(275, 28, 49, 14);
		panel.add(lblRuc);

		lblRazonSocial = new JLabel("* Raz\u00F3n Social:");
		this.lblRazonSocial.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblRazonSocial.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRazonSocial.setBounds(27, 56, 96, 14);
		panel.add(lblRazonSocial);

		txtRuc = new JTextField();
		this.txtRuc.addKeyListener(this);
		txtRuc.setBounds(342, 25, 140, 20);
		panel.add(txtRuc);
		txtRuc.setColumns(10);

		txtRazonSocial = new JTextField();
		this.txtRazonSocial.addKeyListener(this);
		txtRazonSocial.setBounds(133, 53, 351, 20);
		panel.add(txtRazonSocial);
		txtRazonSocial.setColumns(10);

		this.lblCdigo = new JLabel("C\u00F3digo:");
		this.lblCdigo.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblCdigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblCdigo.setBounds(27, 28, 96, 14);
		this.panel.add(this.lblCdigo);

		this.txtIdProveedor = new JTextField();
		this.txtIdProveedor.setEnabled(false);
		this.txtIdProveedor.setBounds(134, 25, 126, 20);
		this.panel.add(this.txtIdProveedor);
		this.txtIdProveedor.setColumns(10);

		panel_1 = new JPanel();
		this.panel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de Proveedores",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 243, 877, 321);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 27, 857, 256);
		panel_1.add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "RUC", "Razón Social", "Distrito",
				"Dirección", "Email", "Teléfono", "Registro", "Representante" }));
		this.formatearTabla();
		scrollPane.setViewportView(table);

		this.lblDeRegistros = new JLabel("# de registros:");
		this.lblDeRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDeRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDeRegistros.setBounds(550, 294, 121, 16);
		this.panel_1.add(this.lblDeRegistros);

		this.lblNumeroRegistros = new JLabel("");
		this.lblNumeroRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblNumeroRegistros.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblNumeroRegistros.setBounds(686, 295, 95, 14);
		this.panel_1.add(this.lblNumeroRegistros);

		lblCategoras = new JLabel("PROVEEDOR");
		this.lblCategoras.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoras.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategoras.setBounds(10, 11, 903, 20);
		contentPane.add(lblCategoras);

		this.panel_2 = new JPanel();
		this.panel_2
				.setBorder(new TitledBorder(null, "Mantenimiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel_2.setBounds(899, 42, 102, 459);
		this.contentPane.add(this.panel_2);
		this.panel_2.setLayout(null);

		btnRegistrar = new JButton("");
		this.btnRegistrar.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.btnRegistrar.setToolTipText("Registrar");
		this.btnRegistrar.setIcon(new ImageIcon(FrmProveedor.class.getResource("/iconos/btnRegistrar.png")));
		this.btnRegistrar.setBounds(10, 23, 82, 82);
		this.panel_2.add(this.btnRegistrar);

		btnEliminar = new JButton("");
		this.btnEliminar.setToolTipText("Eliminar");
		this.btnEliminar.setIcon(new ImageIcon(FrmProveedor.class.getResource("/iconos/btnEliminar2.png")));
		this.btnEliminar.setBounds(10, 108, 82, 82);
		this.panel_2.add(this.btnEliminar);
		btnEliminar.addActionListener(this);
		this.btnEliminar.setEnabled(false);

		btnActualizar = new JButton("");
		this.btnActualizar.setToolTipText("Actualizar");
		this.btnActualizar.setIcon(new ImageIcon(FrmProveedor.class.getResource("/iconos/btnActualizar.png")));
		this.btnActualizar.setBounds(10, 194, 82, 82);
		this.panel_2.add(this.btnActualizar);
		btnActualizar.addActionListener(this);
		this.btnActualizar.setEnabled(false);

		btnSalir = new JButton("");
		this.btnSalir.setToolTipText("Salir");
		this.btnSalir.setIcon(new ImageIcon(FrmProveedor.class.getResource("/iconos/salir.png")));
		this.btnSalir.setBounds(10, 367, 82, 82);
		this.panel_2.add(this.btnSalir);

		this.btnLimpiar = new JButton("");
		this.btnLimpiar.addActionListener(this);
		this.btnLimpiar.setIcon(new ImageIcon(FrmProveedor.class.getResource("/iconos/limpiar.png")));
		this.btnLimpiar.setToolTipText("Limpiar");
		this.btnLimpiar.setBounds(10, 281, 82, 82);
		this.panel_2.add(this.btnLimpiar);
		btnSalir.addActionListener(this);
		btnRegistrar.addActionListener(this);

		this.lblDireccion = new JLabel("* Direcci\u00F3n:");
		this.lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDireccion.setBounds(27, 86, 96, 14);
		this.panel.add(this.lblDireccion);

		this.txtDireccion = new JTextField();
		this.txtDireccion.addKeyListener(this);
		this.txtDireccion.setBounds(133, 84, 351, 20);
		this.panel.add(this.txtDireccion);
		this.txtDireccion.setColumns(10);

		this.lblDistrito = new JLabel("* Distrito");
		this.lblDistrito.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDistrito.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDistrito.setBounds(493, 120, 87, 14);
		this.panel.add(this.lblDistrito);

		this.cboDistrito = new JComboBox<String>();
		this.cboDistrito.setBounds(591, 117, 164, 20);
		this.panel.add(this.cboDistrito);

		this.lblEmail = new JLabel("Email:");
		this.lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblEmail.setBounds(27, 120, 94, 14);
		this.panel.add(this.lblEmail);

		this.txtEmail = new JTextField();
		this.txtEmail.addKeyListener(this);
		this.txtEmail.setBounds(132, 117, 273, 20);
		this.panel.add(this.txtEmail);
		this.txtEmail.setColumns(10);

		this.lblTelefono = new JLabel("Tel\u00E9fono:");
		this.lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblTelefono.setBounds(493, 152, 88, 14);
		this.panel.add(this.lblTelefono);

		this.txtTelefono = new JTextField();
		this.txtTelefono.addKeyListener(this);
		this.txtTelefono.setBounds(591, 149, 164, 20);
		this.panel.add(this.txtTelefono);
		this.txtTelefono.setColumns(10);

		this.lblFecha = new JLabel("* Fecha:");
		this.lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblFecha.setBounds(494, 86, 87, 14);
		this.panel.add(this.lblFecha);

		this.jdFechaRegistro = new JDateChooser();
		this.jdFechaRegistro.setBounds(591, 80, 164, 20);
		this.panel.add(this.jdFechaRegistro);

		this.lblRepresentante = new JLabel("Representante:");
		this.lblRepresentante.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblRepresentante.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblRepresentante.setBounds(27, 152, 100, 14);
		this.panel.add(this.lblRepresentante);

		this.txtNombreRepresentante = new JTextField();
		this.txtNombreRepresentante.addKeyListener(this);
		this.txtNombreRepresentante.setBounds(133, 149, 349, 20);
		this.panel.add(this.txtNombreRepresentante);
		this.txtNombreRepresentante.setColumns(10);

		this.idSeleccionado = "";

		// Se obtiene el modelo de Tabla
		this.modeloTabla = (DefaultTableModel) this.table.getModel();

		// Se crea el modelo de Proveedor
		this.modelo = new ProveedorModel();

		this.txtIdProveedor.setText(modelo.generarCodigoProveedor());
		
		// Se lista los proveedores al cargar el formulario
		this.listarProveedors(modelo.listarProveedor());
		
		this.llenarDistritos();
		this.limpiar();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnLimpiar) {
			actionPerformedBtnLimpiar(e);
		}
		if (e.getSource() == btnSalir) {
			actionPerformedBtnSalir(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(e);
		}
	}

	public void llenarDistritos() {
		DistritoModel modeloDistrito = new DistritoModel();
		List<Distrito> distritos = modeloDistrito.listarDistrito();
		for (Distrito distrito : distritos) {
			this.cboDistrito.addItem(distrito.getNombre());
		}
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

	public void listarProveedors(List<Proveedor> proveedores) {
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

	public void limpiar() {
		this.txtRuc.setText("");
		this.txtRazonSocial.setText("");
		this.txtDireccion.setText("");
		this.jdFechaRegistro.setDate(FechaUtil.today());
		this.txtEmail.setText("");
		if (this.cboDistrito.getModel().getSize() > 0) {
			this.cboDistrito.setSelectedIndex(0);
		}
		this.txtNombreRepresentante.setText("");
		this.txtTelefono.setText("");
		this.txtIdProveedor.setText(modelo.generarCodigoProveedor());
		this.txtRuc.requestFocus();
		
		this.btnRegistrar.setEnabled(true);
		this.btnEliminar.setEnabled(false);
		this.btnActualizar.setEnabled(false);
	}

	public boolean verificarCombosDinamicos() {
		boolean termina = false;

		if (this.cboDistrito.getModel().getSize() == 0) {
			Mensaje.mensajeError(this, "No se han registrado Distritos en la base de datos");
			termina = true;
		}

		return termina;
	}

	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		if (this.verificarCombosDinamicos()) {
			return;
		}

		// Entrada de datos
		String idProveedor = this.txtIdProveedor.getText().trim();
		String ruc = this.txtRuc.getText().trim();
		String razonSocial = this.txtRazonSocial.getText().trim();
		String direccion = this.txtDireccion.getText().trim();
		String email = this.txtEmail.getText().trim();
		String telefono = this.txtTelefono.getText().trim();
		String fechaRegistro = FechaUtil.dateToString(this.jdFechaRegistro.getDate());
		String nombreRepresentante = this.txtNombreRepresentante.getText().trim();
		String nombreDistrito = this.cboDistrito.getSelectedItem().toString();

		// Validación
		if (!(ruc.length() > 0 && ruc.length() <= 11)) {
			Mensaje.mensajeError(this, "El RUC debe tener entre 1 a 11 caracteres");
			this.txtRuc.requestFocus();
			return;

		} else {
			if (!ruc.matches("\\d*")) {
				Mensaje.mensajeError(this, "Error en el formato de RUC. Debe ingresar solo números");
				this.txtRuc.requestFocus();
				return;
			}
		}

		if (!(razonSocial.length() > 0 && razonSocial.length() <= 100)) {
			Mensaje.mensajeError(this, "La razón social debe tener entre 1 a 100 caracteres");
			this.txtRazonSocial.requestFocus();
			return;
		}

		if (!(direccion.length() > 0 && direccion.length() <= 100)) {
			Mensaje.mensajeError(this, "La dirección debe tener entre 1 a 100 caracteres");
			this.txtDireccion.requestFocus();
			return;
		}

		// Solo se valida el email si se ha ingresado
		if (email.length() > 0) {
			if (email.length() > 45) {
				Mensaje.mensajeError(this, "El email debe tener entre 0 a 45 caracteres");
				this.txtEmail.requestFocus();
				return;
			}

			if (!email
					.matches("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
				Mensaje.mensajeError(this, "Error en el formato del email");
				this.txtEmail.requestFocus();
				return;
			}
		}

		if (!(telefono.length() >= 0 && telefono.length() <= 12)) {
			Mensaje.mensajeError(this, "El teléfono debe tener entre 0 a 12 caracteres");
			this.txtTelefono.requestFocus();
			return;
		} else {
			if (!telefono.matches("\\d*")) {
				Mensaje.mensajeError(this, "Error en el formato de teléfono. Debe ingresar solo números");
				this.txtTelefono.requestFocus();
				return;
			}
		}

		if (fechaRegistro == null) {
			Mensaje.mensajeError(this, "Debe introducir una fecha de registro");
			this.jdFechaRegistro.setDate(FechaUtil.today());
			this.jdFechaRegistro.requestFocusInWindow();
			return;
		}

		if (!(nombreRepresentante.length() >= 0 && nombreRepresentante.length() <= 100)) {
			Mensaje.mensajeError(this, "El nombre del representante debe tener entre 0 a 100 caracteres");
			this.txtNombreRepresentante.requestFocus();
			return;
		}

		// Se instancia el objeto
		Proveedor proveedor = new Proveedor();
		proveedor.setIdProveedor(idProveedor);
		proveedor.setRuc(ruc);
		proveedor.setRazonSocial(razonSocial);
		proveedor.setDireccion(direccion);
		proveedor.setEmail(email);
		proveedor.setTelefono(telefono);
		proveedor.setFechaRegistro(fechaRegistro);
		proveedor.setNombreRepresentante(nombreRepresentante);

		Distrito distrito = (new DistritoModel()).getDistritoXNombre(nombreDistrito);
		proveedor.setDistrito(distrito);

		// Se envía al modelo
		int salida = modelo.insertarProveedor(proveedor);

		if (salida > 0) {
			this.listarProveedors(modelo.listarProveedor());
			Mensaje.mensajeInformacion(this, Constantes.MSG_SUCCESSFULLY_REGISTRATION);
		} else {
			Mensaje.mensajeError(this, Constantes.MSG_FAILED_REGISTRATION);
		}
		this.limpiar();
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		if (this.idSeleccionado.length() >= 0) {
			int respuesta = Mensaje.mensajeConfirmacion(this,
					"¿Seguro que desea eliminar el proveedor con ID: " + idSeleccionado + "?");

			if (respuesta == JOptionPane.YES_OPTION) {

				int salida = modelo.eliminarProveedor(idSeleccionado);
				this.listarProveedors(modelo.listarProveedor());

				if (salida > 0) {
					Mensaje.mensajeInformacion(this, Constantes.MSG_SUCCESSFULLY_REMOVED);
				} else {
					Mensaje.mensajeError(this, Constantes.MSG_FAILED_REMOVED);
				}
				this.limpiar();
				this.idSeleccionado = "";
			}

		} else {
			Mensaje.mensajeAdvertencia(this, Constantes.MSG_SELECT_ROW_TO_DELETE);
		}
	}

	protected void actionPerformedBtnActualizar(ActionEvent e) {
		if (this.idSeleccionado.length() >= 0) {
			int respuesta = Mensaje.mensajeConfirmacion(this,
					"¿Seguro que desea actualizar el proveedor con ID: " + idSeleccionado + "?");

			if (respuesta == JOptionPane.YES_OPTION) {
				String ruc = this.txtRuc.getText().trim();
				String razonSocial = this.txtRazonSocial.getText().trim();
				String direccion = this.txtDireccion.getText().trim();
				String email = this.txtEmail.getText().trim();
				String telefono = this.txtTelefono.getText().trim();
				String fechaRegistro = FechaUtil.dateToString(this.jdFechaRegistro.getDate());
				String nombreRepresentante = this.txtNombreRepresentante.getText().trim();
				String nombreDistrito = this.cboDistrito.getSelectedItem().toString();

				// Validación
				if (!(ruc.length() > 0 && ruc.length() <= 11)) {
					Mensaje.mensajeError(this, "El RUC debe tener entre 1 a 11 caracteres");
					this.txtRuc.requestFocus();
					return;

				} else {
					if (!ruc.matches("\\d*")) {
						Mensaje.mensajeError(this, "Error en el formato de RUC. Debe ingresar solo números");
						this.txtRuc.requestFocus();
						return;
					}
				}

				if (!(razonSocial.length() > 0 && razonSocial.length() <= 100)) {
					Mensaje.mensajeError(this, "La razón social debe tener entre 1 a 100 caracteres");
					this.txtRazonSocial.requestFocus();
					return;
				}

				if (!(direccion.length() > 0 && direccion.length() <= 100)) {
					Mensaje.mensajeError(this, "La dirección debe tener entre 1 a 100 caracteres");
					this.txtDireccion.requestFocus();
					return;
				}

				// Solo se valida el email si se ha ingresado
				if (email.length() > 0) {
					if (email.length() > 45) {
						Mensaje.mensajeError(this, "El email debe tener entre 0 a 45 caracteres");
						this.txtEmail.requestFocus();
						return;
					}

					if (!email.matches(
							"^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
						Mensaje.mensajeError(this, "Error en el formato del email");
						this.txtEmail.requestFocus();
						return;
					}
				}

				if (!(telefono.length() >= 0 && telefono.length() <= 12)) {
					Mensaje.mensajeError(this, "El teléfono debe tener entre 0 a 12 caracteres");
					this.txtTelefono.requestFocus();
					return;
				} else {
					if (!telefono.matches("\\d*")) {
						Mensaje.mensajeError(this, "Error en el formato de teléfono. Debe ingresar solo números");
						this.txtTelefono.requestFocus();
						return;
					}
				}

				if (fechaRegistro == null) {
					Mensaje.mensajeError(this, "Debe introducir una fecha de registro");
					this.jdFechaRegistro.setDate(FechaUtil.today());
					this.jdFechaRegistro.requestFocusInWindow();
					return;
				}

				if (!(nombreRepresentante.length() >= 0 && nombreRepresentante.length() <= 100)) {
					Mensaje.mensajeError(this, "El nombre del representante debe tener entre 0 a 100 caracteres");
					this.txtNombreRepresentante.requestFocus();
					return;
				}

				Proveedor proveedor = new Proveedor();
				proveedor.setIdProveedor(idSeleccionado);
				proveedor.setRuc(ruc);
				proveedor.setRazonSocial(razonSocial);
				proveedor.setDireccion(direccion);
				proveedor.setEmail(email);
				proveedor.setTelefono(telefono);
				proveedor.setFechaRegistro(fechaRegistro);
				proveedor.setNombreRepresentante(nombreRepresentante);

				Distrito distrito = (new DistritoModel()).getDistritoXNombre(nombreDistrito);
				proveedor.setDistrito(distrito);

				int salida = modelo.actualizarProveedor(proveedor);
				this.listarProveedors(modelo.listarProveedor());

				if (salida > 0) {
					Mensaje.mensajeInformacion(this, Constantes.MSG_SUCCESSFULLY_UPDATED);
				} else {
					Mensaje.mensajeError(this, Constantes.MSG_FAILED_UPDATED);
				}

				this.limpiar();
				this.idSeleccionado = "";
			}

		} else {
			Mensaje.mensajeAdvertencia(this, Constantes.MSG_SELECT_ROW_TO_UPDATE);
		}
	}

	protected void actionPerformedBtnSalir(ActionEvent e) {
		this.dispose();
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			mouseClickedTable(e);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	protected void mouseClickedTable(MouseEvent e) {
		// Se obtiene la fila seleccionada
		int filaSeleccionada = this.table.getSelectedRow();
		System.out.println("Fila seleccionada -> " + filaSeleccionada);

		// Se obtiene el id de la fila seleccionada
		this.idSeleccionado = this.modeloTabla.getValueAt(filaSeleccionada, 0).toString();
		System.out.println("El ID seleccionado es: " + this.idSeleccionado);

		String RUC = this.modeloTabla.getValueAt(filaSeleccionada, 1).toString();
		String razonSocial = this.modeloTabla.getValueAt(filaSeleccionada, 2).toString();
		String distrito = this.modeloTabla.getValueAt(filaSeleccionada, 3).toString();
		String direccion = this.modeloTabla.getValueAt(filaSeleccionada, 4).toString();
		String email = this.modeloTabla.getValueAt(filaSeleccionada, 5).toString();
		String telefono = this.modeloTabla.getValueAt(filaSeleccionada, 6).toString();
		String fechaRegistro = this.modeloTabla.getValueAt(filaSeleccionada, 7).toString();
		String nombreRepresentante = this.modeloTabla.getValueAt(filaSeleccionada, 8).toString();

		this.txtIdProveedor.setText(this.idSeleccionado);
		this.txtRuc.setText(RUC);
		this.txtRazonSocial.setText(razonSocial);
		this.txtDireccion.setText(direccion);
		this.jdFechaRegistro.setDate(FechaUtil.stringToDate(fechaRegistro));
		this.txtEmail.setText(email);
		this.cboDistrito.setSelectedItem(distrito.toString());
		this.txtNombreRepresentante.setText(nombreRepresentante);
		this.txtTelefono.setText(telefono);

		this.btnRegistrar.setEnabled(false);
		this.btnEliminar.setEnabled(true);
		this.btnActualizar.setEnabled(true);
	}

	protected void actionPerformedBtnLimpiar(ActionEvent e) {
		this.limpiar();
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if (e.getSource() == this.txtNombreRepresentante) {
			keyReleasedTxtNombreRepresentante(e);
		}
		if (e.getSource() == this.txtTelefono) {
			keyReleasedTxtTelefono(e);
		}
		if (e.getSource() == this.txtEmail) {
			keyReleasedTxtEmail(e);
		}
		if (e.getSource() == this.txtDireccion) {
			keyReleasedTxtDireccion(e);
		}
		if (e.getSource() == this.txtRazonSocial) {
			keyReleasedTxtRazonSocial(e);
		}
		if (e.getSource() == this.txtRuc) {
			keyReleasedTxtRuc(e);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	// Valida un máximo de 11 caracteres
	protected void keyReleasedTxtRuc(KeyEvent e) {
		int maxCaracteres = 11;
		if (this.txtRuc.getText().length() > maxCaracteres) {
			this.txtRuc.setText(this.txtRuc.getText().substring(0, maxCaracteres));
		}
	}

	// Valida un máximo de 100 caracteres
	protected void keyReleasedTxtRazonSocial(KeyEvent e) {
		int maxCaracteres = 100;
		if (this.txtRazonSocial.getText().length() > maxCaracteres) {
			this.txtRazonSocial.setText(this.txtRazonSocial.getText().substring(0, maxCaracteres));
		}
	}

	// Valida un máximo de 100 caracteres
	protected void keyReleasedTxtDireccion(KeyEvent e) {
		int maxCaracteres = 100;
		if (this.txtDireccion.getText().length() > maxCaracteres) {
			this.txtDireccion.setText(this.txtDireccion.getText().substring(0, maxCaracteres));
		}
	}

	// Valida un máximo de 45 caracteres
	protected void keyReleasedTxtEmail(KeyEvent e) {
		int maxCaracteres = 45;
		if (this.txtEmail.getText().length() > maxCaracteres) {
			this.txtEmail.setText(this.txtEmail.getText().substring(0, maxCaracteres));
		}
	}

	// Valida un máximo de 12 caracteres
	protected void keyReleasedTxtTelefono(KeyEvent e) {
		int maxCaracteres = 12;
		if (this.txtTelefono.getText().length() > maxCaracteres) {
			this.txtTelefono.setText(this.txtTelefono.getText().substring(0, maxCaracteres));
		}
	}

	// Valida un máximo de 100 caracteres
	protected void keyReleasedTxtNombreRepresentante(KeyEvent e) {
		int maxCaracteres = 100;
		if (this.txtNombreRepresentante.getText().length() > maxCaracteres) {
			this.txtNombreRepresentante.setText(this.txtNombreRepresentante.getText().substring(0, maxCaracteres));
		}
	}
}
