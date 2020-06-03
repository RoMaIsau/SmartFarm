package ar.edu.unlam.tallerweb1.repositorios;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.TipoDeUsuario;

@Repository
@Transactional
public class RepositorioTipoDeUsuarioImpl implements RepositorioTipoDeUsuario {
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Override
	public TipoDeUsuario consultarRol(String rol) {
		return (TipoDeUsuario) sessionFactory.getCurrentSession().createCriteria(TipoDeUsuario.class)
				.add(Restrictions.eq("nombre", rol)).uniqueResult();
	}

}

