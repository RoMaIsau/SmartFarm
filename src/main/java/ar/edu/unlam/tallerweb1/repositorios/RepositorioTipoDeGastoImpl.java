package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.TipoDeGasto;

@Repository
@Transactional
public class RepositorioTipoDeGastoImpl implements RepositorioTipoDeGasto {
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public List<TipoDeGasto> obtenerTiposDeGastos() {
		return sessionFactory.getCurrentSession().createCriteria(TipoDeGasto.class).list();
	}

}
