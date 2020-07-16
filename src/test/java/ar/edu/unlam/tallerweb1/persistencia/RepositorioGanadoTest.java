package ar.edu.unlam.tallerweb1.persistencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGanado;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGanadoImpl;
import static org.mockito.Mockito.*;

public class RepositorioGanadoTest {
	
	private RepositorioGanado repositorioGanado;
	private SessionFactory sessionFactory;
	
	@Before
	public void iniciar() {
		this.repositorioGanado = new RepositorioGanadoImpl(this.sessionFactory);
	}
	
	@Test
	public void testQueVerificaElMetodoVer() {
		AnimalDeGranja animal = new AnimalDeGranja();
		animal.setId(1L);
		this.repositorioGanado.guardar(animal);
		
		//AnimalDeGranja aVerificar = this.repositorioGanado.ver(1L);
		
		//assertTrue(animal.equals(aVerificar));
	}

}
/*
@Override
	public AnimalDeGranja ver(Long id) {

		final Session session = sessionFactory.getCurrentSession();
		return (AnimalDeGranja) session.createCriteria(AnimalDeGranja.class)
				.add(Restrictions.eq("id", id))
				
				.uniqueResult();
	}
*/







