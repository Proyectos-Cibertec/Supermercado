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

import entidad.Producto;
import model.ProductoModel;
import util.FechaUtil;
import util.Mensaje;
import util.TablaUtil;
import javax.swing.ImageIcon;

public class FrmBuscarProducto extends JInternalFrame implements ActionListener, KeyListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblProducto;
	private JTextField txtProducto;
	private JPanel panel;
	private JRadioButton rbtCodigo;
	private JRadioButton rbtCategoria;
	private JRadioButton rbtProveedor;
	private JRadioButton rbtNombre;
	private JRadioButton rbtDescripcion;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private DefaultTableModel modeloTabla;
	private ProductoModel modeloProducto;
	private JLabel lblNumeroRegistros;
	private JLabel lblNroDeRegistros;
	private int filaSeleccionada;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmBuscarProducto frame = new FrmBuscarProducto();
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
	public FrmBuscarProducto() {
		setIconifiable(true);
		setClosable(true);
		setTitle(".:: Buscar Producto ::.");
		setBounds(100, 100, 862, 554);
		getContentPane().setLayout(null);

		this.lblProducto = new JLabel("Producto:");
		this.lblProducto.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblProducto.setBounds(10, 27, 71, 14);
		getContentPane().add(this.lblProducto);

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
		this.rbtCodigo.setBounds(40, 12, 66, 23);
		this.panel.add(this.rbtCodigo);

		this.rbtCategoria = new JRadioButton("Categor\u00EDa");
		this.rbtCategoria.setBounds(352, 12, 84, 23);
		this.panel.add(this.rbtCategoria);

		this.rbtProveedor = new JRadioButton("Proveedor");
		this.rbtProveedor.setBounds(460, 12, 84, 23);
		this.panel.add(this.rbtProveedor);

		this.rbtNombre = new JRadioButton("Nombre");
		this.rbtNombre.setSelected(true);
		this.rbtNombre.setBounds(138, 12, 66, 23);
		this.panel.add(this.rbtNombre);

		this.rbtDescripcion = new JRadioButton("Descripci\u00F3n");
		this.rbtDescripcion.setBounds(234, 12, 84, 23);
		this.panel.add(this.rbtDescripcion);

		ButtonGroup bg = new ButtonGroup();
		bg.add(this.rbtCodigo);
		bg.add(this.rbtCategoria);
		bg.add(this.rbtProveedor);
		bg.add(this.rbtNombre);
		bg.add(this.rbtDescripcion);

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(10, 56, 826, 403);
		getContentPane().add(this.scrollPane);

		this.table = new JTable();
		this.table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre", "Categoría", "Proveedor",
				"Precio Venta", "Precio Compra", "Stock", "Mínimo Stock", "Fecha Registro", "Descripción" }));
		this.formatearTabla();
		this.table.setFillsViewportHeight(true);
		this.scrollPane.setViewportView(this.table);

		this.btnAceptar = new JButton("ACEPTAR");
		this.btnAceptar.setIcon(new ImageIcon(FrmBuscarProducto.class.getResource("/iconos/btnACeptar.png")));
		this.btnAceptar.addActionListener(this);
		this.btnAceptar.setBounds(10, 470, 132, 43);
		getContentPane().add(this.btnAceptar);

		this.btnCancelar = new JButton("CANCELAR");
		this.btnCancelar.setIcon(new ImageIcon(FrmBuscarProducto.class.getResource("/iconos/btnSalir.png")));
		this.btnCancelar.addActionListener(this);
		this.btnCancelar.setBounds(146, 470, 132, 43);
		getContentPane().add(this.btnCancelar);

		this.lblNumeroRegistros = new JLabel("");
		this.lblNumeroRegistros.setBounds(612, 474, 72, 14);
		getContentPane().add(this.lblNumeroRegistros);

		this.lblNroDeRegistros = new JLabel("Nro de registros:");
		this.lblNroDeRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblNroDeRegistros.setBounds(485, 474, 117, 14);
		getContentPane().add(this.lblNroDeRegistros);

		// Se obtiene el modelo de Tabla
		this.modeloTabla = (DefaultTableModel) this.table.getModel();

		// Se crea el modelo
		this.modeloProducto = new ProductoModel();

		// Se lista los productos al cargar el formulario
		this.listarProductos(this.modeloProducto.listarProducto());
		
		this.filaSeleccionada = -1;  
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

	public void formatearTabla() {
		this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Se establece un ancho a las columnas
		TableColumnModel modeloColumna = this.table.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(70); // ID
		modeloColumna.getColumn(1).setPreferredWidth(280); // Nombre
		modeloColumna.getColumn(2).setPreferredWidth(110); // Categoría
		modeloColumna.getColumn(3).setPreferredWidth(150); // Proveedor
		modeloColumna.getColumn(4).setPreferredWidth(80); // Precio Venta
		modeloColumna.getColumn(5).setPreferredWidth(80); // Precio Compra
		modeloColumna.getColumn(6).setPreferredWidth(60); // Stock Actual
		modeloColumna.getColumn(7).setPreferredWidth(60); // Stock Mínimo
		modeloColumna.getColumn(8).setPreferredWidth(80); // Fecha Registro
		modeloColumna.getColumn(9).setPreferredWidth(280); // Descripción

		// Se centran algunas columnas
		for (int i = 0; i < modeloColumna.getColumnCount(); ++i) {
			TablaUtil.centrarCelda(this.table, i);
		}
	}

	public void seleccionarProducto() {
		if (this.filaSeleccionada == -1) {
			Mensaje.mensajeError(this, "No ha seleccionado ninguna fila");
			
		} else {
			// Se obtiene el código y nombre del producto seleccionado
			String codigoProducto = this.table.getValueAt(this.filaSeleccionada, 0).toString();
			String nombreProducto = this.table.getValueAt(this.filaSeleccionada, 1).toString();
			String precioVenta = this.table.getValueAt(this.filaSeleccionada, 4).toString();			

			// Se envía el código y nombre del producto elegido
			FrmVenta.txtCodigoProducto.setText(codigoProducto);
			FrmVenta.txtDescripcionProducto.setText(nombreProducto);
			FrmVenta.txtPrecioVenta.setText(precioVenta);

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
		this.seleccionarProducto();
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
			this.listarProductos(this.modeloProducto.listarProductoxCodigo(filtro));

		} else if (this.rbtNombre.isSelected()) {
			this.listarProductos(this.modeloProducto.listarProductoxNombre(filtro));

		} else if (this.rbtDescripcion.isSelected()) {
			this.listarProductos(this.modeloProducto.listarProductoxDescripcion(filtro));

		} else if (this.rbtCategoria.isSelected()) {
			this.listarProductos(this.modeloProducto.listarProductoxNombreCategoria(filtro));

		} else if (this.rbtProveedor.isSelected()) {
			this.listarProductos(this.modeloProducto.listarProductoxRazonSocialProveedor(filtro));
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

			this.seleccionarProducto();
		}
	}

}
