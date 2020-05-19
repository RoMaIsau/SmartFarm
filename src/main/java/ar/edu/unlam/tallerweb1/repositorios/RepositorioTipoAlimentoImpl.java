package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.TipoAlimento;

@Repository
public class RepositorioTipoAlimentoImpl implements RepositorioTipoAlimento {

	private SessionFactory sessionFactory;

	public RepositorioTipoAlimentoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<TipoAlimento> obtenerTiposDeAlimentos() {
		final Session session = sessionFactory.getCurrentSession();
		return (List<TipoAlimento>) session.createCriteria(TipoAlimento.class).list();
	}

}
