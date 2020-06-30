package ar.edu.unlam.tallerweb1.persistencia;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorGanado;
import ar.edu.unlam.tallerweb1.controladores.ControladorVeterinario;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.Vacuna;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGanado;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioVacunas;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanado;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanadoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioVacunaImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioVacunas;

public class TestVacunas {
	
	@Test
	 @Transactional @Rollback
	    public void testVerVacunas(){
	       
			
	     //Preparacion  
	    AnimalDeGranja animalMock=  mock(AnimalDeGranja.class);
	    when(animalMock.getId()).thenReturn(1L);
	       
	      Vacuna vacunaMock=mock(Vacuna.class);
	      when(vacunaMock.getNombre()).thenReturn("vacuna");
	     
	       RepositorioVacunas servicioVacunaDaoMock=mock(RepositorioVacunas.class);
	       
	       
	       when(servicioVacunaDaoMock.getVacuna("vacuna")).thenReturn(vacunaMock);
	       

	       ServicioVacunaImpl servicio= new ServicioVacunaImpl(servicioVacunaDaoMock);
	    
	       
	    //Ejecucion   
		Vacuna resultado= servicio.getVacuna("vacuna");
	    
	       
	       //verificacion 
	       assertTrue(resultado.getNombre()=="vacuna");
	        
	    }
	
	@Test
	 @Transactional @Rollback
	    public void testServicioVacunar(){
	       
			
	     //Preparacion  
	    AnimalDeGranja animalMock=  mock(AnimalDeGranja.class);
	    
	   ServicioGanado servicioGanadoMock= mock(ServicioGanado.class);
	   when(servicioGanadoMock.ver(1L)).thenReturn(animalMock);  
	   
	   ServicioVacunas servicioVacunaMock= mock(ServicioVacunas.class);
	   List<Vacuna>listaVacunas= new ArrayList<Vacuna>();
	   when(servicioVacunaMock.alarmaVacuna(animalMock)).thenReturn(listaVacunas);  
	     
	     
	  ControladorVeterinario cg = new ControladorVeterinario();
	  cg.setServicioGanado(servicioGanadoMock);
	  cg.setServicioVacuna(servicioVacunaMock);
	  
	    ModelAndView mv= new ModelAndView();   
	       
	      mv= cg.vacunar(1L);
	      
	     List<Vacuna>vencidas= (List<Vacuna>) mv.getModelMap().get("vencidas");
	       

	    
	       
	  
	    
	       
	       //verificacion 
	       assertTrue(vencidas.equals(listaVacunas));
	        
	    }

}


