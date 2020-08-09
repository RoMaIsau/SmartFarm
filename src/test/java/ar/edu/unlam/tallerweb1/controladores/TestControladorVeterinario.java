package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanado;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanadoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioHistoriaClinica;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioVacunaImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioVacunas;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;

public class TestControladorVeterinario {
	private ControladorVeterinario controladorVeterinario;
	
	private ServicioNotificacion servicioNotificacion;
	private ServicioGanado servicioGanado;
	private ServicioVacunas servicioVacunas;
	private ServicioDeAnimales servicioDeAnimales;
	private ServicioHistoriaClinica servicioHistoriaClinica;
	
	private List<Raza> crearRazas() {
		Raza caballoArabe = new Raza();
		caballoArabe.setId(1L);
		caballoArabe.setNombre("CABALLO ARABE");
		List<Raza> razas = new LinkedList<Raza>();
		razas.add(caballoArabe);
		return razas;
	}
	private List<TipoAnimal> crearTiposDeAnimales() {
		TipoAnimal vacuno = new TipoAnimal();
		vacuno.setId(1L);
		vacuno.setNombre("VACUNO");
		List<TipoAnimal> tiposDeAnimales = new LinkedList<TipoAnimal>();
		tiposDeAnimales.add(vacuno);
		return tiposDeAnimales;
	}
	private List<Genero> crearGeneros() {
		Genero femenino = new Genero();
		femenino.setId(1L);
		femenino.setNombre("FEMENINO");
		List<Genero> generos = new LinkedList<Genero>();
		generos.add(femenino);
		return generos;
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
	private HttpServletRequest configurarRolLogueado(String rol) {
		HttpServletRequest pedido = mock(HttpServletRequest.class);
		HttpSession sesionHttp = mock(HttpSession.class);
		when(pedido.getSession()).thenReturn(sesionHttp);
		when(sesionHttp.getAttribute("ROL")).thenReturn(rol);
		return pedido;
	}
	
	@Before	
	public void inicializar() {
		this.servicioDeAnimales = crearMockServicioDeAnimales();
		this.servicioNotificacion = mock(ServicioNotificacion.class);
		this.servicioHistoriaClinica = mock(ServicioHistoriaClinica.class);
		this.servicioGanado = mock(ServicioGanado.class);
		this.servicioVacunas = mock(ServicioVacunas.class);
		
		this.controladorVeterinario = new ControladorVeterinario(this.servicioNotificacion, this.servicioGanado,
				this.servicioVacunas, this.servicioDeAnimales, this.servicioHistoriaClinica);
	}
	
	
	
	
	
	@Test
	public void testVerSaludViewError(){
		HistoriaClinica hc = new HistoriaClinica();
		ServicioVacunaImpl servicioVacunaMock= mock(ServicioVacunaImpl.class);
		ServicioGanadoImpl servicioNullMock= mock(ServicioGanadoImpl.class);
		AnimalDeGranja animalNullMock= mock(AnimalDeGranja.class);
		when(servicioNullMock.verHC(animalNullMock)).thenReturn(hc);
		List<SignosVitales>signos= new ArrayList<SignosVitales>();
		when(servicioNullMock.signos(hc)).thenReturn(signos);
		when(servicioVacunaMock.obtenerVacunasAplicadas(1L)).thenReturn(null);
		when(servicioNullMock.ver(1L)).thenReturn(animalNullMock);
		
		this.controladorVeterinario.setServicioGanado(servicioNullMock);
		this.controladorVeterinario.setServicioVacuna(servicioVacunaMock);
		
		HttpServletRequest requestMock = mock(HttpServletRequest.class);
		HttpSession sessionMock = mock(HttpSession.class);
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("ROL")).thenReturn("Veterinario");
		ModelAndView modelView = this.controladorVeterinario.verSalud(1L, requestMock);
		assertThat(modelView.getViewName()).isEqualTo("Error");
	}
	
	@Test
	public void testVerSaludViewHomeAnimal(){
		HistoriaClinica hc = new HistoriaClinica();
		ServicioVacunaImpl servicioVacunaMock= mock(ServicioVacunaImpl.class);
		ServicioGanadoImpl servicioMock= mock(ServicioGanadoImpl.class);
		List<SignosVitales> signos= new ArrayList<SignosVitales>();
		SignosVitales signo1= new SignosVitales();
		SignosVitales signo2= new SignosVitales();
		signos.add(signo1);
		signos.add(signo2);
		when(servicioMock.signos(hc)).thenReturn(signos);
		when(servicioVacunaMock.obtenerVacunasAplicadas(1L)).thenReturn(null);
		AnimalDeGranja animalMock= mock(AnimalDeGranja.class);
		when(servicioMock.verHC(animalMock)).thenReturn(hc);
		when(servicioMock.ver(1L)).thenReturn(animalMock);
		this.controladorVeterinario.setServicioGanado(servicioMock);
		this.controladorVeterinario.setServicioVacuna(servicioVacunaMock);
		
		//ejecucion
		HttpServletRequest requestMock = mock(HttpServletRequest.class);
		HttpSession sessionMock = mock(HttpSession.class);
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("ROL")).thenReturn("Veterinario");
		ModelAndView modelView2 = this.controladorVeterinario.verSalud(1L, requestMock);
		assertThat(modelView2.getViewName()).isEqualTo("HomeAnimal");
	}
	
	@Test
	public void testParaValidarElIngresoASignosVitales() {
		HttpServletRequest request = configurarRolLogueado("Veterinario");
		Long idAnimal = 1l;
		
		SignosVitales signosVitalesReales = mock(SignosVitales.class);
		when(this.servicioDeAnimales.buscarUltimosSignosVitalesDelAnimal(idAnimal)).thenReturn(signosVitalesReales);
		
		ModelAndView modelAndView = controladorVeterinario.signosVitales(request, 1L);
		ModelMap modelo = modelAndView.getModelMap();
		
		assertThat(modelAndView.getViewName()).isEqualTo("signosVitales");
		assertThat(modelo).containsKey("cardio1");
		assertThat(modelo).containsKey("orina1");
		assertThat(modelo).containsKey("temperatura1");
		assertThat(modelo).containsKey("respiracion1");
		assertThat(modelo).containsKey("enfermedadClase");
	}
	
	@Test
	public void testParaValidarQueNoIngreseASignosVitalesSiNoEsVeterinario() {
		HttpServletRequest request = configurarRolLogueado("Empleado");
		Long idAnimal = 1l;
		
		ModelAndView modelAndView = new ModelAndView("redirect:/login");
		modelAndView = controladorVeterinario.signosVitales(request, idAnimal);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/login");
	}
	
	@Test
	public void testParaValidarElIngresoADiagnosticar() {
		Long id = 1L;
		
		HttpServletRequest requestMock = mock(HttpServletRequest.class);
		HttpSession sessionMock = mock(HttpSession.class);
		when(requestMock.getSession()).thenReturn(sessionMock);
		when(sessionMock.getAttribute("ROL")).thenReturn("Veterinario");
		
		ModelAndView modelAndView = controladorVeterinario.diagnosticar(id, requestMock);
		
		assertThat(modelAndView.getViewName()).isEqualTo("consultaVeterinario");
	}
	
}
