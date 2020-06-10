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
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;

@Controller
public class ControladorVeterinario{

	public List<Notificacion> listarNotificacionesDelVeterinario(HttpServletRequest request) {
		Long idUsuario = (Long) request.getSession().getAttribute("ID");
		List<Notificacion> notificaciones = servicioNotificacion.listarNotificaciones(idUsuario);
		return notificaciones;
	}
	
	@Inject
	private ServicioNotificacion servicioNotificacion;
	
	@RequestMapping(path = "/indexVeterinario")
	public ModelAndView irAIndexVeterinario(HttpServletRequest request) {
		// Verifica que sea un usuario de tipo Veterinario, si no lo es, lo redirige al login
		String rol = (String) request.getSession().getAttribute("ROL");
		
		ModelMap model = new ModelMap();
		model.put("notificaciones", listarNotificacionesDelVeterinario(request));
		
		if(rol.equals("Veterinario")) {
			return new ModelAndView("indexVeterinario", model);
		}else {
			return new ModelAndView("redirect:/login");
		}
	}
	
	
	
}
