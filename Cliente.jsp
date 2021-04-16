<%@ page import="java.io.*"%>
<%--<%@ page import="modelBanco.BancoADjdbc"%>--%>

<jsp:useBean id="banco" class="modelBanco.BancoADjdbc"/>
<jsp:useBean id="clientedp" class="modelBanco.ClienteDP"/>
<jsp:setProperty name="clientedp" property="*"/>


<%
	if (request.getParameter("bCapturar") == null && request.getParameter("bConsultar") == null && request.getParameter("bConsultarNCta") == null && request.getParameter("bConsultarTipo") == null){
%>


<html>
	<head>
		<title>Apache y Jsps</title>
		<!--link rel="stylesheet" type="text/css" href="../bancojsp/styles/styles.css"-->
	</head>
	<body>
		<h2>Scotia Bank</h2>
		<!--form action='../cgi-bin/ClienteController.py' method='get'-->
		<!--form action='../banco/banco' method='get'-->
		<form action='../bancojsp/Cliente.jsp' method='get'>
			
				NO. CUENTA:	 <input type='text' name='nocta'><br>
				NOMBRE:      <input type='text' name='nombre'><br>
				TIPO CUENTA: <input type='text' name='tipo'><br/>
				SALDO:       <input type='text' name='saldo'><br/><br/>
							<input type='submit' name='bCapturar' value='Capturar Datos'>
							<input type='submit' name='bConsultar' value='Consultar Clientes'>
							<input type='submit' name='bConsultarNCta' value='Consultar No Cuenta'>
							<input type='submit' name='bConsultarTipo' value='Consultar Tipo Cuenta'>
		</form>
		<image align='center' alt='IMAGEN' src='../bancojsp/images/nino.jpg'></image>
	</body>
</html>
<%
	}

	//Atributos
	

	else{
		//BancoADjdbc banco = new BancoADjdbc();
		String datos, respuesta;

		if (request.getParameter("bCapturar") != null){
			//response.sendRedirect("RespuestaServer.jsp?datos=Capturar datos...");

			// 2.1 Obtener datos del URL String
			//datos = obtenerDatos(request);
			datos = clientedp.toString();

			// 2.2 Capturar datos en BD
			//respuesta = banco.capturar(datos);
			//respuesta = datos;
			respuesta = banco.capturar(clientedp);

			// 2.3 Enviar al server el resultado de la transaccion
			response.sendRedirect("RespuestaServer1.jsp?datos="+respuesta);
		}
		if (request.getParameter("bConsultar") != null){
			//response.sendRedirect("RespuestaServer.jsp?datos=Consultar datos...");

			//1 Consultar datos de la BD
			datos=banco.consultar();

			//2 Mostrar los datos
			//response.sendRedirect("RespuestaServer.jsp?datos="+datos);
			respuestaServer(response,datos);
		}
		if (request.getParameter("bConsultarNCta") != null){
			//response.sendRedirect("RespuestaServer.jsp?datos=Consultar no. cuenta...");

			//String nocta = request.getParameter("nocta");
			String nocta = clientedp.getNocta();

			datos=banco.consultarNocta(nocta);
			//response.sendRedirect("RespuestaServer.jsp?datos="+datos);
			%>
			<jsp:forward page ="RespuestaServer.jsp">
				<jsp:param name="datos" value="<%=datos%>"/>
			</jsp:forward>

			<%
		}
		if (request.getParameter("bConsultarTipo") != null){
			//response.sendRedirect("RespuestaServer.jsp?datos=Consultar tipo cuenta...");
			
			//Obtener tipo de cuenta a consultar
			//String tcta = request.getParameter("tipo");
			String tcta = clientedp.getTipo();

			//Consultar cliente con ese tipo de cuenta
			datos=banco.consultarTipo(tcta);

			//Mostrar datos
			//response.sendRedirect("RespuestaServer.jsp?datos="+datos);
			%>
			<jsp:forward page ="RespuestaServer.jsp">
				<jsp:param name="datos" value="<%=datos%>"/>
			</jsp:forward>

			<%
		}
	}
%>

<%!
	//Metodos
	private String obtenerDatos(HttpServletRequest request){
		String nocta, nombre, tipo, saldo, datos;

		nocta = request.getParameter("nocta");
		nombre = request.getParameter("nombre");
		tipo = request.getParameter("tipo");
		saldo = request.getParameter("saldo");

		datos = nocta+"_"+nombre+"_"+tipo+"_"+saldo;

		return datos;
	}

	private void respuestaServer(HttpServletResponse response, String datosJson) throws IOException{
		PrintWriter salidaServer = response.getWriter();
		response.setContentType("text/plain");

		salidaServer.println(datosJson);
	}
%>