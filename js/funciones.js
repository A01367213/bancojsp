	var xhr;
	
	
	function obtenerResultadoJson()
	{	
		var libros;
		var datos="";
		
		if(xhr.readyState == 4)
		{
			//document.getElementById("transaccion").innerHTML = xhr.responseText;
			
			clientes = eval("("+xhr.responseText+")");
			//clientes = JSON.parse(xhr.responseText); //soporta Json3
			
			document.getElementById("transaccion").innerHTML = xhr.responseText;
			//document.getElementById("transaccion").innerHTML = clientes;
			
			datos = "<table border=1>";
			for(i=0; i<clientes.length; i++)
			{
				//datos = datos + clientes[i].nocta+"   "+clientes[i].nombre+"   "+clientes[i].nombre+"   "+clientes[i].saldo+"<br/>";
				datos = datos +"<tr><td>"+clientes[i].nocta+"</td><td>"+clientes[i].nombre+"</td><td>"+clientes[i].tipo+"</td><td>"+clientes[i].saldo+"</td></tr>";
			}
			datos = datos + "</table>";
			
			document.getElementById("transaccion").innerHTML = datos;
			
		}
	}
	
	function establecerConexionConsultar(urlString)
	{
		xhr.open("get",urlString,true);
		//xhr.onreadystatechange=obtenerResultado;
		xhr.onreadystatechange=obtenerResultadoJson;
		xhr.send(null);
	}
	
	function obtenerResultado()
	{	
		if(xhr.readyState == 4)
		{
			document.getElementById("transaccion").innerHTML = xhr.responseText;
		}
	}
	
	function establecerConexion(urlString)
	{
		xhr.open("get",urlString,true);
		xhr.onreadystatechange=obtenerResultado;
		xhr.send(null);
	}
	
	function consultarTipo()
	{
		// 1. Obtener el valor del Tipo de Cuenta del TextField correspondiente
		var tcta = document.getElementById("tipo").value;
		
		// 2. Preparar el URL String
		var urlString = "../bancojsp/Cliente.jsp?bConsultarTipo=consultar&tipo="+tcta;
		
		// 3. Crear el objeto de conexion
		xhr = new XMLHttpRequest();
		
		// 4. Establecer conexion y ejecutar transaccion
		establecerConexion(urlString);
	}
	
	function consultar()
	{
		
		// 1. Preparar el URL String
		var urlString = "../bancojsp/Cliente.jsp?bConsultar=consultar";
		
		// 1. Crear el objeto de conexion
		xhr = new XMLHttpRequest();
		
		// 3. Establecer conexion y ejecutar transaccion
		establecerConexionConsultar(urlString);
	}
