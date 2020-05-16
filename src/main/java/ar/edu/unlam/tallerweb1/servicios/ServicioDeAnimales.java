package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;

public interface ServicioDeAnimales {

	List<TipoAnimal> obtenerTiposDeAnimales();

	List<Raza> obtenerRazasPorTipoAnimal(TipoAnimal tipoAnimal);

	List<Genero> obtenerGeneros();
}
