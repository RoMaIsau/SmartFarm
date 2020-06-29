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
	       
	      
	       
	    
	       
	    //Ejecucion   
	Boolean resultado= servicio.alarmaSV(signos);
	    
	       
	       //verificacion 
	       assertTrue(resultado==true);
	        
	    }
		

	

}
