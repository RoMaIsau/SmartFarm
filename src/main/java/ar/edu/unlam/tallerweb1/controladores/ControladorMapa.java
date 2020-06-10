package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Ubicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUbicacion;

@Controller
public class ControladorMapa {

	@Inject
	private ServicioUbicacion servicioUbicacion;
	
	@Inject
	private ServicioNotificacion servicioNotificacion;
	
	@RequestMapping("/mapa")
	public ModelAndView irAMapa(HttpServletRequest request, ModelMap model) {

		String rol = (String) request.getSession().getAttribute("ROL");
		Long idUsuario = (Long) request.getSession().getAttribute("ID");

		if (!rol.equals("Empleado") && !rol.equals("Veterinario") && !rol.equals("")) {
			return new ModelAndView("redirect:/login");
		}
		
		List<Ubicacion> ubicaciones = servicioUbicacion.obtenerUbicaciones();

		model.put("lists", ubicaciones);

		List<Notificacion> notificaciones = servicioNotificacion.listarNotificaciones(idUsuario);
		model.put("notificaciones", notificaciones);

		return new ModelAndView("mapa", model);
	}

}