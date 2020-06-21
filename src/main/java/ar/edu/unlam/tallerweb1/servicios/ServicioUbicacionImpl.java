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
	private ServicioDeAnimales servicioAnimales;
	private ServicioNotificacion servicioNotificacion;

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

	public float setearLatitudAleatorea(AnimalDeGranja a) {
		float n = 0;
		int x = 0;
		x = (int) ( (Math.random() * (1000 - 1)) + 1);
		
		if(x > 997) {
			servicioNotificacion.crearNotificacionAnimalFueraDeLugar(a.getId());
		}
		
		
		switch (a.getTipo().getNombre()) {

		case "VACUNO":
			if(x <= 997) {
				n = (float) ((float) (Math.random() * (35.277499 - 35.274499)) + 35.274499);
			} else {
				n = (float) ((float) (Math.random() * (35.271174 - 35.268174)) + 35.268174);
			}

			break;

		case "CAPRINO":
			if(x <= 997) {
				n = (float) ((float) (Math.random() * (35.282443 - 35.279443)) + 35.279443);
			} else {
				n = (float) ((float) (Math.random() * (35.277380 - 35.274380)) + 35.274380);
			}
			break;

		case "EQUINO":
			if(x <= 997) {
				n = (float) ((float) (Math.random() * (35.277380 - 35.274380)) + 35.274380);
			} else {
				n = (float) ((float) (Math.random() * (35.275471 - 35.272471)) + 35.272471);
			}
			break;

		case "OVINO":
			if(x <= 997) {
				n = (float) ((float) (Math.random() * (35.275471 - 35.272471)) + 35.272471);
			} else {
				n = (float) ((float) (Math.random() * (35.282443 - 35.279443)) + 35.279443);
			}
			break;

		case "PORCINO":
			if(x <= 997) {
				n = (float) ((float) (Math.random() * (35.271174 - 35.268174)) + 35.268174);
			} else {
				n = (float) ((float) (Math.random() * (35.277499 - 35.274499)) + 35.274499);
			}
			break;
		}

		return -n;
	}

	public float setearLongitudAleatorea(AnimalDeGranja a) {
		float n = 0;
		int x = 0;
		x = (int) ( (Math.random() * (1000 - 1)) + 1);

		if(x > 997) {
			servicioNotificacion.crearNotificacionAnimalFueraDeLugar(a.getId());
		}
		
		
		switch (a.getTipo().getNombre()) {

		case "VACUNO":
			if(x <= 997) {
				n = (float) ((float) (Math.random() * (59.245634 - 59.242634)) + 59.242634);
			} else {
				n = (float) ((float) (Math.random() * (59.246042 - 59.243042)) + 59.243042);
			}
			break;

		case "CAPRINO":
			if(x <= 997) {
				n = (float) ((float) (Math.random() * (59.243749 - 59.240749)) + 59.240749);
			} else {
				n = (float) ((float) (Math.random() * (59.233771 - 59.230771)) + 59.230771);
			}
			break;

		case "EQUINO":
			if(x <= 997) {
				n = (float) ((float) (Math.random() * (59.233771 - 59.230771)) + 59.230771);
			} else {
				n = (float) ((float) (Math.random() * (59.257975 - 59.254975)) + 59.254975);
			}
			break;

		case "OVINO":
			if(x <= 997) {
				n = (float) ((float) (Math.random() * (59.257975 - 59.254975)) + 59.254975);
			} else {
				n = (float) ((float) (Math.random() * (59.243749 - 59.240749)) + 59.240749);
			}
			break;

		case "PORCINO":
			if(x <= 997) {
				n = (float) ((float) (Math.random() * (59.246042 - 59.243042)) + 59.243042);
			} else {
				n = (float) ((float) (Math.random() * (59.245634 - 59.242634)) + 59.242634);
			}
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