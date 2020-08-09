package ar.edu.unlam.tallerweb1.repositorios;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.SignosVitales;

@Repository
@Transactional
public class RepositorioSignosVitalesImpl implements RepositorioSignosVitales {
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public void eliminarSignosVitales(SignosVitales signosVitales) {
		sessionFactory.getCurrentSession().delete(signosVitales);
	}
	
	@Override
	public void guardarSignosVitales(SignosVitales signosVitales) {
		sessionFactory.getCurrentSession().save(signosVitales);
	}
	
}
