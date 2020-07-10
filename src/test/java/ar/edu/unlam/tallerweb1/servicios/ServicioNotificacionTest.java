package ar.edu.unlam.tallerweb1.servicios;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServicioNotificacionTest {
	
	private ServicioNotificacion servicioNotificacion;
	
	private RepositorioNotificacion repositorioNotificacion;
	private RepositorioAlimento repositorioAlimento;
	
	@Before
	public void inicializar() {
		
		this.repositorioNotificacion = mock(RepositorioNotificacion.class);
		this.repositorioAlimento = mock(RepositorioAlimento.class);
		
		this.servicioNotificacion = new ServicioNotificacionImpl(this.repositorioNotificacion, this.repositorioAlimento);
	}
	
//	@Test
//	public void deberiaCrearNotificacionDeStockMinimo() {
//		List<Alimento> alimentos = new ArrayList<Alimento>();
//		Alimento pasto = new Alimento();
//		pasto.setId(1L);
//		pasto.setCantidad(10.0);
//		pasto.setStockMinimo(15.0);
//		pasto.setNombre("Pasto");
//		alimentos.add(pasto);
//		
//		when(this.repositorioAlimento.listarAlimentos()).thenReturn(alimentos);
//		
//		this.servicioNotificacion.crearNotificacionStock();
//		
//		verify(this.repositorioAlimento).listarAlimentos();
//		
//	}
	
	@Test
	public void deberiaListarLasNotificacionesPorIdDeUsuario() {
		Long idUsuario = 1L;
		
		List<Notificacion> notificaciones = this.servicioNotificacion.listarNotificaciones(idUsuario);
		
		verify(this.repositorioNotificacion).listarNotificaciones(eq(idUsuario));
		assertNotNull(notificaciones);
	}
	
	
}
