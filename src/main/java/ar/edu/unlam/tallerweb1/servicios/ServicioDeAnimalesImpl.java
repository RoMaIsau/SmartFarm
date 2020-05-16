package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeRazas;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ServicioDeAnimalesImpl implements ServicioDeAnimales {

	private RepositorioDeRazas repositorioDeRazas;
	
	@Autowired
	public ServicioDeAnimalesImpl(RepositorioDeRazas repositorioDeRazas) {
		this.repositorioDeRazas = repositorioDeRazas;
	}

	@Override
	public List<Raza> obtenerRazasPorTipoAnimal(TipoAnimal tipoAnimal) {
		return this.repositorioDeRazas.listarPorTipoAnimal(tipoAnimal);
	}

}
