package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.TipoAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoAlimentoImpl;

@Transactional
public class RepositorioTipoAlimentoTest extends SpringTest {
	
	private RepositorioTipoAlimento repositorioTipoAlimento;
	
	@Before
	public void inicializar() {
		this.repositorioTipoAlimento = new RepositorioTipoAlimentoImpl(this.sessionFactory);
	}
	
	@Test
	public void deberiaObtenerTodosLosTiposDeAlimentos() {
		TipoAlimento cereal = new TipoAlimento();
		cereal.setNombre("Cereal");
		
		TipoAlimento granos = new TipoAlimento();
		granos.setNombre("Granos");
		
		Session session = this.sessionFactory.getCurrentSession();
		session.save(cereal);
		session.save(granos);
		
		List<TipoAlimento> tiposObtenidos = this.repositorioTipoAlimento.obtenerTiposDeAlimentos();
		
		assertThat(tiposObtenidos).hasSize(2);
	}
}
