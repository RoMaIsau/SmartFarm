package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAnimalUbicacion;

@Service
@Transactional
public class ServicioAnimalUbicacionImpl implements ServicioAnimalUbicacion {

	private RepositorioAnimalUbicacion repositorioAnimalUbicacion;

	@Autowired
	public ServicioAnimalUbicacionImpl(RepositorioAnimalUbicacion repositorioAnimalUbicacion) {
		super();
		this.repositorioAnimalUbicacion = repositorioAnimalUbicacion;
	}

	@Override
	public List<AnimalUbicacion> obtenerPorIdAnimal(Long idAnimal) {
		return repositorioAnimalUbicacion.obtenerPorIdAnimal(idAnimal);
	}

	@Override
	public AnimalUbicacion obtenerUbicacionAnimal(Long idAnimal) {
		return repositorioAnimalUbicacion.obtenerUbicacionAnimal(idAnimal);
	}

}
