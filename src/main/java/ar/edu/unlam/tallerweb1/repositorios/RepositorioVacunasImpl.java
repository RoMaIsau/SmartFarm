package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.GanadoVacuno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import antlr.collections.List;

import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;

// implelemtacion del repositorio de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("RepositorioVacunas")
public class RepositorioVacunasImpl implements RepositorioVacunas {

	// Como todo repositorio maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	private SessionFactory sessionFactory;

    @Autowired
	public RepositorioVacunasImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}



	@Override
	public void vacunar(GanadoVacuno gv, Vacuna vacuna) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardar(Vacuna vacuna) {
		// TODO Auto-generated method stub
		final Session session = sessionFactory.getCurrentSession();
		session.save(vacuna);
		
	}







	



	@Override
	public Vacuna getVacuna(String nombre) {
		
		final Session session = sessionFactory.getCurrentSession();
		return (Vacuna) session.createCriteria(Vacuna.class)
				.add(Restrictions.eq("nombre", nombre))
				
				.uniqueResult();

	}}

