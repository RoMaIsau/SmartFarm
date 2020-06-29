package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlimento;
import ar.edu.unlam.tallerweb1.servicios.ServicioAnimalUbicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUbicacion;

@Controller
public class ControladorMapa {

	private ServicioUbicacion servicioUbicacion;
	private ServicioNotificacion servicioNotificacion;
	private ServicioDeAnimales servicioDeAnimales;
	private ServicioAnimalUbicacion servicioAnimalUbicacion;
	private ServicioAlimento servicioAlimento;

	@Autowired
	public ControladorMapa(ServicioUbicacion servicioUbicacion, ServicioNotificacion servicioNotificacion,
			ServicioDeAnimales servicioDeAnimales, ServicioAnimalUbicacion servicioAnimalUbicacion,
			ServicioAlimento servicioAlimento) {
		this.servicioUbicacion = servicioUbicacion;
		this.servicioNotificacion = servicioNotificacion;
		this.servicioDeAnimales = servicioDeAnimales;
		this.servicioAnimalUbicacion = servicioAnimalUbicacion;
		this.servicioAlimento = servicioAlimento;
	}

	@RequestMapping("/mapa")
	public ModelAndView irAMapa(HttpServletRequest request, ModelMap model) {

		String rol = (String) request.getSession().getAttribute("ROL");
		Long idUsuario = (Long) request.getSession().getAttribute("ID");

		if (rol.equals(null)) {
			return new ModelAndView("redirect:/login");
		}

		List<AnimalUbicacion> animalesUbicaciones = servicioUbicacion.obtenerUbicaciones();

		model.put("lista", animalesUbicaciones);

		List<Notificacion> notificaciones = servicioNotificacion.listarNotificaciones(idUsuario);
		model.put("notificaciones", notificaciones);

		return new ModelAndView("mapa", model);
	}
	
	@RequestMapping (path = "/verAnimal")
	public ModelAndView verAnimal(@RequestParam ("id") Long idAnimal) {
		
		ModelMap model = new ModelMap();
		
		AnimalDeGranja animal = servicioDeAnimales.obtenerPorId(idAnimal);
		AnimalUbicacion ubicacion = servicioAnimalUbicacion.obtenerUbicacionAnimal(idAnimal);
		List<AnimalUbicacion> animalUbicacion = servicioAnimalUbicacion.obtenerPorIdAnimal(idAnimal);
		List<Alimento> alimentos = servicioAlimento.listarAlimentosConsumidosPorAnimal(idAnimal);
		
		model.put("animal", animal);
		model.put("ubicacion", ubicacion);
		model.put("animalUbicacion", animalUbicacion);
		model.put("alimentos", alimentos);
		
		return new ModelAndView("verAnimal", model);
	}

}