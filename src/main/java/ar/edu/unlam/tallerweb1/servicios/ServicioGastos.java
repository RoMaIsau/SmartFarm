package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.TreeMap;

import ar.edu.unlam.tallerweb1.modelo.Gastos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioGastos {

	List<Gastos> consultarGastos();

	List<Gastos> consultarGastosPorUsuario(Usuario usuario);

	Long guardarNuevoRegistro(Gastos gastos);

	Gastos consultaGastosPorID(Long id);

	void eliminarGastos(Gastos gastos);

	void modificarGasto(Gastos gastosActuales);

	TreeMap<Integer, Double> consultarGastosPorMes(String gasto);

	List<Gastos> consultarGastosEnTotalPorTipo();

	TreeMap<Integer, Double> consultarGastosEnTotal();

}
