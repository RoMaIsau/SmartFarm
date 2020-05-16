package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Sexo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeSexo;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeSexoImpl;

@Transactional
public class RepositorioDeSexoTest extends SpringTest {
	
	private RepositorioDeSexo repositorioDeSexo;
	
	@Before
	public void inicializarRepositorio() {
	
		this.repositorioDeSexo = new RepositorioDeSexoImpl(this.sessionFactory);
	}
	
	@Test
	public void listarTodosLosSexos() {
		
		Sexo masculino = this.guardarSexo("MASCULINO");
		Sexo femenino = this.guardarSexo("FEMENINO");
		
		List<Sexo> todosLosSexos = this.repositorioDeSexo.listar();
		
		assertThat(todosLosSexos).hasSize(2);
		assertThat(todosLosSexos).extracting("nombre").contains(masculino.getNombre(), femenino.getNombre());
	}
	
	private Sexo guardarSexo(String nombre) {
		Sexo sexo = new Sexo();
		sexo.setNombre(nombre);
		this.sessionFactory.getCurrentSession().save(sexo);
		return sexo;
	}
}
