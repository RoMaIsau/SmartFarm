package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Before;
import static org.mockito.Mockito.*;

import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

public class ControladorUsuarioTest {
	
	private ControladorUsuario controladorUsuario;
	private ServicioUsuario servicioUsuario;
	
	@Before
	public void inicializar() {
		this.servicioUsuario = mock(ServicioUsuario.class);
		
		this.controladorUsuario = new ControladorUsuario(this.servicioUsuario);
	}
}
