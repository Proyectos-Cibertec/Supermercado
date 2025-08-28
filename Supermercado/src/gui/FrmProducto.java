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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import entidad.Categoria;
import entidad.Producto;
import entidad.Proveedor;
import model.CategoriaModel;
import model.ProductoModel;
import model.ProveedorModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import util.Conexion;
import util.Constantes;
import util.FechaUtil;
import util.Foto;
import util.Mensaje;
import util.TablaUtil;

public class FrmProducto extends JInternalFrame implements ActionListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnRegistrar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnSalir;
	private JLabel lblCategoras;
	private DefaultTableModel modeloTabla;
	private ProductoModel modelo;
	private String idSeleccionado;
	private JLabel lblDeRegistros;
	private JLabel lblNumeroRegistros;
	private JPanel panel_2;
	private JLabel lblCdigo;
	private JTextField txtIdProducto;
	private JLabel lblPrecioVenta;
	private JTextField txtPrecioVenta;
	private JLabel lblStockActual;
	private JComboBox<String> cboCategoria;
	private JLabel lblPrecioCompra;
	private JTextField txtPrecioCompra;
	private JLabel lblCategoria;
	private JLabel lblFecha;
	private JDateChooser jdFechaRegistro;
	private JPanel panelFoto;
	private JLabel lblProveedor;
	private JComboBox<String> cboProveedor;
	private JLabel lblStockMinimo;
	private CategoriaModel modeloCategoria;
	private ProveedorModel modeloProveedor;
	private JSpinner spinnerStockActual;
	private JSpinner spinnerStockMinimo;
	private JButton btnLimpiar;
	private JLabel lblFoto;
	private File ficheroSeleccionado; // Foto
	private JButton btnSubir;
	private JButton btnCancelar;
	private JButton btnReporte;

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
					FrmProducto frame = new FrmProducto();
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
	public FrmProducto() {
		setFrameIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/productoNormal.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle(".:: Productos ::.");
		setBounds(100, 100, 1027, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		this.panel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.setBorder(new TitledBorder(null, "Ingreso de Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 42, 621, 281);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNombre = new JLabel("* Nombre:");
		this.lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(248, 28, 76, 14);
		panel.add(lblNombre);

		lblDescripcion = new JLabel("Descripci\u00F3n:");
		this.lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescripcion.setBounds(13, 56, 85, 14);
		panel.add(lblDescripcion);

		txtNombre = new JTextField();
		this.txtNombre.addKeyListener(this);
		txtNombre.setBounds(334, 25, 264, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtDescripcion = new JTextField();
		this.txtDescripcion.addKeyListener(this);
		txtDescripcion.setBounds(106, 53, 492, 20);
		panel.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		this.lblCdigo = new JLabel("C\u00F3digo:");
		this.lblCdigo.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblCdigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblCdigo.setBounds(13, 28, 85, 14);
		this.panel.add(this.lblCdigo);

		this.txtIdProducto = new JTextField();
		this.txtIdProducto.setEnabled(false);
		this.txtIdProducto.setBounds(107, 25, 126, 20);
		this.panel.add(this.txtIdProducto);
		this.txtIdProducto.setColumns(10);

		panel_1 = new JPanel();
		this.panel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de Productos",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 334, 877, 281);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 857, 218);
		panel_1.add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre", "Categoría", "Proveedor",
				"Precio Venta", "Precio Compra", "Stock", "Mínimo Stock", "Fecha Registro", "Descripción" }));
		this.formatearTabla();
		scrollPane.setViewportView(table);

		this.lblDeRegistros = new JLabel("# de registros:");
		this.lblDeRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDeRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDeRegistros.setBounds(540, 252, 121, 16);
		this.panel_1.add(this.lblDeRegistros);

		this.lblNumeroRegistros = new JLabel("");
		this.lblNumeroRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblNumeroRegistros.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblNumeroRegistros.setBounds(671, 252, 95, 14);
		this.panel_1.add(this.lblNumeroRegistros);

		lblCategoras = new JLabel("PRODUCTOS");
		this.lblCategoras.setHorizontalAlignment(SwingConstants.CENTER);
		lblCategoras.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategoras.setBounds(10, 11, 903, 20);
		contentPane.add(lblCategoras);

		this.panel_2 = new JPanel();
		this.panel_2
				.setBorder(new TitledBorder(null, "Mantenimiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel_2.setBounds(899, 42, 102, 573);
		this.contentPane.add(this.panel_2);
		this.panel_2.setLayout(null);

		btnRegistrar = new JButton("");
		this.btnRegistrar.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.btnRegistrar.setToolTipText("Registrar");
		this.btnRegistrar.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/btnRegistrar.png")));
		this.btnRegistrar.setBounds(10, 23, 82, 82);
		this.panel_2.add(this.btnRegistrar);

		btnEliminar = new JButton("");
		this.btnEliminar.setToolTipText("Eliminar");
		this.btnEliminar.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/btnEliminar2.png")));
		this.btnEliminar.setBounds(10, 108, 82, 82);
		this.panel_2.add(this.btnEliminar);
		btnEliminar.addActionListener(this);
		this.btnEliminar.setEnabled(false);

		btnActualizar = new JButton("");
		this.btnActualizar.setToolTipText("Actualizar");
		this.btnActualizar.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/btnActualizar.png")));
		this.btnActualizar.setBounds(10, 194, 82, 82);
		this.panel_2.add(this.btnActualizar);
		btnActualizar.addActionListener(this);
		this.btnActualizar.setEnabled(false);

		btnSalir = new JButton("");
		this.btnSalir.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/salir.png")));
		this.btnSalir.setToolTipText("Salir");
		this.btnSalir.setBounds(10, 367, 82, 82);
		this.panel_2.add(this.btnSalir);

		this.btnLimpiar = new JButton("");
		this.btnLimpiar.addActionListener(this);
		this.btnLimpiar.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/limpiar.png")));
		this.btnLimpiar.setToolTipText("Limpiar");
		this.btnLimpiar.setBounds(10, 280, 82, 82);
		this.panel_2.add(this.btnLimpiar);
		
		btnReporte = new JButton("");
		btnReporte.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/btnReporte.png")));
		btnReporte.setToolTipText("Reporte");
		btnReporte.addActionListener(this);
		btnReporte.setBounds(10, 454, 82, 82);
		panel_2.add(btnReporte);
		btnSalir.addActionListener(this);
		btnRegistrar.addActionListener(this);

		this.lblPrecioVenta = new JLabel("* Precio venta:");
		this.lblPrecioVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblPrecioVenta.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblPrecioVenta.setBounds(317, 163, 113, 14);
		this.panel.add(this.lblPrecioVenta);

		this.txtPrecioVenta = new JTextField();
		this.txtPrecioVenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.txtPrecioVenta.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtPrecioVenta.setBounds(440, 150, 96, 40);
		this.panel.add(this.txtPrecioVenta);
		this.txtPrecioVenta.setColumns(10);

		this.lblStockActual = new JLabel("* Stock:");
		this.lblStockActual.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblStockActual.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblStockActual.setBounds(58, 219, 113, 14);
		this.panel.add(this.lblStockActual);

		this.cboCategoria = new JComboBox<String>();
		this.cboCategoria.setBounds(106, 84, 192, 20);
		this.panel.add(this.cboCategoria);

		this.lblPrecioCompra = new JLabel("* Precio compra:");
		this.lblPrecioCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblPrecioCompra.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblPrecioCompra.setBounds(58, 163, 113, 14);
		this.panel.add(this.lblPrecioCompra);

		this.txtPrecioCompra = new JTextField();
		this.txtPrecioCompra.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.txtPrecioCompra.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtPrecioCompra.setBounds(181, 150, 96, 40);
		this.panel.add(this.txtPrecioCompra);
		this.txtPrecioCompra.setColumns(10);

		this.lblCategoria = new JLabel("* Categor\u00EDa:");
		this.lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblCategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblCategoria.setBounds(13, 81, 85, 23);
		this.panel.add(this.lblCategoria);

		this.lblFecha = new JLabel("* Fecha:");
		this.lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblFecha.setBounds(337, 87, 87, 14);
		this.panel.add(this.lblFecha);

		this.jdFechaRegistro = new JDateChooser();
		this.jdFechaRegistro.setBounds(434, 84, 164, 20);
		this.panel.add(this.jdFechaRegistro);

		this.lblProveedor = new JLabel("* Proveedor:");
		this.lblProveedor.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblProveedor.setBounds(13, 115, 86, 20);
		this.panel.add(this.lblProveedor);

		this.cboProveedor = new JComboBox<String>();
		this.cboProveedor.setBounds(106, 115, 492, 20);
		this.panel.add(this.cboProveedor);

		this.lblStockMinimo = new JLabel("* Stock m\u00EDnimo:");
		this.lblStockMinimo.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblStockMinimo.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblStockMinimo.setBounds(317, 219, 111, 14);
		this.panel.add(this.lblStockMinimo);

		this.panelFoto = new JPanel();
		this.panelFoto.setBorder(new TitledBorder(null, "Foto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelFoto.setBounds(641, 42, 241, 281);
		this.contentPane.add(this.panelFoto);
		this.panelFoto.setLayout(null);

		this.lblFoto = new JLabel("");
		this.lblFoto.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/fotoProductoGenerica.png")));
		this.lblFoto.addMouseListener(this);
		this.lblFoto.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.lblFoto.setBounds(20, 26, 200, 200);
		this.panelFoto.add(this.lblFoto);
		
		this.btnSubir = new JButton("");
		this.btnSubir.addActionListener(this);
		this.btnSubir.setToolTipText("Seleccionar foto");
		this.btnSubir.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/btnSubirFoto2.png")));
		this.btnSubir.setBounds(20, 235, 101, 33);
		this.panelFoto.add(this.btnSubir);
		
		this.btnCancelar = new JButton("");
		this.btnCancelar.addActionListener(this);
		this.btnCancelar.setToolTipText("Cancelar foto");
		this.btnCancelar.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/btnCancelFoto2.png")));
		this.btnCancelar.setBounds(125, 235, 95, 33);
		this.panelFoto.add(this.btnCancelar);

		this.idSeleccionado = "";

		// Se obtiene el modelo de Tabla
		this.modeloTabla = (DefaultTableModel) this.table.getModel();

		// Se crea el modelo de Producto y los otros modelos
		this.modelo = new ProductoModel();
		modeloCategoria = new CategoriaModel();
		modeloProveedor = new ProveedorModel();

		// Genera el nuevo código del Producto
		this.txtIdProducto.setText(this.modelo.generarCodigoProducto());

		this.spinnerStockActual = new JSpinner();
		this.spinnerStockActual.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		this.spinnerStockActual.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.spinnerStockActual.setBounds(181, 206, 96, 40);
		this.panel.add(this.spinnerStockActual);

		this.spinnerStockMinimo = new JSpinner();
		this.spinnerStockMinimo.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		this.spinnerStockMinimo.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.spinnerStockMinimo.setBounds(440, 206, 96, 40);
		this.panel.add(this.spinnerStockMinimo);

		// Se lista los productos al cargar el formulario
		this.listarProductos(modelo.listarProducto());

		this.llenarCategorias();
		this.llenarProveedores();
		this.limpiar();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnReporte) {
			actionPerformedBtnReporte(e);
		}
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

	public void llenarCategorias() {
		List<Categoria> categorias = modeloCategoria.listarCategoria();
		categorias.forEach(categoria -> {
			this.cboCategoria.addItem(categoria.getNombre());
		});
	}

	public void llenarProveedores() {
		List<Proveedor> proveedores = modeloProveedor.listarProveedor();
		proveedores.forEach(proveedor -> {
			this.cboProveedor.addItem(proveedor.getRazonSocial());
		});
	}

	public void formatearTabla() {
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Se establece un ancho a las columnas
		TableColumnModel modeloColumna = this.table.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(80); // ID
		modeloColumna.getColumn(1).setPreferredWidth(290); // Nombre
		modeloColumna.getColumn(2).setPreferredWidth(120); // Categoría
		modeloColumna.getColumn(3).setPreferredWidth(150); // Proveedor
		modeloColumna.getColumn(4).setPreferredWidth(90); // Precio Venta
		modeloColumna.getColumn(5).setPreferredWidth(100); // Precio Compra
		modeloColumna.getColumn(6).setPreferredWidth(60); // Stock Actual
		modeloColumna.getColumn(7).setPreferredWidth(80); // Stock Mínimo
		modeloColumna.getColumn(8).setPreferredWidth(90); // Fecha Registro
		modeloColumna.getColumn(9).setPreferredWidth(150); // Descripción

		// Se centran algunas columnas
		for (int i = 0; i < modeloColumna.getColumnCount(); ++i) {
			TablaUtil.centrarCelda(this.table, i);
		}
	}

	public void listarProductos(List<Producto> productos) {
		// Se limpia la tabla
		this.modeloTabla.setRowCount(0);

		// Se añade a la tabla los productos fila a fila
		productos.forEach(producto -> {
			Object[] fila = { producto.getIdProducto(), producto.getNombre(), producto.getCategoria().getNombre(),
					producto.getProveedor().getRazonSocial(), producto.getPrecioVenta(), producto.getPrecioCompra(),
					producto.getStockActual(), producto.getStockMinimo(),
					FechaUtil.formatoDeFechaClasico(producto.getFechaRegistro()), producto.getDescripcion() };
			this.modeloTabla.addRow(fila);
		});

		this.lblNumeroRegistros.setText(String.valueOf(productos.size()));
	}

	public void limpiar() {
		this.txtNombre.setText("");
		this.txtDescripcion.setText("");

		if (this.cboCategoria.getModel().getSize() > 0) {
			this.cboCategoria.setSelectedIndex(0);
		}

		if (this.cboProveedor.getModel().getSize() > 0) {
			this.cboProveedor.setSelectedIndex(0);
		}
		
		this.jdFechaRegistro.setDate(FechaUtil.today());
		this.txtPrecioCompra.setText("0.00");
		this.txtPrecioVenta.setText("0.00");
		this.spinnerStockActual.setValue(0);
		// this.txtStockActual.setText("0");
		this.spinnerStockMinimo.setValue(0);
		// this.txtStockMinimo.setText("0");
		this.txtIdProducto.setText(this.modelo.generarCodigoProducto());

		this.btnRegistrar.setEnabled(true);
		this.btnEliminar.setEnabled(false);
		this.btnActualizar.setEnabled(false);
		this.cancelarFoto();
		this.txtNombre.requestFocus();
	}

	public boolean verificarCombosDinamicos() {
		boolean termina = false;

		if (this.cboCategoria.getModel().getSize() == 0 && this.cboProveedor.getModel().getSize() == 0) {
			Mensaje.mensajeError(this, "No se han registrado Categorías ni Proveedores en la base de datos");
			termina = true;

		} else if (this.cboCategoria.getModel().getSize() == 0) {
			Mensaje.mensajeError(this, "No se han registrado Categorías en la base de datos");
			termina = true;

		} else if (this.cboProveedor.getModel().getSize() == 0) {
			Mensaje.mensajeError(this, "No se han registrado Proveedores en la base de datos");
			termina = true;
		}

		return termina;
	}

	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		if (this.verificarCombosDinamicos()) {
			return;
		}

		// Entrada de datos
		String idProducto = this.txtIdProducto.getText().trim();
		String nombre = this.txtNombre.getText().trim();
		String descripcion = this.txtDescripcion.getText().trim();
		String nombreCategoria = this.cboCategoria.getSelectedItem().toString();
		String razonSocialProveedor = this.cboProveedor.getSelectedItem().toString();
		String fechaRegistro = FechaUtil.dateToString(this.jdFechaRegistro.getDate());
		String precioCompra = this.txtPrecioCompra.getText().trim();
		String precioVenta = this.txtPrecioVenta.getText().trim();
		String stockActual = this.spinnerStockActual.getValue().toString().trim();
		// String stockActual = this.txtStockActual.getText().trim();
		String stockMinimo = this.spinnerStockMinimo.getValue().toString().trim();
		// String stockMinimo = this.txtStockMinimo.getText().trim();

		// Validación
		if (!(nombre.length() > 0 && nombre.length() <= 100)) {
			Mensaje.mensajeError(this, "El nombre debe tener entre 1 a 100 caracteres");
			this.txtNombre.requestFocus();
			return;
		}

		if (!(descripcion.length() >= 0 && descripcion.length() <= 200)) {
			Mensaje.mensajeError(this, "La descripción debe tener entre 0 a 200 caracteres");
			this.txtDescripcion.requestFocus();
			return;
		}

		if (fechaRegistro == null) {
			Mensaje.mensajeError(this, "Debe introducir una fecha de registro");
			this.jdFechaRegistro.setDate(FechaUtil.today());
			this.jdFechaRegistro.requestFocusInWindow();
			return;
		}

		if (!precioCompra.matches("\\d+|\\d+.\\d{1,2}")) {
			Mensaje.mensajeError(this, "Error de formato de precio (#.##)");
			this.txtPrecioCompra.requestFocus();
			return;
		}

		if (!precioVenta.matches("\\d+|\\d+.\\d{1,2}")) {
			Mensaje.mensajeError(this, "Error de formato de precio (#.##)");
			this.txtPrecioVenta.requestFocus();
			return;
		}

		if (!stockActual.matches("\\d+")) {
			Mensaje.mensajeError(this, "Error de formato de número");
			// this.txtStockActual.requestFocus();
			this.spinnerStockActual.requestFocus();
			return;
		}

		if (!stockMinimo.matches("\\d+")) {
			Mensaje.mensajeError(this, "Error de formato de número");
			// this.txtStockMinimo.requestFocus();
			this.spinnerStockMinimo.requestFocus();
			return;
		}

		// Se instancia el objeto
		Producto producto = new Producto();
		producto.setIdProducto(idProducto);
		producto.setNombre(nombre);
		producto.setDescripcion(descripcion);
		producto.setPrecioVenta(Double.parseDouble(precioVenta));
		producto.setPrecioCompra(Double.parseDouble(precioCompra));
		producto.setStockActual(Integer.parseInt(stockActual));
		producto.setStockMinimo(Integer.parseInt(stockMinimo));
		producto.setFechaRegistro(fechaRegistro);

		try {
			FileInputStream fis = new FileInputStream(this.ficheroSeleccionado);
			Foto foto = new Foto();
			foto.setInputStream(fis);
			producto.setFoto(foto);
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		Categoria categoria = modeloCategoria.getCategoriaXNombre(nombreCategoria);
		Proveedor proveedor = modeloProveedor.getProveedorXRazonSocial(razonSocialProveedor);
		producto.setCategoria(categoria);
		producto.setProveedor(proveedor);

		// Se envía al modelo
		int salida = modelo.insertarProducto(producto);

		if (salida > 0) {
			this.listarProductos(modelo.listarProducto());
			Mensaje.mensajeInformacion(this, Constantes.MSG_SUCCESSFULLY_REGISTRATION);
		} else {
			Mensaje.mensajeError(this, Constantes.MSG_FAILED_REGISTRATION);
		}
		this.limpiar();
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		if (this.idSeleccionado.length() >= 0) {
			int respuesta = Mensaje.mensajeConfirmacion(this,
					"¿Seguro que desea eliminar el producto con ID: " + idSeleccionado + "?");

			if (respuesta == JOptionPane.YES_OPTION) {

				int salida = modelo.eliminarProducto(idSeleccionado);
				this.listarProductos(modelo.listarProducto());

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
					"¿Seguro que desea actualizar el producto con ID: " + idSeleccionado + "?");

			if (respuesta == JOptionPane.YES_OPTION) {
				String idProducto = this.txtIdProducto.getText().trim();
				String nombre = this.txtNombre.getText().trim();
				String descripcion = this.txtDescripcion.getText().trim();
				String nombreCategoria = this.cboCategoria.getSelectedItem().toString();
				String razonSocialProveedor = this.cboProveedor.getSelectedItem().toString();
				String fechaRegistro = FechaUtil.dateToString(this.jdFechaRegistro.getDate());
				String precioCompra = this.txtPrecioCompra.getText().trim();
				String precioVenta = this.txtPrecioVenta.getText().trim();
				String stockActual = this.spinnerStockActual.getValue().toString().trim();
				// String stockActual = this.txtStockActual.getText().trim();
				String stockMinimo = this.spinnerStockMinimo.getValue().toString().trim();
				// String stockMinimo = this.txtStockMinimo.getText().trim();

				// Validación
				if (!(nombre.length() > 0 && nombre.length() <= 100)) {
					Mensaje.mensajeError(this, "El nombre debe tener entre 1 a 100 caracteres");
					this.txtNombre.requestFocus();
					return;
				}

				if (!(descripcion.length() >= 0 && descripcion.length() <= 200)) {
					Mensaje.mensajeError(this, "La descripción debe tener entre 0 a 200 caracteres");
					this.txtDescripcion.requestFocus();
					return;
				}

				if (fechaRegistro == null) {
					Mensaje.mensajeError(this, "Debe introducir una fecha de registro");
					this.jdFechaRegistro.setDate(FechaUtil.today());
					this.jdFechaRegistro.requestFocusInWindow();
					return;
				}

				if (!precioCompra.matches("\\d+|\\d+.\\d{1,2}")) {
					Mensaje.mensajeError(this, "Error de formato de precio (#.##)");
					this.txtPrecioCompra.requestFocus();
					return;
				}

				if (!precioVenta.matches("\\d+|\\d+.\\d{1,2}")) {
					Mensaje.mensajeError(this, "Error de formato de precio (#.##)");
					this.txtPrecioVenta.requestFocus();
					return;
				}

				if (!stockActual.matches("\\d+")) {
					Mensaje.mensajeError(this, "Error de formato de número");
					// this.txtStockActual.requestFocus();
					this.spinnerStockActual.requestFocus();
					return;
				}

				if (!stockMinimo.matches("\\d+")) {
					Mensaje.mensajeError(this, "Error de formato de número");
					// this.txtStockMinimo.requestFocus();
					this.spinnerStockMinimo.requestFocus();
					return;
				}

				// Se instancia el objeto
				Producto producto = new Producto();
				producto.setIdProducto(idProducto);
				producto.setNombre(nombre);
				producto.setDescripcion(descripcion);
				producto.setPrecioVenta(Double.parseDouble(precioVenta));
				producto.setPrecioCompra(Double.parseDouble(precioCompra));
				producto.setStockActual(Integer.parseInt(stockActual));
				producto.setStockMinimo(Integer.parseInt(stockMinimo));
				producto.setFechaRegistro(fechaRegistro);

				if (this.ficheroSeleccionado == null) { // Se mantiene la foto del producto sin cambios
					try {
						Foto foto = this.modelo.getFoto(this.idSeleccionado);
						producto.setFoto(foto);
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
				} else { // Se ha elegido alguna foto nueva para el producto (nueva o genérica)
					try {
						FileInputStream fis = new FileInputStream(this.ficheroSeleccionado);
						Foto foto = new Foto();
						foto.setInputStream(fis);
						producto.setFoto(foto);
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}

				Categoria categoria = modeloCategoria.getCategoriaXNombre(nombreCategoria);
				Proveedor proveedor = modeloProveedor.getProveedorXRazonSocial(razonSocialProveedor);
				producto.setCategoria(categoria);
				producto.setProveedor(proveedor);

				int salida = modelo.actualizarProducto(producto);
				this.listarProductos(modelo.listarProducto());

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
		this.limpiar();
		
		// Se obtiene la fila seleccionada
		int filaSeleccionada = this.table.getSelectedRow();
		System.out.println("Fila seleccionada -> " + filaSeleccionada);

		// Se obtiene el id de la fila seleccionada
		this.idSeleccionado = this.modeloTabla.getValueAt(filaSeleccionada, 0).toString();
		System.out.println("El ID seleccionado es: " + this.idSeleccionado);

		String nombre = this.modeloTabla.getValueAt(filaSeleccionada, 1).toString();
		String nombreCategoria = this.modeloTabla.getValueAt(filaSeleccionada, 2).toString();
		String razonSocialProveedor = this.modeloTabla.getValueAt(filaSeleccionada, 3).toString();
		String precioVenta = this.modeloTabla.getValueAt(filaSeleccionada, 4).toString();
		String precioCompra = this.modeloTabla.getValueAt(filaSeleccionada, 5).toString();
		String stockActual = this.modeloTabla.getValueAt(filaSeleccionada, 6).toString();
		String stockMinimo = this.modeloTabla.getValueAt(filaSeleccionada, 7).toString();
		String fechaRegistro = this.modeloTabla.getValueAt(filaSeleccionada, 8).toString();
		String descripcion = this.modeloTabla.getValueAt(filaSeleccionada, 9).toString();

		this.txtIdProducto.setText(this.idSeleccionado);
		this.txtNombre.setText(nombre);
		this.cboCategoria.setSelectedItem(nombreCategoria.toString());
		this.cboProveedor.setSelectedItem(razonSocialProveedor.toString());
		this.jdFechaRegistro.setDate(FechaUtil.stringToDate(fechaRegistro));
		this.txtPrecioCompra.setText(precioCompra);
		this.txtPrecioVenta.setText(precioVenta);
		this.spinnerStockActual.setValue(Integer.parseInt(stockActual));
		// this.txtStockActual.setText(stockActual);
		this.spinnerStockMinimo.setValue(Integer.parseInt(stockMinimo));
		// this.txtStockMinimo.setText(stockMinimo);
		this.txtDescripcion.setText(descripcion);

		// Se muestra la foto a partir del idSeleccionado
		try {
			Foto foto = this.modelo.getFoto(this.idSeleccionado);
			Image imagen = ImageIO.read(foto.getInputStream());
			this.lblFoto.setIcon(new ImageIcon(imagen.getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_DEFAULT)));
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
		if (e.getSource() == this.txtDescripcion) {
			keyReleasedTxtDescripcion(e);
		}
		if (e.getSource() == this.txtNombre) {
			keyReleasedTxtNombre(e);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	// Valida un máximo de 100 caracteres
	protected void keyReleasedTxtNombre(KeyEvent e) {
		int maxCaracteres = 100;
		if (this.txtNombre.getText().length() > maxCaracteres) {
			this.txtNombre.setText(this.txtNombre.getText().substring(0, maxCaracteres));
		}
	}

	// Valida un máximo de 200 caracteres
	protected void keyReleasedTxtDescripcion(KeyEvent e) {
		int maxCaracteres = 200;
		if (this.txtDescripcion.getText().length() > maxCaracteres) {
			this.txtDescripcion.setText(this.txtDescripcion.getText().substring(0, maxCaracteres));
		}
	}

	protected void mouseClickedLblFoto(MouseEvent e) {
		seleccionarFoto();
	}

	public void seleccionarFoto() {
		// Se crea el objeto JFileChooser
		JFileChooser fileChooser = new JFileChooser();

		// Se establece como directorio por defecto la ubicación del proyecto
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

		// Se establece el tipo de selección de archivo
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); // Solo
																	// ficheros

		// Se establece el filtro
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.jpg *.png *.gif", "jpg", "png", "gif");
		fileChooser.setFileFilter(filtro);

		// Se abre la ventana y se guarda la opción seleccionada por el usuario
		int respuesta = fileChooser.showOpenDialog(this);

		// Si el usuario eligió el botón Aceptar
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
		this.ficheroSeleccionado = new File("src/iconos/fotoProductoGenerica.png");
		this.lblFoto.setIcon(new ImageIcon(FrmProducto.class.getResource("/iconos/fotoProductoGenerica.png")));
	}
	
	public void generarReporte() {
		Map hashMap = new HashMap();
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		
		try {
			jasperReport = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/reportes/ReporteProductos.jrxml");
			jasperPrint = JasperFillManager.fillReport(jasperReport, hashMap, (new Conexion()).getConexion());
			JasperViewer view = new JasperViewer(jasperPrint, false);
			view.setTitle("Reporte de productos");
			view.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void actionPerformedBtnSubir(ActionEvent e) {
		this.seleccionarFoto();
	}
	
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		this.cancelarFoto();
	}
	
	protected void actionPerformedBtnReporte(ActionEvent e) {
		this.generarReporte();
	}
}
