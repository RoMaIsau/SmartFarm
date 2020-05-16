package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;

public interface ServicioDeAnimales {

	List<Raza> obtenerRazasPorTipoAnimal(TipoAnimal tipoAnimal);

}
