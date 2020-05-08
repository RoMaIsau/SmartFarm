package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.GanadoVacuno;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;

public interface ServicioVacunas {
	
	void vacunar(GanadoVacuno ganado, Vacuna vacuna);
	
	void guardar(Vacuna vacuna);
	
	Vacuna getVacuna(String nombre);

	Vacuna alarmaVacuna(GanadoVacuno gv);

}
