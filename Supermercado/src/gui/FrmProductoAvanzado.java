package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
import util.Constantes;
import util.FechaUtil;
import util.Foto;
import util.Mensaje;
import util.TablaUtil;

public class FrmProductoAvanzado extends JInternalFrame
		implements ActionListener, MouseListener, ChangeListener, KeyListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelIngreso;
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JTextField txtNombre;
	private JTextField txtDescripcion;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnRegistrar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnSalir;
	private DefaultTableModel modeloTabla;
	private ProductoModel modelo;
	private String idSeleccionado;
	private JLabel lblDeRegistros;
	private JLabel lblNumeroRegistros;
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
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JCheckBox chkEstirar;
	private JPanel panelTable;
	private JToolBar toolBar;
	private JTextField txtConsultaCodigo;
	private JLabel lblCdigo_1;
	private JLabel lblDescripcin;
	private JTextField txtConsultaNombre;
	private JTextField txtConsultaDescripcion;
	private JLabel label;
	private JLabel lblCategora;
	private JComboBox<String> cboConsultaCategoria;
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
					FrmProductoAvanzado frame = new FrmProductoAvanzado();
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
	public FrmProductoAvanzado() {
		setFrameIcon(new ImageIcon(FrmProductoAvanzado.class.getResource("/iconos/productoDetallado.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle(".:: Productos ::.");
		setBounds(100, 100, 1236, 707);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panelIngreso = new JPanel();
		this.panelIngreso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panelIngreso.setBorder(
				new TitledBorder(null, "Ingreso de Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelIngreso.setBounds(779, 11, 431, 610);
		contentPane.add(panelIngreso);
		panelIngreso.setLayout(null);

		lblNombre = new JLabel("* Nombre");
		this.lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(7, 45, 85, 14);
		panelIngreso.add(lblNombre);

		lblDescripcion = new JLabel("Descripci\u00F3n");
		this.lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDescripcion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescripcion.setBounds(7, 68, 85, 14);
		panelIngreso.add(lblDescripcion);

		txtNombre = new JTextField();
		this.txtNombre.addKeyListener(this);
		txtNombre.setBounds(99, 43, 306, 20);
		panelIngreso.add(txtNombre);
		txtNombre.setColumns(10);

		txtDescripcion = new JTextField();
		this.txtDescripcion.addKeyListener(this);
		txtDescripcion.setBounds(99, 65, 306, 20);
		panelIngreso.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		this.lblCdigo = new JLabel("C\u00F3digo");
		this.lblCdigo.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblCdigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblCdigo.setBounds(7, 24, 85, 14);
		this.panelIngreso.add(this.lblCdigo);

		this.txtIdProducto = new JTextField();
		this.txtIdProducto.setEnabled(false);
		this.txtIdProducto.setBounds(99, 21, 148, 20);
		this.panelIngreso.add(this.txtIdProducto);
		this.txtIdProducto.setColumns(10);

		this.lblPrecioVenta = new JLabel("* Precio venta:");
		this.lblPrecioVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblPrecioVenta.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblPrecioVenta.setBounds(7, 181, 110, 14);
		this.panelIngreso.add(this.lblPrecioVenta);

		this.txtPrecioVenta = new JTextField();
		this.txtPrecioVenta.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.txtPrecioVenta.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtPrecioVenta.setBounds(127, 178, 96, 20);
		this.panelIngreso.add(this.txtPrecioVenta);
		this.txtPrecioVenta.setColumns(10);

		this.lblStockActual = new JLabel("* Stock:");
		this.lblStockActual.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblStockActual.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblStockActual.setBounds(24, 231, 94, 14);
		this.panelIngreso.add(this.lblStockActual);

		this.cboCategoria = new JComboBox<String>();
		this.cboCategoria.setBounds(99, 87, 228, 20);
		this.panelIngreso.add(this.cboCategoria);

		this.lblPrecioCompra = new JLabel("* Precio compra:");
		this.lblPrecioCompra.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblPrecioCompra.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblPrecioCompra.setBounds(7, 158, 108, 14);
		this.panelIngreso.add(this.lblPrecioCompra);

		this.txtPrecioCompra = new JTextField();
		this.txtPrecioCompra.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.txtPrecioCompra.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtPrecioCompra.setBounds(127, 156, 96, 20);
		this.panelIngreso.add(this.txtPrecioCompra);
		this.txtPrecioCompra.setColumns(10);

		this.lblCategoria = new JLabel("* Categor\u00EDa");
		this.lblCategoria.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblCategoria.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblCategoria.setBounds(7, 89, 85, 14);
		this.panelIngreso.add(this.lblCategoria);

		this.lblFecha = new JLabel("* Fecha:");
		this.lblFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblFecha.setBounds(20, 298, 102, 14);
		this.panelIngreso.add(this.lblFecha);

		this.jdFechaRegistro = new JDateChooser();
		this.jdFechaRegistro.setBounds(131, 295, 164, 20);
		this.panelIngreso.add(this.jdFechaRegistro);

		this.lblProveedor = new JLabel("* Proveedor");
		this.lblProveedor.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblProveedor.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblProveedor.setBounds(7, 111, 85, 14);
		this.panelIngreso.add(this.lblProveedor);

		this.cboProveedor = new JComboBox<String>();
		this.cboProveedor.setBounds(99, 109, 312, 20);
		this.panelIngreso.add(this.cboProveedor);

		this.lblStockMinimo = new JLabel("* Stock m\u00EDnimo:");
		this.lblStockMinimo.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblStockMinimo.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblStockMinimo.setBounds(24, 256, 94, 14);
		this.panelIngreso.add(this.lblStockMinimo);

		this.spinnerStockActual = new JSpinner();
		this.spinnerStockActual.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		this.spinnerStockActual.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.spinnerStockActual.setBounds(130, 228, 96, 20);
		this.panelIngreso.add(this.spinnerStockActual);

		this.spinnerStockMinimo = new JSpinner();
		this.spinnerStockMinimo.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		this.spinnerStockMinimo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.spinnerStockMinimo.setBounds(130, 250, 96, 20);
		this.panelIngreso.add(this.spinnerStockMinimo);

		this.separator = new JSeparator();
		this.separator.setBounds(18, 143, 398, 2);
		this.panelIngreso.add(this.separator);

		this.panelFoto = new JPanel();
		this.panelFoto.setBounds(24, 326, 381, 273);
		this.panelIngreso.add(this.panelFoto);
		this.panelFoto.setBorder(new TitledBorder(null, "Foto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelFoto.setLayout(null);

		this.lblFoto = new JLabel("");
		this.lblFoto.addMouseListener(this);
		this.lblFoto.setIcon(new ImageIcon(FrmProductoAvanzado.class.getResource("/iconos/fotoProductoGenerica.png")));
		this.lblFoto.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.lblFoto.setBounds(97, 20, 200, 200);
		this.panelFoto.add(this.lblFoto);

		this.btnSubir = new JButton("");
		this.btnSubir.addActionListener(this);
		this.btnSubir.setIcon(new ImageIcon(FrmProductoAvanzado.class.getResource("/iconos/btnSubirFoto.png")));
		this.btnSubir.setToolTipText("Seleccionar foto");
		this.btnSubir.setBounds(97, 229, 101, 33);
		this.panelFoto.add(this.btnSubir);

		this.btnCancelar = new JButton("");
		this.btnCancelar.addActionListener(this);
		this.btnCancelar.setIcon(new ImageIcon(FrmProductoAvanzado.class.getResource("/iconos/btnCancelFoto2.png")));
		this.btnCancelar.setToolTipText("Cancelar foto");
		this.btnCancelar.setBounds(202, 229, 95, 33);
		this.panelFoto.add(this.btnCancelar);

		this.separator_1 = new JSeparator();
		this.separator_1.setBounds(19, 216, 398, 2);
		this.panelIngreso.add(this.separator_1);

		this.separator_2 = new JSeparator();
		this.separator_2.setBounds(18, 281, 398, 2);
		this.panelIngreso.add(this.separator_2);

		this.chkEstirar = new JCheckBox("Estirar contenido");
		this.chkEstirar.addChangeListener(this);
		this.chkEstirar.setBounds(779, 628, 184, 23);
		this.contentPane.add(this.chkEstirar);

		this.panelTable = new JPanel();
		this.panelTable.setBounds(0, 70, 777, 480);
		this.contentPane.add(this.panelTable);
		this.panelTable.setLayout(null);

		scrollPane = new JScrollPane();
		this.scrollPane.setBounds(3, 0, 768, 426);
		this.panelTable.add(this.scrollPane);

		table = new JTable();
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setShowHorizontalLines(false);
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre", "Categoría", "Proveedor",
				"Precio Venta", "Precio Compra", "Stock", "Mínimo Stock", "Fecha Registro", "Descripción" }));
		scrollPane.setViewportView(table);

		this.formatearTabla();

		this.idSeleccionado = "";

		// Se crea el modelo de Producto y los otros modelos
		this.modelo = new ProductoModel();
		modeloCategoria = new CategoriaModel();
		modeloProveedor = new ProveedorModel();

		// Genera el nuevo código del Producto
		this.txtIdProducto.setText(this.modelo.generarCodigoProducto());

		this.lblDeRegistros = new JLabel("# de registros:");
		this.lblDeRegistros.setBounds(522, 557, 121, 16);
		this.contentPane.add(this.lblDeRegistros);
		this.lblDeRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDeRegistros.setHorizontalAlignment(SwingConstants.RIGHT);

		this.lblNumeroRegistros = new JLabel("");
		this.lblNumeroRegistros.setBounds(677, 557, 95, 14);
		this.contentPane.add(this.lblNumeroRegistros);
		this.lblNumeroRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblNumeroRegistros.setHorizontalAlignment(SwingConstants.LEFT);

		this.toolBar = new JToolBar();
		this.toolBar.setBounds(0, -1, 572, 29);
		this.contentPane.add(this.toolBar);

		btnRegistrar = new JButton("Grabar");
		this.btnRegistrar.setIcon(new ImageIcon(FrmProductoAvanzado.class.getResource("/iconos/btnGrabarChico.png")));
		this.toolBar.add(this.btnRegistrar);
		this.btnRegistrar.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.btnRegistrar.setToolTipText("Registrar");

		btnEliminar = new JButton("Eliminar");
		this.btnEliminar.setIcon(new ImageIcon(FrmProductoAvanzado.class.getResource("/iconos/btnEliminarChico.png")));
		this.toolBar.add(this.btnEliminar);
		this.btnEliminar.setToolTipText("Eliminar");
		btnEliminar.addActionListener(this);
		this.btnEliminar.setEnabled(false);

		btnActualizar = new JButton("Actualizar");
		this.btnActualizar
				.setIcon(new ImageIcon(FrmProductoAvanzado.class.getResource("/iconos/btnActualizarChico.png")));
		this.toolBar.add(this.btnActualizar);
		this.btnActualizar.setToolTipText("Actualizar");
		btnActualizar.addActionListener(this);
		this.btnActualizar.setEnabled(false);

		this.btnLimpiar = new JButton("Limpiar");
		this.btnLimpiar.setIcon(new ImageIcon(FrmProductoAvanzado.class.getResource("/iconos/btnLimpiarChico.png")));
		this.toolBar.add(this.btnLimpiar);
		this.btnLimpiar.addActionListener(this);
		this.btnLimpiar.setToolTipText("Limpiar");

		btnSalir = new JButton("Salir");
		this.btnSalir.setIcon(new ImageIcon(FrmProductoAvanzado.class.getResource("/iconos/btnCerrarChico.png")));
		this.toolBar.add(this.btnSalir);
		this.btnSalir.setToolTipText("Salir");

		this.txtConsultaCodigo = new JTextField();
		this.txtConsultaCodigo.addKeyListener(this);
		this.txtConsultaCodigo.setBounds(3, 48, 147, 20);
		this.contentPane.add(this.txtConsultaCodigo);
		this.txtConsultaCodigo.setColumns(10);

		this.lblCdigo_1 = new JLabel("C\u00D3DIGO");
		this.lblCdigo_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblCdigo_1.setForeground(Color.BLUE);
		this.lblCdigo_1.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblCdigo_1.setBounds(2, 28, 148, 19);
		this.contentPane.add(this.lblCdigo_1);

		this.lblDescripcin = new JLabel("NOMBRE");
		this.lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDescripcin.setForeground(Color.BLUE);
		this.lblDescripcin.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblDescripcin.setBounds(152, 28, 207, 19);
		this.contentPane.add(this.lblDescripcin);

		this.txtConsultaNombre = new JTextField();
		this.txtConsultaNombre.addKeyListener(this);
		this.txtConsultaNombre.setColumns(10);
		this.txtConsultaNombre.setBounds(152, 48, 207, 20);
		this.contentPane.add(this.txtConsultaNombre);

		this.txtConsultaDescripcion = new JTextField();
		this.txtConsultaDescripcion.addKeyListener(this);
		this.txtConsultaDescripcion.setColumns(10);
		this.txtConsultaDescripcion.setBounds(360, 48, 251, 20);
		this.contentPane.add(this.txtConsultaDescripcion);

		this.label = new JLabel("DESCRIPCI\u00D3N");
		this.label.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.label.setForeground(Color.BLUE);
		this.label.setHorizontalAlignment(SwingConstants.CENTER);
		this.label.setBounds(359, 28, 252, 19);
		this.contentPane.add(this.label);

		this.lblCategora = new JLabel("CATEGOR\u00CDA");
		this.lblCategora.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblCategora.setForeground(Color.BLUE);
		this.lblCategora.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblCategora.setBounds(613, 28, 156, 19);
		this.contentPane.add(this.lblCategora);

		this.cboConsultaCategoria = new JComboBox<String>();
		this.cboConsultaCategoria.addItemListener(this);
		this.cboConsultaCategoria.setBounds(613, 48, 156, 20);
		this.contentPane.add(this.cboConsultaCategoria);
		btnSalir.addActionListener(this);
		btnRegistrar.addActionListener(this);

		// Se obtiene el modelo de Tabla
		this.modeloTabla = (DefaultTableModel) this.table.getModel();

		this.llenarCategorias();
		this.llenarProveedores();
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

	public void llenarCategorias() {
		List<Categoria> categorias = modeloCategoria.listarCategoria();
		for (Categoria categoria : categorias) {
			this.cboCategoria.addItem(categoria.getNombre());
			this.cboConsultaCategoria.addItem(categoria.getNombre());
		}
	}

	public void llenarProveedores() {
		List<Proveedor> proveedores = modeloProveedor.listarProveedor();
		for (Proveedor proveedor : proveedores) {
			this.cboProveedor.addItem(proveedor.getRazonSocial());
		}
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
		modeloColumna.getColumn(9).setPreferredWidth(170); // Descripción

		// Se centran algunas columnas
		for (int i = 0; i < modeloColumna.getColumnCount(); ++i) {
			TablaUtil.centrarCelda(this.table, i);
		}
	}

	public void listarProductos(List<Producto> productos) {
		// Se limpia la tabla
		this.modeloTabla.setRowCount(0);

		// Se añade a la tabla los productos fila a fila
		for (Producto producto : productos) {
			Object[] fila = { producto.getIdProducto(), producto.getNombre(), producto.getCategoria().getNombre(),
					producto.getProveedor().getRazonSocial(), producto.getPrecioVenta(), producto.getPrecioCompra(),
					producto.getStockActual(), producto.getStockMinimo(),
					FechaUtil.formatoDeFechaClasico(producto.getFechaRegistro()), producto.getDescripcion() };

			this.modeloTabla.addRow(fila);
		}

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
		this.spinnerStockMinimo.setValue(0);
		this.txtIdProducto.setText(this.modelo.generarCodigoProducto());

		this.listarProductos(modelo.listarProducto());
		this.txtNombre.requestFocus();

		this.btnRegistrar.setEnabled(true);
		this.btnEliminar.setEnabled(false);
		this.btnActualizar.setEnabled(false);
		this.cancelarFoto();
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
		// this.limpiar();
		
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
			Foto foto = this.modelo.getFoto(idSeleccionado);
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

	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == this.chkEstirar) {
			stateChangedChkEstirar(e);
		}
	}

	protected void stateChangedChkEstirar(ChangeEvent e) {
		if (this.chkEstirar.isSelected()) {
			// Muestra la tabla totalmente estirada
			this.table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			this.panelTable.setBounds(0, 70, 1216, 480);
			this.scrollPane.setBounds(3, 0, 1202, 426);
			this.panelIngreso.setVisible(false);

		} else {
			// Vuelve a la normalidad
			this.panelTable.setBounds(0, 70, 777, 480);
			this.scrollPane.setBounds(3, 0, 768, 426);
			this.panelIngreso.setVisible(true);

			this.formatearTabla();
		}
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
		if (e.getSource() == this.txtConsultaDescripcion) {
			keyReleasedTxtConsultaDescripcion(e);
		}
		if (e.getSource() == this.txtConsultaNombre) {
			keyReleasedTxtConsultaNombre(e);
		}
		if (e.getSource() == this.txtConsultaCodigo) {
			keyReleasedTxtConsultaCodigo(e);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	protected void keyReleasedTxtConsultaCodigo(KeyEvent e) {
		String idProducto = this.txtConsultaCodigo.getText().trim();
		this.listarProductos(modelo.listarProductoxCodigo(idProducto));
	}

	protected void keyReleasedTxtConsultaNombre(KeyEvent e) {
		String nombre = this.txtConsultaNombre.getText().trim();
		this.listarProductos(modelo.listarProductoxNombre(nombre));
	}

	protected void keyReleasedTxtConsultaDescripcion(KeyEvent e) {
		String descripcion = this.txtConsultaDescripcion.getText().trim();
		this.listarProductos(modelo.listarProductoxDescripcion(descripcion));
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == this.cboConsultaCategoria) {
			itemStateChangedCboConsultaCategoria(e);
		}
	}

	protected void itemStateChangedCboConsultaCategoria(ItemEvent e) {
		String categoria = this.cboConsultaCategoria.getSelectedItem().toString();
		Categoria objCategoria = this.modeloCategoria.getCategoriaXNombre(categoria);
		this.listarProductos(modelo.listarProductoxCategoria(objCategoria.getIdCategoria()));
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

	protected void mouseClickedLblFoto(MouseEvent e) {
		this.seleccionarFoto();
	}
	protected void actionPerformedBtnSubir(ActionEvent e) {
		this.seleccionarFoto();
	}
	protected void actionPerformedBtnCancelar(ActionEvent e) {
		this.cancelarFoto();
	}
}
