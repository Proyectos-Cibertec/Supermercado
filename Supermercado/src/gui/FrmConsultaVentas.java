package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import entidad.DetalleVenta;
import entidad.Producto;
import entidad.Venta;
import model.ProductoModel;
import model.VentaModel;
import util.FechaUtil;
import util.Mensaje;
import util.Redondear;
import util.RelojFechaHora;
import util.TablaUtil;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class FrmConsultaVentas extends JInternalFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JLabel lblDesde;
	private JDateChooser jdDesde;
	private JLabel lblHasta;
	private JDateChooser jdHasta;
	private JButton btnConsultar;
	private JButton btnImprimir;
	private JPanel panel1;
	private JScrollPane scrollPane;
	private JTable table1;
	private DefaultTableModel modeloTabla1;
	private DefaultTableModel modeloTabla2;
	private VentaModel modeloVenta;
	private ProductoModel modeloProducto;
	private int filaSeleccionada = -1;
	private JPanel detalle;
	private JScrollPane scrollPane_1;
	private JTable table2;
	private JLabel lblFechaHora;
	private JLabel lblTotal;
	private JLabel lblTotalVenta;
	private JPanel panel_1;
	private JLabel lblS;
	private JLabel lblSubtotal;
	private JTextField txtSubtotal;
	private JLabel lblIgv;
	private JTextField txtIGV;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmConsultaVentas frame = new FrmConsultaVentas();
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
	public FrmConsultaVentas() {
		setTitle("REPORTE DE VENTAS");
		setClosable(true);
		setFrameIcon(new ImageIcon(FrmConsultaVentas.class.getResource("/iconos/consultaVenta.png")));
		setIconifiable(true);
		setBounds(100, 100, 813, 612);
		getContentPane().setLayout(null);

		this.panel = new JPanel();
		this.panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Opciones de consulta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		this.panel.setBounds(6, 6, 784, 71);
		getContentPane().add(this.panel);
		this.panel.setLayout(null);

		this.lblDesde = new JLabel("DESDE:");
		this.lblDesde.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDesde.setBounds(10, 25, 133, 14);
		this.panel.add(this.lblDesde);

		this.jdDesde = new JDateChooser();
		this.jdDesde.setBounds(10, 39, 133, 20);
		this.panel.add(this.jdDesde);

		this.lblHasta = new JLabel("HASTA:");
		this.lblHasta.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblHasta.setBounds(153, 25, 135, 14);
		this.panel.add(this.lblHasta);

		this.jdHasta = new JDateChooser();
		this.jdHasta.setBounds(153, 39, 135, 20);
		this.panel.add(this.jdHasta);

		this.btnConsultar = new JButton("");
		this.btnConsultar.setToolTipText("BUSCAR");
		this.btnConsultar.addActionListener(this);
		this.btnConsultar.setIcon(new ImageIcon(FrmConsultaVentas.class.getResource("/iconos/buscar32.png")));
		this.btnConsultar.setBounds(298, 16, 89, 43);
		this.panel.add(this.btnConsultar);

		this.btnImprimir = new JButton("");
		this.btnImprimir.setToolTipText("IMPRIMIR");
		this.btnImprimir.addActionListener(this);
		this.btnImprimir.setIcon(new ImageIcon(FrmConsultaVentas.class.getResource("/iconos/imprimir.png")));
		this.btnImprimir.setBounds(392, 16, 89, 43);
		this.panel.add(this.btnImprimir);

		this.lblFechaHora = new JLabel("");
		this.lblFechaHora.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.lblFechaHora.setBounds(515, 45, 240, 14);
		this.panel.add(this.lblFechaHora);

		this.panel1 = new JPanel();
		this.panel1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panel1.setBounds(8, 77, 782, 221);
		getContentPane().add(this.panel1);
		this.panel1.setLayout(null);

		this.scrollPane = new JScrollPane();
		this.scrollPane.setBounds(10, 11, 757, 194);
		this.panel1.add(this.scrollPane);

		this.table1 = new JTable();
		this.table1.addMouseListener(this);
		this.table1.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "CLIENTE", "FECHA", "TIPO COMPROBANTE", "SERIE-NUM", "IGV", "EMPLEADO" }));
		this.table1.setFillsViewportHeight(true);
		this.formatearTabla1();
		this.scrollPane.setViewportView(this.table1);

		this.detalle = new JPanel();
		this.detalle.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Detalle de venta",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.detalle.setBounds(6, 309, 784, 262);
		getContentPane().add(this.detalle);
		this.detalle.setLayout(null);

		this.scrollPane_1 = new JScrollPane();
		this.scrollPane_1.setBounds(10, 23, 761, 192);
		this.detalle.add(this.scrollPane_1);

		this.table2 = new JTable();
		this.table2.setFillsViewportHeight(true);
		this.table2.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "C\u00D3DIGO", "DESCRIPCI\u00D3N",
				"CANTIDAD", "PRECIO VENTA", "DESCT.", "PRECIO FINAL", "TOTAL S/." }));
		this.formatearTabla2();
		this.scrollPane_1.setViewportView(this.table2);

		this.lblTotal = new JLabel("TOTAL:");
		this.lblTotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.lblTotal.setBounds(513, 226, 65, 25);
		this.detalle.add(this.lblTotal);

		this.panel_1 = new JPanel();
		this.panel_1.setBackground(new Color(0, 0, 0));
		this.panel_1.setBounds(588, 226, 143, 25);
		this.detalle.add(this.panel_1);
		this.panel_1.setLayout(null);

		this.lblTotalVenta = new JLabel("0.00");
		this.lblTotalVenta.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblTotalVenta.setBounds(20, 0, 123, 25);
		this.panel_1.add(this.lblTotalVenta);
		this.lblTotalVenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.lblTotalVenta.setForeground(new Color(0, 204, 0));
		this.lblTotalVenta.setBackground(new Color(0, 0, 0));

		this.lblS = new JLabel("S/.");
		this.lblS.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.lblS.setForeground(new Color(51, 204, 0));
		this.lblS.setBounds(10, 0, 36, 25);
		this.panel_1.add(this.lblS);

		this.lblSubtotal = new JLabel("SUBTOTAL:");
		this.lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.lblSubtotal.setBounds(73, 229, 86, 22);
		this.detalle.add(this.lblSubtotal);

		this.txtSubtotal = new JTextField();
		this.txtSubtotal.setBackground(Color.WHITE);
		this.txtSubtotal.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtSubtotal.setEditable(false);
		this.txtSubtotal.setBounds(157, 226, 86, 25);
		this.detalle.add(this.txtSubtotal);
		this.txtSubtotal.setColumns(10);

		this.lblIgv = new JLabel("IGV:");
		this.lblIgv.setFont(new Font("Tahoma", Font.BOLD, 12));
		this.lblIgv.setBounds(323, 229, 46, 22);
		this.detalle.add(this.lblIgv);

		this.txtIGV = new JTextField();
		this.txtIGV.setBackground(Color.WHITE);
		this.txtIGV.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtIGV.setEditable(false);
		this.txtIGV.setBounds(367, 226, 86, 25);
		this.detalle.add(this.txtIGV);
		this.txtIGV.setColumns(10);

		// Se obtiene el modelo de Tabla
		this.modeloTabla1 = (DefaultTableModel) this.table1.getModel();
		this.modeloTabla2 = (DefaultTableModel) this.table2.getModel();

		this.modeloVenta = new VentaModel();
		this.modeloProducto = new ProductoModel();

		this.limpiar();
		iniciaReloj(this.lblFechaHora);
	}

	public void iniciaReloj(JLabel label) {
		RelojFechaHora fechaHora = new RelojFechaHora(label);
		fechaHora.actualiza();
	}

	public void formatearTabla1() {
		this.table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Se establece un ancho a las columnas
		TableColumnModel modeloColumna = this.table1.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(80); // ID
		modeloColumna.getColumn(1).setPreferredWidth(180); // Cliente
		modeloColumna.getColumn(2).setPreferredWidth(80); // Fecha de Registro
		modeloColumna.getColumn(3).setPreferredWidth(100); // Tipo de
															// comprobante
		modeloColumna.getColumn(4).setPreferredWidth(120);// Serie
		modeloColumna.getColumn(5).setPreferredWidth(50); // IGV
		modeloColumna.getColumn(6).setPreferredWidth(180); // Empleado
		// modeloColumna.getColumn(6).setPreferredWidth(80);// Total

		// Se centran algunas columnas
		for (int i = 0; i < modeloColumna.getColumnCount(); ++i) {
			TablaUtil.centrarCelda(this.table1, i);
		}
	}

	public void formatearTabla2() {
		this.table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Se establece un ancho a las columnas
		TableColumnModel modeloColumna = this.table2.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(80); // ID Producto
		modeloColumna.getColumn(1).setPreferredWidth(300); // Descripción
		modeloColumna.getColumn(2).setPreferredWidth(70); // Cantidad
		modeloColumna.getColumn(3).setPreferredWidth(85); // Precio de venta
		modeloColumna.getColumn(4).setPreferredWidth(80); // Descuento
		modeloColumna.getColumn(5).setPreferredWidth(80); // Precio Final
		modeloColumna.getColumn(6).setPreferredWidth(75); // Total

		// Se centran algunas columnas
		for (int i = 0; i < modeloColumna.getColumnCount(); ++i) {
			TablaUtil.centrarCelda(this.table2, i);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnImprimir) {
			actionPerformedBtnImprimir(e);
		}
		if (e.getSource() == this.btnConsultar) {
			actionPerformedBtnConsultar(e);
		}
	}

	public void listarVenta(List<Venta> ventas) {
		this.modeloTabla1.setRowCount(0);
		for (Venta venta : ventas) {
			Object[] fila = { venta.getIdVenta(),
					String.format("%s %s", venta.getCliente().getNombres(), venta.getCliente().getApellidos()),
					FechaUtil.formatoDeFechaClasico(venta.getFechaVenta()), venta.getTipoComprobante(),
					String.format("%s - %s", venta.getSerieComprobante(), venta.getNumeroComprobante()), 
					venta.getIgv(),
					String.format("%s %s", venta.getEmpleado().getNombres(), venta.getEmpleado().getApellidos()) };

			this.modeloTabla1.addRow(fila);
		}
	}

	public void listarDetalleVenta(List<DetalleVenta> detalles, double igv) {
		this.modeloTabla2.setRowCount(0);
		double subtotalDetalleVenta = 0;
		double totalIgv;
		double totalAPagar;

		for (DetalleVenta detalleVenta : detalles) {
			Producto producto = this.modeloProducto.getProducto(detalleVenta.getIdProducto());
			double precioFinal = detalleVenta.getPrecioVenta() - detalleVenta.getDescuento();
			double subtotal = precioFinal * detalleVenta.getCantidad();
			Object[] fila = { producto.getIdProducto(), producto.getDescripcion(), detalleVenta.getCantidad(),
					Redondear.redondear(detalleVenta.getPrecioVenta(), 2),
					Redondear.redondear(detalleVenta.getDescuento(), 2), Redondear.redondear(precioFinal, 2),
					Redondear.redondear(subtotal, 2) };

			this.modeloTabla2.addRow(fila);
			subtotalDetalleVenta += subtotal;
		}
		
		totalIgv = subtotalDetalleVenta * igv / 100;
		totalAPagar = subtotalDetalleVenta + totalIgv;
		
		//this.lblTotalVenta.setText(String.valueOf(Redondear.redondear(totalDetalleVenta, 2)));
		this.txtSubtotal.setText(String.valueOf(Redondear.redondear(subtotalDetalleVenta, 2)));
		this.txtIGV.setText(String.valueOf(Redondear.redondear(totalIgv, 2)));
		this.lblTotalVenta.setText(String.valueOf(Redondear.redondear(totalAPagar, 2)));
	}

	public void limpiar() {
		this.jdDesde.setDate(FechaUtil.today());
		this.jdHasta.setDate(FechaUtil.today());
		this.modeloTabla1.setRowCount(0);
		this.modeloTabla2.setRowCount(0);
		this.filaSeleccionada = -1;
	}

	protected void actionPerformedBtnConsultar(ActionEvent e) {
		String desde = FechaUtil.dateToString(this.jdDesde.getDate());
		String hasta = FechaUtil.dateToString(this.jdHasta.getDate());

		// Validación
		if (desde == null) {
			Mensaje.mensajeError(this, "Debe introducir una fecha de inicio");
			this.jdDesde.setDate(FechaUtil.today());
			this.jdDesde.requestFocusInWindow();
			return;
		}

		if (hasta == null) {
			Mensaje.mensajeError(this, "Debe introducir una fecha de fin");
			this.jdHasta.setDate(FechaUtil.today());
			this.jdHasta.requestFocusInWindow();
			return;
		}

		this.listarVenta(this.modeloVenta.listarVentaxFecha(desde, hasta));
	}

	protected void actionPerformedBtnImprimir(ActionEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this.table1) {
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
		this.filaSeleccionada = this.table1.getSelectedRow();

		if (this.filaSeleccionada != -1) {
			// Se lista el detalle de la venta
			String idVenta = this.modeloTabla1.getValueAt(this.filaSeleccionada, 0).toString();
			double igv = Double.parseDouble(this.modeloTabla1.getValueAt(this.filaSeleccionada, 5).toString()); 
			this.listarDetalleVenta(this.modeloVenta.listarDetalleVenta(idVenta), igv);
		}
	}
}
