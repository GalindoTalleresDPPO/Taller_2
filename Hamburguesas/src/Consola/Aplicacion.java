package Consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Procesamiento.Pedido;
import Procesamiento.Producto;
import Procesamiento.ProductoMenu;
import Procesamiento.Restaurante;

public class Aplicacion {
	Restaurante restaurante = new Restaurante();
	public static Pedido pedidoEnCurso = null;
	
	
	public void ejecutarAplicacion()
	{

		
		System.out.println("Selecciona una opcion: ");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción:\n"));
				if (opcion_seleccionada == 1) {
					menu();
				}
				
				else if (opcion_seleccionada == 2) {
			
					String direccion = input("Ingresa tu direccion: ");
					String nombreCliente = input("Ingrese Su Nombre: ");
					
					
					Pedido pedido=new Pedido(direccion, nombreCliente);
					pedidoEnCurso=pedido;
					restaurante.nuevoElementoPedido(pedido);
					
					System.out.println("\n******IMPORTANTE******\n\nEl ID de su pedido es: "+pedido.getID());
					
				}
				
				else if (opcion_seleccionada == 3) {
					restaurante.agregarProductoPedido(pedidoEnCurso);
					
				}
				
				else if (opcion_seleccionada == 4) {
					int idPedido = Integer.parseInt(input("Digite el ID del pedido:\n"));
					pedidoEnCurso.infoPedido(idPedido);
					
					
				}
					
				
				else if (opcion_seleccionada == 5)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
		
	}
	
	
	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Mostrar el  Menu");
		System.out.println("2. Iniciar un pedido");
		System.out.println("3. Agregar Elementos al pedido en curso");
		System.out.println("4. Info Pedido con el ID");
		System.out.println("6. Salir de la aplicación\n");
	}
	
	
	public void menu() {
		File ingredientes = new File("./data/ingredientes.txt");
		File combos = new File("./data/combos.txt");
		File menu = new File("./data/menu.txt");
		restaurante.cargarInformacion(ingredientes, menu, combos);
		
		ArrayList<ProductoMenu> listaMenu =restaurante.getMenuBase();
		for(int i=0; i<listaMenu.size(); i++ )
		{
			ProductoMenu producto = listaMenu.get(i);
			String nombreProducto = producto.getNombre();
			int precio = producto.getPrecio();
			
			System.out.println(i+". "+nombreProducto+"\t"+"precio: "+ precio);
		}
		
		//Ingredientes[] ingredientes = restaurante.getIngredientes();

		
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
	

	public static void main(String[] args) {
		
		Aplicacion aplicacion= new Aplicacion();
		aplicacion.ejecutarAplicacion();
		
		
		
		
		
		
		
		
		
	}

}
