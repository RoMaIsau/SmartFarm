package ar.edu.unlam.tallerweb1.servicios;

import java.util.Calendar;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.Sintomas;


public interface ServicioGanado {
	public List<AnimalDeGranja> listar();
	public AnimalDeGranja ver(Long id);
	
	public HistoriaClinica verHC(Long id);
	
	void guardar(AnimalDeGranja animal);
	void guardarHC(HistoriaClinica hc);
	void guardarSV(SignosVitales sv);
	
	public void modificarSignos(SignosVitales signos);
	public List<SignosVitales> alarmaSignos(AnimalDeGranja v);
	public String diagnosticar(HistoriaClinica hc,Sintomas sintomas);
	public void guardarSintomas(Sintomas sintomas);
	
	

}
