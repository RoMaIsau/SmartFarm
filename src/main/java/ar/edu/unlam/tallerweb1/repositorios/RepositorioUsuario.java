package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Repositorio de Usuarios.
public interface RepositorioUsuario {
	
	Usuario consultarUsuario (Usuario usuario);

	Long registrarUsuario(Usuario usuario);

	List<Usuario> listarUsuarios();

	Usuario consultarUsuarioPorId(Long id);

	void eliminarUsuario(Usuario usuario);

	Usuario consultarUsuarioPorEmail(String email);
}
