package cdo.Util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBD {




	static final private String NOMBRE_BD = "comercio_electronico";
    static final private String USUARIO_BD = "desmay";
static final private String PASSWORD_BD = "D3sM4y2020";
static final private String SERVIDOR_BD = "deswebcdobd18.corprama.com.mx";
//static final private String USUARIO_BD = "admin.www";
//static final private String PASSWORD_BD = "hENNR5eIGwMz";


	public static Connection abrirConexionBD()
	{		
		String  nombreServidorBD = "jdbc:mysql://" + SERVIDOR_BD + ":3306/" + NOMBRE_BD;
		Connection conexionBD=null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conexionBD = DriverManager.getConnection(nombreServidorBD,USUARIO_BD,PASSWORD_BD); 
		}
		catch(Exception ex)
		{
			System.out.println("Error al abrir la conexión a la BD. "+ex.getMessage().toString());
			System.out.println("nombreServidorBD: "+ nombreServidorBD +" usr: "+USUARIO_BD + " psw: "+PASSWORD_BD);
		}
		return conexionBD;
	
	}

//	CONEXION A DESARROLLO
	
	public static Connection AbrirConexionBDD(String cdo)
	{		
		Connection conexionBD=null;
		String  nombreServidorBD = "jdbc:mysql://descdf:3306/"+cdo.toUpperCase()+"" ;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conexionBD = DriverManager.getConnection(nombreServidorBD,USUARIO_BD,PASSWORD_BD); 
		}
		catch(Exception ex)
		{
			System.out.println(ex);
			System.out.println("Error al abrir la conexión a la BD.");
		}
		return conexionBD;
	}
//	

	
}
