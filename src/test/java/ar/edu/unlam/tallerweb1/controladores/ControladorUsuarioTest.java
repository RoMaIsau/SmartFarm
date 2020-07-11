package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

public class ControladorUsuarioTest {

	private ControladorUsuario controladorUsuario;
	private ServicioUsuario servicioUsuario;

	@Before
	public void inicializar() {
		this.servicioUsuario = mock(ServicioUsuario.class);

		this.controladorUsuario = new ControladorUsuario(this.servicioUsuario);
	}

	@Test
	public void cuandoElRolEsAdminRedirigeALaVistaIndexAdmin() {
		HttpServletRequest request = configurarRolLogueado("Admin");
		Usuario usuario = new Usuario();
		usuario.setId(1L);

		when(servicioUsuario.consultarUsuario(usuario)).thenReturn(usuario);

		ModelAndView modelAndView = this.controladorUsuario.validarLogin(usuario, request);

		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/indexAdmin");
	}
	
	@Test
	public void cuandoElRolEsEmpleadoRedirigeALaVistaIndexEmpleado() {
		HttpServletRequest request = configurarRolLogueado("Empleado");
		Usuario usuario = new Usuario();
		usuario.setId(1L);

		when(servicioUsuario.consultarUsuario(usuario)).thenReturn(usuario);

		ModelAndView modelAndView = this.controladorUsuario.validarLogin(usuario, request);

		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/indexEmpleado");
	}

	private HttpServletRequest configurarRolLogueado(String rol) {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession session = mock(HttpSession.class);

		when(request.getSession()).thenReturn(session);
		when(session.getAttribute("ROL")).thenReturn(rol);

		return request;

	}
}
