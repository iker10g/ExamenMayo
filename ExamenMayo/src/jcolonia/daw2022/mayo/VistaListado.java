package jcolonia.daw2022.mayo;

import java.util.Vector;


public class VistaListado {
	
	private Vector <AgendaTeléfonos> lista;
	
	
	public VistaListado(Vector<AgendaTeléfonos> vector) {
		lista = new Vector<> ();
		for(AgendaTeléfonos elemento: vector) {
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
