package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.TipoDeUsuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoDeUsuario;

@Service
@Transactional
public class ServicioTipoDeUsuarioImpl implements ServicioTipoDeUsuario {
	
	@Inject
	private RepositorioTipoDeUsuario repositorioTipoDeUsuario;
	
	@Override
	public TipoDeUsuario consultarRol(String rol) {
		return repositorioTipoDeUsuario.consultarRol(rol);
	}
	
}

