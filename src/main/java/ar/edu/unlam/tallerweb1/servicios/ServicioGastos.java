package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Gastos;

public interface ServicioGastos {

	List<Gastos> consultarGastos();
	List<Gastos> consultarGastosPorUsuario(String idEncontrado);
	
}
