package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.DetalleVenta;
import entidad.Venta;
import util.Conexion;

public class VentaModel {
	
	private EmpleadoModel modeloEmpleado = new EmpleadoModel();
	private ClienteModel modeloCliente = new ClienteModel();
	
	/*public List<Venta> listarVenta() {
		List<Venta> ventas = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL	= "SELECT V.*, E.dni, E.nombres, E.apellidos, C.dni, C.nombres, C.apellidos FROM venta V " +
					"INNER JOIN empleado E ON E.idEmpleado = V.idEmpleado " +
					"INNER JOIN cliente C ON C.idCliente = V.idCliente ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Venta venta = null;
			Caja caja = null;
			Empleado empleado = null;
			Cliente cliente = null;
			while (rs.next()) {
				venta = new Venta();
				venta.setIdVenta(rs.getString(1));
				venta.setFechaVenta(rs.getString(2));
				venta.setTipoComprobante(rs.getString(3));
				venta.setSerieComprobante(rs.getString(4));
				venta.setNumeroComprobante(rs.getString(5));
				venta.setIgv(rs.getDouble(6));
				
				caja = new Caja();
				caja.setIdCaja(rs.getString(7));
				empleado = new Empleado();
				empleado.setIdEmpleado(rs.getString(8));
				cliente = new Cliente();
				cliente.setIdCliente(rs.getString(9));
				empleado.setDni(rs.getString(10));
				empleado.setNombres(rs.getString(11));
				empleado.setApellidos(rs.getString(12));
				cliente.setDni(rs.getString(13));
				cliente.setNombres(rs.getString(14));
				cliente.setApellidos(rs.getString(15));
				
				venta.setCaja(caja);
				venta.setEmpleado(empleado);
				venta.setCliente(cliente);

				ventas.add(venta);
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

		return ventas;
	}
	*/

	public boolean insertarVenta(Venta venta, List<DetalleVenta> detalleVentas) {
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
			
			String sentenciaSQL = "INSERT INTO venta VALUES(?,?,?,?,?,?,?,?)";
			pstm1 = conexion.prepareStatement(sentenciaSQL);
			pstm1.setString(1, venta.getIdVenta());
			pstm1.setString(2, venta.getFechaVenta());
			pstm1.setString(3, venta.getTipoComprobante());
			pstm1.setString(4, venta.getSerieComprobante());
			pstm1.setString(5, venta.getNumeroComprobante());
			pstm1.setDouble(6, venta.getIgv());
			pstm1.setString(7, venta.getEmpleado().getIdEmpleado());
			pstm1.setString(8, venta.getCliente().getIdCliente());

			// Se imprime el SQL
			System.out.println("SQL -> " + pstm1);

			// Se ejecuta el SQL
			pstm1.executeUpdate();
			
			
			// Se inserta el DetalleVenta y se actualiza el Stock de los productos
			String sentenciaSQL2 = "INSERT INTO detalle_venta VALUES(?,?,?,?,?)";
			pstm2 = conexion.prepareStatement(sentenciaSQL2);
			
			String sentenciaSQL3 = "UPDATE producto SET stockActual = stockActual - ? WHERE idProducto = ?";
			pstm3 = conexion.prepareStatement(sentenciaSQL3);
			
			
			for (DetalleVenta detalleVenta : detalleVentas) {
				pstm2.setString(1, detalleVenta.getIdVenta());
				pstm2.setString(2, detalleVenta.getIdProducto());
				pstm2.setInt(3, detalleVenta.getCantidad());
				pstm2.setDouble(4, detalleVenta.getPrecioVenta());
				pstm2.setDouble(5, detalleVenta.getDescuento());
				pstm2.executeUpdate();
				
				pstm3.setInt(1, detalleVenta.getCantidad());
				pstm3.setString(2, detalleVenta.getIdProducto());
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
	
	public List<Venta> listarVentaxFecha(String desde, String hasta) {
		List<Venta> ventas = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL	= "SELECT V.* FROM venta V WHERE V.fechaVenta BETWEEN ? AND ?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, desde);
			pstm.setString(2, hasta);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Venta venta = null;
			while (rs.next()) {
				venta = new Venta();
				venta.setIdVenta(rs.getString(1));
				venta.setFechaVenta(rs.getString(2));
				venta.setTipoComprobante(rs.getString(3));
				venta.setSerieComprobante(rs.getString(4));
				venta.setNumeroComprobante(rs.getString(5));
				venta.setIgv(rs.getDouble(6));
				
				venta.setEmpleado(this.modeloEmpleado.getEmpleado(rs.getString(7)));
				venta.setCliente(this.modeloCliente.getCliente(rs.getString(8)));

				ventas.add(venta);
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

		return ventas;
	}
	
	public List<DetalleVenta> listarDetalleVenta(String idVenta) {
		List<DetalleVenta> detalles = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL	= "SELECT DV.* FROM detalle_venta DV WHERE DV.idVenta = ?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, idVenta);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			DetalleVenta detalleVenta = null;
			while (rs.next()) {
				detalleVenta = new DetalleVenta();
				detalleVenta.setIdVenta(rs.getString(1));
				detalleVenta.setIdProducto(rs.getString(2));
				detalleVenta.setCantidad(rs.getInt(3));
				detalleVenta.setPrecioVenta(rs.getDouble(4));
				detalleVenta.setDescuento(rs.getDouble(5));

				detalles.add(detalleVenta);
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

		return detalles;
	}

	/*public int eliminarVenta(String idVenta) {
		int eliminados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "DELETE FROM venta WHERE idVenta=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, idVenta);

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
*/
	/*public int actualizarVenta(Venta venta) {
		int actualizados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "UPDATE venta SET fechaVenta=?, tipoComprobante=?, serieComprobante=?, numeroComprobante=?, " +  
					"igv=?, idCaja=?, idEmpleado=?, idCliente=? WHERE idVenta=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, venta.getFechaVenta());
			pstm.setString(2, venta.getTipoComprobante());
			pstm.setString(3, venta.getSerieComprobante());
			pstm.setString(4, venta.getNumeroComprobante());
			pstm.setDouble(5, venta.getIgv());
			pstm.setString(6, venta.getCaja().getIdCaja());
			pstm.setString(7, venta.getEmpleado().getIdEmpleado());
			pstm.setString(8, venta.getCliente().getIdCliente());
			pstm.setString(9, venta.getIdVenta());

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
	*/
	
	// Obtiene el último código. Si no hay registros, obtiene el String vacío
	public String getUltimoCodigoVenta() {
		String ultimoId = "";

		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT V.idVenta FROM venta V ORDER BY 1 DESC LIMIT 1"; // MySQL

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
	
	public String generarCodigoVenta() {
		String idVenta = this.getUltimoCodigoVenta();
		int numero;
		
		if (idVenta.length() == 0) {
			numero = 1;
			
		} else {
			numero = Integer.parseInt(idVenta.substring(1));
			numero++;
		}
		
		idVenta = "V" + String.format("%09d", numero);
		
		return idVenta;
	}
	
	// Obtiene el último número de Boleta emitida
	public String getUltimoNumeroBoleta() {
		String ultimoNumeroBoleta = "";

		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT V.numeroComprobante FROM venta V WHERE V.tipoComprobante = 'BOLETA' " 
					+ "ORDER BY 1 DESC LIMIT 1"; // MySQL

			// Se obtiene el resultado de la base de datos
			pstm = conexion.prepareStatement(sentenciaSQL);
			rs = pstm.executeQuery();

			// Se obtiene el único resultado
			if (rs.next()) {
				ultimoNumeroBoleta = rs.getString(1);
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

		return ultimoNumeroBoleta;
	}
	
	public String generarNumeroBoleta() {
		String numeroBoleta = this.getUltimoNumeroBoleta();
		int numero;
		
		if (numeroBoleta.length() == 0) {
			numero = 1;
			
		} else {
			numero = Integer.parseInt(numeroBoleta);
			numero++;
		}
		
		numeroBoleta = String.format("%09d", numero);
		
		return numeroBoleta;
	}
	
	// Obtiene el último número de Factura emitida
	public String getUltimoNumeroFactura() {
		String ultimoNumeroFactura = "";

		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT V.numeroComprobante FROM venta V WHERE V.tipoComprobante = 'FACTURA' " 
					+ "ORDER BY 1 DESC LIMIT 1"; // MySQL

			// Se obtiene el resultado de la base de datos
			pstm = conexion.prepareStatement(sentenciaSQL);
			rs = pstm.executeQuery();

			// Se obtiene el único resultado
			if (rs.next()) {
				ultimoNumeroFactura = rs.getString(1);
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

		return ultimoNumeroFactura;
	}

	
	public String generarNumeroFactura() {
		String numeroFactura = this.getUltimoNumeroFactura();
		int numero;
		
		if (numeroFactura.length() == 0) {
			numero = 1;
			
		} else {
			numero = Integer.parseInt(numeroFactura);
			numero++;
		}
		
		numeroFactura = String.format("%09d", numero);
		
		return numeroFactura;
	}
}
