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

import entidad.Caja;
import model.CajaModel;
import util.Constantes;
import util.Mensaje;
import util.TablaUtil;

public class FrmCaja extends JInternalFrame implements ActionListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblDescripcin;
	private JTextField txtDescripcion;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnRegistrar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JButton btnSalir;
	private JLabel lblCaja;
	private DefaultTableModel modeloTabla;
	private CajaModel modelo;
	private String idSeleccionado;
	private JLabel lblDeRegistros;
	private JLabel lblNumeroRegistros;
	private JPanel panel_2;
	private JLabel lblCdigo;
	private JTextField txtIdCaja;
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
					FrmCaja frame = new FrmCaja();
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
	public FrmCaja() {
		setFrameIcon(new ImageIcon(FrmCaja.class.getResource("/iconos/caja.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle(".:: Caja ::.");
		setBounds(100, 100, 524, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		this.panel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.setBorder(new TitledBorder(null, "Ingreso de Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 42, 381, 106);
		contentPane.add(panel);
		panel.setLayout(null);

		lblDescripcin = new JLabel("Descripci\u00F3n:");
		this.lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescripcin.setBounds(27, 65, 73, 14);
		panel.add(lblDescripcin);

		txtDescripcion = new JTextField();
		this.txtDescripcion.addKeyListener(this);
		txtDescripcion.setBounds(104, 62, 193, 20);
		panel.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		this.lblCdigo = new JLabel("C\u00F3digo:");
		this.lblCdigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblCdigo.setBounds(27, 28, 46, 14);
		this.panel.add(this.lblCdigo);

		this.txtIdCaja = new JTextField();
		this.txtIdCaja.setEnabled(false);
		this.txtIdCaja.setBounds(104, 25, 114, 20);
		this.panel.add(this.txtIdCaja);
		this.txtIdCaja.setColumns(10);

		panel_1 = new JPanel();
		this.panel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de Caja",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 159, 381, 325);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 27, 344, 260);
		panel_1.add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "C\u00F3digo", "Descripci\u00F3n" }));
		this.formatearTabla();
		scrollPane.setViewportView(table);

		this.lblDeRegistros = new JLabel("# de registros:");
		this.lblDeRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDeRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDeRegistros.setBounds(123, 298, 121, 16);
		this.panel_1.add(this.lblDeRegistros);

		this.lblNumeroRegistros = new JLabel("");
		this.lblNumeroRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblNumeroRegistros.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblNumeroRegistros.setBounds(259, 299, 95, 14);
		this.panel_1.add(this.lblNumeroRegistros);

		lblCaja = new JLabel("CAJA");
		this.lblCaja.setHorizontalAlignment(SwingConstants.CENTER);
		lblCaja.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCaja.setBounds(10, 11, 488, 20);
		contentPane.add(lblCaja);

		this.idSeleccionado = "";

		// Se obtiene el modelo de Tabla
		this.modeloTabla = (DefaultTableModel) this.table.getModel();

		// Se crea el modelo de Caja
		this.modelo = new CajaModel();

		// Se listan las cajas al cargar el formulario
		// this.listarCajas(modelo.listarCaja());

		this.panel_2 = new JPanel();
		this.panel_2
				.setBorder(new TitledBorder(null, "Mantenimiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel_2.setBounds(401, 42, 102, 442);
		this.contentPane.add(this.panel_2);
		this.panel_2.setLayout(null);

		btnRegistrar = new JButton("");
		this.btnRegistrar.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.btnRegistrar.setToolTipText("Registrar");
		this.btnRegistrar.setIcon(new ImageIcon(FrmCaja.class.getResource("/iconos/btnRegistrar.png")));
		this.btnRegistrar.setBounds(10, 23, 82, 82);
		this.panel_2.add(this.btnRegistrar);

		btnEliminar = new JButton("");
		this.btnEliminar.setToolTipText("Eliminar");
		this.btnEliminar.setIcon(new ImageIcon(FrmCaja.class.getResource("/iconos/btnEliminar2.png")));
		this.btnEliminar.setBounds(10, 105, 82, 82);
		this.panel_2.add(this.btnEliminar);
		btnEliminar.addActionListener(this);
		this.btnEliminar.setEnabled(false);

		btnActualizar = new JButton("");
		this.btnActualizar.setToolTipText("Actualizar");
		this.btnActualizar.setIcon(new ImageIcon(FrmCaja.class.getResource("/iconos/btnActualizar.png")));
		this.btnActualizar.setBounds(10, 187, 82, 82);
		this.panel_2.add(this.btnActualizar);
		btnActualizar.addActionListener(this);
		this.btnActualizar.setEnabled(false);

		btnSalir = new JButton("");
		this.btnSalir.setToolTipText("Salir");
		this.btnSalir.setIcon(new ImageIcon(FrmCaja.class.getResource("/iconos/salir.png")));
		this.btnSalir.setBounds(10, 352, 82, 82);
		this.panel_2.add(this.btnSalir);

		this.btnLimpiar = new JButton("");
		this.btnLimpiar.addActionListener(this);
		this.btnLimpiar.setIcon(new ImageIcon(FrmCaja.class.getResource("/iconos/limpiar.png")));
		this.btnLimpiar.setToolTipText("Limpiar");
		this.btnLimpiar.setBounds(10, 269, 82, 82);
		this.panel_2.add(this.btnLimpiar);
		btnSalir.addActionListener(this);
		btnRegistrar.addActionListener(this);

		this.txtIdCaja.setText(modelo.generarCodigoCaja());
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

	public void formatearTabla() {
		// this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// Se establece un ancho a las columnas
		TableColumnModel modeloColumna = this.table.getColumnModel();
		modeloColumna.getColumn(0).setPreferredWidth(50);
		modeloColumna.getColumn(1).setPreferredWidth(150);

		// Se centran algunas columnas
		TablaUtil.centrarCelda(this.table, 0);
		TablaUtil.centrarCelda(this.table, 1);
	}

	public void listarCajas(List<Caja> cajas) {
		// Se limpia la tabla
		this.modeloTabla.setRowCount(0);

		// Se añade a la tabla las cajas fila a fila
		for (Caja caja : cajas) {
			Object[] fila = { caja.getIdCaja(), caja.getDescripcion() };

			this.modeloTabla.addRow(fila);
		}

		this.lblNumeroRegistros.setText(String.valueOf(cajas.size()));
	}

	public void limpiar() {
		this.txtDescripcion.setText("");
		this.txtDescripcion.requestFocus();
		this.txtIdCaja.setText(modelo.generarCodigoCaja());
		this.btnRegistrar.setEnabled(true);
		this.btnEliminar.setEnabled(false);
		this.btnActualizar.setEnabled(false);
	}

	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		
		// Entrada de datos
		String idCaja = this.txtIdCaja.getText().trim();
		String descripcion = this.txtDescripcion.getText().trim();

		// Validación
		if (!(descripcion.length() >= 0 && descripcion.length() <= 45)) {
			Mensaje.mensajeError(this, "La descripción debe tener entre 0 a 45 caracteres");
			this.txtDescripcion.requestFocus();
			return;
		}

		// Se instancia el objeto
		Caja caja = new Caja();
		caja.setIdCaja(idCaja);
		caja.setDescripcion(descripcion);

		// Se envía al modelo
		int salida = modelo.insertarCaja(caja);

		if (salida > 0) {
			this.listarCajas(modelo.listarCaja());
			Mensaje.mensajeInformacion(this, Constantes.MSG_SUCCESSFULLY_REGISTRATION);
		} else {
			Mensaje.mensajeError(this, Constantes.MSG_FAILED_REGISTRATION);
		}
		this.limpiar();
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		if (this.idSeleccionado.length() >= 0) {
			int respuesta = Mensaje.mensajeConfirmacion(this,
					"¿Seguro que desea eliminar la Caja con ID: " + idSeleccionado + "?");

			if (respuesta == JOptionPane.YES_OPTION) {

				int salida = modelo.eliminarCaja(idSeleccionado);
				this.listarCajas(modelo.listarCaja());

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
					"¿Seguro que desea actualizar la caja con ID: " + idSeleccionado + "?");

			if (respuesta == JOptionPane.YES_OPTION) {
				String descripcion = this.txtDescripcion.getText().trim();

				// Validación
				if (!(descripcion.length() >= 0 && descripcion.length() <= 45)) {
					Mensaje.mensajeError(this, "La descripción debe tener entre 0 a 45 caracteres");
					this.txtDescripcion.requestFocus();
					return;
				}

				Caja caja = new Caja();
				caja.setIdCaja(idSeleccionado);
				caja.setDescripcion(descripcion);

				int salida = modelo.actualizarCaja(caja);
				this.listarCajas(modelo.listarCaja());

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

		String descripcion = this.modeloTabla.getValueAt(filaSeleccionada, 1).toString();

		this.txtIdCaja.setText(this.idSeleccionado);
		this.txtDescripcion.setText(descripcion);

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
		if (e.getSource() == this.txtDescripcion) {
			keyReleasedTxtDescripcion(e);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	// Valida un máximo de 45 caracteres
	protected void keyReleasedTxtDescripcion(KeyEvent e) {
		int maxCaracteres = 45;
		if (this.txtDescripcion.getText().length() > maxCaracteres) {
			this.txtDescripcion.setText(this.txtDescripcion.getText().substring(0, maxCaracteres));
		}
	}
}
