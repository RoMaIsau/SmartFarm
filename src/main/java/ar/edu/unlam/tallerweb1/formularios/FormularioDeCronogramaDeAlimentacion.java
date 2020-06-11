package ar.edu.unlam.tallerweb1.formularios;

import java.util.LinkedList;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.CronogramaDeAlimentacion;

public class FormularioDeCronogramaDeAlimentacion {

	private AnimalDeGranja animal;
	private List<CronogramaDeAlimentacion> cronogramaDeAlimentacion;

	public FormularioDeCronogramaDeAlimentacion() {
		this.cronogramaDeAlimentacion = new LinkedList<CronogramaDeAlimentacion>();
		CronogramaDeAlimentacion cronograma = new CronogramaDeAlimentacion();
		this.cronogramaDeAlimentacion.add(cronograma);
	}

	public void setAnimal(AnimalDeGranja animal) {
		this.animal = animal;
	}

	public AnimalDeGranja getAnimal() {
		return animal;
	}

	public void setCronogramaDeAlimentacion(List<CronogramaDeAlimentacion> cronogramaDeAlimentacion) {
		this.cronogramaDeAlimentacion = cronogramaDeAlimentacion;
	}

	public List<CronogramaDeAlimentacion> getCronogramaDeAlimentacion() {
		return cronogramaDeAlimentacion;
	}
}
