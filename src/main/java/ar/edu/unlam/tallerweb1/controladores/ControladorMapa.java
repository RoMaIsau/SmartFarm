package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Ubicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUbicacion;

@Controller
public class ControladorMapa {

	@Inject
	private ServicioUbicacion servicioUbicacion;

	@RequestMapping("/mapa")
	public ModelAndView irAMapa(HttpServletRequest request, ModelMap model) {

		String rol = (String) request.getSession().getAttribute("ROL");

		if (!rol.equals("Admin") && !rol.equals("Empleado") && !rol.equals("")) {
			return new ModelAndView("redirect:/login");
		}

		List<Ubicacion> ubicaciones = servicioUbicacion.obtenerUbicaciones();

		model.put("lists", ubicaciones);

		return new ModelAndView("mapa", model);
	}

}