package ar.edu.unlam.tallerweb1.persistencia;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorGanado;
import ar.edu.unlam.tallerweb1.controladores.ControladorVeterinario;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanado;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanadoImpl;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class TestControladorVeterinario {
	
	@Test
	 @Transactional @Rollback
	    public void testVerSaludViewError(){
		
		AnimalDeGranja animalNullMock= mock(AnimalDeGranja.class);
		
		when(animalNullMock.getSignos()).thenReturn(null);
		
		
		
		 ServicioGanadoImpl servicioNullMock= mock(ServicioGanadoImpl.class);
		
		 when(servicioNullMock.ver(1L)).thenReturn(animalNullMock);
		 
		 ControladorVeterinario cv = new ControladorVeterinario();
		 
		 cv.setServicioGanado(servicioNullMock);
		 
		 ModelAndView modelView = cv.verSalud(1L);
		 
		 String viewName= modelView.getViewName();
		 
		 assertTrue(viewName == "Error");}
	
	@Test
	 @Transactional @Rollback
	    public void testVerSaludViewHomeAnimal(){
		 
		 ServicioGanadoImpl servicioMock= mock(ServicioGanadoImpl.class);
		 
		 SignosVitales signos= new SignosVitales();
			
	        AnimalDeGranja animalMock= mock(AnimalDeGranja.class);
	    	when(animalMock.getSignos()).thenReturn(signos);
			
		 when(servicioMock.ver(1L)).thenReturn(animalMock);
		 
             
        	 ControladorVeterinario cv = new ControladorVeterinario();
    		 
    		 cv.setServicioGanado(servicioMock);
		 
		 ModelAndView modelView2 = cv.verSalud(1L);
		 
		 String viewName2= modelView2.getViewName();
		 
		 assertTrue(viewName2 == "HomeAnimal");
		 
		
		 
		 
		 
		 
		 

}}
