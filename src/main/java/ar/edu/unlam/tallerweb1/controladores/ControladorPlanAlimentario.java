package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;

@Controller
public class ControladorPlanAlimentario {
	private ServicioDeAnimales servicioDeAnimales;

	@Autowired
	public ControladorPlanAlimentario(ServicioDeAnimales servicioDeAnimales) {
		this.servicioDeAnimales = servicioDeAnimales;
	}

	@RequestMapping("animales/planAlimentario")
		public ModelAndView crearPlanAlimentario(@RequestParam("id") Long idAnimal) {
			AnimalDeGranja animal = this.servicioDeAnimales.obtenerPorId(idAnimal);
			
			ModelMap modelo = new ModelMap();
			modelo.put("animal", animal);
			return new ModelAndView("planAlimentario", modelo);
	}
}