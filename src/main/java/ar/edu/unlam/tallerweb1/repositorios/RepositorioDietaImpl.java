package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Dieta;


import ar.edu.unlam.tallerweb1.modelo.Vacuna;

@Repository("RepositorioDieta")
public class RepositorioDietaImpl implements RepositorioDieta {
	
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioDietaImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void alimentar(AnimalDeGranja gv, Dieta dieta) {
		// TODO Auto-generated method stub
		final Session session = sessionFactory.getCurrentSession();
		dieta.setPendiente(false);
		session.update(dieta);
		
		
	}

	@Override
	public void guardarDieta(Dieta dieta) {
		// TODO Auto-generated method stub
		final Session session = sessionFactory.getCurrentSession();
		session.save(dieta);
		
		
	}
	
	@Override
	public Dieta getDieta(String nombre) {
		
		final Session session = sessionFactory.getCurrentSession();
		return (Dieta) session.createCriteria(Dieta.class)
				.add(Restrictions.eq("nombre", nombre))
				
				.uniqueResult();

	}
	
	@Override
	public Dieta getDieta(AnimalDeGranja gv) {
		
		final Session session = sessionFactory.getCurrentSession();
		return (Dieta) session.createCriteria(Dieta.class).createAlias("animal", "aliasAnimal")
				.add(Restrictions.eq("aliasAnimal.id", gv.getId()))
				
				.uniqueResult();

	}
	
	

	

}
