package ar.edu.unlam.tallerweb1.servicios;

import java.util.Calendar;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Enfermedad;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.Sintomas;
import ar.edu.unlam.tallerweb1.modelo.Tratamiento;


public interface ServicioGanado {
	public List<AnimalDeGranja> listar();
	public AnimalDeGranja ver(Long id);
	public Enfermedad buscarEnfermedad(Long id);
	
	public HistoriaClinica verHC(AnimalDeGranja animal);
	public HistoriaClinica verHC(Long id);
	void guardar(AnimalDeGranja animal);
	void guardarHC(HistoriaClinica hc);
	void guardarSV(SignosVitales sv);
	
	public void modificarSignos(SignosVitales signos);
	public boolean alarmaTratamientoA(HistoriaClinica historia);
	public List<SignosVitales> signos(HistoriaClinica h);
	public void guardarEnfermedad(Enfermedad enfermedad);
	public void guardarSintomas(Sintomas sintomas);
	public void updateEnfermedad(Enfermedad e);
	String diagnosticar(List<SignosVitales> signos, Sintomas sintomas);
	SignosVitales signosFecha(HistoriaClinica hc);
	List<Enfermedad> enfermedadesComunes(HistoriaClinica hc);
	List<Enfermedad> enfermedadesComunesRanking(List<Enfermedad>enfermedades);
	Boolean alarmaSV(SignosVitales signos);
	List<Enfermedad> todasEnfermedades();
	public String tratamientoA(String enfermedad);
	public String tratamientoB(String enfermedad);
	public Tratamiento buscarTratamiento(String nombre);
	Enfermedad tipoTratamientoB(HistoriaClinica historia);
	Enfermedad tipoTratamientoA(HistoriaClinica historia);
	public void generarNuevosSignosVitales(HistoriaClinica historia, String nombre);
	public void crearTemperaturaAnormal(HistoriaClinica historia);
	public void crearPulsoAnormal(HistoriaClinica historia);
	public void crearFCAnormal(HistoriaClinica historia);
	public void crearFRAnormal(HistoriaClinica historia);
	public void crearSVNormales(HistoriaClinica historia);

}
