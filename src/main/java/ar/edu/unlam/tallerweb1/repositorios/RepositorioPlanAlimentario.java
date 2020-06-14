package ar.edu.unlam.tallerweb1.repositorios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.CronogramaDeAlimentacion;
import ar.edu.unlam.tallerweb1.modelo.PlanAlimentario;

public interface RepositorioPlanAlimentario {

	void guardarPlan(PlanAlimentario plan);

	void guardarItemDeCronograma(CronogramaDeAlimentacion itemCronograma);

	PlanAlimentario buscarPlanPorAnimal(AnimalDeGranja animal);

	List<CronogramaDeAlimentacion> listartCronograma(PlanAlimentario plan);

	void eliminarCronograma(CronogramaDeAlimentacion cronograma);

}
