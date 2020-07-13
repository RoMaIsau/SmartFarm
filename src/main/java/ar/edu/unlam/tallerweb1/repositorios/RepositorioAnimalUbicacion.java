package ar.edu.unlam.tallerweb1.repositorios;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;

public interface RepositorioAnimalUbicacion {

	void guardar(AnimalUbicacion animalUbicacion);

	AnimalUbicacion obtenerAnimalUbicacion(Long id, LocalDateTime fecha);
	
	List<AnimalUbicacion> obtenerPorIdAnimal(Long idAnimal);

	AnimalUbicacion obtenerUbicacionAnimal(Long idAnimal);

}
