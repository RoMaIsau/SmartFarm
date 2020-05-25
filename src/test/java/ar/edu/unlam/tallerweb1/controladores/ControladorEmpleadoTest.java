package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;

public class ControladorEmpleadoTest {
	
	private ControladorEmpleado controlador;
	
	private ServicioDeAnimales servicioDeAnimales;
	
	@Before
	public void inicializar() {
		
		this.servicioDeAnimales = crearMockServicioDeAnimales();
		
		this.controlador = new ControladorEmpleado();
		this.controlador.setServicioDeAnimales(this.servicioDeAnimales);
	}
	
	private ServicioDeAnimales crearMockServicioDeAnimales() {

		List<TipoAnimal> tiposDeAnimales = this.crearTiposDeAnimales();
		List<Raza> razas = this.crearRazas();
		List<Genero> sexos = this.crearGeneros();
		
		ServicioDeAnimales servicio = mock(ServicioDeAnimales.class);
		when(servicio.obtenerTiposDeAnimales()).thenReturn(tiposDeAnimales);
		when(servicio.obtenerRazasPorTipoAnimal(any(TipoAnimal.class))).thenReturn(razas);
		when(servicio.obtenerGeneros()).thenReturn(sexos);

		return servicio;
	}

	private List<TipoAnimal> crearTiposDeAnimales() {
		
		TipoAnimal vacuno = new TipoAnimal();
		vacuno.setId(1L);
		vacuno.setNombre("VACUNO");
		List<TipoAnimal> tiposDeAnimales = new LinkedList<TipoAnimal>();
		tiposDeAnimales.add(vacuno);
		
		return tiposDeAnimales;
	}

	private List<Raza> crearRazas() {

		Raza caballoArabe = new Raza();
		caballoArabe.setId(1L);
		caballoArabe.setNombre("CABALLO ARABE");

		List<Raza> razas = new LinkedList<Raza>();
		razas.add(caballoArabe);

		return razas;
	}

	private List<Genero> crearGeneros() {
		
		Genero femenino = new Genero();
		femenino.setId(1L);
		femenino.setNombre("FEMENINO");

		List<Genero> generos = new LinkedList<Genero>();
		generos.add(femenino);
		
		return generos;
	}

	@Test
	public void registrarAnimalRedirigeALaVistaDeRegistrarAnimal() {
		
		ModelAndView modelAndView = this.controlador.irAFormularioDeRegistroDeAnimales();
		
		String vista = modelAndView.getViewName();
		
		assertThat(vista).isEqualTo("registrarAnimal");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void laVistaRegistrarAnimalMuestraUnaListaDeTiposDeAnimalesDisponibles() {
		
		ModelAndView modelAndView = this.controlador.irAFormularioDeRegistroDeAnimales();
		
		Map<String, Object> modelo = modelAndView.getModel();
		
		verify(this.servicioDeAnimales).obtenerTiposDeAnimales();

		assertThat(modelo).containsKey("tiposDeAnimales");
		
		List<TipoAnimal> tiposDeAnimales = (List<TipoAnimal>) modelo.get("tiposDeAnimales");
		assertThat(tiposDeAnimales).isNotEmpty();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void laVistaRegistrarAnimalMuestraUnaListaDeRazasDisponibles() {

		ModelAndView modelAndView = this.controlador.irAFormularioDeRegistroDeAnimales();
		Map<String, Object> modelo = modelAndView.getModel();

		verify(this.servicioDeAnimales).obtenerRazasPorTipoAnimal(any(TipoAnimal.class));

		assertThat(modelo).containsKey("razas");

		List<Raza> razas = (List<Raza>) modelo.get("razas");
		assertThat(razas).isNotEmpty();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void laVistaRegistrarAnimalMuestaUnaListaDeGeneros() {
		
		ModelAndView modelAndView = this.controlador.irAFormularioDeRegistroDeAnimales();
		Map<String, Object> modelo = modelAndView.getModel();
		
		verify(this.servicioDeAnimales).obtenerGeneros();
		
		assertThat(modelo).containsKey("generos");
		List<Genero> generos = (List<Genero>) modelo.get("generos");
		assertThat(generos).isNotEmpty();
	}

	@Test
	public void seSeleccionaPorDefaultElPrimerElementoDeLosCombosDeSeleccion() {

		ModelAndView modelAndView = this.controlador.irAFormularioDeRegistroDeAnimales();
		Map<String, Object> modelo = modelAndView.getModel();

		assertThat(modelo).containsKey("animal");
		AnimalDeGranja animal = (AnimalDeGranja) modelo.get("animal");

		assertThat(animal).extracting("tipo").isNotNull();
		assertThat(animal).extracting("raza").isNotNull();
		assertThat(animal).extracting("genero").isNotNull();
	}
	
	@Test
	public void cargarRazasSegunTipoDeAnimalSeleccionado() {
		
		final Long idTipoAnimal = 1L;
		
		List<Raza> razas = this.controlador.cargarRazas(idTipoAnimal);
		
		TipoAnimal tipoAnimalSeleccionado = new TipoAnimal();
		tipoAnimalSeleccionado.setId(idTipoAnimal);
		
		verify(this.servicioDeAnimales).obtenerRazasPorTipoAnimal(eq(tipoAnimalSeleccionado));
		assertThat(razas).isNotNull();
	}
	
	@Test
	public void registrarAnimalRedirigeHaciaAnimales() {

		AnimalDeGranja animalNuevo = new AnimalDeGranja();

		ModelAndView modelAndView = this.controlador.registrarAnimal(animalNuevo);

		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/animales");
		verify(this.servicioDeAnimales).registrar(eq(animalNuevo));
	}

	@Test
	public void siElRolNoEsEmpleadoNiAdminRedirigeAlLogin() {

		HttpServletRequest pedido = this.configurarRolLogueado("Veterinario");

		ModelAndView view = this.controlador.listarAnimales(pedido);

		assertThat(view.getViewName()).isEqualTo("redirect:/login");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void siElRolEsAdminListaTodosLosAnimales() {

		HttpServletRequest pedido = this.configurarRolLogueado("Admin");

		ModelAndView view = this.controlador.listarAnimales(pedido);
		ModelMap modelo = view.getModelMap();

		verify(this.servicioDeAnimales).obtenerTodos();
		assertThat(view.getViewName()).isEqualTo("animales");
		assertThat(modelo).containsKeys("animales");

		List<AnimalDeGranja> animales = (List<AnimalDeGranja>) modelo.get("animales");
		assertThat(animales).isNotNull();
	}

	@Test
	public void cuandoRedireccionaHaciaIndexEmpleadoDebeListarLosAnimales() {

		HttpServletRequest pedido = this.configurarRolLogueado("Empleado");

		ModelAndView modelAndView = this.controlador.irAIndexEmpleado(pedido);

		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/animales");
	}
	
	@Test
	public void cuandoSeEditaElAnimalSeDebeMostrarLaPantallaDeEdicion(){
		
		Long idAnimal = 1L;
		when(servicioDeAnimales.obtenerPorId(eq(idAnimal))).thenReturn(new AnimalDeGranja());		
		
		ModelAndView modelAndView = this.controlador.editarAnimal(idAnimal);
		
		ModelMap modelo = modelAndView.getModelMap();
		
		AnimalDeGranja animal = (AnimalDeGranja) modelo.get("animalEditable");
		assertThat(animal).isNotNull();

		assertThat(modelo).containsKeys("tiposDeAnimales", "razas", "generos");
		assertThat(modelAndView.getViewName()).isEqualTo("editarAnimal");
	}

	@Test
	public void cuandoSeActualizaElAnimalSeRedirigeAlListadoDeAnimales() {

		AnimalDeGranja animalEditado = new AnimalDeGranja();
		animalEditado.setId(1L);

		ModelAndView modelAndView = this.controlador.actualizarAnimal(animalEditado);

		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/animales");
		verify(this.servicioDeAnimales).actualizarAnimal(eq(animalEditado));
	}

	private HttpServletRequest configurarRolLogueado(String rol) {

		HttpServletRequest pedido = mock(HttpServletRequest.class);
		HttpSession sesionHttp = mock(HttpSession.class);
		when(pedido.getSession()).thenReturn(sesionHttp);
		when(sesionHttp.getAttribute("ROL")).thenReturn(rol);

		return pedido;
	}
}
