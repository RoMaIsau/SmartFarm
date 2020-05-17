package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Raza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	@ManyToOne
	private TipoAnimal tipo;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public TipoAnimal getTipo() {
		return tipo;
	}
	
	public void setTipo(TipoAnimal tipo) {
		this.tipo = tipo;
	}
	
	@Override
    public boolean equals(Object objeto) {

        boolean iguales = (this == objeto);
        
        if (! iguales && (objeto != null) && this.getClass().isAssignableFrom(objeto.getClass())) {
            
            Raza otraRaza = Raza.class.cast(objeto);
            
            iguales = (this.id != null) && (otraRaza.id != null) && 
                      this.id.equals(otraRaza.id);       
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
