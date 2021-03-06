package ar.edu.unlam.tallerweb1.servicios;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.List;

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
	
	@Test
	public void deberiaListarLosUsuarios() {
		
		List<Usuario> usuarios = this.servicioUsuario.listarUsuarios();
		
		verify(this.repositorioUsuario).listarUsuarios();
		assertThat(usuarios).isNotNull();

	}
	
	@Test
	public void deberiaConsultarUsuarioPorId() {
		Long idUsuario = 1L;
		
		when(this.repositorioUsuario.consultarUsuarioPorId(idUsuario)).thenReturn(new Usuario());
		
		Usuario usuario = this.servicioUsuario.consultarUsuarioPorId(idUsuario);
		
		verify(this.repositorioUsuario).consultarUsuarioPorId(eq(idUsuario));
		assertThat(usuario).isNotNull();
	}
	
	@Test
	public void deberiaEliminarUnUsuario() {
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		
		this.servicioUsuario.eliminarUsuario(usuario);
		
		verify(this.repositorioUsuario).eliminarUsuario(eq(usuario));
	}
	
}
