package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.TipoAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlimento;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioAlimentoImpl;

@Transactional
public class RepositorioAlimentoTest extends SpringTest {

	private RepositorioAlimento repositorioAlimento;

	@Before
	public void inicializar() {
		this.repositorioAlimento = new RepositorioAlimentoImpl(this.sessionFactory);
	}
	
	@Test
	public void deberiaGuardarUnAlimento() {
		Alimento alimento = this.crearAlimento("Arroz", 220.0, 50.0, "Granos");
		
		this.repositorioAlimento.registrarAlimento(alimento);
		
		assertNotNull(alimento.getId());
		assertNotNull(alimento.getTipo().getId());
	}
	
	private Alimento crearAlimento(String nombre, Double cantidad, Double stockMinimo, String tipo) {
		TipoAlimento tipoAlimento = new TipoAlimento();
		tipoAlimento.setNombre(tipo);
		
		this.sessionFactory.getCurrentSession().save(tipoAlimento);
		
		Alimento alimento = new Alimento();
		alimento.setNombre(nombre);
		alimento.setCantidad(cantidad);
		alimento.setStockMinimo(stockMinimo);
		alimento.setTipo(tipoAlimento);
		
		return alimento;
	}
}
