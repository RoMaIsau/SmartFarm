package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorVeterinario {
	
	@RequestMapping(path = "/indexVeterinario")
	public ModelAndView irAIndexVeterinario(HttpServletRequest request) {
		
		// Verifica que sea un usuario de tipo Veterinario, si no lo es, lo redirige al login
		String rol = (String) request.getSession().getAttribute("ROL");
		
		if(rol.equals("Veterinario")) {
			return new ModelAndView("indexVeterinario");
		}else {
			return new ModelAndView("redirect:/login");
		}
		
	}
}
