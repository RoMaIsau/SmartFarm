package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.GanadoVacuno;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGanado;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioUsuario;

@Service("servicioGanado")
@Transactional
public class ServicioGanadoImpl implements ServicioGanado {
	
	private RepositorioGanado servicioGanadoDao;

	@Autowired
	public ServicioGanadoImpl(RepositorioGanado servicioGanadoDao){
		this.servicioGanadoDao = servicioGanadoDao;
	}

	@Override
	public List<GanadoVacuno> listar() {
		// TODO Auto-generated method stub
		return servicioGanadoDao.listar();
	}

	@Override
	public GanadoVacuno ver(Long id) {
		// TODO Auto-generated method stub
		return servicioGanadoDao.ver(id);
	}

	@Override
	public void guardar(GanadoVacuno ganado) {
		// TODO Auto-generated method stub
		servicioGanadoDao.guardar(ganado);
		
	}

}
