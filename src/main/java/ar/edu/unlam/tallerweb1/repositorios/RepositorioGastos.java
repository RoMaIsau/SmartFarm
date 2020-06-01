package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Gastos;

public interface RepositorioGastos {

	List<Gastos> consultarGastos();
	List<Gastos> consultarGastosPorUsuario(String idEncontrado);

}
