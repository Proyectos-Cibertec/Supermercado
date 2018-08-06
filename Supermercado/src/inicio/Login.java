// Login.java
package inicio;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import entidad.Empleado;
import model.LoginModel;
import util.Mensaje;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class Login extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblFondo;
	private JTextField txtUsuario;
	private JButton btnIngresar;
	private JButton btnSalir;
	private JPasswordField txtPassword;
	private LoginModel modeloLogin;
	private Empleado empleado = null;
	private int contador; // Contabiliza el # de intentos de logeo
	private JProgressBar pbBarraProgreso;
	private Timer timer;
	private JLabel lblMiniatura;
	private JLabel lblIconoUsuario;
	private JLabel lblIconoPassword;

	/**
	 * Launch the application.
	 */
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
				try {
					Login frame = new Login();
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
	public Login() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/iconos/iconoBtnLoginIngresar.png")));
		setResizable(false);
		setBackground(Color.BLACK);
		setTitle("Ingreso al Sistema");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 490, 236);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(null);

		this.txtUsuario = new JTextField();
		this.txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtUsuario.setToolTipText("Ingrese su nombre de usuario");
		this.txtUsuario.setBorder(new LineBorder(new Color(171, 173, 179)));
		this.txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 22));
		this.txtUsuario.addActionListener(this);
		this.txtUsuario.setBounds(194, 24, 273, 31);
		this.contentPane.add(this.txtUsuario);
		this.txtUsuario.setColumns(10);

		this.btnIngresar = new JButton("INGRESAR");
		this.btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.btnIngresar.addActionListener(this);
		this.btnIngresar.setIcon(new ImageIcon(Login.class.getResource("/iconos/iconoBtnLoginIngresar.png")));
		this.btnIngresar.setBounds(194, 108, 139, 46);
		this.contentPane.add(this.btnIngresar);

		this.btnSalir = new JButton("SALIR");
		this.btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		this.btnSalir.addActionListener(this);
		this.btnSalir.setIcon(new ImageIcon(Login.class.getResource("/iconos/iconoBtnLoginSalir.png")));
		this.btnSalir.setBounds(343, 108, 124, 46);
		this.contentPane.add(this.btnSalir);

		this.txtPassword = new JPasswordField();
		this.txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		this.txtPassword.setToolTipText("Ingrese su contrase\u00F1a");
		this.txtPassword.setBorder(new LineBorder(new Color(171, 173, 179)));
		this.txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 22));
		this.txtPassword.addActionListener(this);
		this.txtPassword.setBounds(194, 66, 273, 31);
		this.contentPane.add(this.txtPassword);

		this.pbBarraProgreso = new JProgressBar();
		this.pbBarraProgreso.setFont(new Font("Tahoma", Font.BOLD, 14));
		this.pbBarraProgreso.setStringPainted(true);
		this.pbBarraProgreso.setBounds(10, 165, 457, 30);
		this.pbBarraProgreso.setVisible(false);
		this.contentPane.add(this.pbBarraProgreso);

		this.lblMiniatura = new JLabel("");
		this.lblMiniatura.setIcon(new ImageIcon(Login.class.getResource("/iconos/imagenLogin.png")));
		this.lblMiniatura.setBackground(Color.WHITE);
		this.lblMiniatura.setBounds(10, 11, 128, 143);
		this.contentPane.add(this.lblMiniatura);

		this.lblIconoUsuario = new JLabel("");
		this.lblIconoUsuario.setIcon(new ImageIcon(Login.class.getResource("/iconos/iconoLoginUsuario.png")));
		this.lblIconoUsuario.setBounds(155, 24, 32, 31);
		this.contentPane.add(this.lblIconoUsuario);

		this.lblIconoPassword = new JLabel("");
		this.lblIconoPassword.setIcon(new ImageIcon(Login.class.getResource("/iconos/iconoLoginPassword.png")));
		this.lblIconoPassword.setBounds(155, 68, 32, 31);
		this.contentPane.add(this.lblIconoPassword);

		this.lblFondo = new JLabel("");
		this.lblFondo.setBounds(0, 0, 525, 245);
		this.contentPane.add(this.lblFondo);

		this.modeloLogin = new LoginModel();

		this.setLocationRelativeTo(null);
	}

	public void salir() {
		int respuesta = Mensaje.mensajeConfirmacion(this, "Est seguro que desea salir?");

		if (respuesta == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void ingresar() {

		String usuario = this.txtUsuario.getText().trim();
		String password = new String(this.txtPassword.getPassword());

		// Validacin de la entrada de usuario y password
		if (usuario.length() == 0 && password.length() == 0) {
			Mensaje.mensajeError(this, "No debe dejar en blanco el nombre de usuario y contrasea");
			txtUsuario.requestFocus();
			return;

		} else if (usuario.length() == 0) {
			Mensaje.mensajeError(this, "No debe dejar en blanco el nombre de usuario");
			txtUsuario.requestFocus();
			return;

		} else if (password.length() == 0) {
			Mensaje.mensajeError(this, "No debe dejar en blanco la contrasea");
			txtPassword.requestFocus();
			return;
		}

		empleado = modeloLogin.login(usuario, password);

		if (empleado == null) { // No existe el empleado cuyo usuario y password
								// se ingresaron

			contador++; // Cuenta las veces que un usuario intenta logearse al
						// sistema

			// Verifica que no se haya pasado el lmite de intentos fallidos
			if (contador == 3) {
				Mensaje.mensajeError(this,
						"Ha sobrepasado el lmite de intentos fallidos. Comunquese con el Administrador del Sistema");
				System.exit(0);
			}

			Mensaje.mensajeError(this,
					"El usuario y/o contrasea que ha ingresado es incorrecto. Intente nuevamente.\n"
							+ "Verifique la tecla Bloq Mayus. Recuerde que luego de 3 intentos fallidos el programa "
							+ "se cerrar.");
			txtUsuario.setText("");
			txtPassword.setText("");
			txtUsuario.requestFocus();

		} else {
			iniciarEfectoDeCarga();
		}
	}

	// Inicia el efecto de carga en la barra de progreso y luego se abre el
	// programa principal
	public void iniciarEfectoDeCarga() {
		pbBarraProgreso.setVisible(true);
		pbBarraProgreso.setValue(0);

		// Se deshabilita los cuadros de texto y los botones
		txtUsuario.setEnabled(false);
		txtPassword.setEnabled(false);
		btnIngresar.setEnabled(false);
		btnSalir.setEnabled(false);

		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (pbBarraProgreso.getValue() < 100) {
					pbBarraProgreso.setValue(pbBarraProgreso.getValue() + 1);

				} else {
					timer.stop();
					iniciarSistema(); // Abre el programa principal
				}
			}
		});
		timer.start();
	}

	public void iniciarSistema() {
		// Declara y crea el JFrame Principal
		Sistema principal = new Sistema();
		principal.setEmpleadoActual(empleado);

		// Finaliza la ventana de Login
		this.dispose();

		// Muestra mensaje de bienvenida
		Mensaje.mensajeInformacion(this,
				"Bienvenido al Sistema: " + empleado.getNombres() + " " + empleado.getApellidos());

		// Hace visible el JFrame Principal
		principal.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.txtPassword) {
			actionPerformedTxtPassword(e);
		}
		if (e.getSource() == this.txtUsuario) {
			actionPerformedTxtUsuario(e);
		}
		if (e.getSource() == this.btnSalir) {
			actionPerformedBtnSalir(e);
		}
		if (e.getSource() == this.btnIngresar) {
			actionPerformedBtnIngresar(e);
		}
	}

	protected void actionPerformedBtnIngresar(ActionEvent e) {
		ingresar();
	}

	protected void actionPerformedBtnSalir(ActionEvent e) {
		salir();
	}

	protected void actionPerformedTxtUsuario(ActionEvent e) {
		this.txtPassword.requestFocus();
	}

	protected void actionPerformedTxtPassword(ActionEvent e) {
		ingresar();
	}
}
