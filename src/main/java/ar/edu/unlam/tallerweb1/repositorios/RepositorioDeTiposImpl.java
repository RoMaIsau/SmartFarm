package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioDeTiposImpl implements RepositorioDeTipos {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioDeTiposImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
