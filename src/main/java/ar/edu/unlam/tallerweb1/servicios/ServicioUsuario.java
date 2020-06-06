package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioUsuario {

	Usuario consultarUsuario(Usuario usuario);

	Long registrarUsuario(Usuario usuario);

	List<Usuario> listarUsuarios();

	Usuario consultarUsuarioPorId(Long id);

	void eliminarUsuario(Usuario usuario);

	Usuario consultarUsuarioPorEmail(String email);
  
  List<Usuario> consultarUsuariosEmpleados();
}
