package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.TipoDeGasto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoDeGasto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoDeGastoImpl;

@Transactional
public class RepositorioTipoDeGastoTest extends SpringTest{
	
	private RepositorioTipoDeGasto repositorioTipoDeGasto;
	
	@Before
	public void inicializar() {
		this.repositorioTipoDeGasto = new RepositorioTipoDeGastoImpl(this.sessionFactory);
	}
	
	@Test
	public void deberiaObtenerTodosLosTiposDeGastos() {
		TipoDeGasto alimenticio = new TipoDeGasto();
		alimenticio.setNombre("Alimenticio");
		
		TipoDeGasto medico = new TipoDeGasto();
		medico.setNombre("Medico");
		
		Session session = this.sessionFactory.getCurrentSession();
		session.save(alimenticio);
		session.save(medico);
		
		List<TipoDeGasto> tipos = this.repositorioTipoDeGasto.obtenerTiposDeGastos();
		
		assertThat(tipos).hasSize(2);
		assertThat(tipos).extracting("nombre").contains("Medico");
	}
}
