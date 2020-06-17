package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAnimalUbicacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUbicacion;

@Service("servicioUbicacion")
@Transactional
public class ServicioUbicacionImpl implements ServicioUbicacion {

	@Inject
	ServicioDeAnimales servicioAnimales;

	private RepositorioUbicacion repositorioUbicacion;
	private RepositorioAnimalUbicacion repositorioAnimalUbicacion;

	@Autowired
	public ServicioUbicacionImpl(RepositorioUbicacion repositorioUbicacion,
			RepositorioAnimalUbicacion repositorioAnimalUbicacion) {
		this.repositorioUbicacion = repositorioUbicacion;
		this.repositorioAnimalUbicacion = repositorioAnimalUbicacion;
	}

	@Override
	public List<AnimalUbicacion> obtenerUbicaciones() {

		List<AnimalUbicacion> animalesUbicaciones = new ArrayList<AnimalUbicacion>();
		List<AnimalDeGranja> animales = servicioAnimales.obtenerTodos();

		for (AnimalDeGranja animal : animales) {
			Ubicacion ubicacion = new Ubicacion(0.0, 0.0);
			
			ubicacion.setLatitud(crearLatitudAleatorea(animal));
			ubicacion.setLongitud(crearLongitudAleatorea(animal));
			repositorioUbicacion.guardarUbicacion(ubicacion);
			
			if((repositorioAnimalUbicacion.obtenerAnimalUbicacion(animal.getId(), LocalDate.now())) == null) {
				AnimalUbicacion animalUbicacion = new AnimalUbicacion();
				
				animalUbicacion.setAnimal(animal);
				animalUbicacion.setUltimaUbicacion(ubicacion);
				animalUbicacion.setMetrosRecorridos(0);
				animalUbicacion.setFecha(LocalDate.now());
				
				repositorioAnimalUbicacion.guardar(animalUbicacion);
				animalesUbicaciones.add(animalUbicacion);
			}else {
				AnimalUbicacion animalUbicacionObtenido = repositorioAnimalUbicacion.obtenerAnimalUbicacion(animal.getId(), LocalDate.now());
				
				Ubicacion ultimaUbicacion = animalUbicacionObtenido.getUltimaUbicacion();

				Integer distancia = calcularDistancia(ubicacion.getLatitud(), ubicacion.getLongitud(),
						ultimaUbicacion.getLatitud(), ultimaUbicacion.getLongitud());
			
				Integer metrosEnTotal = distancia + animalUbicacionObtenido.getMetrosRecorridos();

				animalUbicacionObtenido.setAnimal(animal);
				animalUbicacionObtenido.setUltimaUbicacion(ubicacion);
				animalUbicacionObtenido.setMetrosRecorridos(metrosEnTotal);
				animalUbicacionObtenido.setFecha(LocalDate.now());
				
				repositorioAnimalUbicacion.guardar(animalUbicacionObtenido);
				animalesUbicaciones.add(animalUbicacionObtenido);
			}
		}

		return animalesUbicaciones;
	}

	private Integer calcularDistancia(Double ubicacionLat, Double ubicacionLon, Double ultimaUbicacionLat,
			Double ultimaUbicacionLon) {

		int radioTierra = 6371;

		ubicacionLat = Math.toRadians(ubicacionLat);
		ubicacionLon = Math.toRadians(ubicacionLon);
		ultimaUbicacionLat = Math.toRadians(ultimaUbicacionLat);
		ultimaUbicacionLon = Math.toRadians(ultimaUbicacionLon);

		double dlon = (ultimaUbicacionLon - ubicacionLon);
		double dlat = (ultimaUbicacionLat - ubicacionLat);

		double sinlat = Math.sin(dlat / 2);
		double sinlon = Math.sin(dlon / 2);

		double a = (sinlat * sinlat) + Math.cos(ubicacionLat) * Math.cos(ultimaUbicacionLat) * (sinlon * sinlon);
		double c = 2 * Math.asin(Math.min(1.0, Math.sqrt(a)));

		double distanciaEnMetros = radioTierra * c * 1000;

		return (int) distanciaEnMetros;
	}

	public Double crearLatitudAleatorea(AnimalDeGranja a) {
		Double n = 0.0;

		switch (a.getTipo().getNombre()) {

		case "VACUNO":
			n = (Double) ((Double) (Math.random() * (35.277499 - 35.274499)) + 35.274499);
			break;

		case "CAPRINO":
			n = (Double) ((Double) (Math.random() * (35.282443 - 35.279443)) + 35.279443);
			break;

		case "EQUINO":
			n = (Double) ((Double) (Math.random() * (35.277380 - 35.274380)) + 35.274380);
			break;

		case "OVINO":
			n = (Double) ((Double) (Math.random() * (35.275471 - 35.272471)) + 35.272471);
			break;

		case "PORCINO":
			n = (Double) ((Double) (Math.random() * (35.271174 - 35.268174)) + 35.268174);
			break;
		}

		return -n;
	}

	public Double crearLongitudAleatorea(AnimalDeGranja a) {
		Double n = 0.0;

		switch (a.getTipo().getNombre()) {

		case "VACUNO":
			n = (Double) ((Double) (Math.random() * (59.245634 - 59.242634)) + 59.242634);
			break;

		case "CAPRINO":
			n = (Double) ((Double) (Math.random() * (59.243749 - 59.240749)) + 59.240749);
			break;

		case "EQUINO":
			n = (Double) ((Double) (Math.random() * (59.233771 - 59.230771)) + 59.230771);
			break;

		case "OVINO":
			n = (Double) ((Double) (Math.random() * (59.257975 - 59.254975)) + 59.254975);
			break;

		case "PORCINO":
			n = (Double) ((Double) (Math.random() * (59.246042 - 59.243042)) + 59.243042);
			break;
		}

		return -n;
	}

}

/*
 * VACUNO: Est� mal cuando est� en PORCINO PORCINO: Est� mal cuando est� en
 * VACUNO CAPRINO: Est� mal cuando est� en OVINO OVINO: Est� mal cuando est� en
 * EQUINO EQUINO: Est� mal cuando est� en CAPRINO
 * 
 * VACUNO (35.271174 - 35.268174)) + 35.268174) (59.246042 - 59.243042)) +
 * 59.243042)
 * 
 * CAPRINO (35.277380 - 35.274380)) + 35.274380) (59.233771 - 59.230771)) +
 * 59.230771)
 * 
 * EQUINO (35.275471 - 35.272471)) + 35.272471) (59.257975 - 59.254975)) +
 * 59.254975)
 * 
 * OVINO (35.282443 - 35.279443)) + 35.279443) (59.243749 - 59.240749)) +
 * 59.240749)
 * 
 * PORCINO (35.277499 - 35.274499)) + 35.274499) (59.245634 - 59.242634)) +
 * 59.242634)
 */