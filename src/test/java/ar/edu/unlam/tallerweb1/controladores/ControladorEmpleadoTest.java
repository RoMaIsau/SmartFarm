package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class ControladorEmpleadoTest {
	
	private ControladorEmpleado controlador;
	
	@Before
	public void inicializar() {
		
		this.controlador = new ControladorEmpleado();
	}
	
	
	@Test
	public void registrarAnimalRedirigeALaVistaDeRegistrarAnimal() {
		
		ModelAndView modelAndView = this.controlador.registrarAnimal();
		
		String vista = modelAndView.getViewName();
		
		assertThat(vista).isEqualTo("registrarAnimal");
	}

}
