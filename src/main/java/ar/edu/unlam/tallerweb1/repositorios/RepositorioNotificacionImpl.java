package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioNotificacion;

@Repository("repositorioNotificacion")
@Transactional
public class RepositorioNotificacionImpl implements RepositorioNotificacion {

	private SessionFactory sessionFactory;
	private RepositorioUsuario repositorioUsuario;

	@Autowired
	public RepositorioNotificacionImpl(SessionFactory sessionFactory, RepositorioUsuario repositorioUsuario) {
		this.sessionFactory = sessionFactory;
		this.repositorioUsuario = repositorioUsuario;
	}

	@Override
	public void crearNotificacionStock(Notificacion notificacion) {
		Session session = sessionFactory.getCurrentSession();

		session.save(notificacion);

		List<Usuario> empleados = repositorioUsuario.consultarUsuariosEmpleados();

		for (Usuario e : empleados) {
			UsuarioNotificacion usuarioNotificacion = new UsuarioNotificacion();
			usuarioNotificacion.setNotificacion(notificacion);
			usuarioNotificacion.setUsuario(e);
			session.save(usuarioNotificacion);
		}

	}

	@Override
	public List<Notificacion> listarNotificaciones(Long idUsuario) {
		Session session = sessionFactory.getCurrentSession();

		return (List<Notificacion>) session.createCriteria(UsuarioNotificacion.class)
				.add(Restrictions.eq("usuario.id", idUsuario)).createAlias("notificacion", "n")
				.addOrder(Order.desc("n.id"))
				.list();

	}

	@Override
	public Notificacion notificacionPorDetalles(String detalles, String fecha) {
		Session session = sessionFactory.getCurrentSession();
		return (Notificacion) session.createCriteria(Notificacion.class)
				.add(Restrictions.eq("detalles", detalles))
				.add(Restrictions.eq("fecha", fecha))
				.uniqueResult();
	}

	@Override
	public Notificacion notificacionPorId(Long id) {
		Session session = sessionFactory.getCurrentSession();

		return (Notificacion) session.createCriteria(Notificacion.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	@Override
	public void actualizarNotificacion(Notificacion notificacion) {
		Session session = sessionFactory.getCurrentSession();
		session.update(notificacion);
	}

	@Override
	public void crearNotificacionAnimal(Notificacion notificacion) {
		Session session = sessionFactory.getCurrentSession();
		session.save(notificacion);
		List<Usuario> empleados = repositorioUsuario.consultarUsuariosVeterinarios();

		for (Usuario e : empleados) {
			UsuarioNotificacion usuarioNotificacion = new UsuarioNotificacion();
			usuarioNotificacion.setNotificacion(notificacion);
			usuarioNotificacion.setUsuario(e);
			session.save(usuarioNotificacion);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notificacion> listarNotificacionesPendientesPorTitulo(String titulo) {
		return this.sessionFactory.getCurrentSession()
				.createQuery("SELECT n FROM Notificacion n WHERE n.estado = false AND n.titulo = :titulo")
				.setParameter("titulo", titulo)
				.getResultList();
	}

	@Override
	public void crearNotificacionDeVacunaVencida(Notificacion notificacion) {
		Session session = sessionFactory.getCurrentSession();
		session.save(notificacion);
		List<Usuario> empleados = repositorioUsuario.consultarUsuariosVeterinarios();

		for (Usuario e : empleados) {
			UsuarioNotificacion usuarioNotificacion = new UsuarioNotificacion();
			usuarioNotificacion.setNotificacion(notificacion);
			usuarioNotificacion.setUsuario(e);
			session.save(usuarioNotificacion);
		}
	}
}
