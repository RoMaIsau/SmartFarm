package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.UsuarioNotificacion;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.transaction.Transactional;

// implelemtacion del repositorio de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("repositorioUsuario")
@Transactional
public class RepositorioUsuarioImpl implements RepositorioUsuario {

	// Como todo repositorio maneja acciones de persistencia, normalmente estará
	// inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	private SessionFactory sessionFactory;

	@Autowired
	public RepositorioUsuarioImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Usuario consultarUsuario(Usuario usuario) {

		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que
		// invoca a este metodo y se crea un criterio
		// de busqueda de Usuario donde el email y password sean iguales a los del
		// objeto recibido como parametro
		// uniqueResult da error si se encuentran más de un resultado en la busqueda.
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("email", usuario.getEmail()))
				.add(Restrictions.eq("password", usuario.getPassword()))
				.uniqueResult();
	}

	@Override
	public Long registrarUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();

		return (Long) session.save(usuario);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		final Session session = sessionFactory.getCurrentSession();

		return (List<Usuario>) session.createCriteria(Usuario.class).add(Restrictions.ne("rol", "Admin")).list();
	}

	@Override
	public Usuario consultarUsuarioPorId(Long id) {
		final Session session = sessionFactory.getCurrentSession();

		return session.get(Usuario.class, id);
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();

		List<UsuarioNotificacion> usuarioNotificacion = (List<UsuarioNotificacion>) session
				.createCriteria(UsuarioNotificacion.class).add(Restrictions.eq("usuario.id", usuario.getId())).list();
		for (UsuarioNotificacion un : usuarioNotificacion) {
			session.delete(un);
		}

		Usuario usuarioABorrar = (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("id", usuario.getId()))
				.uniqueResult();
		session.delete(usuarioABorrar);
	}
	
	@Override
	public Usuario consultarUsuarioPorEmail(String email) {
		return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
		.add(Restrictions.eq("email", email)).uniqueResult();
	}
  
	public List<Usuario> consultarUsuariosEmpleados() {
		Session session = sessionFactory.getCurrentSession();
		return (List<Usuario>) session.createCriteria(Usuario.class).add(Restrictions.eq("rol", "Empleado")).list();
	}
}
