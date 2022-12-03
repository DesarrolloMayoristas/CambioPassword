package cdo.Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cdo.Datos.Log;
import cdo.Util.Cls_Log;
import cdo.Util.ConexionBD;

public class GestorPassword {

	public String actualizarPassword(String passInicio, String passInicioDecode, String passEdo, String passEdoDecode, String opcion, String cliente, String centro) 
	{
		String respuesta = "false";
		Connection connBD = null;
		PreparedStatement pstm = null;
		connBD = ConexionBD.abrirConexionBD();
		String qry = "";
		String accion = "";
//		Log log1=new Log(01,Integer.parseInt(centro),Integer.parseInt(cliente) ,0, "Password mandado  a "+accion+". "+"Inicio: "+passInicio+". InicioDecode: "+passInicioDecode+". Edo: "+passEdo+". EdoDecode: "+passEdoDecode);
//		Cls_Log.insertaLog(log1);
		try 
		{
			if (opcion.equals("3")) 
			{
				qry = "UPDATE comercio_electronico.c_cliente SET"
						+ " contrasena = '"+passInicio+"', "
						+ "huella = '"+passInicioDecode+"', "
						+ "huella2 = '"+passEdo+"',"
						+ " huella3 = '"+passEdoDecode+"' ,fecha_contrasena = curdate() "
						+ "WHERE cve_client = '"+cliente+"' AND cve_centro = '"+centro+"'";
				accion = "AMBOS PASSWORDS";
			}
			else if (opcion.equals("2"))
			{
				qry = "UPDATE comercio_electronico.c_cliente SET"
						+ " huella2 = '"+passEdo+"',"
						+ " huella3 = '"+passEdoDecode+"' ,fecha_contrasena = curdate() "
						+ "WHERE cve_client = '"+cliente+"' AND cve_centro = '"+centro+"'";
				accion = "ESTADO CUENTA";
			}
			else if (opcion.equals("1")) 
			{
				qry = "UPDATE comercio_electronico.c_cliente SET"
						+ " contrasena = '"+passInicio+"', "
						+ "huella = '"+passInicioDecode+"' ,fecha_contrasena = curdate() "
						+ "WHERE cve_client = '"+cliente+"' AND cve_centro = '"+centro+"'";
				accion = "INICIO SESION";
			}
			System.out.println(qry);
			pstm = connBD.prepareStatement(qry);
			pstm.executeUpdate();
			Log log=new Log(01,Integer.parseInt(centro),Integer.parseInt(cliente) ,0, "Password actualizado correctamente a "+accion+". "+"Inicio: "+passInicio+". InicioDecode: "+passInicioDecode+". Edo: "+passEdo+". EdoDecode: "+passEdoDecode);
			Cls_Log.insertaLog(log);
			respuesta = "true";
		}
		catch(Exception ex)
		{
			Log log=new Log(01,Integer.parseInt(centro),Integer.parseInt(cliente) ,0, "[Error: Cambiar password a "+accion+". "+"Inicio: "+passInicio+". InicioDecode: "+passInicioDecode+". Edo: "+passEdo+". EdoDecode: "+passEdoDecode+",  Clase: GestorPasswrod,  Detalle: " + ex.getMessage().toString() +"]");
			Cls_Log.insertaLog(log);
		}
		finally
		{
			try {connBD.close();} catch (SQLException e) {e.printStackTrace();}
			try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return respuesta;
	}

	public static  boolean validarCaducidad(String centro, String clave, String cliente) throws java.text.ParseException 
	{
		boolean rsp = false;
		Connection connBD = null;
		PreparedStatement pstm = null;
		connBD = ConexionBD.abrirConexionBD();
		String fecha = "";
		String fechaActual ="";
		
		
		String horaActual = "";
		try
		{
			String qry = "SELECT ifnull(huella4,'') as fecha, curdate() as fechaActual, curtime() as horaActual  FROM `c_cliente` where cve_centro = '"+centro+"' and cve_client = '"+clave+"'";
			System.out.println(qry);
			pstm = connBD.prepareStatement(qry);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				fecha = rs.getString("fecha");
				fechaActual = rs.getString("fechaActual");
				horaActual = rs.getString("horaActual");
			}
			System.out.println(fecha);
			if (!fecha.equals(""))
			{
				String [] fechaSplit = fecha.split(",");
				if (fechaSplit[0].equals(fechaActual)) 
				{
					  String horaInicial = horaActual;
				        String horaFinal = fechaSplit[1];
				        String n = datedifferencehoras(horaFinal,horaActual).replace("-","");
				        if (Integer.parseInt(n) <=15) 
				        {
							rsp = true;
						}
					
				}
				else
				{
					Log log=new Log(01,Integer.parseInt(centro),Integer.parseInt(cliente) ,0, "Diferencia mayor a 15 minutos para la actualizacion de password");
					Cls_Log.insertaLog(log);
				}
			}
		}
		
		catch(Exception ex)
		{
			Log log=new Log(01,Integer.parseInt(centro),Integer.parseInt(cliente) ,0, "Error al validar fechas para atualizacion de password: "+ex.toString());
			Cls_Log.insertaLog(log);
		}
		finally
		{
			try {connBD.close();} catch (SQLException e) {e.printStackTrace();}
			try {pstm.close();} catch (SQLException e) {e.printStackTrace();}
		}
		return rsp;
	}
	
	
	public static String datedifferencehoras(String dateStart, String dateStop)
	{
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		 
		Date d1 = null;
		Date d2 = null;
		long diffHours=0;
		long diffMinutes = 0;
		String difhoras="";
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
 
			//in milliseconds
			long diff = d2.getTime() - d1.getTime();
 
			long diffSeconds = diff / 1000 % 60;
			 diffMinutes = diff / (60 * 1000) % 60;
			diffHours = diff / (60 * 60 * 1000) ;
			//long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
 
			
			
			
           difhoras =String.valueOf(diffHours);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return String.valueOf(diffMinutes);
	}

}
