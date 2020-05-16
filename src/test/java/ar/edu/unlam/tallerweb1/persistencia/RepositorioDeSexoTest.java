package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeGenero;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeGeneroImpl;

@Transactional
public class RepositorioDeSexoTest extends SpringTest {
	
	private RepositorioDeGenero repositorioDeSexo;
	
	@Before
	public void inicializarRepositorio() {
	
		this.repositorioDeSexo = new RepositorioDeGeneroImpl(this.sessionFactory);
	}
	
	@Test
	public void listarTodosLosSexos() {
		
		Genero masculino = this.guardarSexo("MASCULINO");
		Genero femenino = this.guardarSexo("FEMENINO");
		
		List<Genero> todosLosSexos = this.repositorioDeSexo.listar();
		
		assertThat(todosLosSexos).hasSize(2);
		assertThat(todosLosSexos).extracting("nombre").contains(masculino.getNombre(), femenino.getNombre());
	}
	
	private Genero guardarSexo(String nombre) {
		Genero sexo = new Genero();
		sexo.setNombre(nombre);
		this.sessionFactory.getCurrentSession().save(sexo);
		return sexo;
	}
}
