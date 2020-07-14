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
import ar.edu.unlam.tallerweb1.servicios.ServicioVacunaImpl;

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
		ServicioVacunaImpl servicioVacunaMock= mock(ServicioVacunaImpl.class);
		 ServicioGanadoImpl servicioNullMock= mock(ServicioGanadoImpl.class);
		 AnimalDeGranja animalNullMock= mock(AnimalDeGranja.class);
		 when(servicioNullMock.verHC(animalNullMock)).thenReturn(hc);
		List<SignosVitales>signos= new ArrayList<SignosVitales>();
		 when(servicioNullMock.signos(hc)).thenReturn(signos);
		 when(servicioVacunaMock.obtenerVacunasAplicadas(1L)).thenReturn(null);
		
			
		 when(servicioNullMock.ver(1L)).thenReturn(animalNullMock);
		 
		 ControladorVeterinario cv = new ControladorVeterinario();
		 
		 cv.setServicioGanado(servicioNullMock);
		 cv.setServicioVacuna(servicioVacunaMock);
		 
		 //ejecucion
		 ModelAndView modelView = cv.verSalud(1L);
		 
		 String viewName= modelView.getViewName();
		 
		 assertTrue(viewName == "Error");}
	
	@Test
	 @Transactional @Rollback
	    public void testVerSaludViewHomeAnimal(){
		 
		HistoriaClinica hc = new HistoriaClinica();
		ServicioVacunaImpl servicioVacunaMock= mock(ServicioVacunaImpl.class);
		 ServicioGanadoImpl servicioMock= mock(ServicioGanadoImpl.class);
		 List<SignosVitales> signos= new ArrayList<SignosVitales>();
		 SignosVitales signo1= new SignosVitales();
		 SignosVitales signo2= new SignosVitales();
		 signos.add(signo1);
		 signos.add(signo2);
		 		 when(servicioMock.signos(hc)).thenReturn(signos);
		 		 when(servicioVacunaMock.obtenerVacunasAplicadas(1L)).thenReturn(null);
		AnimalDeGranja animalMock= mock(AnimalDeGranja.class);
		 
		when(servicioMock.verHC(animalMock)).thenReturn(hc);
		
		 
		when(servicioMock.ver(1L)).thenReturn(animalMock);
        	 ControladorVeterinario cv = new ControladorVeterinario();
    		 
    		 cv.setServicioGanado(servicioMock);
		 cv.setServicioVacuna(servicioVacunaMock);
		 //ejecucion
		 ModelAndView modelView2 = cv.verSalud(1L);
		 
		 String viewName2= modelView2.getViewName();
		 
		 assertTrue(viewName2 == "HomeAnimal");
		 
		
		 
		 
		 
		 
		 

}}
