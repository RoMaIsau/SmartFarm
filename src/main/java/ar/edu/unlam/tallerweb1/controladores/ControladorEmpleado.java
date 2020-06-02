package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAlimento;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlimento;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorEmpleado {

	private ServicioDeAnimales servicioDeAnimales;
	private ServicioAlimento servicioAlimento;
	private ServicioNotificacion servicioNotificacion;
	private ServicioUsuario servicioUsuario;

	@Autowired
	public void setServicioDeAnimales(ServicioDeAnimales servicioDeAnimales, ServicioAlimento servicioAlimento,
			ServicioNotificacion servicioNotificacion, ServicioUsuario servicioUsuario) {
		this.servicioDeAnimales = servicioDeAnimales;
		this.servicioAlimento = servicioAlimento;
		this.servicioNotificacion = servicioNotificacion;
		this.servicioUsuario = servicioUsuario;
	}

	@Autowired
	public void setServicioDeAnimales(ServicioDeAnimales servicioDeAnimales) {
		this.servicioDeAnimales = servicioDeAnimales;
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
		Long idUsuario = (Long) request.getSession().getAttribute("ID");
		
		if (rol.equals("Empleado") || rol.equals("Admin")) {

			ModelMap modelo = new ModelMap();

			servicioNotificacion.crearNotificacionStock();
			Usuario usuario = servicioUsuario.consultarUsuarioPorId(idUsuario);
			List<Notificacion> notificaciones = servicioNotificacion.listarNotificaciones(usuario);
			List<AnimalDeGranja> animales = this.servicioDeAnimales.obtenerTodos();
			modelo.put("animales", animales);
			modelo.put("notificaciones", notificaciones);
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

		List<Alimento> alimentos = servicioAlimento.listarAlimentos();

		List<TipoAlimento> tiposAlimentos = servicioAlimento.obtenerTiposDeAlimentos();

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

		return new ModelAndView("registroAlimento", model);

	}

	@RequestMapping(path = "/validar-registro-alimento")
	public ModelAndView validarRegistroAlimento(@ModelAttribute("alimento") Alimento alimento) {
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

	@RequestMapping(value = "/animales/registrar")
	public ModelAndView irAFormularioDeRegistroDeAnimales() {

		AnimalDeGranja animal = new AnimalDeGranja();

		List<TipoAnimal> tiposDeAnimales = this.servicioDeAnimales.obtenerTiposDeAnimales();
		List<Raza> razas = this.servicioDeAnimales.obtenerRazasPorTipoAnimal(tiposDeAnimales.get(0));
		List<Genero> generos = this.servicioDeAnimales.obtenerGeneros();

		animal.setTipo(tiposDeAnimales.get(0));
		animal.setRaza(razas.get(0));
		animal.setGenero(generos.get(0));

		ModelMap modelo = new ModelMap();
		modelo.put("tiposDeAnimales", tiposDeAnimales);
		modelo.put("razas", razas);
		modelo.put("generos", generos);
		modelo.put("animal", animal);

		return new ModelAndView("registrarAnimal", modelo);
	}

	@RequestMapping(value = "/animales/registrar", method = RequestMethod.POST)
	public ModelAndView registrarAnimal(@ModelAttribute("animal") AnimalDeGranja animal) {

		this.servicioDeAnimales.registrar(animal);
		return new ModelAndView("redirect:/animales");
	}

	@RequestMapping(value = "/animales/cargarRazas")
	@ResponseBody
	public List<Raza> cargarRazas(@RequestParam("idTipoAnimal") Long idTipoAnimal) {
		TipoAnimal tipoAnimal = new TipoAnimal();
		tipoAnimal.setId(idTipoAnimal);
		return this.servicioDeAnimales.obtenerRazasPorTipoAnimal(tipoAnimal);
	}

	@RequestMapping("/animales/editar")
	public ModelAndView editarAnimal(@RequestParam("id") Long idAnimal) {

		AnimalDeGranja animal = this.servicioDeAnimales.obtenerPorId(idAnimal);

		List<TipoAnimal> tiposDeAnimales = this.servicioDeAnimales.obtenerTiposDeAnimales();
		List<Raza> razas = this.servicioDeAnimales.obtenerRazasPorTipoAnimal(animal.getTipo());
		List<Genero> generos = this.servicioDeAnimales.obtenerGeneros();

		ModelMap modelo = new ModelMap();
		modelo.put("animalEditable", animal);
		modelo.put("tiposDeAnimales", tiposDeAnimales);
		modelo.put("razas", razas);
		modelo.put("generos", generos);
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

		if("Empleado".equals(rol)) {

			this.servicioDeAnimales.eliminarPorId(idAnimal);
			modelAndView =  new ModelAndView("redirect:/animales");
		}
		return modelAndView;
	}
}