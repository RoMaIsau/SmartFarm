package ar.edu.unlam.tallerweb1.modelo;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

public class PosicionTest {

	@Test
	public void toStringDebeMostrarElIdentificadorConLatitudYLongitud() {
		
		Posicion posicion = new Posicion();
		posicion.setIdentificador("GPS001");
		posicion.setLatitud(-34.6277969);
		posicion.setLongitud(58.4528271);

		assertThat(posicion.toString())
			.isEqualTo("Identificador:GPS001 - Latitud:-34.6277969 - Longitud:58.4528271");
	}

}
