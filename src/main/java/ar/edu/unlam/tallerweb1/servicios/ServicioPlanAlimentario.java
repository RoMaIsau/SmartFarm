package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.excepciones.NoSePudoCompletarCronogramaException;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.CronogramaDeAlimentacion;
import ar.edu.unlam.tallerweb1.modelo.PlanAlimentario;

public interface ServicioPlanAlimentario {
	
	void crearPlan(AnimalDeGranja animal);

	PlanAlimentario buscarPlanPorAnimal(AnimalDeGranja animal);

	List<CronogramaDeAlimentacion> listarCronograma(PlanAlimentario plan);

	void agregarCronograma(CronogramaDeAlimentacion cronograma);

	void eliminarCronograma(CronogramaDeAlimentacion cronograma);

	CronogramaDeAlimentacion buscarCronograma(Long idCronograma);

	void actualizarCronograma(CronogramaDeAlimentacion cronograma);

	void terminarCronograma(CronogramaDeAlimentacion cronograma) throws NoSePudoCompletarCronogramaException;

	void eliminarPlan(AnimalDeGranja animal);

	void vencerCronogramasSinCompletar(PlanAlimentario plan);
}
