package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;

@Repository
public class RepositorioDeRazasImpl implements RepositorioDeRazas {
	
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioDeRazasImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Raza> listarPorTipoAnimal(TipoAnimal tipoAnimal) {
		
		Session session = this.sessionFactory.getCurrentSession();
		
		return session.createQuery("SELECT r FROM Raza r WHERE r.tipo = :tipoAnimal", Raza.class)
				.setParameter("tipoAnimal", tipoAnimal)
				.getResultList();
	}

}
