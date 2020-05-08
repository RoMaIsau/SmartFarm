package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.GanadoVacuno;

public interface ServicioGanado {
	public List<GanadoVacuno> listar();
	public GanadoVacuno ver(Long id);
	
	void guardar(GanadoVacuno ganado);

}
