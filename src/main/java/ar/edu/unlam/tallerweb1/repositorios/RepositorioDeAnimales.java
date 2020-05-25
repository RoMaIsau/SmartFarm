package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;

public interface RepositorioDeAnimales {
	
	void guardar(AnimalDeGranja animal);

	List<AnimalDeGranja> listar();

	AnimalDeGranja buscarPorId(Long id);

	void actualizar(AnimalDeGranja animal);

}
