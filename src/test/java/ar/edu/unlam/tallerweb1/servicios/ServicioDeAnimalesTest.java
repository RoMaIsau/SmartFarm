package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeRazas;

public class ServicioDeAnimalesTest {
	
	private ServicioDeAnimales servicio;
	
	private RepositorioDeRazas repositorioDeRazas;
	
	@Before
	public void inicializar() {
		
		this.repositorioDeRazas = mock(RepositorioDeRazas.class);
		
		this.servicio = new ServicioDeAnimalesImpl(this.repositorioDeRazas);
	}
	
	@Test
	public void debeObtenerLasRazasSegunTipoDeAnimal() {
		
		TipoAnimal equino = new TipoAnimal();
		equino.setId(1L);
		
		List<Raza> razas = this.servicio.obtenerRazasPorTipoAnimal(equino);
		
		verify(this.repositorioDeRazas).listarPorTipoAnimal(eq(equino));
		assertThat(razas).isNotNull();
	}

}
