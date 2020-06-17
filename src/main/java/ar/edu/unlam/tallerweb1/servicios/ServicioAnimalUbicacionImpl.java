package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;

@Service
@Transactional
public class ServicioAnimalUbicacionImpl implements ServicioAnimalUbicacion {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public ServicioAnimalUbicacionImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<AnimalUbicacion> obtenerPorIdAnimal(Long idAnimal) {
		
		Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(AnimalUbicacion.class)
				.add(Restrictions.eq("animal.id", idAnimal))
				.list();
	}

}
