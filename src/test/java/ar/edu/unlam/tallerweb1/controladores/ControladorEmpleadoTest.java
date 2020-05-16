package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeTiposDeAnimales;

public class ControladorEmpleadoTest {
	
	private ControladorEmpleado controlador;
	
	private ServicioDeTiposDeAnimales servicioDeTiposDeAnimales;
	
	@Before
	public void inicializar() {
		
		this.servicioDeTiposDeAnimales = crearMockServicioDeTiposDeAnimales();
		
		this.controlador = new ControladorEmpleado();
		this.controlador.setServicioDeTiposDeAnimales(this.servicioDeTiposDeAnimales);
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
}
