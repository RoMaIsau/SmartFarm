package ar.edu.unlam.tallerweb1.repositorios;

import java.time.LocalDate;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

public interface RepositorioAnimalUbicacion {

	void guardar(AnimalUbicacion animalUbicacion);

	AnimalUbicacion obtenerAnimalUbicacion(Long id, LocalDate fecha);

}
