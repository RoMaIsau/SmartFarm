package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDateTime;
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
			Ubicacion ubicacion = new Ubicacion(0, 0);
			AnimalUbicacion animalUbicacion = new AnimalUbicacion();
			
			ubicacion.setLatitud(setearLatitudAleatorea(animal));
			ubicacion.setLongitud(setearLongitudAleatorea(animal));
			
			animalUbicacion.setAnimal(animal);
			animalUbicacion.setUbicacion(ubicacion);
			animalUbicacion.setFecha(LocalDateTime.now());
			
			repositorioUbicacion.guardarUbicacion(ubicacion);
			repositorioAnimalUbicacion.guardar(animalUbicacion);
			
			animalesUbicaciones.add(animalUbicacion);
		}

		return animalesUbicaciones;
	}

	public float setearLatitudAleatorea(AnimalDeGranja a) {
		float n = 0;
		
		switch (a.getTipo().getNombre()) {

		case "VACUNO":
			n = (float) ((float) (Math.random() * (35.277499 - 35.274499)) + 35.274499);
			break;
			
		case "CAPRINO":
			n = (float) ((float) (Math.random() * (35.282443 - 35.279443)) + 35.279443);
			break;
			
		case "EQUINO":
			n = (float) ((float) (Math.random() * (35.277380 - 35.274380)) + 35.274380);
			break;
			
		case "OVINO":
			n = (float) ((float) (Math.random() * (35.275471 - 35.272471)) + 35.272471);
			break;
			
		case "PORCINO":
			n = (float) ((float) (Math.random() * (35.271174 - 35.268174)) + 35.268174);
			break;
		}
		
		return -n;
	}

	public float setearLongitudAleatorea(AnimalDeGranja a) {
		float n = 0;
		
		switch (a.getTipo().getNombre()) {

		case "VACUNO":
			n = (float) ((float) (Math.random() * (59.245634 - 59.242634)) + 59.242634);
			break;
			
		case "CAPRINO":
			n = (float) ((float) (Math.random() * (59.243749 - 59.240749)) + 59.240749);
			break;
			
		case "EQUINO":
			n = (float) ((float) (Math.random() * (59.233771 - 59.230771)) + 59.230771);
			break;
			
		case "OVINO":
			n = (float) ((float) (Math.random() * (59.257975 - 59.254975)) + 59.254975);
			break;
			
		case "PORCINO":
			n = (float) ((float) (Math.random() * (59.246042 - 59.243042)) + 59.243042);
			break;
		}

		return -n;
	}

}

/*
VACUNO: Est� mal cuando est� en PORCINO
PORCINO: Est� mal cuando est� en VACUNO
CAPRINO: Est� mal cuando est� en OVINO
OVINO: Est� mal cuando est� en EQUINO
EQUINO: Est� mal cuando est� en CAPRINO

VACUNO
(35.271174 - 35.268174)) + 35.268174)
(59.246042 - 59.243042)) + 59.243042)

CAPRINO
(35.277380 - 35.274380)) + 35.274380)
(59.233771 - 59.230771)) + 59.230771)

EQUINO
(35.275471 - 35.272471)) + 35.272471)
(59.257975 - 59.254975)) + 59.254975)

OVINO
(35.282443 - 35.279443)) + 35.279443)
(59.243749 - 59.240749)) + 59.240749)

PORCINO
(35.277499 - 35.274499)) + 35.274499)
(59.245634 - 59.242634)) + 59.242634)
*/