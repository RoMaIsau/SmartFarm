package ar.edu.unlam.tallerweb1.servicios;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Implementacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.servicios
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateCOntext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioUsuario")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	private RepositorioUsuario servicioUsuarioDao;

	@Autowired
	public ServicioUsuarioImpl(RepositorioUsuario servicioUsuarioDao){
		this.servicioUsuarioDao = servicioUsuarioDao;
	}

	@Override
	public Usuario consultarUsuario (Usuario usuario) {
		return servicioUsuarioDao.consultarUsuario(usuario);
	}

	@Override
	public Long registrarUsuario(Usuario usuario) {
		Date myDate = new Date();
		usuario.setFechaAlta(new java.text.SimpleDateFormat("dd-MM-yyyy").format(myDate));
		return (Long) servicioUsuarioDao.registrarUsuario(usuario);
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return (List<Usuario>) servicioUsuarioDao.listarUsuarios();
	}

	@Override
	public Usuario consultarUsuarioPorId(Long id) {
		return (Usuario) servicioUsuarioDao.consultarUsuarioPorId(id);
	}

	@Override
	public void eliminarUsuario(Usuario usuario) {
		servicioUsuarioDao.eliminarUsuario(usuario);
		
	}

}
