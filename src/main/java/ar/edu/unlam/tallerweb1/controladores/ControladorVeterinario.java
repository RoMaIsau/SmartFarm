package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.Date;
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
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.Sintomas;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanado;
import ar.edu.unlam.tallerweb1.servicios.ServicioVacunas;

@Controller
public class ControladorVeterinario {
	
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
		
		if(rol.equals("Veterinario")) {
			
		
		 List<AnimalDeGranja> animales= servicioGanado.listar();
	      
	       HashSet<AnimalDeGranja> animalesVencidos= new HashSet<AnimalDeGranja>();
	       for(AnimalDeGranja v: animales) {
	      
          List<Vacuna> vencidas= servicioVacuna.alarmaVacuna(v);
          
          if(!vencidas.isEmpty()){
         
          animalesVencidos.add(v);}}
	       
	      
           ModelMap modelo= new ModelMap();
           modelo.put("vencidos",animalesVencidos);
           
          
          
		return new ModelAndView("indexVeterinario",modelo);}
		else {
			return new ModelAndView("redirect:/login");
		}
		
	}
	
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
  	   HashSet< AnimalDeGranja> vacasAnormales= new HashSet<AnimalDeGranja>();
  	     
  	   for(AnimalDeGranja v: animales) {
  	      
             signosAnormales= servicioGanado.alarmaSignos(v);
             if(signosAnormales!= null) {
            	 vacasAnormales.add(v);
  	       }
  	   }
  	   
	
  	    ModelMap modelo= new ModelMap();
        
        modelo.put("anormales",vacasAnormales);
        modelo.put("animales",animalesNoRepetidos);
      
      
    
      return new ModelAndView("HomeAnimal", modelo);
      }
	
	@RequestMapping("/verEstadoSalud")
	public ModelAndView verSalud(@RequestParam(value="id", required=true) Long id) {
	
             
		AnimalDeGranja gv= servicioGanado.ver(id);
	     
	      SignosVitales signos=gv.getSignos();
             
             ModelMap modelo= new ModelMap();
             
             if(signos != null) {
             
             modelo.put("signos",signos);
             
           
           
             return new ModelAndView("HomeAnimal", modelo);}
	else {
		 return new ModelAndView("Error"); 
	}}
	
	
  	   
  	 @RequestMapping("/historiaClinica")
 	public ModelAndView verHistoria() {
 	     
 	       
              
              
              List<AnimalDeGranja> animales= servicioGanado.listar();
              HashSet<AnimalDeGranja> animalesNoRepetidos= new HashSet<>();
              for(AnimalDeGranja animal : animales) {
             	 animalesNoRepetidos.add(animal);
              }
   	      
   	     List<HistoriaClinica> historias= new ArrayList<HistoriaClinica>();
   	   Long id = null;
   	   
   	  HistoriaClinica hc= new HistoriaClinica();
   	   for(AnimalDeGranja v: animalesNoRepetidos) {
   	      
              id= v.getId();
              hc = servicioGanado.verHC(id);
             	historias.add(hc);}
   	   
   	   
   	ModelMap modelo= new ModelMap();
    
    modelo.put("historias",historias);
  
 
  

  return new ModelAndView("historiaClinica", modelo);
             	
   	      
   	   
   	    }
  	 
   	   
   	@RequestMapping("/verhistoria")
	public ModelAndView hc(@RequestParam(value="id", required=true) Long id){
	     
	       
        
	   
	  HistoriaClinica hc= new HistoriaClinica();
	
	      
     
        hc = servicioGanado.verHC(id);
       
       	
	       
               ModelMap modelo= new ModelMap();
             
               modelo.put("hc",hc);
             
            
             
           
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
	   
	@RequestMapping("/diagnosticarPost")
	public ModelAndView diagnostico(@ModelAttribute("sintomas") Sintomas sintomas){
	     
	      
	  HistoriaClinica hc= new HistoriaClinica();
	
	      
  
     hc = servicioGanado.verHC(sintomas.getIdAnimal());
    
    
    	
	   String enfermedad= servicioGanado.diagnosticar(hc,sintomas);    
	   
	   String diagnostico="El animal podria tener"+ enfermedad;
      
	       
	   
            
            ModelMap modelo= new ModelMap();
          
            modelo.put("enfermedad",diagnostico);
          
         
          
        
          return new ModelAndView("historiaClinica", modelo);
          
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
	       
             
            
             modelo.put("animales",animales);
             
            
             
            
             
           
             return new ModelAndView("HomeAnimal", modelo);
             
	}
}
