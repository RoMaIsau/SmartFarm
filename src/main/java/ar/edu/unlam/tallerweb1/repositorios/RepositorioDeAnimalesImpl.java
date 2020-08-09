package ar.edu.unlam.tallerweb1.repositorios;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.cj.x.protobuf.MysqlxCrud.Find;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Enfermedad;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;

@Repository
public class RepositorioDeAnimalesImpl implements RepositorioDeAnimales {
	
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioDeAnimalesImpl(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void guardar(AnimalDeGranja animal, SignosVitales sv, HistoriaClinica hc) {		
		this.sessionFactory.getCurrentSession().save(animal);
		
		this.sessionFactory.getCurrentSession().save(hc);
		
		this.sessionFactory.getCurrentSession().save(sv);
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

	@Override
	public AnimalDeGranja obtenerPorIdentificadorGps(String identificador) {
		return this.sessionFactory.getCurrentSession()
				.createQuery("SELECT a FROM AnimalDeGranja a WHERE identificadorGps = :identificador", AnimalDeGranja.class)
				.setParameter("identificador", identificador)
				.getSingleResult();
	}
	
	@Override
	public Enfermedad buscarUltimaEnfermedadDelAnimal(Long id) {
		String query = "select e from Enfermedad as e where historia_id = " + id + " order by id desc";
		Enfermedad enfermedad = (Enfermedad) this.sessionFactory.getCurrentSession()
				.createQuery(query, Enfermedad.class).setMaxResults(1).uniqueResult();
		return enfermedad;
	}

	@Override
	public SignosVitales buscarUltimosSignosVitalesDelAnimal(Long id) {
		String query = "select s from SignosVitales as s where historia_id = " + id + " order by id desc";
		SignosVitales sv = (SignosVitales) this.sessionFactory.getCurrentSession()
				.createQuery(query, SignosVitales.class).setMaxResults(1).uniqueResult();
		return sv;
	}
	
}
