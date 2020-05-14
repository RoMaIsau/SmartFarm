package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;

public interface RepositorioDeRazas {

	List<Raza> listarPorTipoAnimal(TipoAnimal tipoAnimal);

}
