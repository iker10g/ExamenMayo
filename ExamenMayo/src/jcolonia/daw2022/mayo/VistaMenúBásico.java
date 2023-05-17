package jcolonia.daw2022.mayo;

import static java.lang.System.out;

import java.util.Scanner;

public class VistaMenúBásico {
	
	private Scanner sc;
	
	private int opcionElegida;

	private String[] OPCIONES_MENÚ_PRINCIPAL = { "Agregar teléfono", "Mostrar agenda", "Mostrar estado",
			"Restablecer", "SALIR" };
	private static final String TÍTULO_MENÚ_PRINCIPAL = "Agenda telefónica";
	
	
	public VistaMenúBásico(String títuloMenúPrincipal, String[] opcionesMenúPrincipal) {
		
	}
	public void VistaMenuBasico(String títuloMenúPrincipal, Scanner entrada, String[] opcionesMenúPrincipal) {
		this.sc=entrada;

		// Duplicamos lista
		this.OPCIONES_MENÚ_PRINCIPAL = new String[opcionesMenúPrincipal.length];
		for (int i = 0; i < opcionesMenúPrincipal.length; i++) {
			this.OPCIONES_MENÚ_PRINCIPAL[i] = opcionesMenúPrincipal[i];
		}
	}
	public void mostrarTítulo1() {
		System.out.println("=================");
		System.out.printf(TÍTULO_MENÚ_PRINCIPAL);
		System.out.println();
		System.out.println("=================");
		
		
	}
	public void mostrarOpciones() {
		for (int i = 0; i < OPCIONES_MENÚ_PRINCIPAL.length; i++) {
			System.out.printf("%d)  %s%n", (i + 1), OPCIONES_MENÚ_PRINCIPAL[i]);
		}
		System.out.printf("Introduzca la opcion que desa utilizar %n");
		
	}
	public int pedirOpción() {
		sc=new Scanner(System.in);
		String línea;
		boolean salir=false;

		while(!salir) {
			try {
				línea=sc.nextLine();
				opcionElegida=Integer.parseUnsignedInt(línea);
				if(opcionElegida>OPCIONES_MENÚ_PRINCIPAL.length) {
					out.println("Esta opcion no es valida.");
				}else {
					salir=true;
				}
			}catch(NumberFormatException ex){
				out.printf("\tNo es una de las opciones validas <<%s>>%n", ex.getMessage());
			}
		}	

		return opcionElegida;
		
	}
}
