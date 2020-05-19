package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Alimento;

public interface RepositorioAlimento {
	
	Long registrarAlimento(Alimento alimento);

	Alimento consultarAlimento(Alimento alimento);
}
