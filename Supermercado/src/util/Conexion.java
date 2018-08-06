package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private final static String DRIVER = "com.mysql.jdbc.Driver";
	private final static String URL_DATABASE = "jdbc:mysql://localhost:3306/supermercado";
	private final static String USUARIO = "root";
	private final static String PASSWORD = "mysql";
	
	public Connection getConexion() {
		Connection conexion = null;
		
		try {
			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(URL_DATABASE, USUARIO, PASSWORD);
			// javax.swing.JOptionPane.showMessageDialog(null, "Conexión OK");
			
		} catch (ClassNotFoundException e) {
			javax.swing.JOptionPane.showMessageDialog(null, "Error en el driver");
			e.printStackTrace();
			
		} catch (SQLException e) {
			javax.swing.JOptionPane.showMessageDialog(null, "Error en la conexión");
			e.printStackTrace();
		}
		
		return conexion;
	}
	
	/* Testing connection
	 * public static void main(String[] args) {
		new Conexion().getConexion();
	}*/
}
