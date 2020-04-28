package ar.edu.unlam.tallerweb1.smartfarm.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorDeInicio {
	
	@RequestMapping("/")
	public ModelAndView irAInicio() {
		ModelMap modelo = new ModelMap();
		modelo.put("status", "OK");
		return new ModelAndView("index", modelo);
	}

}
