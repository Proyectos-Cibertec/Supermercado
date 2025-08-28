package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import entidad.Cargo;
import entidad.Distrito;
import entidad.Empleado;
import entidad.Sexo;
import model.CargoModel;
import model.DistritoModel;
import model.EmpleadoModel;
import model.SexoModel;
import util.Constantes;
import util.FechaUtil;
import util.Foto;
import util.Mensaje;
import util.TablaUtil;

public class FrmEmpleado extends JInternalFrame implements ActionListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelIngreso;
	private JLabel lblDni;
	private JLabel lblNombres;
	private JTextField txtDni;
	private JTextField txtNombres;
	private JPanel panelListado;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnRegistrar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnSalir;
	private JLabel lblEmpleados;
	private DefaultTableModel modeloTabla;
	private EmpleadoModel modelo;
	private String idSeleccionado;
	private JLabel lblDeRegistros;
	private JLabel lblNumeroRegistros;
	private JPanel panelBotones;
	private JLabel lblCodigo;
	private JTextField txtIdEmpleado;
	private JLabel lblDireccion;
	private JTextField txtDireccion;
	private JLabel lblDistrito;
	private JComboBox<String> cboDistrito;
	private JLabel lblEmail;
	private JTextField txtEmail;
	private JLabel lblTelefono;
	private JTextField txtTelefono;
	private JLabel lblFechaNacimiento;
	private JDateChooser jdFechaNacimiento;
	private JButton btnLimpiar;
	private JLabel lblApellidos;
	private JTextField txtApellidos;
	private JComboBox<String> cboSexo;
	private JLabel lblSexo;
	private JPanel panelFoto;
	private JLabel lblCargo;
	private JComboBox<String> cboCargo;
	private JLabel lblFechaContratacion;
	private JDateChooser jdFechaContratacion;
	private JPanel panel;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblPassword;
	private JPasswordField txtPassword;
	private JLabel lblFoto;
	private File ficheroSeleccionado; // Foto
	private JButton btnSubir;
	private JButton btnCancelar;

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
					FrmEmpleado frame = new FrmEmpleado();
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
	public FrmEmpleado() {
		setFrameIcon(new ImageIcon(FrmEmpleado.class.getResource("/iconos/empleados.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle(".:: Empleados ::.");
		setBounds(100, 100, 1063, 651);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelIngreso = new JPanel();
		this.panelIngreso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelIngreso.setBorder(
				new TitledBorder(null, "Ingreso de Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelIngreso.setBounds(10, 42, 673, 208);
		contentPane.add(panelIngreso);
		panelIngreso.setLayout(null);

		lblDni = new JLabel("* DNI:");
		this.lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDni.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDni.setBounds(248, 27, 49, 14);
		panelIngreso.add(lblDni);

		lblNombres = new JLabel("* Nombres:");
		this.lblNombres.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblNombres.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombres.setBounds(10, 55, 86, 14);
		panelIngreso.add(lblNombres);

		txtDni = new JTextField();
		this.txtDni.addKeyListener(this);
		txtDni.setBounds(315, 24, 140, 20);
		panelIngreso.add(txtDni);
		txtDni.setColumns(10);

		txtNombres = new JTextField();
		this.txtNombres.addKeyListener(this);
		txtNombres.setBounds(106, 52, 351, 20);
		panelIngreso.add(txtNombres);
		txtNombres.setColumns(10);

		this.lblCodigo = new JLabel("C\u00F3digo:");
		this.lblCodigo.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblCodigo.setBounds(10, 27, 86, 14);
		this.panelIngreso.add(this.lblCodigo);

		this.txtIdEmpleado = new JTextField();
		this.txtIdEmpleado.setEnabled(false);
		this.txtIdEmpleado.setBounds(107, 24, 126, 20);
		this.panelIngreso.add(this.txtIdEmpleado);
		this.txtIdEmpleado.setColumns(10);

		panelListado = new JPanel();
		this.panelListado.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelListado.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de Empleados",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelListado.setBounds(10, 330, 916, 283);
		contentPane.add(panelListado);
		panelListado.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 27, 896, 212);
		panelListado.add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "DNI", "Nombres", "Apellidos", "Fecha Nac", "Direcci�n", "Distrito", "Email",
						"Tel�fono", "Fecha Contrato", "Sexo", "Usuario", "Password", "Cargo" }));
		this.formatearTabla();
		scrollPane.setViewportView(table);

		this.lblDeRegistros = new JLabel("# de registros:");
		this.lblDeRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDeRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDeRegistros.setBounds(675, 250, 121, 16);
		this.panelListado.add(this.lblDeRegistros);

		this.lblNumeroRegistros = new JLabel("");
		this.lblNumeroRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblNumeroRegistros.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblNumeroRegistros.setBounds(811, 251, 95, 14);
		this.panelListado.add(this.lblNumeroRegistros);

		lblEmpleados = new JLabel("EMPLEADOS");
		this.lblEmpleados.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpleados.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmpleados.setBounds(10, 7, 903, 20);
		contentPane.add(lblEmpleados);

		this.panelBotones = new JPanel();
		this.panelBotones
				.setBorder(new TitledBorder(null, "Mantenimiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelBotones.setBounds(936, 42, 102, 459);
		this.contentPane.add(this.panelBotones);
		this.panelBotones.setLayout(null);

		btnRegistrar = new JButton("");
		this.btnRegistrar.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.btnRegistrar.setToolTipText("Registrar");
		this.btnRegistrar.setIcon(new ImageIcon(FrmEmpleado.class.getResource("/iconos/btnRegistrar.png")));
		this.btnRegistrar.setBounds(10, 23, 82, 82);
		this.panelBotones.add(this.btnRegistrar);

		btnEliminar = new JButton("");
		this.btnEliminar.setToolTipText("Eliminar");
		this.btnEliminar.setIcon(new ImageIcon(FrmEmpleado.class.getResource("/iconos/btnEliminar2.png")));
		this.btnEliminar.setBounds(10, 108, 82, 82);
		this.panelBotones.add(this.btnEliminar);
		btnEliminar.addActionListener(this);
		this.btnEliminar.setEnabled(false);

		btnActualizar = new JButton("");
		this.btnActualizar.setToolTipText("Actualizar");
		this.btnActualizar.setIcon(new ImageIcon(FrmEmpleado.class.getResource("/iconos/btnActualizar.png")));
		this.btnActualizar.setBounds(10, 194, 82, 82);
		this.panelBotones.add(this.btnActualizar);
		btnActualizar.addActionListener(this);
		this.btnActualizar.setEnabled(false);

		btnSalir = new JButton("");
		this.btnSalir.setToolTipText("Salir");
		this.btnSalir.setIcon(new ImageIcon(FrmEmpleado.class.getResource("/iconos/salir.png")));
		this.btnSalir.setBounds(10, 367, 82, 82);
		this.panelBotones.add(this.btnSalir);

		this.btnLimpiar = new JButton("");
		this.btnLimpiar.addActionListener(this);
		this.btnLimpiar.setIcon(new ImageIcon(FrmEmpleado.class.getResource("/iconos/limpiar.png")));
		this.btnLimpiar.setToolTipText("Limpiar");
		this.btnLimpiar.setBounds(10, 281, 82, 82);
		this.panelBotones.add(this.btnLimpiar);
		btnSalir.addActionListener(this);
		btnRegistrar.addActionListener(this);

		this.lblDireccion = new JLabel("* Direcci\u00F3n:");
		this.lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDireccion.setBounds(10, 111, 86, 14);
		this.panelIngreso.add(this.lblDireccion);

		this.txtDireccion = new JTextField();
		this.txtDireccion.addKeyListener(this);
		this.txtDireccion.setBounds(106, 109, 351, 20);
		this.panelIngreso.add(this.txtDireccion);
		this.txtDireccion.setColumns(10);

		this.lblDistrito = new JLabel("* Distrito:");
		this.lblDistrito.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDistrito.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDistrito.setBounds(10, 144, 86, 14);
		this.panelIngreso.add(this.lblDistrito);

		this.cboDistrito = new JComboBox<String>();
		this.cboDistrito.setBounds(107, 141, 194, 20);
		this.panelIngreso.add(this.cboDistrito);

		this.lblEmail = new JLabel("Email:");
		this.lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblEmail.setBounds(282, 178, 94, 14);
		this.panelIngreso.add(this.lblEmail);

		this.txtEmail = new JTextField();
		this.txtEmail.addKeyListener(this);
		this.txtEmail.setBounds(387, 175, 273, 20);
		this.panelIngreso.add(this.txtEmail);
		this.txtEmail.setColumns(10);

		this.lblTelefono = new JLabel("Tel\u00E9fono:");
		this.lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblTelefono.setBounds(10, 178, 88, 14);
		this.panelIngreso.add(this.lblTelefono);

		this.txtTelefono = new JTextField();
		this.txtTelefono.addKeyListener(this);
		this.txtTelefono.setBounds(108, 175, 194, 20);
		this.panelIngreso.add(this.txtTelefono);
		this.txtTelefono.setColumns(10);

		this.lblFechaNacimiento = new JLabel("* Fecha de Nacimiento:");
		this.lblFechaNacimiento.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblFechaNacimiento.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblFechaNacimiento.setBounds(489, 24, 166, 14);
		this.panelIngreso.add(this.lblFechaNacimiento);

		this.jdFechaNacimiento = new JDateChooser();
		this.jdFechaNacimiento.setBounds(490, 41, 164, 20);
		this.panelIngreso.add(this.jdFechaNacimiento);

		this.lblApellidos = new JLabel("* Apellidos:");
		this.lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblApellidos.setBounds(10, 84, 86, 14);
		this.panelIngreso.add(this.lblApellidos);

		this.txtApellidos = new JTextField();
		this.txtApellidos.addKeyListener(this);
		this.txtApellidos.setText("");
		this.txtApellidos.setColumns(10);
		this.txtApellidos.setBounds(106, 81, 351, 20);
		this.panelIngreso.add(this.txtApellidos);

		this.cboSexo = new JComboBox<String>();
		this.cboSexo.setBounds(464, 141, 193, 20);
		this.panelIngreso.add(this.cboSexo);

		this.lblSexo = new JLabel("* Sexo:");
		this.lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblSexo.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblSexo.setBounds(366, 144, 87, 14);
		this.panelIngreso.add(this.lblSexo);

		this.panelFoto = new JPanel();
		this.panelFoto.setBorder(new TitledBorder(null, "Foto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelFoto.setBounds(693, 42, 233, 281);
		this.contentPane.add(this.panelFoto);
		this.panelFoto.setLayout(null);

		this.lblFoto = new JLabel("");
		this.lblFoto.addMouseListener(this);
		this.lblFoto.setIcon(new ImageIcon(FrmEmpleado.class.getResource("/iconos/fotoPersonaGenerica.png")));
		this.lblFoto.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.lblFoto.setBounds(16, 22, 200, 200);
		this.panelFoto.add(this.lblFoto);

		this.btnSubir = new JButton("");
		this.btnSubir.addActionListener(this);
		this.btnSubir.setIcon(new ImageIcon(FrmEmpleado.class.getResource("/iconos/btnSubirFoto.png")));
		this.btnSubir.setToolTipText("Seleccionar foto");
		this.btnSubir.setBounds(16, 231, 101, 33);
		this.panelFoto.add(this.btnSubir);

		this.btnCancelar = new JButton("");
		this.btnCancelar.addActionListener(this);
		this.btnCancelar.setIcon(new ImageIcon(FrmEmpleado.class.getResource("/iconos/btnCancelarFoto.png")));
		this.btnCancelar.setToolTipText("Cancelar foto");
		this.btnCancelar.setBounds(121, 231, 95, 33);
		this.panelFoto.add(this.btnCancelar);

		this.lblFechaContratacion = new JLabel("* Fecha de Contrataci\u00F3n:");
		this.lblFechaContratacion.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblFechaContratacion.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblFechaContratacion.setBounds(491, 83, 166, 14);
		this.panelIngreso.add(this.lblFechaContratacion);

		this.jdFechaContratacion = new JDateChooser();
		this.jdFechaContratacion.setBounds(492, 100, 164, 20);
		this.panelIngreso.add(this.jdFechaContratacion);

		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(null, "Privilegios", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel.setBounds(10, 251, 673, 79);
		this.contentPane.add(this.panel);
		this.panel.setLayout(null);

		this.lblUsuario = new JLabel("* Usuario:");
		this.lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblUsuario.setBounds(9, 18, 76, 14);
		this.panel.add(this.lblUsuario);

		this.txtUsuario = new JTextField();
		this.txtUsuario.addKeyListener(this);
		this.txtUsuario.setText("");
		this.txtUsuario.setColumns(10);
		this.txtUsuario.setBounds(95, 15, 198, 20);
		this.panel.add(this.txtUsuario);

		this.lblPassword = new JLabel("* Password:");
		this.lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblPassword.setBounds(9, 47, 76, 14);
		this.panel.add(this.lblPassword);

		this.txtPassword = new JPasswordField();
		this.txtPassword.addKeyListener(this);
		this.txtPassword.setBounds(95, 46, 198, 20);
		this.panel.add(this.txtPassword);

		this.lblCargo = new JLabel("* Cargo:");
		this.lblCargo.setBounds(365, 44, 101, 14);
		this.panel.add(this.lblCargo);
		this.lblCargo.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblCargo.setFont(new Font("Tahoma", Font.BOLD, 11));

		this.cboCargo = new JComboBox<String>();
		this.cboCargo.setBounds(476, 41, 173, 20);
		this.panel.add(this.cboCargo);

		this.idSeleccionado = "";

		// Se obtiene el modelo de Tabla
		this.modeloTabla = (DefaultTableModel) this.table.getModel();

		// Se crea el modelo de Empleado
		this.modelo = new EmpleadoModel();

		this.txtIdEmpleado.setText(modelo.generarCodigoEmpleado());

		// Se lista los empleados al cargar el formulario
		this.listarEmpleados(modelo.listarEmpleado());

		this.llenarDistritos();
		this.llenarSexos();
		this.llenarCargos();
		this.limpiar();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnCancelar) {
			actionPerformedBtnCancelar(e);
		}
		if (e.getSource() == this.btnSubir) {
			actionPerformedBtnSubir(e);
		}
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

	public void llenarSexos() {
		SexoModel modeloSexo = new SexoModel();
		List<Sexo> sexos = modeloSexo.listarSexo();
		for (Sexo sexo : sexos) {
			this.cboSexo.addItem(sexo.getDescripcion());
		}
	}

	public void llenarCargos() {
		CargoModel modeloCargo = new CargoModel();
		List<Cargo> cargos = modeloCargo.listarCargo();
		for (Cargo cargo : cargos) {
			this.cboCargo.addItem(cargo.getDescripcion());
		}
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
		modeloColumna.getColumn(5).setPreferredWidth(170); // Direcci�n
		modeloColumna.getColumn(6).setPreferredWidth(170); // Distrito
		modeloColumna.getColumn(7).setPreferredWidth(140); // Email
		modeloColumna.getColumn(8).setPreferredWidth(70); // Tel�fono
		modeloColumna.getColumn(9).setPreferredWidth(85); // Fecha de
															// Contrataci�n
		modeloColumna.getColumn(10).setPreferredWidth(100); // Sexo
		modeloColumna.getColumn(11).setPreferredWidth(75); // Usuario
		modeloColumna.getColumn(12).setPreferredWidth(75); // Password
		modeloColumna.getColumn(13).setPreferredWidth(75); // Cargo

		// Se centran algunas columnas
		for (int i = 0; i < modeloColumna.getColumnCount(); ++i) {
			TablaUtil.centrarCelda(this.table, i);
		}
	}

	public void listarEmpleados(List<Empleado> empleados) {
		// Se limpia la tabla
		this.modeloTabla.setRowCount(0);

		// Se a�ade a la tabla los empleados fila a fila
		for (Empleado empleado : empleados) {
			Object[] fila = { empleado.getIdEmpleado(), empleado.getDni(), empleado.getNombres(),
					empleado.getApellidos(), FechaUtil.formatoDeFechaClasico(empleado.getFechaNacimiento()),
					empleado.getDireccion(), empleado.getDistrito().getNombre(), empleado.getEmail(),
					empleado.getTelefono(), FechaUtil.formatoDeFechaClasico(empleado.getFechaContratacion()),
					empleado.getSexo().getDescripcion(), empleado.getUsuario(), empleado.getPassword(),
					empleado.getCargo().getDescripcion() };

			this.modeloTabla.addRow(fila);
		}

		this.lblNumeroRegistros.setText(String.valueOf(empleados.size()));
	}

	public void limpiar() {
		this.txtDni.setText("");
		this.txtNombres.setText("");
		this.txtApellidos.setText("");
		this.txtDireccion.setText("");
		this.jdFechaNacimiento.setDate(FechaUtil.today());
		this.jdFechaContratacion.setDate(FechaUtil.today());

		if (this.cboDistrito.getModel().getSize() > 0) {
			this.cboDistrito.setSelectedIndex(0);
		}

		if (this.cboSexo.getModel().getSize() > 0) {
			this.cboSexo.setSelectedIndex(0);
		}

		if (this.cboCargo.getModel().getSize() > 0) {
			this.cboCargo.setSelectedIndex(0);
		}

		this.txtTelefono.setText("");
		this.txtEmail.setText("");
		this.txtUsuario.setText("");
		this.txtPassword.setText("");

		this.txtIdEmpleado.setText(modelo.generarCodigoEmpleado());
		this.txtDni.requestFocus();

		this.btnRegistrar.setEnabled(true);
		this.btnEliminar.setEnabled(false);
		this.btnActualizar.setEnabled(false);
	}

	public boolean verificarCombosDinamicos() {
		boolean termina = true;

		if (this.cboDistrito.getModel().getSize() > 0 && this.cboSexo.getModel().getSize() > 0
				&& this.cboCargo.getModel().getSize() > 0) {
			termina = false;
		} else {
			Mensaje.mensajeError(this,
					"Faltan registrar datos en alguna de las siguientes tablas: Distrito|Sexo|Cargo");
		}

		return termina;
	}

	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		if (this.verificarCombosDinamicos()) {
			return;
		}

		// Entrada de datos
		String idEmpleado = this.txtIdEmpleado.getText().trim();
		String dni = this.txtDni.getText().trim();
		String nombres = this.txtNombres.getText().trim();
		String apellidos = this.txtApellidos.getText().trim();
		String fechaNacimiento = FechaUtil.dateToString(this.jdFechaNacimiento.getDate());
		String fechaContratacion = FechaUtil.dateToString(this.jdFechaContratacion.getDate());
		String direccion = this.txtDireccion.getText().trim();
		String telefono = this.txtTelefono.getText().trim();
		String email = this.txtEmail.getText().trim();
		String descripcionSexo = this.cboSexo.getSelectedItem().toString();
		String nombreDistrito = this.cboDistrito.getSelectedItem().toString();
		String usuario = this.txtUsuario.getText().trim();
		String password = new String(this.txtPassword.getPassword());
		String descripcionCargo = this.cboCargo.getSelectedItem().toString();

		// Validaci�n
		if (!(dni.length() > 0 && dni.length() <= 8)) {
			Mensaje.mensajeError(this, "El DNI debe tener entre 1 a 8 caracteres");
			this.txtDni.requestFocus();
			return;

		} else {
			if (!dni.matches("\\d*")) {
				Mensaje.mensajeError(this, "Error en el formato de DNI. Debe ingresar solo n�meros");
				this.txtDni.requestFocus();
				return;
			}
		}

		if (!(nombres.length() > 0 && nombres.length() <= 45)) {
			Mensaje.mensajeError(this, "El nombre debe tener entre 1 a 45 caracteres");
			this.txtNombres.requestFocus();
			return;
		}

		if (!(apellidos.length() > 0 && apellidos.length() <= 45)) {
			Mensaje.mensajeError(this, "El apellido debe tener entre 1 a 45 caracteres");
			this.txtApellidos.requestFocus();
			return;
		}

		if (fechaNacimiento == null) {
			Mensaje.mensajeError(this, "Debe introducir una fecha de nacimiento");
			this.jdFechaNacimiento.setDate(FechaUtil.today());
			this.jdFechaNacimiento.requestFocusInWindow();
			return;
		}

		if (fechaContratacion == null) {
			Mensaje.mensajeError(this, "Debe introducir una fecha de contrataci�n");
			this.jdFechaContratacion.setDate(FechaUtil.today());
			this.jdFechaContratacion.requestFocusInWindow();
			return;
		}

		if (!(direccion.length() > 0 && direccion.length() <= 100)) {
			Mensaje.mensajeError(this, "La direcci�n debe tener entre 1 a 100 caracteres");
			this.txtDireccion.requestFocus();
			return;
		}

		if (!(telefono.length() >= 0 && telefono.length() <= 12)) {
			Mensaje.mensajeError(this, "El tel�fono debe tener entre 0 a 12 caracteres");
			this.txtTelefono.requestFocus();
			return;
		} else {
			if (!telefono.matches("\\d*")) {
				Mensaje.mensajeError(this, "Error en el formato de tel�fono. Debe ingresar solo n�meros");
				this.txtTelefono.requestFocus();
				return;
			}
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

		if (!(usuario.length() > 0 && usuario.length() <= 45)) {
			Mensaje.mensajeError(this, "El Nombre de Usuario debe tener entre 1 a 45 caracteres");
			this.txtUsuario.requestFocus();
			return;
		}

		if (!(password.length() > 0 && password.length() <= 45)) {
			Mensaje.mensajeError(this, "El password debe tener entre 1 a 45 caracteres");
			this.txtPassword.requestFocus();
			return;
		}

		// Se instancia el objeto
		Empleado empleado = new Empleado();
		empleado.setIdEmpleado(idEmpleado);
		empleado.setDni(dni);
		empleado.setNombres(nombres);
		empleado.setApellidos(apellidos);
		empleado.setFechaNacimiento(fechaNacimiento);
		empleado.setFechaContratacion(fechaContratacion);
		empleado.setDireccion(direccion);
		empleado.setTelefono(telefono);
		empleado.setEmail(email);
		empleado.setUsuario(usuario);
		empleado.setPassword(password);

		try {
			FileInputStream fis = new FileInputStream(this.ficheroSeleccionado);
			Foto foto = new Foto();
			foto.setInputStream(fis);
			empleado.setFoto(foto);

		} catch (Exception e2) {
			e2.printStackTrace();
		}

		Sexo sexo = (new SexoModel()).getSexoXDescripcion(descripcionSexo);
		empleado.setSexo(sexo);

		Distrito distrito = (new DistritoModel()).getDistritoXNombre(nombreDistrito);
		empleado.setDistrito(distrito);

		Cargo cargo = (new CargoModel()).getCargoXDescripcion(descripcionCargo);
		empleado.setCargo(cargo);

		// Se env�a al modelo
		int salida = modelo.insertarEmpleado(empleado);

		if (salida > 0) {
			this.listarEmpleados(modelo.listarEmpleado());
			Mensaje.mensajeInformacion(this, Constantes.MSG_SUCCESSFULLY_REGISTRATION);
		} else {
			Mensaje.mensajeError(this, Constantes.MSG_FAILED_REGISTRATION);
		}
		this.limpiar();
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		if (this.idSeleccionado.length() >= 0) {
			int respuesta = Mensaje.mensajeConfirmacion(this,
					"�Seguro que desea eliminar el empleado con ID: " + idSeleccionado + "?");

			if (respuesta == JOptionPane.YES_OPTION) {

				int salida = modelo.eliminarEmpleado(idSeleccionado);
				this.listarEmpleados(modelo.listarEmpleado());

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
					"�Seguro que desea actualizar el empleado con ID: " + idSeleccionado + "?");

			if (respuesta == JOptionPane.YES_OPTION) {
				String idEmpleado = this.txtIdEmpleado.getText().trim();
				String dni = this.txtDni.getText().trim();
				String nombres = this.txtNombres.getText().trim();
				String apellidos = this.txtApellidos.getText().trim();
				String fechaNacimiento = FechaUtil.dateToString(this.jdFechaNacimiento.getDate());
				String fechaContratacion = FechaUtil.dateToString(this.jdFechaContratacion.getDate());
				String direccion = this.txtDireccion.getText().trim();
				String telefono = this.txtTelefono.getText().trim();
				String email = this.txtEmail.getText().trim();
				String descripcionSexo = this.cboSexo.getSelectedItem().toString();
				String nombreDistrito = this.cboDistrito.getSelectedItem().toString();
				String usuario = this.txtUsuario.getText().trim();
				String password = new String(this.txtPassword.getPassword());
				String descripcionCargo = this.cboCargo.getSelectedItem().toString();

				// Validaci�n
				if (!(dni.length() > 0 && dni.length() <= 8)) {
					Mensaje.mensajeError(this, "El DNI debe tener entre 1 a 8 caracteres");
					this.txtDni.requestFocus();
					return;

				} else {
					if (!dni.matches("\\d*")) {
						Mensaje.mensajeError(this, "Error en el formato de DNI. Debe ingresar solo n�meros");
						this.txtDni.requestFocus();
						return;
					}
				}

				if (!(nombres.length() > 0 && nombres.length() <= 45)) {
					Mensaje.mensajeError(this, "El nombre debe tener entre 1 a 45 caracteres");
					this.txtNombres.requestFocus();
					return;
				}

				if (!(apellidos.length() > 0 && apellidos.length() <= 45)) {
					Mensaje.mensajeError(this, "El apellido debe tener entre 1 a 45 caracteres");
					this.txtApellidos.requestFocus();
					return;
				}

				if (fechaNacimiento == null) {
					Mensaje.mensajeError(this, "Debe introducir una fecha de nacimiento");
					this.jdFechaNacimiento.setDate(FechaUtil.today());
					this.jdFechaNacimiento.requestFocusInWindow();
					return;
				}

				if (fechaContratacion == null) {
					Mensaje.mensajeError(this, "Debe introducir una fecha de contrataci�n");
					this.jdFechaContratacion.setDate(FechaUtil.today());
					this.jdFechaContratacion.requestFocusInWindow();
					return;
				}

				if (!(direccion.length() > 0 && direccion.length() <= 100)) {
					Mensaje.mensajeError(this, "La direcci�n debe tener entre 1 a 100 caracteres");
					this.txtDireccion.requestFocus();
					return;
				}

				if (!(telefono.length() >= 0 && telefono.length() <= 12)) {
					Mensaje.mensajeError(this, "El tel�fono debe tener entre 0 a 12 caracteres");
					this.txtTelefono.requestFocus();
					return;
				} else {
					if (!telefono.matches("\\d*")) {
						Mensaje.mensajeError(this, "Error en el formato de tel�fono. Debe ingresar solo n�meros");
						this.txtTelefono.requestFocus();
						return;
					}
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

				if (!(usuario.length() > 0 && usuario.length() <= 45)) {
					Mensaje.mensajeError(this, "El Nombre de Usuario debe tener entre 1 a 45 caracteres");
					this.txtUsuario.requestFocus();
					return;
				}

				if (!(password.length() > 0 && password.length() <= 45)) {
					Mensaje.mensajeError(this, "El password debe tener entre 1 a 45 caracteres");
					this.txtPassword.requestFocus();
					return;
				}

				// Se instancia el objeto
				Empleado empleado = new Empleado();
				empleado.setIdEmpleado(idEmpleado);
				empleado.setDni(dni);
				empleado.setNombres(nombres);
				empleado.setApellidos(apellidos);
				empleado.setFechaNacimiento(fechaNacimiento);
				empleado.setFechaContratacion(fechaContratacion);
				empleado.setDireccion(direccion);
				empleado.setTelefono(telefono);
				empleado.setEmail(email);
				empleado.setUsuario(usuario);
				empleado.setPassword(password);

				if (this.ficheroSeleccionado == null) { // Se mantiene la foto
														// del producto sin
														// cambios
					try {
						Foto foto = this.modelo.getFoto(this.idSeleccionado);
						empleado.setFoto(foto);

					} catch (Exception e2) {
						e2.printStackTrace();
					}

				} else { // Se ha elegido alguna foto nueva para el producto
							// (nueva o gen�rica)
					try {
						FileInputStream fis = new FileInputStream(this.ficheroSeleccionado);
						Foto foto = new Foto();
						foto.setInputStream(fis);
						empleado.setFoto(foto);

					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

				Sexo sexo = (new SexoModel()).getSexoXDescripcion(descripcionSexo);
				empleado.setSexo(sexo);

				Distrito distrito = (new DistritoModel()).getDistritoXNombre(nombreDistrito);
				empleado.setDistrito(distrito);

				Cargo cargo = (new CargoModel()).getCargoXDescripcion(descripcionCargo);
				empleado.setCargo(cargo);

				int salida = modelo.actualizarEmpleado(empleado);
				this.listarEmpleados(modelo.listarEmpleado());

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
		if (e.getSource() == this.lblFoto) {
			mouseClickedLblFoto(e);
		}
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

		String dni = this.modeloTabla.getValueAt(filaSeleccionada, 1).toString();
		String nombres = this.modeloTabla.getValueAt(filaSeleccionada, 2).toString();
		String apellidos = this.modeloTabla.getValueAt(filaSeleccionada, 3).toString();
		String fechaNacimiento = this.modeloTabla.getValueAt(filaSeleccionada, 4).toString();
		String direccion = this.modeloTabla.getValueAt(filaSeleccionada, 5).toString();
		String distrito = this.modeloTabla.getValueAt(filaSeleccionada, 6).toString();
		String email = this.modeloTabla.getValueAt(filaSeleccionada, 7).toString();
		String telefono = this.modeloTabla.getValueAt(filaSeleccionada, 8).toString();
		String fechaContratacion = this.modeloTabla.getValueAt(filaSeleccionada, 9).toString();
		String sexo = this.modeloTabla.getValueAt(filaSeleccionada, 10).toString();
		String usuario = this.modeloTabla.getValueAt(filaSeleccionada, 11).toString();
		String password = this.modeloTabla.getValueAt(filaSeleccionada, 12).toString();
		String cargo = this.modeloTabla.getValueAt(filaSeleccionada, 13).toString();

		this.txtIdEmpleado.setText(this.idSeleccionado);
		this.txtDni.setText(dni);
		this.txtNombres.setText(nombres);
		this.txtApellidos.setText(apellidos);
		this.jdFechaNacimiento.setDate(FechaUtil.stringToDate(fechaNacimiento));
		this.txtDireccion.setText(direccion);
		this.cboDistrito.setSelectedItem(distrito);
		this.txtEmail.setText(email);
		this.txtTelefono.setText(telefono);
		this.jdFechaContratacion.setDate(FechaUtil.stringToDate(fechaContratacion));
		this.cboSexo.setSelectedItem(sexo);
		this.txtUsuario.setText(usuario);
		this.txtPassword.setText(password);
		this.cboCargo.setSelectedItem(cargo);

		// Se muestra la foto a partir del idSeleccionado
		try {
			Foto foto = this.modelo.getFoto(this.idSeleccionado);
			Image imagen = ImageIO.read(foto.getInputStream());
			this.lblFoto.setIcon(new ImageIcon(
					imagen.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT)));
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		this.btnRegistrar.setEnabled(false);
		this.btnEliminar.setEnabled(true);
		this.btnActualizar.setEnabled(true);
		this.ficheroSeleccionado = null;
	}

	protected void actionPerformedBtnLimpiar(ActionEvent e) {
		this.limpiar();
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		if (e.getSource() == this.txtPassword) {
			keyReleasedTxtPassword(e);
		}
		if (e.getSource() == this.txtUsuario) {
			keyReleasedTxtUsuario(e);
		}
		if (e.getSource() == this.txtApellidos) {
			keyReleasedTxtApellidos(e);
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
		if (e.getSource() == this.txtNombres) {
			keyReleasedTxtNombres(e);
		}
		if (e.getSource() == this.txtDni) {
			keyReleasedTxtDni(e);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	// Valida un m�ximo de 8 caracteres
	protected void keyReleasedTxtDni(KeyEvent e) {
		int maxCaracteres = 8;
		if (this.txtDni.getText().length() > maxCaracteres) {
			this.txtDni.setText(this.txtDni.getText().substring(0, maxCaracteres));
		}
	}

	// Valida un m�ximo de 45 caracteres
	protected void keyReleasedTxtNombres(KeyEvent e) {
		int maxCaracteres = 45;
		if (this.txtNombres.getText().length() > maxCaracteres) {
			this.txtNombres.setText(this.txtNombres.getText().substring(0, maxCaracteres));
		}
	}

	// Valida un m�ximo de 45 caracteres
	protected void keyReleasedTxtApellidos(KeyEvent e) {
		int maxCaracteres = 45;
		if (this.txtApellidos.getText().length() > maxCaracteres) {
			this.txtApellidos.setText(this.txtApellidos.getText().substring(0, maxCaracteres));
		}
	}

	// Valida un m�ximo de 100 caracteres
	protected void keyReleasedTxtDireccion(KeyEvent e) {
		int maxCaracteres = 100;
		if (this.txtDireccion.getText().length() > maxCaracteres) {
			this.txtDireccion.setText(this.txtDireccion.getText().substring(0, maxCaracteres));
		}
	}

	// Valida un m�ximo de 12 caracteres
	protected void keyReleasedTxtTelefono(KeyEvent e) {
		int maxCaracteres = 12;
		if (this.txtTelefono.getText().length() > maxCaracteres) {
			this.txtTelefono.setText(this.txtTelefono.getText().substring(0, maxCaracteres));
		}
	}

	// Valida un m�ximo de 45 caracteres
	protected void keyReleasedTxtEmail(KeyEvent e) {
		int maxCaracteres = 45;
		if (this.txtEmail.getText().length() > maxCaracteres) {
			this.txtEmail.setText(this.txtEmail.getText().substring(0, maxCaracteres));
		}
	}

	// Valida un m�ximo de 45 caracteres
	protected void keyReleasedTxtUsuario(KeyEvent e) {
		int maxCaracteres = 45;
		if (this.txtUsuario.getText().length() > maxCaracteres) {
			this.txtUsuario.setText(this.txtUsuario.getText().substring(0, maxCaracteres));
		}
	}

	// Valida un m�ximo de 45 caracteres
	protected void keyReleasedTxtPassword(KeyEvent e) {
		int maxCaracteres = 45;
		if (new String(this.txtPassword.getPassword()).length() > maxCaracteres) {
			this.txtPassword.setText(new String(this.txtPassword.getPassword()).substring(0, maxCaracteres));
		}
	}

	public void seleccionarFoto() {
		// Se crea el objeto JFileChooser
		JFileChooser fileChooser = new JFileChooser();

		// Se establece como directorio por defecto la ubicaci�n del proyecto
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

		// Se establece el tipo de selecci�n de archivo
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Solo
																	// ficheros

		// Se establece el filtro
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.jpg *.png *.gif", "jpg", "png", "gif");
		fileChooser.setFileFilter(filtro);

		// Se abre la ventana y se guarda la opci�n seleccionada por el usuario
		int respuesta = fileChooser.showOpenDialog(this);

		// Si el usuario eligi� el bot�n Aceptar
		if (respuesta == JFileChooser.APPROVE_OPTION) {

			// Se selecciona el fichero
			this.ficheroSeleccionado = fileChooser.getSelectedFile();

			// Se lee el fichero mostrando su contenido en el label
			ImageIcon imageIcon = new ImageIcon(this.ficheroSeleccionado.toString());
			Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(),
					Image.SCALE_DEFAULT));
			this.lblFoto.setIcon(icon);
		}
	}

	public void cancelarFoto() {
		this.ficheroSeleccionado = new File("src/iconos/fotoPersonaGenerica.png");
		this.lblFoto.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/fotoPersonaGenerica.png")));
	}

	protected void actionPerformedBtnSubir(ActionEvent e) {
		this.seleccionarFoto();
	}

	protected void actionPerformedBtnCancelar(ActionEvent e) {
		this.cancelarFoto();
	}

	protected void mouseClickedLblFoto(MouseEvent e) {
		this.seleccionarFoto();
	}
}
