package ar.edu.unlam.tallerweb1.servicios;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.TipoAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoAlimento;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class ServicioAlimentoTest {

	private ServicioAlimento servicioAlimento;

	private RepositorioTipoAlimento repositorioTipoAlimento;
	private RepositorioAlimento repositorioAlimento;

	@Before
	public void inicializar() {

		this.repositorioTipoAlimento = mock(RepositorioTipoAlimento.class);
		this.repositorioAlimento = mock(RepositorioAlimento.class);

		this.servicioAlimento = new ServicioAlimentoImpl(this.repositorioTipoAlimento, this.repositorioAlimento);
	}

	@Test
	public void seObtienenTodosLosTiposDeAlimentoDelRepositorioTipoAlimento() {

		List<TipoAlimento> tiposDeAlimentos = new ArrayList<TipoAlimento>();
		TipoAlimento tipo1 = new TipoAlimento();
		tipo1.setId(1L);
		tipo1.setNombre("Granos");

		tiposDeAlimentos.add(tipo1);

		when(this.repositorioTipoAlimento.obtenerTiposDeAlimentos()).thenReturn(tiposDeAlimentos);

		List<TipoAlimento> tiposDeAlimentosObtenidos = this.servicioAlimento.obtenerTiposDeAlimentos();

		verify(this.repositorioTipoAlimento).obtenerTiposDeAlimentos();
		assertThat(tiposDeAlimentosObtenidos ).hasSize(1);
		assertThat(tiposDeAlimentosObtenidos ).extracting("nombre").contains("Granos");

	}
	
	@Test
	public void deberiaObtenerAlimentoSegunId() {
		Long id = 1L;
		
		when(this.repositorioAlimento.consultarAlimentoPorId(id)).thenReturn(new Alimento());
		
		Alimento alimentoObtenido = this.servicioAlimento.consultarAlimentoPorId(id);
		
		verify(this.repositorioAlimento).consultarAlimentoPorId(eq(id));
		assertThat(alimentoObtenido).isNotNull();
	}
	
	@Test
	public void deberiaActualizarAlimento() {
		Alimento alimento = new Alimento();
		alimento.setId(1L);
		
		this.servicioAlimento.actualizarAlimento(alimento);
		
		verify(this.repositorioAlimento).actualizarAlimento(eq(alimento));
	}
	
	

}
