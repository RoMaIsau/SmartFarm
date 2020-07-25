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
	
	private static final String ANIMAL_FUERA_DE_LUGAR = "Animal fuera de rango";
	
	private ServicioNotificacion servicioNotificacion;
	
	private RepositorioNotificacion repositorioNotificacion;
	private RepositorioAlimento repositorioAlimento;

	@Before
	public void inicializar() {

		this.repositorioNotificacion = mock(RepositorioNotificacion.class);
		this.repositorioAlimento = mock(RepositorioAlimento.class);

		this.servicioNotificacion = new ServicioNotificacionImpl(this.repositorioNotificacion,
				this.repositorioAlimento);
	}

	@Test
	public void noDeberiaCrearNotificacionDeStockMinimoSiYaExiste() {
		List<Alimento> alimentos = new ArrayList<Alimento>();
		Alimento pasto = new Alimento();
		pasto.setId(1L);
		pasto.setCantidad(10.0);
		pasto.setStockMinimo(15.0);
		pasto.setNombre("Pasto");
		alimentos.add(pasto);

		when(this.repositorioAlimento.listarAlimentos()).thenReturn(alimentos);

		Notificacion notificacion = new Notificacion();
		notificacion.setTitulo("Stock");
		notificacion.setDetalles("Pasto ha llegado a su stock minimo");
		notificacion.setFecha("13/7/2020");

		when(this.repositorioNotificacion.notificacionPorDetalles(notificacion.getDetalles(), notificacion.getFecha()))
				.thenReturn(notificacion);

		this.servicioNotificacion.crearNotificacionStock();

		verify(this.repositorioAlimento).listarAlimentos();
		verify(this.repositorioNotificacion, never()).crearNotificacionStock(notificacion);

	}

	@Test
	public void deberiaListarLasNotificacionesPorIdDeUsuario() {
		Long idUsuario = 1L;

		List<Notificacion> notificaciones = this.servicioNotificacion.listarNotificaciones(idUsuario);

		verify(this.repositorioNotificacion).listarNotificaciones(eq(idUsuario));
		assertNotNull(notificaciones);
	}

	@Test
	public void deberiaObtenerNotificacionPorDetalles() {
		String detalles = "Pasto ha llegado a su stock minimo";
		String fecha = "10/07/2020";

		Notificacion notificacion = new Notificacion();
		notificacion.setDetalles(detalles);
		notificacion.setFecha(fecha);

		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		notificaciones.add(notificacion);

		when(this.repositorioNotificacion.notificacionPorDetalles(detalles, fecha)).thenReturn(notificacion);

		Notificacion notificacionObtenida = this.servicioNotificacion.notificacionPorDetalles(detalles, fecha);

		verify(this.repositorioNotificacion).notificacionPorDetalles(eq(detalles), eq(fecha));
		assertNotNull(notificacionObtenida);
	}

	@Test
	public void deberiaObtenerNotificacionPorId() {
		Long idNoti = 1L;

		Notificacion notificacion = new Notificacion();
		notificacion.setId(idNoti);

		when(this.repositorioNotificacion.notificacionPorId(idNoti)).thenReturn(notificacion);

		Notificacion notificacionObtenida = this.servicioNotificacion.notificacionPorId(idNoti);

		verify(this.repositorioNotificacion).notificacionPorId(eq(idNoti));
		assertNotNull(notificacionObtenida);
	}

	@Test
	public void deberiaActualizarNotificacion() {
		Notificacion notificacion = new Notificacion();
		notificacion.setId(1L);

		this.servicioNotificacion.actualizarNotificacion(notificacion);

		verify(this.repositorioNotificacion).actualizarNotificacion(eq(notificacion));
	}
	
	@Test
	public void deberiaListarNotificacionesPendientesDeAnimalFueraDeCorral() {
		 
		Notificacion notificacion = new Notificacion();
		notificacion.setId(1L);
		
		List<Notificacion> notificaciones = new ArrayList<Notificacion>();
		notificaciones.add(notificacion);
		
		when(this.repositorioNotificacion.listarNotificacionesPendientesPorTitulo(ANIMAL_FUERA_DE_LUGAR)).thenReturn(notificaciones);
		
		List<Notificacion> notificacionesObtenidas = this.servicioNotificacion.obtenerNotificacionesPendientesDeAnimalFueraDeCorral();
		
		assertNotNull(notificacionesObtenidas);
		assertThat(notificacionesObtenidas).isEqualTo(notificaciones);
	}
	
}
