package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import entidad.Parametro;
import util.Conexion;

public class ParametrosModel {
	public HashMap<String, Parametro> listarParametros() {
		HashMap<String, Parametro> mapParametros = new HashMap<>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Parametro parametro = null;

		try {
			cn = new Conexion().getConexion();
			pstm = cn.prepareStatement("SELECT * FROM parametros");
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				parametro = new Parametro();
				parametro.setIdParametro(rs.getInt("idParametro"));
				parametro.setNombre(rs.getString("nombre"));
				parametro.setValor(rs.getString("valor"));
				parametro.setObs(rs.getString("obs"));
				
				mapParametros.put(parametro.getNombre(), parametro);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (pstm != null) {
					pstm.close();
				}
				
				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return mapParametros;
	}
}
