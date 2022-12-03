package cdo.Web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cdo.Persistencia.GestorPassword;

public class PasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GestorPassword gPassword;
	
    public PasswordServlet() 
    {
    	 super();
         gPassword = new GestorPassword();   
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(false);
		if(session != null)
		{
			verificaPeticionOrigen(request,response, session);
		}
		else
		{
			if(session == null)
			{
				request.getRequestDispatcher("/index.jsp").forward(request, response);
				return;
			}
		}
	
	}

	private void verificaPeticionOrigen(HttpServletRequest request, HttpServletResponse response, HttpSession session) 
	{
		String operacion = String.valueOf(request.getParameter("operacion"));
		if (operacion.equals("ActualizarPasswrod")) 
		{
			String var = gPassword.actualizarPassword(
					String.valueOf(request.getParameter("passInicio")),String.valueOf(request.getParameter("passInicioDecode")),
					String.valueOf(request.getParameter("passEdo")),String.valueOf(request.getParameter("passEdoDecode")),String.valueOf(request.getParameter("opcion")),String.valueOf(session.getAttribute("clave")),String.valueOf(session.getAttribute("centro")));
					
			enviarRespuestaTextoJS(request, response,var);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
	private void enviarRespuestaTextoJS(HttpServletRequest request, HttpServletResponse response, String respuesta)
	{
		try
		{
			PrintWriter out = response.getWriter();
		    out.write(respuesta);
		}
		catch(Exception ex)
		{
			System.out.println("Error al re-direccionar vista." + ex.getMessage().toString());
		}
	}
}
