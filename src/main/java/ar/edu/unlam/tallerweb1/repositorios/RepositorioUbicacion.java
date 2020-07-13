package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

public interface RepositorioUbicacion {

	void guardarUbicacion(Ubicacion ubicacion);

	List<AnimalUbicacion> obtenerUbicacionesRecientes();

}
