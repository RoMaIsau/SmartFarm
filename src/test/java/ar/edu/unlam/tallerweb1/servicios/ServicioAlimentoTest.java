package ar.edu.unlam.tallerweb1.servicios;

import org.junit.Before;

import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioTipoAlimento;
import static org.mockito.Mockito.*;

public class ServicioAlimentoTest {
	
	private ServicioAlimento servicioAlimento;
	
	private RepositorioTipoAlimento repositorioTipoAlimento;
	private RepositorioAlimento repositorioAlimento;
	
	@Before
	public void inicializar() {
		
		this.repositorioTipoAlimento = mock(RepositorioTipoAlimento.class);
		this.repositorioAlimento = mock(RepositorioAlimento.class);
		
		this.servicioAlimento = new ServicioAlimentoImpl(this.repositorioTipoAlimento, this.repositorioAlimento);
	}

}
