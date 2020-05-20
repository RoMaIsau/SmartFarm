package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Alimento;

public interface RepositorioAlimento {
	
	Long registrarAlimento(Alimento alimento);

	Alimento consultarAlimento(Alimento alimento);

	List<Alimento> listarAlimentos();

	Alimento consultarAlimentoPorId(String id);
}
