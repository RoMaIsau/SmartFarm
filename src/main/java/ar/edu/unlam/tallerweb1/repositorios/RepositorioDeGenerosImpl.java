package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Genero;

@Repository
public class RepositorioDeGenerosImpl implements RepositorioDeGeneros {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioDeGenerosImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Genero> listar() {

		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT g FROM Genero g", Genero.class).getResultList();
	}
	
}
