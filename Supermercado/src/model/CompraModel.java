package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import entidad.Compra;
import entidad.DetalleCompra;
import util.Conexion;

public class CompraModel {

	/*
	 * public List<Compra> listarCompra() { List<Compra> compras = new
	 * ArrayList<>(); Connection conexion = null; PreparedStatement pstm = null;
	 * ResultSet rs = null;
	 * 
	 * try { // Se obtiene la conexión y se prepara la sentencia SQL conexion =
	 * new Conexion().getConexion(); String sentenciaSQL =
	 * "SELECT I.*, PR.ruc, PR.razonSocial, E.dni, E.nombres, E.apellidos FROM ingreso I "
	 * + "INNER JOIN proveedor PR ON PR.idProveedor = I.idProveedor " +
	 * "INNER JOIN empleado E ON E.idEmpleado = I.idEmpleado ORDER BY 1 ASC";
	 * pstm = conexion.prepareStatement(sentenciaSQL);
	 * 
	 * // Se obtiene el resultado de la base de datos rs = pstm.executeQuery();
	 * 
	 * // Se recorre el resultado y se almacena en el ArrayList Compra ingreso =
	 * null; Proveedor proveedor = null; Empleado empleado = null; while
	 * (rs.next()) { ingreso = new Compra();
	 * ingreso.setIdCompra(rs.getString(1));
	 * ingreso.setTipoComprobante(rs.getString(2));
	 * ingreso.setSerieComprobante(rs.getString(3));
	 * ingreso.setNumeroComprobante(rs.getString(4));
	 * ingreso.setFechaCompra(rs.getString(5)); ingreso.setIgv(rs.getDouble(6));
	 * 
	 * proveedor = new Proveedor(); proveedor.setIdProveedor(rs.getString(7));
	 * empleado = new Empleado(); empleado.setIdEmpleado(rs.getString(8));
	 * proveedor.setRuc(rs.getString(9));
	 * proveedor.setRazonSocial(rs.getString(10));
	 * empleado.setDni(rs.getString(11)); empleado.setNombres(rs.getString(12));
	 * empleado.setApellidos(rs.getString(13));
	 * 
	 * ingreso.setProveedor(proveedor); ingreso.setEmpleado(empleado);
	 * 
	 * compras.add(ingreso); }
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * 
	 * } finally { // Se cierran las conexiones try { if (rs != null)
	 * rs.close(); if (pstm != null) pstm.close(); if (conexion != null)
	 * conexion.close(); } catch (Exception e2) { e2.printStackTrace(); } }
	 * 
	 * return compras; }
	 */

	public boolean insertarCompra(Compra ingreso, List<DetalleCompra> detalleCompras) {
		boolean inserta = false;

		Connection conexion = null;
		PreparedStatement pstm1 = null;
		PreparedStatement pstm2 = null;
		PreparedStatement pstm3 = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();

			// Se anula el autoenvío
			conexion.setAutoCommit(false);

			String sentenciaSQL = "INSERT INTO ingreso VALUES(?,?,?,?,?,?,?,?)";
			pstm1 = conexion.prepareStatement(sentenciaSQL);
			pstm1.setString(1, ingreso.getIdCompra());
			pstm1.setString(2, ingreso.getTipoComprobante());
			pstm1.setString(3, ingreso.getSerieComprobante());
			pstm1.setString(4, ingreso.getNumeroComprobante());
			pstm1.setString(5, ingreso.getFechaCompra());
			pstm1.setDouble(6, ingreso.getIgv());
			pstm1.setString(7, ingreso.getProveedor().getIdProveedor());
			pstm1.setString(8, ingreso.getEmpleado().getIdEmpleado());

			// Se imprime el SQL
			System.out.println("SQL -> " + pstm1);

			// Se ejecuta el SQL
			pstm1.executeUpdate();
			
			// Se inserta el DetalleVenta y se actualiza el Stock de los productos
			String sentenciaSQL2 = "INSERT INTO detalle_ingreso VALUES(?,?,?,?,?)";
			pstm2 = conexion.prepareStatement(sentenciaSQL2);
			
			String sentenciaSQL3 = "UPDATE producto SET stockActual = stockActual + ? WHERE idProducto = ?";
			pstm3 = conexion.prepareStatement(sentenciaSQL3);
			
			for (DetalleCompra detalleCompra : detalleCompras) {
				pstm2.setString(1, detalleCompra.getIdCompra());
				pstm2.setString(2, detalleCompra.getIdProducto());
				pstm2.setInt(3, detalleCompra.getCantidad());
				pstm2.setDouble(4, detalleCompra.getPrecioCompra());
				pstm2.setDouble(5, detalleCompra.getDescuento());
				pstm2.executeUpdate();
				
				pstm3.setInt(1, detalleCompra.getCantidad());
				pstm3.setString(2, detalleCompra.getIdProducto());
				pstm3.executeUpdate();
			}
			
			// Se ejecutan todas las sentencias SQL en la base de datos
			conexion.commit();
			inserta = true;

		} catch (Exception e) {
			try {
				conexion.rollback(); // No permite un SQL a medias
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();

		} finally {
			// Se cierran las conexiones
			try {
				if (pstm3 != null) {
					pstm3.close();
				}
				if (pstm2 != null) {
					pstm2.close();
				}
				if (pstm1 != null) {
					pstm1.close();
				}
				if (conexion != null) {
					conexion.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return inserta;
	}

	/*public int eliminarCompra(String idCompra) {
		int eliminados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "DELETE FROM ingreso WHERE idIngreso=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, idCompra);

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

	public int actualizarCompra(Compra ingreso) {
		int actualizados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "UPDATE ingreso SET tipoComprobante=?, serieComprobante=?, numeroComprobante=?, "
					+ "fechaCompra=?, igv=?, idProveedor=?, idEmpleado=? WHERE idIngreso=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, ingreso.getTipoComprobante());
			pstm.setString(2, ingreso.getSerieComprobante());
			pstm.setString(3, ingreso.getNumeroComprobante());
			pstm.setString(4, ingreso.getFechaCompra());
			pstm.setDouble(5, ingreso.getIgv());
			pstm.setString(6, ingreso.getProveedor().getIdProveedor());
			pstm.setString(7, ingreso.getEmpleado().getIdEmpleado());
			pstm.setString(8, ingreso.getIdCompra());

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
	}*/

	// Obtiene el último código. Si no hay registros, obtiene el String vacío
	public String getUltimoCodigoCompra() {
		String ultimoId = "";

		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT I.idIngreso FROM ingreso I ORDER BY 1 DESC LIMIT 1"; // MySQL

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

	public String generarCodigoCompra() {
		String idCompra = this.getUltimoCodigoCompra();
		int numero;

		if (idCompra.length() == 0) {
			numero = 1;

		} else {
			numero = Integer.parseInt(idCompra.substring(1));
			numero++;
		}

		idCompra = "I" + String.format("%09d", numero);

		return idCompra;
	}
}
