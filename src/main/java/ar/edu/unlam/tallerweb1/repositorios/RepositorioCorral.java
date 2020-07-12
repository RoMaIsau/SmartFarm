package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Corral;

public interface RepositorioCorral {

	List<Corral> obtenerTodosLosCorrales();

	void crear(Corral corralNuevo);

	void actualizar(Corral corral);

	void eliminar(Corral corral);

	String obtenerNombre(Long idCorral);

	List<AnimalDeGranja> obtenerAnimalesPorCorral(Long idCorral);

	List<AnimalDeGranja> obtenerAnimalesSinCorral();

	void asignarAnimales(Long idCorral, Long[] idAnimales);

	void quitarAnimales(Corral corral);

	Corral obtenerCorralPorAnimal(AnimalDeGranja animal);
}
