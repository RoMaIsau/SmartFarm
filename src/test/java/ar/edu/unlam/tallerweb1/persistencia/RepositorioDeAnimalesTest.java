package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeAnimales;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeAnimalesImpl;

@Transactional
public class RepositorioDeAnimalesTest extends SpringTest {

	private final String IDENTIFICADOR_GPS = "GPS0001";
	private RepositorioDeAnimales repositorioDeAnimales;
	
	@Before
	public void inicializarRepositorio() {
		
		this.repositorioDeAnimales = new RepositorioDeAnimalesImpl(this.sessionFactory);
	}
	
	@Test
	public void deberiaPersitirUnAnimalDeTipoVacunoDeRazaAberdeenAngus() {
		
		AnimalDeGranja vaca = this.crearAnimal("VACUNO", "ABERDEEN ANGUS", "FEMENINO", 720.0, IDENTIFICADOR_GPS);
		
		this.repositorioDeAnimales.guardar(vaca);
		
		assertThat(vaca.getId()).isNotNull();
		assertThat(vaca.getTipo().getId()).isNotNull();
		assertThat(vaca.getRaza().getId()).isNotNull();
	}

	@Test
	public void deberiaListarTodosLosAnimalesAlmacenados() {

		AnimalDeGranja vaca = this.crearAnimal("VACUNO", "ABERDEEN ANGUS", "FEMENINO", 720.0, IDENTIFICADOR_GPS);
		AnimalDeGranja caballo = this.crearAnimal("EQUINO", "CABALLO ARABE", "FEMENINO", 900.0, IDENTIFICADOR_GPS);
		this.repositorioDeAnimales.guardar(vaca);
		this.repositorioDeAnimales.guardar(caballo);

		List<AnimalDeGranja> animales = this.repositorioDeAnimales.listar();

		assertThat(animales).hasSize(2);
	}

	@Test
	public void deberiaObtenerUnAnimalPorId() {

		AnimalDeGranja vaca = this.crearAnimal("VACUNO", "ABERDEEN ANGUS", "FEMENINO", 720.0, IDENTIFICADOR_GPS);
		AnimalDeGranja caballo = this.crearAnimal("EQUINO", "CABALLO ARABE", "FEMENINO", 900.0, IDENTIFICADOR_GPS);

		this.repositorioDeAnimales.guardar(vaca);
		this.repositorioDeAnimales.guardar(caballo);

		AnimalDeGranja animalBuscado = this.repositorioDeAnimales.buscarPorId(vaca.getId());

		assertThat(animalBuscado).isEqualTo(vaca);
	}

	@Test
	public void deberiaActualizarElPesoDelAnimal() {
		AnimalDeGranja vaca = this.crearAnimal("VACUNO", "ABERDEEN ANGUS", "FEMENINO", 720.0, IDENTIFICADOR_GPS);

		this.repositorioDeAnimales.guardar(vaca);
		
		vaca.setPeso(800.0);
		
		this.repositorioDeAnimales.actualizar(vaca);
		
		AnimalDeGranja animalBuscado = this.repositorioDeAnimales.buscarPorId(vaca.getId());
		assertThat(animalBuscado.getPeso()).isEqualTo(800.0);
	}

	@Test
	public void deberiaEliminarAnimal() {

		AnimalDeGranja animalParaBorrar = this.crearAnimal("VACUNO", "ABERDEEN ANGUS", "FEMENINO", 720.0, IDENTIFICADOR_GPS);
		this.repositorioDeAnimales.guardar(animalParaBorrar);

		this.repositorioDeAnimales.eliminar(animalParaBorrar);

		AnimalDeGranja animalEncontrado = null;

		try {

			animalEncontrado = this.repositorioDeAnimales.buscarPorId(animalParaBorrar.getId());
			Assertions.fail("No debería encontrar animal con id: " + animalParaBorrar.getId());

		} catch (NoResultException e) {

			assertThat(animalEncontrado).isNull();
		}
	}

	@Test(expected = ConstraintViolationException.class)
	public void noDeberianInsertarseAnimalesConMismoIdentificadorGPS() {

		AnimalDeGranja vaca = this.crearAnimal("VACUNO", "ABERDEEN ANGUS", "FEMENINO", 720.0, IDENTIFICADOR_GPS);
		AnimalDeGranja caballo = this.crearAnimal("EQUINO", "CABALLO ARABE", "FEMENINO", 900.0, IDENTIFICADOR_GPS);

		this.repositorioDeAnimales.guardar(vaca);
		this.repositorioDeAnimales.guardar(caballo);
	}

	private AnimalDeGranja crearAnimal(String nombreTipo, String nombreRaza, String nombreGenero, double peso, String identificadorGps) {

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
		animal.setIdentificadorGps(identificadorGps);

		return animal;
	}
}