package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Gastos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface RepositorioGastos {

	List<Gastos> consultarGastos();

	List<Gastos> consultarGastosPorUsuario(Usuario usuario);

	Long guardarNuevoRegistro(Gastos gastos);

	Gastos consultaGastosPorID(Long id);

	void eliminarGastos(Gastos gastos);

	void modificarGasto(Gastos gastosActuales);

	List<Gastos> consultarGastosEnTotal();

}
