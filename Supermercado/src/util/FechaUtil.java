package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaUtil {
	
	public static void main(String[] args) {
	}
	
	// Obtiene la fecha actual formateada dd/MM/yyyy
	public static Date today() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaActual = new Date(); // Obtiene fecha actual
		return FechaUtil.stringToDate(sdf.format(fechaActual));
	}
	
	// Convierte un dato de tipo Date a String en formato yyyy-MM-dd válido para base de datos
	public static String dateToString(Date fecha) {
		if (fecha == null) {
			return null;
		}
		
		String strFecha = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		strFecha = sdf.format(fecha);
		
		return strFecha;
	}
	
	// Recibe una String en formato "yyyy-MM-dd" y retorna "dd/MM/yyyy"
	public static String formatoDeFechaClasico(String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd"); 
		String fechaFormateada = "";
		try {
			fechaFormateada = sdf.format(sdf2.parse(fecha));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fechaFormateada;
	}
	
	// Convierte un dato de tipo String cuyo format es: "dd/MM/yyyy" a Date
	public static Date stringToDate(String fecha) {
		Date dateFecha = null;
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			dateFecha = sdf.parse(fecha);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		
		return dateFecha;
	}
}
