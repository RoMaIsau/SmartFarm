package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeRazas;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeTipos;

public class ServicioDeAnimalesTest {
	
	private ServicioDeAnimales servicio;

	private RepositorioDeTipos repositorioDeTipos;
	private RepositorioDeRazas repositorioDeRazas;

	
	@Before
	public void inicializar() {

		this.repositorioDeTipos = mock(RepositorioDeTipos.class);
		this.repositorioDeRazas = mock(RepositorioDeRazas.class);
		
		this.servicio = new ServicioDeAnimalesImpl(this.repositorioDeTipos, this.repositorioDeRazas);
	}
	
	@Test
	public void seObtienenLosTiposDisponiblesDeAnimalesDelRepositorioDeAnimales() {
		
		List<TipoAnimal> tiposDisponibles = new LinkedList<TipoAnimal>();
		TipoAnimal vacuno = new TipoAnimal();
		vacuno.setId(1L);
		vacuno.setNombre("VACUNO");
		tiposDisponibles.add(vacuno);
		
		when(this.repositorioDeTipos.listarDisponibles()).thenReturn(tiposDisponibles);
		
		List<TipoAnimal> tiposObtenidos = this.servicio.obtenerTiposDeAnimales();
		
		verify(this.repositorioDeTipos).listarDisponibles();
		assertThat(tiposObtenidos).hasSize(1);
		assertThat(tiposObtenidos).extracting("nombre").contains("VACUNO");
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
