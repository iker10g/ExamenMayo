package jcolonia.daw2022.mayo;

import java.util.Map;
import java.util.Vector;


public class VistaListado extends AgendaTeléfonos{
	
	private Vector<AgendaTeléfonos> lista;
	
	
	public VistaListado(Map<Integer, String> Map) {
		lista = new Vector<> ();
		for(AgendaTeléfonos elemento:Map {
			lista.add(elemento);
		}
	}

	public void mostrar() throws ListaException {
		if(lista.isEmpty() == true) {
			throw new ListaException();
		}
		
		System.out.println("Mostrando listado");
		for(AgendaTeléfonos elemento: lista) {
			System.out.printf("→\t%s%n", elemento);
		}
		System.out.println('\n');
		
	}

}
