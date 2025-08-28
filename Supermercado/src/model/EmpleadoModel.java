package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Cargo;
import entidad.Distrito;
import entidad.Empleado;
import entidad.Sexo;
import util.Conexion;
import util.Foto;

public class EmpleadoModel {

	public List<Empleado> listarEmpleado() {
		List<Empleado> empleados = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT E.*, S.descripcion, C.descripcion, D.nombre FROM empleado E "
					+ "INNER JOIN sexo S ON S.idSexo = E.idSexo INNER JOIN cargo C ON C.idCargo = E.idCargo "
					+ "INNER JOIN distrito D ON D.idDistrito = E.idDistrito ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Empleado empleado = null;
			Sexo sexo = null;
			Cargo cargo = null;
			Distrito distrito = null;

			while (rs.next()) {
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

				// Foto
				Foto foto = new Foto();
				foto.setInputStream(rs.getBinaryStream(12));
				empleado.setFoto(foto);

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

				empleados.add(empleado);
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

		return empleados;
	}

	public int insertarEmpleado(Empleado empleado) {
		int salida = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "INSERT INTO empleado VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; // Foto
																								// ?
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, empleado.getIdEmpleado());
			pstm.setString(2, empleado.getDni());
			pstm.setString(3, empleado.getNombres());
			pstm.setString(4, empleado.getApellidos());
			pstm.setString(5, empleado.getFechaNacimiento());
			pstm.setString(6, empleado.getDireccion());
			pstm.setString(7, empleado.getEmail());
			pstm.setString(8, empleado.getTelefono());
			pstm.setString(9, empleado.getFechaContratacion());
			pstm.setString(10, empleado.getUsuario());
			pstm.setString(11, empleado.getPassword());
			pstm.setBlob(12, empleado.getFoto().getInputStream()); // foto
			pstm.setString(13, empleado.getSexo().getIdSexo());
			pstm.setString(14, empleado.getCargo().getIdCargo());
			pstm.setString(15, empleado.getDistrito().getIdDistrito());

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

	public int eliminarEmpleado(String idEmpleado) {
		int eliminados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "DELETE FROM empleado WHERE idEmpleado=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, idEmpleado);

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

	public int actualizarEmpleado(Empleado empleado) {
		int actualizados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "UPDATE empleado SET dni=?, nombres=?, apellidos=?, fechaNacimiento=?, direccion=?, "
					+ "email=?, telefono=?, fechaContratacion=?, usuario=?, password=?, foto=?, idSexo=?, idCargo=?, idDistrito=? "
					+ "WHERE idEmpleado=?";
			pstm = conexion.prepareStatement(sentenciaSQL);

			pstm.setString(1, empleado.getDni());
			pstm.setString(2, empleado.getNombres());
			pstm.setString(3, empleado.getApellidos());
			pstm.setString(4, empleado.getFechaNacimiento());
			pstm.setString(5, empleado.getDireccion());
			pstm.setString(6, empleado.getEmail());
			pstm.setString(7, empleado.getTelefono());
			pstm.setString(8, empleado.getFechaContratacion());
			pstm.setString(9, empleado.getUsuario());
			pstm.setString(10, empleado.getPassword());
			pstm.setBlob(11, empleado.getFoto().getInputStream()); // foto
			pstm.setString(12, empleado.getSexo().getIdSexo());
			pstm.setString(13, empleado.getCargo().getIdCargo());
			pstm.setString(14, empleado.getDistrito().getIdDistrito());
			pstm.setString(15, empleado.getIdEmpleado());

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

	// Obtiene la foto a partir del código
	public Foto getFoto(String codigo) {
		Foto foto = null;

		List<Empleado> empleados = this.listarEmpleado();
		for (Empleado empleado : empleados) {
			if (empleado.getIdEmpleado().equalsIgnoreCase(codigo)) {
				foto = empleado.getFoto();
				break;
			}
		}

		return foto;
	}
	
	// Obtiene un Empleado a partir de su código
	public Empleado getEmpleado(String idEmpleado) {
		Empleado empleado = null;
		List<Empleado> empleados = this.listarEmpleado();
		
		for (Empleado e : empleados) {
			if (e.getIdEmpleado().equalsIgnoreCase(idEmpleado)) {
				empleado = e;
				break;
			}
		}
		
		return empleado;
	}

	// Obtiene el último código. Si no hay registros, obtiene el String vacío
	public String getUltimoCodigoEmpleado() {
		String ultimoId = "";

		// Si hay categorías registradas
		List<Empleado> empleados = this.listarEmpleado();
		int nroEmpleados = empleados.size();
		if (empleados.size() > 0) {
			// Se obtiene el último código registrado
			ultimoId = empleados.get(nroEmpleados - 1).getIdEmpleado();
		}

		return ultimoId;
	}

	// Obtiene el último código. Si no hay registros, obtiene el String vacío
	public String getUltimoCodigoEmpleado2() {
		String ultimoId = "";

		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT E.idEmpleado FROM empleado E ORDER BY 1 DESC LIMIT 1"; // MySQL

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

	public String generarCodigoEmpleado() {
		// String idEmpleado = this.getUltimoCodigoEmpleado();
		String idEmpleado = this.getUltimoCodigoEmpleado2();
		int numero;

		if (idEmpleado.length() == 0) {
			numero = 1;

		} else {
			numero = Integer.parseInt(idEmpleado.substring(1));
			numero++;
		}

		idEmpleado = "E" + String.format("%07d", numero);

		return idEmpleado;
	}
}
