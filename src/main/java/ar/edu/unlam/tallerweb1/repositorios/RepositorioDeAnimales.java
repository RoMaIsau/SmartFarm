package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Enfermedad;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;

public interface RepositorioDeAnimales {
	
	void guardar(AnimalDeGranja animal, SignosVitales sv, HistoriaClinica hc);

	List<AnimalDeGranja> listar();

	AnimalDeGranja buscarPorId(Long id);

	void actualizar(AnimalDeGranja animal);

	void eliminar(AnimalDeGranja animal, SignosVitales signosVitales, HistoriaClinica historiaClinica);

	AnimalDeGranja obtenerPorIdentificadorGps(String identificador);

	Enfermedad buscarUltimaEnfermedadDelAnimal(Long id);

	SignosVitales buscarUltimosSignosVitalesDelAnimal(Long id);
}
