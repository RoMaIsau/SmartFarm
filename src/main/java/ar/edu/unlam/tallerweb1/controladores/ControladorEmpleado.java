package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
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

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.GanadoVacuno;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanado;

@Controller
public class ControladorEmpleado {

	@Inject
	private ServicioGanado servicioGanado;

	private ServicioDeAnimales servicioDeAnimales;

	@RequestMapping(path = "/indexEmpleado")
	public ModelAndView irAIndexEmpleado(HttpServletRequest request) {

		// Verifica que sea un usuario de tipo Empleado, si no lo es, lo redirige al
		// login
		String rol = (String) request.getSession().getAttribute("ROL");

		if (rol.equals("Empleado")) {
			return new ModelAndView("indexEmpleado");
		} else {
			return new ModelAndView("redirect:/login");
		}
	}

	@RequestMapping(path = "/animales")
	public ModelAndView listarAnimales(HttpServletRequest request) {
		ModelMap model = new ModelMap();
		String rol = (String) request.getSession().getAttribute("ROL");

		if (rol.equals("Empleado") || rol.equals("Admin")) {

			List<GanadoVacuno> ganadoVacuno = servicioGanado.listar();

			if (ganadoVacuno != null) {
				model.put("ganadoVacuno", ganadoVacuno);
			} else {
				model.put("error", "No hay animales registrados");
			}
		} else {
			return new ModelAndView("redirect:/login");
		}

		return new ModelAndView("animales", model);
	}

	@RequestMapping(path = "/stock")
	public ModelAndView irAStock(HttpServletRequest request) {

		String rol = (String) request.getSession().getAttribute("ROL");

		if (rol.equals("Empleado")) {
			return new ModelAndView("stock");
		} else {
			return new ModelAndView("redirect:/login");
		}

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

	@Autowired
	public void setServicioDeAnimales(ServicioDeAnimales servicioDeAnimales) {
		this.servicioDeAnimales = servicioDeAnimales;
	}
}
