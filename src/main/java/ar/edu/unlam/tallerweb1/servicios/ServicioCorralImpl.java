package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Corral;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCorral;

@Service
@Transactional
public class ServicioCorralImpl implements ServicioCorral {
	
	private RepositorioCorral repositorioCorral;

	@Autowired
	public ServicioCorralImpl(RepositorioCorral repositorioCorral) {
		this.repositorioCorral = repositorioCorral;
	}

	@Override
	public List<Corral> obtenerTodosLosCorrales() {
		return repositorioCorral.obtenerTodosLosCorrales();
	}
}
