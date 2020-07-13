package ar.edu.unlam.tallerweb1.repositorios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
		sessionFactory.getCurrentSession().saveOrUpdate(animalUbicacion);
	}

	@Override
	public AnimalUbicacion obtenerAnimalUbicacion(Long id, LocalDateTime fecha) {
		Session session = sessionFactory.getCurrentSession();
		return (AnimalUbicacion) session.createCriteria(AnimalUbicacion.class).add(Restrictions.eq("animal.id", id))
				.add(Restrictions.eq("fecha", fecha)).uniqueResult();
	}

	@Override
	public List<AnimalUbicacion> obtenerPorIdAnimal(Long idAnimal) {

		Session session = sessionFactory.getCurrentSession();

		return (List<AnimalUbicacion>) session.createCriteria(AnimalUbicacion.class)
				.add(Restrictions.eq("animal.id", idAnimal))
				.addOrder(Order.asc("fecha"))
				.list();
	}

	@Override
	public AnimalUbicacion obtenerUbicacionAnimal(Long idAnimal) {
		return (AnimalUbicacion) sessionFactory.getCurrentSession().createCriteria(AnimalUbicacion.class)
				.add(Restrictions.eq("animal.id", idAnimal))
				.add(Restrictions.eq("fecha", LocalDate.now())).uniqueResult();
	}
}
