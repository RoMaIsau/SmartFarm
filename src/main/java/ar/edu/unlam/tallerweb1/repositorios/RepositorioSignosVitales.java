package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.SignosVitales;

public interface RepositorioSignosVitales {
	public void eliminarSignosVitales(SignosVitales signosVitales);
	public void guardarSignosVitales(SignosVitales signosVitales);
}
