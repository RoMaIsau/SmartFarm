package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUbicacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUbicacionImpl;

@Transactional
public class RepositorioUbicacionTest extends SpringTest {

	private static final String GPS001 = "GPS-001";
	private Raza vacaCanaria;
	private TipoAnimal vacuno;
	private Genero hembra;
	private AnimalDeGranja vaca;

	private RepositorioUbicacion repositorioUbicacion;

	@Before
	public void inicializar() {
		this.repositorioUbicacion = new RepositorioUbicacionImpl(this.sessionFactory);
		crearGeneros();
		crearTiposDeAnimal();
		crearVaca();
	}

	private void crearGeneros() {
		this.hembra = crearGenero("HEMBRA");
	}

	private Genero crearGenero(String nombre) {
		Genero genero = new Genero();
		genero.setNombre(nombre);
		this.sessionFactory.getCurrentSession().save(genero);
		return genero;
	}

	private void crearTiposDeAnimal() {
		this.vacuno = crearTiposAnimal("VACUNO");
		this.vacaCanaria = crearRaza(vacuno, "VACA CANARIA");
	}

	private TipoAnimal crearTiposAnimal(String nombre) {
		TipoAnimal tipo = new TipoAnimal();
		tipo.setNombre(nombre);
		this.sessionFactory.getCurrentSession().save(tipo);
		return tipo;
	}
	
	private Raza crearRaza(TipoAnimal tipo, String nombre) {
		Raza raza = new Raza();
		raza.setNombre(nombre);
		raza.setTipo(tipo);
		this.sessionFactory.getCurrentSession().save(raza);
		return raza;
	}

	private void crearVaca() {
		this.vaca = new AnimalDeGranja();
		vaca.setGenero(hembra);
		vaca.setTipo(vacuno);
		vaca.setRaza(vacaCanaria);
		vaca.setIdentificadorGps(GPS001);
		this.sessionFactory.getCurrentSession().save(vaca);
	}

	@Test
	public void deberiaObtenerLaUbicacionMasRecienteDelDia() {
		
		LocalDateTime haceUnaHora = LocalDateTime.now().minusHours(1);
		LocalDateTime ahora = LocalDateTime.now();
		
		Ubicacion ubicacionVieja = crearUbicacion(-38.416097, -63.616672, haceUnaHora);
		Ubicacion ubicacionNueva = crearUbicacion(-38.416097, -63.700000, ahora);
		
		List<AnimalUbicacion> ubicaciones = this.repositorioUbicacion.obtenerUbicacionesRecientes();

		assertThat(ubicaciones).hasSize(1);
		
	}

	private Ubicacion crearUbicacion(double latitud, double longitud, LocalDateTime fechaHora) {
		
		Ubicacion ubicacion = new Ubicacion();
		ubicacion.setLatitud(latitud);
		ubicacion.setLongitud(longitud);
		this.sessionFactory.getCurrentSession().save(ubicacion);

		AnimalUbicacion animalUbicacion = new AnimalUbicacion();
		animalUbicacion.setAnimal(this.vaca);
		animalUbicacion.setFecha(fechaHora);
		animalUbicacion.setUltimaUbicacion(ubicacion);
		this.sessionFactory.getCurrentSession().save(animalUbicacion);

		return ubicacion;
	}
	
}
