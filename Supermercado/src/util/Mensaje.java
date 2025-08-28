package util;

import java.awt.Component;

import javax.swing.JOptionPane;

public class Mensaje {

	public static void mensaje(Component parentComponent, String mensaje) {
		JOptionPane.showMessageDialog(parentComponent, mensaje);
	}
	
	public static void mensajeInformacion(Component parentComponent, String mensaje) {
		JOptionPane.showMessageDialog(parentComponent, mensaje, Constantes.MSG_TYPE_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static int mensajeConfirmacion(Component parentComponent, String mensaje) {
		return JOptionPane.showConfirmDialog(parentComponent, mensaje, Constantes.MSG_TYPE_CONFIRM, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
	}
	
	public static int mensajeAdvertencia(Component parentComponent, String mensaje) {
		return JOptionPane.showConfirmDialog(parentComponent, mensaje, Constantes.MSG_TYPE_WARNING, JOptionPane.WARNING_MESSAGE);
	}
	
	public static void mensajeError(Component parentComponent, String mensaje) {
		JOptionPane.showMessageDialog(parentComponent, mensaje, Constantes.MSG_TYPE_ERROR, JOptionPane.ERROR_MESSAGE);
	}
}
