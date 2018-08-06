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

import entidad.Sexo;
import model.SexoModel;
import util.Mensaje;
import util.TablaUtil;

public class FrmSexo extends JInternalFrame implements ActionListener, MouseListener, KeyListener {

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
	private JLabel lblSexo;
	private DefaultTableModel modeloTabla;
	private SexoModel modelo;
	private String idSeleccionado;
	private JLabel lblDeRegistros;
	private JLabel lblNumeroRegistros;
	private JPanel panel_2;
	private JLabel lblCdigo;
	private JTextField txtIdSexo;
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
					FrmSexo frame = new FrmSexo();
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
	public FrmSexo() {
		setFrameIcon(new ImageIcon(FrmSexo.class.getResource("/iconos/sexo.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle(".:: Sexo ::.");
		setBounds(100, 100, 524, 526);
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

		lblDescripcin = new JLabel("* Descripci\u00F3n:");
		this.lblDescripcin.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDescripcin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescripcin.setBounds(10, 65, 90, 14);
		panel.add(lblDescripcin);

		txtDescripcion = new JTextField();
		this.txtDescripcion.addKeyListener(this);
		txtDescripcion.setBounds(108, 63, 193, 20);
		panel.add(txtDescripcion);
		txtDescripcion.setColumns(10);

		this.lblCdigo = new JLabel("C\u00F3digo:");
		this.lblCdigo.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblCdigo.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblCdigo.setBounds(10, 28, 90, 14);
		this.panel.add(this.lblCdigo);

		this.txtIdSexo = new JTextField();
		this.txtIdSexo.setEnabled(false);
		this.txtIdSexo.setBounds(108, 26, 114, 20);
		this.panel.add(this.txtIdSexo);
		this.txtIdSexo.setColumns(10);

		panel_1 = new JPanel();
		this.panel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de Sexo",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 159, 381, 327);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 27, 344, 262);
		panel_1.add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "C\u00F3digo", "Descripci\u00F3n" }));
		this.formatearTabla();
		scrollPane.setViewportView(table);

		this.lblDeRegistros = new JLabel("# de registros:");
		this.lblDeRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblDeRegistros.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDeRegistros.setBounds(123, 300, 121, 16);
		this.panel_1.add(this.lblDeRegistros);

		this.lblNumeroRegistros = new JLabel("");
		this.lblNumeroRegistros.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.lblNumeroRegistros.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblNumeroRegistros.setBounds(259, 301, 95, 14);
		this.panel_1.add(this.lblNumeroRegistros);

		lblSexo = new JLabel("SEXO");
		this.lblSexo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSexo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSexo.setBounds(10, 11, 488, 20);
		contentPane.add(lblSexo);

		this.idSeleccionado = "";

		// Se obtiene el modelo de Tabla
		this.modeloTabla = (DefaultTableModel) this.table.getModel();

		// Se crea el modelo de Sexo
		this.modelo = new SexoModel();

		// Se lista los sexos al cargar el formulario
		this.listarSexos(modelo.listarSexo());

		this.panel_2 = new JPanel();
		this.panel_2
				.setBorder(new TitledBorder(null, "Mantenimiento", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panel_2.setBounds(401, 42, 102, 444);
		this.contentPane.add(this.panel_2);
		this.panel_2.setLayout(null);

		btnRegistrar = new JButton("");
		this.btnRegistrar.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.btnRegistrar.setToolTipText("Registrar");
		this.btnRegistrar.setIcon(new ImageIcon(FrmSexo.class.getResource("/iconos/btnRegistrar.png")));
		this.btnRegistrar.setBounds(10, 23, 82, 82);
		this.panel_2.add(this.btnRegistrar);

		btnEliminar = new JButton("");
		this.btnEliminar.setToolTipText("Eliminar");
		this.btnEliminar.setIcon(new ImageIcon(FrmSexo.class.getResource("/iconos/btnEliminar2.png")));
		this.btnEliminar.setBounds(10, 105, 82, 82);
		this.panel_2.add(this.btnEliminar);
		btnEliminar.addActionListener(this);
		this.btnEliminar.setEnabled(false);

		btnActualizar = new JButton("");
		this.btnActualizar.setToolTipText("Actualizar");
		this.btnActualizar.setIcon(new ImageIcon(FrmSexo.class.getResource("/iconos/btnActualizar.png")));
		this.btnActualizar.setBounds(10, 187, 82, 82);
		this.panel_2.add(this.btnActualizar);
		btnActualizar.addActionListener(this);
		this.btnActualizar.setEnabled(false);

		btnSalir = new JButton("");
		this.btnSalir.setToolTipText("Salir");
		this.btnSalir.setIcon(new ImageIcon(FrmSexo.class.getResource("/iconos/salir.png")));
		this.btnSalir.setBounds(10, 351, 82, 82);
		this.panel_2.add(this.btnSalir);

		this.btnLimpiar = new JButton("");
		this.btnLimpiar.addActionListener(this);
		this.btnLimpiar.setIcon(new ImageIcon(FrmSexo.class.getResource("/iconos/limpiar.png")));
		this.btnLimpiar.setToolTipText("Limpiar");
		this.btnLimpiar.setBounds(10, 269, 82, 82);
		this.panel_2.add(this.btnLimpiar);
		btnSalir.addActionListener(this);
		btnRegistrar.addActionListener(this);

		this.txtIdSexo.setText(modelo.generarCodigoSexo());
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

	public void listarSexos(List<Sexo> sexos) {
		// Se limpia la tabla
		this.modeloTabla.setRowCount(0);

		// Se añade a la tabla los sexos fila a fila
		for (Sexo sexo : sexos) {
			Object[] fila = { sexo.getIdSexo(), sexo.getDescripcion() };

			this.modeloTabla.addRow(fila);
		}

		this.lblNumeroRegistros.setText(String.valueOf(sexos.size()));
	}

	public void limpiar() {
		this.txtDescripcion.setText("");
		this.txtDescripcion.requestFocus();
		this.txtIdSexo.setText(modelo.generarCodigoSexo());
		
		this.btnRegistrar.setEnabled(true);
		this.btnEliminar.setEnabled(false);
		this.btnActualizar.setEnabled(false);
	}

	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		// Entrada de datos
		String idSexo = this.txtIdSexo.getText().trim();
		String descripcion = this.txtDescripcion.getText().trim();

		// Validación
		if (!(descripcion.length() > 0 && descripcion.length() <= 45)) {
			Mensaje.mensajeError(this, "La descripción debe tener entre 1 a 45 caracteres");
			this.txtDescripcion.requestFocus();
			return;
		} else {
			if (!descripcion.matches("[a-zA-ZñÑ]*")) {
				Mensaje.mensajeError(this, "La descripción solo debe tener caracteres alfabéticos");
				this.txtDescripcion.requestFocus();
				return;
			}
		}

		// Se instancia el objeto
		Sexo sexo = new Sexo();
		sexo.setIdSexo(idSexo);
		sexo.setDescripcion(descripcion);

		// Se envía al modelo
		int salida = modelo.insertarSexo(sexo);

		if (salida > 0) {
			this.listarSexos(modelo.listarSexo());
			Mensaje.mensajeInformacion(this, "Registro exitoso");
		} else {
			Mensaje.mensajeError(this, "Ocurrió un error al registrar");
		}
		this.limpiar();
	}

	protected void actionPerformedBtnEliminar(ActionEvent e) {
		if (this.idSeleccionado.length() >= 0) {
			int respuesta = Mensaje.mensajeConfirmacion(this,
					"¿Seguro que desea eliminar el Sexo con ID: " + idSeleccionado + "?");

			if (respuesta == JOptionPane.YES_OPTION) {

				int salida = modelo.eliminarSexo(idSeleccionado);
				this.listarSexos(modelo.listarSexo());

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
					"¿Seguro que desea actualizar el sexo con ID: " + idSeleccionado + "?");

			if (respuesta == JOptionPane.YES_OPTION) {
				String descripcion = this.txtDescripcion.getText().trim();

				// Validación
				if (!(descripcion.length() > 0 && descripcion.length() <= 45)) {
					Mensaje.mensajeError(this, "La descripción debe tener entre 1 a 45 caracteres");
					this.txtDescripcion.requestFocus();
					return;
				} else {
					if (!descripcion.matches("[a-zA-ZñÑ]*")) {
						Mensaje.mensajeError(this, "La descripción solo debe tener caracteres alfabéticos");
						this.txtDescripcion.requestFocus();
						return;
					}
				}

				Sexo sexo = new Sexo();
				sexo.setIdSexo(idSeleccionado);
				sexo.setDescripcion(descripcion);

				int salida = modelo.actualizarSexo(sexo);
				this.listarSexos(modelo.listarSexo());

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

		String descripcion = this.modeloTabla.getValueAt(filaSeleccionada, 1).toString();

		this.txtIdSexo.setText(this.idSeleccionado);
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
