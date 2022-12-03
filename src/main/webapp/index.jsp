<html>
<body>
    <%
          RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/CambiarPassword?vista=index");
		  dispatcher.forward(request, response);
	%>
</body>
</html>
