package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.GanadoVacuno;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeTiposDeAnimales;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanado;

@Controller
public class ControladorEmpleado {

	@Inject
	private ServicioGanado servicioGanado;
	
	private ServicioDeTiposDeAnimales servicioDeTiposDeAnimales;

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

	@RequestMapping(path = "/animales/registrar")
	public ModelAndView registrarAnimal() {
		
		List<TipoAnimal> tiposDeAnimales = this.servicioDeTiposDeAnimales.obtenerDisponibles();
		
		ModelMap modelo = new ModelMap();
		modelo.put("tiposDeAnimales", tiposDeAnimales);
		
		return new ModelAndView("registrarAnimal", modelo);
	}

	@Autowired
	public void setServicioDeTiposDeAnimales(ServicioDeTiposDeAnimales servicioDeTiposDeAnimales) {
		this.servicioDeTiposDeAnimales = servicioDeTiposDeAnimales;		
	}

}
