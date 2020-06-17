package ar.edu.unlam.tallerweb1.servicios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import java.util.List;

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
		Date myDate = new Date();
        gastos.setFecha(new java.text.SimpleDateFormat("dd-MM-yyyy").format(myDate));
		
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

}
