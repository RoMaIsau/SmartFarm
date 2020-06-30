package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.TipoDeGasto;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoDeGasto;

@Service
@Transactional
public class ServicioTipoDeGastoImpl implements ServicioTipoDeGasto {
	
	@Inject
	private RepositorioTipoDeGasto repositorioTipoDeGasto;
	
	@Override
	public List<TipoDeGasto> obtenerTiposDeGastos() {
		return repositorioTipoDeGasto.obtenerTiposDeGastos();
	}

}
