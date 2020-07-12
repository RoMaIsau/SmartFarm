package ar.edu.unlam.tallerweb1.controladores;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.mapbox.AnimalEnMapa;
import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUbicacion;

@Controller
public class ControladorUbicacionEnTiempoReal {

	private ServicioUbicacion servicioUbicacion;
	private ServicioNotificacion servicioNotificacion;

	@Autowired
	public ControladorUbicacionEnTiempoReal(ServicioUbicacion servicioUbicacion, ServicioNotificacion servicioNotificacion) {
		this.servicioUbicacion = servicioUbicacion;
		this.servicioNotificacion = servicioNotificacion;
	}

	@RequestMapping("ubicaciones/mapa")
	ModelAndView mostrarMapa() {
		return new ModelAndView("mapaEnTiempoReal");
	}

	@RequestMapping(value = "ubicaciones/ultimas", produces = "application/json")
	@ResponseBody
	public List<AnimalEnMapa> obtenerUltimasPosiciones() {

		List<AnimalUbicacion> ubicaciones = this.servicioUbicacion.obtenerUbicacionesRecientes();

		List<AnimalEnMapa> animalesEnMapa = new LinkedList<>();
		for(AnimalUbicacion animalUbicacion :ubicaciones) {
			AnimalEnMapa animalEnMapa = new AnimalEnMapa();
			animalEnMapa.setId(animalUbicacion.getAnimal().getId());
			animalEnMapa.setLatitud(animalUbicacion.getUltimaUbicacion().getLatitud());
			animalEnMapa.setLongitud(animalUbicacion.getUltimaUbicacion().getLongitud());
			animalEnMapa.setTipoAnimal(animalUbicacion.getAnimal().getTipo().getNombre());
			animalesEnMapa.add(animalEnMapa);
		}

		return animalesEnMapa;
	}

	@RequestMapping("ubicaciones/notificaciones")
	public ModelAndView obtenerNotificaciones() {
		List<Notificacion> notificaciones = this.servicioNotificacion.obtenerNotificacionesPendientesDeAnimalFueraDeCorral();
		ModelMap modelo = new ModelMap();
		modelo.put("notificaciones", notificaciones);
		return new ModelAndView("notificacion", modelo);
	}

	@RequestMapping(value = "ubicaciones/notificacionVista", method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public String marcarNotificacionComoVista(@RequestBody Notificacion notificacion) {
		this.servicioNotificacion.marcarComoVista(notificacion);
		return "OK";
	}
}
