package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeGeneros;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeGenerosImpl;

@Transactional
public class RepositorioDeGenerosTest extends SpringTest {
	
	private RepositorioDeGeneros repositorioDeGeneros;
	
	@Before
	public void inicializarRepositorio() {
	
		this.repositorioDeGeneros = new RepositorioDeGenerosImpl(this.sessionFactory);
	}
	
	@Test
	public void listarTodosLosGeneros() {
		
		Genero masculino = this.guardarGenero("MASCULINO");
		Genero femenino = this.guardarGenero("FEMENINO");
		
		List<Genero> todosLosGeneros = this.repositorioDeGeneros.listar();
		
		assertThat(todosLosGeneros).hasSize(2);
		assertThat(todosLosGeneros).extracting("nombre").contains(masculino.getNombre(), femenino.getNombre());
	}
	
	private Genero guardarGenero(String nombre) {
		Genero genero = new Genero();
		genero.setNombre(nombre);
		this.sessionFactory.getCurrentSession().save(genero);
		return genero;
	}
}
