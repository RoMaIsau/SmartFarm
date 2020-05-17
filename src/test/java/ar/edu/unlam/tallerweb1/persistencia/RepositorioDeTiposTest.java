package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeTipos;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeTiposImpl;

@Transactional
public class RepositorioDeTiposTest extends SpringTest {
	
	private RepositorioDeTipos repositorioDeTipos;
	
	@Before
	public void inicializarRepositorio() {
		
		this.repositorioDeTipos = new RepositorioDeTiposImpl(this.sessionFactory);
	}
	
	@Test
	public void listaLosTiposDeAnimalesDisponibles() {

		TipoAnimal equino = new TipoAnimal();
		equino.setNombre("EQUINO");

		TipoAnimal vacuno = new TipoAnimal();
		vacuno.setNombre("VACUNO");

		TipoAnimal porcino = new TipoAnimal();
		porcino.setNombre("PORCINO");

		Session session = this.sessionFactory.getCurrentSession();

		session.save(equino);
		session.save(vacuno);
		session.save(porcino);

		List<TipoAnimal> tiposDisponibles = this.repositorioDeTipos.listarDisponibles();

		assertThat(tiposDisponibles).hasSize(3);
		assertThat(tiposDisponibles).extracting("nombre").contains("EQUINO", "VACUNO", "PORCINO");
	}

}
