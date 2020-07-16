package ar.edu.unlam.tallerweb1.controladores;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Enfermedad;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.Sintomas;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanado;
import ar.edu.unlam.tallerweb1.servicios.ServicioVacunas;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;


@Controller
public class ControladorVeterinario{
	
	public List<Notificacion> listarNotificacionesDelVeterinario(HttpServletRequest request) {
		Long idUsuario = (Long) request.getSession().getAttribute("ID");
		List<Notificacion> notificaciones = servicioNotificacion.listarNotificaciones(idUsuario);
		return notificaciones;
	}
	
	@Inject
	private ServicioNotificacion servicioNotificacion;
	
	@Inject
	ServicioGanado servicioGanado;
	
	@Inject
	ServicioVacunas servicioVacuna;
	
	public ServicioVacunas getServicioVacuna() {
		return servicioVacuna;
	}
	
	public void setServicioVacuna(ServicioVacunas servicioVacuna) {
		this.servicioVacuna = servicioVacuna;
	}
	
	public ServicioGanado getServicioGanado() {
		return servicioGanado;
	}
	
	public void setServicioGanado(ServicioGanado servicioGanado) {
		this.servicioGanado = servicioGanado;
	}
	
	
	@RequestMapping(path = "/indexVeterinario")
	public ModelAndView irAIndexVeterinario(HttpServletRequest request){
  		String rol = (String) request.getSession().getAttribute("ROL");
  		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
		
		ModelMap model = new ModelMap();
		model.put("notificaciones", listarNotificacionesDelVeterinario(request));
		
		if(rol.equals("Veterinario")) {
			List<AnimalDeGranja> animales= servicioGanado.listar();
			HashSet<AnimalDeGranja> animalesVencidos= new HashSet<AnimalDeGranja>();
			
	        for(AnimalDeGranja v: animales) {
	        	List<Vacuna> vencidas= servicioVacuna.alarmaVacuna(v);
	        	
	        	if(!vencidas.isEmpty()){
	        		animalesVencidos.add(v);
	        	}
	        }
	        
	        ModelMap modelo= new ModelMap();
	        modelo.put("vencidos",animalesVencidos);
	        modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
	        
	        return new ModelAndView("redirect:/historiaClinica",modelo);
		} else if(!rol.equals("Veterinario")) {
            return new ModelAndView("redirect:/historiaClinica", model);
		} else {
			return new ModelAndView("redirect:/login");
		}
	}
	
	@RequestMapping("/detalle")
	public ModelAndView vacunar(@RequestParam(value="id", required=true) Long id, HttpServletRequest request){
  		String rol = (String) request.getSession().getAttribute("ROL");
  		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
  		
		ModelMap modelo= new ModelMap();
        AnimalDeGranja gv =servicioGanado.ver(id);
		List<Vacuna>v = servicioVacuna.alarmaVacuna(gv);
		
		modelo.put("vencidas",v);
        modelo.put("vacaId",id);
        modelo.put("volver","VOLVER");
        modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
        
        return new ModelAndView("indexVeterinario", modelo);
	}
	
	@RequestMapping("/vacunar")
	public ModelAndView vacunar(@RequestParam(value="id", required=true) Long id, HttpServletRequest request,
							    @RequestParam(value="nombre", required=true) String nombre){
  		String rol = (String) request.getSession().getAttribute("ROL");
  		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
  		
		ModelMap modelo= new ModelMap();
        AnimalDeGranja gv =servicioGanado.ver(id);
        Vacuna v = servicioVacuna.getVacuna(nombre);
        
        servicioVacuna.vacunar(gv, v);
        AnimalDeGranja g= servicioGanado.ver(id);
        g.getVacunasParaAplicar();
        Vacuna v1 = servicioVacuna.getVacuna(nombre);  
        modelo.put("mensaje","La vacuna "+nombre+" fue aplicada");
        modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
        
        return new ModelAndView("indexVeterinario", modelo);
	}
	
	@RequestMapping("/listaGanado")
	public ModelAndView listaGanado(HttpServletRequest request){
  		String rol = (String) request.getSession().getAttribute("ROL");
  		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
  		
		List<AnimalDeGranja> animales= servicioGanado.listar();
        HashSet<AnimalDeGranja> animalesNoRepetidos= new HashSet<>();
        
        for(AnimalDeGranja animal : animales) {
        	animalesNoRepetidos.add(animal);
        }
        
        List< SignosVitales> signosAnormales= new ArrayList<SignosVitales>();
        List< AnimalDeGranja> vacasAnormales= new ArrayList<AnimalDeGranja>();
        SignosVitales signos1= new SignosVitales();
        SignosVitales signos2= new SignosVitales();
        
        for(AnimalDeGranja v: animalesNoRepetidos) {
        	HistoriaClinica hc= v.getHistoria();
        	signos1= servicioGanado.signosFecha(hc);
        	
        	if(signos1 != null) {
        		signos2= signos1;
        		Boolean res= servicioGanado.alarmaSV(signos2);
        		
        		if(res == true){
        			vacasAnormales.add(v);
        		}
        	}
        }
        
        ModelMap modelo= new ModelMap();
        
        modelo.put("anormales",vacasAnormales);
        modelo.put("animales",animalesNoRepetidos);
        modelo.put("signos2",signosAnormales);
        modelo.put("signos3",signos2);
        modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
        
        return new ModelAndView("HomeAnimal", modelo);
    }
	
	@RequestMapping("/verEstadoSalud")
	public ModelAndView verSalud(@RequestParam(value="id", required=true) Long id, HttpServletRequest request) {
  		String rol = (String) request.getSession().getAttribute("ROL");
  		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
  		
		AnimalDeGranja gv= servicioGanado.ver(id);
	    HistoriaClinica historia=gv.getHistoria();
        List<SignosVitales> signos= servicioGanado.signos(historia);
	    ModelMap modelo= new ModelMap();
        
	    if(signos != null) {
	    	int pos= signos.size();
	    	int pos2=pos-1;
	    	SignosVitales signo= signos.get(pos2);
	    	modelo.put("signos",signo);
	    	modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
	    	
	    	return new ModelAndView("HomeAnimal", modelo);
	    } else {
	    	return new ModelAndView("Error");
	    	}
	}
	
	@RequestMapping("/historiaClinica")
 	public ModelAndView verHistoria(HttpServletRequest request) {
  		String rol = (String) request.getSession().getAttribute("ROL");
  		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
  		
 	    List<AnimalDeGranja> animales= servicioGanado.listar();
 	    HashSet<AnimalDeGranja> animalesNoRepetidos= new HashSet<>();
 	    
 	    for(AnimalDeGranja animal : animales) {
 	    	animalesNoRepetidos.add(animal);
 	    }
 	    
 	    ModelMap modelo= new ModelMap();
 	    
 	    modelo.put("animales",animalesNoRepetidos);
 	    modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
 	    modelo.put("mostrarTabla", "si");
 	    
 	    return new ModelAndView("historiaClinica", modelo);
	}
	
   	@RequestMapping("/verhistoria")
	public ModelAndView hc(@RequestParam(value="id", required=true) Long id, HttpServletRequest request){
  		String rol = (String) request.getSession().getAttribute("ROL");
  		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
  		
   		HistoriaClinica hc= new HistoriaClinica();
   		AnimalDeGranja animal= servicioGanado.ver(id);
   		hc = servicioGanado.verHC(animal);
   		List<SignosVitales> signos= servicioGanado.signos(hc);
   		
   		ModelMap modelo= new ModelMap();
   		modelo.put("hc",hc);
   		modelo.put("signos",signos);
   		modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
   		
   		return new ModelAndView("historiaClinica", modelo);
   	}
   	
   	@RequestMapping("/diagnosticar")
	public ModelAndView diagnosticar(@RequestParam(value="id", required=true) Long id, HttpServletRequest request){
  		String rol = (String) request.getSession().getAttribute("ROL");
  		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
  		
   		Long idAnimal = id;
   		Sintomas sintomas= new Sintomas();
   		ModelMap modelo= new ModelMap();
   		
   		modelo.put("sintomas",sintomas);
   		modelo.put("idAnimal", idAnimal);
   		modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
   		
   		return new ModelAndView("consultaVeterinario", modelo);
   	}
   	
   	@RequestMapping("/diagnosticarPost")
	public ModelAndView diagnostico(@ModelAttribute("sintomas") Sintomas sintomas, HttpServletRequest request){
  		String rol = (String) request.getSession().getAttribute("ROL");
  		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
  		
   		HistoriaClinica hc= new HistoriaClinica();
   		AnimalDeGranja animal= servicioGanado.ver(sintomas.getIdAnimal());
   		hc = servicioGanado.verHC(animal);
   		List<SignosVitales>signos= servicioGanado.signos(hc);
   		String enfermedad= servicioGanado.diagnosticar(signos,sintomas);
   		
   		String diagnostico="El animal podria tener "+ enfermedad;
   		Enfermedad enfermedad1= new Enfermedad();
   		enfermedad1.setHistoria(hc);
   		enfermedad1.setNombre(enfermedad);
   		Date actual= new Date();
   		enfermedad1.setFecha(actual);
   		servicioGanado.guardarEnfermedad(enfermedad1);
   		
   		ModelMap modelo= new ModelMap();
   		modelo.put("enfermedad",diagnostico);
   		modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
   		
   		return new ModelAndView("historiaClinica", modelo);
   	}
   	
   	@RequestMapping("/enfermedades")
	public ModelAndView enfermedades(HttpServletRequest request){
  		String rol = (String) request.getSession().getAttribute("ROL");
  		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
  		
   		List<AnimalDeGranja> animales= servicioGanado.listar();
   		HashSet<AnimalDeGranja> animalesNoRepetidos= new HashSet<>();
   		
   		for(AnimalDeGranja animal : animales) {
   			animalesNoRepetidos.add(animal);
        }
   		
   		List<Enfermedad> enfermedades= new ArrayList<Enfermedad>();
   		Integer cantFiebre = 0;
   		Integer cantLeptos = 0;
   		Integer cantMio = 0;
   		Integer cantInto = 0;
   		Integer cantRino = 0;
   		
   		enfermedades=servicioGanado.todasEnfermedades();
   		
   		for(Enfermedad e: enfermedades) {
        	if(e.getNombre().equals("Fiebre Aftosa")) {
        		cantFiebre=cantFiebre+1;
        	} if(e.getNombre().equals("Leptospirosis")) {
        	  cantLeptos=cantLeptos+1;
        	} if(e.getNombre().equals("Miocardiopatia congenita")) {
        		cantMio= cantMio+1;
        	}if(e.getNombre().equals("Intoxicacion por consumo de plantas toxicas")) {
        		cantInto= cantInto+1;
        	}if(e.getNombre().equals("Rinotraqueitis infecciosa")) {
        		cantRino = cantRino+1;
        	}
        }
   		
   		HashMap<String, Integer> todos= new HashMap<String,Integer>();
   		todos.put("Fiebre Aftosa", cantFiebre);
   		todos.put("Leptospirosis", cantLeptos);
   		todos.put("Miocardiopatia congenita", cantMio);
   		todos.put("Intoxicacion alimentaria", cantInto);
   		todos.put("Rinotraqueitis infecciosa", cantRino);
   		
   		ModelMap modelo= new ModelMap();
   		modelo.put("ranking",todos);
   		modelo.put("enfermedades",enfermedades);
   		modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
   		
   		return new ModelAndView("historiaClinica", modelo);
   	}
   	
   	@RequestMapping("/modificarSignos")
	public ModelAndView modsignos(@RequestParam(value="id", required=true) Long id, HttpServletRequest request) {
  		String rol = (String) request.getSession().getAttribute("ROL");
  		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
  		
   		ModelMap modelo= new ModelMap();
   		modelo.put("idSigno",id);
   		modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
   		
   		return new ModelAndView("modificarSignos", modelo);
   	}
   	
   	@RequestMapping("/modificarSignosPost")
	public ModelAndView modsignos(@ModelAttribute("signosAttribute") SignosVitales signos, HttpServletRequest request) {
  		String rol = (String) request.getSession().getAttribute("ROL");
  		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
  		
   		Date actual= new Date();
   		signos.setFecha(actual);
   		servicioGanado.modificarSignos(signos);
   		
   		ModelMap modelo= new ModelMap();
   		List<AnimalDeGranja> animales= servicioGanado.listar();
   		HashSet<AnimalDeGranja> animalesNoRepetidos= new HashSet<>();
   		
   		for(AnimalDeGranja animal : animales) {
   			animalesNoRepetidos.add(animal);
   		}
   		
   		modelo.put("animales",animalesNoRepetidos);
        modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
   		
   		return new ModelAndView("HomeAnimal", modelo);
   	}
	
	@RequestMapping("/signosVitales")
 	public ModelAndView signosVitales(HttpServletRequest request, @RequestParam(value="id", required=true) Long id) {
  		String rol = (String) request.getSession().getAttribute("ROL");
  		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
		ModelMap modelo= new ModelMap();
		
		String enfermedad = "";
		Boolean cardio1 = true;
		Boolean orina1 = true;
		Boolean temperatura1 = true;
		Boolean respiracion1 = true;
		switch(enfermedad){
		case "Fiebre Aftosa":
			cardio1 = false;
			respiracion1 = false;
			break;
		case "Leptospirosis":
			orina1 = false;
			temperatura1 = false;
			break;
		case "Miocardiopatia congenita":
			cardio1 = false;
			respiracion1 = false;
			break;
		case "Rinotraqueitis infecciosa":
			temperatura1 = false;
			respiracion1 = false;
			break;
		case "Intoxicacion por consumo de plantas toxicas":
			cardio1 = false;
			orina1 = false;
			temperatura1 = false;
			respiracion1 = false;
			break;
		}
		
		modelo.put("cardio1", cardio1);
		modelo.put("orina1", orina1);
		modelo.put("temperatura1", temperatura1);
		modelo.put("respiracion1", respiracion1);
		modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
		modelo.addAttribute("enfermedadClase", enfermedad);
		
        return new ModelAndView("signosVitales", modelo);
	}
	
}
