package ar.edu.unlam.tallerweb1.servicios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.SignosVitales;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGanado;


import static org.mockito.Mockito.*;

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
	
	@Test
	public void testQueVerificaElTratamientoA() {
		RepositorioGanado repositorioGanado = mock(RepositorioGanado.class);
		ServicioGanado servicioGanado = new ServicioGanadoImpl(repositorioGanado);
		
		String enfermedad = "Miocardiopatia congenita";
		servicioGanado.tratamientoA(enfermedad);
		
		String resultado = servicioGanado.tratamientoA(enfermedad);
		assertEquals("Cardiosina", resultado);
	}

	@Test
	public void testQueVerificaElTratamientoB() {
		RepositorioGanado repositorioGanado = mock(RepositorioGanado.class);
		ServicioGanado servicioGanado = new ServicioGanadoImpl(repositorioGanado);
		
		String enfermedad = "Miocardiopatia congenita";
		servicioGanado.tratamientoA(enfermedad);
		
		String resultado = servicioGanado.tratamientoB(enfermedad);
		assertEquals("Cirugia", resultado);
	}
	
}
