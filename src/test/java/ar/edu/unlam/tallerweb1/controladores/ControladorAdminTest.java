package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.*;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioGastos;
import ar.edu.unlam.tallerweb1.servicios.ServicioTipoDeGasto;
import ar.edu.unlam.tallerweb1.servicios.ServicioTipoDeUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

public class ControladorAdminTest {

	private ControladorAdmin controladorAdmin;

	private ServicioUsuario servicioUsuario;
	private ServicioGastos servicioGastos;
	private ServicioTipoDeGasto servicioTipoDeGasto;
	private ServicioTipoDeUsuario servicioTipoDeUsuario;

	@Before
	public void inicializar() {

		this.servicioUsuario = mock(ServicioUsuario.class);
		this.servicioGastos = mock(ServicioGastos.class);
		this.servicioTipoDeGasto = mock(ServicioTipoDeGasto.class);
		this.servicioTipoDeUsuario = mock(ServicioTipoDeUsuario.class);

		this.controladorAdmin = new ControladorAdmin(this.servicioUsuario, this.servicioGastos,
				this.servicioTipoDeGasto, this.servicioTipoDeUsuario);
	}
	
	@Test
	public void siElRolNoEsAdminRedirigeALaVistaLogin() {
		HttpServletRequest request = this.configurarRolLogueado("Empleado");
		
		ModelAndView modelAndView = this.controladorAdmin.irAIndexAdmin(request);
		
		assertThat(modelAndView.getViewName()).isEqualTo("redirect:/login");
	}
	
	@Test
	public void siElRolEsAdminDebeEnlistarLosUsuarios() {
		HttpServletRequest request = this.configurarRolLogueado("Admin");
		
		Usuario usuarioUno = new Usuario();
		Usuario usuarioDos = new Usuario();
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(usuarioUno);
		usuarios.add(usuarioDos);
		
		when(this.servicioUsuario.listarUsuarios()).thenReturn(usuarios);
		
		ModelAndView modelAndV = this.controladorAdmin.listarUsuario(request);
		ModelMap modelo = modelAndV.getModelMap();
		
		verify(this.servicioUsuario).listarUsuarios();
		
		assertThat(modelAndV.getViewName()).isEqualTo("usuarios");
		assertThat(modelo).containsKey("usuarios");
		
		List<Usuario> usuariosObtenidos = (List<Usuario>) modelo.get("usuarios");
		
		assertThat(usuariosObtenidos).hasSize(2);
	}
	
	private HttpServletRequest configurarRolLogueado(String rol) {

		HttpServletRequest pedido = mock(HttpServletRequest.class);
		HttpSession sesionHttp = mock(HttpSession.class);
		when(pedido.getSession()).thenReturn(sesionHttp);
		when(sesionHttp.getAttribute("ROL")).thenReturn(rol);

		return pedido;
	}
}
