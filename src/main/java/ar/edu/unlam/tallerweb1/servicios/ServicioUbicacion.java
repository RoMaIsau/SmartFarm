package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;
import ar.edu.unlam.tallerweb1.modelo.Posicion;

public interface ServicioUbicacion {

	List<AnimalUbicacion> obtenerUbicaciones();

	void registrar(Posicion posicion);

}
