package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Vacunar;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;

public interface RepositorioVacunas {
	
   void vacunar (AnimalDeGranja gv, Vacuna vacuna);
	
	void guardar(Vacuna vacuna);

	

	Vacuna getVacuna(String nombre);

	Vacunar getAnimalVacuna(Vacuna v, AnimalDeGranja a);

}
