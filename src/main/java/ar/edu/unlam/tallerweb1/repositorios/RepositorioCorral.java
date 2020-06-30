package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Corral;

public interface RepositorioCorral {

	List<Corral> obtenerTodosLosCorrales();

	void crear(Corral corralNuevo);

	void actualizar(Corral corral);

	void eliminar(Corral corral);
}
