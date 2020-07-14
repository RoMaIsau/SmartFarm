package ar.edu.unlam.tallerweb1.persistencia;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGastos;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGastosImpl;

@Transactional
public class RepositorioGastosTest extends SpringTest{
	
	private RepositorioGastos repositorioGastos;
	
	@Before
	public void inicializar() {
		
		this.repositorioGastos = new RepositorioGastosImpl(this.sessionFactory);
	}

}
