package ar.edu.unlam.tallerweb1.servicios;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeTipos;

public class ServicioDeTiposDeAnimalesTest {
	
	private ServicioDeTiposDeAnimales servicio;

	private RepositorioDeTipos repositorioDeTipos;
	
	@Before
	public void inicializarServicio() {
		
		this.repositorioDeTipos = mock(RepositorioDeTipos.class);
		this.servicio = new ServicioDeTiposDeAnimalesImpl(this.repositorioDeTipos);
	}
	
	@Test
	public void seObtienenLosTiposDisponiblesDeAnimalesDelRepositorioDeAnimales() {
		
		List<TipoAnimal> tiposDisponibles = new LinkedList<TipoAnimal>();
		TipoAnimal vacuno = new TipoAnimal();
		vacuno.setId(1L);
		vacuno.setNombre("VACUNO");
		tiposDisponibles.add(vacuno);
		
		when(this.repositorioDeTipos.listarDisponibles()).thenReturn(tiposDisponibles);
		
		List<TipoAnimal> tiposObtenidos = this.servicio.obtenerDisponibles();
		
		verify(this.repositorioDeTipos).listarDisponibles();
		assertThat(tiposObtenidos).hasSize(1);
		assertThat(tiposObtenidos).extracting("nombre").contains("VACUNO");
	}	
}
