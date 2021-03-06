package ar.edu.unlam.tallerweb1.servicios;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.TipoAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoAlimento;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
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
		assertThat(tiposDeAlimentosObtenidos).hasSize(1);
		assertThat(tiposDeAlimentosObtenidos).extracting("nombre").contains("Granos");

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

	@Test
	public void deberiaConsultarStockDelAlimento() {
		Alimento alimento = new Alimento();
		alimento.setCantidad(10.0);
		alimento.setStockMinimo(2.0);

		when(this.repositorioAlimento.consultarAlimento(alimento)).thenReturn(alimento);

		Alimento alimentoObtenido = this.servicioAlimento.consultarAlimento(alimento);

		Double stock = alimentoObtenido.getCantidad() - alimentoObtenido.getStockMinimo();

		verify(this.repositorioAlimento).consultarAlimento(eq(alimentoObtenido));
		assertThat(alimentoObtenido).isNotNull();
		assertTrue(alimentoObtenido.getCantidad().equals(10.0));
		assertTrue(alimentoObtenido.getStockMinimo().equals(2.0));
		assertTrue(stock.equals(8.0));
	}

	@Test
	public void deberiaDecrementarStockDeAlimento() {
		int cantidad = 5;
		Alimento alimento = new Alimento();
		alimento.setCantidad(10.0);
		alimento.setStockMinimo(2.0);

		when(this.repositorioAlimento.consultarAlimento(alimento)).thenReturn(alimento);

		Alimento alimentoObtenido = this.servicioAlimento.consultarAlimento(alimento);

		Double nuevoStock = alimentoObtenido.getCantidad() - cantidad;

		verify(this.repositorioAlimento).consultarAlimento(eq(alimentoObtenido));
		assertThat(alimentoObtenido).isNotNull();
		assertTrue(alimentoObtenido.getCantidad().equals(10.0));
		assertTrue(nuevoStock.equals(5.0));

		alimentoObtenido.setCantidad(nuevoStock);
		assertTrue(alimentoObtenido.getCantidad().equals(5.0));

	}
	
	@Test
	public void deberiaEnlistarTodosLosAlimentosConsumidosPorAnimal() {
		Long idAnimal = 1L;
		AnimalDeGranja animal = new AnimalDeGranja();
		animal.setId(idAnimal);
		
		List<Alimento> alimentos = this.servicioAlimento.listarAlimentosConsumidosPorAnimal(idAnimal);
		
		verify(this.repositorioAlimento).listarAlimentosConsumidosPorAnimal(eq(idAnimal));
		assertThat(alimentos).isNotNull();
	}
}
