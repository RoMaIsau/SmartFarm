package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Restrictions;
import ar.edu.unlam.tallerweb1.modelo.Alimento;

@Repository("repositorioalimento")
public class RepositorioAlimentoImpl implements RepositorioAlimento {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioAlimentoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Long registrarAlimento(Alimento alimento) {

		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(alimento);
	}

	@Override
	public Alimento consultarAlimento(Alimento alimento) {
		Session session = sessionFactory.getCurrentSession();
		return (Alimento) session.createCriteria(Alimento.class).add(Restrictions.eq("nombre", alimento.getNombre()))
				.add(Restrictions.eq("tipo", alimento.getTipo())).uniqueResult();
	}

	@Override
	public List<Alimento> listarAlimentos() {
		Session session = sessionFactory.getCurrentSession();
		
		return session.createCriteria(Alimento.class).list();
	}

}
