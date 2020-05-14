package ar.edu.unlam.tallerweb1.persistencia;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
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
	public void testVacio() {
		
	}

}
