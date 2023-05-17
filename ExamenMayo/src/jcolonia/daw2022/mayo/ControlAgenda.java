package jcolonia.daw2022.mayo;

import java.util.Scanner;

/**
 * Gestión de números de teléfono.
 * 
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 * @version 2023.3.2_incompleta (20230516)
 */
public class ControlAgenda {
	/**
	 * Texto identificativo de las funciones de la aplicación que aparecerán en el
	 * menú principal.
	 */
	private static final String[] OPCIONES_MENÚ_PRINCIPAL = { "Agregar teléfono", "Mostrar agenda", "Mostrar estado",
			"Restablecer", "SALIR" };

	/** Título de la aplicación. Se mostrará como encabezado del menú principal. */
	private static final String TÍTULO_MENÚ_PRINCIPAL = "Agenda telefónica";
	
	private Scanner entrada;

	/** Almacen de números de teléfono. */
	private AgendaTeléfonos agenda;
	/** Marca de que la agenda actual fue importada. */
	private boolean agendaFueImportada;
	/** Marca de que la agenda actual fue exportada. */
	private boolean agendaFueExportada;
	/** Marca de que la agenda actual fue borrada. */
	private boolean agendaFueBorrada;

	/** Vista del menú principal. */
	private VistaMenúBásico menúPrincipal;

	/**
	 * Crea una nueva agenda y quita todas las marcas de estado.
	 * 
	 * @see #agendaFueImportada
	 * @see #agendaFueExportada
	 * @see #agendaFueBorrada
	 */
	public ControlAgenda() {
		agenda = new AgendaTeléfonos();

		agendaFueImportada = false;
		agendaFueExportada = false;
		agendaFueBorrada = false;
	}

	/**
	 * Gestiona el menú principal y ejecuta las operaciones elegidas.
	 */
	private void buclePrincipal() {
		int opciónElegida;
		boolean fin = false;

		menúPrincipal = new VistaMenúBásico(TÍTULO_MENÚ_PRINCIPAL, OPCIONES_MENÚ_PRINCIPAL);

		// Bucle general
		do {
			menúPrincipal.mostrarTítulo1();
			menúPrincipal.mostrarOpciones();
			opciónElegida = menúPrincipal.pedirOpción();
			switch (opciónElegida) {
			case 0:
			case 5: // SALIR
				fin = true;
				Vista.mostrarAviso("¡¡¡A-D-I-O-S!!");
				break;
			case 1: // Opción 1: Entrada datos
				cargarTeléfono();
			break;
			case 2: // Opción 2: Mostrar listado
			    mostrarAgenda();
			break;
			//case 3: // Opción 3: Mostrar estado
			//	mostrarEstado();
			//	break;
			//case 4: // Opción 4: Reset
			//	restablecer();
			//	break;
			default: // Opción no esperada: abortar
				ejecutarGenérico(opciónElegida);
				Vista.mostrarError("Error interno de programa - operación pendiente de desarrollo");
				System.exit(1);
			}
		} while (!fin);
	}

	private void mostrarAgenda() {
		try {
			VistaListado lista=new VistaListado(agenda.obtenerLista());
			lista.mostrar();
		} catch (ListaException e) {
			System.err.printf("Se ha producido un error");
		}
	}

	private void cargarTeléfono() {
		VistaAlta telefono= new VistaAlta(entrada,null);
		telefono.cargarTelefono();
		
	}

	/**
	 * Proporciona al usuario un mensaje informativo sobre la opción elegida.
	 * Operación «comodín» a ejecutar para opciones no implementadas.
	 * 
	 * @param id el número de la opción elegida
	 */
	private void ejecutarGenérico(int id) {
		String mensaje;
		mensaje = String.format("%n  Ha elegido la opción %d: «%s»", id, OPCIONES_MENÚ_PRINCIPAL[id - 1]);
		Vista.mostrarTexto(mensaje);
	}

	/**
	 * Activa el programa con el menú principal.
	 * 
	 * @param argumentos opciones en la línea de ejecución —no se usan—
	 */
	public static void main(String[] argumentos) {
		ControlAgenda control = new ControlAgenda();
		control.buclePrincipal();
	}
}
