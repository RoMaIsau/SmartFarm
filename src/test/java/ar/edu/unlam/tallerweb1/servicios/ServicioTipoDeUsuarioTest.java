package ar.edu.unlam.tallerweb1.servicios;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.TipoDeUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoDeUsuario;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import java.util.List;

public class ServicioTipoDeUsuarioTest {

	private ServicioTipoDeUsuario servicioTipoDeUsuario;
	private RepositorioTipoDeUsuario repositorioTipoDeUsuario;
	
	@Before
	public void inicializar() {
		this.repositorioTipoDeUsuario = mock(RepositorioTipoDeUsuario.class);
		this.servicioTipoDeUsuario = new ServicioTipoDeUsuarioImpl(this.repositorioTipoDeUsuario);
	}
	
	@Test
	public void testParaValidarElRolDelUsuario() {
		String rol = "Admin";
		
		this.servicioTipoDeUsuario.consultarRol(rol);
		verify(this.repositorioTipoDeUsuario).consultarRol(eq(rol));
	}
	
	@Test
	public void testParaObtenerTodosLosRoles() {
		List<TipoDeUsuario> lista = this.servicioTipoDeUsuario.ObtenerTodosLosRoles();
		verify(this.repositorioTipoDeUsuario).ObtenerTodosLosRoles();
		assertThat(lista).isNotNull();
	}
	
}
