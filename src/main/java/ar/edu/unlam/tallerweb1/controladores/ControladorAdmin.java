package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorAdmin {

	@Inject
	private ServicioUsuario servicioUsuario;
	
	/* El path de indexAdmin redirecciona a la primera seccion del sidebar, Usuarios */
	@RequestMapping(path = "/indexAdmin")
	public ModelAndView irAIndexAdmin(HttpServletRequest request) {
		
		// Verifica que sea un usuario de tipo Admin, si no lo es, lo redirige al login
		String rol = (String) request.getSession().getAttribute("ROL");
		
		if(rol.equals("Admin")) {
			return new ModelAndView("redirect:/usuarios");
		}else {
			return new ModelAndView("redirect:/login");
		}
		
		
	}
	
	/* La vista Usuarios muestra todos los usuarios registrados, trayendolos en una lista */
	@RequestMapping(path = "/usuarios")
	public ModelAndView listarUsuario(HttpServletRequest request) {
		
		String rol = (String) request.getSession().getAttribute("ROL");
		
		if(!rol.equals("Admin")) {
			return new ModelAndView("redirect:/login");
		}
		
		ModelMap model = new ModelMap();

		List<Usuario> usuarios = servicioUsuario.listarUsuarios();

		if (usuarios != null) {
			model.put("usuarios", usuarios);
		} else {
			model.put("error", "No hay usuarios registrados");
		}

		return new ModelAndView("usuarios", model);
	}

}
