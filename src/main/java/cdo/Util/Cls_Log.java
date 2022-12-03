package cdo.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cdo.Datos.Log;

public class Cls_Log 
{
	public static void insertaLog(Log log)
	{
		insertaSentenciaLogDeCarritoEnBD(log);
	}
	
	private static void insertaSentenciaLogDeCarritoEnBD(Log log)
	{
		Connection connBD = null;
		PreparedStatement pstm= null;		
		connBD = ConexionBD.abrirConexionBD();
		try
		{
			String qryLog=InicializaQueryLog(log);
			pstm = connBD.prepareStatement("{call " + "comercio_electronico.usp_EXECUTE_QUERY(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			EjecutaQuerysBD.EjecutarQuery(qryLog, "","", "", "", "",  "", "", "", "","", "", "","", "", "","", "", "", "", "", "", "","", "", "",pstm, connBD);
		}
		catch(Exception ex)
		{
			System.out.println("Error al insertar Log.\nDetalle del Error: " + ex.getMessage().toString() );
		}
		finally
		{
			try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
			try {connBD.close();} catch (SQLException e) {e.printStackTrace();}
		}
	}
	
	private static String InicializaQueryLog(Log log)
	{
		String Query =  "INSERT INTO comercio_electronico.t_log_www("
									+ "tipo_acceso,"
									+ "cve_empresa,"
									+ "cve_client,"
									+ "cve_pedido,"
									+ "accion, "
									+ "fecha_pro, "
									+ "hora_pro) "
						+ "VALUES"
									+ "('" + log.getTipo_acceso() +"',"
									+ " '" + log.getCve_empresa() + "',"
									+ " '" + log.getCve_cliente() + "',"
									+ " '" + log.getCve_pedido() + "',"
									+ " '" + log.getAccion()+ "',"
									+ " CURDATE(),"
									+ "CURTIME());";
		return Query;
	}
	
	

}

