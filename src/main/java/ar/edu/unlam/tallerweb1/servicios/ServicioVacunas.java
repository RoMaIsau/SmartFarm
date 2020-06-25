package ar.edu.unlam.tallerweb1.servicios;


import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Vacunar;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;


public interface ServicioVacunas {
	
	void vacunar(AnimalDeGranja animal, Vacuna vacuna);
	
	void guardar(Vacuna vacuna);
	
	Vacuna getVacuna(String nombre);
	
	Vacunar getAnimalVacuna(Vacuna v, AnimalDeGranja a);

	List<Vacuna> alarmaVacuna(AnimalDeGranja animal);

}
