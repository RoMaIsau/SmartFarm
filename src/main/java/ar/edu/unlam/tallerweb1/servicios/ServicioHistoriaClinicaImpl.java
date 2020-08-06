package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioHistoriaClinica;

@Service
@Transactional
public class ServicioHistoriaClinicaImpl implements ServicioHistoriaClinica {
	
	@Inject
	private RepositorioHistoriaClinica repositorioHistoriaClinica;
	
	@Override
	public HistoriaClinica buscarHistoriaClinicaPorId(Long id) {
		return repositorioHistoriaClinica.buscarHistoriaClinicaPorId(id);
	}

}
