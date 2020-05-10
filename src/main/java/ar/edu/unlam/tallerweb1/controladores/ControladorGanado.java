package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.GanadoVacuno;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanado;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

import ar.edu.unlam.tallerweb1.servicios.ServicioVacunas;

@Controller
public class ControladorGanado {
	
	
	
	@Inject
	ServicioGanado servicioGanado;
	
	@Inject
	ServicioVacunas servicioVacuna;

	
	@RequestMapping("/homeGanado")
	public ModelAndView home() {
	     
	       List<GanadoVacuno> vacas= servicioGanado.listar();
	       HashMap<GanadoVacuno, Vacuna> vencidas= new HashMap<>();
	       for(GanadoVacuno v: vacas) {
	      
            Vacuna vencida= servicioVacuna.alarmaVacuna(v);
            if(vencida != null){
           
            vencidas.put(v, vencida);}}
             
             ModelMap modelo= new ModelMap();
             modelo.put("vacunas",vencidas);
            
             
           
             return new ModelAndView("HomeGanado", modelo);
             
	}

	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es invocada por metodo http GET
	
	
	
	@RequestMapping("/GuardarGanadoVacuno")
	public ModelAndView guardarGanadoV() {
		
		GanadoVacuno uno= new GanadoVacuno();
		uno.setPeso(220.0);
             uno.setFrecuenciaCardiaca("11.2A");
             List<Vacuna> vacunas= new ArrayList<Vacuna>(); 
        
             Vacuna aftosa= servicioVacuna.getVacuna("Aftosa");   
             vacunas.add(aftosa);
             Vacuna brucelosis= servicioVacuna.getVacuna("Brucelosis");   
             vacunas.add(brucelosis);
             Vacuna leptospirosis= servicioVacuna.getVacuna("Leptospirosis");   
             vacunas.add(leptospirosis);
             
             
         
            uno.setVacunasParaAplicar(vacunas);
             servicioGanado.guardar(uno);
             
             GanadoVacuno dos= new GanadoVacuno();
     		dos.setPeso(120.0);
                  dos.setFrecuenciaCardiaca("11.2A");
                 
                  
                  
              
                 dos.setVacunasParaAplicar(vacunas);
                  servicioGanado.guardar(dos);
		GanadoVacuno ge= new GanadoVacuno();
		ge.setPeso(280.0);
             ge.setFrecuenciaCardiaca("11.2A");
        
             
             
         
            ge.setVacunasParaAplicar(vacunas);
            Calendar fechaNac = Calendar.getInstance();
            fechaNac.set(2019,02,10);
            ge.setFechaNacimiento(fechaNac);
             servicioGanado.guardar(ge);
             
         
             
         
             
             ModelMap modelo= new ModelMap();
             modelo.put("idGanado",ge.getId());
            
           
             return new ModelAndView("HomeGanado", modelo);
             
	}
	
	
	
	
	
	@RequestMapping("/guardarVacunas")
	public ModelAndView guardarVacuna() {
		Vacuna aftosa= new Vacuna();
		aftosa.setNombre("Aftosa");
		aftosa.setDisponile(true);
		aftosa.setEdadAplicacionMeses(2);
		 servicioVacuna.guardar(aftosa);
		 
		 Vacuna brucelosis= new Vacuna();
			brucelosis.setNombre("Brucelosis");
			brucelosis.setDisponile(true);
			brucelosis.setEdadAplicacionMeses(5);
			 servicioVacuna.guardar(brucelosis);
			 
			 Vacuna leptospirosis= new Vacuna();
				leptospirosis.setNombre("Leptospirosis");
				leptospirosis.setDisponile(true);
				leptospirosis.setEdadAplicacionMeses(4);
				 servicioVacuna.guardar(leptospirosis);
             
             ModelMap modelo= new ModelMap();
             modelo.put("vacuna",aftosa);
             
             return new ModelAndView("HomeGanado", modelo);
             
	}
	
	@RequestMapping("/buscarGanado")
	public ModelAndView buscarGanado(@RequestParam(value="id", required=true) Long id) {
	
             
            GanadoVacuno gv= servicioGanado.ver(id);
             
             ModelMap modelo= new ModelMap();
             modelo.put("gv", gv);
            
             return new ModelAndView("HomeGanado", modelo);
             
	}
}
