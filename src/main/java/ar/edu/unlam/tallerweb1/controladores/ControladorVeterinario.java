package ar.edu.unlam.tallerweb1.controladores;


import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Enfermedad;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.Sintomas;
import ar.edu.unlam.tallerweb1.modelo.Tratamiento;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;
import ar.edu.unlam.tallerweb1.modelo.Vacunar;
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
		modelo.put("volver","VOLVER");
        modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
        
        return new ModelAndView("indexVeterinario", modelo);
	}
	
	@RequestMapping("/listaGanado")
	public ModelAndView listaGanado(HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("ROL");
		if (!rol.equals("Veterinario") || rol == null) {
		  return new ModelAndView("redirect:/login");
		}
		
		Date actual= new Date(2020,01,10);
        List<AnimalDeGranja> animales= servicioGanado.listar();
        HashSet<AnimalDeGranja> animalesNoRepetidos= new HashSet<>();
        HashSet<AnimalDeGranja> animalesConHistoria= new HashSet<>();
		
		for(AnimalDeGranja animal : animales) {
        	 animalesNoRepetidos.add(animal);
        }
		  
		List< SignosVitales> signosAnormales= new ArrayList<SignosVitales>();
  	    HashSet< AnimalDeGranja> vacasAnormales= new HashSet<AnimalDeGranja>();
  	 	List<SignosVitales> signos1= new ArrayList<SignosVitales>(); 
  	 	SignosVitales signos2= new SignosVitales();
			 
		for(AnimalDeGranja v: animalesNoRepetidos) {
			HistoriaClinica hc= servicioGanado.verHC(v);
			  
			if(hc != null) {
 				animalesConHistoria.add(v);
            	signos1= servicioGanado.signos(hc);
				
				if(signos1 != null) {
            		for(SignosVitales sv : signos1){
            			// if(sv.getFecha().compareTo(actual)== 0){
            			Boolean res= servicioGanado.alarmaSV(sv);
						
						if(res == true){
						vacasAnormales.add(v);
						}
					}
				}
			}
		}
		  
		ModelMap modelo= new ModelMap();
        modelo.put("anormales",vacasAnormales);
        modelo.put("animales",animalesConHistoria);
        modelo.put("signos2",signosAnormales);
        modelo.put("signos3",signos2);
        modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
        
        return new ModelAndView("HomeAnimal", modelo);
    }
	
	@RequestMapping("/signos")
	public ModelAndView signosAnormales(@RequestParam(value="id", required=true) Long id) {
	
		
		 ModelMap modelo= new ModelMap();
		AnimalDeGranja gv= servicioGanado.ver(id);
		
	      HistoriaClinica historia= servicioGanado.verHC(gv);
	      if(historia != null){
	    
	          
	      
	      List<SignosVitales> signos= servicioGanado.signos(historia);
	      
            
             
             if(signos.size()>0) {
            	 int pos= signos.size();
            	 int pos2=pos-1;
             SignosVitales signo= signos.get(pos2);
             modelo.put("signos",signo);
           }
	      }
            
           
           
             return new ModelAndView("HomeAnimal", modelo);}
	
	
	@RequestMapping("/verEstadoSalud")
	public ModelAndView verSalud(@RequestParam(value="id", required=true) Long id, HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("ROL");
  		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
	
		Boolean tratamiento= false; 
		Enfermedad enfermedadA=null;
		Enfermedad enfermedadB=null;
		String nombreTratamiento="";
		AnimalDeGranja gv= servicioGanado.ver(id);
		List<Vacunar>aplicadas= new ArrayList<Vacunar>();
		aplicadas= servicioVacuna.obtenerVacunasAplicadas(id);
		HashSet<Vacuna>vacunas= new HashSet<Vacuna>();
		
		if(aplicadas != null) {
			for(Vacunar v : aplicadas) {
				vacunas.add(v.getVacuna());
			}
		}
		
		String alarma="";
	    HistoriaClinica historia= servicioGanado.verHC(gv);
		
		if(historia != null){
			tratamiento= servicioGanado.alarmaTratamientoA(historia);
		}
		
		if(tratamiento == true) {
			alarma="Ya han pasado 4 dias desde que el animal "+ id + " ha comenzado su tratamiento. Se requiere un nuevo diagnostico";
		}
		
		List<SignosVitales> signos= servicioGanado.signos(historia);
	    ModelMap modelo= new ModelMap();
		
		if(signos.size()>0) {
        	int pos= signos.size();
            int pos2=pos-1;
            SignosVitales signo= signos.get(pos2);
            modelo.put("signos",signo);
            modelo.put("alarmaTratamiento",alarma);
            modelo.put("idAnimalTratamiento",id);
            modelo.put("vacunas",vacunas);/*
			modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));*/

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
		
		HashSet<AnimalDeGranja> animalesVencidos= new HashSet<AnimalDeGranja>();

   	    for(AnimalDeGranja v: animalesNoRepetidos) {
			   List<Vacuna> vencidas= servicioVacuna.alarmaVacuna(v);
				
			   if(!vencidas.isEmpty()){
				   animalesVencidos.add(v);
				}
		}

		ModelMap modelo= new ModelMap();
        modelo.put("vencidos",animalesVencidos);
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
   	
   	@RequestMapping("/diagnosticarPost")
	public ModelAndView diagnostico(HttpServletRequest request, @ModelAttribute("sintomas") Sintomas sintomas){
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
	
	@RequestMapping("/guardarEnfermedad")
	public ModelAndView guardarEnfermedad(@RequestParam(value="id", required=true) Long id, HttpServletRequest request,
										  @RequestParam(value="nombre", required=true) String nombre,@RequestParam(value="tratamiento", required=true) String tratamiento){
		String rol = (String) request.getSession().getAttribute("ROL");
		if (!rol.equals("Veterinario") || rol == null) {
		return new ModelAndView("redirect:/login");
		}
		 
		HistoriaClinica historia = servicioGanado.verHC(id);
		List<Enfermedad> guardadas= servicioGanado.enfermedadesComunes(historia); 
		Long idEnf= 0L;
		
		Enfermedad enfermedad1= new Enfermedad();
	    enfermedad1.setHistoria(historia);
	    enfermedad1.setNombre(nombre);
		
		Date actual= new Date();
		enfermedad1.setFecha(actual);
		servicioGanado.guardarEnfermedad(enfermedad1);

		List<Enfermedad> guardadas1= servicioGanado.enfermedadesComunes(historia);
		for(Enfermedad e: guardadas1) {
			if(e.getNombre().equals(nombre)) {
				idEnf=e.getId();
			}
		}

		ModelMap modelo= new ModelMap();
		modelo.put("guardada", "La enfermedad ha sido registrada en la historia clinica");
		modelo.put("e1Id",idEnf);
		modelo.put("tratamiento",tratamiento);
		modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));

		return new ModelAndView("historiaClinica", modelo);
	}
	
	
	   
	@RequestMapping(value="/diagnosticarPost", method = RequestMethod.POST)
	public ModelAndView diagnostico(@ModelAttribute("sintomas") Sintomas sintomas, HttpServletRequest request){
		String rol = (String) request.getSession().getAttribute("ROL");
		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
		ModelMap modelo= new ModelMap();
        modelo.put("notificaciones", listarNotificacionesDelVeterinario(request)); 
		  
		HistoriaClinica hc= new HistoriaClinica();
		AnimalDeGranja animal= servicioGanado.ver(sintomas.getIdAnimal());
		
		String tratamiento=null;
	 	String tratamientoB=null;
	 	String curada=null;
	  	String sugerenciaB=null;
	   	String sugerencia=null;
		
		hc = servicioGanado.verHC(animal);
    	List<SignosVitales>signos= servicioGanado.signos(hc);
		
		if(signos == null) {
			return new ModelAndView("historiaClinica", modelo);
		}

		List<Enfermedad> guardadas= servicioGanado.enfermedadesComunes(hc);
    	Enfermedad guardada= null;
    	Enfermedad sinTratar= null;
		
		for(Enfermedad e: guardadas) {
    		if(e.getFinTratamiento()== null && e.getInicioTratamiento()!= null) {
    			guardada=e;
    		} else if(e.getFinTratamiento()== null && e.getInicioTratamiento()== null){
    		sinTratar=e;
    		}
		}
		
		String enfermedad = servicioGanado.diagnosticar(signos,sintomas);
		String diagnostico = "No hay suficientes síntomas de enfermedad";
		
		if(enfermedad != "No hay suficientes sintomas de enfermedad") {
			diagnostico = "El animal podria tener " + enfermedad;
		}
		
		/*
		Fiebre Aftosa = anorexia, salivacionEspumosa
		Leptospirosis = anorexia, debilidad, secrecionNasal
		Miocardiopatia congenita = 	Ulceras, debilidad, tos
		Rinotraqueitis infecciosa = anorexia, bajaProduccionLeche, secrecionNasal, conjuntivitis
		Intoxicacion por consumo de plantas toxicas = Ulceras, anorexia, debilidad, salivacionEspumosa, bajaProduccionLeche, diarrea, conjuntivitis, tos, secrecionNasal
		*/
		
		if(enfermedad != "No hay suficientes sintomas de enfermedad") {
			if(guardada == null && guardadas != null) {
	   			tratamiento= servicioGanado.tratamientoA(enfermedad);
				sugerencia="Se sugiere realizar un tratamiento con "+ tratamiento;
			} else if(guardada != null && guardada.getTratamientoB()==null){
				tratamientoB= servicioGanado.tratamientoB(enfermedad); 
		   		sugerenciaB="Se sugiere cambiar a tratamiento con "+ tratamientoB;
	   			}
	   	}else if(enfermedad == "No hay suficientes sintomas de enfermedad" && guardada != null) {
			   curada="El animal se ha recuperado";
			} else if(enfermedad != "No hay suficientes sintomas de enfermedad" && guardada != null && guardada.getTratamientoB().equals("Iniciado")){
				curada="El animal no se ha recuperado. Realice una interconsulta";
		   }
		
        modelo.put("nombre",enfermedad);
        modelo.put("enfermedad",diagnostico);
        modelo.put("tratamiento",sugerencia);
        modelo.put("tratamientoB",tratamientoB);
        modelo.put("curada",curada);
        modelo.put("hcId",hc.getId());
		
		if(guardada != null) {
			modelo.put("e1Id",guardada.getId());
        }else if(guardada == null  && sinTratar != null) {
			modelo.put("e1Id",sinTratar.getId());
        }
           
		return new ModelAndView("historiaClinica", modelo);
	}
	
	@RequestMapping("/nuevoDiagnostico")
	public ModelAndView diagnosticarA(@RequestParam(value="id", required=true) Long id, HttpServletRequest request) throws ParseException{
		String rol = (String) request.getSession().getAttribute("ROL");
		if (!rol.equals("Veterinario") || rol == null) {
		  return new ModelAndView("redirect:/login");
		}
		
		Long idAnimal = id;
	   	AnimalDeGranja animal=servicioGanado.ver(id);
	   	HistoriaClinica historia= servicioGanado.verHC(animal);
	   	List<Enfermedad>enfermedades=servicioGanado.enfermedadesComunes(historia);
		Enfermedad enfermedad=null;
		
		for(Enfermedad e: enfermedades) {
			if(e.getFinTratamiento()== null && e.getInicioTratamiento() != null) {
				 enfermedad=e;
			}
		}
		
	    Date fechaInicioTratamiento= enfermedad.getInicioTratamiento();
	    SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy"); 
	    String date = dt.format(fechaInicioTratamiento);
	    Date fecha= dt.parse(date);
	    Sintomas sintomas= new Sintomas();
	    sintomas.setFechaSignosVitales(fecha);
		
		ModelMap modelo= new ModelMap();
        modelo.put("sintomas",sintomas);
        modelo.put("idAnimal", idAnimal);
        modelo.put("fecha", fecha);
		modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
		
		return new ModelAndView("consultaVeterinario", modelo);
	}
	
	@RequestMapping("/tratamientoA")
	public ModelAndView tratamientoA(@RequestParam(value="id", required=true) Long id, HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("ROL");
		if (!rol.equals("Veterinario") || rol == null) {
		  return new ModelAndView("redirect:/login");
	  	}
		
		Enfermedad enfermedad= servicioGanado.buscarEnfermedad(id) ;   
	    Date actual= new Date(2020,06,07);
	    enfermedad.setId(id);
	    enfermedad.setInicioTratamiento(actual);
		enfermedad.setTratamientoA("Iniciado"); 
     	servicioGanado.updateEnfermedad(enfermedad);
        
        ModelMap modelo= new ModelMap();
        modelo.put("mensaje","El tratamiento fue iniciado");
		modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
		
		return new ModelAndView("historiaClinica", modelo);
	}
	
	@RequestMapping("/tratamientoB")
	public ModelAndView tratamientoB(@RequestParam(value="id", required=true) Long id, HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("ROL");
		if (!rol.equals("Veterinario") || rol == null) {
		  return new ModelAndView("redirect:/login");
	  	}
		
		Enfermedad enfermedad= servicioGanado.buscarEnfermedad(id) ;   
	    Date actual= new Date(2020,06,07);   
	    enfermedad.setInicioTratamiento(actual);
	    enfermedad.setId(id);
	    enfermedad.setTratamientoA("finalizado");
	    enfermedad.setTratamientoB("Iniciado");
	    enfermedad.setInicioTratamiento(actual);
		servicioGanado.updateEnfermedad(enfermedad);
        
        ModelMap modelo= new ModelMap();
        modelo.put("mensaje","El tratamiento fue reiniciado");
		modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
		
		return new ModelAndView("historiaClinica", modelo);
	}
	
	@RequestMapping("/finTratamiento")
	public ModelAndView fin(@RequestParam(value="id", required=true) Long id, HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("ROL");
		if (!rol.equals("Veterinario") || rol == null) {
		  return new ModelAndView("redirect:/login");
	  	}
		
		Enfermedad enfermedad= servicioGanado.buscarEnfermedad(id) ;   
	    Date actual= new Date();   
	    enfermedad.setTratamientoA("finalizado");
	    enfermedad.setTratamientoB("finalizado");
	    enfermedad.setId(id);
	    enfermedad.setFinTratamiento(actual);
		servicioGanado.updateEnfermedad(enfermedad);
        
        ModelMap modelo= new ModelMap();
        modelo.put("mensaje","El tratamiento fue finalizado");
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
        List<Enfermedad>enfermedadesTodas= new ArrayList<Enfermedad>();
        Integer cantFiebre= 0;
        Integer cantLeptos= 0;
        Integer cantMio= 0;
        Integer cantInto=0;
        Integer cantRino= 0;
        
        Integer abril=0;
        Integer mayo= 0;
        Integer junio= 0;
        Integer julio= 0;
        Integer agosto=0;
        Integer sept=0;
		
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
        
        for(Enfermedad e: enfermedades) {
        	if(e.getFecha().getMonth()== 03) {
        		abril= abril+1;}
        	if(e.getFecha().getMonth()== 04) {
        		mayo= mayo+1;}
        	if( e.getFecha().getMonth()== 05) {
        		junio=junio+1;
        	} if(e.getFecha().getMonth()== 06) {
        	  julio=julio+1;
        	} if(e.getFecha().getMonth()== 07) {
        		agosto= agosto+1;
        	}if(e.getFecha().getMonth()== 8) {
        		sept= sept+1;}
        }
        
        ArrayList<Integer> todos= new ArrayList<Integer>();
        todos.add(cantFiebre);
        todos.add(cantLeptos);
        todos.add(cantMio);
        todos.add(cantInto);
        todos.add(cantRino);
        
        ArrayList<Integer>mes= new ArrayList<Integer>();
        mes.add(abril);
        mes.add(mayo);
        mes.add(junio);
        mes.add(julio);
        mes.add(agosto);
        mes.add(sept);
		
		Tratamiento tratamiento = new Tratamiento();
		
		ModelMap modelo= new ModelMap();
        modelo.put("todos",todos);
        modelo.put("mes",mes);
        modelo.put("tratamiento", tratamiento);
		modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
		
		return new ModelAndView("Enfermedades", modelo);
	}
	
	@RequestMapping("/buscarEnfermedad")
	public ModelAndView buscar(@ModelAttribute("tratamiento") Tratamiento busqueda, HttpServletRequest request) {
		String rol = (String) request.getSession().getAttribute("ROL");
		if (!rol.equals("Veterinario") || rol == null) {
		  return new ModelAndView("redirect:/login");
	  	}
		
		String nombre= busqueda.getNombre();
		ModelMap model= new ModelMap();
		model.put("notificaciones", listarNotificacionesDelVeterinario(request));

		Tratamiento t= servicioGanado.buscarTratamiento(nombre);
		if(t != null) {
			model.put("trat",t.getTratamiento());
			model.put("descripcion",t.getDescripcion());
		} else {
			model.put("trat","No se encuentra esa enfermedad");	
		}
		
		return new ModelAndView("Enfermedades",model);
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
			temperatura1 = false;
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
		modelo.addAttribute("enfermedadClase", enfermedad);
		
        return new ModelAndView("signosVitales", modelo);
	}
	
}
