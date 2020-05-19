package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.TipoAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoAlimento;

@Service
@Transactional
public class ServicioAlimentoImpl implements ServicioAlimento {

	private RepositorioTipoAlimento repositorioTipoAlimento;

	public ServicioAlimentoImpl(RepositorioTipoAlimento repositorioTipoAlimento) {

		this.repositorioTipoAlimento = repositorioTipoAlimento;
	}

	@Override
	public List<TipoAlimento> obtenerTiposDeAlimentos() {
		return (List<TipoAlimento>) repositorioTipoAlimento.obtenerTiposDeAlimentos();
	}

}