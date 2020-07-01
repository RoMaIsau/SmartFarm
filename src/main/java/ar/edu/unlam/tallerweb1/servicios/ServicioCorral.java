package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Corral;

public interface ServicioCorral {
	List<Corral> obtenerTodosLosCorrales();

	void guardar(Corral corral);

	void eliminar(Corral corral);

	String obtenerNombre(Long idCorral);

	List<AnimalDeGranja> obtenerAnimalesPorCorral(Long idCorral);

	List<AnimalDeGranja> obtenerAnimalesSinCorral();

	void quitarAnimales(Corral corral);

	void asignarAnimales(Long idCorral, Long[] idAnimales);
}
