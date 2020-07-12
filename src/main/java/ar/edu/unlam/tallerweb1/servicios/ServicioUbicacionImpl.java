package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.excepciones.AnimalSinIdentificadorGpsException;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;
import ar.edu.unlam.tallerweb1.modelo.Corral;
import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;
import ar.edu.unlam.tallerweb1.modelo.UbicacionesCentrales;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAnimalUbicacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUbicacion;

@Service("servicioUbicacion")
@Transactional
public class ServicioUbicacionImpl implements ServicioUbicacion {

	private Logger logger = LoggerFactory.getLogger(ServicioAnimalUbicacionImpl.class);

	@Inject
	private ServicioDeAnimales servicioAnimales;
	private ServicioNotificacion servicioNotificacion;

	private RepositorioUbicacion repositorioUbicacion;
	private RepositorioAnimalUbicacion repositorioAnimalUbicacion;
	
	@Inject
	private ServicioUbicacionesCentrales servicioUbicacionesCentrales;

	private ServicioCorral servicioCorral;
	/*
	private UbicacionesCentrales ubicacionesCentrales = new UbicacionesCentrales(35.280943, 59.242249, 35.275880, 59.232271, 35.273971, 59.256475, 35.269674, 59.244542, 35.275999, 59.244134);
	*/
	/*
	private UbicacionesCentrales ubicacionesCentrales = servicioUbicacionesCentrales.obtenerUbicacionesCentrales();*/
	/*
	private UbicacionesCentrales ubicacionesCentrales = new UbicacionesCentrales(ubicacionesCentralesDeLaBA.getLatitudCaprinoCentral(), ubicacionesCentralesDeLaBA.getLongitudCaprinoCentral(),
																			ubicacionesCentralesDeLaBA.getLatitudEquinoCentral(), ubicacionesCentralesDeLaBA.getLongitudEquinoCentral(),
																			ubicacionesCentralesDeLaBA.getLatitudOvinoCentral(), ubicacionesCentralesDeLaBA.getLongitudOvinoCentral(),
																			ubicacionesCentralesDeLaBA.getLatitudPorcinoCentral(), ubicacionesCentralesDeLaBA.getLongitudPorcinoCentral(),
																			ubicacionesCentralesDeLaBA.getLatitudVacunoCentral(), ubicacionesCentralesDeLaBA.getLongitudVacunoCentral());*/
	
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
			
			if((repositorioAnimalUbicacion.obtenerAnimalUbicacion(animal.getId(), LocalDateTime.now())) == null) {
				AnimalUbicacion animalUbicacion = new AnimalUbicacion();
				
				animalUbicacion.setAnimal(animal);
				animalUbicacion.setUltimaUbicacion(ubicacion);
				animalUbicacion.setMetrosRecorridos(0);
				animalUbicacion.setFecha(LocalDateTime.now());
				
				repositorioAnimalUbicacion.guardar(animalUbicacion);
				animalesUbicaciones.add(animalUbicacion);
			}else {
				AnimalUbicacion animalUbicacionObtenido = repositorioAnimalUbicacion.obtenerAnimalUbicacion(animal.getId(), LocalDateTime.now());
				
				Ubicacion ultimaUbicacion = animalUbicacionObtenido.getUltimaUbicacion();

				Integer distancia = calcularDistancia(ubicacion.getLatitud(), ubicacion.getLongitud(),
						ultimaUbicacion.getLatitud(), ultimaUbicacion.getLongitud());

				Integer metrosRecorridos = animalUbicacionObtenido.getMetrosRecorridos() == null? 0 : animalUbicacionObtenido.getMetrosRecorridos();
				Integer metrosEnTotal = distancia + metrosRecorridos;

				animalUbicacionObtenido.setAnimal(animal);
				animalUbicacionObtenido.setUltimaUbicacion(ubicacion);
				animalUbicacionObtenido.setMetrosRecorridos(metrosEnTotal);
				animalUbicacionObtenido.setFecha(LocalDateTime.now());
				
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
		UbicacionesCentrales ubicacionesCentrales = servicioUbicacionesCentrales.obtenerUbicacionesCentrales();
		
		Double n = 0.0;
		int x = 0;
		x = (int) ( (Math.random() * (1000 - 1)) + 1);
		
		if(x > 997) {
			servicioNotificacion.crearNotificacionAnimalFueraDeLugar(a.getId());
		}
		
		switch (a.getTipo().getNombre()) {
		
		case "VACUNO":
			if(x <= 997) {
				n = (Double) ((Double) (Math.random() * ((ubicacionesCentrales.getLatitudVacunoCentral() + 0.0015) - (ubicacionesCentrales.getLatitudVacunoCentral() - 0.0015))) + (ubicacionesCentrales.getLatitudVacunoCentral() - 0.0015));
			} else {
				n = (Double) ((Double) (Math.random() * (35.271174 - 35.268174)) + 35.268174);
			}

			break;

		case "CAPRINO":
			if(x <= 997) {
				n = (Double) ((Double) (Math.random() * ((ubicacionesCentrales.getLatitudCaprinoCentral() + 0.0015) - (ubicacionesCentrales.getLatitudCaprinoCentral() - 0.0015))) + (ubicacionesCentrales.getLatitudCaprinoCentral() - 0.0015));
			} else {
				n = (Double) ((Double) (Math.random() * (35.277380 - 35.274380)) + 35.274380);
			}
			break;

		case "EQUINO":
			if(x <= 997) {
				n = (Double) ((Double) (Math.random() * ((ubicacionesCentrales.getLatitudEquinoCentral() + 0.0015) - (ubicacionesCentrales.getLatitudEquinoCentral() - 0.0015))) + (ubicacionesCentrales.getLatitudEquinoCentral() - 0.0015));
			} else {
				n = (Double) ((Double) (Math.random() * (35.275471 - 35.272471)) + 35.272471);
			}
			break;

		case "OVINO":
			if(x <= 997) {
				n = (Double) ((Double) (Math.random() * ((ubicacionesCentrales.getLatitudOvinoCentral() + 0.0015) - (ubicacionesCentrales.getLatitudOvinoCentral() - 0.0015))) + (ubicacionesCentrales.getLatitudOvinoCentral() - 0.0015));
			} else {
				n = (Double) ((Double) (Math.random() * (35.282443 - 35.279443)) + 35.279443);
			}
			break;

		case "PORCINO":
			if(x <= 997) {
				n = (Double) ((Double) (Math.random() * ((ubicacionesCentrales.getLatitudPorcinoCentral() + 0.0015) - (ubicacionesCentrales.getLatitudPorcinoCentral() - 0.0015))) + (ubicacionesCentrales.getLatitudPorcinoCentral() - 0.0015));
			} else {
				n = (Double) ((Double) (Math.random() * (35.277499 - 35.274499)) + 35.274499);
			}
			break;
		}

		return -n;
	}

	public Double crearLongitudAleatorea(AnimalDeGranja a) {
		UbicacionesCentrales ubicacionesCentrales = servicioUbicacionesCentrales.obtenerUbicacionesCentrales();
		
		Double n = 0.0;
		int x = 0;
		x = (int) ( (Math.random() * (1000 - 1)) + 1);

		if(x > 997) {
			servicioNotificacion.crearNotificacionAnimalFueraDeLugar(a.getId());
		}
		
		
		switch (a.getTipo().getNombre()) {

		case "VACUNO":
			if(x <= 997) {
				n = (Double) ((Double) (Math.random() * ((ubicacionesCentrales.getLongitudVacunoCentral() + 0.0015) - (ubicacionesCentrales.getLongitudVacunoCentral() - 0.0015))) + (ubicacionesCentrales.getLongitudVacunoCentral() - 0.0015));
			} else {
				n = (Double) ((Double) (Math.random() * (59.246042 - 59.243042)) + 59.243042);
			}
			break;

		case "CAPRINO":
			if(x <= 997) {
				n = (Double) ((Double) (Math.random() * ((ubicacionesCentrales.getLongitudCaprinoCentral() + 0.0015) - (ubicacionesCentrales.getLongitudCaprinoCentral() - 0.0015))) + (ubicacionesCentrales.getLongitudCaprinoCentral() - 0.0015));
			} else {
				n = (Double) ((Double) (Math.random() * (59.233771 - 59.230771)) + 59.230771);
			}
			break;

		case "EQUINO":
			if(x <= 997) {
				n = (Double) ((Double) (Math.random() * ((ubicacionesCentrales.getLongitudEquinoCentral() + 0.0015) - (ubicacionesCentrales.getLongitudEquinoCentral() - 0.0015))) + (ubicacionesCentrales.getLongitudEquinoCentral() - 0.0015));
			} else {
				n = (Double) ((Double) (Math.random() * (59.257975 - 59.254975)) + 59.254975);
			}
			break;

		case "OVINO":
			if(x <= 997) {
				n = (Double) ((Double) (Math.random() * ((ubicacionesCentrales.getLongitudOvinoCentral() + 0.0015) - (ubicacionesCentrales.getLongitudOvinoCentral() - 0.0015))) + (ubicacionesCentrales.getLongitudOvinoCentral() - 0.0015));
			} else {
				n = (Double) ((Double) (Math.random() * (59.243749 - 59.240749)) + 59.240749);
			}
			break;

		case "PORCINO":
			if(x <= 997) {
				n = (Double) ((Double) (Math.random() * ((ubicacionesCentrales.getLongitudPorcinoCentral() + 0.0015) - (ubicacionesCentrales.getLongitudPorcinoCentral() - 0.0015))) + (ubicacionesCentrales.getLongitudPorcinoCentral() - 0.0015));
			} else {
				n = (Double) ((Double) (Math.random() * (59.245634 - 59.242634)) + 59.242634);
			}
			break;
		}

		return -n;
	}

	@Override
	public void registrar(Posicion posicion) {

		try {

			AnimalDeGranja animal = this.servicioAnimales.obtenerPorIdentificadorGps(posicion.getIdentificador());
			Ubicacion ubicacion = new Ubicacion(posicion.getLatitud(), posicion.getLongitud());

			this.repositorioUbicacion.guardarUbicacion(ubicacion);

			AnimalUbicacion animalUbicacion = new AnimalUbicacion();
			animalUbicacion.setAnimal(animal);
			animalUbicacion.setFecha(LocalDateTime.now());
			animalUbicacion.setUltimaUbicacion(ubicacion);
			this.repositorioAnimalUbicacion.guardar(animalUbicacion);

			comprobarAnimalDentroDeCorral(animal, ubicacion);

		} catch(AnimalSinIdentificadorGpsException e) {
			logger.error("No se puede registrar la posición. Causa: {}", e.getMessage());
		}
	}

	private void comprobarAnimalDentroDeCorral(AnimalDeGranja animal, Ubicacion ubicacion) {
		Corral corral = servicioCorral.obtenerCorralAsignado(animal);
		if (corral != null) {
			boolean estaDentro = corral.contiene(ubicacion.getLatitud(), ubicacion.getLongitud());
			if(!estaDentro) {
				logger.info("El animal {} se escapo del corral {}!!!", animal, corral);
				this.servicioNotificacion.crearNotificacionAnimalFueraDeLugar(animal.getId());
			}
		}
	}

	@Override
	public List<AnimalUbicacion> obtenerUbicacionesRecientes() {
		return this.repositorioUbicacion.obtenerUbicacionesRecientes();
	}

	@Autowired
	public void setServicioCorral(ServicioCorral servicioCorral) {
		this.servicioCorral = servicioCorral;
	}

	@Autowired
	public void setServicioNotificacion(ServicioNotificacion servicioNotificacion) {
		this.servicioNotificacion = servicioNotificacion;
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