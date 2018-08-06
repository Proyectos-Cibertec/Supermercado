package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Distrito;
import util.Conexion;

public class DistritoModel {	
	public List<Distrito> listarDistrito() {
		List<Distrito> distritos = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT D.idDistrito, D.nombre FROM distrito D ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Distrito distrito = null;

			while (rs.next()) {
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(1));
				distrito.setNombre(rs.getString(2));

				distritos.add(distrito);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// Se cierran las conexiones
			try {
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conexion != null)
					conexion.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return distritos;
	}

	public int insertarDistrito(Distrito distrito) {
		int salida = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "INSERT INTO distrito VALUES(?,?)";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, distrito.getIdDistrito());
			pstm.setString(2, distrito.getNombre());

			// Se imprime el SQL
			System.out.println("SQL -> " + pstm);

			// Se ejecuta el SQL y se recibe la cantidad de registrados
			salida = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// Se cierran las conexiones
			try {
				if (pstm != null)
					pstm.close();
				if (conexion != null)
					conexion.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return salida;
	}

	public int eliminarDistrito(String idDistrito) {
		int eliminados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "DELETE FROM distrito WHERE idDistrito=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, idDistrito);

			// Se imprime el SQL
			System.out.println("SQL -> " + pstm);

			// Se ejecuta el SQL y se recibe la cantidad de eliminados
			eliminados = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// Se cierran las conexiones
			try {
				if (pstm != null)
					pstm.close();
				if (conexion != null)
					conexion.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return eliminados;
	}

	public int actualizarDistrito(Distrito distrito) {
		int actualizados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "UPDATE distrito SET nombre=? WHERE idDistrito=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, distrito.getNombre());
			pstm.setString(2, distrito.getIdDistrito());

			// Se imprime el SQL
			System.out.println("SQL -> " + pstm);

			// Se ejecuta el SQL y se recibe la cantidad de actualizados
			actualizados = pstm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// Se cierran las conexiones
			try {
				if (pstm != null)
					pstm.close();
				if (conexion != null)
					conexion.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return actualizados;
	}
	
	// Obtiene el último código. Si no hay registros, obtiene el String vacío
	public String getUltimoCodigoDistrito() {
		String ultimoId = "";
		
		// Si hay datos registradas
		List<Distrito> distritos = this.listarDistrito();
		int nroDistritos = distritos.size();
		if (distritos.size() > 0) {
			// Se obtiene el último código registrado
			ultimoId = distritos.get(nroDistritos - 1).getIdDistrito();
		}
		
		return ultimoId;
	}
	
	// Obtiene el último código. Si no hay registros, obtiene el String vacío
	public String getUltimoCodigoDistrito2() {
		String ultimoId = "";

		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT D.idDistrito FROM distrito D ORDER BY 1 DESC LIMIT 1"; // MySQL

			// Se obtiene el resultado de la base de datos
			pstm = conexion.prepareStatement(sentenciaSQL);
			rs = pstm.executeQuery();

			// Se obtiene el único resultado
			if (rs.next()) {
				ultimoId = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				// Se cierran las conexiones
				if (rs != null)
					rs.close();
				if (pstm != null)
					pstm.close();
				if (conexion != null)
					conexion.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return ultimoId;
	}
	
	public String generarCodigoDistrito() {
		// String idDistrito = this.getUltimoCodigoDistrito();
		String idDistrito = this.getUltimoCodigoDistrito2();
		int numero;
		
		if (idDistrito.length() == 0) {
			numero = 1;
			
		} else {
			numero = Integer.parseInt(idDistrito.substring(1));
			numero++;
		}
		
		idDistrito = "D" + String.format("%03d", numero);
		
		return idDistrito;
	}
	
	// Obtiene el Distrito por nombre
	public Distrito getDistritoXNombre(String nombre) {
		List<Distrito> distritos = this.listarDistrito();
		Distrito distrito = null;
		
		for (Distrito d : distritos) {
			if (d.getNombre().equalsIgnoreCase(nombre)) {
				distrito = d;
				break;
			}
		}
		
		return distrito;
	}
}
