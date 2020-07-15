package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;

public interface RepositorioAnimalUbicacion {

	void guardar(AnimalUbicacion animalUbicacion);

	List<AnimalUbicacion> obtenerPorIdAnimal(Long idAnimal);

	AnimalUbicacion obtenerUbicacionAnimal(Long idAnimal);

	void eliminarUbicaciones(AnimalDeGranja animal);

}
