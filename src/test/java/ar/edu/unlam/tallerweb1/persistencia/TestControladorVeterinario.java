package ar.edu.unlam.tallerweb1.persistencia;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.controladores.ControladorGanado;
import ar.edu.unlam.tallerweb1.controladores.ControladorVeterinario;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.HistoriaClinica;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanado;
import ar.edu.unlam.tallerweb1.servicios.ServicioGanadoImpl;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class TestControladorVeterinario {
	
	@Test
	 @Transactional @Rollback
	    public void testVerSaludViewError(){
		HistoriaClinica hc = new HistoriaClinica();
		
		 ServicioGanadoImpl servicioNullMock= mock(ServicioGanadoImpl.class);
		
		 when(servicioNullMock.signos(hc)).thenReturn(null);
		AnimalDeGranja animalNullMock= mock(AnimalDeGranja.class);
		
		when(animalNullMock.getHistoria()).thenReturn(hc);
		
		
		
		
		
		 when(servicioNullMock.ver(1L)).thenReturn(animalNullMock);
		 
		 ControladorVeterinario cv = new ControladorVeterinario();
		 
		 cv.setServicioGanado(servicioNullMock);
		 
		 //ejecucion
		 ModelAndView modelView = cv.verSalud(1L);
		 
		 String viewName= modelView.getViewName();
		 
		 assertTrue(viewName == "Error");}
	
	@Test
	 @Transactional @Rollback
	    public void testVerSaludViewHomeAnimal(){
		 
		HistoriaClinica hc = new HistoriaClinica();
		
		 ServicioGanadoImpl servicioMock= mock(ServicioGanadoImpl.class);
		 List<SignosVitales> signos= new ArrayList<SignosVitales>();
		 SignosVitales signo1= new SignosVitales();
		 SignosVitales signo2= new SignosVitales();
		 signos.add(signo1);
		 signos.add(signo2);
		 		 when(servicioMock.signos(hc)).thenReturn(signos);
		AnimalDeGranja animalMock= mock(AnimalDeGranja.class);
		
		when(animalMock.getHistoria()).thenReturn(hc);
		 
		when(servicioMock.ver(1L)).thenReturn(animalMock);
        	 ControladorVeterinario cv = new ControladorVeterinario();
    		 
    		 cv.setServicioGanado(servicioMock);
		 
		 //ejecucion
		 ModelAndView modelView2 = cv.verSalud(1L);
		 
		 String viewName2= modelView2.getViewName();
		 
		 assertTrue(viewName2 == "HomeAnimal");
		 
		
		 
		 
		 
		 
		 

}}
