package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;

public interface RepositorioHistoriaClinica {
	public HistoriaClinica buscarHistoriaClinicaPorId(Long id);
	public void guardarHistoriaClinica(HistoriaClinica historiaClinica);
	public void eliminarHistoriaClinica(HistoriaClinica historiaClinica);
}
