package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<AnimalUbicacion> obtenerUbicacionesRecientes() {
		return sessionFactory.getCurrentSession()
				.createQuery("SELECT au.animal.id, au.animal.tipo.nombre, MAX(au.fecha) FROM AnimalUbicacion au GROUP BY au.animal.id, au.animal.tipo.nombre ")
				.getResultList();
	}
}
