package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.TipoDeUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoDeUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoDeUsuarioImpl;

@Transactional
public class RepositorioTipoDeUsuarioTest extends SpringTest{
	
	private RepositorioTipoDeUsuario repositorioTipoDeUsuario;
	
	@Before
	public void inicializar() {
		this.repositorioTipoDeUsuario = new RepositorioTipoDeUsuarioImpl(this.sessionFactory);
	}
	
	@Test
	public void testParaValidarLaCargaDeMasDeUnNuevoTipoDeUsuario() {
		TipoDeUsuario rol1 = new TipoDeUsuario();
		rol1.setNombre("Contador");
		this.repositorioTipoDeUsuario.guardarTipoDeUsuario(rol1);
		
		TipoDeUsuario rol2 = new TipoDeUsuario();
		rol2.setNombre("Recursos humanos");
		this.repositorioTipoDeUsuario.guardarTipoDeUsuario(rol2);
		
		List<TipoDeUsuario> tipo = this.repositorioTipoDeUsuario.ObtenerTodosLosRoles();
		
		assertThat(tipo).hasSize(2);
	}
	
	@Test
	public void testParaValidarLaCargaDeUnTipoDeUsuario() {
		TipoDeUsuario rol = new TipoDeUsuario();
		rol.setNombre("Admin");
		this.repositorioTipoDeUsuario.guardarTipoDeUsuario(rol);
		
		TipoDeUsuario tipo = this.repositorioTipoDeUsuario.consultarRol(rol.getNombre());
		
		assertEquals(rol, tipo);
	}
}










