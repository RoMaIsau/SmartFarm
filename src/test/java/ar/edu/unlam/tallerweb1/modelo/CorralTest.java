package ar.edu.unlam.tallerweb1.modelo;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class CorralTest {

	@Test
	public void losPuntosDeberianEstarDentroDelCorral() {

		Corral corral = ConstructorDeCorral.crear()
				.conPunto(-35.27943238,	-59.25639695)
				.conPunto(-35.27050061,	-59.24681765)
				.conPunto(-35.27482256,	-59.23890212)
				.conPunto(-35.28256033,	-59.25004436)
				.conPunto(-35.27943238,	-59.25639695)
				.corral();

		assertThat(corral.contiene(-35.27699061134853, -59.24939917767695)).isTrue();
		assertThat(corral.contiene(-35.27189641088621, -59.246782457908026)).isTrue();
		assertThat(corral.contiene(-35.28016747737641, -59.25275394661165)).isTrue();
	}

	@Test
	public void losPuntosDeberianEstarFueraDelCorral() {

		Corral corral = ConstructorDeCorral.crear()
				.conPunto(-35.27943238,	-59.25639695)
				.conPunto(-35.27050061,	-59.24681765)
				.conPunto(-35.27482256,	-59.23890212)
				.conPunto(-35.28256033,	-59.25004436)
				.conPunto(-35.27943238,	-59.25639695)
				.corral();

		assertThat(corral.contiene(-35.27780814141548, -59.23786377665017)).isFalse();
		assertThat(corral.contiene(-35.271125428754274, -59.25356409526432)).isFalse();
		assertThat(corral.contiene(-35.27123498586599, -59.24088306869129)).isFalse();
		assertThat(corral.contiene(-35.282467888773674, -59.256041620167636)).isFalse();
	}
		
}

class ConstructorDeCorral {
	private List<Vertice> vertices;

	private ConstructorDeCorral() {
		this.vertices = new LinkedList<>();
	}

	public static ConstructorDeCorral crear() {
		return new ConstructorDeCorral();
	}

	public ConstructorDeCorral conPunto(double latitud, double longitud) {
		Vertice vertice = new Vertice();
		vertice.setLatitud(BigDecimal.valueOf(latitud));
		vertice.setLongitud(BigDecimal.valueOf(longitud));
		this.vertices.add(vertice);
		return this;
	}

	public Corral corral() {
		Corral corral = new Corral();
		corral.setVertices(this.vertices);
		return corral;
	}
}