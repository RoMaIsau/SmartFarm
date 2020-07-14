package ar.edu.unlam.tallerweb1.persistencia;

import javax.transaction.Transactional;

import org.junit.Before;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlimentoImpl;

@Transactional
public class RepositorioAlimentoTest extends SpringTest{
	
	private RepositorioAlimento repositorioAlimento;
	
	@Before
	public void inicializar() {
		this.repositorioAlimento = new RepositorioAlimentoImpl(this.sessionFactory);
	}
}	
