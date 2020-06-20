package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("servicioOperacion")
@Transactional
public class ServicioOperacionImpl implements ServicioOperacion {

	@Override
	public Double sumar(Double uno, Double dos) {
		 Double res= uno + dos;
		return res;
	}

	@Override
	public Double multiplicar(Double uno, Double dos) {
		 Double res= uno * dos;
			return res;
	}

	
}
