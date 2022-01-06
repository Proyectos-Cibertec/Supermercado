package util;

import java.util.HashMap;

import entidad.Parametro;
import model.ParametrosModel;

public class Parametros {
	public static HashMap<String, Parametro> parametrosDB = new HashMap<>();
	public static double IGV = Constantes.IGV;
	
	static {
		Parametros.cargarParametrosDB();
		Parametros.asignarIGV();
	}
	
	public static void cargarParametrosDB() {
		Parametros.parametrosDB = new ParametrosModel().listarParametros();
		System.out.println("Lista de parámetros: " + Parametros.parametrosDB);
	}
	
	public static void asignarIGV() {
		Parametros.IGV = Constantes.IGV;
		String valor = "";
		try {
			Parametro parametro = Parametros.parametrosDB.get(Constantes.PARAM_IGV_PERU);
			if (parametro != null) {
				valor = parametro.getValor();
				Parametros.IGV = Double.parseDouble(valor);				
			} else {
				System.out.println("Parámetro con nombre \"" + Constantes.PARAM_IGV_PERU + "\" no existe en base de datos.");
				System.out.println("Valor predeterminado de IGV: " + Parametros.IGV);	
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Error al convertir el valor \"" + valor + "\" en double: " + e.getMessage());
			System.out.println("Valor predeterminado de IGV: " + Parametros.IGV);
			e.printStackTrace();
			
		} catch (Exception e) {
			System.out.println("Ocurrió un error al obtener el valor del igv: " + e.getMessage());
			System.out.println("Valor predeterminado de IGV: " + Parametros.IGV);
			e.printStackTrace();
			
		}
	}
}
