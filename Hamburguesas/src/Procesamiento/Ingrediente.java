package Procesamiento;

public class Ingrediente 
{
	private String nombre;
	private int costoAdicional;
	
	
	
	public Ingrediente(String nombre, int costoAdicional) 
	{
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
		
		
	}

	
	public String getNombre() 
	{
		return nombre;
		
	}
	
	public int costoAdicional()
	{
		return costoAdicional;
		
	}
}
