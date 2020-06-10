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
		int x = 0;

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
