package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Distrito;
import entidad.Proveedor;
import util.Conexion;

public class ProveedorModel {
	
	public List<Proveedor> listarProveedorxCodigo(String idProveedor) {
		List<Proveedor> proveedores = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL	= "SELECT P.*, D.nombre FROM proveedor P " + 
					"INNER JOIN distrito D ON D.idDistrito = P.idDistrito " + 
					"WHERE P.idProveedor LIKE ? ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + idProveedor + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Proveedor proveedor = null;
			Distrito distrito = null;

			while (rs.next()) {
				proveedor = new Proveedor();
				proveedor.setIdProveedor(rs.getString(1));
				proveedor.setRuc(rs.getString(2));
				proveedor.setRazonSocial(rs.getString(3));
				proveedor.setDireccion(rs.getString(4));
				proveedor.setEmail(rs.getString(5));
				proveedor.setTelefono(rs.getString(6));
				proveedor.setFechaRegistro(rs.getString(7));
				proveedor.setNombreRepresentante(rs.getString(8));
				
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(9));
				distrito.setNombre(rs.getString(10));				
				proveedor.setDistrito(distrito);

				proveedores.add(proveedor);
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

		return proveedores;
	}
	
	public List<Proveedor> listarProveedorxRuc(String ruc) {
		List<Proveedor> proveedores = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL	= "SELECT P.*, D.nombre FROM proveedor P " + 
					"INNER JOIN distrito D ON D.idDistrito = P.idDistrito " + 
					"WHERE P.ruc LIKE ? ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + ruc + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Proveedor proveedor = null;
			Distrito distrito = null;

			while (rs.next()) {
				proveedor = new Proveedor();
				proveedor.setIdProveedor(rs.getString(1));
				proveedor.setRuc(rs.getString(2));
				proveedor.setRazonSocial(rs.getString(3));
				proveedor.setDireccion(rs.getString(4));
				proveedor.setEmail(rs.getString(5));
				proveedor.setTelefono(rs.getString(6));
				proveedor.setFechaRegistro(rs.getString(7));
				proveedor.setNombreRepresentante(rs.getString(8));
				
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(9));
				distrito.setNombre(rs.getString(10));				
				proveedor.setDistrito(distrito);

				proveedores.add(proveedor);
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

		return proveedores;
	}
	
	public List<Proveedor> listarProveedorxRazonSocial(String razonSocial) {
		List<Proveedor> proveedores = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL	= "SELECT P.*, D.nombre FROM proveedor P " + 
					"INNER JOIN distrito D ON D.idDistrito = P.idDistrito " + 
					"WHERE P.razonSocial LIKE ? ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + razonSocial + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Proveedor proveedor = null;
			Distrito distrito = null;

			while (rs.next()) {
				proveedor = new Proveedor();
				proveedor.setIdProveedor(rs.getString(1));
				proveedor.setRuc(rs.getString(2));
				proveedor.setRazonSocial(rs.getString(3));
				proveedor.setDireccion(rs.getString(4));
				proveedor.setEmail(rs.getString(5));
				proveedor.setTelefono(rs.getString(6));
				proveedor.setFechaRegistro(rs.getString(7));
				proveedor.setNombreRepresentante(rs.getString(8));
				
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(9));
				distrito.setNombre(rs.getString(10));				
				proveedor.setDistrito(distrito);

				proveedores.add(proveedor);
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

		return proveedores;
	}
	
	public List<Proveedor> listarProveedorxNombreDistrito(String nombreDistrito) {
		List<Proveedor> proveedores = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL	= "SELECT P.*, D.nombre FROM proveedor P " + 
					"INNER JOIN distrito D ON D.idDistrito = P.idDistrito " + 
					"WHERE D.nombre LIKE ? ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + nombreDistrito + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Proveedor proveedor = null;
			Distrito distrito = null;

			while (rs.next()) {
				proveedor = new Proveedor();
				proveedor.setIdProveedor(rs.getString(1));
				proveedor.setRuc(rs.getString(2));
				proveedor.setRazonSocial(rs.getString(3));
				proveedor.setDireccion(rs.getString(4));
				proveedor.setEmail(rs.getString(5));
				proveedor.setTelefono(rs.getString(6));
				proveedor.setFechaRegistro(rs.getString(7));
				proveedor.setNombreRepresentante(rs.getString(8));
				
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(9));
				distrito.setNombre(rs.getString(10));				
				proveedor.setDistrito(distrito);

				proveedores.add(proveedor);
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

		return proveedores;
	}
	
	
	public List<Proveedor> listarProveedorxDireccion(String direccion) {
		List<Proveedor> proveedores = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL	= "SELECT P.*, D.nombre FROM proveedor P " + 
					"INNER JOIN distrito D ON D.idDistrito = P.idDistrito " + 
					"WHERE P.direccion LIKE ? ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + direccion + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Proveedor proveedor = null;
			Distrito distrito = null;

			while (rs.next()) {
				proveedor = new Proveedor();
				proveedor.setIdProveedor(rs.getString(1));
				proveedor.setRuc(rs.getString(2));
				proveedor.setRazonSocial(rs.getString(3));
				proveedor.setDireccion(rs.getString(4));
				proveedor.setEmail(rs.getString(5));
				proveedor.setTelefono(rs.getString(6));
				proveedor.setFechaRegistro(rs.getString(7));
				proveedor.setNombreRepresentante(rs.getString(8));
				
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(9));
				distrito.setNombre(rs.getString(10));				
				proveedor.setDistrito(distrito);

				proveedores.add(proveedor);
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

		return proveedores;
	}
	
	public List<Proveedor> listarProveedorxEmail(String email) {
		List<Proveedor> proveedores = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL	= "SELECT P.*, D.nombre FROM proveedor P " + 
					"INNER JOIN distrito D ON D.idDistrito = P.idDistrito " + 
					"WHERE P.email LIKE ? ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + email + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Proveedor proveedor = null;
			Distrito distrito = null;

			while (rs.next()) {
				proveedor = new Proveedor();
				proveedor.setIdProveedor(rs.getString(1));
				proveedor.setRuc(rs.getString(2));
				proveedor.setRazonSocial(rs.getString(3));
				proveedor.setDireccion(rs.getString(4));
				proveedor.setEmail(rs.getString(5));
				proveedor.setTelefono(rs.getString(6));
				proveedor.setFechaRegistro(rs.getString(7));
				proveedor.setNombreRepresentante(rs.getString(8));
				
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(9));
				distrito.setNombre(rs.getString(10));				
				proveedor.setDistrito(distrito);

				proveedores.add(proveedor);
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

		return proveedores;
	}
	
	public List<Proveedor> listarProveedorxTelefono(String telefono) {
		List<Proveedor> proveedores = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL	= "SELECT P.*, D.nombre FROM proveedor P " + 
					"INNER JOIN distrito D ON D.idDistrito = P.idDistrito " + 
					"WHERE P.telefono LIKE ? ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + telefono + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Proveedor proveedor = null;
			Distrito distrito = null;

			while (rs.next()) {
				proveedor = new Proveedor();
				proveedor.setIdProveedor(rs.getString(1));
				proveedor.setRuc(rs.getString(2));
				proveedor.setRazonSocial(rs.getString(3));
				proveedor.setDireccion(rs.getString(4));
				proveedor.setEmail(rs.getString(5));
				proveedor.setTelefono(rs.getString(6));
				proveedor.setFechaRegistro(rs.getString(7));
				proveedor.setNombreRepresentante(rs.getString(8));
				
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(9));
				distrito.setNombre(rs.getString(10));				
				proveedor.setDistrito(distrito);

				proveedores.add(proveedor);
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

		return proveedores;
	}
	
	public List<Proveedor> listarProveedorxRepresentante(String representante) {
		List<Proveedor> proveedores = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL	= "SELECT P.*, D.nombre FROM proveedor P " + 
					"INNER JOIN distrito D ON D.idDistrito = P.idDistrito " + 
					"WHERE P.nombreRepresentante LIKE ? ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + representante + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Proveedor proveedor = null;
			Distrito distrito = null;

			while (rs.next()) {
				proveedor = new Proveedor();
				proveedor.setIdProveedor(rs.getString(1));
				proveedor.setRuc(rs.getString(2));
				proveedor.setRazonSocial(rs.getString(3));
				proveedor.setDireccion(rs.getString(4));
				proveedor.setEmail(rs.getString(5));
				proveedor.setTelefono(rs.getString(6));
				proveedor.setFechaRegistro(rs.getString(7));
				proveedor.setNombreRepresentante(rs.getString(8));
				
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(9));
				distrito.setNombre(rs.getString(10));				
				proveedor.setDistrito(distrito);

				proveedores.add(proveedor);
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

		return proveedores;
	}
	
	public List<Proveedor> listarProveedor() {
		List<Proveedor> proveedores = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL	= "SELECT P.*, D.nombre FROM proveedor P " + 
					"INNER JOIN distrito D ON D.idDistrito = P.idDistrito ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Proveedor proveedor = null;
			Distrito distrito = null;

			while (rs.next()) {
				proveedor = new Proveedor();
				proveedor.setIdProveedor(rs.getString(1));
				proveedor.setRuc(rs.getString(2));
				proveedor.setRazonSocial(rs.getString(3));
				proveedor.setDireccion(rs.getString(4));
				proveedor.setEmail(rs.getString(5));
				proveedor.setTelefono(rs.getString(6));
				proveedor.setFechaRegistro(rs.getString(7));
				proveedor.setNombreRepresentante(rs.getString(8));
				
				distrito = new Distrito();
				distrito.setIdDistrito(rs.getString(9));
				distrito.setNombre(rs.getString(10));				
				proveedor.setDistrito(distrito);

				proveedores.add(proveedor);
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

		return proveedores;
	}

	public int insertarProveedor(Proveedor proveedor) {
		int salida = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "INSERT INTO proveedor VALUES(?,?,?,?,?,?,?,?,?)";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, proveedor.getIdProveedor());
			pstm.setString(2, proveedor.getRuc());
			pstm.setString(3, proveedor.getRazonSocial());
			pstm.setString(4, proveedor.getDireccion());
			pstm.setString(5, proveedor.getEmail());
			pstm.setString(6, proveedor.getTelefono());
			pstm.setString(7, proveedor.getFechaRegistro());
			pstm.setString(8, proveedor.getNombreRepresentante());
			pstm.setString(9, proveedor.getDistrito().getIdDistrito());

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

	public int eliminarProveedor(String idProveedor) {
		int eliminados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "DELETE FROM proveedor WHERE idProveedor=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, idProveedor);

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

	public int actualizarProveedor(Proveedor proveedor) {
		int actualizados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "UPDATE proveedor SET ruc=?, razonSocial=?, direccion=?, email=?, telefono=?, " + 
					"fechaRegistro=?, nombreRepresentante=?, idDistrito=? WHERE idProveedor=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			
			pstm.setString(1, proveedor.getRuc());
			pstm.setString(2, proveedor.getRazonSocial());
			pstm.setString(3, proveedor.getDireccion());
			pstm.setString(4, proveedor.getEmail());
			pstm.setString(5, proveedor.getTelefono());
			pstm.setString(6, proveedor.getFechaRegistro());
			pstm.setString(7, proveedor.getNombreRepresentante());
			pstm.setString(8, proveedor.getDistrito().getIdDistrito());
			pstm.setString(9, proveedor.getIdProveedor());

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
	public String getUltimoCodigoProveedor() {
		String ultimoId = "";
		
		// Si hay categorías registradas
		List<Proveedor> proveedores = this.listarProveedor();
		int nroProveedors = proveedores.size();
		if (proveedores.size() > 0) {
			// Se obtiene el último código registrado
			ultimoId = proveedores.get(nroProveedors - 1).getIdProveedor();
		}
		
		return ultimoId;
	}
	
	// Obtiene el último código. Si no hay registros, obtiene el String vacío
	public String getUltimoCodigoProveedor2() {
		String ultimoId = "";

		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT PR.idProveedor FROM proveedor PR ORDER BY 1 DESC LIMIT 1"; // MySQL

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
	
	public String generarCodigoProveedor() {
		// String idProveedor = this.getUltimoCodigoProveedor();
		String idProveedor = this.getUltimoCodigoProveedor2();
		int numero;
		
		if (idProveedor.length() == 0) {
			numero = 1;
			
		} else {
			numero = Integer.parseInt(idProveedor.substring(2));
			numero++;
		}
		
		idProveedor = "PR" + String.format("%06d", numero);
		
		return idProveedor;
	}
	
	public Proveedor getProveedor(String idProveedor) {
		Proveedor proveedor = null;
		List<Proveedor> proveedores= this.listarProveedor();
		
		for (Proveedor p : proveedores) {
			if (p.getIdProveedor().equalsIgnoreCase(idProveedor)) {
				proveedor = p;
				break;
			}
		}
		
		return proveedor;
	}
	
	// Obtiene el proveedor por Razón Social
	public Proveedor getProveedorXRazonSocial(String razonSocial) {
		Proveedor proveedor= null;
		List<Proveedor> proveedores = this.listarProveedor();
		
		for (Proveedor p : proveedores) {
			if (p.getRazonSocial().equalsIgnoreCase(razonSocial)) {
				proveedor = p;
				break;
			}
		}
		
		return proveedor;
	}
}
