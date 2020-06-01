package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Gastos;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGastos;

@Service
@Transactional
public class ServicioGastosImpl implements ServicioGastos {
	
	@Inject
	private RepositorioGastos repositorioGastos;
	
	@Override
	public List<Gastos> consultarGastosPorUsuario(String idEncontrado) {
		return repositorioGastos.consultarGastosPorUsuario(idEncontrado);
	}

	@Override
	public List<Gastos> consultarGastos() {
		return repositorioGastos.consultarGastos();
	}

}
