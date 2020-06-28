package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Corral;

public interface ServicioCorral {
	List<Corral> obtenerTodosLosCorrales();

	void guardar(Corral corral);
}
