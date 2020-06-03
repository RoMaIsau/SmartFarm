package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Gastos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioGastos {

	List<Gastos> consultarGastos();
	List<Gastos> consultarGastosPorUsuario(Long idEncontrado);
	Long guardarNuevoRegistro(Gastos gastos, Usuario usuario);
	
}
