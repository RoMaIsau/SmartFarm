package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlimento;
import ar.edu.unlam.tallerweb1.servicios.ServicioAnimalUbicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUbicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioVacunas;

public class ControladorMapaTest {

	private ControladorMapa controladorMapa;

	private ServicioUbicacion servicioUbicacion;
	private ServicioNotificacion servicioNotificacion;
	private ServicioDeAnimales servicioDeAnimales;
	private ServicioAnimalUbicacion servicioAnimalUbicacion;
	private ServicioAlimento servicioAlimento;
	private ServicioVacunas servicioVacunas;

	@Before
	public void inicializar() {

		this.servicioUbicacion = mock(ServicioUbicacion.class);
		this.servicioAlimento = mock(ServicioAlimento.class);
		this.servicioAnimalUbicacion = mock(ServicioAnimalUbicacion.class);
		this.servicioNotificacion = mock(ServicioNotificacion.class);
		this.servicioDeAnimales = mock(ServicioDeAnimales.class);
		this.servicioVacunas = mock(ServicioVacunas.class);

		this.controladorMapa = new ControladorMapa(this.servicioUbicacion, this.servicioNotificacion,
				this.servicioDeAnimales, this.servicioAnimalUbicacion, this.servicioAlimento, this.servicioVacunas);
	}
	
	@Test
	public void siElRolNoEsEmpleadoNiAdminNiVeterinarioRedirigeALaVistaLogin() {
		HttpServletRequest request = configurarRolLogueado("");
	
		ModelMap modelo = new ModelMap();
		
		ModelAndView modelAndView = this.controladorMapa.irAMapa(request, modelo);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/login");
	}
	
	@Test
	public void dirigeAVistaMapaConUbicacionesYNotificaciones() {
		HttpServletRequest request = configurarRolLogueado("Empleado");
		
		AnimalUbicacion animalUbicacion = new AnimalUbicacion();
		animalUbicacion.setId(1L);
		
		Notificacion notificacion = new Notificacion();
		notificacion.setId(1L);
		
		List<AnimalUbicacion> listaUbicaciones = new ArrayList<AnimalUbicacion>();
		listaUbicaciones.add(animalUbicacion);
		
		List<Notificacion> listaNotificaciones= new ArrayList<Notificacion>();
		listaNotificaciones.add(notificacion);
		
		when(this.servicioUbicacion.obtenerUbicaciones()).thenReturn(listaUbicaciones);
		when(this.servicioNotificacion.listarNotificaciones(1L)).thenReturn(listaNotificaciones);
		
		ModelAndView modelAndView = this.controladorMapa.irAMapa(request, new ModelMap());
		
		assertThat(modelAndView.getViewName()).isEqualTo("mapa");
		assertThat(modelAndView.getModelMap()).containsKey("lista");
		assertThat(modelAndView.getModelMap()).containsKey("notificaciones");
		
		List<AnimalUbicacion> aUbiObtenidos = (List<AnimalUbicacion>) modelAndView.getModelMap().get("lista");
		List<Notificacion> notificacionesObtenidos = (List<Notificacion>) modelAndView.getModelMap().get("notificaciones");
		
		assertThat(aUbiObtenidos).isNotEmpty();
		assertThat(notificacionesObtenidos).isNotEmpty();
	}
	
	private HttpServletRequest configurarRolLogueado(String rol) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);

		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("ROL")).thenReturn(rol);
		when(session.getAttribute("ID")).thenReturn(1L);

		return request;

	}
}
