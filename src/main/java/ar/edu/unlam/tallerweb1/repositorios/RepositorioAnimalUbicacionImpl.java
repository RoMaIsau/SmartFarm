package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;

@Repository("repositorioAnimalUbicacion")
public class RepositorioAnimalUbicacionImpl implements RepositorioAnimalUbicacion {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioAnimalUbicacionImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void guardar(AnimalUbicacion animalUbicacion) {
		sessionFactory.getCurrentSession().save(animalUbicacion);
	}

}
