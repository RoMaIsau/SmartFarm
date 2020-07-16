package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.TipoDeUsuario;

public interface RepositorioTipoDeUsuario {

	TipoDeUsuario consultarRol(String rol);

	List<TipoDeUsuario> ObtenerTodosLosRoles();
	
	void guardarTipoDeUsuario(TipoDeUsuario tipoDeUsuario);
}
