package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.GanadoVacuno;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;

public interface RepositorioVacunas {
	
   void vacunar (GanadoVacuno gv, Vacuna vacuna);
	
	void guardar(Vacuna vacuna);

	

	Vacuna getVacuna(String nombre);

}
