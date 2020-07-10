package ar.edu.unlam.tallerweb1.servicios;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioNotificacion;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServicioNotificacionTest {
	
	private ServicioNotificacion servicioNotificacion;
	
	private RepositorioNotificacion repositorioNotificacion;
	private RepositorioAlimento repositorioAlimento;
	private ServicioDeAnimales servicioAnimales;
	
	@Before
	public void inicializar() {
		
		this.repositorioNotificacion = mock(RepositorioNotificacion.class);
		this.repositorioAlimento = mock(RepositorioAlimento.class);
		
		this.servicioNotificacion = new ServicioNotificacionImpl(this.repositorioNotificacion, this.repositorioAlimento);
	}
	
	
}
