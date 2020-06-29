package ar.edu.unlam.tallerweb1.controladores;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unlam.tallerweb1.modelo.Posicion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUbicacion;

@RestController
@RequestMapping("api-posiciones")
public class ControladorDePosiciones {

	private ServicioUbicacion servicioUbicacion;	
	private Logger logger = LoggerFactory.getLogger(ControladorDePosiciones.class);

	@Autowired
	public ControladorDePosiciones(ServicioUbicacion servicioUbicacion) {
		this.servicioUbicacion = servicioUbicacion;
	}

	@RequestMapping(value = "escuchar", method = RequestMethod.POST, consumes = "application/json")
	public void escucharPosicion(@RequestBody Posicion posicion) {
		logger.info("Posicion recibida: {}", posicion);
		this.servicioUbicacion.registrar(posicion);
	}

}
