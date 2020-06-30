package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.UbicacionesCentrales;

public interface RepositorioUbicacionesCentrales {

	UbicacionesCentrales obtenerUbicacionesCenrtales();

	void caprinoModificarCoordenadas(Double latitud, Double longitud);
	void equinoModificarCoordenadas(Double latitud, Double longitud);
	void ovinoModificarCoordenadas(Double latitud, Double longitud);
	void porcinoModificarCoordenadas(Double latitud, Double longitud);
	void vacunoModificarCoordenadas(Double latitud, Double longitud);

}
