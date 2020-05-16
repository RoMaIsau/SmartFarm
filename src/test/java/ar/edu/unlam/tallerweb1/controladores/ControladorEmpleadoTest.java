package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

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
		
		ServicioDeAnimales servicio = mock(ServicioDeAnimales.class);
		when(servicio.obtenerTiposDeAnimales()).thenReturn(tiposDeAnimales);
		when(servicio.obtenerRazasPorTipoAnimal(any(TipoAnimal.class))).thenReturn(razas);

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

	@Test
	public void registrarAnimalRedirigeALaVistaDeRegistrarAnimal() {
		
		ModelAndView modelAndView = this.controlador.registrarAnimal();
		
		String vista = modelAndView.getViewName();
		
		assertThat(vista).isEqualTo("registrarAnimal");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void laVistaRegistrarAnimalMuestraUnaListaDeTiposDeAnimalesDisponibles() {
		
		ModelAndView modelAndView = this.controlador.registrarAnimal();
		
		Map<String, Object> modelo = modelAndView.getModel();
		
		verify(this.servicioDeAnimales).obtenerTiposDeAnimales();

		assertThat(modelo).containsKey("tiposDeAnimales");
		
		List<TipoAnimal> tiposDeAnimales = (List<TipoAnimal>) modelo.get("tiposDeAnimales");
		assertThat(tiposDeAnimales).isNotEmpty();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void laVistaRegistrarAnimalMuestraUnaListaDeRazasDisponibles() {

		ModelAndView modelAndView = this.controlador.registrarAnimal();
		Map<String, Object> modelo = modelAndView.getModel();

		verify(this.servicioDeAnimales).obtenerRazasPorTipoAnimal(any(TipoAnimal.class));

		assertThat(modelo).containsKey("razas");

		List<Raza> razas = (List<Raza>) modelo.get("razas");
		assertThat(razas).isNotEmpty();
	}
}
