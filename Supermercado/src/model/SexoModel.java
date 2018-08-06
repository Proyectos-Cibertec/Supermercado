package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Sexo;
import util.Conexion;

public class SexoModel {
	public List<Sexo> listarSexo() {
		List<Sexo> sexos = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT S.idSexo, S.descripcion FROM sexo S ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Sexo sexo = null;

			while (rs.next()) {
				sexo = new Sexo();
				sexo.setIdSexo(rs.getString(1));
				sexo.setDescripcion(rs.getString(2));

				sexos.add(sexo);
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

		return sexos;
	}

	public int insertarSexo(Sexo sexo) {
		int salida = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "INSERT INTO sexo VALUES(?,?)";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, sexo.getIdSexo());
			pstm.setString(2, sexo.getDescripcion());

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

	public int eliminarSexo(String idSexo) {
		int eliminados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "DELETE FROM sexo WHERE idSexo=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, idSexo);

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

	public int actualizarSexo(Sexo sexo) {
		int actualizados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "UPDATE sexo SET descripcion=? WHERE idSexo=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, sexo.getDescripcion());
			pstm.setString(2, sexo.getIdSexo());

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
	public String getUltimoCodigoSexo() {
		String ultimoId = "";

		// Si hay datos registradas
		List<Sexo> sexos = this.listarSexo();
		int nroSexos = sexos.size();
		if (sexos.size() > 0) {
			// Se obtiene el último código registrado
			ultimoId = sexos.get(nroSexos - 1).getIdSexo();
		}

		return ultimoId;
	}
	
	// Obtiene el último código. Si no hay registros, obtiene el String vacío
	public String getUltimoCodigoSexo2() {
		String ultimoId = "";

		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT S.idSexo FROM sexo S ORDER BY 1 DESC LIMIT 1"; // MySQL

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

	public String generarCodigoSexo() {
		// String idSexo = this.getUltimoCodigoSexo();
		String idSexo = this.getUltimoCodigoSexo2();
		int numero;

		if (idSexo.length() == 0) {
			numero = 1;

		} else {
			numero = Integer.parseInt(idSexo.substring(1));
			numero++;
		}

		idSexo = "S" + String.format("%02d", numero);

		return idSexo;
	}

	// Obtiene el Sexo por Descripcion
	public Sexo getSexoXDescripcion(String descripcion) {
		List<Sexo> sexos = this.listarSexo();
		Sexo sexo = null;

		for (Sexo s : sexos) {
			if (s.getDescripcion().equalsIgnoreCase(descripcion)) {
				sexo = s;
				break;
			}
		}

		return sexo;
	}
}
