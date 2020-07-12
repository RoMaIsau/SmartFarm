package ar.edu.unlam.tallerweb1.modelo;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AnimalUbicacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	private AnimalDeGranja animal;

	@ManyToOne(optional = false)
	private Ubicacion ultimaUbicacion;

	private LocalDateTime fecha;
	
	private Integer metrosRecorridos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AnimalDeGranja getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalDeGranja animal) {
		this.animal = animal;
	}

	public Ubicacion getUltimaUbicacion() {
		return ultimaUbicacion;
	}

	public void setUltimaUbicacion(Ubicacion ultimaUbicacion) {
		this.ultimaUbicacion = ultimaUbicacion;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Integer getMetrosRecorridos() {
		return metrosRecorridos;
	}

	public void setMetrosRecorridos(Integer metrosRecorridos) {
		this.metrosRecorridos = metrosRecorridos;
	}
	
	

}
