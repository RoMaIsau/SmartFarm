package ar.edu.unlam.tallerweb1.servicios;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Gastos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGastos;

@Service
@Transactional
public class ServicioGastosImpl implements ServicioGastos {
	
	@Inject
	private RepositorioGastos repositorioGastos;
	
	@Override
	public List<Gastos> consultarGastosPorUsuario(Long idEncontrado) {
		return repositorioGastos.consultarGastosPorUsuario(idEncontrado);
	}

	@Override
	public List<Gastos> consultarGastos() {
		return repositorioGastos.consultarGastos();
	}

	@Override
	public Long guardarNuevoRegistro(Gastos gastos, Usuario usuario) {
		Double gastoTotal =+ gastos.getGastosAlimenticios();
		gastoTotal =+ gastos.getGastosEmpresariales();
		gastoTotal =+ gastos.getGastosMedicos();
		gastoTotal =+ gastos.getGastosTecnologicos();
		gastos.setGastosTotal(gastoTotal);
		
		Date date = Calendar.getInstance().getTime();  
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		String fechaNac = dateFormat.format(date);  
        gastos.setFecha(fechaNac);
        
        gastos.setUsuario(usuario);
		
		return repositorioGastos.guardarNuevoRegistro(gastos);
	}

}
