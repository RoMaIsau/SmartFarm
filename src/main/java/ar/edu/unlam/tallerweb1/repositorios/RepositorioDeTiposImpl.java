package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;

@Repository
public class RepositorioDeTiposImpl implements RepositorioDeTipos {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioDeTiposImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<TipoAnimal> listarDisponibles() {

		Session session = this.sessionFactory.getCurrentSession();
		return session.createQuery("SELECT t FROM TipoAnimal t", TipoAnimal.class).getResultList();
	}

}
