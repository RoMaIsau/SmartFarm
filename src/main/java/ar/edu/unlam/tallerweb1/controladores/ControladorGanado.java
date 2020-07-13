package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;

import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.Persona;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.Sintomas;
import ar.edu.unlam.tallerweb1.modelo.TipoAlimento;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;


import ar.edu.unlam.tallerweb1.servicios.ServicioGanado;


import ar.edu.unlam.tallerweb1.servicios.ServicioVacunas;

@Controller
public class ControladorGanado {
	
	
	
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
	
	
	@RequestMapping("/buscarGanado")
	public ModelAndView buscarGanado(@RequestParam(value="id", required=true) Long id) {
	
             
		AnimalDeGranja gv= servicioGanado.ver(id);
             
             ModelMap modelo= new ModelMap();
             modelo.put("gv", gv);
            
             return new ModelAndView("HomeGanado", modelo);
             
	}
	
	
	


	
	
	
	@RequestMapping("/guardarGanadoVacuno")
	public ModelAndView guardarGanadoV() {
		
		 Vacuna aftosa= servicioVacuna.getVacuna("Aftosa");   
     
         Vacuna brucelosis= servicioVacuna.getVacuna("Brucelosis");   
         
         Vacuna leptospirosis= servicioVacuna.getVacuna("Leptospirosis");   
         Vacuna carbunco = servicioVacuna.getVacuna("Carbunco");  
         Vacuna botulismo= servicioVacuna.getVacuna("Botulismo");  
         Vacuna policlostridiales1= servicioVacuna.getVacuna("Policlostridiales1");  
         Vacuna policlostridiales2= servicioVacuna.getVacuna("Policlostridiales2");  
         
		
         AnimalDeGranja gv1= new AnimalDeGranja();
		gv1.setPeso(220.0);
             
             ArrayList<Vacuna> vacunas1= new ArrayList<Vacuna>(); 
        
              
             vacunas1.add(aftosa);
               
             vacunas1.add(brucelosis);
             
             vacunas1.add(leptospirosis);
             vacunas1.add(carbunco);
             
             
         
           gv1.setVacunasParaAplicar(vacunas1);
           HistoriaClinica hc = new HistoriaClinica();
          hc.setAnimal(gv1);
           gv1.setHistoria(hc);
           
       
       
      
       
       Calendar fechaNac1 = Calendar.getInstance();
       fechaNac1.set(2020,01,10);
       gv1.setFechaNacimiento(fechaNac1);
       
            servicioGanado.guardar(gv1);
            
            SignosVitales signos1= new SignosVitales();
            signos1.setTemperatura(42.0);
            signos1.setPulso(48.1);
            signos1.setFrecuenciaRespiratoria(25.2);
            signos1.setFrecuenciaCardiaca(70.4);
            Date fechaSignos1 = new Date(2020,01,10);
            
            signos1.setFecha(fechaSignos1);
            signos1.setHistoria(hc);
            servicioGanado.guardarSV(signos1);
            
          
           
            
           
            
            SignosVitales signos11= new SignosVitales();
            signos11.setFrecuenciaCardiaca(40.0);
            signos11.setTemperatura(37.6);
            signos11.setPulso(80.1);
            signos11.setFrecuenciaRespiratoria(25.2);
            
            Date fechasignos11 = new Date(2020,04,10);
            
            signos11.setFecha(fechasignos11);
            signos11.setHistoria(hc);
            servicioGanado.guardarSV(signos11);
            
            SignosVitales signos12= new SignosVitales();
            signos12.setFrecuenciaCardiaca(50.0);
            signos12.setTemperatura(37.6);
            signos12.setPulso(80.1);
            signos12.setFrecuenciaRespiratoria(25.2);
            
            Date fechasignos12= new Date(2020,05,10);
            signos12.setFecha(fechasignos12);
            signos12.setHistoria(hc);
            servicioGanado.guardarSV(signos12);
            
            SignosVitales signos13= new SignosVitales();
            signos13.setFrecuenciaCardiaca(40.0);
            signos13.setTemperatura(37.6);
            signos13.setPulso(80.1);
            signos13.setFrecuenciaRespiratoria(25.2);
            
            Date fechasignos13 = new Date(2020,02,10);
            signos13.setFecha(fechasignos13);
            signos13.setHistoria(hc);
            servicioGanado.guardarSV(signos13);
            
            SignosVitales signos14= new SignosVitales();
            signos14.setFrecuenciaCardiaca(40.0);
            signos14.setTemperatura(37.6);
            signos14.setPulso(80.1);
            signos14.setFrecuenciaRespiratoria(25.2);
            
            Date fechasignos14 = new Date(2020,01,13);
            signos14.setFecha(fechasignos14);
            signos14.setHistoria(hc);
            servicioGanado.guardarSV(signos14);
            
            SignosVitales signos15= new SignosVitales();
            signos15.setFrecuenciaCardiaca(35.0);
            signos15.setTemperatura(37.6);
            signos15.setPulso(80.1);
            signos15.setFrecuenciaRespiratoria(25.2);
            
            Date fechasignos15 = new Date(2020,01,11);
            signos15.setFecha(fechasignos15);
            signos15.setHistoria(hc);
            servicioGanado.guardarSV(signos15);
            
          
            
           
            
            AnimalDeGranja gv2= new AnimalDeGranja();
     		gv2.setPeso(120.0);
     		  HistoriaClinica hc1 = new HistoriaClinica();
            hc1.setAnimal(gv2);
              gv2.setHistoria(hc1);
             
              
                  
                 
                  ArrayList<Vacuna> vacunas2= new ArrayList<Vacuna>(); 
                  
                     
                  vacunas2.add(aftosa);
                  
                  vacunas2.add(brucelosis);
                  
                  vacunas2.add(leptospirosis);
                  vacunas2.add(policlostridiales1);
                 
                        
                  
              
                 gv2.setVacunasParaAplicar(vacunas2);
                 
            
               
            
                 
                 
                 
                 Calendar fechaNac2 = Calendar.getInstance();
                 fechaNac2.set(2020,02,10);
                 gv2.setFechaNacimiento(fechaNac2);
                  servicioGanado.guardar(gv2);
                  
                  SignosVitales signos2= new SignosVitales();
                  signos2.setTemperatura(38.4);
                  signos2.setPulso(90.4);
                  signos2.setFrecuenciaRespiratoria(27.2);
                  signos2.setFrecuenciaCardiaca(52.4);
                  
                  Date fechaSignos2= new Date(2019,01,10);
                  signos2.setFecha(fechaSignos2);
                  signos2.setHistoria(hc1);
                  
                  servicioGanado.guardarSV(signos2);
                  
                  
                  
                
                  SignosVitales signos16= new SignosVitales();
                  signos16.setFrecuenciaCardiaca(90.0);
                  signos16.setTemperatura(40.0);
                  signos16.setPulso(80.1);
                  signos16.setFrecuenciaRespiratoria(25.2);
                  
                  Date fechasignos16= new Date(2020,03,13);
                  signos16.setFecha(fechasignos16);
                  signos16.setHistoria(hc1);
                  servicioGanado.guardarSV(signos16);
                  
                  SignosVitales signos17= new SignosVitales();
                  signos17.setFrecuenciaCardiaca(80.0);
                  signos17.setTemperatura(39.6);
                  signos17.setPulso(80.1);
                  signos17.setFrecuenciaRespiratoria(25.2);
                  
                  Date fechasignos17= new Date(2020,03,10);
                  signos17.setFecha(fechasignos17);
                  signos17.setHistoria(hc1);
                  servicioGanado.guardarSV(signos17);
                  
                  SignosVitales signos18= new SignosVitales();
                  signos18.setFrecuenciaCardiaca(85.0);
                  signos18.setTemperatura(39.7);
                  signos18.setPulso(80.1);
                  signos18.setFrecuenciaRespiratoria(25.2);
                  
                  Date fechasignos18= new Date(2020,03,18);
                  signos18.setFecha(fechasignos18);
                  signos18.setHistoria(hc1);
                  servicioGanado.guardarSV(signos18);
                  
                  SignosVitales signos19= new SignosVitales();
                  signos19.setFrecuenciaCardiaca(90.0);
                  signos19.setTemperatura(39.6);
                  signos19.setPulso(80.1);
                  signos19.setFrecuenciaRespiratoria(25.2);
                  
                 Date fechasignos19= new Date(2020,11,10);
                  signos19.setFecha(fechasignos19);
                  signos19.setHistoria(hc1);
                  servicioGanado.guardarSV(signos19);
                  
                  SignosVitales signos20= new SignosVitales();
                  signos20.setFrecuenciaCardiaca(100.0);
                  signos20.setTemperatura(40.6);
                  signos20.setPulso(80.1);
                  signos20.setFrecuenciaRespiratoria(25.2);
                  
                  Date fechasignos20 = new Date(2020,12,10);
                  signos20.setFecha(fechasignos20);
                  signos20.setHistoria(hc1);
                  servicioGanado.guardarSV(signos20);
                  
                 
                  
                 
                 
                 
                  
                
                
                  
                  AnimalDeGranja gv3= new AnimalDeGranja();
		     gv3.setPeso(280.0);
		     HistoriaClinica hc2 = new HistoriaClinica();
             hc2.setAnimal(gv3);
             gv3.setHistoria(hc2);
             
        
             ArrayList<Vacuna> vacunas3= new ArrayList<Vacuna>();
             vacunas3.add(aftosa);
             
             vacunas3.add(brucelosis);
             
             vacunas3.add(leptospirosis);
             vacunas3.add(carbunco);
             vacunas3.add(botulismo);
             vacunas3.add(policlostridiales1);
             vacunas3.add(policlostridiales2);
            
             
         
            gv3.setVacunasParaAplicar(vacunas3);
            Calendar fechaNac = Calendar.getInstance();
            fechaNac.set(2019,02,10);
            gv3.setFechaNacimiento(fechaNac);
            
           
            
          
            
           
          
             servicioGanado.guardar(gv3);
             
             SignosVitales signos3= new SignosVitales();
             signos3.setTemperatura(36.4);
             signos3.setPulso(43.4);
             signos3.setFrecuenciaRespiratoria(29.2);
             signos3.setFrecuenciaCardiaca(57.4);
             signos3.setHistoria(hc2);
             
             servicioGanado.guardarSV(signos3);
             
         
             
         
             
             ModelMap modelo= new ModelMap();
             modelo.put("idGanado",gv1.getId());
            
           
             return new ModelAndView("HomeGanado", modelo);
             
	}
	
	
	
	
	
	@RequestMapping("/guardarVacunas")
	public ModelAndView guardarVacuna() {
		
		Vacuna aftosa= new Vacuna();
		aftosa.setNombre("Aftosa");
		
		aftosa.setEdadAplicacionMeses(2);
		 servicioVacuna.guardar(aftosa);
		 
		 Vacuna brucelosis= new Vacuna();
			brucelosis.setNombre("Brucelosis");
			
			brucelosis.setEdadAplicacionMeses(5);
			 servicioVacuna.guardar(brucelosis);
			 
			 Vacuna carbunco= new Vacuna();
				carbunco.setNombre("Carbunco");
				
				carbunco.setEdadAplicacionMeses(3);
				 servicioVacuna.guardar(carbunco);
				 
				 Vacuna botulismo= new Vacuna();
					botulismo.setNombre("Botulismo");
					
					botulismo.setEdadAplicacionMeses(12);
					 servicioVacuna.guardar(botulismo);
					 
			 Vacuna leptospirosis= new Vacuna();
				leptospirosis.setNombre("Leptospirosis");
				
				leptospirosis.setEdadAplicacionMeses(4);
				 servicioVacuna.guardar(leptospirosis);
				 
				 Vacuna policlostridiales1= new Vacuna();
					policlostridiales1.setNombre("Policlostridiales1");
					
					policlostridiales1.setEdadAplicacionMeses(4);
					 servicioVacuna.guardar(policlostridiales1);
					 
					 Vacuna policlostridiales2= new Vacuna();
						policlostridiales2.setNombre("Policlostridiales2");
						
						policlostridiales2.setEdadAplicacionMeses(12);
						 servicioVacuna.guardar(policlostridiales2);
						 
						 List<Vacuna> calendario= new ArrayList<Vacuna>();
						 calendario.add(aftosa);
						 calendario.add(brucelosis);
						 calendario.add(carbunco);
						 calendario.add(leptospirosis);
						 calendario.add(botulismo);
						 calendario.add(policlostridiales1);
						 calendario.add(policlostridiales2);
             
             ModelMap modelo= new ModelMap();
             modelo.put("vacunas",calendario);
             
             return new ModelAndView("CalendarioVacunacion", modelo);
             
	}
	

	
	

}