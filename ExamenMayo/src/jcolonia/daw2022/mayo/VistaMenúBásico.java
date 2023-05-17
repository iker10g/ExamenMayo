package jcolonia.daw2022.mayo;

import static java.lang.System.out;

import java.util.Scanner;

/**
 * Clase que gestiona el menu principal que aparece al usuario al iniciar 
 * el programa 
 * @author dawm1-07
 *
 */
public class VistaMenúBásico  {
	
	/**
	 * Scanner
	 */
	private Scanner sc;
	/** La opcion elegida. */
	private int opcionElegida;
	/** El texto opciones. */
	private String[] OPCIONES_MENÚ_PRINCIPAL = { "Agregar teléfono", "Mostrar agenda", "Mostrar estado",
			"Restablecer", "SALIR" };
	/** El titulo. */
	private static final String TÍTULO_MENÚ_PRINCIPAL = "Agenda telefónica";
	
	/** Constructor en el cual se le pasan el titulo y las distintas opciones de menu . 
	 * @param títuloMenúPrincipal titulo
	 * @param opcionesMenúPrincipal las opciones */
	public VistaMenúBásico(String títuloMenúPrincipal, String[] opcionesMenúPrincipal) {
		
	}
	/** Constructor el cual nos muestra las diferentes opciones que contine el menu  . 
	 * @param títuloMenúPrincipal titulo
	 * @param entrada Scanner
	 * @param opcionesMenúPrincipal las opciones */
	public void VistaMenuBasico(String títuloMenúPrincipal, Scanner entrada, String[] opcionesMenúPrincipal) {
		this.sc=entrada;

		this.OPCIONES_MENÚ_PRINCIPAL = new String[opcionesMenúPrincipal.length];
		for (int i = 0; i < opcionesMenúPrincipal.length; i++) {
			this.OPCIONES_MENÚ_PRINCIPAL[i] = opcionesMenúPrincipal[i];
		}
	}
	/**
	 * Nos muestra el titulo
	 */
	public void mostrarTítulo1() {
		System.out.println("=================");
		System.out.printf(TÍTULO_MENÚ_PRINCIPAL);
		System.out.println();
		System.out.println("=================");
		
		
	}
	/**
	 * Mustra las opciones
	 */
	public void mostrarOpciones() {
		for (int i = 0; i < OPCIONES_MENÚ_PRINCIPAL.length; i++) {
			System.out.printf("%d)  %s%n", (i + 1), OPCIONES_MENÚ_PRINCIPAL[i]);
		}
		System.out.printf("Introduzca la opcion que desa utilizar %n");
		
	}
	/**
	 * Nos pide que eligamos la opcione que queramos de el menu
	 * @return opcionElegida
	 */
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
