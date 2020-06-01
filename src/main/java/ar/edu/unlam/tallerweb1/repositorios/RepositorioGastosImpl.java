package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Gastos;

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
	public List<Gastos> consultarGastosPorUsuario(String idEncontrado) {
		return sessionFactory.getCurrentSession().createCriteria(Gastos.class)
			.createAlias("usuario", "usuariojoin")
			.add(Restrictions.eq("usuariojoin.id", idEncontrado))
			.list();
	}
	
	
	
}
