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
	
	@Inject ServicioDeAnimales servicioAnimales;
	
	
	@Override
	public List<Ubicacion> obtenerUbicaciones() {
		
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		List<AnimalDeGranja> animales = servicioAnimales.obtenerTodos();
		
		for(AnimalDeGranja animal : animales){
			Ubicacion ubicacion = new Ubicacion(0, 0);
			ubicacion.setLatitud(setearLatitudAleatorea());
			ubicacion.setLongitud(setearLongitudAleatorea());
			
			ubicacion.setAnimal(animal);
			
			ubicaciones.add(ubicacion);
		}
		
		return ubicaciones;
	}
	
	public float setearLatitudAleatorea() {
		float n = (float) ((float) (Math.random() * (35.27552 - 35.28118)) + 35.28118);
		return -n;
	}
	
	public float setearLongitudAleatorea() {
		float n = (float) ((float) (Math.random() * (59.24598 - 59.22949)) + 59.22949);
		return -n;
	}
	
}
