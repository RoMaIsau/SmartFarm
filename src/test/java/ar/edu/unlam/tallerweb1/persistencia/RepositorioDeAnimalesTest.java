package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeAnimales;

@Transactional
public class RepositorioDeAnimalesTest extends SpringTest {
	
	private RepositorioDeAnimales repositorioDeAnimales;
	
	@Before
	public void inicializarRepositorio() {
		
		this.repositorioDeAnimales = new RepositorioDeAnimalesImpl(this.sessionFactory);
	}
	
	@Test
	public void deberiaPersitirUnAnimalDeTipoVacuno() {
		
		TipoAnimal tipoVacuno = new TipoAnimal();
		tipoVacuno.setNombre("VACUNO");		
		
		AnimalDeGranja vaca = new AnimalDeGranja();
		vaca.setTipo(tipoVacuno);
		
		Session session = this.sessionFactory.getCurrentSession();		
		session.save(tipoVacuno);
		session.save(vaca);
		
		assertThat(vaca.getId()).isEqualTo(1L);
		assertThat(vaca.getTipo().getId()).isEqualTo(1L);
	}

}
