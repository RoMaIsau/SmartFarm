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
	public ModelAndView irAIndexVeterinario(HttpServletRequest request) {
		// Verifica que sea un usuario de tipo Veterinario, si no lo es, lo redirige al login
	String rol = (String) request.getSession().getAttribute("ROL");
		
		ModelMap model = new ModelMap();
		model.put("notificaciones", listarNotificacionesDelVeterinario(request));
		
		if(rol.equals("Veterinario")) {

	      
          
          
		return new ModelAndView("redirect:/historiaClinica");}
		else if(!rol.equals("Veterinario")) {
            return new ModelAndView("redirect:/historiaClinica", model);
		} else {return new ModelAndView("redirect:/login");}}

	

	@RequestMapping("/detalle")
	public ModelAndView vacunar(@RequestParam(value="id", required=true) Long id)  {
		   ModelMap modelo= new ModelMap();
           
		   AnimalDeGranja gv =servicioGanado.ver(id);
		   
        List<Vacuna>v = servicioVacuna.alarmaVacuna(gv);
       
        
         modelo.put("vencidas",v);
         modelo.put("vacaId",id);
         modelo.put("volver","VOLVER");
       
         
         
           return new ModelAndView("historiaClinica", modelo);
		
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
         modelo.put("volver","VOLVER");
         
           return new ModelAndView("historiaClinica", modelo);
		
	}
	

	
	
	@RequestMapping("/listaGanado")
	public ModelAndView listaGanado() {
	     
	       
             Date actual= new Date();
             
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
            vacasAnormales.add(v);}
  	       
            	 }}}}
  	   
  	   
  	
  	   
	
  	    ModelMap modelo= new ModelMap();
        
        modelo.put("anormales",vacasAnormales);
        modelo.put("animales",animalesConHistoria);
        modelo.put("signos2",signosAnormales);
        modelo.put("signos3",signos2);
      
    
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
	public ModelAndView verSalud(@RequestParam(value="id", required=true) Long id) {
	
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
		}}
		String alarma="";
	      HistoriaClinica historia= servicioGanado.verHC(gv);
	      if(historia != null){
	      tratamiento= servicioGanado.alarmaTratamientoA(historia);
	      enfermedadA=servicioGanado.tipoTratamientoA(historia);
	      enfermedadB=servicioGanado.tipoTratamientoB(historia);}
	      if(tratamiento == true) {
	      alarma="Se requiere un nuevo diagnosico para el animal "+ id ;}
          
	      if(enfermedadA != null) {
		      nombreTratamiento=servicioGanado.tratamientoA(enfermedadA.getNombre());}
	      
	      if(enfermedadB != null) {
		      nombreTratamiento=servicioGanado.tratamientoB(enfermedadB.getNombre());}
	          
	      
	      List<SignosVitales> signos= servicioGanado.signos(historia);
	      
             ModelMap modelo= new ModelMap();
             
             if(signos.size()>0) {
            	 int pos= signos.size();
            	 int pos2=pos-1;
             SignosVitales signo= signos.get(pos2);
            // modelo.put("signos",signo);
             modelo.put("alarmaTratamiento",alarma);
             modelo.put("idAnimalTratamiento",id);
             modelo.put("vacunas",vacunas);
             modelo.put("nombreTratamiento",nombreTratamiento);
             if(enfermedadA != null){
             modelo.put("enfermedadA",enfermedadA.getNombre());}
             if(enfermedadB != null) {
             modelo.put("enfermedadB",enfermedadB.getNombre());}
             
            
           
           
             return new ModelAndView("HomeAnimal", modelo);}
	else {
		 return new ModelAndView("Error"); 
	}}
	
	
  	   
  	 @RequestMapping("/historiaClinica")
 	public ModelAndView verHistoria(HttpServletRequest request) {
  		String rol = (String) request.getSession().getAttribute("ROL");
 	       
              
              
              List<AnimalDeGranja> animales= servicioGanado.listar();
              HashSet<AnimalDeGranja> animalesNoRepetidos= new HashSet<>();
              for(AnimalDeGranja animal : animales) {
             	 animalesNoRepetidos.add(animal);
              }
              
              HashSet<AnimalDeGranja> animalesVencidos= new HashSet<AnimalDeGranja>();
   	       for(AnimalDeGranja v: animalesNoRepetidos) {
   	      
             List<Vacuna> vencidas= servicioVacuna.alarmaVacuna(v);
             
             if(!vencidas.isEmpty()){
            
             animalesVencidos.add(v);}}
   	       
   	      
              ModelMap modelo= new ModelMap();
              modelo.put("vencidos",animalesVencidos);
    
    modelo.put("animales",animalesNoRepetidos);
    modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
    modelo.put("mostrarTabla", "si");
 
  

  return new ModelAndView("historiaClinica", modelo);
             	
   	      
   	   
   	    }
  	 
   	   
   	@RequestMapping("/verhistoria")
	public ModelAndView hc(@RequestParam(value="id", required=true) Long id){
	     
	       
        
	   
	  HistoriaClinica hc= new HistoriaClinica();
	
	      
     AnimalDeGranja animal= servicioGanado.ver(id);
        hc = servicioGanado.verHC(animal);
       List<SignosVitales> signos= servicioGanado.signos(hc);
       	List<Enfermedad>enfermedades= servicioGanado.enfermedadesComunes(hc);
	       
               ModelMap modelo= new ModelMap();
             
               modelo.put("hc",hc);
               modelo.put("signos",signos);
               if(enfermedades != null) {
               modelo.put("enfermedades",enfermedades);}
             
            
             
           
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
	
	
	   
	@RequestMapping(value="/diagnosticarPost", method = RequestMethod.POST)
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
    if(signos == null) {  return new ModelAndView("historiaClinica");}
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
	   Date actual= new Date();
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
	   Date actual= new Date();   
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
         
         
          
        
          return new ModelAndView("Enfermedades", modelo);
          
	}
	
	
	
	@RequestMapping("/buscarEnfermedad")
	public ModelAndView buscar(@ModelAttribute("tratamiento") Tratamiento busqueda) {
		
		String nombre= busqueda.getNombre();
		ModelMap model= new ModelMap();
		
		Tratamiento t= servicioGanado.buscarTratamiento(nombre);
		if(t != null) {
		model.put("trat",t.getTratamiento());
		model.put("descripcion",t.getDescripcion());}else {
			model.put("trat","No se encuentra esa enfermedad");	
		}
		
		return new ModelAndView("Enfermedades",model);
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
		
		String enfermedad = "Fiebre Aftosa";/* OTRA ENFERMEDAD usarlo para cambiar resultados */
		modelo.put("enfermedad", enfermedad);
		modelo.put("notificaciones", listarNotificacionesDelVeterinario(request));
		modelo.addAttribute("enfermedadClase", enfermedad);
		
        return new ModelAndView("signosVitales", modelo);
	}
	
	
}
