package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
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
import ar.edu.unlam.tallerweb1.servicios.ServicioDeTiposDeAnimales;

public class ControladorEmpleadoTest {
	
	private ControladorEmpleado controlador;
	
	private ServicioDeAnimales servicioDeAnimales;
	private ServicioDeTiposDeAnimales servicioDeTiposDeAnimales;
	
	@Before
	public void inicializar() {
		
		this.servicioDeAnimales = crearMockServicioDeAnimales();
		this.servicioDeTiposDeAnimales = crearMockServicioDeTiposDeAnimales();
		
		this.controlador = new ControladorEmpleado();
		this.controlador.setServicioDeAnimales(this.servicioDeAnimales);
		this.controlador.setServicioDeTiposDeAnimales(this.servicioDeTiposDeAnimales);
	}
	
	private ServicioDeAnimales crearMockServicioDeAnimales() {

		Raza caballoArabe = new Raza();
		caballoArabe.setId(1L);
		caballoArabe.setNombre("CABALLO ARABE");
		List<Raza> razas = new LinkedList<Raza>();
		razas.add(caballoArabe);

		ServicioDeAnimales servicio = mock(ServicioDeAnimales.class);

		when(servicio.obtenerRazasPorTipoAnimal(any())).thenReturn(razas);

		return servicio;
	}
	private ServicioDeTiposDeAnimales crearMockServicioDeTiposDeAnimales() {
		
		TipoAnimal vacuno = new TipoAnimal();
		vacuno.setId(1L);
		vacuno.setNombre("VACUNO");
		List<TipoAnimal> listaDeTiposDeAnimales = new LinkedList<TipoAnimal>();
		listaDeTiposDeAnimales.add(vacuno);		
		
		ServicioDeTiposDeAnimales servicio = mock(ServicioDeTiposDeAnimales.class);

		when(servicio.obtenerDisponibles())
			.thenReturn(listaDeTiposDeAnimales);
		
		return servicio; 
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
		
		verify(this.servicioDeTiposDeAnimales).obtenerDisponibles();

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
