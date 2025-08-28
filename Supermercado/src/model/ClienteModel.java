package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Cliente;
import entidad.Distrito;
import entidad.Sexo;
import util.Conexion;
import util.Foto;

public class ClienteModel {

	public List<Cliente> listarClientexCodigo(String idCliente) {
		List<Cliente> clientes = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT C.*, S.descripcion, D.nombre FROM cliente C "
					+ "INNER JOIN sexo S ON S.idSexo = C.idSexo "
					+ "INNER JOIN distrito D ON D.idDistrito = C.idDistrito WHERE C.idCliente LIKE ? "
					+ "ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + idCliente + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Cliente cliente = null;
			Sexo sexo = null;
			Distrito distrito = null;

			while (rs.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(rs.getString(1));
				cliente.setDni(rs.getString(2));
				cliente.setNombres(rs.getString(3));
				cliente.setApellidos(rs.getString(4));
				cliente.setFechaNacimiento(rs.getString(5));
				cliente.setDireccion(rs.getString(6));
				cliente.setTelefono(rs.getString(7));
				cliente.setEmail(rs.getString(8));

				// Foto
				Foto foto = new Foto();
				foto.setInputStream(rs.getBinaryStream(9));
				cliente.setFoto(foto);

				sexo = new Sexo();
				sexo.setIdSexo(rs.getString(10));
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(11));
				sexo.setDescripcion(rs.getString(12));
				distrito.setNombre(rs.getString(13));

				cliente.setSexo(sexo);
				cliente.setDistrito(distrito);

				clientes.add(cliente);
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

		return clientes;
	}
	
	public List<Cliente> listarClientexDni(String dni) {
		List<Cliente> clientes = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT C.*, S.descripcion, D.nombre FROM cliente C "
					+ "INNER JOIN sexo S ON S.idSexo = C.idSexo "
					+ "INNER JOIN distrito D ON D.idDistrito = C.idDistrito WHERE C.dni LIKE ? "
					+ "ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + dni + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Cliente cliente = null;
			Sexo sexo = null;
			Distrito distrito = null;

			while (rs.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(rs.getString(1));
				cliente.setDni(rs.getString(2));
				cliente.setNombres(rs.getString(3));
				cliente.setApellidos(rs.getString(4));
				cliente.setFechaNacimiento(rs.getString(5));
				cliente.setDireccion(rs.getString(6));
				cliente.setTelefono(rs.getString(7));
				cliente.setEmail(rs.getString(8));

				// Foto
				Foto foto = new Foto();
				foto.setInputStream(rs.getBinaryStream(9));
				cliente.setFoto(foto);

				sexo = new Sexo();
				sexo.setIdSexo(rs.getString(10));
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(11));
				sexo.setDescripcion(rs.getString(12));
				distrito.setNombre(rs.getString(13));

				cliente.setSexo(sexo);
				cliente.setDistrito(distrito);

				clientes.add(cliente);
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

		return clientes;
	}
	
	public List<Cliente> listarClientexNombres(String nombres) {
		List<Cliente> clientes = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT C.*, S.descripcion, D.nombre FROM cliente C "
					+ "INNER JOIN sexo S ON S.idSexo = C.idSexo "
					+ "INNER JOIN distrito D ON D.idDistrito = C.idDistrito WHERE C.nombres LIKE ? "
					+ "ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + nombres + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Cliente cliente = null;
			Sexo sexo = null;
			Distrito distrito = null;

			while (rs.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(rs.getString(1));
				cliente.setDni(rs.getString(2));
				cliente.setNombres(rs.getString(3));
				cliente.setApellidos(rs.getString(4));
				cliente.setFechaNacimiento(rs.getString(5));
				cliente.setDireccion(rs.getString(6));
				cliente.setTelefono(rs.getString(7));
				cliente.setEmail(rs.getString(8));

				// Foto
				Foto foto = new Foto();
				foto.setInputStream(rs.getBinaryStream(9));
				cliente.setFoto(foto);

				sexo = new Sexo();
				sexo.setIdSexo(rs.getString(10));
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(11));
				sexo.setDescripcion(rs.getString(12));
				distrito.setNombre(rs.getString(13));

				cliente.setSexo(sexo);
				cliente.setDistrito(distrito);

				clientes.add(cliente);
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

		return clientes;
	}
	
	public List<Cliente> listarClientexApellidos(String apellidos) {
		List<Cliente> clientes = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT C.*, S.descripcion, D.nombre FROM cliente C "
					+ "INNER JOIN sexo S ON S.idSexo = C.idSexo "
					+ "INNER JOIN distrito D ON D.idDistrito = C.idDistrito WHERE C.apellidos LIKE ? "
					+ "ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + apellidos + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Cliente cliente = null;
			Sexo sexo = null;
			Distrito distrito = null;

			while (rs.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(rs.getString(1));
				cliente.setDni(rs.getString(2));
				cliente.setNombres(rs.getString(3));
				cliente.setApellidos(rs.getString(4));
				cliente.setFechaNacimiento(rs.getString(5));
				cliente.setDireccion(rs.getString(6));
				cliente.setTelefono(rs.getString(7));
				cliente.setEmail(rs.getString(8));

				// Foto
				Foto foto = new Foto();
				foto.setInputStream(rs.getBinaryStream(9));
				cliente.setFoto(foto);

				sexo = new Sexo();
				sexo.setIdSexo(rs.getString(10));
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(11));
				sexo.setDescripcion(rs.getString(12));
				distrito.setNombre(rs.getString(13));

				cliente.setSexo(sexo);
				cliente.setDistrito(distrito);

				clientes.add(cliente);
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

		return clientes;
	}
	
	public List<Cliente> listarClientexDireccion(String direccion) {
		List<Cliente> clientes = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT C.*, S.descripcion, D.nombre FROM cliente C "
					+ "INNER JOIN sexo S ON S.idSexo = C.idSexo "
					+ "INNER JOIN distrito D ON D.idDistrito = C.idDistrito WHERE C.direccion LIKE ? "
					+ "ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + direccion + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Cliente cliente = null;
			Sexo sexo = null;
			Distrito distrito = null;

			while (rs.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(rs.getString(1));
				cliente.setDni(rs.getString(2));
				cliente.setNombres(rs.getString(3));
				cliente.setApellidos(rs.getString(4));
				cliente.setFechaNacimiento(rs.getString(5));
				cliente.setDireccion(rs.getString(6));
				cliente.setTelefono(rs.getString(7));
				cliente.setEmail(rs.getString(8));

				// Foto
				Foto foto = new Foto();
				foto.setInputStream(rs.getBinaryStream(9));
				cliente.setFoto(foto);

				sexo = new Sexo();
				sexo.setIdSexo(rs.getString(10));
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(11));
				sexo.setDescripcion(rs.getString(12));
				distrito.setNombre(rs.getString(13));

				cliente.setSexo(sexo);
				cliente.setDistrito(distrito);

				clientes.add(cliente);
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

		return clientes;
	}
	
	public List<Cliente> listarClientexNombreDistrito(String nombreDistrito) {
		List<Cliente> clientes = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT C.*, S.descripcion, D.nombre FROM cliente C "
					+ "INNER JOIN sexo S ON S.idSexo = C.idSexo "
					+ "INNER JOIN distrito D ON D.idDistrito = C.idDistrito WHERE D.nombre LIKE ? "
					+ "ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + nombreDistrito + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Cliente cliente = null;
			Sexo sexo = null;
			Distrito distrito = null;

			while (rs.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(rs.getString(1));
				cliente.setDni(rs.getString(2));
				cliente.setNombres(rs.getString(3));
				cliente.setApellidos(rs.getString(4));
				cliente.setFechaNacimiento(rs.getString(5));
				cliente.setDireccion(rs.getString(6));
				cliente.setTelefono(rs.getString(7));
				cliente.setEmail(rs.getString(8));

				// Foto
				Foto foto = new Foto();
				foto.setInputStream(rs.getBinaryStream(9));
				cliente.setFoto(foto);

				sexo = new Sexo();
				sexo.setIdSexo(rs.getString(10));
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(11));
				sexo.setDescripcion(rs.getString(12));
				distrito.setNombre(rs.getString(13));

				cliente.setSexo(sexo);
				cliente.setDistrito(distrito);

				clientes.add(cliente);
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

		return clientes;
	}
	
	public List<Cliente> listarClientexEmail(String email) {
		List<Cliente> clientes = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT C.*, S.descripcion, D.nombre FROM cliente C "
					+ "INNER JOIN sexo S ON S.idSexo = C.idSexo "
					+ "INNER JOIN distrito D ON D.idDistrito = C.idDistrito WHERE C.email LIKE ? "
					+ "ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + email + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Cliente cliente = null;
			Sexo sexo = null;
			Distrito distrito = null;

			while (rs.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(rs.getString(1));
				cliente.setDni(rs.getString(2));
				cliente.setNombres(rs.getString(3));
				cliente.setApellidos(rs.getString(4));
				cliente.setFechaNacimiento(rs.getString(5));
				cliente.setDireccion(rs.getString(6));
				cliente.setTelefono(rs.getString(7));
				cliente.setEmail(rs.getString(8));

				// Foto
				Foto foto = new Foto();
				foto.setInputStream(rs.getBinaryStream(9));
				cliente.setFoto(foto);

				sexo = new Sexo();
				sexo.setIdSexo(rs.getString(10));
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(11));
				sexo.setDescripcion(rs.getString(12));
				distrito.setNombre(rs.getString(13));

				cliente.setSexo(sexo);
				cliente.setDistrito(distrito);

				clientes.add(cliente);
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

		return clientes;
	}

	public List<Cliente> listarCliente() {
		List<Cliente> clientes = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT C.*, S.descripcion, D.nombre FROM cliente C "
					+ "INNER JOIN sexo S ON S.idSexo = C.idSexo "
					+ "INNER JOIN distrito D ON D.idDistrito = C.idDistrito ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Cliente cliente = null;
			Sexo sexo = null;
			Distrito distrito = null;

			while (rs.next()) {
				cliente = new Cliente();
				cliente.setIdCliente(rs.getString(1));
				cliente.setDni(rs.getString(2));
				cliente.setNombres(rs.getString(3));
				cliente.setApellidos(rs.getString(4));
				cliente.setFechaNacimiento(rs.getString(5));
				cliente.setDireccion(rs.getString(6));
				cliente.setTelefono(rs.getString(7));
				cliente.setEmail(rs.getString(8));

				// Foto
				Foto foto = new Foto();
				foto.setInputStream(rs.getBinaryStream(9));
				cliente.setFoto(foto);

				sexo = new Sexo();
				sexo.setIdSexo(rs.getString(10));
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(11));
				sexo.setDescripcion(rs.getString(12));
				distrito.setNombre(rs.getString(13));

				cliente.setSexo(sexo);
				cliente.setDistrito(distrito);

				clientes.add(cliente);
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

		return clientes;
	}

	public int insertarCliente(Cliente cliente) {
		int salida = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "INSERT INTO cliente VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, cliente.getIdCliente());
			pstm.setString(2, cliente.getDni());
			pstm.setString(3, cliente.getNombres());
			pstm.setString(4, cliente.getApellidos());
			pstm.setString(5, cliente.getFechaNacimiento());
			pstm.setString(6, cliente.getDireccion());
			pstm.setString(7, cliente.getTelefono());
			pstm.setString(8, cliente.getEmail());
			pstm.setBlob(9, cliente.getFoto().getInputStream()); // foto
			pstm.setString(10, cliente.getSexo().getIdSexo());
			pstm.setString(11, cliente.getDistrito().getIdDistrito());

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

	public int eliminarCliente(String idCliente) {
		int eliminados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "DELETE FROM cliente WHERE idCliente=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, idCliente);

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

	public int actualizarCliente(Cliente cliente) {
		int actualizados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "UPDATE cliente SET dni=?, nombres=?, apellidos=?, fechaNacimiento=?, direccion=?, "
					+ "telefono=?, email=?, foto=?, idSexo=?, idDistrito=? WHERE idCliente=?";
			pstm = conexion.prepareStatement(sentenciaSQL);

			pstm.setString(1, cliente.getDni());
			pstm.setString(2, cliente.getNombres());
			pstm.setString(3, cliente.getApellidos());
			pstm.setString(4, cliente.getFechaNacimiento());
			pstm.setString(5, cliente.getDireccion());
			pstm.setString(6, cliente.getTelefono());
			pstm.setString(7, cliente.getEmail());
			pstm.setBlob(8, cliente.getFoto().getInputStream()); // foto
			pstm.setString(9, cliente.getSexo().getIdSexo());
			pstm.setString(10, cliente.getDistrito().getIdDistrito());
			pstm.setString(11, cliente.getIdCliente());

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

		List<Cliente> clientes = this.listarCliente();
		for (Cliente cliente : clientes) {
			if (cliente.getIdCliente().equalsIgnoreCase(codigo)) {
				foto = cliente.getFoto();
				break;
			}
		}

		return foto;
	}
	
	// Obtiene un Cliente a partir de su código
	public Cliente getCliente(String idCliente) {
		Cliente cliente = null;
		List<Cliente> clientes = this.listarCliente();
		
		for (Cliente c : clientes) {
			if (c.getIdCliente().equalsIgnoreCase(idCliente)) {
				cliente = c;
				break;
			}
		}
		
		return cliente;
	}

	// Obtiene el último código. Si no hay registros, obtiene el String vacío
	public String getUltimoCodigoCliente() {
		String ultimoId = "";

		// Si hay categorías registradas
		List<Cliente> clientes = this.listarCliente();
		int nroClientes = clientes.size();
		if (clientes.size() > 0) {
			// Se obtiene el último código registrado
			ultimoId = clientes.get(nroClientes - 1).getIdCliente();
		}

		return ultimoId;
	}

	// Obtiene el último código. Si no hay registros, obtiene el String vacío
	public String getUltimoCodigoCliente2() {
		String ultimoId = "";

		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			/*
			 * SQLSERVER: Predicado TOP String sentenciaSQL =
			 * "SELECT TOP 1 C.idCliente FROM cliente C ORDER BY 1 DESC";
			 */
			String sentenciaSQL = "SELECT C.idCliente FROM cliente C ORDER BY 1 DESC LIMIT 1"; // MySQL

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

	public String generarCodigoCliente() {
		// String idCliente = this.getUltimoCodigoCliente();
		String idCliente = this.getUltimoCodigoCliente2();
		int numero;

		if (idCliente.length() == 0) {
			numero = 1;

		} else {
			numero = Integer.parseInt(idCliente.substring(1));
			numero++;
		}

		idCliente = "C" + String.format("%07d", numero);

		return idCliente;
	}
}
