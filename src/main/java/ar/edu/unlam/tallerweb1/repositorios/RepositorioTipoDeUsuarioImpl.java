package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.TipoDeUsuario;

@Repository
@Transactional
public class RepositorioTipoDeUsuarioImpl implements RepositorioTipoDeUsuario {
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioTipoDeUsuarioImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public TipoDeUsuario consultarRol(String rol) {
		return (TipoDeUsuario) sessionFactory.getCurrentSession().createCriteria(TipoDeUsuario.class)
				.add(Restrictions.eq("nombre", rol)).uniqueResult();
	}

	@Override
	public List<TipoDeUsuario> ObtenerTodosLosRoles() {
		return sessionFactory.getCurrentSession().createCriteria(TipoDeUsuario.class).list();
	}

	@Override
	public void guardarTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {
		sessionFactory.getCurrentSession().save(tipoDeUsuario);
	}

}

