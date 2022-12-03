package cdo.Web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cdo.Persistencia.GestorPassword;
import cdo.Util.ConexionBD;

/**
 * Servlet implementation class CambiarPasswordServlet
 */
public class CambiarPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiarPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session != null)
		{
			try
			{
				session.setAttribute("clave", String.valueOf(request.getParameter("clave")));
				session.setAttribute("cliente", String.valueOf(request.getParameter("cliente")));
				session.setAttribute("centro", String.valueOf(request.getParameter("centro")));
				if (validarNumerico(String.valueOf(session.getAttribute("clave"))) && 
						validarNumerico(String.valueOf(session.getAttribute("centro")))) 
				{
					if (!obtenerCDO(Integer.parseInt(String.valueOf(session.getAttribute("centro")))).equals("")) 
					{
						session.setAttribute("centroDescripcion",obtenerCDO(Integer.parseInt(String.valueOf(session.getAttribute("centro")))) );
						if (!String.valueOf(session.getAttribute("clave")).equals("null") && !String.valueOf(session.getAttribute("cliente")).equals("null") 
								&& !String.valueOf(session.getAttribute("centro")).equals("null")) 
						{
							if (GestorPassword.validarCaducidad(String.valueOf(session.getAttribute("centro")),String.valueOf(session.getAttribute("clave")),String.valueOf(session.getAttribute("cliente"))))
							{
								RedireccionarVista(request, response,"CambiarPassword.jsp");
							}
							else
							{
								RedireccionarVista(request, response,"EnlaceCaducado.jsp");
							}
							
						}
						else
						{
							RedireccionarVista(request, response,"EnlaceErroneo.jsp");	
						}
					}
					else
					{
						RedireccionarVista(request, response,"EnlaceErroneo.jsp");
					}
				}
				else
				{
					RedireccionarVista(request, response,"EnlaceErroneo.jsp");	
				}
				
			}
			catch (Exception ex)
			{
				RedireccionarVista(request, response,"EnlaceErroneo.jsp");	
			}
			
			
		}
		else
		{
			if(session == null)
			{
				request.getRequestDispatcher("/EnlaceErroneo.jsp").forward(request, response);
				return;
			}
		}
		
	}

	public String obtenerCDO(int cdo) 
	{
		switch (cdo) 
		{
		case 7:
			return "cdf";
		case 5:
			return "cdm";
		case 8:
			return "cd2";
		case 4:
			return "cdl";
		default:
			return "";
		}
	}
	
	private boolean validarNumerico(String valor) 
	{
		
		try 
		{
			Integer.parseInt(valor);
			return true;
		} catch (NumberFormatException nfe)
		{
			return false;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void RedireccionarVista(HttpServletRequest request, HttpServletResponse response, String vista)
	{
		try
		{
			RequestDispatcher rdIndex = request.getRequestDispatcher("jsp/" + vista);			    	
		    rdIndex.forward(request, response);
		}
		catch(Exception ex)
		{
			System.out.println("Error al re-direccionar vista." + ex.getMessage().toString());
		}
	}

}
