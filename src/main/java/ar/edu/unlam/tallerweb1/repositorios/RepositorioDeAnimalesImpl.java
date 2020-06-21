package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
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

	@Override
	public List<AnimalDeGranja> listar() {

		Session session = this.sessionFactory.getCurrentSession();

		return session.createQuery("SELECT a FROM AnimalDeGranja a", AnimalDeGranja.class).getResultList();
	}

	@Override
	public AnimalDeGranja buscarPorId(Long id) {

		Session session = this.sessionFactory.getCurrentSession();

		return session.createQuery("SELECT a FROM AnimalDeGranja a WHERE a.id = :id", AnimalDeGranja.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public void actualizar(AnimalDeGranja animal) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(animal);
	}

	@Override
	public void eliminar(AnimalDeGranja animal) {
		this.sessionFactory.getCurrentSession().delete(animal);
	}
}
