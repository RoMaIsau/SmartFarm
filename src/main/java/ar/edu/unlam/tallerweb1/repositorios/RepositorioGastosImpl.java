package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

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
	public List<Gastos> consultarGastosPorUsuario(Long idEncontrado) {
		return sessionFactory.getCurrentSession().createCriteria(Gastos.class)
			.createAlias("usuario", "usuariojoin")
			.add(Restrictions.eq("usuariojoin.id", idEncontrado))
			.list();
	}

	@Override
	public Long guardarNuevoRegistro(Gastos gastos) {
		return (Long) sessionFactory.getCurrentSession().save(gastos);
	}
	
	
	
}
