package Procesamiento;

import java.util.ArrayList;

public class Combo implements Producto
{
	
	double descuento;
	String nombreCombo;
	ArrayList<ProductoMenu> listaCombo;
	public Combo(String nombreCombo, double descuento, ArrayList<ProductoMenu> listaCombo) 
	{
		
		this.nombreCombo=nombreCombo;
		this.descuento=descuento;
		this.listaCombo=listaCombo;
		
	}
	
	public int getPrecio(){
		int precioTotal=0;
		
		for (int i=0; i<listaCombo.size(); i++) {
			ProductoMenu producto = listaCombo.get(i);
			int precioProducto=producto.getPrecio();
			precioTotal=precioTotal + precioProducto; 
		}
		return (precioTotal);
	}
	
	public String getNombre() {
		return(nombreCombo);
	}
	

}
