package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.*;

import java.sql.Date;
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
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeAnimales;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeAnimalesImpl;

@Transactional
public class RepositorioDeAnimalesTest extends SpringTest {
	
	private final String IDENTIFICADOR_GPS_VACA = "GPS0001";
	private final String IDENTIFICADOR_GPS_CABALLO = "GPS0002";
	private RepositorioDeAnimales repositorioDeAnimales;
	SignosVitales signosVitales = new SignosVitales();
	HistoriaClinica historiaClinica = new HistoriaClinica();
	Date fecha = new Date(1);;
	
	@Before
	public void inicializarRepositorio() {
		this.repositorioDeAnimales = new RepositorioDeAnimalesImpl(this.sessionFactory);
		
		this.signosVitales.setId(1L);
		this.signosVitales.setFecha(this.fecha);
		this.signosVitales.setTemperatura(37.0);
		this.signosVitales.setFrecuenciaRespiratoria(25.0);
		this.signosVitales.setFrecuenciaCardiaca(80.0);
		this.signosVitales.setPulso(80.0);
	}
	
	@Test
	public void deberiaPersitirUnAnimalDeTipoVacunoDeRazaAberdeenAngus() {
		AnimalDeGranja vaca = this.crearAnimal("VACUNO", "ABERDEEN ANGUS", "FEMENINO", 720.0, IDENTIFICADOR_GPS_VACA);
		historiaClinica.setAnimal(vaca);
		signosVitales.setHistoria(historiaClinica);
		
		this.repositorioDeAnimales.guardar(vaca, signosVitales, historiaClinica);
		
		assertThat(vaca.getId()).isNotNull();
		assertThat(vaca.getTipo().getId()).isNotNull();
		assertThat(vaca.getRaza().getId()).isNotNull();
	}
	
	@Test
	public void deberiaListarTodosLosAnimalesAlmacenados() {
		AnimalDeGranja vaca = this.crearAnimal("VACUNO", "ABERDEEN ANGUS", "FEMENINO", 720.0, IDENTIFICADOR_GPS_VACA);
		AnimalDeGranja caballo = this.crearAnimal("EQUINO", "CABALLO ARABE", "FEMENINO", 900.0, IDENTIFICADOR_GPS_CABALLO);
		
		historiaClinica.setAnimal(vaca);
		signosVitales.setHistoria(historiaClinica);
		this.repositorioDeAnimales.guardar(vaca, signosVitales, historiaClinica);

		historiaClinica.setAnimal(caballo);
		signosVitales.setHistoria(historiaClinica);
		this.repositorioDeAnimales.guardar(caballo, signosVitales, historiaClinica);

		List<AnimalDeGranja> animales = this.repositorioDeAnimales.listar();

		assertThat(animales).hasSize(2);
	}
	
	@Test
	public void deberiaObtenerUnAnimalPorId() {
		AnimalDeGranja vaca = this.crearAnimal("VACUNO", "ABERDEEN ANGUS", "FEMENINO", 720.0, IDENTIFICADOR_GPS_VACA);
		AnimalDeGranja caballo = this.crearAnimal("EQUINO", "CABALLO ARABE", "FEMENINO", 900.0, IDENTIFICADOR_GPS_CABALLO);
		
		historiaClinica.setAnimal(vaca);
		signosVitales.setHistoria(historiaClinica);
		this.repositorioDeAnimales.guardar(vaca, signosVitales, historiaClinica);
		
		historiaClinica.setAnimal(caballo);
		signosVitales.setHistoria(historiaClinica);
		this.repositorioDeAnimales.guardar(caballo, signosVitales, historiaClinica);

		AnimalDeGranja animalBuscado = this.repositorioDeAnimales.buscarPorId(vaca.getId());

		assertThat(animalBuscado).isEqualTo(vaca);
	}
	
	@Test
	public void deberiaActualizarElPesoDelAnimal() {
		AnimalDeGranja vaca = this.crearAnimal("VACUNO", "ABERDEEN ANGUS", "FEMENINO", 720.0, IDENTIFICADOR_GPS_VACA);
		
		historiaClinica.setAnimal(vaca);
		signosVitales.setHistoria(historiaClinica);
		this.repositorioDeAnimales.guardar(vaca, signosVitales, historiaClinica);
		
		vaca.setPeso(800.0);
		
		this.repositorioDeAnimales.actualizar(vaca);
		
		AnimalDeGranja animalBuscado = this.repositorioDeAnimales.buscarPorId(vaca.getId());
		assertThat(animalBuscado.getPeso()).isEqualTo(800.0);
	}
	
	@Test
	public void deberiaEliminarAnimal() {
		AnimalDeGranja animalParaBorrar = this.crearAnimal("VACUNO", "ABERDEEN ANGUS", "FEMENINO", 720.0, IDENTIFICADOR_GPS_VACA);
		
		historiaClinica.setAnimal(animalParaBorrar);
		signosVitales.setHistoria(historiaClinica);
		this.repositorioDeAnimales.guardar(animalParaBorrar, signosVitales, historiaClinica);
		
		this.repositorioDeAnimales.eliminar(animalParaBorrar, signosVitales, historiaClinica);
		
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
		AnimalDeGranja vaca = this.crearAnimal("VACUNO", "ABERDEEN ANGUS", "FEMENINO", 720.0, IDENTIFICADOR_GPS_VACA);
		AnimalDeGranja caballo = this.crearAnimal("EQUINO", "CABALLO ARABE", "FEMENINO", 900.0, IDENTIFICADOR_GPS_VACA);
		
		historiaClinica.setAnimal(vaca);
		signosVitales.setHistoria(historiaClinica);
		this.repositorioDeAnimales.guardar(vaca, signosVitales, historiaClinica);
		
		historiaClinica.setAnimal(caballo);
		signosVitales.setHistoria(historiaClinica);
		this.repositorioDeAnimales.guardar(caballo, signosVitales, historiaClinica);
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