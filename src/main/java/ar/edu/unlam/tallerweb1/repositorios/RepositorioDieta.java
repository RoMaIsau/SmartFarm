package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Dieta;


import ar.edu.unlam.tallerweb1.modelo.Vacuna;

public interface RepositorioDieta {
	
	 void alimentar (AnimalDeGranja gv, Dieta dieta);
		
		void guardarDieta(Dieta dieta);
      
		Dieta getDieta(String nombre);

		Dieta getDieta(AnimalDeGranja gv);

		

		

	

}
