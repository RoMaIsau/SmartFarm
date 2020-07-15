package ar.edu.unlam.tallerweb1.persistencia;

import java.util.List;

import javax.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Gastos;
import ar.edu.unlam.tallerweb1.modelo.TipoDeGasto;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGastos;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioGastosImpl;

@Transactional
public class RepositorioGastosTest extends SpringTest{
	
	private RepositorioGastos repositorioGastos;
	
	@Before
	public void inicializar() {
		
		this.repositorioGastos = new RepositorioGastosImpl(this.sessionFactory);
	}
	
	@Test
	public void deberiaObtenerTodosLosGastos() {
		
		TipoDeGasto tipo = new TipoDeGasto();
		tipo.setNombre("Alimentario");
		
		this.sessionFactory.getCurrentSession().save(tipo);
		
		Gastos gastoUno = new Gastos();
		gastoUno.setTipoDeGasto(tipo);
		
		Gastos gastoDos = new Gastos();
		gastoDos.setTipoDeGasto(tipo);
		
		this.repositorioGastos.guardarNuevoRegistro(gastoUno);
		this.repositorioGastos.guardarNuevoRegistro(gastoDos);
		
		List<Gastos> gastosObtenidos = this.repositorioGastos.consultarGastos();
		
		assertThat(gastosObtenidos).hasSize(2);
	}
	
	@Test
	public void deberiaObtenerGastoPorUsuario() {
		TipoDeGasto tipo = new TipoDeGasto();
		tipo.setNombre("Alimentario");
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		
		Session session = this.sessionFactory.getCurrentSession();
		session.save(tipo);
		session.save(usuario);
		
		Gastos gastoUno = new Gastos();
		gastoUno.setTipoDeGasto(tipo);
		gastoUno.setUsuario(usuario);
		
		this.repositorioGastos.guardarNuevoRegistro(gastoUno);
		
		List<Gastos> gastosObtenidos = this.repositorioGastos.consultarGastosPorUsuario(usuario);
		
		assertThat(gastosObtenidos).hasSize(1);
		assertThat(gastosObtenidos).extracting("tipoDeGasto").extracting("nombre").contains("Alimentario");
	}
	
	@Test 
	public void deberiaGuardarUnNuevoGgasto() {
		Gastos gastoUno = new Gastos();
		gastoUno.setId(1L);
		
		Long idGastoObtenido = this.repositorioGastos.guardarNuevoRegistro(gastoUno);
		
		assertThat(idGastoObtenido).isEqualTo(gastoUno.getId());
	}
	
	@Test
	public void deberiaObtenerGastoPorId() {
		Gastos gastoUno = new Gastos();
		Gastos gastoDos = new Gastos();
		
		this.repositorioGastos.guardarNuevoRegistro(gastoUno);
		this.repositorioGastos.guardarNuevoRegistro(gastoDos);
		
		Gastos gastoObtenido = this.repositorioGastos.consultaGastosPorID(gastoUno.getId());
		
		assertEquals(gastoUno, gastoObtenido);
	}
	
	@Test
	public void deberiaEliminarUnGasto() {
		Gastos gastoUno = new Gastos();
		gastoUno.setId(1L);
	
		this.repositorioGastos.guardarNuevoRegistro(gastoUno);
		
		assertNotNull(this.repositorioGastos.consultaGastosPorID(gastoUno.getId()));
		
		this.repositorioGastos.eliminarGastos(gastoUno);
		
		assertNull(this.repositorioGastos.consultaGastosPorID(gastoUno.getId()));
	}
	
}
