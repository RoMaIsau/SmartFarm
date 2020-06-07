package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Gastos;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioGastos;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorAdmin {

	@Inject
	private ServicioUsuario servicioUsuario;
	
	@Inject
	private ServicioGastos servicioGastos;

	/*
	 * El path de indexAdmin redirecciona a la primera seccion del sidebar, Usuarios
	 */
	@RequestMapping(path = "/indexAdmin")
	public ModelAndView irAIndexAdmin(HttpServletRequest request) {

		// Verifica que sea un usuario de tipo Admin, si no lo es, lo redirige al login
		String rol = (String) request.getSession().getAttribute("ROL");

		if (rol.equals("Admin")) {
			return new ModelAndView("redirect:/usuarios");
		} else {
			return new ModelAndView("redirect:/login");
		}

	}

	/*
	 * La vista Usuarios muestra todos los usuarios registrados, trayendolos en una
	 * lista
	 */
	@RequestMapping(path = "/usuarios")
	public ModelAndView listarUsuario(HttpServletRequest request) {

		String rol = (String) request.getSession().getAttribute("ROL");

		if (!rol.equals("Admin")) {
			return new ModelAndView("redirect:/login");
		}

		ModelMap model = new ModelMap();

		List<Usuario> usuarios = servicioUsuario.listarUsuarios();

		if (usuarios != null) {
			model.put("usuarios", usuarios);
		} else {
			model.put("error", "No hay usuarios registrados");
		}

		return new ModelAndView("usuarios", model);
	}
	
	@RequestMapping(path = "/estadisticas")
	public ModelAndView irAEstadisticas(HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("ROL");
		if (!rol.equals("Admin")) {
			return new ModelAndView("redirect:/login");
		}
		
		ModelMap modelo = new ModelMap();
		
		
		Long id = (Long) request.getSession().getAttribute("ID");
		Usuario usuario = servicioUsuario.consultarUsuarioPorId(id);
		List<Gastos> gastos = servicioGastos.consultarGastosPorUsuario(usuario);
		
		modelo.put("gastos", gastos);
		
		return new ModelAndView("estadisticas", modelo);
	}
	
	@RequestMapping(path = "/estadisticasnuevas")
	public ModelAndView irANuevaEstadistica() {
		ModelMap modelo = new ModelMap();
		Gastos gastos = new Gastos();
		modelo.put("gastos", gastos);
		return new ModelAndView("estadisticasNuevas", modelo);
	}
	
	@RequestMapping(path = "/validarnuevaestadistica", method = RequestMethod.POST)
	public ModelAndView validarNuevaEstadistica(@ModelAttribute("gastos") Gastos gastos, HttpServletRequest request) {
		Long id = (Long) request.getSession().getAttribute("ID");
		ModelMap modelo = new ModelMap();
		
		if(gastos.getGastosAlimenticios() == null) {
			gastos.setGastosAlimenticios(0.0);
		}
		if(gastos.getGastosEmpresariales() == null) {
			gastos.setGastosEmpresariales(0.0);
		}
		if(gastos.getGastosMedicos() == null) {
			gastos.setGastosMedicos(0.0);
		}
		if(gastos.getGastosTecnologicos() == null) {
			gastos.setGastosTecnologicos(0.0);
		}
		
		if(gastos.getGastosAlimenticios() == 0 && gastos.getGastosEmpresariales() == 0
			  && gastos.getGastosMedicos() == 0 && gastos.getGastosTecnologicos() == 0) {
			modelo.put("error", "Debe contabilizar al menos un gasto.");
			return new ModelAndView("estadisticasNuevas", modelo);
		}
		
		Usuario usuario = servicioUsuario.consultarUsuarioPorId(id);
		gastos.setUsuario(usuario);
		
		if(servicioGastos.guardarNuevoRegistro(gastos) != null){
			modelo.put("mensaje", "Registro guardado exitosamente.");
			return new ModelAndView("estadisticasNuevas", modelo);
		} else {
			modelo.put("error", "No se pudo guardar el registro.");
			return new ModelAndView("estadisticasNuevas", modelo);
		}
	}
	
	@RequestMapping(path = "/estadisticasamodificar")
	public ModelAndView irAModificarEstadistica(@RequestParam(value = "id", required = true) Long id) {
		ModelMap modelo = new ModelMap();
		Gastos gastos = (Gastos) servicioGastos.consultaGastosPorID(id);
		modelo.put("gastos", gastos);
		
		Gastos gastosNuevos = new Gastos();
		modelo.put("gastosNuevos", gastosNuevos);
		
		return new ModelAndView("estadisticasAModificar", modelo);
	}
	
	@RequestMapping(path = "/validarcambiosenestadistica")
	public ModelAndView validarCambiosEnGastos(@RequestParam(value = "id", required = true) Long id,
											   @ModelAttribute("gastosNuevos") Gastos gastosAModificar) {
		ModelMap modelo = new ModelMap();
		Gastos gastosAntiguos = (Gastos) servicioGastos.consultaGastosPorID(id);
		Gastos gastosActuales = (Gastos) servicioGastos.consultaGastosPorID(id);
		
		if(gastosAModificar.getGastosAlimenticios() == null &&
		   gastosAModificar.getGastosEmpresariales() == null &&
		   gastosAModificar.getGastosMedicos() == null &&
		   gastosAModificar.getGastosTecnologicos() == null) {
		   modelo.put("error", "Debe elegir al menos un campo para modificar el registro.");
		   modelo.put("gastos", gastosActuales);
		   return new ModelAndView("estadisticasAModificar", modelo);
		}
		
		modelo.put("gastosAntiguos", gastosAntiguos);
		
		if(gastosAModificar.getGastosAlimenticios() != null) {
			gastosActuales.setGastosAlimenticios(gastosAModificar.getGastosAlimenticios());
		}
		if(gastosAModificar.getGastosEmpresariales() != null) {
			gastosActuales.setGastosEmpresariales(gastosAModificar.getGastosEmpresariales());
		}
		if(gastosAModificar.getGastosMedicos() != null) {
			gastosActuales.setGastosMedicos(gastosAModificar.getGastosMedicos());
		}
		if(gastosAModificar.getGastosTecnologicos() != null) {
			gastosActuales.setGastosTecnologicos(gastosAModificar.getGastosTecnologicos());
		}
		
		servicioGastos.modificarGasto(gastosActuales);
		modelo.put("gastos", gastosActuales);
		
		return new ModelAndView("estadisticasAModificar", modelo);
	}
	
	@RequestMapping(path = "/estadisticasaeliminar", method = RequestMethod.GET)
	public ModelAndView irAEliminarEstadistica(@RequestParam(value = "id", required = true) Long id) {
		Gastos gastos = (Gastos) servicioGastos.consultaGastosPorID(id);
		servicioGastos.eliminarGastos(gastos);
		return new ModelAndView("redirect:/estadisticas");
	}
}
