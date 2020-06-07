package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	private Genero genero;
	
	@OneToOne
	private Location localizacion;
	
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
	
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	
	public Genero getGenero() {
		return genero;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	@Override
    public boolean equals(Object objeto) {

        boolean iguales = (this == objeto);

        if (! iguales && (objeto != null) && this.getClass().isAssignableFrom(objeto.getClass())) {

            AnimalDeGranja otroAnimal = AnimalDeGranja.class.cast(objeto);

            iguales = (this.id != null) && (otroAnimal.id != null) &&
                      this.id.equals(otroAnimal.id);
        }
        return iguales;
    }

	@Override
    public int hashCode() {

        return this.id != null ? this.id.hashCode() : super.hashCode();
    }

	@Override
    public String toString() {

        return this.getClass().getSimpleName() + "[id: " + this.id + "]";
    }
}