package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Ubicacion;
import ar.edu.unlam.tallerweb1.modelo.UbicacionesCentrales;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUbicacionesCentrales;

@Service
@Transactional
public class ServicioUbicacionesCentralesImpl implements ServicioUbicacionesCentrales {
	
	@Inject
	private RepositorioUbicacionesCentrales repositorioUbicacionesCentrales;
	
	@Override
	public UbicacionesCentrales obtenerUbicacionesCentrales() {
		return repositorioUbicacionesCentrales.obtenerUbicacionesCenrtales();
	}

	@Override
	public void caprinoModificarCoordenadas(Double latitud, Double longitud) {
		repositorioUbicacionesCentrales.caprinoModificarCoordenadas(latitud, longitud);
	}

	@Override
	public void equinoModificarCoordenadas(Double latitud, Double longitud) {
		repositorioUbicacionesCentrales.equinoModificarCoordenadas(latitud, longitud);
	}

	@Override
	public void ovinoModificarCoordenadas(Double latitud, Double longitud) {
		repositorioUbicacionesCentrales.ovinoModificarCoordenadas(latitud, longitud);
	}

	@Override
	public void porcinoModificarCoordenadas(Double latitud, Double longitud) {
		repositorioUbicacionesCentrales.porcinoModificarCoordenadas(latitud, longitud);
	}

	@Override
	public void vacunoModificarCoordenadas(Double latitud, Double longitud) {
		repositorioUbicacionesCentrales.vacunoModificarCoordenadas(latitud, longitud);
	}

}
