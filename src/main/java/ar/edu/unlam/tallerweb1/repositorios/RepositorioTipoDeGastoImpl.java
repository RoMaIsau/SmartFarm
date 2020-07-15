package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.TipoDeGasto;

@Repository
@Transactional
public class RepositorioTipoDeGastoImpl implements RepositorioTipoDeGasto {

	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioTipoDeGastoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<TipoDeGasto> obtenerTiposDeGastos() {
		return sessionFactory.getCurrentSession().createCriteria(TipoDeGasto.class).list();
	}

}
