package gui;

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

import entidad.Categoria;
import model.CategoriaModel;
import util.Mensaje;
import util.TablaUtil;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FrmCategoria extends JInternalFrame implements ActionListener, MouseListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNombre;
	private JLabel lblDescripcin;
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
	private CategoriaModel modelo;
	private String idSeleccionado;
	private JLabel lblDeRegistros;
	private JLabel lblNumeroRegistros;
	private JPanel panel_2;
	private JLabel lblCdigo;
	private JTextField txtIdCategoria;
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
					FrmCategoria frame = new FrmCategoria();
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
	public FrmCategoria() {
		setFrameIcon(new ImageIcon(FrmCategoria.class.getResource("/iconos/categorias.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle(".:: Categor\u00EDas ::.");
		setBounds(100, 100, 672, 531);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		this.panel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel.setBorder(new TitledBorder(null, "Ingreso de Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 42, 522, 122);
		contentPane.add(panel);
		panel.setLayout(null);

		lblNombre = new JLabel("* Nombre:");
		this.lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(27, 59, 73, 14);
		panel.add(lblNombre);

		lblDescripcin = new JLabel("Descripci\u00F3n:");
		this.lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescripcin.setBounds(27, 90, 73, 14);
		panel.add(lblDescripcin);

		txtNombre = new JTextField();
		this.txtNombre.addKeyListener(this);
		txtNombre.setBounds(104, 56, 250, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtDescripcion = new JTextField();
		this.txtDescripcion.addKeyListener(this);
		txtDescripcion.setBounds(104, 87, 380, 20);
		panel.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		this.lblCdigo = new JLabel("C\u00F3digo:");
		this.lblCdigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblCdigo.setBounds(27, 28, 46, 14);
		this.panel.add(this.lblCdigo);

		this.txtIdCategoria = new JTextField();
		this.txtIdCategoria.setEnabled(false);
		this.txtIdCategoria.setBounds(104, 25, 156, 20);
		this.panel.add(this.txtIdCategoria);
		this.txtIdCategoria.setColumns(10);

		panel_1 = new JPanel();
		this.panel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de Categor\u00EDas",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 161, 522, 333);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 27, 502, 268);
		panel_1.add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "C\u00F3digo", "Nombre", "Descripci\u00F3n" }));
		this.formatearTabla();
		scrollPane.setViewportView(table);

		this.lblDeRegistros = new JLabel("# de registros:");
		this.lblDeRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDeRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDeRegistros.setBounds(254, 306, 121, 16);
		this.panel_1.add(this.lblDeRegistros);

		this.lblNumeroRegistros = new JLabel("");
		this.lblNumeroRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblNumeroRegistros.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblNumeroRegistros.setBounds(390, 307, 95, 14);
		this.panel_1.add(this.lblNumeroRegistros);

		lblCategoras = new JLabel("CATEGOR\u00CDAS");
		lblCategoras.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCategoras.setBounds(220, 11, 109, 20);
		contentPane.add(lblCategoras);

		this.idSeleccionado = "";

		// Se obtiene el modelo de Tabla
		this.modeloTabla = (DefaultTableModel) this.table.getModel();

		// Se crea el modelo de Categoría
		this.modelo = new CategoriaModel();

		// Se lista las categorías al cargar el formulario
		this.listarCategorias(modelo.listarCategoria());

		this.panel_2 = new JPanel();
		this.panel_2
				.setBorder(new TitledBorder(null, "Mantenimiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel_2.setBounds(542, 42, 102, 452);
		this.contentPane.add(this.panel_2);
		this.panel_2.setLayout(null);

		btnRegistrar = new JButton("");
		this.btnRegistrar.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.btnRegistrar.setToolTipText("Registrar");
		this.btnRegistrar.setIcon(new ImageIcon(FrmCategoria.class.getResource("/iconos/btnRegistrar.png")));
		this.btnRegistrar.setBounds(10, 23, 82, 82);
		this.panel_2.add(this.btnRegistrar);

		btnEliminar = new JButton("");
		this.btnEliminar.setToolTipText("Eliminar");
		this.btnEliminar.setIcon(new ImageIcon(FrmCategoria.class.getResource("/iconos/btnEliminar2.png")));
		this.btnEliminar.setBounds(10, 106, 82, 82);
		this.panel_2.add(this.btnEliminar);
		btnEliminar.addActionListener(this);
		this.btnEliminar.setEnabled(false);

		btnActualizar = new JButton("");
		this.btnActualizar.setToolTipText("Actualizar");
		this.btnActualizar.setIcon(new ImageIcon(FrmCategoria.class.getResource("/iconos/btnActualizar.png")));
		this.btnActualizar.setBounds(11, 188, 82, 82);
		this.panel_2.add(this.btnActualizar);
		btnActualizar.addActionListener(this);
		this.btnActualizar.setEnabled(false);

		btnSalir = new JButton("");
		this.btnSalir.setToolTipText("Salir");
		this.btnSalir.setIcon(new ImageIcon(FrmCategoria.class.getResource("/iconos/salir.png")));
		this.btnSalir.setBounds(12, 354, 82, 82);
		this.panel_2.add(this.btnSalir);

		this.btnLimpiar = new JButton("");
		this.btnLimpiar.addActionListener(this);
		this.btnLimpiar.setIcon(new ImageIcon(FrmCategoria.class.getResource("/iconos/limpiar.png")));
		this.btnLimpiar.setToolTipText("Limpiar");
		this.btnLimpiar.setBounds(11, 271, 82, 82);
		this.panel_2.add(this.btnLimpiar);
		btnSalir.addActionListener(this);
		btnRegistrar.addActionListener(this);

		this.txtIdCategoria.setText(modelo.generarCodigoCategoria());
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
		modeloColumna.getColumn(2).setPreferredWidth(300);

		// Se centran algunas columnas
		TablaUtil.centrarCelda(this.table, 0);
		TablaUtil.centrarCelda(this.table, 1);
	}

	public void listarCategorias(List<Categoria> categorias) {
		// Se limpia la tabla
		this.modeloTabla.setRowCount(0);

		// Se añade a la tabla las categorías fila a fila
		for (Categoria categoria : categorias) {
			Object[] fila = { categoria.getIdCategoria(), categoria.getNombre(), categoria.getDescripcion() };

			this.modeloTabla.addRow(fila);
		}

		this.lblNumeroRegistros.setText(String.valueOf(categorias.size()));
	}

	public void limpiar() {
		this.txtNombre.setText("");
		this.txtDescripcion.setText("");
		this.txtNombre.requestFocus();
		this.txtIdCategoria.setText(modelo.generarCodigoCategoria());
		this.btnRegistrar.setEnabled(true);
		this.btnEliminar.setEnabled(false);
		this.btnActualizar.setEnabled(false);
	}

	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		// Entrada de datos
		String idCategoria = this.txtIdCategoria.getText().trim();
		String nombre = this.txtNombre.getText().trim();
		String descripcion = this.txtDescripcion.getText().trim();

		// Validación
		if (!(nombre.length() > 0 && nombre.length() <= 45)) {
			Mensaje.mensajeError(this, "El nombre debe tener entre 1 a 45 caracteres");
			this.txtNombre.requestFocus();
			return;
		}

		if (!(descripcion.length() >= 0 && descripcion.length() <= 100)) {
			Mensaje.mensajeError(this, "La descripción debe tener entre 0 a 100 caracteres");
			this.txtDescripcion.requestFocus();
			return;
		}

		// Se instancia el objeto
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(idCategoria);
		categoria.setNombre(nombre);
		categoria.setDescripcion(descripcion);

		// Se envía al modelo
		int salida = modelo.insertarCategoria(categoria);

		if (salida > 0) {
			this.listarCategorias(modelo.listarCategoria());
			Mensaje.mensajeInformacion(this, "Registro exitoso");
		} else {
			Mensaje.mensajeError(this, "Ocurrió un error al registrar");
		}
		this.limpiar();
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		if (this.idSeleccionado.length() >= 0) {
			int respuesta = Mensaje.mensajeConfirmacion(this,
					"¿Seguro que desea eliminar la Categoría con ID: " + idSeleccionado + "?");

			if (respuesta == JOptionPane.YES_OPTION) {

				int salida = modelo.eliminarCategoria(idSeleccionado);
				this.listarCategorias(modelo.listarCategoria());

				if (salida > 0) {
					Mensaje.mensajeInformacion(this, "Eliminación correcta");
				} else {
					Mensaje.mensajeError(this, "Ocurrió un error al intentar eliminar");
				}
				this.limpiar();
				this.idSeleccionado = "";
			}

		} else {
			Mensaje.mensajeAdvertencia(this, "Debe seleccionar una fila para eliminar");
		}
	}

	protected void actionPerformedBtnActualizar(ActionEvent e) {
		if (this.idSeleccionado.length() >= 0) {
			int respuesta = Mensaje.mensajeConfirmacion(this,
					"¿Seguro que desea actualizar la Categoría con ID: " + idSeleccionado + "?");

			if (respuesta == JOptionPane.YES_OPTION) {

				String nombre = this.txtNombre.getText().trim();
				String descripcion = this.txtDescripcion.getText().trim();

				// Validación
				if (!(nombre.length() > 0 && nombre.length() <= 45)) {
					Mensaje.mensajeError(this, "El nombre debe tener entre 1 a 45 caracteres");
					this.txtNombre.requestFocus();
					return;
				}

				if (!(descripcion.length() >= 0 && descripcion.length() <= 100)) {
					Mensaje.mensajeError(this, "La descripción debe tener entre 0 a 100 caracteres");
					this.txtDescripcion.requestFocus();
					return;
				}

				Categoria categoria = new Categoria();
				categoria.setIdCategoria(idSeleccionado);
				categoria.setNombre(nombre);
				categoria.setDescripcion(descripcion);

				int salida = modelo.actualizarCategoria(categoria);
				this.listarCategorias(modelo.listarCategoria());

				if (salida > 0) {
					Mensaje.mensajeInformacion(this, "Actualización correcta");
				} else {
					Mensaje.mensajeError(this, "Ocurrió un error al intentar actualizar");
				}
				this.limpiar();
				this.idSeleccionado = "";
			}

		} else {
			Mensaje.mensajeAdvertencia(this, "Debe seleccionar una fila para actualizar");
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

		String nombre = this.modeloTabla.getValueAt(filaSeleccionada, 1).toString();
		String descripcion = this.modeloTabla.getValueAt(filaSeleccionada, 2).toString();

		this.txtIdCategoria.setText(this.idSeleccionado);
		this.txtNombre.setText(nombre);
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
		if (e.getSource() == this.txtNombre) {
			keyReleasedTxtNombre(e);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	// Valida un máximo de 45 caracteres
	protected void keyReleasedTxtNombre(KeyEvent e) {
		int maxCaracteres = 45;
		if (this.txtDescripcion.getText().length() > maxCaracteres) {
			this.txtDescripcion.setText(this.txtDescripcion.getText().substring(0, maxCaracteres));
		}
	}

	// Valida un máximo de 100 caracteres
	protected void keyReleasedTxtDescripcion(KeyEvent e) {
		int maxCaracteres = 100;
		if (this.txtDescripcion.getText().length() > maxCaracteres) {
			this.txtDescripcion.setText(this.txtDescripcion.getText().substring(0, maxCaracteres));
		}
	}
}
