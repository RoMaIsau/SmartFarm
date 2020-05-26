package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("repositorioNotificacion")
public class RepositorioNotificacionImpl implements RepositorioNotificacion {

	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioNotificacionImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void crearNotificacionStock(Notificacion notificacion) {
		Session session = sessionFactory.getCurrentSession();

		List<Usuario> usuarios = session.createCriteria(Usuario.class).add(Restrictions.eq("rol", "Empleado")).list();

		notificacion.setUsuarios(usuarios);
		session.save(notificacion);

	}

	@Override
	public List<Notificacion> listarNotificaciones(Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		return (List<Notificacion>) session.createCriteria(Notificacion.class).createAlias("usuarios", "U")
				.add(Restrictions.eq("U.id", usuario.getId())).list();

	}

}
