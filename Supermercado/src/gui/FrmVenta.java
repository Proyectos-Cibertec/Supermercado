package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import entidad.DetalleVenta;
import entidad.Producto;
import entidad.Venta;
import inicio.Sistema;
import model.ClienteModel;
import model.EmpleadoModel;
import model.ProductoModel;
import model.VentaModel;
import util.FechaUtil;
import util.Mensaje;
import util.Parametros;
import util.Redondear;
import util.TablaUtil;

public class FrmVenta extends JInternalFrame implements ActionListener, MouseListener, ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblR;
	private JLabel lblEmpleado;
	public static JTextField txtCodigoEmpleado;
	public static JTextField txtNombreEmpleado;
	private JLabel lblCliente;
	public static JTextField txtCodigoCliente;
	public static JTextField txtCliente;
	private JLabel lblTipoDeComprobante;
	private JComboBox<String> cboTipoComprobante;
	private JTextField txtSerieComprobante;
	private JTextField txtNumeroComprobante;
	private JLabel lblIgv;
	private JTextField txtIgv;
	private JPanel panel;
	private JLabel lblCdigo;
	public static JTextField txtCodigoProducto;
	private JButton btnBuscar;
	public static JTextField txtDescripcionProducto;
	private JLabel lblCantidad;
	private JButton btnAgregar;
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel modeloTabla;
	private JLabel lblDscto;
	private JTextField txtDescuento;
	private JLabel lblPrecioDeVenta;
	public static JTextField txtPrecioVenta;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnLimpiar;
	private JButton btnRegistrarVenta;
	private JButton btnCancelar;
	private JButton btnBuscarCliente;
	private JLabel lblFechaDeVenta;
	private JDateChooser jdFechaVenta;
	private JSpinner spinnerCantidad;
	private JLabel lblNewLabel;
	private JButton btnLimpiar_1;
	private JLabel lblIdVenta;
	private JTextField txtIdVenta;
	private VentaModel modeloVenta;
	private ClienteModel modeloCliente;
	private EmpleadoModel modeloEmpleado;
	private JPanel panel_1;
	private JLabel lblRucN;
	private JLabel lblTipoComprobante;
	private JLabel lblNewLabel_1;
	private JPanel panel_2;
	private JLabel lblNomre;
	private JPanel panel_3;
	private JLabel lblDireccin;
	public static JTextField txtDireccionCliente;
	private JLabel lblDni;
	public static JTextField txtDni;
	private JLabel lblDistrito;
	public static JTextField txtDistrito;
	private JPanel panel_4;
	private JLabel lblNeto;
	private JTextField txtSubtotal;
	private JLabel lblIgv_1;
	private JTextField txtImpuesto;
	private JTextField txtTotal;
	private JLabel lblTotalAPagar;
	private double subtotal;
	private double impuesto;
	private double totalAPagar;
	private JLabel lblNewLabel_2;
	private double igv = Parametros.IGV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmVenta frame = new FrmVenta();
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
	public FrmVenta() {
		setFrameIcon(new ImageIcon(FrmVenta.class.getResource("/iconos/venta.png")));
		setClosable(true);
		setIconifiable(true);
		setTitle(".:: Venta ::.");
		setBounds(100, 100, 899, 556);
		getContentPane().setLayout(null);

		this.lblR = new JLabel("REGISTRO DE VENTA");
		this.lblR.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.lblR.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblR.setBounds(292, 485, 457, 14);
		getContentPane().add(this.lblR);

		this.panel = new JPanel();
		this.panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panel.setBounds(3, 157, 876, 288);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);

		this.lblCdigo = new JLabel("C\u00F3digo:");
		this.lblCdigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblCdigo.setBounds(9, 17, 46, 14);
		this.panel.add(this.lblCdigo);

		txtCodigoProducto = new JTextField();
		txtCodigoProducto.setBackground(Color.WHITE);
		txtCodigoProducto.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigoProducto.setEditable(false);
		txtCodigoProducto.setBounds(58, 14, 64, 20);
		this.panel.add(txtCodigoProducto);
		txtCodigoProducto.setColumns(10);

		this.btnBuscar = new JButton("Buscar");
		this.btnBuscar.setIcon(new ImageIcon(FrmVenta.class.getResource("/iconos/buscar20.png")));
		this.btnBuscar.addActionListener(this);
		this.btnBuscar.setBounds(125, 13, 89, 23);
		this.panel.add(this.btnBuscar);

		txtDescripcionProducto = new JTextField();
		txtDescripcionProducto.setEditable(false);
		txtDescripcionProducto.setBounds(224, 14, 298, 20);
		this.panel.add(txtDescripcionProducto);
		txtDescripcionProducto.setColumns(10);

		this.lblCantidad = new JLabel("Cantidad:");
		this.lblCantidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblCantidad.setBounds(532, 17, 64, 14);
		this.panel.add(this.lblCantidad);

		this.btnAgregar = new JButton("");
		this.btnAgregar.setToolTipText("A\u00F1adir producto");
		this.btnAgregar.setIcon(new ImageIcon(FrmVenta.class.getResource("/iconos/agregar16.png")));
		this.btnAgregar.addActionListener(this);
		this.btnAgregar.setBounds(379, 40, 35, 25);
		this.panel.add(this.btnAgregar);

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(9, 69, 729, 196);
		this.panel.add(this.scrollPane);

		this.table = new JTable();
		this.table.setFillsViewportHeight(true);
		this.table.addMouseListener(this);
		this.table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "C\u00D3DIGO", "DESCRIPCI\u00D3N",
				"CANTIDAD", "PRECIO VENTA", "DESCT.", "PRECIO FINAL", "TOTAL S/." }));
		this.scrollPane.setViewportView(this.table);

		this.lblDscto = new JLabel("Dscto.");
		this.lblDscto.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDscto.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDscto.setBounds(223, 46, 46, 14);
		this.panel.add(this.lblDscto);

		this.txtDescuento = new JTextField();
		this.txtDescuento.setBounds(273, 43, 89, 20);
		this.panel.add(this.txtDescuento);
		this.txtDescuento.setColumns(10);

		this.lblPrecioDeVenta = new JLabel("Precio de venta:");
		this.lblPrecioDeVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblPrecioDeVenta.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblPrecioDeVenta.setBounds(9, 45, 113, 14);
		this.panel.add(this.lblPrecioDeVenta);

		txtPrecioVenta = new JTextField();
		txtPrecioVenta.setColumns(10);
		txtPrecioVenta.setBounds(125, 42, 89, 20);
		this.panel.add(txtPrecioVenta);

		this.btnEliminar = new JButton("");
		this.btnEliminar.setToolTipText("Quitar producto");
		this.btnEliminar.setIcon(new ImageIcon(FrmVenta.class.getResource("/iconos/quitar242.png")));
		this.btnEliminar.addActionListener(this);
		this.btnEliminar.setBounds(415, 40, 35, 25);
		this.panel.add(this.btnEliminar);

		this.btnActualizar = new JButton("");
		this.btnActualizar.setToolTipText("Actualizar producto");
		this.btnActualizar.setIcon(new ImageIcon(FrmVenta.class.getResource("/iconos/btnActualizarChico.png")));
		this.btnActualizar.addActionListener(this);
		this.btnActualizar.setBounds(451, 40, 35, 25);
		this.panel.add(this.btnActualizar);

		this.btnLimpiar = new JButton("");
		this.btnLimpiar.setToolTipText("Limpiar detalle");
		this.btnLimpiar.setIcon(new ImageIcon(FrmVenta.class.getResource("/iconos/limpiar16.png")));
		this.btnLimpiar.addActionListener(this);
		this.btnLimpiar.setBounds(487, 40, 35, 25);
		this.panel.add(this.btnLimpiar);

		this.spinnerCantidad = new JSpinner();
		this.spinnerCantidad.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		this.spinnerCantidad.setBounds(596, 13, 56, 22);
		this.panel.add(this.spinnerCantidad);

		this.lblNeto = new JLabel("SUBTOTAL");
		this.lblNeto.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblNeto.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNeto.setBounds(748, 92, 120, 14);
		this.panel.add(this.lblNeto);

		this.txtSubtotal = new JTextField();
		this.txtSubtotal.setBackground(Color.WHITE);
		this.txtSubtotal.setEditable(false);
		this.txtSubtotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.txtSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtSubtotal.setBounds(748, 107, 120, 31);
		this.panel.add(this.txtSubtotal);
		this.txtSubtotal.setColumns(10);

		this.lblIgv_1 = new JLabel("IGV");
		this.lblIgv_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblIgv_1.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblIgv_1.setBounds(750, 149, 118, 14);
		this.panel.add(this.lblIgv_1);

		this.txtImpuesto = new JTextField();
		this.txtImpuesto.setBackground(Color.WHITE);
		this.txtImpuesto.setEditable(false);
		this.txtImpuesto.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.txtImpuesto.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtImpuesto.setBounds(750, 164, 118, 31);
		this.panel.add(this.txtImpuesto);
		this.txtImpuesto.setColumns(10);

		this.txtTotal = new JTextField();
		this.txtTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtTotal.setForeground(Color.GREEN);
		this.txtTotal.setBackground(Color.BLACK);
		this.txtTotal.setColumns(10);
		this.txtTotal.setBounds(748, 234, 118, 31);
		this.panel.add(this.txtTotal);

		this.lblTotalAPagar = new JLabel("TOTAL A PAGAR");
		this.lblTotalAPagar.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblTotalAPagar.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblTotalAPagar.setBounds(750, 218, 118, 14);
		this.panel.add(this.lblTotalAPagar);
		
		this.lblNewLabel_2 = new JLabel("");
		this.lblNewLabel_2.setIcon(new ImageIcon(FrmVenta.class.getResource("/iconos/logoVentaCompraHorizontal.png")));
		this.lblNewLabel_2.setBounds(662, 4, 206, 61);
		this.panel.add(this.lblNewLabel_2);

		this.btnRegistrarVenta = new JButton("Registrar Venta");
		this.btnRegistrarVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.btnRegistrarVenta.setIcon(new ImageIcon(FrmVenta.class.getResource("/iconos/venta.png")));
		this.btnRegistrarVenta.addActionListener(this);
		this.btnRegistrarVenta.setBounds(10, 474, 162, 41);
		getContentPane().add(this.btnRegistrarVenta);

		this.btnCancelar = new JButton("Cancelar");
		this.btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.btnCancelar.setIcon(new ImageIcon(FrmVenta.class.getResource("/iconos/btnSalir.png")));
		this.btnCancelar.addActionListener(this);
		this.btnCancelar.setBounds(749, 474, 130, 41);
		getContentPane().add(this.btnCancelar);

		this.btnLimpiar_1 = new JButton("Limpiar");
		this.btnLimpiar_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.btnLimpiar_1.setIcon(new ImageIcon(FrmVenta.class.getResource("/iconos/btnLimpiar.png")));
		this.btnLimpiar_1.addActionListener(this);
		this.btnLimpiar_1.setBounds(182, 474, 111, 41);
		getContentPane().add(this.btnLimpiar_1);

		this.formatearTabla();

		// Se obtiene el modelo de Tabla
		this.modeloTabla = (DefaultTableModel) this.table.getModel();

		// Se crea el modelo de Venta y los otros modelos
		this.modeloVenta = new VentaModel();
		this.modeloEmpleado = new EmpleadoModel();
		this.modeloCliente = new ClienteModel();

		this.panel_1 = new JPanel();
		this.panel_1.setBackground(Color.WHITE);
		this.panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		this.panel_1.setBounds(637, 71, 242, 83);
		getContentPane().add(this.panel_1);
		this.panel_1.setLayout(null);

		this.lblRucN = new JLabel("R.U.C.    N\u00BA 20501345122");
		this.lblRucN.setFont(new Font("Arial", Font.BOLD, 16));
		this.lblRucN.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblRucN.setBounds(10, 5, 222, 20);
		this.panel_1.add(this.lblRucN);

		this.txtSerieComprobante = new JTextField();
		this.txtSerieComprobante.setBackground(Color.WHITE);
		this.txtSerieComprobante.setBorder(null);
		this.txtSerieComprobante.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtSerieComprobante.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.txtSerieComprobante.setBounds(20, 54, 54, 20);
		this.panel_1.add(this.txtSerieComprobante);
		this.txtSerieComprobante.setEditable(false);
		this.txtSerieComprobante.setColumns(10);

		this.txtNumeroComprobante = new JTextField();
		this.txtNumeroComprobante.setBackground(Color.WHITE);
		this.txtNumeroComprobante.setBorder(null);
		this.txtNumeroComprobante.setForeground(Color.BLUE);
		this.txtNumeroComprobante.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtNumeroComprobante.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.txtNumeroComprobante.setBounds(84, 53, 148, 20);
		this.panel_1.add(this.txtNumeroComprobante);
		this.txtNumeroComprobante.setEditable(false);
		this.txtNumeroComprobante.setColumns(10);
		this.txtNumeroComprobante.setText(this.modeloVenta.generarNumeroFactura());

		this.lblTipoComprobante = new JLabel("FACTURA");
		this.lblTipoComprobante.setFont(new Font("Arial", Font.BOLD, 20));
		this.lblTipoComprobante.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblTipoComprobante.setBounds(10, 25, 222, 30);
		this.panel_1.add(this.lblTipoComprobante);

		this.lblNewLabel_1 = new JLabel("-");
		this.lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		this.lblNewLabel_1.setBounds(63, 52, 35, 20);
		this.panel_1.add(this.lblNewLabel_1);

		this.panel_2 = new JPanel();
		this.panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panel_2.setBounds(3, 3, 877, 31);
		getContentPane().add(this.panel_2);
		this.panel_2.setLayout(null);

		this.lblIdVenta = new JLabel("Venta:");
		this.lblIdVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblIdVenta.setBounds(10, 8, 46, 14);
		this.panel_2.add(this.lblIdVenta);

		this.txtIdVenta = new JTextField();
		this.txtIdVenta.setBounds(48, 5, 86, 20);
		this.panel_2.add(this.txtIdVenta);
		this.txtIdVenta.setEditable(false);
		this.txtIdVenta.setColumns(10);

		// Genera el nuevo código de la venta y el número de Factura a emitir
		this.txtIdVenta.setText(this.modeloVenta.generarCodigoVenta());

		this.lblEmpleado = new JLabel("Empleado:");
		this.lblEmpleado.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblEmpleado.setBounds(149, 8, 80, 14);
		this.panel_2.add(this.lblEmpleado);

		txtCodigoEmpleado = new JTextField();
		txtCodigoEmpleado.setBackground(Color.WHITE);
		txtCodigoEmpleado.setBounds(229, 5, 86, 20);
		this.panel_2.add(txtCodigoEmpleado);
		txtCodigoEmpleado.setEditable(false);
		txtCodigoEmpleado.setColumns(10);

		txtNombreEmpleado = new JTextField();
		txtNombreEmpleado.setBackground(Color.WHITE);
		txtNombreEmpleado.setBounds(325, 5, 244, 20);
		this.panel_2.add(txtNombreEmpleado);
		txtNombreEmpleado.setEditable(false);
		txtNombreEmpleado.setColumns(10);

		this.lblFechaDeVenta = new JLabel("Fecha:");
		this.lblFechaDeVenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblFechaDeVenta.setBounds(590, 9, 50, 14);
		this.panel_2.add(this.lblFechaDeVenta);

		this.jdFechaVenta = new JDateChooser();
		jdFechaVenta.setDateFormatString("dd/MMM/yyyy");
		this.jdFechaVenta.setBounds(627, 5, 89, 20);
		this.panel_2.add(this.jdFechaVenta);

		this.lblIgv = new JLabel("IGV:");
		this.lblIgv.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblIgv.setBounds(738, 8, 30, 14);
		this.panel_2.add(this.lblIgv);

		this.txtIgv = new JTextField();
		this.txtIgv.setForeground(Color.RED);
		this.txtIgv.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.txtIgv.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtIgv.setBounds(770, 5, 59, 20);
		this.panel_2.add(this.txtIgv);
		this.txtIgv.setColumns(10);

		this.lblNewLabel = new JLabel("%");
		this.lblNewLabel.setBounds(837, 8, 30, 14);
		this.panel_2.add(this.lblNewLabel);

		this.panel_3 = new JPanel();
		this.panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panel_3.setBounds(3, 71, 624, 83);
		getContentPane().add(this.panel_3);
		this.panel_3.setLayout(null);

		this.lblCliente = new JLabel("Cod. Cliente:");
		this.lblCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblCliente.setBounds(11, 11, 71, 14);
		this.panel_3.add(this.lblCliente);

		txtCodigoCliente = new JTextField();
		txtCodigoCliente.setBackground(Color.WHITE);
		txtCodigoCliente.setBounds(11, 27, 101, 20);
		this.panel_3.add(txtCodigoCliente);
		txtCodigoCliente.setEditable(false);
		txtCodigoCliente.setColumns(10);

		this.btnBuscarCliente = new JButton("");
		this.btnBuscarCliente.setBorder(UIManager.getBorder("Button.border"));
		this.btnBuscarCliente.setIcon(new ImageIcon(FrmVenta.class.getResource("/iconos/buscar32.png")));
		this.btnBuscarCliente.setBounds(551, 8, 64, 62);
		this.panel_3.add(this.btnBuscarCliente);

		txtCliente = new JTextField();
		txtCliente.setBackground(Color.WHITE);
		txtCliente.setBounds(114, 27, 324, 20);
		this.panel_3.add(txtCliente);
		txtCliente.setEditable(false);
		txtCliente.setColumns(10);

		this.lblNomre = new JLabel("Nombre:");
		this.lblNomre.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblNomre.setBounds(114, 11, 324, 14);
		this.panel_3.add(this.lblNomre);

		this.lblDireccin = new JLabel("Direcci\u00F3n:");
		this.lblDireccin.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDireccin.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblDireccin.setBounds(10, 55, 72, 14);
		this.panel_3.add(this.lblDireccin);

		txtDireccionCliente = new JTextField();
		txtDireccionCliente.setBackground(Color.WHITE);
		txtDireccionCliente.setEditable(false);
		txtDireccionCliente.setBounds(85, 51, 259, 20);
		this.panel_3.add(txtDireccionCliente);
		txtDireccionCliente.setColumns(10);

		this.lblDni = new JLabel("DNI:");
		this.lblDni.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDni.setBounds(440, 11, 145, 14);
		this.panel_3.add(this.lblDni);

		txtDni = new JTextField();
		txtDni.setBackground(Color.WHITE);
		txtDni.setEditable(false);
		txtDni.setBounds(441, 27, 104, 20);
		panel_3.add(txtDni);
		txtDni.setColumns(10);

		this.lblDistrito = new JLabel("Distrito");
		this.lblDistrito.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblDistrito.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDistrito.setBounds(348, 55, 63, 14);
		this.panel_3.add(this.lblDistrito);

		txtDistrito = new JTextField();
		txtDistrito.setBackground(Color.WHITE);
		txtDistrito.setEditable(false);
		txtDistrito.setColumns(10);
		txtDistrito.setBounds(407, 50, 138, 20);
		this.panel_3.add(txtDistrito);

		this.panel_4 = new JPanel();
		this.panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panel_4.setBounds(3, 36, 877, 32);
		getContentPane().add(this.panel_4);
		this.panel_4.setLayout(null);

		this.lblTipoDeComprobante = new JLabel("Tipo de comprobante:");
		this.lblTipoDeComprobante.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblTipoDeComprobante.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblTipoDeComprobante.setBounds(3, 8, 127, 14);
		this.panel_4.add(this.lblTipoDeComprobante);

		this.cboTipoComprobante = new JComboBox<String>();
		this.cboTipoComprobante.setBounds(137, 4, 106, 20);
		this.panel_4.add(this.cboTipoComprobante);
		this.cboTipoComprobante.setFont(new Font("Tahoma", Font.PLAIN, 11));
		this.cboTipoComprobante.addItemListener(this);
		this.cboTipoComprobante.setModel(new DefaultComboBoxModel<String>(new String[] { "FACTURA", "BOLETA" }));
		this.btnBuscarCliente.addActionListener(this);
		
		this.limpiar();
	}

	public void formatearTabla() {
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Se establece un ancho a las columnas
		TableColumnModel modeloColumna = this.table.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(75); // ID Producto
		modeloColumna.getColumn(1).setPreferredWidth(270); // Descripción
		modeloColumna.getColumn(2).setPreferredWidth(70); // Cantidad
		modeloColumna.getColumn(3).setPreferredWidth(85); // Precio de venta
		modeloColumna.getColumn(4).setPreferredWidth(75); // Descuento
		modeloColumna.getColumn(5).setPreferredWidth(75); // Precio Final
		modeloColumna.getColumn(6).setPreferredWidth(75); // Total

		// Se centran algunas columnas
		for (int i = 0; i < modeloColumna.getColumnCount(); ++i) {
			TablaUtil.centrarCelda(this.table, i);
		}
	}

	public void actualizarTotales() {
		this.txtSubtotal.setText(String.valueOf(Redondear.redondear(this.subtotal, 2)));
		this.txtImpuesto.setText(String.valueOf(Redondear.redondear(this.impuesto, 2)));
		this.txtTotal.setText(String.valueOf(Redondear.redondear(this.totalAPagar, 2)));
	}

	public void limpiar() {
		txtCodigoCliente.setText("");
		txtCliente.setText("");
		txtDireccionCliente.setText("");
		txtDni.setText("");
		txtDistrito.setText("");
		this.cboTipoComprobante.setSelectedIndex(0);
		this.txtSerieComprobante.setText("001");
		this.txtIgv.setText(String.valueOf(this.igv * 100)); // Carga valor de IGV de base de datos
		this.jdFechaVenta.setDate(FechaUtil.today());
		this.modeloTabla.setRowCount(0);
		this.txtIdVenta.setText(this.modeloVenta.generarCodigoVenta());
		this.txtNumeroComprobante.setText(this.modeloVenta.generarNumeroFactura());

		this.subtotal = 0;
		this.impuesto = 0;
		this.totalAPagar = 0;
		this.actualizarTotales();

		this.limpiarDetalle();
	}

	public void limpiarDetalle() {
		txtCodigoProducto.setText("");
		txtDescripcionProducto.setText("");
		this.spinnerCantidad.setValue(1);
		txtPrecioVenta.setText("0.00");
		this.txtDescuento.setText("0.00");
	}

	// Centra un JInternalFrame
	public void centrar(JInternalFrame frm) {
		// Dimensiones de la pantalla
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		// Dimensiones del formulario
		Dimension ventana = frm.getSize();

		int posX = (int) (pantalla.getWidth() - ventana.getWidth()) / 2;
		frm.setLocation(posX, 40);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnLimpiar_1) {
			actionPerformedBtnLimpiar_1(e);
		}
		if (e.getSource() == this.btnBuscarCliente) {
			actionPerformedBtnBuscarCliente(e);
		}
		if (e.getSource() == this.btnCancelar) {
			actionPerformedBtnCancelar(e);
		}
		if (e.getSource() == this.btnRegistrarVenta) {
			actionPerformedBtnRegistrarVenta(e);
		}
		if (e.getSource() == this.btnLimpiar) {
			actionPerformedBtnLimpiar(e);
		}
		if (e.getSource() == this.btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == this.btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == this.btnAgregar) {
			actionPerformedBtnAgregar(e);
		}
		if (e.getSource() == this.btnBuscar) {
			actionPerformedBtnBuscar(e);
		}
	}

	protected void actionPerformedBtnBuscar(ActionEvent e) {
		FrmBuscarProducto frmBuscarProducto = new FrmBuscarProducto();
		Sistema.desktopPane.add(frmBuscarProducto);
		Sistema.centrar(frmBuscarProducto);
		frmBuscarProducto.setVisible(true);
	}

	protected void actionPerformedBtnAgregar(ActionEvent e) {
		String codigoProducto = txtCodigoProducto.getText().trim();
		String descripcionProducto = txtDescripcionProducto.getText().trim();
		String strCantidad = this.spinnerCantidad.getValue().toString().trim();
		String strPrecioVenta = txtPrecioVenta.getText().trim();
		String strDescuento = this.txtDescuento.getText().trim();

		// Validar (cantidad, precioVenta, descuento)
		if (codigoProducto.length() == 0) { // Valida que código de producto no
											// esté vacío
			Mensaje.mensajeError(this, "No ha seleccionado ningún producto para vender");
			this.btnBuscar.requestFocus();
			return;
		}

		if (!strCantidad.matches("\\d+")) {
			Mensaje.mensajeError(this, "Error de formato de número");
			this.spinnerCantidad.requestFocus();
			return;
		}

		if (!strPrecioVenta.matches("\\d+|\\d+.\\d{1,2}")) {
			Mensaje.mensajeError(this, "Error de formato de precio (#.##)");
			txtPrecioVenta.requestFocus();
			return;
		}

		if (!strDescuento.matches("\\d+|\\d+.\\d{1,2}")) {
			Mensaje.mensajeError(this, "Error de formato de descuento (#.##)");
			this.txtDescuento.requestFocus();
			return;
		}

		int cantidad = Integer.parseInt(strCantidad);
		double precioVenta = Double.parseDouble(strPrecioVenta);
		double descuento = Double.parseDouble(strDescuento);
		double precioFinal = precioVenta - descuento;
		double total = precioFinal * cantidad;

		// Validación de productos repetidos
		for (int i = 0; i < this.modeloTabla.getRowCount(); ++i) {
			String codProducto = this.modeloTabla.getValueAt(i, 0).toString();
			if (codigoProducto.equalsIgnoreCase(codProducto)) {
				Mensaje.mensajeError(this, "El producto ya existe");
				txtCodigoProducto.setText("");
				txtDescripcionProducto.setText("");
				this.btnBuscar.requestFocus();
				return;
			}
		}

		// Se verifica si el stock de producto es >= que la cantidad a vender
		ProductoModel modeloProducto = new ProductoModel();
		Producto producto = modeloProducto.getProducto(codigoProducto);
		int stockActual = producto.getStockActual();
		if (cantidad > stockActual) {
			Mensaje.mensajeError(this, "No hay stock suficiente para la venta. Solo puede vender como máximo: "
					+ stockActual + " productos");
			this.spinnerCantidad.requestFocus();
			return;
		}

		// Se agrega a la lista de productos a vender
		Object[] fila = { codigoProducto, descripcionProducto, cantidad, Redondear.redondear(precioVenta, 2),
				Redondear.redondear(descuento, 2), Redondear.redondear(precioFinal, 2), Redondear.redondear(total, 2) };
		this.modeloTabla.addRow(fila);
		this.limpiarDetalle();

		this.subtotal += total;
		this.impuesto = this.igv * this.subtotal;
		this.totalAPagar = this.subtotal + this.impuesto;
		this.actualizarTotales();
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		int filaSeleccionada = this.table.getSelectedRow();

		if (filaSeleccionada == -1) {
			Mensaje.mensajeError(this, "Seleccione un producto para quitarlo de la lista");
		} else {

			double total = Double.parseDouble(this.modeloTabla.getValueAt(filaSeleccionada, 6).toString());

			this.subtotal -= total;
			this.impuesto = this.igv * this.subtotal;
			this.totalAPagar = this.subtotal + this.impuesto;
			this.actualizarTotales();

			this.modeloTabla.removeRow(filaSeleccionada);

			this.limpiarDetalle();
		}
	}

	protected void actionPerformedBtnActualizar(ActionEvent e) {
		String codigoProducto = txtCodigoProducto.getText().trim();
		String strCantidad = this.spinnerCantidad.getValue().toString().trim();
		String strPrecioVenta = txtPrecioVenta.getText().trim();
		String strDescuento = this.txtDescuento.getText().trim();

		// Validar (cantidad, precioVenta, descuento)
		if (codigoProducto.length() == 0) { // Valida que código de producto no
											// esté vacío
			Mensaje.mensajeError(this, "No ha seleccionado ningún producto");
			this.btnBuscar.requestFocus();
			return;
		}

		if (!strCantidad.matches("\\d+")) {
			Mensaje.mensajeError(this, "Error de formato de número");
			this.spinnerCantidad.requestFocus();
			return;
		}

		if (!strPrecioVenta.matches("\\d+|\\d+.\\d{1,2}")) {
			Mensaje.mensajeError(this, "Error de formato de precio (#.##)");
			txtPrecioVenta.requestFocus();
			return;
		}

		if (!strDescuento.matches("\\d+|\\d+.\\d{1,2}")) {
			Mensaje.mensajeError(this, "Error de formato de descuento (#.##)");
			this.txtDescuento.requestFocus();
			return;
		}

		int cantidad = Integer.parseInt(strCantidad);
		double precioVenta = Double.parseDouble(strPrecioVenta);
		double descuento = Double.parseDouble(strDescuento);
		double precioFinal = precioVenta - descuento;
		double total = precioFinal * cantidad;

		// Se verifica si el stock de producto es >= que la cantidad a vender
		ProductoModel modeloProducto = new ProductoModel();
		Producto producto = modeloProducto.getProducto(codigoProducto);
		int stockActual = producto.getStockActual();
		if (cantidad > stockActual) {
			Mensaje.mensajeError(this, "No hay stock suficiente para la venta. Solo puede vender como máximo: "
					+ stockActual + " productos");
			this.spinnerCantidad.requestFocus();
			return;
		}

		// Se busca el código del producto del listado para actualizar sus
		// valores
		double anteriorTotal = 0;
		for (int i = 0; i < this.modeloTabla.getRowCount(); ++i) {
			if (codigoProducto.equalsIgnoreCase(this.modeloTabla.getValueAt(i, 0).toString())) {
				// Se actualiza sus valores
				this.modeloTabla.setValueAt(cantidad, i, 2);
				this.modeloTabla.setValueAt(Redondear.redondear(precioVenta, 2), i, 3);
				this.modeloTabla.setValueAt(Redondear.redondear(descuento, 2), i, 4);
				this.modeloTabla.setValueAt(Redondear.redondear(precioFinal, 2), i, 5);

				// Se obtiene el anterior total (importe)
				anteriorTotal = Double.parseDouble(this.modeloTabla.getValueAt(i, 6).toString());

				// Se actualiza el nuevo importe
				this.modeloTabla.setValueAt(Redondear.redondear(total, 2), i, 6);
				break;
			}
		}

		this.subtotal = this.subtotal + total - anteriorTotal;
		this.impuesto = this.igv * this.subtotal;
		this.totalAPagar = this.subtotal + this.impuesto;
		this.actualizarTotales();

		this.limpiarDetalle();
	}

	protected void actionPerformedBtnLimpiar(ActionEvent e) {
		this.limpiarDetalle();
	}

	protected void actionPerformedBtnRegistrarVenta(ActionEvent e) {
		// Entrada de datos de la cabecera
		String idVenta = this.txtIdVenta.getText().trim();
		String idEmpleado = txtCodigoEmpleado.getText().trim();
		String idCliente = txtCodigoCliente.getText().trim();
		String fechaVenta = FechaUtil.dateToString(this.jdFechaVenta.getDate());
		String tipoComprobante = this.cboTipoComprobante.getSelectedItem().toString();
		String serieComprobante = this.txtSerieComprobante.getText().trim();
		String numeroComprobante = this.txtNumeroComprobante.getText().trim();
		String igv = this.txtIgv.getText().trim();

		// Validación
		if (idCliente.length() == 0) {
			Mensaje.mensajeError(this, "No ha seleccionado ningún cliente");
			this.btnBuscarCliente.requestFocus();
			return;
		}

		if (!igv.matches("\\d+|\\d+.\\d{1,2}")) {
			Mensaje.mensajeError(this, "Error de formato de igv (#.##)");
			this.txtIgv.requestFocus();
			return;
		}

		if (fechaVenta == null) {
			Mensaje.mensajeError(this, "Debe introducir una fecha de venta");
			this.jdFechaVenta.setDate(FechaUtil.today());
			this.jdFechaVenta.requestFocusInWindow();
			return;
		}

		// Debe haber al menos un producto en la tabla
		if (this.modeloTabla.getRowCount() <= 0) {
			Mensaje.mensajeError(this, "Debe seleccionar al menos un producto para realizar la venta");
			this.btnBuscar.requestFocus();
			return;
		}

		// Se instancia el objeto Venta
		Venta venta = new Venta();
		venta.setIdVenta(idVenta);
		venta.setFechaVenta(fechaVenta);
		venta.setTipoComprobante(tipoComprobante);
		venta.setSerieComprobante(serieComprobante);
		venta.setNumeroComprobante(numeroComprobante);
		venta.setIgv(Double.parseDouble(igv));
		venta.setEmpleado(modeloEmpleado.getEmpleado(idEmpleado));
		venta.setCliente(modeloCliente.getCliente(idCliente));

		// Se crea el detalle
		DetalleVenta detalleVenta = null;
		List<DetalleVenta> detalleVentas = new ArrayList<DetalleVenta>();
		for (int i = 0; i < this.modeloTabla.getRowCount(); ++i) {
			detalleVenta = new DetalleVenta();

			detalleVenta.setIdVenta(idVenta);
			detalleVenta.setIdProducto(this.modeloTabla.getValueAt(i, 0).toString());
			detalleVenta.setCantidad(Integer.parseInt(this.modeloTabla.getValueAt(i, 2).toString()));
			detalleVenta.setPrecioVenta(Double.parseDouble(this.modeloTabla.getValueAt(i, 3).toString()));
			detalleVenta.setDescuento(Double.parseDouble(this.modeloTabla.getValueAt(i, 4).toString()));

			detalleVentas.add(detalleVenta);
		}

		// Se inserta la Venta junto con su Detalle
		boolean inserta = modeloVenta.insertarVenta(venta, detalleVentas);

		if (inserta) {
			Mensaje.mensajeInformacion(this, "Venta exitosa");
		} else {
			Mensaje.mensajeError(this, "Ocurrió un error al registrar la venta");
		}

		this.limpiar();
	}

	protected void actionPerformedBtnCancelar(ActionEvent e) {
		this.dispose();
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
	}

	public void mouseReleased(MouseEvent e) {
	}

	protected void mouseClickedTable(MouseEvent e) {
		int filaSeleccionada = this.table.getSelectedRow();

		if (filaSeleccionada != -1) {
			this.limpiarDetalle();
			// Obtiene el código del producto de la fila seleccionada
			String codigoProducto = this.modeloTabla.getValueAt(filaSeleccionada, 0).toString();
			String descripcionProducto = this.modeloTabla.getValueAt(filaSeleccionada, 1).toString();
			String cantidad = this.modeloTabla.getValueAt(filaSeleccionada, 2).toString();
			String precioVenta = this.modeloTabla.getValueAt(filaSeleccionada, 3).toString();
			String descuento = this.modeloTabla.getValueAt(filaSeleccionada, 4).toString();

			txtCodigoProducto.setText(codigoProducto);
			txtDescripcionProducto.setText(descripcionProducto);
			this.spinnerCantidad.setValue(Integer.parseInt(cantidad));
			txtPrecioVenta.setText(precioVenta);
			this.txtDescuento.setText(descuento);
		}
	}

	protected void actionPerformedBtnBuscarCliente(ActionEvent e) {
		FrmBuscarCliente frmBuscarCliente = new FrmBuscarCliente();
		Sistema.desktopPane.add(frmBuscarCliente);
		Sistema.centrar(frmBuscarCliente);
		frmBuscarCliente.setVisible(true);
	}

	protected void actionPerformedBtnLimpiar_1(ActionEvent e) {
		this.limpiar();
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == this.cboTipoComprobante) {
			itemStateChangedCboTipoComprobante(e);
		}
	}

	protected void itemStateChangedCboTipoComprobante(ItemEvent e) {
		if (this.cboTipoComprobante.getSelectedItem().toString() == "FACTURA") {
			this.txtNumeroComprobante.setText(this.modeloVenta.generarNumeroFactura());
			this.lblTipoComprobante.setText("FACTURA");
		} else {
			this.txtNumeroComprobante.setText(this.modeloVenta.generarNumeroBoleta());
			this.lblTipoComprobante.setText("BOLETA");
		}
	}
}
