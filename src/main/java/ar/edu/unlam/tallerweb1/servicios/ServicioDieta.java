package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Dieta;


import ar.edu.unlam.tallerweb1.modelo.Vacuna;

public interface ServicioDieta {
	void alimentar(AnimalDeGranja animal, Dieta dieta);
	
	
		

	List<Dieta> alarmaDieta(AnimalDeGranja animal);
	
	Dieta getDieta(String nombre);
	

	Dieta getDieta(AnimalDeGranja animal);

	void guardarDieta(Dieta dieta);
	
	
	

}
