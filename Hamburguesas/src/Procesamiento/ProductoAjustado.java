package Procesamiento;

import java.util.ArrayList;

public class ProductoAjustado implements Producto{
	
	ArrayList<Ingrediente> agregados = new ArrayList<Ingrediente>();
	ArrayList<Ingrediente> eliminados = new ArrayList<Ingrediente>();
	
	String nombre;
	int precio;
	int precioAjustado=0;
	
	public ProductoAjustado (Producto base) {
		
		nombre = base.getNombre();
		precio= base.getPrecio();
		
	}
	
	public int getPrecio() {
		return(precio);
	}
	
	public int getPrecioAjustado(Restaurante restaurante) {
		if (agregados.size()!=0) {
		
		for(int i=0;i<agregados.size();i++) {
			String nombreIngredienteAnadido = agregados.get(i).getNombre();
			
			for (int j=0; j<restaurante.listaIngrediente.size(); j++) {
				
				Ingrediente ingrediente=restaurante.getIngredientes().get(j);
			
				String nombre=ingrediente.getNombre();
				if (nombreIngredienteAnadido == nombre ) {
					precioAjustado=precio+(ingrediente.getCostoAdicional());
					}
				}
			
			}
		}
		
		else {
			precioAjustado=precio;
		}
		
		return (precioAjustado);
	}

	public String getNombre()
	{
		return nombre;
	}


}
