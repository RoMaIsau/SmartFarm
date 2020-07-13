package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Enfermedad;
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

	void guardarEnfermedad(Enfermedad enfermedad);

	void modificarSignos(SignosVitales signos);

	

	void guardarSV(SignosVitales sv);

	void guardar(Sintomas sintomas);

	List<SignosVitales> signos(HistoriaClinica hc);
	
	SignosVitales signosFecha(HistoriaClinica hc);

	HistoriaClinica verHC(AnimalDeGranja animal);

	List<Enfermedad> enfermedadesComunes(HistoriaClinica hc);

	List<Enfermedad> todasEnfermedades();

	Enfermedad buscarEnfermedad(Long id);

	void updateEnfermedad(Enfermedad e);

	HistoriaClinica verHC(Long id);

	
	

	
}
