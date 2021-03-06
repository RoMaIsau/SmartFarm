package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.TipoAlimento;

public interface ServicioAlimento {
	
	List<TipoAlimento> obtenerTiposDeAlimentos();

	Long registrarAlimento(Alimento alimento);

	Alimento consultarAlimento(Alimento Alimento);

	List<Alimento> listarAlimentos();

	Alimento consultarAlimentoPorId(Long id);

	void actualizarAlimento(Alimento alimento);

	Double consultarStock(Alimento alimento);

	void decrementarStock(Alimento alimento, int cantidad);

	List<Alimento> listarAlimentosConsumidosPorAnimal(Long idAnimal);
}
