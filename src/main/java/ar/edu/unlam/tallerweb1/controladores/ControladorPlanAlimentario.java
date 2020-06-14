package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.formularios.FormularioDeCronogramaDeAlimentacion;
import ar.edu.unlam.tallerweb1.formularios.FormularioEliminarCronograma;
import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.CronogramaDeAlimentacion;
import ar.edu.unlam.tallerweb1.modelo.PlanAlimentario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlimento;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlanAlimentario;

@Controller
public class ControladorPlanAlimentario {

	private ServicioDeAnimales servicioDeAnimales;
	private ServicioAlimento servicioAlimento;
	private ServicioPlanAlimentario servicioPlanAlimentario;

	@Autowired
	public ControladorPlanAlimentario(ServicioDeAnimales servicioDeAnimales, ServicioAlimento servicioAlimento,
			ServicioPlanAlimentario servicioPlanAlimentario) {
		this.servicioDeAnimales = servicioDeAnimales;
		this.servicioAlimento = servicioAlimento;
		this.servicioPlanAlimentario = servicioPlanAlimentario;
	}

	@RequestMapping("animales/planAlimentario")
	public ModelAndView crearPlanAlimentario(@RequestParam("id") Long idAnimal) {

		AnimalDeGranja animal = this.servicioDeAnimales.obtenerPorId(idAnimal);
		PlanAlimentario plan = this.servicioPlanAlimentario.buscarPlanPorAnimal(animal);
		List<CronogramaDeAlimentacion> cronogramDeAlimentacion = this.servicioPlanAlimentario.listarCronograma(plan);

		FormularioDeCronogramaDeAlimentacion formulario = new FormularioDeCronogramaDeAlimentacion();
		formulario.setAnimal(animal);
		formulario.setPlanAlimentario(plan);

		List<Alimento> alimentos = this.servicioAlimento.listarAlimentos();
		formulario.setAlimentos(alimentos);

		ModelMap modelo = new ModelMap();
		modelo.put("formulario", formulario);
		modelo.put("cronograma", cronogramDeAlimentacion);
		return new ModelAndView("planAlimentario", modelo);
	}

	@RequestMapping(value = "animales/agregarCronograma", method = RequestMethod.POST)
	public ModelAndView agregarCronograma(FormularioDeCronogramaDeAlimentacion formulario) {

		CronogramaDeAlimentacion cronograma = new CronogramaDeAlimentacion();
		cronograma.setAlimento(formulario.getItemCronograma().getAlimento());
		cronograma.setCantidad(formulario.getItemCronograma().getCantidad());
		cronograma.setFecha(formulario.getItemCronograma().getFecha());
		cronograma.setHorario(formulario.getItemCronograma().getHorario());
		cronograma.setPlanAlimentario(formulario.getPlanAlimentario());

		this.servicioPlanAlimentario.agregarCronograma(cronograma);

		formulario.limpiarItemCronograma();

		List<CronogramaDeAlimentacion> cronogramDeAlimentacion = this.servicioPlanAlimentario.listarCronograma(formulario.getPlanAlimentario());

		List<Alimento> alimentos = this.servicioAlimento.listarAlimentos();
		formulario.setAlimentos(alimentos);

		ModelMap modelo = new ModelMap();
		modelo.put("formulario", formulario);
		modelo.put("cronograma", cronogramDeAlimentacion);
		return new ModelAndView("detallePlanAlimentario", modelo);
	}

	@RequestMapping(value = "animales/eliminarCronograma", method = RequestMethod.POST)
	public ModelAndView eliminarCronograma(FormularioEliminarCronograma formulario) {

		this.servicioPlanAlimentario.eliminarCronograma(formulario.getIdCronograma());

		PlanAlimentario plan = new PlanAlimentario();
		plan.setId(formulario.getIdPlan());

		List<CronogramaDeAlimentacion> cronograma = this.servicioPlanAlimentario.listarCronograma(plan);
		ModelMap modelo = new ModelMap();
		modelo.put("cronograma", cronograma);
		return new ModelAndView("cronogramaDeAlimentacion", modelo);
	}
}