package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlimento;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.validadores.ValidadorDeAnimalDeGranja;

public class ControladorEmpleadoTest {
	
	@Inject
	private ControladorUsuario controladorUsuario;
	
	private ControladorEmpleado controlador;
	
	private ServicioDeAnimales servicioDeAnimales;
	private ServicioAlimento servicioAlimento;
	private ValidadorDeAnimalDeGranja validadorDeAnimales;
	private ServicioUsuario servicioUsuario;
	private ServicioNotificacion servicioNotificacion;
	
	@Before	
	public void inicializar() {
		
		this.servicioDeAnimales = crearMockServicioDeAnimales();
		this.servicioAlimento = crearMockServicioAlimento();
		this.validadorDeAnimales = crearMockValidadorDeAnimales();
		this.servicioUsuario = mock(ServicioUsuario.class);
		this.servicioNotificacion = mock(ServicioNotificacion.class);
		
		this.controlador = new ControladorEmpleado(this.servicioDeAnimales,
				this.servicioAlimento, this.servicioUsuario, this.servicioNotificacion,
				this.validadorDeAnimales);
	}

	/*@Test
	public void inicioDeSesionTest() {
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession mockSession = mock(HttpSession.class);
		Usuario mockUsuario = mock(Usuario.class);
		
		when(servicioUsuario.consultarUsuario(mockUsuario)).thenReturn(mockUsuario);
		when(mockRequest.getSession()).thenReturn(mockSession);
		
		ModelAndView resultado = controladorUsuario.validarLogin(mockUsuario, mockRequest);
		String esperado = "redirect:/indexAdmin";
		
		assertThat(resultado.getViewName()).isEqualTo(esperado);
	}*/
	
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

	private ServicioAlimento crearMockServicioAlimento() {
		return mock(ServicioAlimento.class);
	}

	private ValidadorDeAnimalDeGranja crearMockValidadorDeAnimales() {
		return mock(ValidadorDeAnimalDeGranja.class);
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
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession mockSesion = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSesion);
		ServicioNotificacion mockServicioNotificacion = mock(ServicioNotificacion.class);
		controlador.listarNotificacionesDelEmpleado(mockRequest);
		
		ModelAndView modelAndView = this.controlador.irAFormularioDeRegistroDeAnimales(mockRequest, new ModelMap());
		
		String vista = modelAndView.getViewName();
		
		assertThat(vista).isEqualTo("registrarAnimal");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void laVistaRegistrarAnimalMuestraUnaListaDeTiposDeAnimalesDisponibles() {
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession mockSesion = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSesion);
		ServicioNotificacion mockServicioNotificacion = mock(ServicioNotificacion.class);
		controlador.listarNotificacionesDelEmpleado(mockRequest);
		
		ModelAndView modelAndView = this.controlador.irAFormularioDeRegistroDeAnimales(mockRequest, new ModelMap());
		
		Map<String, Object> modelo = modelAndView.getModel();
		
		verify(this.servicioDeAnimales).obtenerTiposDeAnimales();

		assertThat(modelo).containsKey("tiposDeAnimales");
		
		List<TipoAnimal> tiposDeAnimales = (List<TipoAnimal>) modelo.get("tiposDeAnimales");
		assertThat(tiposDeAnimales).isNotEmpty();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void laVistaRegistrarAnimalMuestraUnaListaDeRazasDisponibles() {
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession mockSesion = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSesion);
		ServicioNotificacion mockServicioNotificacion = mock(ServicioNotificacion.class);
		controlador.listarNotificacionesDelEmpleado(mockRequest);

		ModelAndView modelAndView = this.controlador.irAFormularioDeRegistroDeAnimales(mockRequest, new ModelMap());
		Map<String, Object> modelo = modelAndView.getModel();

		verify(this.servicioDeAnimales).obtenerRazasPorTipoAnimal(any(TipoAnimal.class));

		assertThat(modelo).containsKey("razas");

		List<Raza> razas = (List<Raza>) modelo.get("razas");
		assertThat(razas).isNotEmpty();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void laVistaRegistrarAnimalMuestaUnaListaDeGeneros() {
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession mockSesion = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSesion);
		ServicioNotificacion mockServicioNotificacion = mock(ServicioNotificacion.class);
		controlador.listarNotificacionesDelEmpleado(mockRequest);
		
		ModelAndView modelAndView = this.controlador.irAFormularioDeRegistroDeAnimales(mockRequest, new ModelMap());
		Map<String, Object> modelo = modelAndView.getModel();
		
		verify(this.servicioDeAnimales).obtenerGeneros();
		
		assertThat(modelo).containsKey("generos");
		List<Genero> generos = (List<Genero>) modelo.get("generos");
		assertThat(generos).isNotEmpty();
	}

	@Test
	public void seSeleccionaPorDefaultElPrimerElementoDeLosCombosDeSeleccion() {
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession mockSesion = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSesion);
		ServicioNotificacion mockServicioNotificacion = mock(ServicioNotificacion.class);
		controlador.listarNotificacionesDelEmpleado(mockRequest);

		ModelAndView modelAndView = this.controlador.irAFormularioDeRegistroDeAnimales(mockRequest, new ModelMap());
		Map<String, Object> modelo = modelAndView.getModel();

		assertThat(modelo).containsKey("animal");
		AnimalDeGranja animal = (AnimalDeGranja) modelo.get("animal");

		assertThat(animal).extracting("tipo").isNotNull();
		assertThat(animal).extracting("raza").isNotNull();
		assertThat(animal).extracting("genero").isNotNull();
	}
	
	@Test
	public void cuandoSeRedirigeALaPantallaDeRegistroDeAnimalDebidoAUnErrorEntoncesSeMantienenLosDatosCargados() {
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession mockSesion = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSesion);
		ServicioNotificacion mockServicioNotificacion = mock(ServicioNotificacion.class);
		controlador.listarNotificacionesDelEmpleado(mockRequest);

		TipoAnimal tipoSeleccionado = new TipoAnimal();
		tipoSeleccionado.setId(1L);

		AnimalDeGranja animalInvalido = new AnimalDeGranja();
		animalInvalido.setPeso(-500d);
		animalInvalido.setTipo(tipoSeleccionado);

		ModelMap modelo = new ModelMap();
		modelo.put("animal", animalInvalido);

		ModelAndView modelAndView = this.controlador.irAFormularioDeRegistroDeAnimales(mockRequest, modelo);

		verify(this.servicioDeAnimales).obtenerRazasPorTipoAnimal(eq(tipoSeleccionado));

		AnimalDeGranja animal = (AnimalDeGranja) modelAndView.getModelMap().get("animal");
		assertThat(animal.getPeso()).isEqualTo(-500d);
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

		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(false);

		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

		ModelAndView modelAndView = this.controlador.registrarAnimal(animalNuevo, bindingResult, redirectAttributes);

		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/animales");
		verify(this.servicioDeAnimales).registrar(eq(animalNuevo));
	}

	@Test
	public void cuandoFallaLaValidacionAlRegistrarAnimalSeDebeRedigirALaPantallaDeRegistrarAnimal() {

		AnimalDeGranja animalNuevo = new AnimalDeGranja();

		RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

		BindingResult bindingResult = mock(BindingResult.class);
		when(bindingResult.hasErrors()).thenReturn(true);

		ModelAndView modelAndView = this.controlador.registrarAnimal(animalNuevo, bindingResult, redirectAttributes);
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/animales/registrar");
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
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpSession mockSesion = mock(HttpSession.class);
		when(mockRequest.getSession()).thenReturn(mockSesion);
		ServicioNotificacion mockServicioNotificacion = mock(ServicioNotificacion.class);
		controlador.listarNotificacionesDelEmpleado(mockRequest);
		
		Long idAnimal = 1L;
		when(servicioDeAnimales.obtenerPorId(eq(idAnimal))).thenReturn(new AnimalDeGranja());		
		
		ModelAndView modelAndView = this.controlador.editarAnimal(mockRequest, idAnimal);
		
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

	@Test
	public void cuandoUnEmpleadoEliminaUnAnimalSeRedirigeAlListadoDeAnimales() {

		Long idAnimal = 2L;
		HttpServletRequest request = configurarRolLogueado("Empleado");

		ModelAndView modelAndView = this.controlador.eliminarAnimal(idAnimal, request);

		verify(this.servicioDeAnimales).eliminarPorId(eq(idAnimal));
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/animales");
	}

	@Test
	public void cuandoElRolQueEliminaUnAnimalNoEsEmpleadoRedirigeAlLogin() {

		Long idAnimal = 2L;
		HttpServletRequest request = configurarRolLogueado("Veterinario");

		ModelAndView modelAndView = this.controlador.eliminarAnimal(idAnimal, request);

		verifyZeroInteractions(this.servicioDeAnimales);
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/login");
	}

	private HttpServletRequest configurarRolLogueado(String rol) {

		HttpServletRequest pedido = mock(HttpServletRequest.class);
		HttpSession sesionHttp = mock(HttpSession.class);
		when(pedido.getSession()).thenReturn(sesionHttp);
		when(sesionHttp.getAttribute("ROL")).thenReturn(rol);

		return pedido;
	}
}
