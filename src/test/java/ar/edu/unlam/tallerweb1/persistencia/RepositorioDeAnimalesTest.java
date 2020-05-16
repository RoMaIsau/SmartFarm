package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.Genero;
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
	public void deberiaPersitirUnAnimalDeTipoVacunoDeRazaAberdeenAngus() {
		
		TipoAnimal tipoVacuno = new TipoAnimal();
		tipoVacuno.setNombre("VACUNO");		
		
		Raza razaAberdeenAngus = new Raza();
		razaAberdeenAngus.setNombre("ABERDEEN ANGUS");
		razaAberdeenAngus.setTipo(tipoVacuno);
		
		Genero femenino = new Genero();
		femenino.setNombre("FEMENINO");
		
		AnimalDeGranja vaca = new AnimalDeGranja();
		vaca.setTipo(tipoVacuno);
		vaca.setRaza(razaAberdeenAngus);
		vaca.setPeso(720.0);
		vaca.setGenero(femenino);
		
		Session session = this.sessionFactory.getCurrentSession();		
		session.save(tipoVacuno);
		session.save(razaAberdeenAngus);
		session.save(femenino);
		
		this.repositorioDeAnimales.guardar(vaca);
		
		assertThat(vaca.getId()).isNotNull();
		assertThat(vaca.getTipo().getId()).isNotNull();
		assertThat(vaca.getRaza().getId()).isNotNull();
	}

}
