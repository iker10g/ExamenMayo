package jcolonia.daw2022.mayo;

import static java.lang.System.out;
import static java.lang.System.err;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Colección de utilidades comunes para las «vistas» sobre la consola de texto.
 * 
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 * @version 2.3 (20230516)
 */
public abstract class Vista {
	/** Máximo de fallos permitidos antes de abortar una pregunta. */
	private static final int MÁX_ERRORES_ENTRADA = 5;
	// - {field}{static}MÁX_ERRORES_ENTRADA: int = 5 {solo lectura}

	/** Formato tipo «printf» para mensajes generales. */
	private static final String FORMATO_AVISO = "     → %s%n";
	// - {field}{static}FORMATO_AVISO: String = "     → %s%n" {solo lectura}

	/** Formato tipo «printf» para errores. */
	private static final String FORMATO_ERROR = "  %s%n";
	// - {field}{static}FORMATO_ERROR: String = "  %s%n" {solo lectura}

	/** Formato tipo «printf» para preguntas y peticiones. */
	private static final String FORMATO_PREGUNTA = ">> %s ";
	// - {field}{static}FORMATO_PREGUNTA: String = ">> %s " {solo lectura}

	/** Nombre de la vista, para mostrarlo como encabezado o título. */
	private final String nombre;
	// - nombre: String {solo lectura}

	/**
	 * Analizador/escáner compartido vinculado a la entrada estándar del sistema.
	 * Acceso único a la «{@link java.lang.System#in entrada estándar}» provista por
	 * el sistema. Da soporte a las operaciones de entrada por teclado. Debe ser un
	 * objeto único a compartir con las diferentes vistas creadas.
	 */
	private static Scanner scEntrada;
	// - {static}scEntrada: Scanner

	/**
	 * Facilita el gestor para acceso a la entrada estándar. En caso de no estar
	 * definido crea uno —incialización demorada, a modo de «singleton».
	 * 
	 * @return el objeto correspondiente
	 */
	public static Scanner getEntrada() {
		if (scEntrada == null) {
			scEntrada = new Scanner(System.in);
		}
		return scEntrada;
	}
	// + getEntrada(): Scanner

	/**
	 * Inicializa la vista recogiendo el nombre/título a asignar y el acceso a la
	 * entrada estándar.
	 * 
	 * @param nombre    el texto identificador
	 * @param scEntrada el gestor para acceso a la entrada estándar
	 * @throws VistaException si el gestor de la entrada ya estuviera definido
	 */
	public Vista(String nombre, Scanner scEntrada) throws VistaException {
		if (Vista.scEntrada != null) {
			throw new VistaException("El acceso a la entrada estándar ya estaba definido");
		}

		Vista.scEntrada = scEntrada;
		this.nombre = nombre;
	}
	// + Vista(nombre: String, scEntrada: Scanner)

	/**
	 * Inicializa la vista recogiendo el nombre/título a asignar. El acceso único
	 * compartido a la entrada estándar permanece inalterado.
	 * 
	 * @param nombre el texto identificador
	 * @see #getEntrada()
	 */
	public Vista(String nombre) {
		this.nombre = nombre;
	}
	// + Vista(nombre: String)

	/**
	 * Facilita el texto identificador.
	 *
	 * @return el texto correspondiente
	 */
	public final String getNombre() {
		return nombre;
	}
	// + getNombre(): String

	/**
	 * Imprime –muestra en la salida estándar– el nombre o título con cierta
	 * decoración.
	 * 
	 * <div> El título básico se muestra con un subrayado sencillo como:
	 * 
	 * <pre>
	 * Nombre/título
	 * -------------
	 * </pre>
	 * 
	 * Se puede «destacar», a modo de título principal —en mayúsculas con marco
	 * doble— y se vería como:
	 * 
	 * <pre>
	 * ===============
	 *  NOMBRE/TÍTULO
	 * ===============
	 * </pre>
	 * 
	 * </div>
	 * 
	 * @param destacado para indicar si se desea el título destacado o no
	 */
	public final void mostrarTítulo(boolean destacado) {
		StringBuffer textoTítulo;
		String título, líneaBorde;
		char c = (destacado) ? '=' : '-';

		textoTítulo = new StringBuffer();

		título = (destacado) ? nombre.toUpperCase() : nombre;
		líneaBorde = generarBorde(nombre.length() + 2, c);

		if (destacado) {
			textoTítulo.append("\n");
			textoTítulo.append(líneaBorde);
			textoTítulo.append("\n ");
		}
		textoTítulo.append(título);
		textoTítulo.append('\n');
		textoTítulo.append(líneaBorde);

		out.println(textoTítulo);
	}
	// + mostrarTítulo(destacado: boolean): void

	/**
	 * Envía a la salida el título con un marco destacado. <div> Ejemplo:
	 *
	 * <pre>
	 * =================
	 *  Vista principal
	 * =================
	 * </pre>
	 *
	 * </div>
	 */
	public final void mostrarTítulo1() {
		mostrarTítulo(true);
	}
	// + mostrarTítulo1(): void

	/**
	 * Envía a la salida el título con un subrayado sencillo.
	 * 
	 * <div> Ejemplo:
	 *
	 * <pre>
	 * Vista secundaria
	 * ----------------
	 * </pre>
	 *
	 * </div>
	 */
	public final void mostrarTítulo2() {
		mostrarTítulo(false);
	}
	// + mostrarTítulo2(): void

	/**
	 * Envía directamente a la salida un texto arbitrario.
	 *
	 * @param texto el texto indicado
	 */
	public final static void mostrarTexto(String texto) {
		out.println(texto);
	}
	// + {static} mostrarTexto(texto: String): void

	/**
	 * Envía a la salida un texto destacado como aviso.
	 * 
	 * <pre>
	 * 	→ Texto de aviso
	 * </pre>
	 *
	 * @param texto el texto indicado
	 */
	public final static void mostrarAviso(String texto) {
		out.printf(FORMATO_AVISO, texto);
	}
	// + {static} mostrarAviso(texto: String): void

	/**
	 * Envía directamente a la salida un texto arbitrario formateado como pregunta.
	 * 
	 * <pre>
	 * 	> Introducir un dato
	 * </pre>
	 * 
	 * @param pregunta el texto indicado
	 */
	public final static void mostrarPregunta(String pregunta) {
		out.printf(FORMATO_PREGUNTA, pregunta);
	}
	// + {static} mostrarPregunta(pregunta: String): void

	/**
	 * Crea una línea por repetición de un mismo carácter.
	 * 
	 * <div> Ejemplo:
	 *
	 * <pre>
	 * ^^^^^^^^^^^^^^^^^^^^^^
	 * </pre>
	 *
	 * </div>
	 *
	 * @param longitud la longitud de la línea
	 * @param símbolo  el carácter a emplear
	 * @return el texto con la línea creada
	 */
	public final static String generarBorde(int longitud, char símbolo) {
		String textoBorde;
		char[] línea;

		línea = new char[longitud];
		Arrays.fill(línea, símbolo);
		textoBorde = new String(línea);

		return textoBorde;
	}
	// + {static} generarBorde(longitud: int, símbolo: char): String

	/**
	 * Convierte un texto a minúsculas con solo el primer carácter en mayúscula.
	 * 
	 * @param texto el texto original
	 * @return el texto resultante
	 */
	public static String capitalizar(String texto) {
		String resultado;

		if (texto != null && texto.length() > 0) {
			char[] caracteres = texto.toLowerCase().toCharArray();
			caracteres[0] = Character.toUpperCase(caracteres[0]);
			resultado = new String(caracteres);
		} else {
			resultado = texto;
		}

		return resultado;
	}
	// + {static} capitalizar(texto: String): String

	/**
	 * Envía directamente a la salida de error un texto arbitrario.
	 * 
	 * <div>Introduce un pequeño tiempo de espera para facilitar la sincronización
	 * del búffer de salida y no mezclarse con la salida estándar en terminales con
	 * limitaciones (i.e. la consola de eclipse).</div>
	 *
	 * @param texto el texto indicado
	 */
	public static final void mostrarError(String texto) {
		err.printf(FORMATO_ERROR, texto);
		esperar(200); // 0.2 s, evita atascos en consola de eclipse
	}
	// + {static} mostrarError(texto: String): void

	/**
	 * Introduce una pausa, un pequeño tiempo de espera.
	 *
	 * @param milisegundos el tiempo indicado, en milisegundos
	 */
	public static void esperar(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
		} // Nada útil que hacer…
	}
	// + {static} esperar(milisegundos: int): void

	/**
	 * Solicita una respuesta de «sí» o «no» con el texto proporcionado. La pregunta
	 * se repite de manera estricta hasta obtener una entrada con solamente una de
	 * las dos letras iniciales admitidas «S/N», sea mayúscula o minúscula.
	 * 
	 * <div>Ejemplo:
	 *
	 * <pre>
	 * ¿Desea continuar (S/N)?
	 * </pre>
	 *
	 * @param pregunta formato tipo «printf» que incluya la secuencia «%s» donde
	 *                 colocar la opción «S/N»
	 * @return true para «S» o «s», false para «N» o «n»
	 */
	public final static boolean pedirConfirmación(String pregunta) {
		String textoEntrada;
		boolean resultado = false, aceptado = false;

		do {
			out.printf(pregunta, "S/N");
			textoEntrada = scEntrada.nextLine();
			if ("s".equalsIgnoreCase(textoEntrada)) {
				resultado = true;
				aceptado = true;
			} else if ("n".equalsIgnoreCase(textoEntrada)) {
				resultado = false;
				aceptado = true;
			} else {
				mostrarError(" Escriba solo «S» o «N» y pulse «Intro»\n");
			}
		} while (!aceptado);
		return resultado;
	}
	// + {static} pedirConfirmación(pregunta: String): boolean

	/**
	 * Muestra un texto predeterminado y espera hasta que se pulse la tecla «Intro».
	 * Si el usuario teclea algún texto previo, se repite la pregunta de manera
	 * indefinida.
	 * 
	 * <div>Ejemplo:
	 * 
	 * <pre>
	 *  → Pulsar «Intro» para continuar…
	 * </pre>
	 */
	public final static void pedirContinuar() {
		String línea;
		do {
			mostrarPregunta("Pulsar «Intro» para continuar…");
			línea = scEntrada.nextLine();
		} while (línea.length() != 0);
		out.println();
	}
	// + pedirContinuar(): void

	/**
	 * Solicita por consola la introducción de texto mostrando una indicación
	 * personalizada. En caso de obtener una respuesta vacía repite indefinidamente
	 * la pregunta.
	 * 
	 * <div>
	 * 
	 * <pre>
	 * >> Escriba un dato:
	 * </pre>
	 * 
	 * </div>
	 * 
	 * @param pregunta la indicación a mostrar
	 * @return el texto introducido
	 */
	public static String pedirTexto(String pregunta) {
		Scanner in = getEntrada();
		String línea;

		do {
			mostrarPregunta(pregunta);
			línea = in.nextLine();
		} while (línea.length() == 0);
		return línea;
	}
	// + {static}pedirTexto(): String

	/**
	 * Solicita por consola la introducción de un número entero mostrando como
	 * indicador un texto predeterminado.
	 * 
	 * <div>
	 * 
	 * <pre>
	 * Escriba un número entero:
	 * </pre>
	 * 
	 * </div>
	 * 
	 * @return el número introducido
	 * @see Integer#parseInt(String)
	 * @see Integer#MAX_VALUE
	 * @see #pedirEntero(String)
	 */
	public static int pedirEntero() {
		return pedirEntero("Escriba un número entero:");
	}
	// + {static}pedirEntero(): int

	/**
	 * Solicita por consola la introducción de un número entero mostrando como
	 * indicador un texto personalizado. En caso de que se introduzca un texto no
	 * válido se repite la petición de manera indefinida hasta que se obtenga un
	 * valor numérico compatible. Se admite como válido cualquier numero entero
	 * -positivo, negativo o cero, en base diez, usando los dígitos [0..9]- dentro
	 * del rango general soportado.
	 * 
	 * <pre>
	 * >> Valor a emplear para «loquesea»:
	 * </pre>
	 * 
	 * @param pregunta el texto para la pregunta personalizado
	 * @return el número introducido
	 * @see Integer#parseInt(String)
	 * @see Integer#MAX_VALUE
	 * @see #pedirEntero(String)
	 */
	public static int pedirEntero(String pregunta) {
		Scanner in = getEntrada();

		boolean salir = false;
		String texto;
		int número = 0;

		do {
			try {
				mostrarPregunta(pregunta);
				texto = in.nextLine();
				número = Integer.parseInt(texto);
				salir = true;
			} catch (NumberFormatException ex) {
				out.printf("\t%s%n", "¡ATENCIÓN: entrada incorrecta!");
			}

		} while (!salir);

		return número;
	}
	// + {static}pedirEntero(pregunta: String): int

	/**
	 * Solicita por consola la introducción de un número entero dentro de un rango
	 * establecido mostrando como indicador un texto personalizado. En caso de que
	 * se introduzca un texto no válido se repite la petición hasta cinco veces en
	 * caso de no obtener un valor numérico compatible. Se admite como válido
	 * cualquier numero entero -positivo, negativo o cero, en base diez, usando los
	 * dígitos [0..9]- en el rango facilitado y dentro del rango general soportado.
	 * 
	 * <pre>
	 * Valor a emplear para «loquesea»:
	 * </pre>
	 * 
	 * @param pregunta    el texto para la pregunta personalizado
	 * @param valorMínimo el mínimo valor admitido
	 * @param valorMáximo el máximo valor admitido
	 * @return el número introducido
	 * @throws VistaException al sexto error de entrada
	 * @see Integer#parseInt(String)
	 * @see Integer#MAX_VALUE
	 * @see #pedirEntero(String)
	 */
	public static int pedirEntero(String pregunta, int valorMínimo, int valorMáximo) throws VistaException {
		Exception últimaExcepción = null;
		int contadorFallos = 0;

		boolean salir = false;
		String texto, mensaje;
		int número = 0;

		do {
			try {
				texto = pedirTexto(pregunta);
				número = Integer.parseInt(texto);

				if (número < valorMínimo || número > valorMáximo) {
					mensaje = String.format("Valor fuera del rango [%d..%d]", valorMínimo, valorMáximo);
					throw new NumberFormatException(mensaje);
				} else {
					salir = true;
				}
			} catch (NumberFormatException ex) {
				últimaExcepción = ex;
				contadorFallos++;
				mensaje = String.format("%s: «%s» (%d/%d intentos)", "¡ATENCIÓN: entrada incorrecta!",
						ex.getLocalizedMessage(), contadorFallos, MÁX_ERRORES_ENTRADA);
				mostrarAviso(mensaje);
			}

			if (contadorFallos > MÁX_ERRORES_ENTRADA) {
				throw new VistaException("Excedido el número máximo de fallos", últimaExcepción);
			}

		} while (!salir);

		return número;
	}
	// + {static}pedirEntero(pregunta:String,valorMínimo:int,valorMáximo:int):int
}
