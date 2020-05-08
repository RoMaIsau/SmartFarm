package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.GanadoVacuno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioGanado {
	
	GanadoVacuno ver(Long id);
	
	void guardar(GanadoVacuno ganado);

	void obtener(GanadoVacuno ganado);

	List<GanadoVacuno> listar();
}

