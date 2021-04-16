package modelBanco;

import java.util.StringTokenizer;

public class ClienteDP
{
	// Atributos
	private String nocta, nombre, tipo;
	private int saldo;

	// Constructores
	public ClienteDP()
	{
		this.nocta = "";
		this.nombre = "";
		this.tipo = "";
		this.saldo = 0;
	}

	public ClienteDP(String datos)
	{
		StringTokenizer st = new StringTokenizer(datos,"_");

		this.nocta = st.nextToken();
		this.nombre = st.nextToken();
		this.tipo = st.nextToken();
		this.saldo = Integer.parseInt(st.nextToken());
	}

	// Accessors (geters)
	public String getNocta()
	{
		return this.nocta;
	}

	public String getNombre()
	{
		return this.nombre;
	}

	public String getTipo()
	{
		return this.tipo;
	}

	public int getSaldo()
	{
		return this.saldo;
	}

	// Mutators  (seters)
	public void setNocta(String ncta)
	{
		this.nocta = ncta;
	}

	public void setNombre(String name)
	{
		this.nombre = name;
	}

	public void setTipo(String tcta)
	{
		this.tipo = tcta;
	}

	public void setSaldo(int cantidad)
	{
		this.saldo = cantidad;
	}

	public String toString()
	{
		return nocta + "_" + nombre + "_" + tipo + "_" + saldo;
	}

	public String toStringSQL()
	{
		return "'"+nocta+"','"+nombre+"','"+tipo+"',"+saldo;
	}

	public String toStringHtml(){
		return "<tr><td>"+nocta+"</td><td>"+nombre+"</td><td>"+tipo+"</td><td>"+saldo+"</td></tr>";
	}

	public String toStringJson1()
	{
		return "{nocta:'"+nocta+"',nombre:'"+nombre+"',tipo:'"+tipo+"',saldo:'"+saldo+"'}";
	}

	public String toStringJson2()
	{
		return "{'nocta':'"+nocta+"','nombre':'"+nombre+"','tipo':'"+tipo+"','saldo':'"+saldo+"'}";
	}

	public String toStringJson3()
	{
		return "{\"nocta\":\""+nocta+"\",\"nombre\":\""+nombre+"\",\"tipo\":\""+tipo+"\",\"saldo\":\""+saldo+"\"}";
	}
}