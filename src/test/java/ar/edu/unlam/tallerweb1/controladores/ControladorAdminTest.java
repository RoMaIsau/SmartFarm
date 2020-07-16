package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertNotNull;

import ar.edu.unlam.tallerweb1.modelo.Gastos;
import ar.edu.unlam.tallerweb1.modelo.TipoDeGasto;
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
	
	@Test
	public void siElRolEsAdminEnlistaTodosLosGastos() {
		HttpServletRequest request = this.configurarRolLogueado("Admin");
		
		Gastos gastosUno = new Gastos();
		Gastos gastosDos = new Gastos();
		
		List<Gastos> gastos = new ArrayList<Gastos>();
		gastos.add(gastosUno);
		gastos.add(gastosDos);
		
		when(this.servicioGastos.consultarGastos()).thenReturn(gastos);
		
		ModelAndView modelAndV = this.controladorAdmin.irAGastos(request);
		ModelMap modelo = modelAndV.getModelMap();
		
		verify(this.servicioGastos).consultarGastos();
		assertThat(modelAndV.getViewName()).isEqualTo("gastos");
		assertThat(modelo).containsKey("gastos");
		
		List<Gastos> gastosObtenidos = (List<Gastos>) modelo.get("gastos");
		
		assertNotNull(gastosObtenidos);
		assertThat(gastosObtenidos).hasSize(2);
	}
	
	@Test
	public void seDebeObtenerEstadisticasDeGastos() {
		HttpServletRequest request = this.configurarRolLogueado("Admin");
		request.getSession().setAttribute("ID", 1L);
		
		TreeMap<Integer, Double> gastosAlimenticios = new TreeMap<Integer, Double>();
		gastosAlimenticios.put(1, 10.0);
		gastosAlimenticios.put(2, 20.0);
		
		when(this.servicioGastos.consultarGastosPorMes("Alimenticio")).thenReturn(gastosAlimenticios);
		
		Gastos gastosUno = new Gastos();
		Gastos gastosDos = new Gastos();
		
		List<Gastos> gastosEnTotalPorTipo = new ArrayList<Gastos>();
		gastosEnTotalPorTipo.add(gastosUno);
		gastosEnTotalPorTipo.add(gastosDos);
		
		when(this.servicioGastos.consultarGastosEnTotalPorTipo()).thenReturn(gastosEnTotalPorTipo);
		
		TreeMap<Integer, Double> gastosEnTotalPorMes = new TreeMap<Integer, Double>();
		gastosEnTotalPorMes.put(1, 10.0);
		gastosEnTotalPorMes.put(2, 20.0);
		
		when(this.servicioGastos.consultarGastosEnTotal()).thenReturn(gastosEnTotalPorMes);
		
		ModelAndView modelAndV = this.controladorAdmin.irAEstadisticas(request);
		ModelMap modelo = modelAndV.getModelMap();
		
		verify(this.servicioGastos).consultarGastosPorMes(eq("Alimenticio"));
		assertThat(modelAndV.getViewName()).isEqualTo("estadisticas");
		assertThat(modelo).containsKey("alimenticio");
		assertThat(modelo).containsKey("gastosEnTotalPorTipo");
		assertThat(modelo).containsKey("gastosEnTotalPorMes");
		
		TreeMap<Integer, Double> alimenticiosObtenidos = (TreeMap<Integer, Double>) modelo.get("alimenticio");
		List<Gastos> gastosEnTotalPorTipoObtenidos =  (List<Gastos>) modelo.get("gastosEnTotalPorTipo");
		TreeMap<Integer, Double> gastosEnTotalPorMesObtenidos = (TreeMap<Integer, Double>) modelo.get("gastosEnTotalPorMes");
		
		assertNotNull(alimenticiosObtenidos);
		assertThat(alimenticiosObtenidos).hasSize(2);
		assertNotNull(gastosEnTotalPorMesObtenidos);
		assertThat(gastosEnTotalPorMesObtenidos).hasSize(2);
		assertNotNull(gastosEnTotalPorTipoObtenidos);
		assertThat(gastosEnTotalPorTipoObtenidos).hasSize(2);
	}
	
	@Test
	public void laVistaRegistrarGastoMuestraListaDeTiposDeGastos() {
		TipoDeGasto tipoUno = new TipoDeGasto();
		tipoUno.setNombre("Medico");
		TipoDeGasto tipoDos = new TipoDeGasto();
		tipoDos.setNombre("Alimenticio");
		
		List<TipoDeGasto> tipos = new ArrayList<TipoDeGasto>();
		tipos.add(tipoUno);
		tipos.add(tipoDos);
		
		when(this.servicioTipoDeGasto.obtenerTiposDeGastos()).thenReturn(tipos);
		
		ModelAndView modelAndV = this.controladorAdmin.irANuevaEstadistica();
		ModelMap modelo = modelAndV.getModelMap();
		
		verify(this.servicioTipoDeGasto).obtenerTiposDeGastos();
		assertThat(modelo).containsKey("tipoDeGastos");
		
		List<TipoDeGasto> tiposObtenidos = (List<TipoDeGasto>) modelo.get("tipoDeGastos");
		
		assertThat(tiposObtenidos).hasSize(2);
	}
	
	@Test
	public void debeValidarUnNuevoGasto() {
		HttpServletRequest request = this.configurarRolLogueado("Admin");
		request.getSession().setAttribute("ID", 1L);
		
		TipoDeGasto tipoUno = new TipoDeGasto();
		tipoUno.setId(1L);
		tipoUno.setNombre("Medico");
		
		List<TipoDeGasto> tipos = new ArrayList<TipoDeGasto>();
		tipos.add(tipoUno);
		
		when(this.servicioTipoDeGasto.obtenerTiposDeGastos()).thenReturn(tipos);
		
		Gastos gastoUno = new Gastos();
		gastoUno.setMonto(100.0);
		gastoUno.setTipoDeGasto(tipoUno);
		
		ModelAndView modelAndV = this.controladorAdmin.validarNuevaEstadistica(gastoUno, request);
		ModelMap modelo = modelAndV.getModelMap();
		
		verify(this.servicioTipoDeGasto).obtenerTiposDeGastos();
		assertThat(modelo).containsKey("mensaje");
	}
	
	private HttpServletRequest configurarRolLogueado(String rol) {

		HttpServletRequest pedido = mock(HttpServletRequest.class);
		HttpSession sesionHttp = mock(HttpSession.class);
		when(pedido.getSession()).thenReturn(sesionHttp);
		when(sesionHttp.getAttribute("ROL")).thenReturn(rol);

		return pedido;
	}
}
