package ar.edu.unlam.tallerweb1.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Vacunar {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@ManyToOne
	private Vacuna vacuna;
	
	@ManyToOne
	private AnimalDeGranja animal;
	
	
	private Calendar fechaAplicacion;

	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Vacuna getVacuna() {
		return vacuna;
	}

	public void setVacuna(Vacuna vacuna) {
		this.vacuna = vacuna;
	}

	public Calendar getFechaAplicacion() {
		return fechaAplicacion;
	}

	public void setFechaAplicacion(Calendar fechaAplicacion) {
		this.fechaAplicacion = fechaAplicacion;
	}

	public AnimalDeGranja getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalDeGranja animal) {
		this.animal = animal;
	}
	
	
	
	
	
}
