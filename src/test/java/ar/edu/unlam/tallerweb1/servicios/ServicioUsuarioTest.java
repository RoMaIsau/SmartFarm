package ar.edu.unlam.tallerweb1.servicios;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Date;

public class ServicioUsuarioTest {
	
	private ServicioUsuario servicioUsuario;
	private RepositorioUsuario repositorioUsuario;
	
	@Before
	public void inicializar() {
		this.repositorioUsuario = mock(RepositorioUsuario.class);
		
		this.servicioUsuario = new ServicioUsuarioImpl(this.repositorioUsuario);
	}
	
	@Test
	public void debeObtenerUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		
		when(this.repositorioUsuario.consultarUsuario(usuario)).thenReturn(usuario);
		
		Usuario usuarioObtenido = this.servicioUsuario.consultarUsuario(usuario);
		
		verify(this.repositorioUsuario).consultarUsuario(eq(usuario));
		assertEquals(usuario, usuarioObtenido);
		assertThat(usuarioObtenido).isNotNull();
	}
	
	@Test
	public void deberiaRegistrarUnUsuario() {
		Usuario usuario = new Usuario();
		Date date = new Date();
		
		usuario.setFechaAlta(new java.text.SimpleDateFormat("dd-MM-yyyy").format(date));
		
		this.servicioUsuario.registrarUsuario(usuario);
		
		verify(this.repositorioUsuario).registrarUsuario(eq(usuario));
		assertThat(usuario.getFechaAlta()).isNotNull();
	
	}
	
	
	
	
	
	
	
	
	
}
