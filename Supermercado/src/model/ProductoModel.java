package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Categoria;
import entidad.Producto;
import entidad.Proveedor;
import util.Conexion;
import util.Foto;

public class ProductoModel {
	private CategoriaModel modeloCategoria = new CategoriaModel();
	private ProveedorModel modeloProveedor = new ProveedorModel();

	public List<Producto> listarProductoxCodigo(String idProducto) {
		List<Producto> productos = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT P.* FROM producto P WHERE P.idProducto LIKE ?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + idProducto + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Producto producto = null;
			Categoria categoria = null;
			Proveedor proveedor = null;

			while (rs.next()) {
				producto = new Producto();
				producto.setIdProducto(rs.getString(1));
				producto.setNombre(rs.getString(2));
				producto.setDescripcion(rs.getString(3));
				producto.setPrecioVenta(rs.getDouble(4));
				producto.setPrecioCompra(rs.getDouble(5));
				producto.setStockActual(rs.getInt(6));
				producto.setStockMinimo(rs.getInt(7));
				producto.setFechaRegistro(rs.getString(8));
				// Foto 8
				//
				String idCategoria = rs.getString(10);
				categoria = this.modeloCategoria.getCategoria(idCategoria);
				producto.setCategoria(categoria);

				String idProveedor = rs.getString(11);
				proveedor = this.modeloProveedor.getProveedor(idProveedor);
				producto.setProveedor(proveedor);

				productos.add(producto);
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

		return productos;
	}
	
	public List<Producto> listarProductoProveedorxCodigo(String idProducto, String codigoProveedor) {
		List<Producto> productos = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT P.* FROM producto P WHERE P.idProducto LIKE ? AND P.idProveedor = ?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + idProducto + "%");
			pstm.setString(2, codigoProveedor);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Producto producto = null;
			Categoria categoria = null;
			Proveedor proveedor = null;

			while (rs.next()) {
				producto = new Producto();
				producto.setIdProducto(rs.getString(1));
				producto.setNombre(rs.getString(2));
				producto.setDescripcion(rs.getString(3));
				producto.setPrecioVenta(rs.getDouble(4));
				producto.setPrecioCompra(rs.getDouble(5));
				producto.setStockActual(rs.getInt(6));
				producto.setStockMinimo(rs.getInt(7));
				producto.setFechaRegistro(rs.getString(8));
				// Foto 8
				//
				String idCategoria = rs.getString(10);
				categoria = this.modeloCategoria.getCategoria(idCategoria);
				producto.setCategoria(categoria);

				String idProveedor = rs.getString(11);
				proveedor = this.modeloProveedor.getProveedor(idProveedor);
				producto.setProveedor(proveedor);

				productos.add(producto);
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

		return productos;
	}

	public List<Producto> listarProductoxNombre(String nombre) {
		List<Producto> productos = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT P.* FROM producto P WHERE P.nombre LIKE ?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + nombre + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Producto producto = null;
			Categoria categoria = null;
			Proveedor proveedor = null;

			while (rs.next()) {
				producto = new Producto();
				producto.setIdProducto(rs.getString(1));
				producto.setNombre(rs.getString(2));
				producto.setDescripcion(rs.getString(3));
				producto.setPrecioVenta(rs.getDouble(4));
				producto.setPrecioCompra(rs.getDouble(5));
				producto.setStockActual(rs.getInt(6));
				producto.setStockMinimo(rs.getInt(7));
				producto.setFechaRegistro(rs.getString(8));
				// Foto 8

				String idCategoria = rs.getString(10);
				categoria = this.modeloCategoria.getCategoria(idCategoria);
				producto.setCategoria(categoria);

				String idProveedor = rs.getString(11);
				proveedor = this.modeloProveedor.getProveedor(idProveedor);
				producto.setProveedor(proveedor);

				productos.add(producto);
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

		return productos;
	}
	
	public List<Producto> listarProductoProveedorxNombre(String nombre, String codigoProveedor) {
		List<Producto> productos = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT P.* FROM producto P WHERE P.nombre LIKE ? AND P.idProveedor = ?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + nombre + "%");
			pstm.setString(2, codigoProveedor);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Producto producto = null;
			Categoria categoria = null;
			Proveedor proveedor = null;

			while (rs.next()) {
				producto = new Producto();
				producto.setIdProducto(rs.getString(1));
				producto.setNombre(rs.getString(2));
				producto.setDescripcion(rs.getString(3));
				producto.setPrecioVenta(rs.getDouble(4));
				producto.setPrecioCompra(rs.getDouble(5));
				producto.setStockActual(rs.getInt(6));
				producto.setStockMinimo(rs.getInt(7));
				producto.setFechaRegistro(rs.getString(8));
				// Foto 8

				String idCategoria = rs.getString(10);
				categoria = this.modeloCategoria.getCategoria(idCategoria);
				producto.setCategoria(categoria);

				String idProveedor = rs.getString(11);
				proveedor = this.modeloProveedor.getProveedor(idProveedor);
				producto.setProveedor(proveedor);

				productos.add(producto);
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

		return productos;
	}

	public List<Producto> listarProductoxDescripcion(String descripcion) {
		List<Producto> productos = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT P.* FROM producto P WHERE P.descripcion LIKE ?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + descripcion + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Producto producto = null;
			Categoria categoria = null;
			Proveedor proveedor = null;

			while (rs.next()) {
				producto = new Producto();
				producto.setIdProducto(rs.getString(1));
				producto.setNombre(rs.getString(2));
				producto.setDescripcion(rs.getString(3));
				producto.setPrecioVenta(rs.getDouble(4));
				producto.setPrecioCompra(rs.getDouble(5));
				producto.setStockActual(rs.getInt(6));
				producto.setStockMinimo(rs.getInt(7));
				producto.setFechaRegistro(rs.getString(8));
				// Foto 8

				String idCategoria = rs.getString(10);
				categoria = this.modeloCategoria.getCategoria(idCategoria);
				producto.setCategoria(categoria);

				String idProveedor = rs.getString(11);
				proveedor = this.modeloProveedor.getProveedor(idProveedor);
				producto.setProveedor(proveedor);

				productos.add(producto);
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

		return productos;
	}
	
	public List<Producto> listarProductoProveedorxDescripcion(String descripcion, String codigoProveedor) {
		List<Producto> productos = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT P.* FROM producto P WHERE P.descripcion LIKE ? AND P.idProveedor = ?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + descripcion + "%");
			pstm.setString(2, codigoProveedor);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Producto producto = null;
			Categoria categoria = null;
			Proveedor proveedor = null;

			while (rs.next()) {
				producto = new Producto();
				producto.setIdProducto(rs.getString(1));
				producto.setNombre(rs.getString(2));
				producto.setDescripcion(rs.getString(3));
				producto.setPrecioVenta(rs.getDouble(4));
				producto.setPrecioCompra(rs.getDouble(5));
				producto.setStockActual(rs.getInt(6));
				producto.setStockMinimo(rs.getInt(7));
				producto.setFechaRegistro(rs.getString(8));
				// Foto 8

				String idCategoria = rs.getString(10);
				categoria = this.modeloCategoria.getCategoria(idCategoria);
				producto.setCategoria(categoria);

				String idProveedor = rs.getString(11);
				proveedor = this.modeloProveedor.getProveedor(idProveedor);
				producto.setProveedor(proveedor);

				productos.add(producto);
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

		return productos;
	}

	public List<Producto> listarProductoxCategoria(String codigoCategoria) {
		List<Producto> productos = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT P.* FROM producto P WHERE P.idCategoria LIKE ?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + codigoCategoria + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Producto producto = null;
			Categoria categoria = null;
			Proveedor proveedor = null;

			while (rs.next()) {
				producto = new Producto();
				producto.setIdProducto(rs.getString(1));
				producto.setNombre(rs.getString(2));
				producto.setDescripcion(rs.getString(3));
				producto.setPrecioVenta(rs.getDouble(4));
				producto.setPrecioCompra(rs.getDouble(5));
				producto.setStockActual(rs.getInt(6));
				producto.setStockMinimo(rs.getInt(7));
				producto.setFechaRegistro(rs.getString(8));
				// Foto 8

				String idCategoria = rs.getString(10);
				categoria = this.modeloCategoria.getCategoria(idCategoria);
				producto.setCategoria(categoria);

				String idProveedor = rs.getString(11);
				proveedor = this.modeloProveedor.getProveedor(idProveedor);
				producto.setProveedor(proveedor);

				productos.add(producto);
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

		return productos;
	}

	public List<Producto> listarProductoxNombreCategoria(String nombreCategoria) {
		List<Producto> productos = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT P.* FROM producto P INNER JOIN categoria C "
					+ "ON C.idCategoria = P.idCategoria WHERE C.nombre LIKE ?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + nombreCategoria + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Producto producto = null;
			Categoria categoria = null;
			Proveedor proveedor = null;

			while (rs.next()) {
				producto = new Producto();
				producto.setIdProducto(rs.getString(1));
				producto.setNombre(rs.getString(2));
				producto.setDescripcion(rs.getString(3));
				producto.setPrecioVenta(rs.getDouble(4));
				producto.setPrecioCompra(rs.getDouble(5));
				producto.setStockActual(rs.getInt(6));
				producto.setStockMinimo(rs.getInt(7));
				producto.setFechaRegistro(rs.getString(8));
				// Foto 8

				String idCategoria = rs.getString(10);
				categoria = this.modeloCategoria.getCategoria(idCategoria);
				producto.setCategoria(categoria);

				String idProveedor = rs.getString(11);
				proveedor = this.modeloProveedor.getProveedor(idProveedor);
				producto.setProveedor(proveedor);

				productos.add(producto);
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

		return productos;
	}

	public List<Producto> listarProductoProveedorxNombreCategoria(String nombreCategoria, String codigoProveedor) {
		List<Producto> productos = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT P.* FROM producto P INNER JOIN categoria C "
					+ "ON C.idCategoria = P.idCategoria WHERE C.nombre LIKE ? AND P.idProveedor = ?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + nombreCategoria + "%");
			pstm.setString(2, codigoProveedor);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Producto producto = null;
			Categoria categoria = null;
			Proveedor proveedor = null;

			while (rs.next()) {
				producto = new Producto();
				producto.setIdProducto(rs.getString(1));
				producto.setNombre(rs.getString(2));
				producto.setDescripcion(rs.getString(3));
				producto.setPrecioVenta(rs.getDouble(4));
				producto.setPrecioCompra(rs.getDouble(5));
				producto.setStockActual(rs.getInt(6));
				producto.setStockMinimo(rs.getInt(7));
				producto.setFechaRegistro(rs.getString(8));
				// Foto 8

				String idCategoria = rs.getString(10);
				categoria = this.modeloCategoria.getCategoria(idCategoria);
				producto.setCategoria(categoria);

				String idProveedor = rs.getString(11);
				proveedor = this.modeloProveedor.getProveedor(idProveedor);
				producto.setProveedor(proveedor);

				productos.add(producto);
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

		return productos;
	}
	
	public List<Producto> listarProductoxRazonSocialProveedor(String razonSocialProveedor) {
		List<Producto> productos = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT P.* FROM producto P INNER JOIN proveedor PR " + 
					"ON PR.idProveedor = P.idProveedor WHERE PR.razonSocial LIKE ?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, "%" + razonSocialProveedor + "%");

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Producto producto = null;
			Categoria categoria = null;
			Proveedor proveedor = null;

			while (rs.next()) {
				producto = new Producto();
				producto.setIdProducto(rs.getString(1));
				producto.setNombre(rs.getString(2));
				producto.setDescripcion(rs.getString(3));
				producto.setPrecioVenta(rs.getDouble(4));
				producto.setPrecioCompra(rs.getDouble(5));
				producto.setStockActual(rs.getInt(6));
				producto.setStockMinimo(rs.getInt(7));
				producto.setFechaRegistro(rs.getString(8));
				// Foto 8

				String idCategoria = rs.getString(10);
				categoria = this.modeloCategoria.getCategoria(idCategoria);
				producto.setCategoria(categoria);

				String idProveedor = rs.getString(11);
				proveedor = this.modeloProveedor.getProveedor(idProveedor);
				producto.setProveedor(proveedor);

				productos.add(producto);
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

		return productos;
	}

	public List<Producto> listarProducto() {
		List<Producto> productos = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT P.* FROM producto P ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Producto producto = null;
			Categoria categoria = null;
			Proveedor proveedor = null;

			while (rs.next()) {
				producto = new Producto();
				producto.setIdProducto(rs.getString(1));
				producto.setNombre(rs.getString(2));
				producto.setDescripcion(rs.getString(3));
				producto.setPrecioVenta(rs.getDouble(4));
				producto.setPrecioCompra(rs.getDouble(5));
				producto.setStockActual(rs.getInt(6));
				producto.setStockMinimo(rs.getInt(7));
				producto.setFechaRegistro(rs.getString(8));

				// Foto
				Foto foto = new Foto();
				foto.setInputStream(rs.getBinaryStream(9));
				producto.setFoto(foto);

				String idCategoria = rs.getString(10);
				categoria = this.modeloCategoria.getCategoria(idCategoria);
				producto.setCategoria(categoria);

				String idProveedor = rs.getString(11);
				proveedor = this.modeloProveedor.getProveedor(idProveedor);
				producto.setProveedor(proveedor);

				productos.add(producto);
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

		return productos;
	}

	public int insertarProducto(Producto producto) {
		int salida = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "INSERT INTO producto VALUES(?,?,?,?,?,?,?,?,?,?,?)"; // Foto
																						// ?
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, producto.getIdProducto());
			pstm.setString(2, producto.getNombre());
			pstm.setString(3, producto.getDescripcion());
			pstm.setDouble(4, producto.getPrecioVenta());
			pstm.setDouble(5, producto.getPrecioCompra());
			pstm.setInt(6, producto.getStockActual());
			pstm.setInt(7, producto.getStockMinimo());
			pstm.setString(8, producto.getFechaRegistro());
			pstm.setBlob(9, producto.getFoto().getInputStream()); // foto
			pstm.setString(10, producto.getCategoria().getIdCategoria());
			pstm.setString(11, producto.getProveedor().getIdProveedor());

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

	public int eliminarProducto(String idProducto) {
		int eliminados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "DELETE FROM producto WHERE idProducto=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, idProducto);

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

	public int actualizarProducto(Producto producto) {
		int actualizados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "UPDATE producto SET nombre=?, descripcion=?, precioVenta=?, precioCompra=?, "
					+ "stockActual=?, stockMinimo=?, fechaRegistro=?, foto=?, idCategoria=?, idProveedor=? WHERE idProducto=?";
			pstm = conexion.prepareStatement(sentenciaSQL);

			pstm.setString(1, producto.getNombre());
			pstm.setString(2, producto.getDescripcion());
			pstm.setDouble(3, producto.getPrecioVenta());
			pstm.setDouble(4, producto.getPrecioCompra());
			pstm.setInt(5, producto.getStockActual());
			pstm.setInt(6, producto.getStockMinimo());
			pstm.setString(7, producto.getFechaRegistro());
			pstm.setBlob(8, producto.getFoto().getInputStream()); // foto
			pstm.setString(9, producto.getCategoria().getIdCategoria());
			pstm.setString(10, producto.getProveedor().getIdProveedor());
			pstm.setString(11, producto.getIdProducto());

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

		List<Producto> productos = this.listarProducto();
		for (Producto producto : productos) {
			if (producto.getIdProducto().equalsIgnoreCase(codigo)) {
				foto = producto.getFoto();
				break;
			}
		}

		return foto;
	}
	
	// Obtiene un producto a partir de su código
	public Producto getProducto(String idProducto) {
		Producto producto = null;
		List<Producto> productos = this.listarProducto();
		
		for (Producto p : productos) {
			if (p.getIdProducto().equalsIgnoreCase(idProducto)) {
				producto = p;
				break;
			}
		}
		
		return producto;
	}

	// Obtiene el último código. Si no hay registros, obtiene el String vacío
	public String getUltimoCodigoProducto() {
		String ultimoId = "";

		// Si hay categorías registradas
		List<Producto> productos = this.listarProducto();
		int nroProductos = productos.size();
		if (productos.size() > 0) {
			// Se obtiene el último código registrado
			ultimoId = productos.get(nroProductos - 1).getIdProducto();
		}

		return ultimoId;
	}

	// Obtiene el último código. Si no hay registros, obtiene el String vacío
	public String getUltimoCodigoProducto2() {
		String ultimoId = "";

		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT P.idProducto FROM producto P ORDER BY 1 DESC LIMIT 1"; // MySQL

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

	public String generarCodigoProducto() {
		// String idProducto = this.getUltimoCodigoProducto();
		String idProducto = this.getUltimoCodigoProducto2();
		int numero;

		if (idProducto.length() == 0) {
			numero = 1;

		} else {
			numero = Integer.parseInt(idProducto.substring(1));
			numero++;
		}

		idProducto = "P" + String.format("%07d", numero);

		return idProducto;
	}
}
