package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class HistoriaClinica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
    AnimalDeGranja animal;
	
	@OneToOne
    Enfermedad enfermedad;
	
	
	
	
	
	



	public Enfermedad getEnfermedad() {
		return enfermedad;
	}

	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}

	public AnimalDeGranja getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalDeGranja animal) {
		this.animal = animal;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	


	
	
	

}
