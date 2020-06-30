package ar.edu.unlam.tallerweb1.servicios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Gastos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGastos;

@Service
@Transactional
public class ServicioGastosImpl implements ServicioGastos {

	@Inject
	private RepositorioGastos repositorioGastos;

	@Override
	public List<Gastos> consultarGastosPorUsuario(Usuario usuario) {
		return repositorioGastos.consultarGastosPorUsuario(usuario);
	}

	@Override
	public List<Gastos> consultarGastos() {
		return repositorioGastos.consultarGastos();
	}

	@Override
	public Long guardarNuevoRegistro(Gastos gastos) {
		gastos.setFecha(LocalDate.now());

		return repositorioGastos.guardarNuevoRegistro(gastos);
	}

	@Override
	public Gastos consultaGastosPorID(Long id) {
		return repositorioGastos.consultaGastosPorID(id);
	}

	@Override
	public void eliminarGastos(Gastos gastos) {
		repositorioGastos.eliminarGastos(gastos);
	}

	@Override
	public void modificarGasto(Gastos gastosActuales) {
		repositorioGastos.modificarGasto(gastosActuales);
	}

	@Override
	public TreeMap<Integer, Double> consultarGastosPorMes(String gasto) {

		List<Gastos> gastos = repositorioGastos.consultarGastos();
		TreeMap<Integer, Double> gastosPorMes = new TreeMap<Integer, Double>();

		for (Gastos g : gastos) {
			String nombreGasto = g.getTipoDeGasto().getNombre();
			if (nombreGasto.equals(gasto)) {
				Boolean existeMes = false;

				for (Map.Entry<Integer, Double> gMes : gastosPorMes.entrySet()) {
					if (gMes.getKey().equals(g.getFecha().getMonthValue())) {
						Double nuevoMonto = g.getMonto() + gMes.getValue();
						gMes.setValue(nuevoMonto);
						existeMes = true;
					}
				}

				if (!existeMes) {
					int mes = g.getFecha().getMonthValue();
					gastosPorMes.put(mes, g.getMonto());
				}
			}

		}
		return gastosPorMes;
	}

	@Override
	public List<Gastos> consultarGastosEnTotalPorTipo() {
		return repositorioGastos.consultarGastosEnTotalPorTipo();
	}

	@Override
	public TreeMap<Integer, Double> consultarGastosEnTotal() {
		List<Gastos> gastos = repositorioGastos.consultarGastos();
		TreeMap<Integer, Double> gastosEnTotalPorMes = new TreeMap<Integer, Double>();

		for (Gastos g : gastos) {
			Boolean existeMes = false;

			for (Map.Entry<Integer, Double> gMes : gastosEnTotalPorMes.entrySet()) {
				if (gMes.getKey().equals(g.getFecha().getMonthValue())) {
					Double nuevoMonto = g.getMonto() + gMes.getValue();
					gMes.setValue(nuevoMonto);
					existeMes = true;
				}
			}

			if (!existeMes) {
				int mes = g.getFecha().getMonthValue();
				gastosEnTotalPorMes.put(mes, g.getMonto());
			}
		}
		return gastosEnTotalPorMes;
	}

}
