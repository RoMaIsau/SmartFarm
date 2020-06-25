package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorGanado;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.modelo.Sintomas;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGanado;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanado;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanadoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioLogin;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.View;

public class TestServicioGanado{

	
	

		@Test
	 @Transactional @Rollback
	    public void testAlarmaSignos(){
	       
			
	     //Preparacion  
	    AnimalDeGranja animal=  mock(AnimalDeGranja.class);
	        
	       SignosVitales signos= mock(SignosVitales.class);
	       ServicioGanadoImpl servicio= new ServicioGanadoImpl(null);
	     
	     
	       when(signos.getTemperatura()).thenReturn(41.0);
	       when(signos.getFrecuenciaCardiaca()).thenReturn(80.0);
	       when(signos.getFrecuenciaRespiratoria()).thenReturn(10.0);
	       when(signos.getPulso()).thenReturn(150.0);
	       
	       when(animal.getSignos()).thenReturn(signos);
	       
	    
	       
	    //Ejecucion   
		List<SignosVitales> anormales= servicio.alarmaSignos(animal);
	    
	       
	       //verificacion 
	       assertTrue(anormales.size()==4);
	        
	    }
		

		@Test
	 @Transactional @Rollback
	    public void testVerAnimal(){
	       
			
	     //Preparacion  
	    AnimalDeGranja animalMock=  mock(AnimalDeGranja.class);
	    when(animalMock.getId()).thenReturn(1L);
	       
	      
	     
	       RepositorioGanado servicioGanadoDaoMock=mock(RepositorioGanado.class);
	        Long id=1L;
	       
	       when(servicioGanadoDaoMock.ver(id)).thenReturn(animalMock);
	       

	       ServicioGanadoImpl servicio= new ServicioGanadoImpl(servicioGanadoDaoMock);
	    
	       
	    //Ejecucion   
		AnimalDeGranja animal= servicio.ver(id);
	    
	       
	       //verificacion 
	       assertTrue(animal.getId()==1L);
	        
	    }

}
