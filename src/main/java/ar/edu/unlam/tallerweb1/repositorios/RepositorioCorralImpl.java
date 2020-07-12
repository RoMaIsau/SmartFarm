package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Corral;

@Repository
public class RepositorioCorralImpl implements RepositorioCorral {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioCorralImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Corral> obtenerTodosLosCorrales() {

		Session session = this.sessionFactory.getCurrentSession();

		return session.createQuery("SELECT c FROM Corral c", Corral.class).getResultList();
	}

	@Override
	public void crear(Corral corralNuevo) {
		this.sessionFactory.getCurrentSession().save(corralNuevo);
	}

	@Override
	public void actualizar(Corral corral) {
		Corral corralParaActualizar = this.sessionFactory.getCurrentSession().load(Corral.class, corral.getId());
		corralParaActualizar.setNombre(corral.getNombre());
		corralParaActualizar.setVertices(corral.getVertices());
		this.sessionFactory.getCurrentSession().update(corralParaActualizar);
	}

	@Override
	public void eliminar(Corral corral) {
		this.sessionFactory.getCurrentSession().remove(corral);
	}

	@Override
	public String obtenerNombre(Long idCorral) {

	return this.sessionFactory.getCurrentSession()
			.createQuery("select c.nombre from Corral c where c.id = :id", String.class)
			.setParameter("id", idCorral)
			.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AnimalDeGranja> obtenerAnimalesPorCorral(Long idCorral) {
		return this.sessionFactory.getCurrentSession()
				.createQuery("select a from AnimalDeGranja a where a.corral.id = :idCorral")
				.setParameter("idCorral", idCorral)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AnimalDeGranja> obtenerAnimalesSinCorral() {
		return this.sessionFactory.getCurrentSession()
				.createQuery("select a from AnimalDeGranja a where a.corral is null")
				.list();
	}

	@Override
	public void asignarAnimales(Long idCorral, Long[] idAnimales) {
		this.sessionFactory.getCurrentSession()
			.createQuery("update AnimalDeGranja a set a.corral.id = :idCorral where a.id in :idAnimales")
			.setParameter("idCorral", idCorral)
			.setParameterList("idAnimales", idAnimales)
			.executeUpdate();
	}

	@Override
	public void quitarAnimales(Corral corral) {
		this.sessionFactory.getCurrentSession()
			.createQuery("update AnimalDeGranja a set a.corral = null where a.corral = :corral")
			.setParameter("corral", corral)
			.executeUpdate();
	}

	@Override
	public Corral obtenerCorralPorAnimal(AnimalDeGranja animal) {
		return this.sessionFactory.getCurrentSession().createQuery("SELECT a.corral FROM AnimalDeGranja a", Corral.class)
				.getSingleResult();
	}
}