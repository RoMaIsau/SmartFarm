package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Before;

import static org.mockito.Mockito.*;

import ar.edu.unlam.tallerweb1.servicios.ServicioAlimento;
import ar.edu.unlam.tallerweb1.servicios.ServicioAnimalUbicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUbicacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioVacunas;

public class ControladorMapaTest {

	private ControladorMapa controladorMapa;

	private ServicioUbicacion servicioUbicacion;
	private ServicioNotificacion servicioNotificacion;
	private ServicioDeAnimales servicioDeAnimales;
	private ServicioAnimalUbicacion servicioAnimalUbicacion;
	private ServicioAlimento servicioAlimento;
	private ServicioVacunas servicioVacunas;

	@Before
	public void inicializar() {

		this.servicioUbicacion = mock(ServicioUbicacion.class);
		this.servicioAlimento = mock(ServicioAlimento.class);
		this.servicioAnimalUbicacion = mock(ServicioAnimalUbicacion.class);
		this.servicioNotificacion = mock(ServicioNotificacion.class);
		this.servicioDeAnimales = mock(ServicioDeAnimales.class);
		this.servicioVacunas = mock(ServicioVacunas.class);

		this.controladorMapa = new ControladorMapa(this.servicioUbicacion, this.servicioNotificacion,
				this.servicioDeAnimales, this.servicioAnimalUbicacion, this.servicioAlimento, this.servicioVacunas);
	}
}
