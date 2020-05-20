package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.TipoAlimento;

public interface ServicioAlimento {
	
	List<TipoAlimento> obtenerTiposDeAlimentos();

	Long registrarAlimento(Alimento alimento);

	Alimento consultarAlimento(Alimento Alimento);

	List<Alimento> listarAlimentos();

	Alimento consultarAlimentoPorId(String id);
}
