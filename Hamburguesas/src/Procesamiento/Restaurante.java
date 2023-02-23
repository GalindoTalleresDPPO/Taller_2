package Procesamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Restaurante {
	 ArrayList<Ingrediente> listaIngrediente = new ArrayList<Ingrediente>();
	 ArrayList<ProductoMenu> listaMenu = new ArrayList<ProductoMenu>();
	 ArrayList<Combo> listaCombos = new ArrayList<Combo>();
	 Pedido pedidoEnCurso;
		
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
				else 
				{
					descuentoFinal=0.7;
				}
				
				for (int i=2; i<=4; i++) {
					String item = partes[i];
					for(int j= 0; j<listaMenu.size(); j++) {
						ProductoMenu elemento = listaMenu.get(j);
						String nombreElemento = elemento.getNombre();
						
						if (item.equals(nombreElemento)) {
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
	public void nuevoElementoPedido(Pedido pedido) 
	{
		boolean centinela=true;
		while (centinela) 
		{
			int opcionCombo = Integer.parseInt(input("\nQue desea?\n\n1.Combo\n2.Producto\n"));
			if (opcionCombo==2) 
			{	
				imprimirMenu();
				int opcionSeleccionada = Integer.parseInt(input("Por favor seleccione una opción\n"));
				Producto elementoMenu = listaMenu.get(opcionSeleccionada);
				int opcionSeleccionada2 = Integer.parseInt(input("\nDesea modificar el peido?\n\n1. No\n2. Si\n"));
				if (opcionSeleccionada2==2) 
				{	
					elementoMenu=agregarProductoAjustadoPedido(elementoMenu);
				}
				pedido.nuevoElementoPedido(elementoMenu);
				int opcionSeleccionada3 = Integer.parseInt(input("\nDesea anadir otro producto al Pedido?\n\n1. No\n2. Si\n"));
				if (opcionSeleccionada3==1) 
				{
					centinela=false;
				}
			}
			else 
			{
				imprimirMenuCombos();
				int opcionSeleccionada = Integer.parseInt(input("Por favor seleccione una opción\n"));
				Combo comboSeleccionado = listaCombos.get(opcionSeleccionada);
				pedido.nuevoElementoPedido(comboSeleccionado);
				int opcionSeleccionada2 = Integer.parseInt(input("\nDesea anadir otro producto al Pedido?\n\n1. No\n2. Si\n"));
				if (opcionSeleccionada2==1) {
					centinela=false;
					}
				}
			}
		}
		

	public ArrayList<ProductoMenu> getMenuBase(){
		
		return(listaMenu);
	}
	
	public ArrayList<Ingrediente> getIngredientes()
	{
	return(listaIngrediente);
		
	}
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
	private void imprimirMenu() {
		
		ArrayList<ProductoMenu> listaImprimir = listaMenu;
		System.out.println("\nPRODUCTOS\n");
		for(int i=0; i<listaImprimir.size();i++) {
			String nombreElemento=(listaImprimir.get(i)).getNombre();
			System.out.println(i+"."+nombreElemento);
		}
		
		
	}
	
	public void agregarProductoPedido(Pedido pedido) {
		
		imprimirMenu();
		int opcionSeleccionada = Integer.parseInt(input("\nSeleccione el Producto que desea adicionar al pedido: "));
		Producto producto = listaMenu.get(opcionSeleccionada);
		
		pedido.nuevoElementoPedido(producto);
		System.out.println("\nSe ha Anadido el producto "+producto.getNombre()+" al Pedido en curso");
	}
	
	private void imprimirMenuCombos(){
		System.out.println("\nCOMBOS\n");
		for(int i=0; i<listaCombos.size();i++) {
			String nombreElemento=(listaCombos.get(i)).getNombre();
			System.out.println(i+"."+nombreElemento);
		}
	
	}
	
	private Producto agregarProductoAjustadoPedido(Producto producto) {
		imprimirIngredientes();
		int opcionSeleccionada = Integer.parseInt(input("\nSeleccione ingrediente que desea agregar o eliminar\n"));
		Ingrediente ingrediente = listaIngrediente.get(opcionSeleccionada);
		int opcionSeleccionada2 = Integer.parseInt(input("\nAgregar o Elminar?\n1.Agregar\n2.Eliminar\n"));
		ProductoAjustado productoAjustado = new ProductoAjustado(producto);
		if (opcionSeleccionada2==1) {
			productoAjustado.agregados.add(ingrediente);
			String nombre =productoAjustado.getNombre();
			nombre = nombre+" CON ADICION DE " +ingrediente.getNombre();
			productoAjustado.nombre=nombre;
	;		return(productoAjustado);
		} 
		else {
			productoAjustado.eliminados.add(ingrediente);

		}
		String nombre =productoAjustado.getNombre();
		nombre = nombre+" SIN " +ingrediente.getNombre();
		productoAjustado.nombre=nombre;
;		return(productoAjustado);
	
	}
	
	private void imprimirIngredientes() {
		System.out.println("\nINGREDIENTES\n");
		for(int i=0; i<listaIngrediente.size();i++) {
			String nombreElemento=(listaIngrediente.get(i)).getNombre();
			System.out.println(i+"."+nombreElemento);
		}
	
	}
		
	

}
