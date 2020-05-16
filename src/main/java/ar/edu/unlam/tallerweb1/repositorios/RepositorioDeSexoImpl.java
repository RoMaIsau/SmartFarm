package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Sexo;

@Repository
public class RepositorioDeSexoImpl implements RepositorioDeSexo {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioDeSexoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Sexo> listar() {

		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("SELECT s FROM Sexo s", Sexo.class).getResultList();
	}
	
}
