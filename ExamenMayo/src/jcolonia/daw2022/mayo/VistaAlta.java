package jcolonia.daw2022.mayo;

import java.util.Scanner;

public class VistaAlta {

	private Scanner sc;
	
	

	public VistaAlta(Scanner entrada, Vista lista) {
		this.sc=sc;
	}



	public void cargarTelefono() {
		String Texto;
		sc=new Scanner(System.in);
		System.out.print("Introduzca un nuevo telefono");
		String numero=sc.nextLine();
		System.out.print("Introduzca el nombre ");
		String numero1=sc.nextLine();
		System.out.printf("Has introducido correctamente los datos %n");
		
	}
	



}
