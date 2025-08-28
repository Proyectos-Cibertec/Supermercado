package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidad.Cargo;
import entidad.Distrito;
import entidad.Empleado;
import entidad.Sexo;
import util.Conexion;

public class LoginModel {
	
	// Obtiene el Empleado cuyo usuario y password se le pasa como argumento
	public Empleado login(String usuario, String password) {
		Empleado empleado = null;
		Sexo sexo = null;
		Cargo cargo = null;
		Distrito distrito = null;
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			
			String sentenciaSQL = "SELECT E.*, S.descripcion, C.descripcion, D.nombre FROM empleado E "
					+ "INNER JOIN sexo S ON S.idSexo = E.idSexo INNER JOIN cargo C ON C.idCargo = E.idCargo "
					+ "INNER JOIN distrito D ON D.idDistrito = E.idDistrito WHERE E.usuario = ? AND E.password = ?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, usuario);
			pstm.setString(2, password);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			if (rs.next()) {
				empleado = new Empleado();
				empleado.setIdEmpleado(rs.getString(1));
				empleado.setDni(rs.getString(2));
				empleado.setNombres(rs.getString(3));
				empleado.setApellidos(rs.getString(4));
				empleado.setFechaNacimiento(rs.getString(5));
				empleado.setDireccion(rs.getString(6));
				empleado.setEmail(rs.getString(7));
				empleado.setTelefono(rs.getString(8));
				empleado.setFechaContratacion(rs.getString(9));
				empleado.setUsuario(rs.getString(10));
				empleado.setPassword(rs.getString(11));
				// Foto ? 12

				sexo = new Sexo();
				sexo.setIdSexo(rs.getString(13));
				cargo = new Cargo();
				cargo.setIdCargo(rs.getString(14));
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(15));
				sexo.setDescripcion(rs.getString(16));
				cargo.setDescripcion(rs.getString(17));
				distrito.setNombre(rs.getString(18));

				empleado.setSexo(sexo);
				empleado.setCargo(cargo);
				empleado.setDistrito(distrito);
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
		return empleado;
	}
	
	/*public int cantidadAdministradores() {
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int nroAdministradores = 0;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			
			String sentenciaSQL = "SELECT COUNT(*) FROM empleado E WHERE E.idCargo = 'CAR001'";
			pstm = conexion.prepareStatement(sentenciaSQL);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			if (rs.next()) {
				nroAdministradores = rs.getInt(1);
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

		return nroAdministradores;
	}*/
}
