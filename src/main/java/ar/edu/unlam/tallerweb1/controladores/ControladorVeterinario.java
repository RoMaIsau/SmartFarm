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
	public ModelAndView irAIndexVeterinario(HttpServletRequest request) {
		// Verifica que sea un usuario de tipo Veterinario, si no lo es, lo redirige al login
	//String rol = (String) request.getSession().getAttribute("ROL");
		
		/*ModelMap model = new ModelMap();
		model.put("notificaciones", listarNotificacionesDelVeterinario(request));*/
		
		//if(rol.equals("Veterinario")) {

			
		
		 List<AnimalDeGranja> animales= servicioGanado.listar();
	      
	       HashSet<AnimalDeGranja> animalesVencidos= new HashSet<AnimalDeGranja>();
	       for(AnimalDeGranja v: animales) {
	      
          List<Vacuna> vencidas= servicioVacuna.alarmaVacuna(v);
          
          if(!vencidas.isEmpty()){
         
          animalesVencidos.add(v);}}
	       
	      
           ModelMap modelo= new ModelMap();
           modelo.put("vencidos",animalesVencidos);
           modelo.put("animales",animales);
           
          
          
		return new ModelAndView("redirect:/historiaClinica",modelo);}
		else if(!rol.equals("Veterinario")) {
            return new ModelAndView("redirect:/historiaClinica", model);
		} else {

	

	@RequestMapping("/detalle")
	public ModelAndView vacunar(@RequestParam(value="id", required=true) Long id)  {
		   ModelMap modelo= new ModelMap();
           
		   AnimalDeGranja gv =servicioGanado.ver(id);
		   
        List<Vacuna>v = servicioVacuna.alarmaVacuna(gv);
       
        
         modelo.put("vencidas",v);
         modelo.put("vacaId",id);
         modelo.put("volver","VOLVER");
       
         
         
           return new ModelAndView("indexVeterinario", modelo);
		
	}
	
	
	@RequestMapping("/vacunar")
	public ModelAndView vacunar(@RequestParam(value="id", required=true) Long id,@RequestParam(value="nombre", required=true)String nombre)  {
		   ModelMap modelo= new ModelMap();
           
		   AnimalDeGranja gv =servicioGanado.ver(id);
        Vacuna v = servicioVacuna.getVacuna(nombre);
           
         servicioVacuna.vacunar(gv, v);
         AnimalDeGranja g= servicioGanado.ver(id);
         g.getVacunasParaAplicar();
         Vacuna v1 = servicioVacuna.getVacuna(nombre);  
         modelo.put("mensaje","La vacuna "+nombre+" fue aplicada");
         
         
           return new ModelAndView("indexVeterinario", modelo);
		
	}
	

	
	
	@RequestMapping("/listaGanado")
	public ModelAndView listaGanado() {
	     
	       
             
             
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
            vacasAnormales.add(v);}
  	       }}
  	   
  	   
  	
  	   
	
  	    ModelMap modelo= new ModelMap();
        
        modelo.put("anormales",vacasAnormales);
        modelo.put("animales",animalesNoRepetidos);
        modelo.put("signos2",signosAnormales);
        modelo.put("signos3",signos2);
      
    
      return new ModelAndView("HomeAnimal", modelo);
      }
	
	@RequestMapping("/verEstadoSalud")
	public ModelAndView verSalud(@RequestParam(value="id", required=true) Long id) {
	
             
		AnimalDeGranja gv= servicioGanado.ver(id);
		String alarma="";
	      HistoriaClinica historia=gv.getHistoria();
	      Boolean tratamiento= servicioGanado.alarmaTratamientoA(historia);
	      if(tratamiento == true) {
	      alarma="Ya han pasado 4 dias desde que el animal "+ id + " ha comenzado su tratamiento. Se requiere un nuevo diagnostico";}
             
	      List<SignosVitales> signos= servicioGanado.signos(historia);
	      
             ModelMap modelo= new ModelMap();
             
             if(signos != null) {
            	 int pos= signos.size();
            	 int pos2=pos-1;
             SignosVitales signo= signos.get(pos2);
             modelo.put("signos",signo);
             modelo.put("alarmaTratamiento",alarma);
             modelo.put("idAnimalTratamiento",id);
            
           
           
             return new ModelAndView("HomeAnimal", modelo);}
	else {
		 return new ModelAndView("Error"); 
	}}
	
	
  	   
  	 @RequestMapping("/historiaClinica")
 	public ModelAndView verHistoria(HttpServletRequest request) {
  		//String rol = (String) request.getSession().getAttribute("ROL");
 	       
              
              
              List<AnimalDeGranja> animales= servicioGanado.listar();
              HashSet<AnimalDeGranja> animalesNoRepetidos= new HashSet<>();
              for(AnimalDeGranja animal : animales) {
             	 animalesNoRepetidos.add(animal);
              }
   	      
   	 
   	   
   	   
   	ModelMap modelo= new ModelMap();
    
    modelo.put("animales",animalesNoRepetidos);
   // modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
    modelo.put("mostrarTabla", "si");
 
  

  return new ModelAndView("historiaClinica", modelo);
             	
   	      
   	   
   	    }
  	 
   	   
   	@RequestMapping("/verhistoria")
	public ModelAndView hc(@RequestParam(value="id", required=true) Long id){
	     
	       
        
	   
	  HistoriaClinica hc= new HistoriaClinica();
	
	      
     AnimalDeGranja animal= servicioGanado.ver(id);
        hc = servicioGanado.verHC(animal);
       List<SignosVitales> signos= servicioGanado.signos(hc);
       	
	       
               ModelMap modelo= new ModelMap();
             
               modelo.put("hc",hc);
               modelo.put("signos",signos);
             
            
             
           
             return new ModelAndView("historiaClinica", modelo);
             
	}
   	
   
   	
   	
	@RequestMapping("/diagnosticar")
	public ModelAndView diagnosticar(@RequestParam(value="id", required=true) Long id){
	     
	       
	   Long idAnimal = id;
       Sintomas sintomas= new Sintomas();
	
            ModelMap modelo= new ModelMap();
          
            modelo.put("sintomas",sintomas);
          modelo.put("idAnimal", idAnimal);
         
          
        
          return new ModelAndView("consultaVeterinario", modelo);
          
	}
	
	@RequestMapping("/guardarEnfermedad")
	public ModelAndView guardarEnfermedad(@RequestParam(value="id", required=true) Long id,@RequestParam(value="nombre", required=true) String nombre,@RequestParam(value="tratamiento", required=true) String tratamiento){
		 
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
		       return new ModelAndView("historiaClinica", modelo);    
	
	}
	
	
	   
	@RequestMapping("/diagnosticarPost")
	public ModelAndView diagnostico(@ModelAttribute("sintomas") Sintomas sintomas){
	     
	      
	  HistoriaClinica hc= new HistoriaClinica();
	AnimalDeGranja animal= servicioGanado.ver(sintomas.getIdAnimal());
	      
	 String tratamiento=null;
	 String tratamientoB=null;
	 String curada=null;
	   String sugerenciaB=null;
	   String sugerencia=null;
	  
     hc = servicioGanado.verHC(animal);
    List<SignosVitales>signos= servicioGanado.signos(hc);
    List<Enfermedad> guardadas= servicioGanado.enfermedadesComunes(hc);
    Enfermedad guardada= null;
    Enfermedad sinTratar= null;
    for(Enfermedad e: guardadas) {
    	if(e.getFinTratamiento()== null && e.getInicioTratamiento()!= null) {
    		guardada=e;
    	}else if(e.getFinTratamiento()== null && e.getInicioTratamiento()== null){
    		sinTratar=e;
    	}
    }
    
    if(guardada!= null) {
    	Date fechaSignos= new Date(2020,01,01);
    	sintomas.setFechaSignosVitales(fechaSignos);}
    
	   String enfermedad= servicioGanado.diagnosticar(signos,sintomas); 
	   String diagnostico="El animal podria tener "+ enfermedad;
	   
	   if(enfermedad != "No hay suficientes sintomas de enfermedad") {
	   if(guardada == null && guardadas != null) {
	   tratamiento= servicioGanado.tratamientoA(enfermedad);
	   sugerencia="Se sugiere realizar un tratamiento con "+ tratamiento;}
	  
	   else if(guardada != null && guardada.getTratamientoB()==null){
		   tratamientoB= servicioGanado.tratamientoB(enfermedad); 
		   sugerenciaB="Se sugiere cambiar a tratamiento con "+ tratamientoB;
	   }
		   
	   }else if(enfermedad == "No hay suficientes sintomas de enfermedad" && guardada != null) {
		   curada="El animal se ha recuperado";}
	   else if(enfermedad != "No hay suficientes sintomas de enfermedad" && guardada != null && guardada.getTratamientoB().equals("Iniciado")) {
		   curada="El animal no se ha recuperado. Realice una interconsulta";
	   }
	   
            
            ModelMap modelo= new ModelMap();
          
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
	public ModelAndView diagnosticarA(@RequestParam(value="id", required=true) Long id) throws ParseException{
	     
	       
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
         
          
        
          return new ModelAndView("consultaVeterinario", modelo);
          
	}
	
	@RequestMapping("/tratamientoA")
	public ModelAndView tratamientoA(@RequestParam(value="id", required=true) Long id) {
	
	
	 Enfermedad enfermedad= servicioGanado.buscarEnfermedad(id) ;   
	   Date actual= new Date(2020,06,07);
	   enfermedad.setId(id);
	   
	   enfermedad.setInicioTratamiento(actual);
		 enfermedad.setTratamientoA("Iniciado"); 
     servicioGanado.updateEnfermedad(enfermedad);
        
        ModelMap modelo= new ModelMap();
        
        modelo.put("mensaje","El tratamiento fue iniciado");
         
         
          
        
          return new ModelAndView("historiaClinica", modelo);
          
	}
	
	@RequestMapping("/tratamientoB")
	public ModelAndView tratamientoB(@RequestParam(value="id", required=true) Long id) {
	
	
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
         
         
          
        
          return new ModelAndView("historiaClinica", modelo);
          
	}
	

	@RequestMapping("/finTratamiento")
	public ModelAndView fin(@RequestParam(value="id", required=true) Long id) {
	
	
	 Enfermedad enfermedad= servicioGanado.buscarEnfermedad(id) ;   
	   Date actual= new Date();   
	   
	   enfermedad.setTratamientoA("finalizado");
	   enfermedad.setTratamientoB("finalizado");
	   enfermedad.setId(id);
	   enfermedad.setFinTratamiento(actual);
		
     servicioGanado.updateEnfermedad(enfermedad);
        
        ModelMap modelo= new ModelMap();
        
        modelo.put("mensaje","El tratamiento fue finalizado");
         
         
          
        
          return new ModelAndView("historiaClinica", modelo);
          
	}
	
	@RequestMapping("/enfermedades")
	public ModelAndView enfermedades(){
	     
	       
	   
		  
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
           // modelo.put("enfermedades",enfermedades);
           // modelo.put("enfermedades",todos);
         
         
          
        
          return new ModelAndView("Enfermedades", modelo);
          
	}
	
	
	
	@RequestMapping("/modificarSignos")
	public ModelAndView modsignos(@RequestParam(value="id", required=true) Long id) {
	
             
		
	     
	      
             
             ModelMap modelo= new ModelMap();
             
           modelo.put("idSigno",id);
             
            
             
           
             return new ModelAndView("modificarSignos", modelo);
             
	}
	
	@RequestMapping("/modificarSignosPost")
	public ModelAndView modsignos(@ModelAttribute("signosAttribute") SignosVitales signos) {
	
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
             
            
             
            
             
           
             return new ModelAndView("HomeAnimal", modelo);
             
	}
	
	@RequestMapping("/signosVitales")
 	public ModelAndView signosVitales(HttpServletRequest request, @RequestParam(value="id", required=true) Long id) {
  		String rol = (String) request.getSession().getAttribute("ROL");
  		if (!rol.equals("Veterinario") || rol == null) {
			return new ModelAndView("redirect:/login");
		}
  		
		ModelMap modelo= new ModelMap();
		modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
		
        return new ModelAndView("signosVitales", modelo);
	}
	
	
}
