package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.excepciones.AnimalSinIdentificadorGpsException;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Enfermedad;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;

public interface ServicioDeAnimales {

	List<TipoAnimal> obtenerTiposDeAnimales();

	List<Raza> obtenerRazasPorTipoAnimal(TipoAnimal tipoAnimal);

	List<Genero> obtenerGeneros();

	void registrar(AnimalDeGranja animal);

	List<AnimalDeGranja> obtenerTodos();

	AnimalDeGranja obtenerPorId(Long idAnimal);

	void actualizarAnimal(AnimalDeGranja animal);

	void eliminarPorId(Long idAnimal, SignosVitales sv, HistoriaClinica hc);

	AnimalDeGranja obtenerPorIdentificadorGps(String identificador) throws AnimalSinIdentificadorGpsException;

	Enfermedad buscarUltimaEnfermedadDelAnimal(Long id);

	SignosVitales buscarUltimosSignosVitalesDelAnimal(Long id);
}
