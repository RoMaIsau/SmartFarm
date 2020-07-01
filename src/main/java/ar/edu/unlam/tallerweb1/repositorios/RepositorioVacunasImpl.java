package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Vacunar;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

// implelemtacion del repositorio de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("RepositorioVacunas")
public class RepositorioVacunasImpl implements RepositorioVacunas {

	// Como todo repositorio maneja acciones de persistencia, normalmente estar�
	// inyectado el session factory de hibernate
	// el mismo est� difinido en el archivo hibernateContext.xml
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioVacunasImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void vacunar(AnimalDeGranja gv, Vacuna vacuna) {
		// TODO Auto-generated method stub
		final Session session = sessionFactory.getCurrentSession();
		Vacunar av = new Vacunar();
		av.setAnimal(gv);
		av.setVacuna(vacuna);
		LocalDate actual = LocalDate.now();
		av.setFechaAplicacion(actual);

		session.save(av);
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
		return (Vacuna) session.createCriteria(Vacuna.class).add(Restrictions.eq("nombre", nombre)).uniqueResult();

	}

	@Override
	public Vacunar getAnimalVacuna(Vacuna v, AnimalDeGranja a) {
		final Session session = sessionFactory.getCurrentSession();
		return (Vacunar) session.createCriteria(Vacunar.class).createAlias("vacuna", "aliasVacuna")
				.add(Restrictions.eq("aliasVacuna.id", v.getId())).createAlias("animal", "animalAlias")
				.add(Restrictions.eq("animalAlias.id", a.getId())).uniqueResult();
	}

	@Override
	public List<Vacunar> obtenerVacunasAplicadas(Long idAnimal) {
		return (List<Vacunar>) sessionFactory.getCurrentSession().createCriteria(Vacunar.class)
				.add(Restrictions.eq("animal.id", idAnimal)).list();
	}

}
