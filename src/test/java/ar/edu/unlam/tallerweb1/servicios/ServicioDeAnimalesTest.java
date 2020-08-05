package ar.edu.unlam.tallerweb1.servicios;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeAnimales;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeGeneros;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeRazas;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeTipos;

public class ServicioDeAnimalesTest {
	
	private ServicioDeAnimales servicio;

	private RepositorioDeTipos repositorioDeTipos;
	private RepositorioDeRazas repositorioDeRazas;
	private RepositorioDeGeneros repositorioDeGeneros;
	private RepositorioDeAnimales repositorioDeAnimales;
	private ServicioPlanAlimentario servicioPlanAlimentario;
	private ServicioAnimalUbicacion servicioAnimalUbicacion;

	@Before
	public void inicializar() {

		this.repositorioDeTipos = mock(RepositorioDeTipos.class);
		this.repositorioDeRazas = mock(RepositorioDeRazas.class);
		this.repositorioDeGeneros = mock(RepositorioDeGeneros.class);
		this.repositorioDeAnimales = mock(RepositorioDeAnimales.class);
		this.servicioPlanAlimentario = mock(ServicioPlanAlimentario.class);
		this.servicioAnimalUbicacion = mock(ServicioAnimalUbicacion.class);

		this.servicio = new ServicioDeAnimalesImpl(this.repositorioDeTipos, this.repositorioDeRazas, 
				this.repositorioDeGeneros, this.repositorioDeAnimales, this.servicioPlanAlimentario,
				this.servicioAnimalUbicacion);
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
	
	@Test
	public void debeObtenerLosGenerosDisponibles() {
		
		List<Genero> generos = this.servicio.obtenerGeneros();
		
		verify(this.repositorioDeGeneros).listar();
		assertThat(generos).isNotNull();
	}

	@Test
	public void registrarDeberiaGuardarLosAnimalesYCrearUnPlanAlimentario() {

		AnimalDeGranja animalNuevo = new AnimalDeGranja();

		this.servicio.registrar(animalNuevo);

		//verify(this.repositorioDeAnimales).guardar(eq(animalNuevo));
		verify(this.servicioPlanAlimentario).crearPlan(eq(animalNuevo));
	}

	@Test
	public void deberiaObtenerTodosLosAnimales() {

		List<AnimalDeGranja> animales = this.servicio.obtenerTodos();

		verify(this.repositorioDeAnimales).listar();
		assertThat(animales).isNotNull();
	}
	
	@Test
	public void deberiaObtenerAnimalPorId() {
		Long idAnimal = 2L; 
		when(this.repositorioDeAnimales.buscarPorId(idAnimal)).thenReturn(new AnimalDeGranja());

		AnimalDeGranja animal = this.servicio.obtenerPorId(idAnimal);
		
		verify(this.repositorioDeAnimales).buscarPorId(eq(idAnimal));
		assertThat(animal).isNotNull();
	}

	@Test
	public void deberiaActualizarElAnimal() {

		AnimalDeGranja animalParaActualizar = new AnimalDeGranja();
		animalParaActualizar.setId(1L);

		this.servicio.actualizarAnimal(animalParaActualizar); 

		verify(this.repositorioDeAnimales).actualizar(eq(animalParaActualizar));
	}

	@Test
	public void deberiaEliminarAUnAnimal() {

		Long idAnimal = 2L;
		AnimalDeGranja animalConIdDos = new AnimalDeGranja();
		animalConIdDos.setId(idAnimal);
		when(this.repositorioDeAnimales.buscarPorId(idAnimal)).thenReturn(animalConIdDos);

		this.servicio.eliminarPorId(idAnimal);

		AnimalDeGranja animalParaBorrar = new AnimalDeGranja();
		animalParaBorrar.setId(idAnimal);
		verify(this.servicioPlanAlimentario).eliminarPlan(eq(animalParaBorrar));
		verify(this.servicioAnimalUbicacion).eliminarUbicaciones(eq(animalParaBorrar));
		verify(this.repositorioDeAnimales).eliminar(eq(animalParaBorrar));
	}
}
