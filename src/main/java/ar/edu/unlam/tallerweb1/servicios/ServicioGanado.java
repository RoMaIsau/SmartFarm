package ar.edu.unlam.tallerweb1.servicios;

import java.util.Calendar;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Enfermedad;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.Sintomas;


public interface ServicioGanado {
	public List<AnimalDeGranja> listar();
	public AnimalDeGranja ver(Long id);
	
	public HistoriaClinica verHC(AnimalDeGranja animal);
	
	void guardar(AnimalDeGranja animal);
	void guardarHC(HistoriaClinica hc);
	void guardarSV(SignosVitales sv);
	
	public void modificarSignos(SignosVitales signos);
	
	public List<SignosVitales> signos(HistoriaClinica h);
	public void guardarEnfermedad(Enfermedad enfermedad);
	public void guardarSintomas(Sintomas sintomas);
	String diagnosticar(List<SignosVitales> signos, Sintomas sintomas);
	SignosVitales signosFecha(HistoriaClinica hc);
	List<Enfermedad> enfermedadesComunes(HistoriaClinica hc);
	List<Enfermedad> enfermedadesComunesRanking(List<Enfermedad>enfermedades);
	Boolean alarmaSV(SignosVitales signos);
	List<Enfermedad> todasEnfermedades();

}
