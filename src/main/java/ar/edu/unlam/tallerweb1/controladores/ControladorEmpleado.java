package ar.edu.unlam.tallerweb1.controladores;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorEmpleado {
	
	@RequestMapping(path = "/indexEmpleado")
	public ModelAndView irAIndexEmpleado(HttpServletRequest request) {
		
		// Verifica que sea un usuario de tipo Empleado, si no lo es, lo redirige al login
		String rol = (String) request.getSession().getAttribute("ROL");
		
		if(rol.equals("Empleado")) {
			return new ModelAndView("indexEmpleado");
		}else {
			return new ModelAndView("redirect:/login");
		}
		
		
	}


}
