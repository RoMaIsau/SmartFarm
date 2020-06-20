package ar.edu.unlam.tallerweb1.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Dieta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String nombre;
    
   private boolean pendiente=true;

	
	
	
	@OneToOne
	private AnimalDeGranja animal;

	private Calendar fechaYhora;
	
	@OneToOne(cascade= {CascadeType.ALL})
    private Alimento alimento;
    
    
    
	

	public boolean isPendiente() {
		return pendiente;
	}

	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}

	public AnimalDeGranja getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalDeGranja animal) {
		this.animal = animal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	public Calendar getFechaYhora() {
		return fechaYhora;
	}

	public void setFechaYhora(Calendar fechaYhora) {
		this.fechaYhora = fechaYhora;
	}

	

	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	
	
	
}
