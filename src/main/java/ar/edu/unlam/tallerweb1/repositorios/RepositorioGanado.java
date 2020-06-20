package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.Sintomas;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioGanado {
	
	AnimalDeGranja ver(Long id);
	
	void guardar(AnimalDeGranja animal);
	
	void guardarHC(HistoriaClinica hc);

	void obtener(AnimalDeGranja animal);

	List<AnimalDeGranja> listar();

	

	void modificarSignos(SignosVitales signos);

	HistoriaClinica verHC(Long id);

	void guardarSV(SignosVitales sv);

	void guardar(Sintomas sintomas);

	
}
