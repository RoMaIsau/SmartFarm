package ar.edu.unlam.tallerweb1.controladores;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import ar.edu.unlam.tallerweb1.servicios.ServicioGastos;
import ar.edu.unlam.tallerweb1.servicios.ServicioTipoDeGasto;
import ar.edu.unlam.tallerweb1.servicios.ServicioTipoDeUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

public class ControladorAdminTest {

	private ControladorAdmin controladorAdmin;

	private ServicioUsuario servicioUsuario;
	private ServicioGastos servicioGastos;
	private ServicioTipoDeGasto servicioTipoDeGasto;
	private ServicioTipoDeUsuario servicioTipoDeUsuario;

	@Before
	public void inicializar() {

		this.servicioUsuario = mock(ServicioUsuario.class);
		this.servicioGastos = mock(ServicioGastos.class);
		this.servicioTipoDeGasto = mock(ServicioTipoDeGasto.class);
		this.servicioTipoDeUsuario = mock(ServicioTipoDeUsuario.class);

		this.controladorAdmin = new ControladorAdmin(this.servicioUsuario, this.servicioGastos,
				this.servicioTipoDeGasto, this.servicioTipoDeUsuario);
	}
}
