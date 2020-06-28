package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Corral;

@Repository
public class RepositorioCorralImpl implements RepositorioCorral {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioCorralImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Corral> obtenerTodosLosCorrales() {

		Session session = this.sessionFactory.getCurrentSession();

		return session.createQuery("SELECT c FROM Corral c", Corral.class).getResultList();
	}
}