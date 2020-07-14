package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoAlimentoImpl;

public class RepositorioTipoAlimentoTest extends SpringTest {
	
	private RepositorioTipoAlimento repositorioTipoAlimento;
	
	@Before
	public void inicializar() {
		this.repositorioTipoAlimento = new RepositorioTipoAlimentoImpl(this.sessionFactory);
	}
	
	
}
