package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AnimalDeGranja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false)
	private TipoAnimal tipo;
	
	@ManyToOne(optional = false)
	private Raza raza;
	
	@ManyToOne(optional = false)
	private Sexo sexo;
	
	private Double peso;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public TipoAnimal getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoAnimal tipo) {
		this.tipo = tipo;
	}

	public void setRaza(Raza raza) {
		this.raza = raza;		
	}
	
	public Raza getRaza() {
		return raza;
	}
	
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	
	public Sexo getSexo() {
		return sexo;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

}
