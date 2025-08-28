package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Caja;
import util.Conexion;

public class CajaModel {

	public List<Caja> listarCaja() {
		List<Caja> cajas = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT C.idCaja, C.descripcion FROM caja C ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Caja caja = null;

			while (rs.next()) {
				caja = new Caja();
				caja.setIdCaja(rs.getString(1));
				caja.setDescripcion(rs.getString(2));

				cajas.add(caja);
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

		return cajas;
	}

	public int insertarCaja(Caja caja) {
		int salida = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "INSERT INTO caja VALUES(?,?)";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, caja.getIdCaja());
			pstm.setString(2, caja.getDescripcion());

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

	public int eliminarCaja(String idCaja) {
		int eliminados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "DELETE FROM caja WHERE idCaja=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, idCaja);

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

	public int actualizarCaja(Caja caja) {
		int actualizados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "UPDATE caja SET descripcion=? WHERE idCaja=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, caja.getDescripcion());
			pstm.setString(2, caja.getIdCaja());

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
	public String getUltimoCodigoCaja() {
		String ultimoId = "";

		// Si hay datos registradas
		List<Caja> cajas = this.listarCaja();
		int nroCajas = cajas.size();
		if (cajas.size() > 0) {
			// Se obtiene el último código registrado
			ultimoId = cajas.get(nroCajas - 1).getIdCaja();
		}

		return ultimoId;
	}

	// Obtiene el último código. Si no hay registros, obtiene el String vacío
	public String getUltimoCodigoCaja2() {
		String ultimoId = "";

		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT C.idCaja FROM caja C ORDER BY 1 DESC LIMIT 1"; // MySQL

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

	public String generarCodigoCaja() {
		// String idCaja = this.getUltimoCodigoCaja();
		String idCaja = this.getUltimoCodigoCaja2();
		int numero;

		if (idCaja.length() == 0) {
			numero = 1;

		} else {
			numero = Integer.parseInt(idCaja.substring(3));
			numero++;
		}

		idCaja = "CAJ" + String.format("%03d", numero);

		return idCaja;
	}
}
