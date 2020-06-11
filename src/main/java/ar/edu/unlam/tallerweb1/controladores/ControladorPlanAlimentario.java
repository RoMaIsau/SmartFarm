package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.formularios.FormularioDeCronogramaDeAlimentacion;
import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlimento;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;

@Controller
public class ControladorPlanAlimentario {

	private ServicioDeAnimales servicioDeAnimales;
	private ServicioAlimento servicioAlimento;

	@Autowired
	public ControladorPlanAlimentario(ServicioDeAnimales servicioDeAnimales, ServicioAlimento servicioAlimento) {
		this.servicioDeAnimales = servicioDeAnimales;
		this.servicioAlimento = servicioAlimento;
	}

	@RequestMapping("animales/planAlimentario")
	public ModelAndView crearPlanAlimentario(@RequestParam("id") Long idAnimal) {
		AnimalDeGranja animal = this.servicioDeAnimales.obtenerPorId(idAnimal);
		FormularioDeCronogramaDeAlimentacion formulario = new FormularioDeCronogramaDeAlimentacion();
		formulario.setAnimal(animal);
		List<Alimento> alimentos = this.servicioAlimento.listarAlimentos();

		ModelMap modelo = new ModelMap();
		modelo.put("animal", animal);
		modelo.put("formulario", formulario);
		modelo.put("alimentos", alimentos);
		return new ModelAndView("planAlimentario", modelo);
	}
}