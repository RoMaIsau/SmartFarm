package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeTipos;

@Service
@Transactional(value = TxType.SUPPORTS)
public class ServicioDeTiposDeAnimalesImpl implements ServicioDeTiposDeAnimales {

	private RepositorioDeTipos repositorioDeTipos;
	
	@Autowired
	public ServicioDeTiposDeAnimalesImpl(RepositorioDeTipos repositorioDeTipos) {
		this.repositorioDeTipos = repositorioDeTipos;
	}

	@Override
	public List<TipoAnimal> obtenerDisponibles() {
		
		return this.repositorioDeTipos.listarDisponibles();
	}

}
