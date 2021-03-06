package ar.edu.unlam.tallerweb1.controladores;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Gastos;
import ar.edu.unlam.tallerweb1.modelo.TipoDeGasto;
import ar.edu.unlam.tallerweb1.modelo.TipoDeUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioGastos;
import ar.edu.unlam.tallerweb1.servicios.ServicioTipoDeGasto;
import ar.edu.unlam.tallerweb1.servicios.ServicioTipoDeUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorAdmin {

	private ServicioUsuario servicioUsuario;
	private ServicioGastos servicioGastos;
	private ServicioTipoDeGasto servicioTipoDeGasto;
	private ServicioTipoDeUsuario servicioTipoDeUsuario;
	
	@Autowired
	public ControladorAdmin(ServicioUsuario servicioUsuario, ServicioGastos servicioGastos,
			ServicioTipoDeGasto servicioTipoDeGasto, ServicioTipoDeUsuario servicioTipoDeUsuario) {
		this.servicioUsuario = servicioUsuario;
		this.servicioGastos = servicioGastos;
		this.servicioTipoDeGasto = servicioTipoDeGasto;
		this.servicioTipoDeUsuario = servicioTipoDeUsuario;
	}

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

		model.put("usuarios", usuarios);
	
		return new ModelAndView("usuarios", model);
	}

	@RequestMapping(path = "/gastos")
	public ModelAndView irAGastos(HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("ROL");

		if (!rol.equals("Admin")) {
			return new ModelAndView("redirect:/login");
		}

		ModelMap modelo = new ModelMap();

		List<Gastos> gastos = servicioGastos.consultarGastos();

		modelo.put("gastos", gastos);

		return new ModelAndView("gastos", modelo);

	}

	@RequestMapping(path = "/estadisticas")
	public ModelAndView irAEstadisticas(HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("ROL");
		if (!rol.equals("Admin")) {
			return new ModelAndView("redirect:/login");
		}

		ModelMap modelo = new ModelMap();
		
		Long idUsuario = (Long) request.getSession().getAttribute("ID");
		
		Usuario usuario = servicioUsuario.consultarUsuarioPorId(idUsuario);
		
		TreeMap<Integer, Double> alimenticio = servicioGastos.consultarGastosPorMes("Alimenticio");
		TreeMap<Integer, Double> tecnologico = servicioGastos.consultarGastosPorMes("Tecnológico");
		TreeMap<Integer, Double> medico = servicioGastos.consultarGastosPorMes("Médico");
		TreeMap<Integer, Double> empresarial = servicioGastos.consultarGastosPorMes("Empresarial");

		List<Gastos> gastosEnTotalPorTipo = servicioGastos.consultarGastosEnTotalPorTipo();
		
		TreeMap<Integer, Double> gastosEnTotalPorMes = servicioGastos.consultarGastosEnTotal();
		
		modelo.put("usuario", usuario);
		modelo.put("alimenticio", alimenticio);
		modelo.put("tecnologico", tecnologico);
		modelo.put("medico", medico);
		modelo.put("empresarial", empresarial);

		modelo.put("gastosEnTotalPorTipo", gastosEnTotalPorTipo);
		modelo.put("gastosEnTotalPorMes", gastosEnTotalPorMes);

		return new ModelAndView("estadisticas", modelo);
	}

	@RequestMapping(path = "/registrarGasto")
	public ModelAndView irANuevaEstadistica() {
		ModelMap modelo = new ModelMap();
		Gastos gastos = new Gastos();
		modelo.put("gastos", gastos);
		List<TipoDeGasto> tiposDeGastos = servicioTipoDeGasto.obtenerTiposDeGastos();
		modelo.put("tipoDeGastos", tiposDeGastos);
		return new ModelAndView("registrarGasto", modelo);
	}

	@RequestMapping(path = "/validarGastoNuevo", method = RequestMethod.POST)
	public ModelAndView validarNuevaEstadistica(@ModelAttribute("gastos") Gastos gastos, HttpServletRequest request) {
		Long id = (Long) request.getSession().getAttribute("ID");
		ModelMap modelo = new ModelMap();
		List<TipoDeGasto> tiposDeGastos = servicioTipoDeGasto.obtenerTiposDeGastos();
		modelo.put("tipoDeGastos", tiposDeGastos);

		if (gastos.getMonto() == null || gastos.getMonto() == 0) {
			modelo.put("error", "El monto del gasto debe ser mayor a 0.");
			return new ModelAndView("registrarGasto", modelo);
		}

		Boolean validarTipo = false;
		for (TipoDeGasto g : tiposDeGastos) {
			if (gastos.getTipoDeGasto().getId().equals(g.getId())) {
				validarTipo = true;
			}
		}
		if (!validarTipo) {
			modelo.put("error", "No ha elegido un tipo de gasto válido.");
			return new ModelAndView("registrarGasto", modelo);
		}

		Usuario usuario = servicioUsuario.consultarUsuarioPorId(id);
		gastos.setUsuario(usuario);
		gastos.setFecha(LocalDate.now());

		if (servicioGastos.guardarNuevoRegistro(gastos) != null) {
			modelo.put("mensaje", "Registro guardado exitosamente.");
			return new ModelAndView("registrarGasto", modelo);
		} else {
			modelo.put("error", "No se pudo guardar el registro.");
			return new ModelAndView("registrarGasto", modelo);
		}
	}

	@RequestMapping(path = "/modificarGasto")
	public ModelAndView irAModificarEstadistica(@RequestParam(value = "id", required = true) Long id) {
		ModelMap modelo = new ModelMap();
		Gastos gastos = (Gastos) servicioGastos.consultaGastosPorID(id);
		modelo.put("gastos", gastos);

		Gastos gastosNuevos = new Gastos();
		modelo.put("gastosNuevos", gastosNuevos);

		List<TipoDeGasto> tiposDeGastos = servicioTipoDeGasto.obtenerTiposDeGastos();
		modelo.put("tipoDeGastos", tiposDeGastos);

		return new ModelAndView("modificarGasto", modelo);
	}

	@RequestMapping(path = "/validarGastoModificado")
	public ModelAndView validarCambiosEnGastos(@RequestParam(value = "id", required = true) Long id,
			@ModelAttribute("gastosNuevos") Gastos gastosAModificar) {
		ModelMap modelo = new ModelMap();
		Gastos gastosAntiguos = (Gastos) servicioGastos.consultaGastosPorID(id);
		Gastos gastosActuales = (Gastos) servicioGastos.consultaGastosPorID(id);
		List<TipoDeGasto> tiposDeGastos = servicioTipoDeGasto.obtenerTiposDeGastos();
		modelo.put("tipoDeGastos", tiposDeGastos);

		if (gastosAModificar.getMonto() == null && (gastosAModificar.getTipoDeGasto() == null)) {
			modelo.put("error", "Debe elegir al menos un campo para modificar el registro.");
			modelo.put("gastos", gastosActuales);
			return new ModelAndView("modificarGasto", modelo);
		}

		modelo.put("gastos", gastosActuales);

		if (gastosAModificar.getMonto() != null && gastosAModificar.getMonto() >= 0) {
			gastosActuales.setMonto(gastosAModificar.getMonto());
		}

		if (gastosAModificar.getTipoDeGasto() != null && gastosAModificar.getTipoDeGasto().getNombre() != "") {
			Boolean validarTipo = false;
			for (TipoDeGasto g : tiposDeGastos) {
				if (gastosAModificar.getTipoDeGasto().getId().equals(g.getId())) {
					validarTipo = true;
				}
			}
			if (!validarTipo) {
				modelo.put("error", "No ha elegido un tipo de gasto valido.");
				return new ModelAndView("modificarGasto", modelo);
			}
			gastosActuales.setTipoDeGasto(gastosAModificar.getTipoDeGasto());
		}

		servicioGastos.modificarGasto(gastosActuales);
		modelo.put("gastos", gastosActuales);
		modelo.put("gastosAntiguos", gastosAntiguos);

		return new ModelAndView("modificarGasto", modelo);
	}

	@RequestMapping(path = "/eliminarGasto", method = RequestMethod.GET)
	public ModelAndView irAEliminarEstadistica(@RequestParam(value = "id", required = true) Long id) {
		Gastos gastos = (Gastos) servicioGastos.consultaGastosPorID(id);
		servicioGastos.eliminarGastos(gastos);
		return new ModelAndView("redirect:/gastos");
	}

	/*
	 * =============================================================================
	 * ======================
	 */

	@RequestMapping(path = "/registro")
	public ModelAndView irARegistro() {
		ModelMap modelo = new ModelMap();
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);

		List<TipoDeUsuario> roles = servicioTipoDeUsuario.ObtenerTodosLosRoles();
		modelo.put("roles", roles);

		return new ModelAndView("registro", modelo);
	}

	/*
	 * Se comprueba que el usuario no exista, que las contraseÃ±as coincidan y se
	 * registra el usuario
	 */
	/* HAY QUE MODIFICAR ESTO, NO SE PIDE LA CONTRASEÃ‘A AL ADMINISTRADOR */
	@RequestMapping(path = "/validar-registro", method = RequestMethod.POST)
	public ModelAndView validarRegistro(@ModelAttribute("usuario") Usuario usuario,
			@RequestParam(name = "password2") String password2) {
		ModelMap model = new ModelMap();
		List<TipoDeUsuario> roles = servicioTipoDeUsuario.ObtenerTodosLosRoles();
		model.put("roles", roles);

		Usuario usuarioBuscado = servicioUsuario.consultarUsuario(usuario);

		if (usuario.getPassword().equals(password2)) {
			if (usuarioBuscado != null) {
				model.put("error", "Usuario ya registrado.");
			} else {
				TipoDeUsuario tipoDeUsuarioBuscado = servicioTipoDeUsuario.consultarRol(usuario.getRol());
				if (tipoDeUsuarioBuscado != null) {
					if (servicioUsuario.registrarUsuario(usuario) != null) {
						model.put("mensaje", "Usuario creado correctamente");
					} else {
						model.put("mensaje", "No se pudo crear el usuario");
					}
				} else {
					model.put("error", "El tipo de usuario elegido no es válido");
				}
			}
		} else {
			model.put("error", "La contraseÃ±as no coinciden");
		}

		return new ModelAndView("registro", model);
	}

	/*
	 * Se manda al modal el id del Usuario que seleccionÃ³ para borrar, y al aceptar
	 * se se elimina el usuario y se redirige al index HAY QUE MODIFICAR, CUANDO SE
	 * ELIMINA UN USUARIO NO SE NOTIFICA EN LA VISTA
	 */
	@RequestMapping(path = "/eliminarUsuario")
	public ModelAndView eliminarUsuario(@RequestParam(value = "id", required = true) Long id) {

		Usuario usuario = servicioUsuario.consultarUsuarioPorId(id);

		servicioUsuario.eliminarUsuario(usuario);

		return new ModelAndView("redirect:/indexAdmin");

	}
}
