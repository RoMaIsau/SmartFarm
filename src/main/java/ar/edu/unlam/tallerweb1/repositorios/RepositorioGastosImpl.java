package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Repository;

import com.mysql.cj.xdevapi.UpdateParams;

import ar.edu.unlam.tallerweb1.modelo.Gastos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository
@Transactional
public class RepositorioGastosImpl implements RepositorioGastos {
	
	@Inject
	private SessionFactory sessionFactory;
	
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
	
	
}
