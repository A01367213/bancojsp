import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.IOException;

public class ServletBanco extends HttpServlet{

	//Atributos
	private BancoADjdbc banco = new BancoADjdbc();
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

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

		String transaccion, datos, respuesta;

		// 1 Checar transacción
		
		transaccion = request.getParameter("boton");

		// 2 Transacción Capturar:
		if (transaccion.equals("Capturar Datos")){

			// 2.1 Obtener datos del URL String
			datos = obtenerDatos(request);

			// 2.2 Capturar datos en BD
			respuesta = banco.capturar(datos);

			// 2.3 Enviar al server el resultado de la transaccion
			response.sendRedirect("RespuestaServer1.jsp?datos="+respuesta);
		}

		if (transaccion.equals("Consultar Clientes")){
			
			//1 Consultar datos de la BD
			datos=banco.consultar();

			//2 Mostrar los datos
			//response.sendRedirect("RespuestaServer.jsp?datos="+datos);
			respuestaServer(response,datos);
		}

		if (transaccion.equals("Consultar Tipo Cuenta")){

			//Obtener tipo de cuenta a consultar
			String tcta = request.getParameter("tipo");

			//Consultar cliente con ese tipo de cuenta
			datos=banco.consultarTipo(tcta);

			//Mostrar datos
			response.sendRedirect("RespuestaServer.jsp?datos="+datos);
		}

		if (transaccion.equals("Consultar No Cuenta")){
			String nocta = request.getParameter("nocta");

			datos=banco.consultarNocta(nocta);
			response.sendRedirect("RespuestaServer.jsp?datos="+datos);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doGet(request, response);
	}
}