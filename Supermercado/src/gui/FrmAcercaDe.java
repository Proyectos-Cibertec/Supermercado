package gui;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.border.EtchedBorder;

public class FrmAcercaDe extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	// Declaración de variables
	private final JPanel contentPanel = new JPanel();
	private JLabel lblAutores;
	private JLabel lblGuisadoMenaLuis;
	private JButton btnCerrar;
	private JLabel lblIconoPrincipal;
	private JLabel lblPrograma;
	private JLabel lblDesarrolladoEnEl;
	private JSeparator separator;
	private JLabel lblConWindowsbuilder;
	private JLabel lblGestorDeBases;
	private JLabel lblCurso;
	private JLabel lblLenguajeDeProgramacin;
	private JLabel lblDocente;
	private JLabel lblJacintoGutarraJorge;
	private JLabel lblNewLabel;
	private JPanel panel;
	private JPanel panel_1;

	// Lanza la aplicación
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			FrmAcercaDe dialog = new FrmAcercaDe();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Crea la GUI
	public FrmAcercaDe() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FrmAcercaDe.class.getResource("/iconos/acercaDe.png")));
		setModal(true);
		setResizable(false);
		setTitle("Acerca de Supermercado");
		setBounds(100, 100, 588, 344);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(UIManager.getColor("Button.background"));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		btnCerrar = new JButton("CERRAR");
		this.btnCerrar.setIcon(new ImageIcon(FrmAcercaDe.class.getResource("/iconos/btnSalir.png")));
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.addActionListener(this);
		btnCerrar.setBounds(457, 265, 115, 40);
		contentPanel.add(btnCerrar);
		
		lblPrograma = new JLabel("SISTEMA DE VENTAS Y ALMAC\u00C9N");
		lblPrograma.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrograma.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblPrograma.setBounds(16, 11, 556, 29);
		contentPanel.add(lblPrograma);
		
		lblDesarrolladoEnEl = new JLabel("Desarrollado en el IDE Eclipse Mars 2.0 con WindowBuilder");
		lblDesarrolladoEnEl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDesarrolladoEnEl.setBounds(185, 58, 387, 23);
		contentPanel.add(lblDesarrolladoEnEl);
		
		lblConWindowsbuilder = new JLabel("Lenguaje de programaci\u00F3n Java (jdk1.8.0)");
		lblConWindowsbuilder.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblConWindowsbuilder.setBounds(185, 81, 300, 23);
		contentPanel.add(lblConWindowsbuilder);
		
		separator = new JSeparator();
		separator.setBounds(16, 252, 556, 2);
		contentPanel.add(separator);
		
		this.lblGestorDeBases = new JLabel("Gestor de Bases de Datos: MySQL");
		this.lblGestorDeBases.setFont(new Font("Tahoma", Font.PLAIN, 13));
		this.lblGestorDeBases.setBounds(185, 106, 300, 23);
		contentPanel.add(this.lblGestorDeBases);
		
		this.lblNewLabel = new JLabel("");
		this.lblNewLabel.setIcon(new ImageIcon(FrmAcercaDe.class.getResource("/iconos/logo_cibertec.png")));
		this.lblNewLabel.setBounds(16, 252, 207, 51);
		contentPanel.add(this.lblNewLabel);
		
		this.panel = new JPanel();
		this.panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panel.setBounds(185, 139, 387, 102);
		contentPanel.add(this.panel);
		this.panel.setLayout(null);
		
		lblAutores = new JLabel("Autor:");
		this.lblAutores.setBounds(28, 11, 61, 23);
		this.panel.add(this.lblAutores);
		lblAutores.setForeground(Color.DARK_GRAY);
		lblAutores.setHorizontalTextPosition(SwingConstants.CENTER);
		lblAutores.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutores.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblAutores.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		lblGuisadoMenaLuis = new JLabel("GUISADO MENA, LUIS ALBERTO");
		this.lblGuisadoMenaLuis.setBounds(98, 11, 243, 23);
		this.panel.add(this.lblGuisadoMenaLuis);
		lblGuisadoMenaLuis.setForeground(new Color(6, 64, 101));
		lblGuisadoMenaLuis.setHorizontalAlignment(SwingConstants.LEFT);
		lblGuisadoMenaLuis.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		this.lblCurso = new JLabel("Curso:");
		this.lblCurso.setBounds(28, 33, 61, 23);
		this.panel.add(this.lblCurso);
		this.lblCurso.setHorizontalTextPosition(SwingConstants.CENTER);
		this.lblCurso.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblCurso.setForeground(Color.DARK_GRAY);
		this.lblCurso.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.lblCurso.setAlignmentX(0.5f);
		
		this.lblLenguajeDeProgramacin = new JLabel("LENGUAJE DE PROGRAMACI\u00D3N I");
		this.lblLenguajeDeProgramacin.setBounds(98, 33, 243, 23);
		this.panel.add(this.lblLenguajeDeProgramacin);
		this.lblLenguajeDeProgramacin.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblLenguajeDeProgramacin.setForeground(new Color(6, 64, 101));
		this.lblLenguajeDeProgramacin.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		this.lblDocente = new JLabel("Docente:");
		this.lblDocente.setBounds(28, 57, 61, 23);
		this.panel.add(this.lblDocente);
		this.lblDocente.setHorizontalTextPosition(SwingConstants.CENTER);
		this.lblDocente.setHorizontalAlignment(SwingConstants.RIGHT);
		this.lblDocente.setForeground(Color.DARK_GRAY);
		this.lblDocente.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.lblDocente.setAlignmentX(0.5f);
		
		this.lblJacintoGutarraJorge = new JLabel("JACINTO GUTARRA, JORGE LORENZO");
		this.lblJacintoGutarraJorge.setBounds(98, 57, 269, 23);
		this.panel.add(this.lblJacintoGutarraJorge);
		this.lblJacintoGutarraJorge.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblJacintoGutarraJorge.setForeground(new Color(6, 64, 101));
		this.lblJacintoGutarraJorge.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		this.panel_1 = new JPanel();
		this.panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panel_1.setBackground(new Color(255, 255, 255));
		this.panel_1.setBounds(10, 63, 165, 178);
		contentPanel.add(this.panel_1);
		this.panel_1.setLayout(null);
		
		lblIconoPrincipal = new JLabel("");
		this.lblIconoPrincipal.setBounds(10, 11, 145, 156);
		this.panel_1.add(this.lblIconoPrincipal);
		lblIconoPrincipal.setBackground(Color.WHITE);
		lblIconoPrincipal.setIcon(new ImageIcon(FrmAcercaDe.class.getResource("/iconos/iconoPrincipalGrande.png")));
		agregarContenido();
	}
	
	// Direcciona eventos de tipo ActionEvent
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCerrar) {
			actionPerformedBtnCerrar(e);
		}
	}
	
	// Procesa la pulsación del botón Cerrar
	protected void actionPerformedBtnCerrar(ActionEvent e) {
		dispose(); // Cierra el cuadro de diálogo
	}
	
	void agregarContenido() {
	}
}
