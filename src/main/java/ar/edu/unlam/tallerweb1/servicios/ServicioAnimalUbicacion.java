package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;

public interface ServicioAnimalUbicacion {

	List<AnimalUbicacion> obtenerPorIdAnimal(Long idAnimal);

	AnimalUbicacion obtenerUbicacionAnimal(Long idAnimal);

	void eliminarUbicaciones(AnimalDeGranja animal);
}
