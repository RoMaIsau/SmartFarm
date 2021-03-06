package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Gastos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository
@Transactional
public class RepositorioGastosImpl implements RepositorioGastos {
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public RepositorioGastosImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Gastos> consultarGastos() {
		return sessionFactory.getCurrentSession().createCriteria(Gastos.class).list();
	}
	
	@Override
	public List<Gastos> consultarGastosPorUsuario(Usuario usuario) {
		return sessionFactory.getCurrentSession().createCriteria(Gastos.class)
			.add(Restrictions.eq("usuario", usuario))
			.list();
	}

	@Override
	public Long guardarNuevoRegistro(Gastos gastos) {
		return (Long) sessionFactory.getCurrentSession().save(gastos);
	}

	@Override
	public Gastos consultaGastosPorID(Long id) {
		return (Gastos) sessionFactory.getCurrentSession().get(Gastos.class, id);
	}
	
	@Override
	public void eliminarGastos(Gastos gastos) {
		sessionFactory.getCurrentSession().delete(gastos);
	}

	@Override
	public void modificarGasto(Gastos gastosActuales) {
		sessionFactory.getCurrentSession().saveOrUpdate(gastosActuales);
	}

	@Override
	public List<Gastos> consultarGastosEnTotalPorTipo() {
		
		return (List<Gastos>) sessionFactory.getCurrentSession().createCriteria(Gastos.class)
				.createAlias("tipoDeGasto", "tipo")
				.setProjection(Projections.projectionList()
						.add(Projections.property("tipoDeGasto.id"), "id")
			            .add(Projections.sqlProjection("sum(monto) as monto", new String[] {"monto"}, new Type[] {StandardBasicTypes.DOUBLE}))
			            .add(Projections.groupProperty("tipoDeGasto.id")))
				.addOrder(Order.asc("tipoDeGasto.id"))
				.setResultTransformer(Transformers.aliasToBean(Gastos.class))
				.list();
		
	}
	
	
}
