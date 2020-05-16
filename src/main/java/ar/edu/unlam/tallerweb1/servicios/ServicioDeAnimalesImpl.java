package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeGeneros;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeRazas;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeTipos;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ServicioDeAnimalesImpl implements ServicioDeAnimales {

	private RepositorioDeTipos repositorioDeTipos;
	private RepositorioDeRazas repositorioDeRazas;
	private RepositorioDeGeneros repositorioDeGeneros;
	
	@Autowired
	public ServicioDeAnimalesImpl(RepositorioDeTipos repositorioDeTipos, RepositorioDeRazas repositorioDeRazas, 
			RepositorioDeGeneros repositorioDeGeneros) {
		this.repositorioDeTipos = repositorioDeTipos;
		this.repositorioDeRazas = repositorioDeRazas;
		this.repositorioDeGeneros = repositorioDeGeneros;
	}

	@Override
	public List<TipoAnimal> obtenerTiposDeAnimales() {
		return this.repositorioDeTipos.listarDisponibles();
	}

	@Override
	public List<Raza> obtenerRazasPorTipoAnimal(TipoAnimal tipoAnimal) {
		return this.repositorioDeRazas.listarPorTipoAnimal(tipoAnimal);
	}

	@Override
	public List<Genero> obtenerGeneros() {
		return this.repositorioDeGeneros.listar();
	}

}
