package jcolonia.daw2022.mayo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Pruebas sobre la clase «{@link AgendaTeléfonos}».
 * 
 * @author <a href="dmartin.jcolonia@gmail.com">David H. Martín</a>
 * @version 1.0 (20230504)
 */
class TestAgenda {
	/**
	 * Verifica que cadenas de texto con teléfonos válidos —nueve caracteres,
	 * numéricos— sean reconocidos correctamente.
	 * 
	 * @param textoTeléfono el texto a comprobar
	 * @see AgendaTeléfonos#validarTeléfono(String)
	 */
	@ParameterizedTest(name = "{0}")
	@DisplayName("Validación telefonos correctos")
	@ValueSource(strings = { "123123123", "000000000" })
	void testTeléfonosCorrectos(String textoTeléfono) {
		int número = assertDoesNotThrow(() -> AgendaTeléfonos.validarTeléfono(textoTeléfono),
				"Teléfonos correctos no deben producir una excepción");
		assertEquals(Integer.parseInt(textoTeléfono), número, "Valor numérico incoherente");
	}

	/**
	 * Verifica que cadenas de texto con teléfonos no válidos —distintos de nueve caracteres,
	 * con caracteres no numéricos— provoquen una excepción con texto descripctivo.
	 * 
	 * @param textoTeléfono el texto a comprobar
	 * @see AgendaTeléfonos#validarTeléfono(String)
	 * @see DatosException
	 */
	@ParameterizedTest(name = "{0}")
	@DisplayName("Validación telefonos incorrectos")
	@ValueSource(strings = { "+23123123", "-23123123", "abc", "12---3", "123  456", "abc  12   3", "123-abc",
			"asd123456789" })
	void testTeléfonosIncorrectos(String textoTeléfono) {
		DatosException ex = assertThrows(DatosException.class, () -> AgendaTeléfonos.validarTeléfono(textoTeléfono),
				"Teléfonos incorrectos deben producir una excepción");
		assertNotNull(ex.getMessage(), "Excepción sin texto");
		assertNotEquals(0, ex.getMessage().length(), "Excepción con texto vacío");
	}

}
