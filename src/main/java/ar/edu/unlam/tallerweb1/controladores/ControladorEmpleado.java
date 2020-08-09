package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.TipoAlimento;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioDeAnimales;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioHistoriaClinica;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlimento;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;
import ar.edu.unlam.tallerweb1.servicios.ServicioHistoriaClinica;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.validadores.ValidadorDeAnimalDeGranja;

@Controller
public class ControladorEmpleado {

	@Inject
	private RepositorioHistoriaClinica repositorioHistoriaClinica;

	@Inject
	private RepositorioDeAnimales repositorioDeAnimales;
	
	private ServicioHistoriaClinica servicioHistoriaClinica;
	private ServicioDeAnimales servicioDeAnimales;
	private ServicioAlimento servicioAlimento;
	private ServicioNotificacion servicioNotificacion;
	private ServicioUsuario servicioUsuario;
	private ValidadorDeAnimalDeGranja validadorDeAnimales;

	@Autowired
	public ControladorEmpleado(ServicioDeAnimales servicioDeAnimales, ServicioAlimento servicioAlimento,
			ServicioUsuario servicioUsuario, ServicioNotificacion servicioNotificacion,
			ValidadorDeAnimalDeGranja validadorDeAnimales, ServicioHistoriaClinica servicioHistoriaClinica) {
		this.servicioDeAnimales = servicioDeAnimales;
		this.servicioAlimento = servicioAlimento;
		this.servicioNotificacion = servicioNotificacion;
		this.servicioUsuario = servicioUsuario;
		this.validadorDeAnimales = validadorDeAnimales;
		this.servicioHistoriaClinica = servicioHistoriaClinica;
	}
	
	public List<Notificacion> listarNotificacionesDelEmpleado(HttpServletRequest request) {
		Long idUsuario = (Long) request.getSession().getAttribute("ID");
		List<Notificacion> notificaciones = servicioNotificacion.listarNotificaciones(idUsuario);
		return notificaciones;
	}
	
	@RequestMapping(path = "/indexEmpleado")
	public ModelAndView irAIndexEmpleado(HttpServletRequest request) {

		// Verifica que sea un usuario de tipo Empleado, si no lo es, lo redirige al
		// login
		ModelAndView redirect = new ModelAndView("redirect:/login");

		String rol = (String) request.getSession().getAttribute("ROL");

		if (rol.equals("Empleado")) {
			redirect = new ModelAndView("redirect:/animales");
		}

		return redirect;
	}

	@RequestMapping(path = "/animales")
	public ModelAndView listarAnimales(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView("redirect:/login");

		String rol = (String) request.getSession().getAttribute("ROL");

		if (rol.equals("Empleado") || rol.equals("Admin")) {

			ModelMap modelo = new ModelMap();
			
			List<AnimalDeGranja> animales = this.servicioDeAnimales.obtenerTodos();
			modelo.put("animales", animales);
			
			modelo.put("notificaciones", listarNotificacionesDelEmpleado(request));
			
			modelAndView = new ModelAndView("animales", modelo);
		}

		return modelAndView;
	}

	@RequestMapping(path = "/stock")
	public ModelAndView irAStock(HttpServletRequest request) {

		String rol = (String) request.getSession().getAttribute("ROL");

		if (!rol.equals("Empleado")) {
			return new ModelAndView("redirect:/login");

		}

		ModelMap model = new ModelMap();
		servicioNotificacion.crearNotificacionStock();
		

		List<Alimento> alimentos = servicioAlimento.listarAlimentos();

		List<TipoAlimento> tiposAlimentos = servicioAlimento.obtenerTiposDeAlimentos();
		
		model.put("notificaciones", listarNotificacionesDelEmpleado(request));
		model.put("alimentos", alimentos);
		model.put("tipos", tiposAlimentos);
		
		return new ModelAndView("stock", model);
	}

	@RequestMapping(path = "registrarAlimento")
	public ModelAndView irARegistroAlimento(HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("ROL");

		if (!rol.equals("Empleado")) {
			return new ModelAndView("redirect:/login");
		}

		ModelMap model = new ModelMap();

		List<TipoAlimento> tiposAlimentos = this.servicioAlimento.obtenerTiposDeAlimentos();
		Alimento alimento = new Alimento();

		model.put("tiposAlimentos", tiposAlimentos);
		model.put("alimento", alimento);
		
		model.put("notificaciones", listarNotificacionesDelEmpleado(request));
		
		return new ModelAndView("registroAlimento", model);

	}

	@RequestMapping(path = "/validar-registro-alimento")
	public ModelAndView validarRegistroAlimento(HttpServletRequest request, @ModelAttribute("alimento") Alimento alimento) {
		ModelMap model = new ModelMap();

		Alimento alimentoBuscado = servicioAlimento.consultarAlimento(alimento);

		if (alimentoBuscado != null) {
			model.put("error", "Alimento ya registrado");
		} else {
			if (servicioAlimento.registrarAlimento(alimento) != null) {
				model.put("mensaje", "Alimento registrado correctamente");
			} else {
				model.put("mensaje", "No se pudo registrar el alimento");
			}
		}
		List<TipoAlimento> tiposAlimentos = this.servicioAlimento.obtenerTiposDeAlimentos();
		model.put("tiposAlimentos", tiposAlimentos);

		model.put("notificaciones", listarNotificacionesDelEmpleado(request));
		
		return new ModelAndView("registroAlimento", model);
	}

	@RequestMapping(path = "/cambiarStockMinimo")
	public ModelAndView cambiarStockMinimo(@RequestParam(name = "id") Long id,
			@RequestParam(name = "stockMinimo") Double stock) {

		Alimento alimento = servicioAlimento.consultarAlimentoPorId(id);

		alimento.setStockMinimo(stock);

		servicioAlimento.actualizarAlimento(alimento);

		return new ModelAndView("redirect:/stock");
	}
	
	@RequestMapping(path = "/actualizarNotificacion")
	public ModelAndView actualizarNotificacion(@RequestParam(name = "id") Long id, HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("ROL");
		
		Notificacion notificacion = servicioNotificacion.notificacionPorId(id);
		notificacion.setEstado(true);
		servicioNotificacion.actualizarNotificacion(notificacion);
		
		switch (rol) {
		case "Veterinario": return new ModelAndView("redirect:/indexVeterinario");
		case "Empleado": return new ModelAndView("redirect:/stock");
		}
		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(value = "/animales/registrar")
	public ModelAndView irAFormularioDeRegistroDeAnimales(HttpServletRequest request, ModelMap modelo) {
		List<TipoAnimal> tiposDeAnimales = this.servicioDeAnimales.obtenerTiposDeAnimales();
		List<Genero> generos = this.servicioDeAnimales.obtenerGeneros();

		List<Raza> razas;
		AnimalDeGranja animal = null;
		if (!modelo.containsAttribute("animal")) {
			razas = this.servicioDeAnimales.obtenerRazasPorTipoAnimal(tiposDeAnimales.get(0));

			animal = new AnimalDeGranja();
			animal.setTipo(tiposDeAnimales.get(0));
			animal.setRaza(razas.get(0));
			animal.setGenero(generos.get(0));
			modelo.put("animal", animal);

		} else {

			animal = (AnimalDeGranja) modelo.get("animal");
			razas = this.servicioDeAnimales.obtenerRazasPorTipoAnimal(animal.getTipo());
		}

		modelo.put("tiposDeAnimales", tiposDeAnimales);
		modelo.put("razas", razas);
		modelo.put("generos", generos);

		modelo.put("notificaciones", listarNotificacionesDelEmpleado(request));

		return new ModelAndView("registrarAnimal", modelo);
	}

	@RequestMapping(value = "/animales/registrar", method = RequestMethod.POST)
	public ModelAndView registrarAnimal(@ModelAttribute("animal") AnimalDeGranja animal,
			BindingResult result, RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = null;

		this.validadorDeAnimales.validate(animal, result);

		if (result.hasErrors()) {
			 redirectAttributes.addFlashAttribute("animal", animal);
			 redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.animal", result);
			 modelAndView = new ModelAndView("redirect:/animales/registrar");
		} else {

			this.servicioDeAnimales.registrar(animal);
			modelAndView = new ModelAndView("redirect:/animales");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/animales/cargarRazas")
	@ResponseBody
	public List<Raza> cargarRazas(@RequestParam("idTipoAnimal") Long idTipoAnimal) {
		TipoAnimal tipoAnimal = new TipoAnimal();
		tipoAnimal.setId(idTipoAnimal);
		return this.servicioDeAnimales.obtenerRazasPorTipoAnimal(tipoAnimal);
	}

	@RequestMapping("/animales/editar")
	public ModelAndView editarAnimal(HttpServletRequest request, @RequestParam("id") Long idAnimal) {
		AnimalDeGranja animal = this.servicioDeAnimales.obtenerPorId(idAnimal);

		List<TipoAnimal> tiposDeAnimales = this.servicioDeAnimales.obtenerTiposDeAnimales();
		List<Raza> razas = this.servicioDeAnimales.obtenerRazasPorTipoAnimal(animal.getTipo());
		List<Genero> generos = this.servicioDeAnimales.obtenerGeneros();

		ModelMap modelo = new ModelMap();
		modelo.put("animalEditable", animal);
		modelo.put("tiposDeAnimales", tiposDeAnimales);
		modelo.put("razas", razas);
		modelo.put("generos", generos);

		modelo.put("notificaciones", listarNotificacionesDelEmpleado(request));

		return new ModelAndView("editarAnimal", modelo);
	}

	@RequestMapping(value = "/animales/editar", method = RequestMethod.POST)
	public ModelAndView actualizarAnimal(@ModelAttribute AnimalDeGranja animal) {
		this.servicioDeAnimales.actualizarAnimal(animal);
		return new ModelAndView("redirect:/animales");
	}

	@RequestMapping(value = "/animales/eliminar", method = RequestMethod.POST)
	public ModelAndView eliminarAnimal(@ModelAttribute("idAnimal") Long idAnimal, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:/login");
		String rol = (String) request.getSession().getAttribute("ROL");
		
		HistoriaClinica hc = new HistoriaClinica();
		SignosVitales sv = new SignosVitales();
		
		if("Empleado".equals(rol)) {
			sv = servicioDeAnimales.buscarUltimosSignosVitalesDelAnimal(idAnimal);
			hc = servicioHistoriaClinica.buscarHistoriaClinicaPorId(idAnimal);
			
			this.servicioDeAnimales.eliminarPorId(idAnimal, sv, hc);
			modelAndView =  new ModelAndView("redirect:/animales");
		}
		return modelAndView;
	}
	
}
