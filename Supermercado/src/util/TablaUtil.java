package util;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TablaUtil {
	public static void centrarCelda(JTable table, int columna) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(columna).setCellRenderer(dtcr);
	}
	
	public static void izquierdaCelda(JTable table, int columna) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.LEFT);
		table.getColumnModel().getColumn(columna).setCellRenderer(dtcr);
	}
	
	public static void derechaCelda(JTable table, int columna) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		table.getColumnModel().getColumn(columna).setCellRenderer(dtcr);
	}
}
