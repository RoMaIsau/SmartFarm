package ar.edu.unlam.tallerweb1.repositorios;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;

@Repository
@Transactional
public class RepositorioHistoriaClinicaImpl implements RepositorioHistoriaClinica {
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public HistoriaClinica buscarHistoriaClinicaPorId(Long id) {
		return (HistoriaClinica) sessionFactory.getCurrentSession().get(HistoriaClinica.class, id);
	}

}
