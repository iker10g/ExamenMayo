package jcolonia.daw2022.mayo;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Colección de entradas a modo de agenda. Cada pareja está formada por un
 * número de teléfono y un nombre asociado. Los teléfonos no se pueden repetir.
 * 
 * @author <a href= "mailto:dmartin.jcolonia@gmail.com">David H. Martín</a>
 * @version 0.3 (20230515)
 */
public class AgendaTeléfonos {
	/** Almacén de parejas número + nombre, sin números repetidos */
	private Map<Integer, String> lista;

	/** Inicializa el almacén de teléfonos y nombres. */
	public AgendaTeléfonos() {
		lista = new Hashtable<>();
	}

	/**
	 * Facilita el número de registros almacenados.
	 * 
	 * @return el valor correspondiente
	 */
	public int tamaño() {
		return lista.size();
	}

	/**
	 * Añade o modifica un número de teléfono con su nombre.
	 * 
	 * @param nombre el nombre o texto asociado
	 * @param número el número de teléfono
	 */
	public void añadir(String nombre, Integer número) {
		lista.put(número, nombre);
	}

	/**
	 * Comprueba la presencia de un número de teléfono
	 * 
	 * @param número el número a consultar
	 * @return si existe ya o no
	 */
	public boolean existeTeléfono(Integer número) {
		return lista.containsKey(número);
	}

	/**
	 * Realiza una búsqueda de teléfonos por nombre, basado en una coincidencia
	 * exacta.
	 * 
	 * @param nombre el nombre buscado
	 * @return una lista de líneas de texto con las coincidencias
	 */
	public List<String> buscar(String nombre) {
		Vector<String> listaTextos = new Vector<>();

		lista.forEach((teléfonoGuardado, nombreGuardado) -> {
			String línea;
			if (nombre.equals(nombreGuardado)) {
				línea = String.format("%s → %09d", nombreGuardado, teléfonoGuardado);
				listaTextos.add(línea);
			}
		});
		return listaTextos;
	}

	/**
	 * Facilita una relación de todos los teléfonos almacenados.
	 * 
	 * @return una lista de líneas de texto con todos los teléfonos
	 */
	public List<String> toListaTextos() {
		Vector<String> listaTextos = new Vector<>();

		lista.forEach((teléfonoGuardado, nombreGuardado) -> {
			String línea;
			línea = String.format("%s → %09d", nombreGuardado, teléfonoGuardado);
			listaTextos.add(línea);
		});
		return listaTextos;
	}

	/**
	 * Convierte en número un texto representando a un número de teléfono básico de
	 * nueve cifras.
	 * 
	 * @param texto el texto a convertir
	 * @return el valor numérico correspondiente
	 * @throws DatosException si no es un número o la longitud no es adecuada
	 */
	public static int validarTeléfono(String texto) throws DatosException {
		int número = 0;
		String textoDígitos;

		String mensajeError = null;

		if (texto != null) {
			texto = texto.strip(); // Quitar espacios iniciales y finales
			textoDígitos = texto.replaceAll("[\\D]+", ""); // Quitar todos los no-números

			if (texto.length() != 9 || textoDígitos.length() != 9) {
				mensajeError = String.format("«%-9.9s» debe tener 9 dígitos numéricos", texto);
			} else {
				try {
					número = Integer.parseUnsignedInt(texto);
				} catch (NumberFormatException e) {
					mensajeError = String.format("«%-9.9s» no es un teléfono válido", texto);
				}
			}
		} else {
			mensajeError = "Entrada nula para teléfono";
		}

		if (mensajeError != null) {
			throw new DatosException(mensajeError);
		}

		return número;
	}
}
