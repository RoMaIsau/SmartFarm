package ar.edu.unlam.tallerweb1.servicios;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.excepciones.AnimalSinIdentificadorGpsException;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Enfermedad;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeAnimales;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeGeneros;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeRazas;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeTipos;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ServicioDeAnimalesImpl implements ServicioDeAnimales {

	private RepositorioDeTipos repositorioDeTipos;
	private RepositorioDeRazas repositorioDeRazas;
	private RepositorioDeGeneros repositorioDeGeneros;
	private RepositorioDeAnimales repositorioDeAnimales;
	private ServicioPlanAlimentario servicioPlanAlimentario;
	private ServicioAnimalUbicacion servicioAnimalUbicacion;
	
	@Autowired
	public ServicioDeAnimalesImpl(RepositorioDeTipos repositorioDeTipos, RepositorioDeRazas repositorioDeRazas, 
			RepositorioDeGeneros repositorioDeGeneros, RepositorioDeAnimales repositorioDeAnimales,
			ServicioPlanAlimentario servicioPlanAlimentario, ServicioAnimalUbicacion servicioAnimalUbicacion) {
		this.repositorioDeTipos = repositorioDeTipos;
		this.repositorioDeRazas = repositorioDeRazas;
		this.repositorioDeGeneros = repositorioDeGeneros;
		this.repositorioDeAnimales = repositorioDeAnimales;
		this.servicioPlanAlimentario = servicioPlanAlimentario;
		this.servicioAnimalUbicacion = servicioAnimalUbicacion;
	}

	@Override
	public List<TipoAnimal> obtenerTiposDeAnimales() {
		return this.repositorioDeTipos.listarDisponibles();
	}

	@Override
	public List<Raza> obtenerRazasPorTipoAnimal(TipoAnimal tipoAnimal) {
		return this.repositorioDeRazas.listarPorTipoAnimal(tipoAnimal);
	}

	@Override
	public List<Genero> obtenerGeneros() {
		return this.repositorioDeGeneros.listar();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void registrar(AnimalDeGranja animal) {
		HistoriaClinica hc = new HistoriaClinica();
		hc.setAnimal(animal);
		
		SignosVitales sv = new SignosVitales();
		Date fecha = new Date();
		sv.setFecha(fecha);
		sv.setFrecuenciaCardiaca(80.0);
		sv.setFrecuenciaRespiratoria(25.0);
		sv.setPulso(80.0);
		sv.setTemperatura(37.0);
		sv.setHistoria(hc);
		
		this.repositorioDeAnimales.guardar(animal, sv, hc);
		this.servicioPlanAlimentario.crearPlan(animal);
	}

	@Override
	public List<AnimalDeGranja> obtenerTodos() {

		return this.repositorioDeAnimales.listar();
	}

	@Override
	public AnimalDeGranja obtenerPorId(Long idAnimal) {
		return this.repositorioDeAnimales.buscarPorId(idAnimal);
		
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void actualizarAnimal(AnimalDeGranja animal) {
		this.repositorioDeAnimales.actualizar(animal);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void eliminarPorId(Long idAnimal) {
		AnimalDeGranja animal = obtenerPorId(idAnimal);
		servicioPlanAlimentario.eliminarPlan(animal);
		servicioAnimalUbicacion.eliminarUbicaciones(animal);
		repositorioDeAnimales.eliminar(animal);
	}

	@Override
	public AnimalDeGranja obtenerPorIdentificadorGps(String identificador) throws AnimalSinIdentificadorGpsException {

		AnimalDeGranja animal = null;
		try {

			animal = this.repositorioDeAnimales.obtenerPorIdentificadorGps(identificador);

		}catch(NoResultException e) {
			throw new AnimalSinIdentificadorGpsException(identificador);
		}

		return animal;
	}

	@Override
	public Enfermedad buscarUltimaEnfermedadDelAnimal(Long id) {
		return this.repositorioDeAnimales.buscarUltimaEnfermedadDelAnimal(id);
	}
}
