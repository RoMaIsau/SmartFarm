package ar.edu.unlam.tallerweb1.servicios;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.excepciones.NoSePudoCompletarCronogramaException;
import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.CronogramaDeAlimentacion;
import ar.edu.unlam.tallerweb1.modelo.PlanAlimentario;
import ar.edu.unlam.tallerweb1.modelo.CronogramaDeAlimentacion.EstadoCronograma;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioPlanAlimentario;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ServicioPlanAlimentarioImpl implements ServicioPlanAlimentario {

	private static final String DESCRIPCION_DEFAULT = "Plan alimentario";
	private RepositorioPlanAlimentario repositorioPlanAlimentario;
	private ServicioAlimento servicioAlimento;

	@Autowired
	public ServicioPlanAlimentarioImpl(RepositorioPlanAlimentario repositorioPlanAlimentario, ServicioAlimento servicioAlimento) {
		this.repositorioPlanAlimentario = repositorioPlanAlimentario;
		this.servicioAlimento = servicioAlimento;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void crearPlan(AnimalDeGranja animal) {

		PlanAlimentario plan = new PlanAlimentario();
		plan.setAnimal(animal);
		plan.setFechaDeCreacion(new Date());
		plan.setDescripcion(DESCRIPCION_DEFAULT);

		this.repositorioPlanAlimentario.guardarPlan(plan);
	}

	@Override
	public PlanAlimentario buscarPlanPorAnimal(AnimalDeGranja animal) {
		return this.repositorioPlanAlimentario.buscarPlanPorAnimal(animal);
	}

	@Override
	public List<CronogramaDeAlimentacion> listarCronograma(PlanAlimentario plan) {
		return this.repositorioPlanAlimentario.listarCronograma(plan);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void agregarCronograma(CronogramaDeAlimentacion cronograma) {
		cronograma.setEstado(EstadoCronograma.PENDIENTE);
		this.repositorioPlanAlimentario.guardarItemDeCronograma(cronograma);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void eliminarCronograma(CronogramaDeAlimentacion cronograma) {
		this.repositorioPlanAlimentario.eliminarCronograma(cronograma);
	}

	@Override
	public CronogramaDeAlimentacion buscarCronograma(Long idCronograma) {
		return this.repositorioPlanAlimentario.buscarCronograma(idCronograma);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void actualizarCronograma(CronogramaDeAlimentacion cronograma) {
		this.repositorioPlanAlimentario.actualizarCronograma(cronograma);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void terminarCronograma(CronogramaDeAlimentacion cronograma) throws NoSePudoCompletarCronogramaException {

		cronograma = this.repositorioPlanAlimentario.buscarCronograma(cronograma.getId());

		int cantidad = cronograma.getCantidad();
		Alimento alimento = cronograma.getAlimento();

		Double stock = this.servicioAlimento.consultarStock(alimento);

		if(stock >= cronograma.getCantidad()) {
			this.servicioAlimento.decrementarStock(alimento, cantidad);
			cronograma.setEstado(EstadoCronograma.COMPLETO);
			this.repositorioPlanAlimentario.actualizarCronograma(cronograma);
		} else {
			throw new NoSePudoCompletarCronogramaException("Stock insuficiente");
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void eliminarPlan(AnimalDeGranja animal) {

		PlanAlimentario plan = buscarPlanPorAnimal(animal);
		if (plan != null) {
			List<CronogramaDeAlimentacion> cronogramaDeAlimentacion = listarCronograma(plan);
			for(CronogramaDeAlimentacion cronograma :cronogramaDeAlimentacion) {
				eliminarCronograma(cronograma);
			}

			this.repositorioPlanAlimentario.eliminarPlan(plan);
		}
	}
}