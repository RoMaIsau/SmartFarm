package ar.edu.unlam.tallerweb1.repositorios;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;

@Repository
public class RepositorioDeAnimalesImpl implements RepositorioDeAnimales {
	
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioDeAnimalesImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void guardar(AnimalDeGranja animal) {		
		this.sessionFactory.getCurrentSession().save(animal);
	}

}
