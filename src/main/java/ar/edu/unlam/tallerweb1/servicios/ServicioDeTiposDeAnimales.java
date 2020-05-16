package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;

public interface ServicioDeTiposDeAnimales {
	
	List<TipoAnimal> obtenerDisponibles();
}
