package ar.edu.unlam.tallerweb1.repositorios;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Ubicacion;

@Repository("repositorioUbicacion")
@Transactional
public class RepositorioUbicacionImpl implements RepositorioUbicacion {
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioUbicacionImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void guardarUbicacion(Ubicacion ubicacion) {
		sessionFactory.getCurrentSession().saveOrUpdate(ubicacion);
	}

}
