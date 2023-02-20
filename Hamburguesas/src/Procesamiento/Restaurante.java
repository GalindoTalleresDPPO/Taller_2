package Procesamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Restaurante {
	
	ArrayList<Ingrediente> listaIngrediente = new ArrayList<Ingrediente>();
	ArrayList<ProductoMenu> listaMenu = new ArrayList<ProductoMenu>();
	ArrayList<Combo> listaCombos = new ArrayList<Combo>();
	
		
	public void cargarInformacion(File archivoIngredientes, File archivoMenu, File archivoCombos) 
	{
		cargarIngredientes(archivoIngredientes);
		cargarMenu(archivoMenu);
		cargarCombo(archivoCombos);
	
		
	}
	

	private void cargarIngredientes(File archivoIngredientes) {
		
		try {
			BufferedReader br=new BufferedReader(new FileReader(archivoIngredientes));
			String linea = br.readLine();
			while(linea != null) 
			{
				String[] partes =linea.split(";");
				String nombre=partes[0];
				int costo = Integer.parseInt(partes[1]);
				Ingrediente nuevoIngrediente = new Ingrediente(nombre, costo);
				listaIngrediente.add(nuevoIngrediente);
				linea = br.readLine();
				
			}
			br.close();
			System.out.print(listaIngrediente);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void cargarMenu( File archivoMenu) {
		try {
			BufferedReader br=new BufferedReader(new FileReader(archivoMenu));
			String linea = br.readLine();
			while(linea != null) 
			{
				String[] partes =linea.split(";");
				String nombre=partes[0];
				int costo = Integer.parseInt(partes[1]);
				ProductoMenu nuevoProducto = new ProductoMenu(nombre, costo);
				listaMenu.add(nuevoProducto);
				linea = br.readLine();
				
			}
			br.close();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
		
private void cargarCombo(File archivoCombos) {
	
	double descuentoFinal;
	ArrayList<ProductoMenu> listaProductosCombo = new ArrayList<ProductoMenu>();
	try {
		BufferedReader br=new BufferedReader(new FileReader(archivoCombos));
		String linea = br.readLine();
		while(linea != null) 
		{
			String[] partes =linea.split(";");
			String nombre=partes[0];
			String descuento = (partes[1]);
			if (descuento=="10%"){
				descuentoFinal= 0.1;
					
			}
			else {
				descuentoFinal=0.7;
			}
			
			for (int i=2; i<=4; i++) {
				String item = partes[i];
				
				for(int j= 0; j<listaMenu.size(); j++) {
					ProductoMenu elemento = listaMenu.get(j);
					String nombreElemento = elemento.getNombre();
					
					if (item == nombreElemento) {
						listaProductosCombo.add(elemento);
						break;
						
					}
					
				}
				
			}
			Combo nuevoCombo = new Combo(nombre, descuentoFinal, listaProductosCombo);
			listaCombos.add(nuevoCombo);
			linea = br.readLine();
			
		}
		br.close();
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}
		

	public ArrayList<ProductoMenu> getMenuBase(){
		
		return(listaMenu);
	}
	
	public ArrayList<Ingrediente> getIngredientes()
	{
	return(listaIngrediente);
		
	}
	
	

}
