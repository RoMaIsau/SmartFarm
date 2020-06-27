package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.CronogramaDeAlimentacion;
import ar.edu.unlam.tallerweb1.modelo.CronogramaDeAlimentacion.EstadoCronograma;

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

	@Override
	public Alimento consultarAlimentoPorId(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Alimento.class, id);
	}

	@Override
	public void actualizarAlimento(Alimento alimento) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(alimento);
	}

	@Override
	public List<Alimento> listarAlimentosConsumidosPorAnimal(Long idAnimal) {
		Session session = sessionFactory.getCurrentSession();
		
		
		return (List<Alimento>) session.createCriteria(CronogramaDeAlimentacion.class, "c")
				.createAlias("planAlimentario", "p")
				.add(Restrictions.eq("p.animal.id", idAnimal))
				.add(Restrictions.eq("estado", EstadoCronograma.COMPLETO))
				.setProjection(Projections.sum("cantidad"))
				.setProjection(Projections.projectionList()
						.add(Projections.property("alimento.id"), "id")
			            .add(Projections.sqlProjection("sum(cantidad) as cantidad", new String[] {"cantidad"}, new Type[] {StandardBasicTypes.DOUBLE}))
			            .add(Projections.groupProperty("alimento.id")))
				.setResultTransformer(Transformers.aliasToBean(Alimento.class))
				.list();
				
//				.setProjection(Projections.sum("cantidad"))
//				.setProjection(Projections.groupProperty("alimento.id"))
				
	}

}
