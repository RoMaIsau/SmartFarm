package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.TipoAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoAlimento;

@Service("servicioalimento")
@Transactional
public class ServicioAlimentoImpl implements ServicioAlimento {

	private RepositorioTipoAlimento repositorioTipoAlimento;
	
	@Autowired
	public ServicioAlimentoImpl(RepositorioTipoAlimento repositorioTipoAlimento) {

		this.repositorioTipoAlimento = repositorioTipoAlimento;
	}

	@Override
	public List<TipoAlimento> obtenerTiposDeAlimentos() {
		return (List<TipoAlimento>) repositorioTipoAlimento.obtenerTiposDeAlimentos();
	}

}
