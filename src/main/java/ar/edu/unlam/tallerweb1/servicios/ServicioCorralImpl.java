package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Corral;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCorral;

@Service
@Transactional
public class ServicioCorralImpl implements ServicioCorral {

	private Logger logger = LoggerFactory.getLogger(ServicioCorralImpl.class);
	private RepositorioCorral repositorioCorral;

	@Autowired
	public ServicioCorralImpl(RepositorioCorral repositorioCorral) {
		this.repositorioCorral = repositorioCorral;
	}

	@Override
	public List<Corral> obtenerTodosLosCorrales() {
		return repositorioCorral.obtenerTodosLosCorrales();
	}

	@Override
	public void guardar(Corral corral) {
		if (corral.getId() == null) {
			this.repositorioCorral.crear(corral);
		} else {
			this.repositorioCorral.actualizar(corral);
		}

	}

	@Override
	public void eliminar(Corral corral) {
		quitarAnimales(corral);
		this.repositorioCorral.eliminar(corral);
	}

	@Override
	public String obtenerNombre(Long idCorral) {
		return this.repositorioCorral.obtenerNombre(idCorral);
	}

	@Override
	public List<AnimalDeGranja> obtenerAnimalesPorCorral(Long idCorral) {
		return this.repositorioCorral.obtenerAnimalesPorCorral(idCorral);
	}

	@Override
	public List<AnimalDeGranja> obtenerAnimalesSinCorral() {
		return this.repositorioCorral.obtenerAnimalesSinCorral();
	}

	@Override
	public void asignarAnimales(Long idCorral, Long[] idAnimales) {
		this.repositorioCorral.asignarAnimales(idCorral, idAnimales);
	}

	@Override
	public void quitarAnimales(Corral corral) {
		this.repositorioCorral.quitarAnimales(corral);
	}

	@Override
	public Corral obtenerCorralAsignado(AnimalDeGranja animal) {
		Corral corral = null;
		try {
			corral = this.repositorioCorral.obtenerCorralPorAnimal(animal);
		} catch(Exception e) {
			logger.error("Ocurrió un error obteniendo el corral del animal {}", animal);
		}
		return corral;
	}
}
