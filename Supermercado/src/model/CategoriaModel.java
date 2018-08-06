package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Categoria;
import util.Conexion;

public class CategoriaModel {	
	
	public List<Categoria> listarCategoria() {
		List<Categoria> categorias = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT C.idCategoria, C.nombre, C.descripcion FROM categoria C ORDER BY 1 ASC";
			pstm = conexion.prepareStatement(sentenciaSQL);

			// Se obtiene el resultado de la base de datos
			rs = pstm.executeQuery();

			// Se recorre el resultado y se almacena en el ArrayList
			Categoria categoria = null;

			while (rs.next()) {
				categoria = new Categoria();
				categoria.setIdCategoria(rs.getString(1));
				categoria.setNombre(rs.getString(2));
				categoria.setDescripcion(rs.getString(3));

				categorias.add(categoria);
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

		return categorias;
	}

	public int insertarCategoria(Categoria categoria) {
		int salida = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "INSERT INTO categoria VALUES(?,?,?)";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, categoria.getIdCategoria());
			pstm.setString(2, categoria.getNombre());
			pstm.setString(3, categoria.getDescripcion());

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

	public int eliminarCategoria(String idCategoria) {
		int eliminados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "DELETE FROM categoria WHERE idCategoria=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, idCategoria);

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

	public int actualizarCategoria(Categoria categoria) {
		int actualizados = -1;

		Connection conexion = null;
		PreparedStatement pstm = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "UPDATE categoria SET nombre=?, descripcion=? WHERE idCategoria=?";
			pstm = conexion.prepareStatement(sentenciaSQL);
			pstm.setString(1, categoria.getNombre());
			pstm.setString(2, categoria.getDescripcion());
			pstm.setString(3, categoria.getIdCategoria());

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
	public String getUltimoCodigoCategoria() {
		String ultimoId = "";
		
		// Si hay categorías registradas
		List<Categoria> categorias = this.listarCategoria();
		int nroCategorias = categorias.size();
		if (categorias.size() > 0) {
			// Se obtiene el último código registrado
			ultimoId = categorias.get(nroCategorias - 1).getIdCategoria();
		}
		
		return ultimoId;
	}
	
	// Obtiene el último código. Si no hay registros, obtiene el String vacío
	public String getUltimoCodigoCategoria2() {
		String ultimoId = "";

		Connection conexion = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {
			// Se obtiene la conexión y se prepara la sentencia SQL
			conexion = new Conexion().getConexion();
			String sentenciaSQL = "SELECT C.idCategoria FROM categoria C ORDER BY 1 DESC LIMIT 1"; // MySQL

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
	
	public String generarCodigoCategoria() {
		// String idCategoria = this.getUltimoCodigoCategoria();
		String idCategoria = this.getUltimoCodigoCategoria2();
		int numero;
		
		if (idCategoria.length() == 0) {
			numero = 1;
			
		} else {
			numero = Integer.parseInt(idCategoria.substring(3));
			numero++;
		}
		
		idCategoria = "CAT" + String.format("%03d", numero);
		
		return idCategoria;
	}
	
	public Categoria getCategoria(String idCategoria) {
		Categoria categoria = null;
		List<Categoria> categorias = this.listarCategoria();
		
		for (Categoria c : categorias) {
			if (c.getIdCategoria().equalsIgnoreCase(idCategoria)) {
				categoria = c;
				break;
			}
		}
		
		return categoria;
	}
	
	// Obtiene la categoría por nombre
	public Categoria getCategoriaXNombre(String nombre) {
		Categoria categoria = null;
		List<Categoria> categorias = this.listarCategoria();
		
		for (Categoria c : categorias) {
			if (c.getNombre().equalsIgnoreCase(nombre)) {
				categoria = c;
				break;
			}
		}
		
		return categoria;
	}
}
