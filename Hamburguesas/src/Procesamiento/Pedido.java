package Procesamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Pedido {
	int numeroPedido=0;
	int idPedido;
	String direccionCliente;
	String nombreCliente;
	public static int contadorIDPedido=0;
	ArrayList<Producto> listaPedido = new ArrayList<Producto>();
	
		public Pedido(String nombreCliente, String direccionCliente)
		{
			this.nombreCliente=nombreCliente;
			this.direccionCliente=direccionCliente;
			idPedido=contadorIDPedido+1;
			contadorIDPedido=contadorIDPedido+1;
			numeroPedido = contadorIDPedido;
			
		}
		
		private int getPrecioNetoPedido() {
			int precioNeto=0;
			int precioProducto;
			for(int i=0; i<listaPedido.size();i++) 
			{
				Producto producto = listaPedido.get(i);
				precioProducto=producto.getPrecio();
				precioNeto=precioNeto+precioProducto;
			}
			return(precioNeto);
		}
		
	
		private double getPrecioIVAPedido() {
			int precioNeto=getPrecioNetoPedido();
			double precioTotal=precioNeto*1.02;
			
			return(precioTotal);
			
		}
		private void generarTextoFactura() {
			
		}
		public void GuardarFactura(File archivo) {
			
		}
		public void nuevoElementoPedido(Producto elemento) {
			listaPedido.add(elemento);	
		}
		
		
		public void infoPedido(int id) {
			if (id == idPedido) {
				if (listaPedido.size() !=0) {
					System.out.println("Nombre :"+nombreCliente+"\n");
					System.out.println("Direccion :"+direccionCliente+"\n");
					System.out.println("Numero :"+numeroPedido+"\n");
					System.out.println("ID :"+idPedido+"\n");
					System.out.println("Productos:\n");
					for(int i=0; i<listaPedido.size();i++) {
						System.out.println((i+1)+". " +listaPedido.get(i).getNombre());
					}
					System.out.println("\nPrecio con sin IVA:\n"+getPrecioNetoPedido()+"\n");
					System.out.println("\nPrecio con con IVA:\n"+getPrecioIVAPedido()+"\n");
					
				}
				
			}
			
			
		}
		
		public int getID() {
			return(idPedido);
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
		
}



