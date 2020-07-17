package ar.edu.unlam.tallerweb1.persistencia;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.CronogramaDeAlimentacion;
import ar.edu.unlam.tallerweb1.modelo.CronogramaDeAlimentacion.EstadoCronograma;
import ar.edu.unlam.tallerweb1.modelo.Genero;
import ar.edu.unlam.tallerweb1.modelo.PlanAlimentario;
import ar.edu.unlam.tallerweb1.modelo.Raza;
import ar.edu.unlam.tallerweb1.modelo.TipoAlimento;
import ar.edu.unlam.tallerweb1.modelo.TipoAnimal;
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

	@Test
	public void deberiaObtenerUnAlimento() {
		Alimento arroz = this.crearAlimento("Arroz", 220.0, 50.0, "Granos");
		Alimento quinoa = this.crearAlimento("Quinoa", 125.0, 10.0, "Granos");

		this.repositorioAlimento.registrarAlimento(arroz);
		this.repositorioAlimento.registrarAlimento(quinoa);

		Alimento alimentoObtenido = this.repositorioAlimento.consultarAlimento(arroz);

		assertEquals(arroz, alimentoObtenido);
	}

	@Test
	public void deberiaListarTodosLosAlimentos() {
		Alimento arroz = this.crearAlimento("Arroz", 220.0, 50.0, "Granos");
		Alimento quinoa = this.crearAlimento("Quinoa", 125.0, 10.0, "Granos");

		this.repositorioAlimento.registrarAlimento(arroz);
		this.repositorioAlimento.registrarAlimento(quinoa);

		List<Alimento> alimentos = this.repositorioAlimento.listarAlimentos();

		assertThat(alimentos).hasSize(2);
	}
	
	@Test
	public void deberiaObtenerUnAlimentoPorId() {
		Alimento arroz = this.crearAlimento("Arroz", 220.0, 50.0, "Granos");
		Alimento quinoa = this.crearAlimento("Quinoa", 125.0, 10.0, "Granos");

		this.repositorioAlimento.registrarAlimento(arroz);
		this.repositorioAlimento.registrarAlimento(quinoa);
		
		Alimento alimento = this.repositorioAlimento.consultarAlimentoPorId(arroz.getId());
		
		assertEquals(arroz, alimento);
	}
	
	@Test
	public void deberiaActualizarLaCantidadDeAlimento() {
		Alimento arroz = this.crearAlimento("Arroz", 220.0, 50.0, "Granos");
		this.repositorioAlimento.registrarAlimento(arroz);
		
		arroz.setCantidad(300.0);
		
		this.repositorioAlimento.actualizarAlimento(arroz);
		
		Alimento alimentoObtenido = this.repositorioAlimento.consultarAlimento(arroz);
		
		assertThat(alimentoObtenido.getCantidad()).isEqualTo(300.0);
	}
	
	@Test
	public void deberiaListarAlimentosConsumidosPorUnAnimal() {
		Alimento arroz = this.crearAlimento("Arroz", 220.0, 50.0, "Granos");
		this.repositorioAlimento.registrarAlimento(arroz);
		
		CronogramaDeAlimentacion cronograma = this.crearCronograma(arroz);
		
		Long idAnimal = cronograma.getPlanAlimentario().getAnimal().getId();
		
		List<Alimento> alimentoConsumidos = this.repositorioAlimento.listarAlimentosConsumidosPorAnimal(idAnimal);
		
		assertThat(alimentoConsumidos).hasSize(1);
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
	
	private CronogramaDeAlimentacion crearCronograma(Alimento alimento) {
		TipoAnimal tipo = new TipoAnimal();
		tipo.setNombre("VACUNO");
		
		Raza raza = new Raza();
		raza.setTipo(tipo);
		raza.setNombre("Vaquita");
		
		Genero genero = new Genero();
		genero.setNombre("MACHO");
		
		Session session = this.sessionFactory.getCurrentSession();
		session.save(tipo);
		session.save(raza);
		session.save(genero);
		
		AnimalDeGranja animal = new AnimalDeGranja();
		animal.setTipo(tipo);
		animal.setRaza(raza);
		animal.setGenero(genero);
		animal.setIdentificadorGps("GPS0001");
		
		session.save(animal);
		
		PlanAlimentario plan = new PlanAlimentario();
		plan.setAnimal(animal);
		
		session.save(plan);
		
		CronogramaDeAlimentacion cronograma = new CronogramaDeAlimentacion();
		cronograma.setId(1L);
		cronograma.setPlanAlimentario(plan);
		cronograma.setAlimento(alimento);
		cronograma.setCantidad(10);
		cronograma.setEstado(EstadoCronograma.COMPLETO);
		
		session.save(cronograma);
		
		return cronograma;
	}
}
