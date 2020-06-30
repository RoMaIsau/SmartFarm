package ar.edu.unlam.tallerweb1.modelo;

import java.util.Comparator;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Enfermedad implements Comparable<Enfermedad> {

	// La anotacion id indica que este atributo es el utilizado como clave primaria de la entity, se indica que el valor es autogenerado.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private Date fecha;
	
	@ManyToOne
    HistoriaClinica historia;



	public HistoriaClinica getHistoria() {
		return historia;
	}

	public void setHistoria(HistoriaClinica historia) {
		this.historia = historia;
	}

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	

	@Override
	public int compareTo(Enfermedad e) {
		// TODO Auto-generated method stub
		 return e.nombre.compareTo(this.nombre);
	}
      
    
}
