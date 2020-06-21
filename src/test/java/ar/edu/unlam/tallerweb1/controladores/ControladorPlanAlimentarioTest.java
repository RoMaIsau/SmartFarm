package ar.edu.unlam.tallerweb1.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.formularios.FormularioDeCronogramaDeAlimentacion;
import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.CronogramaDeAlimentacion;
import ar.edu.unlam.tallerweb1.modelo.PlanAlimentario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlimento;
import ar.edu.unlam.tallerweb1.servicios.ServicioDeAnimales;
import ar.edu.unlam.tallerweb1.servicios.ServicioPlanAlimentario;
public class ControladorPlanAlimentarioTest {

	private ControladorPlanAlimentario controladorPlanAlimentario;
	private ServicioPlanAlimentario servicioPlanAlimentario;
	private ServicioDeAnimales servicioDeAnimales;
	private ServicioAlimento servicioAlimento;
	private final static Long ID_ANIMAL_VALIDO = 7L;
	private final static Long ID_PLAN_VALIDO = 1L;
	private final static Long ID_ALIMENTO_VALIDO = 5L;


	@Before
	public void inicializarControlador() {

		servicioDeAnimales = mock(ServicioDeAnimales.class);
		servicioAlimento = mock(ServicioAlimento.class);
		servicioPlanAlimentario = mock(ServicioPlanAlimentario.class);

		controladorPlanAlimentario = new ControladorPlanAlimentario(servicioDeAnimales, servicioAlimento, servicioPlanAlimentario);  
	}

	@Test
	public void crearPlanAlimentarioDebeRedirigirALaVistaPlanAlimentario() {
		ModelAndView modelAndView = this.controladorPlanAlimentario.crearPlanAlimentario(ID_ANIMAL_VALIDO);
		assertThat(modelAndView.getViewName()).isEqualTo("planAlimentario");
	}

	@Test
	public void cuandoSeCreeElPlanSeCargaTodoElCronograma() {
		AnimalDeGranja animal = this.crearAnimal(ID_ANIMAL_VALIDO);
		when(this.servicioDeAnimales.obtenerPorId(ID_ANIMAL_VALIDO)).thenReturn(animal);

		PlanAlimentario plan = this.crearPlan(ID_PLAN_VALIDO);
		when(this.servicioPlanAlimentario.buscarPlanPorAnimal(animal)).thenReturn(plan);

		ModelAndView modelAndView = this.controladorPlanAlimentario.crearPlanAlimentario(ID_ANIMAL_VALIDO);
		ModelMap modelo = modelAndView.getModelMap();
		List<CronogramaDeAlimentacion> cronograma = (List<CronogramaDeAlimentacion>) modelo.get("cronograma");

		assertThat(cronograma).isNotNull();
		verify(this.servicioPlanAlimentario).listarCronograma(eq(plan));
	}

	@Test
	public void seVerificaQueAlCrearUnCronogramaLlegueELFormularioCorrectamente() {

		Alimento maiz = this.crearAlimento(ID_ALIMENTO_VALIDO);
		List<Alimento> alimentos = new LinkedList<Alimento>();
		alimentos.add(maiz);
		when(this.servicioAlimento.listarAlimentos()).thenReturn(alimentos);
		
		AnimalDeGranja animal = this.crearAnimal(ID_ANIMAL_VALIDO);
		when(this.servicioDeAnimales.obtenerPorId(ID_ANIMAL_VALIDO)).thenReturn(animal);

		PlanAlimentario plan = this.crearPlan(ID_PLAN_VALIDO);
		when(this.servicioPlanAlimentario.buscarPlanPorAnimal(animal)).thenReturn(plan);

		ModelAndView modelAndView = this.controladorPlanAlimentario.crearPlanAlimentario(ID_ANIMAL_VALIDO);
		ModelMap modelo = modelAndView.getModelMap();
		FormularioDeCronogramaDeAlimentacion formulario = (FormularioDeCronogramaDeAlimentacion) modelo.get("formulario");
		
		assertThat(formulario).isNotNull();
		assertThat(formulario.getAlimentos()).contains(maiz);
		assertThat(formulario.getAnimal()).isEqualTo(animal);

		verify(this.servicioPlanAlimentario).listarCronograma(eq(plan));
	}

	@Test
	public void cuandoSeCreaElPlanSeDebeVerificarElEstadoDelCronogrma() {
		AnimalDeGranja animal = this.crearAnimal(ID_ANIMAL_VALIDO);
		when(this.servicioDeAnimales.obtenerPorId(ID_ANIMAL_VALIDO)).thenReturn(animal);

		PlanAlimentario plan = this.crearPlan(ID_PLAN_VALIDO);
		when(this.servicioPlanAlimentario.buscarPlanPorAnimal(animal)).thenReturn(plan);

		this.controladorPlanAlimentario.crearPlanAlimentario(ID_ANIMAL_VALIDO);

		verify(this.servicioPlanAlimentario).vencerCronogramasSinCompletar(eq(plan));
	}
	
	private AnimalDeGranja crearAnimal(Long idAnimal) {
		AnimalDeGranja animal = new AnimalDeGranja();
		animal.setId(idAnimal);
		return animal;
	}
	
	private Alimento crearAlimento(Long idAlimento) {
		Alimento alimento = new Alimento();
		alimento.setId(idAlimento);
		return alimento;
	}
	
	private PlanAlimentario crearPlan(Long idPlan) {
		PlanAlimentario plan = new PlanAlimentario();
		plan.setId(idPlan);
		return plan;
	}
}