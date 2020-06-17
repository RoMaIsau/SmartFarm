package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.TipoDeUsuario;

public interface ServicioTipoDeUsuario {

	TipoDeUsuario consultarRol(String rol);

	List<TipoDeUsuario> ObtenerTodosLosRoles();

	;
	
}
