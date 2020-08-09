package ar.edu.unlam.tallerweb1.persistencia;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Corral;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGanado;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGanadoImpl;

import java.util.Calendar;

import javax.transaction.Transactional;

@Transactional
public class RepositorioGanadoTest extends SpringTest {
	private RepositorioGanado repositorioGanado;
	
	@Before
	public void iniciar() {
		this.repositorioGanado = new RepositorioGanadoImpl(this.sessionFactory);
	}
	
	private AnimalDeGranja crearAnimal(Long id, TipoAnimal tipo, Raza raza, Genero genero, Calendar fechaNacimiento,
	Double peso, HistoriaClinica historia, String identificadorGps, Corral corral) {
		AnimalDeGranja animal = new AnimalDeGranja();

		this.sessionFactory.getCurrentSession().save(tipo);
		this.sessionFactory.getCurrentSession().save(raza);
		this.sessionFactory.getCurrentSession().save(genero);
		this.sessionFactory.getCurrentSession().save(historia);
		
		animal.setId(id);
		animal.setTipo(tipo);
		animal.setRaza(raza);
		animal.setGenero(genero);
		animal.setFechaNacimiento(fechaNacimiento);
		animal.setPeso(peso);
		animal.setHistoria(historia);
		animal.setIdentificadorGps(identificadorGps);
		animal.setCorral(corral);
		
		return animal;
	}
	
	@Test
	public void testQueVerificaElMetodoGuardar() {
		Calendar fecha = null;
		Long id = 100L;
		
		HistoriaClinica hc = new HistoriaClinica();
		hc.setId(id);
		
		TipoAnimal tipo = new TipoAnimal();
		tipo.setId(id);
		
		Raza raza = new Raza();
		raza.setId(id);
		
		Genero genero = new Genero();
		genero.setId(id);
		
		AnimalDeGranja animal = crearAnimal(id, tipo, raza, genero, fecha, 100.0, hc, null, null);
		
		this.repositorioGanado.guardar(animal);
		
		assertNotNull(animal.getId());
	}
	
}
