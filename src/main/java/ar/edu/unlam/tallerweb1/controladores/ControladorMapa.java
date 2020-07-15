package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
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
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.UbicacionesCentrales;

import ar.edu.unlam.tallerweb1.modelo.Vacunar;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlimento;
import ar.edu.unlam.tallerweb1.servicios.ServicioAnimalUbicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUbicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUbicacionesCentrales;
//import ar.edu.unlam.tallerweb1.servicios.ServicioUbicacionesCentrales;
import ar.edu.unlam.tallerweb1.servicios.ServicioVacunas;

@Controller
public class ControladorMapa {

	private ServicioUbicacion servicioUbicacion;
	private ServicioNotificacion servicioNotificacion;
	private ServicioDeAnimales servicioDeAnimales;
	private ServicioAnimalUbicacion servicioAnimalUbicacion;
	private ServicioAlimento servicioAlimento;
	private ServicioVacunas servicioVacunas;

	@Autowired
	public ControladorMapa(ServicioUbicacion servicioUbicacion, ServicioNotificacion servicioNotificacion,
			ServicioDeAnimales servicioDeAnimales, ServicioAnimalUbicacion servicioAnimalUbicacion,
			ServicioAlimento servicioAlimento, ServicioVacunas servicioVacunas) {
		this.servicioUbicacion = servicioUbicacion;
		this.servicioNotificacion = servicioNotificacion;
		this.servicioDeAnimales = servicioDeAnimales;
		this.servicioAnimalUbicacion = servicioAnimalUbicacion;
		this.servicioAlimento = servicioAlimento;
		this.servicioVacunas = servicioVacunas;
	}

	@Inject
	private ServicioUbicacionesCentrales servicioUbicacionesCentrales;

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

	@RequestMapping(path = "/verAnimal")
	public ModelAndView verAnimal(@RequestParam("id") Long idAnimal) {

		ModelMap model = new ModelMap();

		AnimalDeGranja animal = servicioDeAnimales.obtenerPorId(idAnimal);
		AnimalUbicacion ubicacion = servicioAnimalUbicacion.obtenerUbicacionAnimal(idAnimal);
		List<AnimalUbicacion> animalUbicacion = servicioAnimalUbicacion.obtenerPorIdAnimal(idAnimal);
		List<Alimento> alimentos = servicioAlimento.listarAlimentosConsumidosPorAnimal(idAnimal);
		List<Vacunar> vacunasAplicadas = servicioVacunas.obtenerVacunasAplicadas(idAnimal);

		model.put("animal", animal);
		model.put("ubicacion", ubicacion);
		model.put("animalUbicacion", animalUbicacion);
		model.put("alimentos", alimentos);
		model.put("vacunasAplicadas", vacunasAplicadas);

		return new ModelAndView("verAnimal", model);
	}

	@RequestMapping(value = "/cambiarcoordenadas")
	public ModelAndView irAModificarCoordenadas(HttpServletRequest request, ModelMap model) {
		String rol = (String) request.getSession().getAttribute("ROL");
		Long idUsuario = (Long) request.getSession().getAttribute("ID");
		if (rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}

		List<Notificacion> notificaciones = servicioNotificacion.listarNotificaciones(idUsuario);
		model.put("notificaciones", notificaciones);

		UbicacionesCentrales ubicacionesCentrales = servicioUbicacionesCentrales.obtenerUbicacionesCentrales();
		model.put("ubicacionesCentrales", ubicacionesCentrales);

		List<TipoAnimal> tipos = servicioDeAnimales.obtenerTiposDeAnimales();
		model.put("tiposDeAnimales", tipos);

		return new ModelAndView("mapaCoordenadaAModificar", model);
	}

	@RequestMapping(value = "/validarcambiodecoordenadas")
	public ModelAndView validarCoordenadasNuevas(HttpServletRequest request,
			@RequestParam(value = "Longitud") Double longitud, @RequestParam(value = "Latitud") Double latitud,
			@RequestParam(value = "tipoDeAnimal") String tipoDeAnimal) {
		ModelMap model = new ModelMap();
		String rol = (String) request.getSession().getAttribute("ROL");
		Long idUsuario = (Long) request.getSession().getAttribute("ID");
		if (rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}

		List<Notificacion> notificaciones = servicioNotificacion.listarNotificaciones(idUsuario);
		model.put("notificaciones", notificaciones);
		UbicacionesCentrales ubicacionesCentrales = servicioUbicacionesCentrales.obtenerUbicacionesCentrales();
		model.put("ubicacionesCentrales", ubicacionesCentrales);
		List<TipoAnimal> tipos = servicioDeAnimales.obtenerTiposDeAnimales();
		model.put("tiposDeAnimales", tipos);

		if (longitud == null || latitud == null) {
			model.put("error", "Debe elegir latitud y longitud v�lidas.");
			return new ModelAndView("mapaCoordenadaAModificar", model);
		}
		int valor = 0;
		for (TipoAnimal t : tipos) {
			if (tipoDeAnimal.equals(t.getNombre())) {
				valor = 1;
			}
		}
		if (valor == 0) {
			model.put("error", "Elija un tipo de animal v�lido.");
			return new ModelAndView("mapaCoordenadaAModificar", model);
		}

		switch (tipoDeAnimal) {
		case "CAPRINO":
			servicioUbicacionesCentrales.caprinoModificarCoordenadas(latitud, longitud);
			break;
		case "EQUINO":
			servicioUbicacionesCentrales.equinoModificarCoordenadas(latitud, longitud);
			break;
		case "OVINO":
			servicioUbicacionesCentrales.ovinoModificarCoordenadas(latitud, longitud);
			break;
		case "PORCINO":
			servicioUbicacionesCentrales.porcinoModificarCoordenadas(latitud, longitud);
			break;
		case "VACUNO":
			servicioUbicacionesCentrales.vacunoModificarCoordenadas(latitud, longitud);
			break;
		}

		ubicacionesCentrales = servicioUbicacionesCentrales.obtenerUbicacionesCentrales();
		model.put("ubicacionesCentrales", ubicacionesCentrales);

		model.put("mensaje", "Ubicaci�n modificada exitosamente.");
		return new ModelAndView("mapaCoordenadaAModificar", model);
	}

}