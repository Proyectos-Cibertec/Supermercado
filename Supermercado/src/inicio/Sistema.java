package inicio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import entidad.Empleado;
import gui.FrmAcercaDe;
import gui.FrmCaja;
import gui.FrmCargo;
import gui.FrmCategoria;
import gui.FrmCliente;
import gui.FrmCompra;
import gui.FrmConsultaCliente;
import gui.FrmConsultaProducto;
import gui.FrmConsultaProveedor;
import gui.FrmConsultaVentas;
import gui.FrmDistrito;
import gui.FrmEmpleado;
import gui.FrmProducto;
import gui.FrmProductoAvanzado;
import gui.FrmProveedor;
import gui.FrmSexo;
import gui.FrmVenta;
import util.ImagenFondo;
import util.RelojFechaHora;

public class Sistema extends JFrame implements ActionListener, ComponentListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnArchivo;
	private JMenu mnVentas;
	private JMenu mnConsultas;
	private JMenu mnMantenimientos;
	private JMenu mnAyuda;
	private JMenuItem mntmSalir;
	private JMenuItem mntmCerrarSesin;
	private JMenuItem mntmAbrirSesin;
	private JMenu mnAlmacn;
	private JMenuItem mntmProductoNormal;
	private JMenuItem mntmCategora;
	private JMenu mnCompras;
	private JMenuItem mntmProveedor;
	private JMenuItem mntmIngresos;
	private JMenu mnAnulaciones;
	private JMenuItem mntmAcercaDe;
	public static JDesktopPane desktopPane;
	private JMenuItem mntmManualDelUsuario;
	private JSeparator separator;
	private JMenuItem mntmSexo;
	private JMenuItem mntmDistritos;
	private JMenuItem mntmCargos;
	private JMenuItem mntmCaja;
	private JMenuItem mntmProductoAvanzado;
	private JMenu mnProductos;
	private JToolBar toolBar;
	private JButton btnProductoNormal;
	private JButton btnProductoDetallado;
	private JButton btnSexos;
	private JButton btnDistritos;
	private JButton btnCargos;
	private JButton btnCaja;
	private JButton btnCategorias;
	private JLabel lblSeparador1;
	private JLabel lblSeparador2;
	private JMenu mnConfiguraciones;

	/**
	 * Launch the application.
	 */
	private static Sistema frame;
	private JMenuItem mntmClientes;
	private JMenuItem mntmEmpleados;
	private JButton btnProveedores;
	private JLabel label;
	private JButton btnEmpleados;
	private JLabel label_1;
	private JButton btnClientes;
	private JLabel label_2;
	private JButton btnAcercaDe;
	private JButton btnManualDelUsuario;
	private Empleado empleadoActual = null;
	private JLabel lblcod;
	private JLabel lblEmp;
	private JLabel lblCodigo;
	private JLabel lblEmpleado;
	private JLabel lblCar;
	private JLabel lblCargo;
	private JMenuItem mntmRealizarVenta;
	private JPanel panel;
	private JLabel lblLogoGrande;
	private JPanel panelBarraEstado;
	private JMenuBar barraEstado;
	JMenuItem mntmTipoUsuario;
	private JMenuItem mntmDerechosReservados;
	private JMenuItem mntmFechaHora;
	private JMenuItem mntmReporteProductos;
	private JMenuItem mntmReporteProveedores;
	private JMenuItem mntmReporteClientes;
	private JMenuItem mntmReporteVentas;
	private JButton btnRealizarVenta;
	private JButton btnRealizarCompra;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			// UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
			// UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
			// UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			// UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");

		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				/*
				 * try { Principal frame = new Principal();
				 * frame.setVisible(true); } catch (Exception e) {
				 * e.printStackTrace(); }
				 */
				try {
					frame = new Sistema();
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
	public Sistema() {
		addComponentListener(this);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sistema.class.getResource("/iconos/iconoPrincipal.png")));
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle(".:: SUPERMERCADO ::.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 491);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnArchivo = new JMenu("Archivo");
		this.mnArchivo.setFont(new Font("Segoe UI", Font.BOLD, 12));
		this.mnArchivo.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/archivo.png")));
		menuBar.add(mnArchivo);

		mntmAbrirSesin = new JMenuItem("Iniciar sesi\u00F3n");
		this.mntmAbrirSesin.setEnabled(false);
		this.mntmAbrirSesin.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/iniciarSesion.png")));
		mntmAbrirSesin.addActionListener(this);
		mnArchivo.add(mntmAbrirSesin);

		mntmCerrarSesin = new JMenuItem("Cerrar sesi\u00F3n");
		this.mntmCerrarSesin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		this.mntmCerrarSesin.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/cerrarSesion.png")));
		mntmCerrarSesin.addActionListener(this);
		mnArchivo.add(mntmCerrarSesin);

		mntmSalir = new JMenuItem("Salir del sistema");
		this.mntmSalir.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/salir.png")));
		mntmSalir.addActionListener(this);
		mnArchivo.add(mntmSalir);

		mnAlmacn = new JMenu("Almac\u00E9n");
		this.mnAlmacn.setFont(new Font("Segoe UI", Font.BOLD, 12));
		this.mnAlmacn.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/almacen.png")));
		menuBar.add(mnAlmacn);

		this.mnProductos = new JMenu("Productos");
		this.mnProductos.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/productos.png")));
		this.mnAlmacn.add(this.mnProductos);

		mntmProductoNormal = new JMenuItem("Normal");
		this.mntmProductoNormal.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		this.mntmProductoNormal.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/productoNormal.png")));
		this.mnProductos.add(this.mntmProductoNormal);

		this.mntmProductoAvanzado = new JMenuItem("Detallado");
		this.mntmProductoAvanzado.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		this.mnProductos.add(this.mntmProductoAvanzado);
		this.mntmProductoAvanzado.addActionListener(this);
		this.mntmProductoAvanzado.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/productoDetallado.png")));
		mntmProductoNormal.addActionListener(this);

		mnCompras = new JMenu("Compras");
		this.mnCompras.setFont(new Font("Segoe UI", Font.BOLD, 12));
		this.mnCompras.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/compras2.png")));
		menuBar.add(mnCompras);

		mntmProveedor = new JMenuItem("Proveedor");
		this.mntmProveedor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		this.mntmProveedor.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/proveedor.png")));
		mntmProveedor.addActionListener(this);
		mnCompras.add(mntmProveedor);

		mntmIngresos = new JMenuItem("Realizar Compra");
		this.mntmIngresos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));
		this.mntmIngresos.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/compras.png")));
		mntmIngresos.addActionListener(this);
		mnCompras.add(mntmIngresos);

		mnVentas = new JMenu("Ventas");
		this.mnVentas.setFont(new Font("Segoe UI", Font.BOLD, 12));
		this.mnVentas.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/venta.png")));
		menuBar.add(mnVentas);

		this.mntmClientes = new JMenuItem("Clientes");
		this.mntmClientes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		this.mntmClientes.addActionListener(this);
		this.mntmClientes.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/cliente.png")));
		this.mnVentas.add(this.mntmClientes);

		this.mntmRealizarVenta = new JMenuItem("Realizar Venta");
		this.mntmRealizarVenta.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
		this.mntmRealizarVenta.addActionListener(this);
		this.mntmRealizarVenta.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/venta.png")));
		this.mnVentas.add(this.mntmRealizarVenta);

		mnMantenimientos = new JMenu("Mantenimientos");
		this.mnMantenimientos.setFont(new Font("Segoe UI", Font.BOLD, 12));
		this.mnMantenimientos.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/mantenimiento.png")));
		menuBar.add(mnMantenimientos);

		this.mntmSexo = new JMenuItem("Sexo");
		this.mntmSexo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		this.mntmSexo.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/sexo.png")));
		this.mntmSexo.addActionListener(this);
		this.mnMantenimientos.add(this.mntmSexo);

		this.mntmDistritos = new JMenuItem("Distritos");
		this.mntmDistritos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		this.mntmDistritos.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/distritos.png")));
		this.mntmDistritos.addActionListener(this);
		this.mnMantenimientos.add(this.mntmDistritos);

		this.mntmCargos = new JMenuItem("Cargos");
		this.mntmCargos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		this.mntmCargos.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/cargo2.png")));
		this.mntmCargos.addActionListener(this);
		this.mnMantenimientos.add(this.mntmCargos);

		this.mntmCaja = new JMenuItem("Caja");
		this.mntmCaja.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/caja.png")));
		this.mntmCaja.addActionListener(this);
		this.mnMantenimientos.add(this.mntmCaja);

		mntmCategora = new JMenuItem("Categor\u00EDas");
		this.mntmCategora.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		this.mnMantenimientos.add(this.mntmCategora);
		this.mntmCategora.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/categorias.png")));

		this.mntmEmpleados = new JMenuItem("Empleados");
		this.mntmEmpleados.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		this.mntmEmpleados.addActionListener(this);
		this.mntmEmpleados.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/empleados2.png")));
		this.mnMantenimientos.add(this.mntmEmpleados);
		mntmCategora.addActionListener(this);

		mnAnulaciones = new JMenu("Anulaciones");
		this.mnAnulaciones.setEnabled(false);
		this.mnAnulaciones.setFont(new Font("Segoe UI", Font.BOLD, 12));
		this.mnAnulaciones.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/anulaciones.png")));
		menuBar.add(mnAnulaciones);

		mnConsultas = new JMenu("Consultas");
		this.mnConsultas.setFont(new Font("Segoe UI", Font.BOLD, 12));
		this.mnConsultas.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/consultas.png")));
		menuBar.add(mnConsultas);
		
		this.mntmReporteProductos = new JMenuItem("Reporte Productos");
		this.mntmReporteProductos.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/producto2.png")));
		this.mntmReporteProductos.addActionListener(this);
		this.mnConsultas.add(this.mntmReporteProductos);
		
		this.mntmReporteProveedores = new JMenuItem("Reporte Proveedores");
		this.mntmReporteProveedores.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/proveedor2.png")));
		this.mntmReporteProveedores.addActionListener(this);
		this.mnConsultas.add(this.mntmReporteProveedores);
		
		this.mntmReporteClientes = new JMenuItem("Reporte Clientes");
		this.mntmReporteClientes.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/cliente2.png")));
		this.mntmReporteClientes.addActionListener(this);
		this.mnConsultas.add(this.mntmReporteClientes);
		
		this.mntmReporteVentas = new JMenuItem("Reporte Ventas");
		this.mntmReporteVentas.addActionListener(this);
		this.mntmReporteVentas.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/consultaVenta.png")));
		this.mnConsultas.add(this.mntmReporteVentas);

		this.mnConfiguraciones = new JMenu("Configuraciones");
		this.mnConfiguraciones.setEnabled(false);
		this.mnConfiguraciones.setFont(new Font("Segoe UI", Font.BOLD, 12));
		this.mnConfiguraciones.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/configuraciones.png")));
		this.menuBar.add(this.mnConfiguraciones);

		mnAyuda = new JMenu("Ayuda");
		this.mnAyuda.setFont(new Font("Segoe UI", Font.BOLD, 12));
		this.mnAyuda.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/ayuda.png")));
		menuBar.add(mnAyuda);

		mntmAcercaDe = new JMenuItem("Acerca de...");
		this.mntmAcercaDe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		this.mntmAcercaDe.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/acercaDe.png")));
		mntmAcercaDe.addActionListener(this);

		this.mntmManualDelUsuario = new JMenuItem("Manual del Usuario");
		this.mntmManualDelUsuario
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		this.mntmManualDelUsuario.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/manual.png")));
		this.mntmManualDelUsuario.addActionListener(this);
		this.mnAyuda.add(this.mntmManualDelUsuario);

		this.separator = new JSeparator();
		this.mnAyuda.add(this.separator);
		mnAyuda.add(mntmAcercaDe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);

		desktopPane.setBorder(new ImagenFondo());

		this.panel = new JPanel();
		this.panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		this.panel.setBackground(Color.DARK_GRAY);
		this.panel.setBounds(26, 28, 419, 101);
		desktopPane.add(this.panel);
		this.panel.setLayout(null);

		this.lblCar = new JLabel("CARGO:");
		this.lblCar.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblCar.setForeground(Color.GREEN);
		this.lblCar.setBounds(21, 61, 103, 24);
		this.panel.add(this.lblCar);
		this.lblCar.setFont(new Font("Tahoma", Font.BOLD, 14));

		this.lblEmp = new JLabel("EMPLEADO:");
		this.lblEmp.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblEmp.setForeground(Color.GREEN);
		this.lblEmp.setBounds(21, 36, 103, 24);
		this.panel.add(this.lblEmp);
		this.lblEmp.setFont(new Font("Tahoma", Font.BOLD, 14));

		this.lblcod = new JLabel("C\u00D3DIGO:");
		this.lblcod.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblcod.setForeground(Color.GREEN);
		this.lblcod.setBounds(21, 11, 103, 24);
		this.panel.add(this.lblcod);
		this.lblcod.setFont(new Font("Tahoma", Font.BOLD, 14));

		this.lblCodigo = new JLabel("c\u00F3digo");
		this.lblCodigo.setForeground(Color.WHITE);
		this.lblCodigo.setBounds(144, 11, 239, 24);
		this.panel.add(this.lblCodigo);
		this.lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));

		this.lblEmpleado = new JLabel("empleado");
		this.lblEmpleado.setForeground(Color.WHITE);
		this.lblEmpleado.setBounds(144, 36, 239, 24);
		this.panel.add(this.lblEmpleado);
		this.lblEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));

		this.lblCargo = new JLabel("cargo");
		this.lblCargo.setForeground(Color.WHITE);
		this.lblCargo.setBounds(144, 61, 239, 24);
		this.panel.add(this.lblCargo);
		this.lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 14));

		this.lblLogoGrande = new JLabel("");
		this.lblLogoGrande.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/logoSistema.png")));
		this.lblLogoGrande.setBounds(524, 38, 526, 446);
		desktopPane.add(this.lblLogoGrande);
		centrarLabel(this.lblLogoGrande);

		this.toolBar = new JToolBar();
		this.contentPane.add(this.toolBar, BorderLayout.NORTH);

		this.btnSexos = new JButton("");
		this.btnSexos.addActionListener(this);
		this.btnSexos.setToolTipText("Sexo");
		this.btnSexos.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/sexo.png")));
		this.toolBar.add(this.btnSexos);

		this.btnDistritos = new JButton("");
		this.btnDistritos.setToolTipText("Distritos");
		this.btnDistritos.addActionListener(this);
		this.btnDistritos.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/distritos.png")));
		this.toolBar.add(this.btnDistritos);

		this.btnCargos = new JButton("");
		this.btnCargos.setToolTipText("Cargos");
		this.btnCargos.addActionListener(this);
		this.btnCargos.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/cargo.png")));
		this.toolBar.add(this.btnCargos);

		this.btnCaja = new JButton("");
		this.btnCaja.setToolTipText("Caja");
		this.btnCaja.addActionListener(this);
		this.btnCaja.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/caja.png")));
		this.toolBar.add(this.btnCaja);

		this.btnCategorias = new JButton("");
		this.btnCategorias.setToolTipText("Categor\u00EDas");
		this.btnCategorias.addActionListener(this);
		this.btnCategorias.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/categorias.png")));
		this.toolBar.add(this.btnCategorias);

		this.lblSeparador1 = new JLabel("");
		this.lblSeparador1.setEnabled(false);
		this.lblSeparador1.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/separador.png")));
		this.toolBar.add(this.lblSeparador1);

		this.btnProductoNormal = new JButton("");
		this.btnProductoNormal.setToolTipText("Producto");
		this.btnProductoNormal.addActionListener(this);
		this.btnProductoNormal.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/productoNormal.png")));
		this.toolBar.add(this.btnProductoNormal);

		this.btnProductoDetallado = new JButton("");
		this.btnProductoDetallado.setToolTipText("Producto detallado");
		this.btnProductoDetallado.addActionListener(this);
		this.btnProductoDetallado.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/productoDetallado.png")));
		this.toolBar.add(this.btnProductoDetallado);

		this.lblSeparador2 = new JLabel("");
		this.lblSeparador2.setEnabled(false);
		this.lblSeparador2.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/separador.png")));
		this.toolBar.add(this.lblSeparador2);

		this.btnProveedores = new JButton("");
		this.btnProveedores.setToolTipText("Proveedores");
		this.btnProveedores.addActionListener(this);
		
		this.btnRealizarCompra = new JButton("");
		this.btnRealizarCompra.addActionListener(this);
		this.btnRealizarCompra.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/compras.png")));
		this.btnRealizarCompra.setToolTipText("Realizar Compra");
		this.toolBar.add(this.btnRealizarCompra);
		this.btnProveedores.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/proveedor.png")));
		this.toolBar.add(this.btnProveedores);

		this.label = new JLabel("");
		this.label.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/separador.png")));
		this.label.setEnabled(false);
		this.toolBar.add(this.label);

		this.btnEmpleados = new JButton("");
		this.btnEmpleados.setToolTipText("Empleados");
		this.btnEmpleados.addActionListener(this);
		this.btnEmpleados.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/empleados.png")));
		this.toolBar.add(this.btnEmpleados);

		this.label_1 = new JLabel("");
		this.label_1.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/separador.png")));
		this.label_1.setEnabled(false);
		this.toolBar.add(this.label_1);

		this.btnClientes = new JButton("");
		this.btnClientes.setToolTipText("Clientes");
		this.btnClientes.addActionListener(this);
		this.btnClientes.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/cliente.png")));
		this.toolBar.add(this.btnClientes);
		
		this.btnRealizarVenta = new JButton("");
		this.btnRealizarVenta.addActionListener(this);
		this.btnRealizarVenta.setToolTipText("Realizar Venta");
		this.btnRealizarVenta.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/venta.png")));
		this.toolBar.add(this.btnRealizarVenta);

		this.label_2 = new JLabel("");
		this.label_2.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/separador.png")));
		this.label_2.setEnabled(false);
		this.toolBar.add(this.label_2);

		this.btnManualDelUsuario = new JButton("");
		this.btnManualDelUsuario.setToolTipText("Manual del Usuario");
		this.btnManualDelUsuario.addActionListener(this);
		this.btnManualDelUsuario.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/manual.png")));
		this.toolBar.add(this.btnManualDelUsuario);

		this.btnAcercaDe = new JButton("");
		this.btnAcercaDe.setToolTipText("Acerca de ..");
		this.btnAcercaDe.addActionListener(this);
		this.btnAcercaDe.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/acercaDe.png")));
		this.toolBar.add(this.btnAcercaDe);
		
		this.panelBarraEstado = new JPanel();
		this.contentPane.add(this.panelBarraEstado, BorderLayout.SOUTH);
		this.panelBarraEstado.setLayout(new BorderLayout(0, 0));
		
		this.barraEstado = new JMenuBar();
		this.panelBarraEstado.add(barraEstado);
		
		this.mntmTipoUsuario = new JMenuItem("Tipo de usuario:");
		this.mntmTipoUsuario.setFont(new Font("Segoe UI", Font.BOLD, 13));
		this.mntmTipoUsuario.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/iconoUsuarioBarraEstado.png")));
		this.barraEstado.add(this.mntmTipoUsuario);
		
		this.mntmDerechosReservados = new JMenuItem("Derechos Reservados 2017 - Supermercado v1.0");
		this.mntmDerechosReservados.setFont(new Font("Segoe UI", Font.BOLD, 13));
		this.mntmDerechosReservados.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/iconoCopyright.png")));
		this.barraEstado.add(this.mntmDerechosReservados);
		
		this.mntmFechaHora = new JMenuItem("Fecha y hora:");
		this.mntmFechaHora.setFont(new Font("Segoe UI", Font.BOLD, 13));
		this.mntmFechaHora.setIcon(new ImageIcon(Sistema.class.getResource("/iconos/iconoFechaHora.png")));
		this.barraEstado.add(this.mntmFechaHora);
		
		iniciaReloj(mntmFechaHora);
	}
	
	public void iniciaReloj(JMenuItem mntmFechaHora) {
		RelojFechaHora fechaHora = new RelojFechaHora(mntmFechaHora);
		fechaHora.actualiza();
	}

	public void setEmpleadoActual(Empleado empleado) {
		this.empleadoActual = empleado;
		this.lblCodigo.setText(this.empleadoActual.getIdEmpleado());
		this.lblEmpleado.setText(this.empleadoActual.getApellidos() + " " + empleado.getNombres());
		this.lblCargo.setText(this.empleadoActual.getCargo().getDescripcion());
		this.mntmTipoUsuario.setText("Tipo de Usuario: " + this.empleadoActual.getCargo().getDescripcion());
		
		// Se establece los permisos (Se puede mejorar más haciéndolo con tablas de permisos en la base de datos)
		switch (this.empleadoActual.getCargo().getDescripcion()) {
			case "ADMINISTRADOR":case "GERENTE":
				// Todos los permisos
				break;
				
			case "ALMACENERO":
				
				this.mnVentas.setEnabled(false);
				this.mnMantenimientos.setEnabled(false);
				this.mnConsultas.setEnabled(false);
				this.btnSexos.setEnabled(false);
				this.btnDistritos.setEnabled(false);
				this.btnCargos.setEnabled(false);
				this.btnCaja.setEnabled(false);
				this.btnCategorias.setEnabled(false);
				this.btnEmpleados.setEnabled(false);
				this.btnClientes.setEnabled(false);
				this.btnRealizarVenta.setEnabled(false);
				break;
				
			case "VENDEDOR":
				this.mnAlmacn.setEnabled(false);
				this.mnCompras.setEnabled(false);
				this.mnMantenimientos.setEnabled(false);
				this.mnConsultas.setEnabled(false);
				
				this.btnSexos.setEnabled(false);
				this.btnDistritos.setEnabled(false);
				this.btnCargos.setEnabled(false);
				this.btnCaja.setEnabled(false);
				this.btnCategorias.setEnabled(false);
				this.btnProductoDetallado.setEnabled(false);
				this.btnProductoNormal.setEnabled(false);
				this.btnProveedores.setEnabled(false);
				this.btnRealizarCompra.setEnabled(false);
				this.btnEmpleados.setEnabled(false);
				break;
				
			default:
				break;
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnRealizarVenta) {
			actionPerformedBtnRealizarVenta(e);
		}
		if (e.getSource() == this.btnRealizarCompra) {
			actionPerformedBtnRealizarCompra(e);
		}
		if (e.getSource() == this.mntmReporteVentas) {
			actionPerformedMntmReporteVentas(e);
		}
		if (e.getSource() == this.mntmReporteClientes) {
			actionPerformedMntmReporteClientes(e);
		}
		if (e.getSource() == this.mntmReporteProveedores) {
			actionPerformedMntmReporteProveedores(e);
		}
		if (e.getSource() == this.mntmReporteProductos) {
			actionPerformedMntmReporteProductos(e);
		}
		if (e.getSource() == this.mntmRealizarVenta) {
			actionPerformedMntmRealizarVenta(e);
		}
		if (e.getSource() == this.btnAcercaDe) {
			actionPerformedBtnAcercaDe(e);
		}
		if (e.getSource() == this.btnManualDelUsuario) {
			actionPerformedBtnManualDelUsuario(e);
		}
		if (e.getSource() == this.btnClientes) {
			actionPerformedBtnClientes(e);
		}
		if (e.getSource() == this.btnEmpleados) {
			actionPerformedBtnEmpleados(e);
		}
		if (e.getSource() == this.btnProveedores) {
			actionPerformedBtnProveedores(e);
		}
		if (e.getSource() == this.btnProductoDetallado) {
			actionPerformedBtnProductoDetallado(e);
		}
		if (e.getSource() == this.btnProductoNormal) {
			actionPerformedBtnProductoNormal(e);
		}
		if (e.getSource() == this.btnCategorias) {
			actionPerformedBtnCategorias(e);
		}
		if (e.getSource() == this.btnCaja) {
			actionPerformedBtnCaja(e);
		}
		if (e.getSource() == this.btnCargos) {
			actionPerformedBtnCargos(e);
		}
		if (e.getSource() == this.btnDistritos) {
			actionPerformedBtnDistritos(e);
		}
		if (e.getSource() == this.btnSexos) {
			actionPerformedBtnSexos(e);
		}
		if (e.getSource() == this.mntmEmpleados) {
			actionPerformedMntmEmpleados(e);
		}
		if (e.getSource() == this.mntmClientes) {
			actionPerformedMntmClientes(e);
		}
		if (e.getSource() == this.mntmProductoAvanzado) {
			actionPerformedMntmProductos(e);
		}
		if (e.getSource() == this.mntmCaja) {
			actionPerformedMntmCaja(e);
		}
		if (e.getSource() == this.mntmCargos) {
			actionPerformedMntmCargos(e);
		}
		if (e.getSource() == this.mntmDistritos) {
			actionPerformedMntmDistrito(e);
		}
		if (e.getSource() == this.mntmSexo) {
			actionPerformedMntmSexo(e);
		}
		if (e.getSource() == this.mntmManualDelUsuario) {
			actionPerformedMntmManualDelUsuario(e);
		}
		if (e.getSource() == mntmAcercaDe) {
			actionPerformedMntmAcercaDe(e);
		}
		if (e.getSource() == mntmIngresos) {
			actionPerformedMntmIngresos(e);
		}
		if (e.getSource() == mntmProveedor) {
			actionPerformedMntmProveedor(e);
		}
		if (e.getSource() == mntmCategora) {
			actionPerformedMntmCategora(e);
		}
		if (e.getSource() == mntmProductoNormal) {
			actionPerformedMntmProducto(e);
		}
		if (e.getSource() == mntmSalir) {
			actionPerformedMntmSalir(e);
		}
		if (e.getSource() == mntmCerrarSesin) {
			actionPerformedMntmCerrarSesin(e);
		}
		if (e.getSource() == mntmAbrirSesin) {
			actionPerformedMntmAbrirSesin(e);
		}
	}

	protected void actionPerformedMntmAbrirSesin(ActionEvent e) {
	}

	protected void actionPerformedMntmCerrarSesin(ActionEvent e) {
		Login login = new Login();
		this.dispose();
		login.setVisible(true);
	}

	protected void actionPerformedMntmSalir(ActionEvent e) {
		int respuesta = JOptionPane.showConfirmDialog(this, "¿Seguro que desea salir del Sistema?", "Confirmación",
				JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	protected void actionPerformedMntmProducto(ActionEvent e) {
		FrmProducto crudProducto = new FrmProducto();
		desktopPane.add(crudProducto);
		Sistema.centrar(crudProducto);
		crudProducto.setVisible(true);
	}

	protected void actionPerformedMntmCategora(ActionEvent e) {
		FrmCategoria crudCategoria = new FrmCategoria();
		desktopPane.add(crudCategoria);
		Sistema.centrar(crudCategoria);
		crudCategoria.setVisible(true);
	}

	protected void actionPerformedMntmProveedor(ActionEvent e) {
		FrmProveedor crudProveedor = new FrmProveedor();
		desktopPane.add(crudProveedor);
		Sistema.centrar(crudProveedor);
		crudProveedor.setVisible(true);
	}

	protected void actionPerformedMntmIngresos(ActionEvent e) {
		FrmCompra frmCompra = new FrmCompra();
		FrmCompra.txtCodigoEmpleado.setText(this.empleadoActual.getIdEmpleado());
		FrmCompra.txtNombreEmpleado
				.setText(String.format("%s %s", this.empleadoActual.getNombres(), this.empleadoActual.getApellidos()));
		desktopPane.add(frmCompra);
		Sistema.centrar(frmCompra);
		frmCompra.setVisible(true);
	}

	protected void actionPerformedMntmAcercaDe(ActionEvent e) {
		FrmAcercaDe dialogoAcercaDe = new FrmAcercaDe();
		dialogoAcercaDe.setLocationRelativeTo(this); // Centrar
		dialogoAcercaDe.setVisible(true);
	}

	// Centra un JInternalFrame
	public static void centrar(JInternalFrame frm) {
		// Dimensiones de la pantalla
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		// Dimensiones del formulario
		Dimension ventana = frm.getSize();

		int posX = (int) (pantalla.getWidth() - ventana.getWidth()) / 2;
		frm.setLocation(posX, 20);
	}
	
	// Centra un label
	public static void centrarLabel(JLabel label) {
		// Dimensiones de la pantalla
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
				
		int posX = (int) (pantalla.getWidth() - label.getWidth()) / 2;
		int posY = (int) (pantalla.getHeight() - label.getHeight()) / 2 - 141;
		label.setLocation(posX, posY);
	}

	protected void actionPerformedMntmManualDelUsuario(ActionEvent e) {
		try {
			Desktop.getDesktop()
					.browse(new URL("https://drive.google.com/open?id=0B5PJu2VRH1WzVlpxYzRVMmgyeGM").toURI());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	protected void actionPerformedMntmSexo(ActionEvent e) {
		FrmSexo crudSexo = new FrmSexo();
		desktopPane.add(crudSexo);
		Sistema.centrar(crudSexo);
		crudSexo.setVisible(true);
	}

	protected void actionPerformedMntmDistrito(ActionEvent e) {
		FrmDistrito crudDistrito = new FrmDistrito();
		desktopPane.add(crudDistrito);
		Sistema.centrar(crudDistrito);
		crudDistrito.setVisible(true);
	}

	protected void actionPerformedMntmCargos(ActionEvent e) {
		FrmCargo crudCargo = new FrmCargo();
		desktopPane.add(crudCargo);
		Sistema.centrar(crudCargo);
		crudCargo.setVisible(true);
	}

	protected void actionPerformedMntmCaja(ActionEvent e) {
		FrmCaja crudCaja = new FrmCaja();
		desktopPane.add(crudCaja);
		Sistema.centrar(crudCaja);
		crudCaja.setVisible(true);
	}

	protected void actionPerformedMntmProductos(ActionEvent e) {
		FrmProductoAvanzado crudProducto = new FrmProductoAvanzado();
		desktopPane.add(crudProducto);
		Sistema.centrar(crudProducto);
		crudProducto.setVisible(true);
	}

	protected void actionPerformedMntmClientes(ActionEvent e) {
		FrmCliente crudCliente = new FrmCliente();
		desktopPane.add(crudCliente);
		Sistema.centrar(crudCliente);
		crudCliente.setVisible(true);
	}

	protected void actionPerformedMntmEmpleados(ActionEvent e) {
		FrmEmpleado crudEmpleado = new FrmEmpleado();
		desktopPane.add(crudEmpleado);
		Sistema.centrar(crudEmpleado);
		crudEmpleado.setVisible(true);
	}

	protected void actionPerformedMntmRealizarVenta(ActionEvent e) {
		FrmVenta frmVenta = new FrmVenta();
		FrmVenta.txtCodigoEmpleado.setText(this.empleadoActual.getIdEmpleado());
		FrmVenta.txtNombreEmpleado
				.setText(String.format("%s %s", this.empleadoActual.getNombres(), this.empleadoActual.getApellidos()));
		desktopPane.add(frmVenta);
		Sistema.centrar(frmVenta);
		frmVenta.setVisible(true);
	}

	protected void actionPerformedBtnSexos(ActionEvent e) {
		actionPerformedMntmSexo(e);
	}

	protected void actionPerformedBtnDistritos(ActionEvent e) {
		actionPerformedMntmDistrito(e);
	}

	protected void actionPerformedBtnCargos(ActionEvent e) {
		actionPerformedMntmCargos(e);
	}

	protected void actionPerformedBtnCaja(ActionEvent e) {
		actionPerformedMntmCaja(e);
	}

	protected void actionPerformedBtnCategorias(ActionEvent e) {
		actionPerformedMntmCategora(e);
	}

	protected void actionPerformedBtnProductoNormal(ActionEvent e) {
		actionPerformedMntmProducto(e);
	}

	protected void actionPerformedBtnProductoDetallado(ActionEvent e) {
		actionPerformedMntmProductos(e);
	}

	protected void actionPerformedBtnProveedores(ActionEvent e) {
		actionPerformedMntmProveedor(e);
	}

	protected void actionPerformedBtnEmpleados(ActionEvent e) {
		actionPerformedMntmEmpleados(e);
	}

	protected void actionPerformedBtnClientes(ActionEvent e) {
		actionPerformedMntmClientes(e);
	}

	protected void actionPerformedBtnManualDelUsuario(ActionEvent e) {
		actionPerformedMntmManualDelUsuario(e);
	}

	protected void actionPerformedBtnAcercaDe(ActionEvent e) {
		actionPerformedMntmAcercaDe(e);
	}
	public void componentHidden(ComponentEvent e) {
	}
	public void componentMoved(ComponentEvent e) {
	}
	public void componentResized(ComponentEvent e) {
		if (e.getSource() == this) {
			componentResizedThis(e);
		}
	}
	public void componentShown(ComponentEvent e) {
	}
	
	protected void componentResizedThis(ComponentEvent e) {
		
	}
	
	protected void actionPerformedMntmReporteProductos(ActionEvent e) {
		FrmConsultaProducto frmReporteProducto = new FrmConsultaProducto();
		desktopPane.add(frmReporteProducto);
		Sistema.centrar(frmReporteProducto);
		frmReporteProducto.setVisible(true);
	}
	protected void actionPerformedMntmReporteProveedores(ActionEvent e) {
		FrmConsultaProveedor frmReporteProveedor = new FrmConsultaProveedor();
		desktopPane.add(frmReporteProveedor);
		Sistema.centrar(frmReporteProveedor);
		frmReporteProveedor.setVisible(true);
	}
	
	protected void actionPerformedMntmReporteClientes(ActionEvent e) {
		FrmConsultaCliente frmReporteCliente = new FrmConsultaCliente();
		desktopPane.add(frmReporteCliente);
		Sistema.centrar(frmReporteCliente);
		frmReporteCliente.setVisible(true);
	}
	protected void actionPerformedMntmReporteVentas(ActionEvent e) {
		FrmConsultaVentas frmReporteVentas = new FrmConsultaVentas();
		desktopPane.add(frmReporteVentas);
		Sistema.centrar(frmReporteVentas);
		frmReporteVentas.setVisible(true);
	}
	protected void actionPerformedBtnRealizarCompra(ActionEvent e) {
		FrmCompra frmCompra = new FrmCompra();
		FrmCompra.txtCodigoEmpleado.setText(this.empleadoActual.getIdEmpleado());
		FrmCompra.txtNombreEmpleado
				.setText(String.format("%s %s", this.empleadoActual.getNombres(), this.empleadoActual.getApellidos()));
		desktopPane.add(frmCompra);
		Sistema.centrar(frmCompra);
		frmCompra.setVisible(true);
	}
	protected void actionPerformedBtnRealizarVenta(ActionEvent e) {
		FrmVenta frmVenta = new FrmVenta();
		FrmVenta.txtCodigoEmpleado.setText(this.empleadoActual.getIdEmpleado());
		FrmVenta.txtNombreEmpleado
				.setText(String.format("%s %s", this.empleadoActual.getNombres(), this.empleadoActual.getApellidos()));
		desktopPane.add(frmVenta);
		Sistema.centrar(frmVenta);
		frmVenta.setVisible(true);
	}
}
