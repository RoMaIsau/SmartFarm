package ar.edu.unlam.tallerweb1.repositorios;


import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;

import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.Sintomas;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.inject.Inject;

// implelemtacion del repositorio de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("repositorioGanado")
public class RepositorioGanadoImpl implements RepositorioGanado {

	// Como todo repositorio maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioGanadoImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public AnimalDeGranja ver(Long id) {

		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que invoca a este metodo y se crea un criterio
		// de busqueda de Usuario donde el email y password sean iguales a los del objeto recibido como parametro
		// uniqueResult da error si se encuentran más de un resultado en la busqueda.
		final Session session = sessionFactory.getCurrentSession();
		return (AnimalDeGranja) session.createCriteria(AnimalDeGranja.class)
				.add(Restrictions.eq("id", id))
				
				.uniqueResult();
	}
	
	@Override
	public HistoriaClinica verHC(Long id) {

		
		final Session session = sessionFactory.getCurrentSession();
		return (HistoriaClinica) session.createCriteria(HistoriaClinica.class)
				.add(Restrictions.eq("idAnimal", id))
				
				.uniqueResult();
	}
	

	@Override
	public List<AnimalDeGranja> listar() {

		
		final Session session = sessionFactory.getCurrentSession();
		return (List<AnimalDeGranja>) session.createCriteria(AnimalDeGranja.class)
				.list();
				
				
	}

	@Override
	public void guardar(AnimalDeGranja animal) {
		// TODO Auto-generated method stub
		final Session session = sessionFactory.getCurrentSession();
		session.save(animal);
		
	}
	
	@Override
	public void obtener(AnimalDeGranja animal) {
		// TODO Auto-generated method stub
		final Session session = sessionFactory.getCurrentSession();
		session.get(AnimalDeGranja.class, animal.getId());
		
	}


	@Override
	public void modificarSignos(SignosVitales signos) {
		// TODO Auto-generated method stub
		final Session session = sessionFactory.getCurrentSession();
		session.update(signos);
		
	}

	@Override
	public void guardarHC(HistoriaClinica hc) {
		// TODO Auto-generated method stub
		final Session session = sessionFactory.getCurrentSession();
		session.save(hc);
	}

	@Override
	public void guardarSV(SignosVitales sv) {
		// TODO Auto-generated method stub
		final Session session = sessionFactory.getCurrentSession();
		session.save(sv);
		
	}

	@Override
	public void guardar(Sintomas sintomas) {
		// TODO Auto-generated method stub
		final Session session = sessionFactory.getCurrentSession();
		session.save(sintomas);
		
	}



}


