package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

@Service("servicioUbicacion")
@Transactional
public class ServicioUbicacionImpl implements ServicioUbicacion {

	@Inject
	ServicioDeAnimales servicioAnimales;

	@Override
	public List<Ubicacion> obtenerUbicaciones() {

		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		List<AnimalDeGranja> animales = servicioAnimales.obtenerTodos();

		for (AnimalDeGranja animal : animales) {
			Ubicacion ubicacion = new Ubicacion(0, 0);
			ubicacion.setLatitud(setearLatitudAleatorea(animal));
			ubicacion.setLongitud(setearLongitudAleatorea(animal));

			ubicacion.setAnimal(animal);

			ubicaciones.add(ubicacion);
		}

		return ubicaciones;
	}

	public float setearLatitudAleatorea(AnimalDeGranja a) {
		float n = 0;
		float x = 0;
		x = (float) ((float) (Math.random() * (100.0 - 1.0)) + 1.0);
		
		switch (a.getTipo().getNombre()) {

		case "VACUNO":
			if(x < 99.5) {
				n = (float) ((float) (Math.random() * (35.277499 - 35.274499)) + 35.274499);
			} else {
				n = (float) ((float) (Math.random() * (35.271174 - 35.268174)) + 35.268174);
			}
			break;
			
		case "CAPRINO":
			if(x < 99.5) {
				n = (float) ((float) (Math.random() * (35.282443 - 35.279443)) + 35.279443);
			} else {
				n = (float) ((float) (Math.random() * (35.277380 - 35.274380)) + 35.274380);
			}
			break;
			
		case "EQUINO":
			if(x < 99.5) {
				n = (float) ((float) (Math.random() * (35.277380 - 35.274380)) + 35.274380);
			} else {
				n = (float) ((float) (Math.random() * (35.275471 - 35.272471)) + 35.272471);
			}
			break;
			
		case "OVINO":
			if(x < 99.5) {
				n = (float) ((float) (Math.random() * (35.275471 - 35.272471)) + 35.272471);
			} else {
				n = (float) ((float) (Math.random() * (35.282443 - 35.279443)) + 35.279443);
			}
			break;
			
		case "PORCINO":
			if(x < 99.5) {
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
		float x = 0;
		x = (float) ((float) (Math.random() * (100.0 - 1.0)) + 1.0);
		
		switch (a.getTipo().getNombre()) {

		case "VACUNO":
			if(x < 99.5) {
				n = (float) ((float) (Math.random() * (59.245634 - 59.242634)) + 59.242634);
			} else {
				n = (float) ((float) (Math.random() * (59.246042 - 59.243042)) + 59.243042);
			}
			break;
			
		case "CAPRINO":
			if(x < 99.5) {
				n = (float) ((float) (Math.random() * (59.243749 - 59.240749)) + 59.240749);
			} else {
				n = (float) ((float) (Math.random() * (59.233771 - 59.230771)) + 59.230771);
			}
			break;
			
		case "EQUINO":
			if(x < 99.5) {
				n = (float) ((float) (Math.random() * (59.233771 - 59.230771)) + 59.230771);
			} else {
				n = (float) ((float) (Math.random() * (59.257975 - 59.254975)) + 59.254975);
			}
			break;
			
		case "OVINO":
			if(x < 99.5) {
				n = (float) ((float) (Math.random() * (59.257975 - 59.254975)) + 59.254975);
			} else {
				n = (float) ((float) (Math.random() * (59.243749 - 59.240749)) + 59.240749);
			}
			break;
			
		case "PORCINO":
			if(x < 99.5) {
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
VACUNO: Está mal cuando está en PORCINO
PORCINO: Está mal cuando está en VACUNO
CAPRINO: Está mal cuando está en OVINO
OVINO: Está mal cuando está en EQUINO
EQUINO: Está mal cuando está en CAPRINO

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