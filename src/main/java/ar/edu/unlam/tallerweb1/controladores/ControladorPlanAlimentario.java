package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.excepciones.NoSePudoCompletarCronogramaException;
import ar.edu.unlam.tallerweb1.formularios.FormularioDeCronogramaDeAlimentacion;
import ar.edu.unlam.tallerweb1.formularios.FormularioEliminarCronograma;
import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.CronogramaDeAlimentacion;
import ar.edu.unlam.tallerweb1.modelo.PlanAlimentario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlimento;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlanAlimentario;
import ar.edu.unlam.tallerweb1.vista.Mensaje;

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
		this.servicioPlanAlimentario.vencerCronogramasSinCompletar(plan);

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

		CronogramaDeAlimentacion cronograma = new CronogramaDeAlimentacion();
		cronograma.setId(formulario.getIdCronograma());
		this.servicioPlanAlimentario.eliminarCronograma(cronograma);

		PlanAlimentario plan = new PlanAlimentario();
		plan.setId(formulario.getIdPlan());

		List<CronogramaDeAlimentacion> cronogramaCompleto = this.servicioPlanAlimentario.listarCronograma(plan);
		ModelMap modelo = new ModelMap();
		modelo.put("cronograma", cronogramaCompleto);
		return new ModelAndView("cronogramaDeAlimentacion", modelo);
	}

	@RequestMapping("animales/editarCronograma")
	public ModelAndView editarCronograma(@RequestParam("idCronograma") Long idCronograma) {

		CronogramaDeAlimentacion cronograma = this.servicioPlanAlimentario.buscarCronograma(idCronograma);
		List<Alimento> alimentos = this.servicioAlimento.listarAlimentos();

		ModelMap modelo = new ModelMap();
		modelo.put("cronogramaEditable", cronograma);
		modelo.put("alimentos", alimentos);

		return new ModelAndView("modalEditarCronograma", modelo);
	}

	@RequestMapping(value = "animales/editarCronograma", method = RequestMethod.POST)
	public ModelAndView confirmarEdicionDeCronograma(CronogramaDeAlimentacion cronograma) {
		this.servicioPlanAlimentario.actualizarCronograma(cronograma);
		List<CronogramaDeAlimentacion> cronogramDeAlimentacion = this.servicioPlanAlimentario.listarCronograma(cronograma.getPlanAlimentario());

		ModelMap modelo = new ModelMap();
		modelo.put("cronograma", cronogramDeAlimentacion);

		return new ModelAndView("cronogramaDeAlimentacion", modelo);
	}

	@RequestMapping(value = "animales/terminarCronograma", method = RequestMethod.POST)
	public ModelAndView terminarCronograma(CronogramaDeAlimentacion cronograma) {

		ModelMap modelo = new ModelMap();
		Mensaje mensaje;

		try {

			this.servicioPlanAlimentario.terminarCronograma(cronograma);
			mensaje = Mensaje.crearMensajeDeExito()
						.conTitulo("Operación realizada con éxito")
						.conDetalle("El cronograma fue completado correctamente");

		}catch (NoSePudoCompletarCronogramaException e) {
			mensaje = Mensaje.crearMensajeDeError()
					.conTitulo("Ocurrió un error completando el cronograma")
					.conDetalle(e.getMessage());
		}

		List<CronogramaDeAlimentacion> cronogramDeAlimentacion = this.servicioPlanAlimentario.listarCronograma(cronograma.getPlanAlimentario());
		modelo.put("cronograma", cronogramDeAlimentacion);
		modelo.put("mensaje", mensaje);
		return new ModelAndView("cronogramaDeAlimentacion", modelo);
	}
}