package ar.edu.unlam.tallerweb1.persistencia;

import javax.transaction.Transactional;

import org.junit.Before;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoDeGasto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoDeGastoImpl;

@Transactional
public class RepositorioTipoDeGastoTest extends SpringTest{
	
	private RepositorioTipoDeGasto repositorioTipoDeGasto;
	
	@Before
	public void inicializar() {
		this.repositorioTipoDeGasto = new RepositorioTipoDeGastoImpl(this.sessionFactory);
	}
}
