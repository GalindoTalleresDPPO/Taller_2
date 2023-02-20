package Consola;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import Procesamiento.Restaurante;

public class Aplicacion {
	Restaurante restaurante = new Restaurante();
	
	
	
	public void ejecutarAplicacion()
	{
		
		System.out.println("Estadísticas sobre los Juegos Olímpicos\n");

		boolean continuar = true;
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1) {
					menu();
					
				}
				
				else if (opcion_seleccionada == 2) {
					
				}
					
				
				else if (opcion_seleccionada == 14)
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
		System.out.println("1. Mostrar Menu");
		System.out.println("2. Consultar los atletas de un año dado");
		System.out.println("3. Consultar las medallas de un atleta en un periodo");
		System.out.println("4. Consultar los atletas de un país dado");
		System.out.println("5. Consultar el país con más medallistas");
		System.out.println("6. Consultar todos los medallistas de un evento dado");
		System.out.println("7. Consultar los atletas con un mínimo de medallas");
		System.out.println("8. Consultar el atleta estrella de todos los tiempos");
		System.out.println("9. Consultar mejor país en un evento");
		System.out.println("10. Consultar el atleta todoterreno");
		System.out.println("11. Consultar los medallistas por país y género");
		System.out.println("12. Consultar el porcentaje de atletas que son medallistas");
		System.out.println("13. Consultar pais por atleta");
		System.out.println("14. Salir de la aplicación\n");
	}
	
	
	public void menu() {
		File ingredientes = new File("./data/ingredientes.txt");
		File combos = new File("./data/combos.txt");
		File menu = new File("./data/menu.txt");
		//Ingredientes[] ingredientes = restaurante.getIngredientes();
		
		restaurante.cargarInformacion(ingredientes, menu, combos);
		
	}
	

	
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
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
