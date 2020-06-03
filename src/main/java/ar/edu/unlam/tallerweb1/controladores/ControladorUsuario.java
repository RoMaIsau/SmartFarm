package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.TipoDeUsuario;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioTipoDeUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorUsuario {

	/*
	 * La anotacion @Autowired indica a Spring que se debe utilizar el contructor
	 * como mecanismo de inyección de dependencias, es decir, qeue lo parametros del
	 * mismo deben ser un bean de spring y el framewrok automaticamente pasa como
	 * parametro el bean correspondiente, en este caso, un objeto de una clase que
	 * implemente la interface ServicioLogin, dicha clase debe estar aDarkest Dark
	 * Themenotada como @Service o @Repository y debe estar en un paquete de los
	 * indicados en applicationContext.xml
	 */
	private ServicioUsuario servicioUsuario;

	@Autowired
	public ControladorUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}
	
	@Inject
	private ServicioTipoDeUsuario servicioTipoDeUsuario;
	
	
	
	
	
	
	
	
	
	
	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es
	// invocada por metodo http GET
	@RequestMapping("/login")
	public ModelAndView irALogin(HttpServletRequest request) {

		String rol = request.getSession().getAttribute("ROL") != null
				? (String) request.getSession().getAttribute("ROL")
				: "";

		switch (rol) {
		case "Admin":
			return new ModelAndView("redirect:/indexAdmin");

		case "Empleado":
			return new ModelAndView("redirect:/indexEmpleado");

		case "Veterinario":
			return new ModelAndView("redirect:/indexVeterinario");
		}

		ModelMap modelo = new ModelMap();
		// Se agrega al modelo un objeto del tipo Usuario con key 'usuario' para que el
		// mismo sea asociado
		// al model attribute del form que esta definido en la vista 'login'
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		// Se va a la vista login (el nombre completo de la lista se resuelve utilizando
		// el view resolver definido en el archivo spring-servlet.xml)
		// y se envian los datos a la misma dentro del modelo
		return new ModelAndView("login", modelo);
	}

	// Escucha la url /, y redirige a la URL /login, es lo mismo que si se invoca la
	// url /login directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/login");
	}

	// Este metodo escucha la URL validar-login siempre y cuando se invoque con
	// metodo http POST
	// El método recibe un objeto Usuario el que tiene los datos ingresados en el
	// form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		// invoca el metodo consultarUsuario del servicio y hace un redirect a la URL
		// /home, esto es, en lugar de enviar a una vista
		// hace una llamada a otro action a través de la URL correspondiente a ésta
		Usuario usuarioBuscado = servicioUsuario.consultarUsuario(usuario);
		if (usuarioBuscado != null) {
			request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
			request.getSession().setAttribute("ID", usuarioBuscado.getId());

			String rol = (String) request.getSession().getAttribute("ROL");

			switch (rol) {
			case "Admin":
				return new ModelAndView("redirect:/indexAdmin");
			case "Veterinario":
				return new ModelAndView("redirect:/indexVeterinario");
			case "Empleado":
				return new ModelAndView("redirect:/indexEmpleado");
			}
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}

	@RequestMapping(path = "/registro")
	public ModelAndView irARegistro() {
		ModelMap modelo = new ModelMap();

		Usuario usuario = new Usuario();

		modelo.put("usuario", usuario);

		return new ModelAndView("registro", modelo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * Se comprueba que el usuario no exista, que las contraseñas coincidan y se
	 * registra el usuario
	 */
	/* HAY QUE MODIFICAR ESTO, NO SE PIDE LA CONTRASEÑA AL ADMINISTRADOR */
	@RequestMapping(path = "/validar-registro", method = RequestMethod.POST)
	public ModelAndView validarRegistro(@ModelAttribute("usuario") Usuario usuario,
										@RequestParam(name = "password2") String password2) {
		ModelMap model = new ModelMap();
		
		Usuario usuarioBuscado = servicioUsuario.consultarUsuario(usuario);

		if (usuario.getPassword().equals(password2)) {
			if (usuarioBuscado != null) {
				model.put("error", "Usuario ya registrado.");
			} else {
				TipoDeUsuario tipoDeUsuarioBuscado = servicioTipoDeUsuario.consultarRol(usuario.getRol());
				if(tipoDeUsuarioBuscado != null) {
					if (servicioUsuario.registrarUsuario(usuario) != null) {
						model.put("mensaje", "Usuario creado correctamente");
					} else {
						model.put("mensaje", "No se pudo crear el usuario");
					}
				} else {
					model.put("error", "El tipo de usuario elegido no es v�lido");
				}
			}
		} else {
			model.put("error", "La contraseñas no coinciden");
		}
		
		return new ModelAndView("registro", model);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * Se manda al modal el id del Usuario que seleccionó para borrar, y al aceptar
	 * se se elimina el usuario y se redirige al index HAY QUE MODIFICAR, CUANDO SE
	 * ELIMINA UN USUARIO NO SE NOTIFICA EN LA VISTA
	 */
	@RequestMapping(path = "/eliminarUsuario")
	public ModelAndView eliminarUsuario(@RequestParam(value = "id", required = true) Long id) {

		Usuario usuario = servicioUsuario.consultarUsuarioPorId(id);

		servicioUsuario.eliminarUsuario(usuario);

		return new ModelAndView("redirect:/indexAdmin");

	}

	/*
	 * Se elimina el atributo rol de la session y se redirecciona a la vista del
	 * login
	 */
	@RequestMapping(path = "/cerrarSesion")
	public ModelAndView cerrarSesion(HttpServletRequest request) {
		request.getSession().removeAttribute("ROL");
		return new ModelAndView("redirect:/login");
	}

	// Escucha la URL /home por GET, y redirige a una vista.
	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public ModelAndView irAHome() {
		return new ModelAndView("home");
	}

}

