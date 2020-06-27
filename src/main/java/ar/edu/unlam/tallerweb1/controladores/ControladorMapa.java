package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.AnimalUbicacion;
import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlimento;
import ar.edu.unlam.tallerweb1.servicios.ServicioAnimalUbicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;
import ar.edu.unlam.tallerweb1.servicios.ServicioUbicacion;

@Controller
public class ControladorMapa {

	@Inject
	private ServicioUbicacion servicioUbicacion;
	
	@Inject
	private ServicioNotificacion servicioNotificacion;
	
	@Inject
	private ServicioDeAnimales servicioDeAnimales;
	@Inject
	private ServicioAnimalUbicacion servicioAnimalUbicacion;
	@Inject 
	private ServicioAlimento servicioAlimento;

	@RequestMapping("/mapa")
	public ModelAndView irAMapa(HttpServletRequest request, ModelMap model) {

		String rol = (String) request.getSession().getAttribute("ROL");
		Long idUsuario = (Long) request.getSession().getAttribute("ID");

		if (!rol.equals("Empleado") && !rol.equals("Veterinario") && !rol.equals("")) {
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
		List<AnimalUbicacion> animalUbicacion = servicioAnimalUbicacion.obtenerPorIdAnimal(idAnimal);
		List<Alimento> alimentos = servicioAlimento.listarAlimentosConsumidosPorAnimal(idAnimal);
		
		model.put("animal", animal);
		model.put("animalUbicacion", animalUbicacion);
		model.put("alimentos", alimentos);
		
		return new ModelAndView("verAnimal", model);
	}

}