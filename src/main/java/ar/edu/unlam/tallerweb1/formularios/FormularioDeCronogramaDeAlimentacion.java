package ar.edu.unlam.tallerweb1.formularios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Alimento;
import ar.edu.unlam.tallerweb1.modelo.AnimalDeGranja;
import ar.edu.unlam.tallerweb1.modelo.PlanAlimentario;

public class FormularioDeCronogramaDeAlimentacion {

	private AnimalDeGranja animal;
	private ItemDeCronograma itemCronograma;
	private List<Alimento> alimentos;
	private PlanAlimentario planAlimentario;

	public FormularioDeCronogramaDeAlimentacion() {
		this.itemCronograma = new ItemDeCronograma();
	}

	public void setAnimal(AnimalDeGranja animal) {
		this.animal = animal;
	}

	public AnimalDeGranja getAnimal() {
		return animal;
	}

	public void setItemCronograma(ItemDeCronograma itemCronograma) {
		this.itemCronograma = itemCronograma;
	}

	public ItemDeCronograma getItemCronograma() {
		return itemCronograma;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	public List<Alimento> getAlimentos() {
		return alimentos;
	}

	public void setPlanAlimentario(PlanAlimentario planAlimentario) {
		this.planAlimentario = planAlimentario;
	}

	public PlanAlimentario getPlanAlimentario() {
		return planAlimentario;
	}

	public void limpiarItemCronograma() {
		this.itemCronograma = new ItemDeCronograma();
	}
}
