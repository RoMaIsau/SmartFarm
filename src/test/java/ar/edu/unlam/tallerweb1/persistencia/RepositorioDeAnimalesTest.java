package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

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
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeAnimalesImpl;

@Transactional
public class RepositorioDeAnimalesTest extends SpringTest {
	
	private RepositorioDeAnimales repositorioDeAnimales;
	
	@Before
	public void inicializarRepositorio() {
		
		this.repositorioDeAnimales = new RepositorioDeAnimalesImpl(this.sessionFactory);
	}
	
	@Test
	public void deberiaPersitirUnAnimalDeTipoVacunoDeRazaAberdeenAngus() {
		
		AnimalDeGranja vaca = this.crearAnimal("VACUNO", "ABERDEEN ANGUS", "FEMENINO", 720.0);
		
		this.repositorioDeAnimales.guardar(vaca);
		
		assertThat(vaca.getId()).isNotNull();
		assertThat(vaca.getTipo().getId()).isNotNull();
		assertThat(vaca.getRaza().getId()).isNotNull();
	}

	@Test
	public void deberiaListarTodosLosAnimalesAlmacenados() {

		AnimalDeGranja vaca = this.crearAnimal("VACUNO", "ABERDEEN ANGUS", "FEMENINO", 720.0);
		AnimalDeGranja caballo = this.crearAnimal("EQUINO", "CABALLO ARABE", "FEMENINO", 900.0);
		this.repositorioDeAnimales.guardar(vaca);
		this.repositorioDeAnimales.guardar(caballo);

		List<AnimalDeGranja> animales = this.repositorioDeAnimales.listar();

		assertThat(animales).hasSize(2);
	}

	private AnimalDeGranja crearAnimal(String nombreTipo, String nombreRaza, String nombreGenero, double peso) {

		TipoAnimal tipo = new TipoAnimal();
		tipo.setNombre(nombreTipo);

		Raza raza = new Raza();
		raza.setNombre(nombreRaza);
		raza.setTipo(tipo);

		Genero genero = new Genero();
		genero.setNombre(nombreGenero);

		Session session = this.sessionFactory.getCurrentSession();
		session.save(tipo);
		session.save(raza);
		session.save(genero);

		AnimalDeGranja animal = new AnimalDeGranja();
		animal.setTipo(tipo);
		animal.setRaza(raza);
		animal.setPeso(peso);
		animal.setGenero(genero);

		return animal;
	}
}