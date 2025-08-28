package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Cargo;
import util.Conexion;

public class CargoModel {

	public List<Cargo> listarCargo() {
		List<Cargo> cargos = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT C.idCargo, C.descripcion FROM cargo C ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Cargo cargo = null;

			while (rs.next()) {
				cargo = new Cargo();
				cargo.setIdCargo(rs.getString(1));
				cargo.setDescripcion(rs.getString(2));

				cargos.add(cargo);
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

		return cargos;
	}

	public int insertarCargo(Cargo cargo) {
		int salida = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "INSERT INTO cargo VALUES(?,?)";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, cargo.getIdCargo());
			pstm.setString(2, cargo.getDescripcion());

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

	public int eliminarCargo(String idCargo) {
		int eliminados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "DELETE FROM cargo WHERE idCargo=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, idCargo);

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

	public int actualizarCargo(Cargo cargo) {
		int actualizados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "UPDATE cargo SET descripcion=? WHERE idCargo=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, cargo.getDescripcion());
			pstm.setString(2, cargo.getIdCargo());

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
	public String getUltimoCodigoCargo() {
		String ultimoId = "";

		// Si hay datos registradas
		List<Cargo> cargos = this.listarCargo();
		int nroCargos = cargos.size();
		if (cargos.size() > 0) {
			// Se obtiene el último código registrado
			ultimoId = cargos.get(nroCargos - 1).getIdCargo();
		}

		return ultimoId;
	}

	// Obtiene el último código. Si no hay registros, obtiene el String vacío
	public String getUltimoCodigoCargo2() {
		String ultimoId = "";

		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT C.idCargo FROM cargo C ORDER BY 1 DESC LIMIT 1"; // MySQL

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

	public String generarCodigoCargo() {
		// String idCargo = this.getUltimoCodigoCargo();
		String idCargo = this.getUltimoCodigoCargo2();
		int numero;

		if (idCargo.length() == 0) {
			numero = 1;

		} else {
			numero = Integer.parseInt(idCargo.substring(3));
			numero++;
		}

		idCargo = "CAR" + String.format("%03d", numero);

		return idCargo;
	}

	// Obtiene el Cargo por Descripcion
	public Cargo getCargoXDescripcion(String descripcion) {
		List<Cargo> cargos = this.listarCargo();
		Cargo cargo = null;

		for (Cargo c : cargos) {
			if (c.getDescripcion().equalsIgnoreCase(descripcion)) {
				cargo = c;
				break;
			}
		}

		return cargo;
	}

	// Obtiene el cargo por código de Cargo
	public Cargo getCargo(String idCargo) {
		List<Cargo> cargos = this.listarCargo();
		Cargo cargo = null;

		for (Cargo c : cargos) {
			if (c.getIdCargo().equalsIgnoreCase(idCargo)) {
				cargo = c;
				break;
			}
		}

		return cargo;
	}
}
