package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.CronogramaDeAlimentacion;
import ar.edu.unlam.tallerweb1.modelo.PlanAlimentario;

@Repository
public class RepositorioPlanAlimentarioImpl implements RepositorioPlanAlimentario {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioPlanAlimentarioImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void guardarPlan(PlanAlimentario plan) {
		this.sessionFactory.getCurrentSession().save(plan);
	}

	@Override
	public void guardarItemDeCronograma(CronogramaDeAlimentacion itemCronograma) {
		this.sessionFactory.getCurrentSession().save(itemCronograma);
	}

	@Override
	public PlanAlimentario buscarPlanPorAnimal(AnimalDeGranja animal) {
		return (PlanAlimentario) this.sessionFactory.getCurrentSession()
					.createCriteria(PlanAlimentario.class)
					.add(Restrictions.eq("animal", animal))
					.uniqueResult();
	}

	@Override
	public List<CronogramaDeAlimentacion> listarCronograma(PlanAlimentario plan) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(CronogramaDeAlimentacion.class);
		criteria.add(Restrictions.eq("planAlimentario", plan));
		criteria.addOrder(Order.desc("estado"));
		criteria.addOrder(Order.asc("fecha"));
		criteria.addOrder(Order.asc("horario"));
		return criteria.list();
	}

	@Override
	public void eliminarCronograma(CronogramaDeAlimentacion cronograma) {
		this.sessionFactory.getCurrentSession().delete(cronograma);
	}

	@Override
	public CronogramaDeAlimentacion buscarCronograma(Long idCronograma) {
		return (CronogramaDeAlimentacion) this.sessionFactory.getCurrentSession()
				.createCriteria(CronogramaDeAlimentacion.class)
				.add(Restrictions.eq("id", idCronograma))
				.uniqueResult();
	}

	@Override
	public void actualizarCronograma(CronogramaDeAlimentacion cronograma) {
		this.sessionFactory.getCurrentSession().update(cronograma);
	}

	@Override
	public void eliminarPlan(PlanAlimentario plan) {
		this.sessionFactory.getCurrentSession().delete(plan);
	}
}
